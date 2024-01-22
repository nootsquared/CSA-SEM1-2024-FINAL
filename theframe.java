import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class theframe extends JFrame {
    theframe() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // JFrame method
        this.setResizable(false); // JFrame method
        this.setSize(700, 700); // JFrame method
        this.getContentPane().setBackground(Color.DARK_GRAY); // JFrame method

        Font EHS = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 20f); // FontLoader class, loadFont method

        JLabel label = new JLabel("Welcome to the panel. What do you want to do?", SwingConstants.CENTER); // JLabel class
        label.setForeground(Color.WHITE); // JLabel method
        label.setFont(EHS); // JLabel method
        label.setBorder(new EmptyBorder(20, 0, 0, 0)); // JLabel method
        this.add(label, BorderLayout.NORTH); // JFrame method

        JPanel buttonPanel = new JPanel(new GridBagLayout()); // JPanel class
        buttonPanel.setBackground(Color.DARK_GRAY); // JPanel method
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // JPanel method

        JPanel gridPanel = new JPanel(new GridLayout(2, 2)); // JPanel class
        gridPanel.setOpaque(false); // JPanel method

        String[] buttonLabels = {"Shop", "Games", "Prava", "Coming"}; // String array

        for (String buttonLabel : buttonLabels) {
            CustomButton menubutton = new CustomButton(buttonLabel, 250, 250, 0, 0); // CustomButton class

            if (buttonLabel.equals("Shop")) {
                menubutton.addActionListener(new ActionListener() { // CustomButton method, addActionListener method
                    @Override
                    public void actionPerformed(ActionEvent e) { // ActionListener method
                        Point currentLocation = theframe.this.getLocation(); // theframe method, getLocation method
                        Color backgroundColor = getContentPane().getBackground(); // JFrame method, getContentPane method, getBackground method
                        dispose(); // JFrame method
                        ShopFrame shopFrame = new ShopFrame(backgroundColor, theframe.this); // ShopFrame class
                        shopFrame.setLocation(currentLocation); // ShopFrame method, setLocation method
                        shopFrame.setVisible(true); // ShopFrame method, setVisible method
                    }
                });
            }

            if (buttonLabel.equals("Games")) {
                menubutton.addActionListener(new ActionListener() { // CustomButton method, addActionListener method
                    @Override
                    public void actionPerformed(ActionEvent e) { // ActionListener method
                        Point currentLocation = theframe.this.getLocation(); // theframe method, getLocation method
                        Color backgroundColor = getContentPane().getBackground(); // JFrame method, getContentPane method, getBackground method
                        dispose(); // JFrame method
                        GamesFrame gamesFrame = new GamesFrame(backgroundColor, theframe.this); // GamesFrame class
                        gamesFrame.setLocation(currentLocation); // GamesFrame method, setLocation method
                        gamesFrame.setVisible(true); // GamesFrame method, setVisible method
                    }
                });
            }

            if (buttonLabel.equals("Prava")) {
                menubutton.addActionListener(new ActionListener() { // CustomButton method, addActionListener method
                    @Override
                    public void actionPerformed(ActionEvent e) { // ActionListener method
                        Point currentLocation = theframe.this.getLocation(); // theframe method, getLocation method
                        Color backgroundColor = getContentPane().getBackground(); // JFrame method, getContentPane method, getBackground method
                        dispose(); // JFrame method
                        PravaFrame pravaFrame = new PravaFrame(backgroundColor, theframe.this); // PravaFrame class
                        pravaFrame.setLocation(currentLocation); // PravaFrame method, setLocation method
                        pravaFrame.setVisible(true); // PravaFrame method, setVisible method
                    }
                });
            }

            gridPanel.add(menubutton); // JPanel method
        }

        buttonPanel.add(gridPanel); // JPanel method
        this.add(buttonPanel, BorderLayout.CENTER); // JFrame method
        this.setVisible(true); // JFrame method
    }
}