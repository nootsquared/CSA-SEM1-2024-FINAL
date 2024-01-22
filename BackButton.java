import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends CustomButton {

    // Class: BackButton
    // Methods: BackButton, actionPerformed
    // Objects: currentFrame, mainFrame, currentLocation
    // Accessors: addActionListener

    BackButton(JFrame currentFrame, JFrame mainFrame) {
        super("Home", 100, 50, 10, 10);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Method: actionPerformed
                // Objects: currentFrame, mainFrame, currentLocation
                Point currentLocation = currentFrame.getLocation();
                currentFrame.dispose();
                mainFrame.setLocation(currentLocation);
                mainFrame.setVisible(true);
            }
        });
    }
}

// This thing just makes the frame not close everything (dispose) and kinda minimize and go invisible, and set the new frame as the mainframe
