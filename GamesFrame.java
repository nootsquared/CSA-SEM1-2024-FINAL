import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GamesFrame extends JFrame {
    GamesFrame(Color backgroundColor, JFrame mainFrame) {

        

        Font montserrat20 = FontLoader.loadFont("fonts/ElectronicHighwaySign.TTF", 22f);
        Font montserrat10 = FontLoader.loadFont("fonts/ElectronicHighwaySign.TTF", 18f);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(null);
        CustomButton button = new CustomButton("5 Minute Mayhem", 350, 350, 175, 70);
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
        gamesLabel.setBounds(0, 20, 700, 30);
        gamesLabel.setHorizontalAlignment(JLabel.CENTER);
        gamesLabel.setForeground(Color.WHITE);
        this.getContentPane().add(gamesLabel);

        
        JPanel statsPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
        };
        statsPanel.setBounds(255, 450, 175, 100);
        statsPanel.setBackground(new Color(50, 50, 50));
        
        AbstractBorder roundedBorder = new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(25, 25, 25));
                g2d.setStroke(new BasicStroke(3)); // Set the thickness of the border
                g2d.drawRoundRect(x, y, width-1, height-1, 15, 15);
            }
        };
        statsPanel.setBorder(roundedBorder);

        // Create the coins label
        JLabel coinsLabel = new JLabel("Coins: 20"); // Replace 100 with the actual coins variable
        coinsLabel.setFont(montserrat10);
        coinsLabel.setForeground(Color.WHITE);

        // Create the ec label
        JLabel ecLabel = new JLabel("Grade: 100"); // Replace 200 with the actual ec variable
        ecLabel.setFont(montserrat10);
        ecLabel.setForeground(Color.WHITE);

        // Create a GridBagConstraints object to specify where each component should be displayed
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, -20, 0); // Add this line

        // Add the labels to the panel
        statsPanel.add(coinsLabel, gbc);

        gbc.insets = new Insets(-10, 0, 0, 0); // Add this line
        statsPanel.add(ecLabel, gbc);

        // Add the panel to the content pane
        this.getContentPane().add(statsPanel);

        
    }
}