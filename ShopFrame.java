import javax.swing.*;
import java.awt.*;

public class ShopFrame extends JFrame {
    ShopFrame(Color backgroundColor, JFrame mainFrame) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(null);
        BackButton backButton = new BackButton(this, mainFrame);
        this.getContentPane().add(backButton);

    }
}