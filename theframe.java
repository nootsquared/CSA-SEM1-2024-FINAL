import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class theframe extends JFrame {
    theframe() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        Font montserrat = FontLoader.loadFont("/Montserrat-Medium.ttf", 14f);
        Font montserrat20 = FontLoader.loadFont("/Montserrat-Medium.ttf", 20f);

        JLabel label = new JLabel("Welcome to the panel. What do you want to do?", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(montserrat20);
        label.setBorder(new EmptyBorder(20, 0, 0, 0));
        this.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JPanel gridPanel = new JPanel(new GridLayout(2, 2));
        gridPanel.setOpaque(false);

        String[] buttonLabels = {"Shop", "Games", "Coming", "Coming"};
        for (String buttonLabel : buttonLabels) {
            JButton button = new JButton(buttonLabel);
            button.setForeground(Color.WHITE);
            button.setPreferredSize(new Dimension(250, 250));
            button.setBorder(new ShadowBorder());
            button.setFocusPainted(false);
            button.setRolloverEnabled(true);
            button.setContentAreaFilled(false);
            button.setUI(new BasicButtonUI() {
            
                @Override
                public void update(Graphics g, JComponent c) {
                    if (c instanceof AbstractButton) {
                        AbstractButton b = (AbstractButton) c;
                        ButtonModel model = b.getModel();

                        if (model.isPressed()) {
                            g.setColor(Color.BLACK);
                        } else if (model.isRollover()) {
                            g.setColor(Color.DARK_GRAY.darker());
                        } else {
                            g.setColor(Color.DARK_GRAY);
                        }

                        g.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), 15, 15);
                    }
                    super.update(g, c);
                }
            });
            if (buttonLabel.equals("Shop")) {
                button.addActionListener(new ActionListener() {
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
                button.addActionListener(new ActionListener() {
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
            gridPanel.add(button);
        }
        buttonPanel.add(gridPanel);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}