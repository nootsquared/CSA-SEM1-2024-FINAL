import javax.swing.*;
import java.awt.*;

public class GamesFrame extends JFrame {
    GamesFrame(Color backgroundColor, JFrame mainFrame) {

        Font montserrat20 = FontLoader.loadFont("/Montserrat-Medium.ttf", 20f);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(null);
        CustomButton button = new CustomButton("Car Game", 200, 50, 250, 70);
        this.getContentPane().add(button);

        CustomButton standby = new CustomButton("Nothing", 200, 50, 250, 130);
        this.getContentPane().add(standby);
        CustomButton standby2 = new CustomButton("Nothing", 200, 50, 250, 190);
        this.getContentPane().add(standby2);
        CustomButton standby3 = new CustomButton("Nothing", 200, 50, 250, 250);
        this.getContentPane().add(standby3);
        CustomButton standby4 = new CustomButton("Nothing", 200, 50, 250, 310);
        this.getContentPane().add(standby4);
        CustomButton standby5 = new CustomButton("Nothing", 200, 50, 250, 370);
        this.getContentPane().add(standby5);
        CustomButton standby6 = new CustomButton("Nothing", 200, 50, 250, 430);
        this.getContentPane().add(standby6);

        BackButton backButton = new BackButton(this, mainFrame);
        this.getContentPane().add(backButton);

        JLabel gamesLabel = new JLabel("Games");
        gamesLabel.setFont(montserrat20);
        gamesLabel.setBounds(0, 15, 700, 30);
        gamesLabel.setHorizontalAlignment(JLabel.CENTER);
        gamesLabel.setForeground(Color.WHITE);
        this.getContentPane().add(gamesLabel);
    }
}