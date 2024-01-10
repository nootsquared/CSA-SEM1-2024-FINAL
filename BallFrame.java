import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BallFrame extends JFrame {
    private BackButton backButton;
    private BouncingBall bouncingBall;
    public int frameWidth;
    public int frameHeight;

    public BallFrame(Color backgroundColor, JFrame mainFrame) {
        this.getContentPane().setBackground(backgroundColor);
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.getContentPane().setLayout(null);
        backButton = new BackButton(this, mainFrame);
        backButton.setBounds(10, 10, 100, 50);
        backButton.setOpaque(false); // Make backButton transparent
        this.getContentPane().add(backButton);

        bouncingBall = new BouncingBall(this);
        bouncingBall.setBounds(0, 0, 700, 700);
        bouncingBall.setOpaque(false); // Make bouncingBall transparent
        this.getContentPane().add(bouncingBall);

        // Add a component listener to update the size of the frame whenever it's resized
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frameWidth = getWidth();
                frameHeight = getHeight();
                System.out.println("Frame width: " + frameWidth + ", Frame height: " + frameHeight);
            }
        });
    }
}

class BouncingBall extends JPanel {
    double x = 0, y = 0, vx = 2, vy = 0;
    double gravity = 0.1, friction = 0.01, restitution = 0.7;
    int radius = 25;
    int groundHeight; // Height of the ground from the bottom of the frame
    BallFrame ballFrame;

    public BouncingBall(BallFrame ballFrame) {
        this.ballFrame = ballFrame;
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
            } else if (x + radius > ballFrame.frameWidth) {
                x = ballFrame.frameWidth - radius;
                vx = -vx * restitution;
            }
            if (y - radius < 0) {
                y = radius;
                vy = -vy * restitution;
            } else if (y + radius > ballFrame.frameHeight - groundHeight) {
                y = ballFrame.frameHeight - groundHeight - radius;
                vy = -vy * restitution;
            }

            // Update the ground height based on the frame height
            groundHeight = ballFrame.frameHeight / 5;

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