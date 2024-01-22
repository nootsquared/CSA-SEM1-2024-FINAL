import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomButton extends JButton {
    // Constructor
    public CustomButton(String text, int width, int height, int x, int y) {
        super(text);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
        setBorder(new ShadowBorder());
        setFocusPainted(false);
        setRolloverEnabled(true);
        setContentAreaFilled(false);
        setBounds(x, y, width, height);
        
        // Set custom UI for the button
        setUI(new BasicButtonUI() {
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

        try {
            // Load custom font
            Font ehsFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\ElectronicHighwaySign.TTF")).deriveFont(18f);
            setFont(ehsFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}