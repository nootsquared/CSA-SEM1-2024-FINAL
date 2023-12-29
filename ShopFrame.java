import javax.swing.*;
import java.awt.*;

public class ShopFrame extends JFrame {
    ShopFrame(Color backgroundColor, JFrame mainFrame) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);
        BackButton backButton = new BackButton(this, mainFrame);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(backButton, BorderLayout.WEST);
        this.add(panel, BorderLayout.NORTH);
    }
}