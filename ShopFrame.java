import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShopFrame extends JFrame {
    private JLabel coinsLabel; // JLabel coinsLabel
    CoinCount coinCount = new CoinCount("coin.txt"); // CoinCount coinCount
    GradeCount gradeCount = new GradeCount("grade.txt"); // GradeCount gradeCount

    ShopFrame(Color backgroundColor, JFrame mainFrame) {
        coinsLabel = new JLabel(); // JLabel coinsLabel
        this.getContentPane().add(coinsLabel); // this.getContentPane().add(coinsLabel)

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);

        Font montserrat20 = FontLoader.loadFont("fonts/ElectronicHighwaySign.TTF", 22f); // Font montserrat20
        Font montserrat10 = FontLoader.loadFont("fonts/ElectronicHighwaySign.TTF", 18f); // Font montserrat10

        this.setLayout(null);
        BackButton backButton = new BackButton(this, mainFrame); // BackButton backButton
        this.getContentPane().add(backButton); // this.getContentPane().add(backButton)

        JPanel statsPanel = new JPanel(new GridBagLayout()) { // JPanel statsPanel
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            }
        };
        statsPanel.setBounds(255, 110, 175, 100);
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

        int currentCount = 0;
        try {
            currentCount = coinCount.getCurrentCount(); // coinCount.getCurrentCount()
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file");
        }
        JLabel coinsLabel = new JLabel("Coins: " + currentCount); // JLabel coinsLabel
        coinsLabel.setFont(montserrat10);
        coinsLabel.setForeground(Color.WHITE);

        GradeCount gradeCount = new GradeCount("grade.txt"); // GradeCount gradeCount
        int currentGrade = 0;
        try {
             currentGrade = gradeCount.getCurrentGrade(); // gradeCount.getCurrentGrade()
        } 
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file");
        }       
        JLabel ecLabel = new JLabel("Grade: " + currentGrade+"%"); // JLabel ecLabel
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
        this.getContentPane().add(statsPanel); // this.getContentPane().add(statsPanel)

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    coinsLabel.setText("Coins: " + coinCount.getCurrentCount()); // coinsLabel.setText("Coins: " + coinCount.getCurrentCount())
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error reading from file");
                }
            }
        });

        int statsPanelX = 255;
        int statsPanelWidth = 175;
        int buttonWidth = 200;

        int buttonX = statsPanelX + (statsPanelWidth - buttonWidth) / 2;
        int buttonHeight = 50;

        CustomButton button1 = new CustomButton("+1 Coin", buttonWidth, buttonHeight, buttonX, 220); // CustomButton button1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    coinCount.changeCount(1); // coinCount.changeCount(1)
                    gradeCount.changeGrade(1); // gradeCount.changeGrade(1)
                    coinsLabel.setText("Coins: " + coinCount.getCurrentCount()); // coinsLabel.setText("Coins: " + coinCount.getCurrentCount())
                    ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%"); // ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%")
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error reading from file");
                }
            }
        });
        this.getContentPane().add(button1); // this.getContentPane().add(button1)

        CustomButton button2 = new CustomButton("+5 Coins", buttonWidth, buttonHeight, buttonX, 270); // CustomButton button2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    coinCount.changeCount(5); // coinCount.changeCount(5)
                    gradeCount.changeGrade(5); // gradeCount.changeGrade(5)
                    coinsLabel.setText("Coins: " + coinCount.getCurrentCount()); // coinsLabel.setText("Coins: " + coinCount.getCurrentCount())
                    ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%"); // ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%")
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error reading from file");
                }
            }
        });
        this.getContentPane().add(button2); // this.getContentPane().add(button2)

        CustomButton button3 = new CustomButton("+10 Coins", buttonWidth, buttonHeight, buttonX, 320); // CustomButton button3
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    coinCount.changeCount(10); // coinCount.changeCount(10)
                    gradeCount.changeGrade(10); // gradeCount.changeGrade(10)
                    coinsLabel.setText("Coins: " + coinCount.getCurrentCount()); // coinsLabel.setText("Coins: " + coinCount.getCurrentCount())
                    ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%"); // ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%")
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error reading from file");
                }
            }
        });
        this.getContentPane().add(button3); // this.getContentPane().add(button3)

        CustomButton button4 = new CustomButton("+20 Coins", buttonWidth, buttonHeight, buttonX, 370); // CustomButton button4
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    coinCount.changeCount(20); // coinCount.changeCount(20)
                    gradeCount.changeGrade(20); // gradeCount.changeGrade(20)
                    coinsLabel.setText("Coins: " + coinCount.getCurrentCount()); // coinsLabel.setText("Coins: " + coinCount.getCurrentCount())
                    ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%"); // ecLabel.setText("Grade: " + gradeCount.getCurrentGrade()+"%")
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Error reading from file");
                }
            }
        });
        this.getContentPane().add(button4); // this.getContentPane().add(button4)

        JLabel titleLabel = new JLabel("Shop"); // JLabel titleLabel
        titleLabel.setFont(montserrat20);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(325, 10, 100, 50);
        this.getContentPane().add(titleLabel); // this.getContentPane().add(titleLabel)

    }

    public void updateCoinsLabel() {
        int currentCount = 0;
        try {
            currentCount = coinCount.getCurrentCount(); // coinCount.getCurrentCount()
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading from file");
        }
        coinsLabel.setText("Coins: " + currentCount); // coinsLabel.setText("Coins: " + currentCount)
    }   
}
