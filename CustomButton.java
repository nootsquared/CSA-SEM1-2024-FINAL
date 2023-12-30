import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text, int width, int height, int x, int y) {
        super(text);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));
        setBorder(new ShadowBorder());
        setFocusPainted(false);
        setRolloverEnabled(true);
        setContentAreaFilled(false);
        setBounds(x, y, width, height);
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
    }
}