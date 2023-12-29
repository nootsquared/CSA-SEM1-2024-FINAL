import javax.swing.*;
import java.awt.*;

public class GamesFrame extends JFrame {
    GamesFrame(Color backgroundColor, JFrame mainFrame) {

        Font montserrat20 = FontLoader.loadFont("/Montserrat-Medium.ttf", 20f);

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