import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends CustomButton {
    BackButton(JFrame currentFrame, JFrame mainFrame) {
        super("Home", 100, 50, 10, 10);
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