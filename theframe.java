import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class theframe extends JFrame {
    theframe() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        Font EHS = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 20f);

        JLabel label = new JLabel("Welcome to the panel. What do you want to do?", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(EHS);
        label.setBorder(new EmptyBorder(20, 0, 0, 0));
        this.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JPanel gridPanel = new JPanel(new GridLayout(2, 2));
        gridPanel.setOpaque(false);

        String[] buttonLabels = {"Shop", "Games", "Prava", "Coming"};
        for (String buttonLabel : buttonLabels) {
            CustomButton menubutton = new CustomButton(buttonLabel, 250, 250, 0, 0);
            if (buttonLabel.equals("Shop")) {
                menubutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Point currentLocation = theframe.this.getLocation();
                        Color backgroundColor = getContentPane().getBackground();
                        dispose();
                        ShopFrame shopFrame = new ShopFrame(backgroundColor, theframe.this);
                        shopFrame.setLocation(currentLocation);
                        shopFrame.setVisible(true);
                    }
                });
            }
            if (buttonLabel.equals("Games")) {
                menubutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Point currentLocation = theframe.this.getLocation();
                        Color backgroundColor = getContentPane().getBackground();
                        dispose();
                        GamesFrame gamesFrame = new GamesFrame(backgroundColor, theframe.this);
                        gamesFrame.setLocation(currentLocation);
                        gamesFrame.setVisible(true);
                    }
                });
            }
            if (buttonLabel.equals("Prava")) {
                menubutton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Point currentLocation = theframe.this.getLocation();
                        Color backgroundColor = getContentPane().getBackground();
                        dispose();
                        PravaFrame pravaFrame = new PravaFrame(backgroundColor, theframe.this);
                        pravaFrame.setLocation(currentLocation);
                        pravaFrame.setVisible(true);
                    }
                });
            }
            gridPanel.add(menubutton);
        }
        buttonPanel.add(gridPanel);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}