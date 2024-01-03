import javax.swing.*;
import java.awt.*;


public class OutputFrame extends JFrame {
    private JTextArea textArea;
    private Color backgroundColor;

    OutputFrame(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);
        this.getContentPane().setFocusable(false);
        this.getContentPane().setLayout(null);

        Font EHS2 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 20f);
        Font EHS3 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 16f);

        textArea = new JTextArea();
        textArea.setFont(EHS3);
        textArea.setTabSize(2);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBorder(new ShadowBorder());
        textArea.setEditable(false); // Set the text area to non-editable

        int centerX = (this.getWidth() - 400) / 2;
        int centerY = (this.getHeight() - 400) / 2;

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(backgroundColor);
        scrollPane.getViewport().setBackground(backgroundColor);
        scrollPane.setBounds(centerX, centerY, 400, 400);
        scrollPane.setBorder(null);

        this.getContentPane().add(scrollPane);

        JLabel ideLabel = new JLabel("Output");
        ideLabel.setFont(EHS2);
        ideLabel.setForeground(Color.WHITE);
        ideLabel.setBounds(centerX, centerY - 50, 100, 50);
        this.getContentPane().add(ideLabel);
    }

    public void setOutput(String output) {
        textArea.setText(output);
    }
}

