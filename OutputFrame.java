import javax.swing.*;
import java.awt.*;

public class OutputFrame extends JFrame {
    private JTextArea textArea; // Declaration of JTextArea object
    private Color backgroundColor; // Declaration of Color variable

    OutputFrame(Color backgroundColor) { // Constructor with Color parameter
        this.backgroundColor = backgroundColor; // Assigning the parameter value to the instance variable
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);
        this.getContentPane().setFocusable(false);
        this.getContentPane().setLayout(null);

        Font EHS2 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 20f); // Declaration of Font object
        Font EHS3 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 16f); // Declaration of Font object

        textArea = new JTextArea(); // Initializing JTextArea object
        textArea.setFont(EHS3);
        textArea.setTabSize(2);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBorder(new ShadowBorder());
        textArea.setEditable(false);

        int centerX = (this.getWidth() - 400) / 2; // Calculation of centerX
        int centerY = (this.getHeight() - 400) / 2; // Calculation of centerY

        JScrollPane scrollPane = new JScrollPane(textArea); // Declaration of JScrollPane object
        scrollPane.setBackground(backgroundColor);
        scrollPane.getViewport().setBackground(backgroundColor);
        scrollPane.setBounds(centerX, centerY, 400, 400);
        scrollPane.setBorder(null);

        this.getContentPane().add(scrollPane); // Adding scrollPane to the content pane

        JLabel ideLabel = new JLabel("Output"); // Declaration of JLabel object
        ideLabel.setFont(EHS2);
        ideLabel.setForeground(Color.WHITE);
        ideLabel.setBounds(centerX, centerY - 50, 100, 50);
        this.getContentPane().add(ideLabel); // Adding ideLabel to the content pane
    }

    public void setOutput(String output) { // Method to set the output text
        textArea.setText(output); // Setting the text of the JTextArea
    }
}
