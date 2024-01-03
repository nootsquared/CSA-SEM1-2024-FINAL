import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GamesFrame extends JFrame {
    private JLabel coinsLabel;
    CoinCount coinCount = new CoinCount("coin.txt");
    GamesFrame(Color backgroundColor, JFrame mainFrame) {
        Font montserrat20 = FontLoader.loadFont("fonts/ElectronicHighwaySign.TTF", 22f);
        Font montserrat10 = FontLoader.loadFont("fonts/ElectronicHighwaySign.TTF", 18f);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);

        this.setLayout(null);
        CustomButton button = new CustomButton("5 Minute Mayhem (-10)", 350, 350, 175, 70);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (coinCount.getCurrentCount() - 1 >= 0) {
                        try {
                            String unityGamePath = "myfinal\\5MinuteMayham.exe";
                            String width = "1280";
                            String height = "720";
                            ProcessBuilder pb = new ProcessBuilder(unityGamePath, "-screen-width", width, "-screen-height", height);
                            Process unityGameProcess = pb.start();
                            GamesFrame.this.setVisible(false);
                            unityGameProcess.waitFor();
                            GamesFrame.this.setVisible(true);
            
                            coinCount.changeCount(-10);
            
                        } catch (IOException | InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                } 
                catch (IOException ex) {
                    ex.printStackTrace();
                }
                    
                }
            }
        );
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
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRoundRect(x, y, width-1, height-1, 15, 15);
            }
        };
        statsPanel.setBorder(roundedBorder);
        int coins = 20;
        int grade = 100;

        int currentCount = 0;
        try {
            currentCount = coinCount.getCurrentCount();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file");
        }
        JLabel coinsLabel = new JLabel("Coins: " + currentCount);
        coinsLabel.setFont(montserrat10);
        coinsLabel.setForeground(Color.WHITE);

        GradeCount gradeCount = new GradeCount("grade.txt");
        int currentGrade = 0;
        try {
             currentGrade = gradeCount.getCurrentGrade();
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file");
        }       
        JLabel ecLabel = new JLabel("Grade: " + currentGrade + "%");
        ecLabel.setFont(montserrat10);
        ecLabel.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, -20, 0);
        statsPanel.add(coinsLabel, gbc);
        gbc.insets = new Insets(-10, 0, 0, 0);
        statsPanel.add(ecLabel, gbc);
        this.getContentPane().add(statsPanel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                // Update the coins label when the window is activated
                try {
                    coinsLabel.setText("Coins: " + coinCount.getCurrentCount());
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error reading from file");
                }
            }
        });
    }
    public void updateCoinsLabel() {
        int currentCount = 0;
        try {
            currentCount = coinCount.getCurrentCount();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file");
        }
        coinsLabel.setText("Coins: " + currentCount);
    }   
    
}