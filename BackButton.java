import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton {
    BackButton(JFrame currentFrame, JFrame mainFrame) {
        super("Home");
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(100, 50));
        setBorder(new ShadowBorder());
        setFocusPainted(false);
        setRolloverEnabled(true);
        setContentAreaFilled(false);
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
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point currentLocation = currentFrame.getLocation();
                currentFrame.dispose();
                mainFrame.setLocation(currentLocation);
                mainFrame.setVisible(true);
            }
        });
    }
}