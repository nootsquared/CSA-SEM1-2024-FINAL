import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class BallFrame extends JFrame {
    private BackButton backButton; // Declaration of backButton object
    private BouncingBall bouncingBall; // Declaration of bouncingBall object
    public int frameWidth; // Declaration of frameWidth variable
    public int frameHeight; // Declaration of frameHeight variable

    public BallFrame(Color backgroundColor, JFrame mainFrame) {
        this.getContentPane().setBackground(backgroundColor);
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.getContentPane().setLayout(null);
        backButton = new BackButton(this, mainFrame); // Creating an instance of BackButton
        backButton.setBounds(10, 10, 100, 50);
        backButton.setOpaque(false);
        this.getContentPane().add(backButton);

        bouncingBall = new BouncingBall(this); // Creating an instance of BouncingBall
        bouncingBall.setBounds(0, 0, 700, 700);
        bouncingBall.setOpaque(false);
        this.getContentPane().add(bouncingBall);

        this.addComponentListener(new ComponentAdapter() { // Adding a ComponentListener
            @Override
            public void componentResized(ComponentEvent e) {
                frameWidth = getWidth(); // Assigning the width of the frame to frameWidth
                frameHeight = getHeight(); // Assigning the height of the frame to frameHeight
                System.out.println("Frame width: " + frameWidth + ", Frame height: " + frameHeight);
            }
        });
    }
}

/**
 * Represents a bouncing ball in a BallFrame.
 */
class BouncingBall extends JPanel {
    double x = 0, y = 0, vx = 2, vy = 0; // Declaration of variables
    double gravity = 0.1, friction = 0.01, restitution = 0.7; // Declaration of variables
    int radius = 25; // Declaration of radius variable
    int groundHeight; // Declaration of groundHeight variable
    BallFrame ballFrame; // Declaration of ballFrame object
    private boolean isDragging = false; // Declaration of isDragging variable

    public BouncingBall(BallFrame ballFrame) {
        this.ballFrame = ballFrame; // Assigning the passed BallFrame object to ballFrame

        Timer timer = new Timer(5, e -> { // Creating a Timer
            //  gravity
            vy += gravity;

            // Apply friction
            vx *= (1 - friction);
            vy *= (1 - friction);

            // Update pos
            x += vx;
            y += vy;

            // Check for collision with edge
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

        this.addMouseMotionListener(new MouseAdapter() { // Adding a MouseMotionListener
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    x = e.getX() - radius;
                    y = e.getY() - radius;
                }
            }
        });

        this.addMouseListener(new MouseAdapter() { // Adding a MouseListener
            @Override
            public void mousePressed(MouseEvent e) {
                double distance = Math.hypot(e.getX() - x, e.getY() - y);
                if (distance < radius) {
                    isDragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillOval((int) (x - radius), (int) (y - radius), 2 * radius, 2 * radius);

        int groundY = ballFrame.frameHeight - groundHeight;
        int lineThickness = 5;
        for (int i = 0; i < lineThickness; i++) {
            g.drawLine(0, groundY + i, ballFrame.frameWidth, groundY + i);
        }
    }
}