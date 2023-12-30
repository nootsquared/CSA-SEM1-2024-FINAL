import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GamesFrame extends JFrame {
    GamesFrame(Color backgroundColor, JFrame mainFrame) {

        Font montserrat20 = FontLoader.loadFont("fonts/ElectronicHighwaySign.TTF", 22f);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(null);
        CustomButton button = new CustomButton("Car Game", 350, 350, 175, 70);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Path to the Unity game's executable
                    String unityGamePath = "myfinal\\5MinuteMayham.exe";

                    String width = "1280";
                    String height = "720";
                    // Start the Unity game
                    ProcessBuilder pb = new ProcessBuilder(unityGamePath, "-screen-width", width, "-screen-height", height);
                    Process unityGameProcess = pb.start();

                    // Hide the GamesFrame
                    GamesFrame.this.setVisible(false);

                    // Wait for the Unity game to close
                    unityGameProcess.waitFor();

                    // Reopen the GamesFrame
                    GamesFrame.this.setVisible(true);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.getContentPane().add(button);

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