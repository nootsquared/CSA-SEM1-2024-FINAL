import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PravaFrame extends JFrame {
    private JTextArea textArea;
    private Color backgroundColor;
    Font EHS1 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 16f);

    PravaFrame(Color backgroundColor, JFrame mainFrame) {
        this.backgroundColor = backgroundColor;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);
        this.getContentPane().setFocusable(false); // Disable focus on the content pane
        this.getContentPane().setLayout(null); // Set layout to null for absolute positioning

        Font EHS = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 20f);
        Font EHS1 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 16f);
        Font EHS2 = FontLoader.loadFont("fonts\\Antonio.ttf", 16f);

        BackButton backButton = new BackButton(this, mainFrame);
        backButton.setBounds(10, 10, 100, 50);
        this.getContentPane().add(backButton);

        textArea = new JTextArea();
        textArea.setFont(EHS1); // Set the font of the text area to EHS
        textArea.setTabSize(2); // Set the tab size to 2
        textArea.setBackground(backgroundColor); // Set the background color of the text box
        textArea.setForeground(Color.WHITE); // Set the text color to white
        textArea.setCaretColor(Color.WHITE); // Set the cursor color to white
        textArea.setBorder(new ShadowBorder()); // Add a shadow around the text box

        // Calculate the center of the screen
        int centerX = (this.getWidth() - 400) / 2;
        int centerY = (this.getHeight() - 400) / 2;

        // Resize the text box to a square and center it
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(backgroundColor); // Set the background color of the scroll pane
        scrollPane.getViewport().setBackground(backgroundColor); // Set the background color of the viewport
        scrollPane.setBounds(centerX, centerY, 400, 400);
        scrollPane.setBorder(null); // Remove border from the scroll pane

        // Change the color of the scroll bars
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = backgroundColor;
            }
        });
        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = backgroundColor;
            }
        });

        this.getContentPane().add(scrollPane);

        JLabel ideLabel = new JLabel("IDE");
        ideLabel.setFont(EHS);
        ideLabel.setForeground(Color.WHITE); // Set the text color to white
        ideLabel.setBounds(centerX, centerY - 50, 100, 50); // Adjust the position and size as needed
        this.getContentPane().add(ideLabel);

        CustomButton runButton = new CustomButton("Run", 100, 50, centerX, centerY + 410);
        runButton.addActionListener(new RunActionListener());
        this.getContentPane().add(runButton);

        CustomButton docsButton = new CustomButton("Docs", 100, 50, centerX+300, centerY + 410);
        docsButton.addActionListener(new DocsActionListener());
        this.getContentPane().add(docsButton);

        loadFile();

        // Request focus on the textArea
        textArea.requestFocusInWindow();
    }

    private void loadFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("prava/source.txt")));
            textArea.setText(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private class RunActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Files.write(Paths.get("prava/source.txt"), textArea.getText().getBytes());
    
                ProcessBuilder pb = new ProcessBuilder("python", "prava/main.py");
                Process p = pb.start();
                p.waitFor();
    
                String javaOutput = new String(Files.readAllBytes(Paths.get("prava/java_output.txt")));
    
                OutputFrame outputFrame = new OutputFrame(backgroundColor);
                outputFrame.setOutput(javaOutput);
                outputFrame.setVisible(true);
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class DocsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame docsFrame = new JFrame("Docs");
            docsFrame.setSize(700, 700); // Adjust the size as needed
            docsFrame.getContentPane().setBackground(backgroundColor); // Set the background color
    
            JTextArea docsTextArea = new JTextArea();
            docsTextArea.setBackground(backgroundColor); // Set the background color of the text area
            docsTextArea.setForeground(Color.WHITE); // Set the text color to white
            docsTextArea.setFont(EHS1); // Set the font to EHS1
    
            JScrollPane docsScrollPane = new JScrollPane(docsTextArea);
            docsScrollPane.setBounds(400, 400, 400, 400);
            docsScrollPane.setBorder(null);
            docsFrame.getContentPane().add(docsScrollPane);
            docsFrame.setVisible(true);
    
            try {
                String content = new String(Files.readAllBytes(Paths.get("prava\\docs.txt")));
                docsTextArea.setText(content);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}