import javax.swing.*;
import java.awt.*;

public class BallFrame extends JFrame {
    private BackButton backButton;
    private BouncingBall bouncingBall;

    public BallFrame(Color backgroundColor, JFrame mainFrame) {
        this.getContentPane().setBackground(backgroundColor);
        this.setSize(700, 700);

        this.getContentPane().setLayout(null);
        backButton = new BackButton(this, mainFrame);
        backButton.setBounds(10, 10, 100, 50);
        this.getContentPane().add(backButton);

        bouncingBall = new BouncingBall();
        bouncingBall.setBounds(0, 0, 700, 700);
        this.getContentPane().add(bouncingBall);
    }
}

class BouncingBall extends JPanel {
    double x = 0, y = 0, vx = 2, vy = 0;
    double gravity = 0.1, friction = 0.01, restitution = 0.7;
    int radius = 25;

    public BouncingBall() {
        Timer timer = new Timer(5, e -> {
            // Apply gravity
            vy += gravity;

            // Apply friction
            vx *= (1 - friction);
            vy *= (1 - friction);

            // Update position
            x += vx;
            y += vy;

            // Check for collision with edges
            if (x - radius < 0) {
                x = radius;
                vx = -vx * restitution;
            } else if (x + radius > getWidth()) {
                x = getWidth() - radius;
                vx = -vx * restitution;
            }
            if (y - radius < 0) {
                y = radius;
                vy = -vy * restitution;
            } else if (y + radius > getHeight()) {
                y = getHeight() - radius;
                vy = -vy * restitution;
            }

            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillOval((int)(x - radius), (int)(y - radius), 2 * radius, 2 * radius);
    }
}