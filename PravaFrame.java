import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PravaFrame extends JFrame {
    private JTextArea textArea;
    private Color backgroundColor;
    Font EHS1 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 16f);
    CoinCount coinCount = new CoinCount("coin.txt");
    PravaFrame(Color backgroundColor, JFrame mainFrame) {

        this.backgroundColor = backgroundColor;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700, 700);
        this.getContentPane().setBackground(backgroundColor);
        this.getContentPane().setFocusable(false);
        this.getContentPane().setLayout(null);

        Font EHS = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 20f);
        Font EHS1 = FontLoader.loadFont("fonts\\ElectronicHighwaySign.TTF", 16f);

        BackButton backButton = new BackButton(this, mainFrame);
        backButton.setBounds(10, 10, 100, 50);
        this.getContentPane().add(backButton);

        textArea = new JTextArea();
        textArea.setFont(EHS1);
        textArea.setTabSize(2);
        textArea.setBackground(backgroundColor);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setBorder(new ShadowBorder());

        int centerX = (this.getWidth() - 400) / 2;
        int centerY = (this.getHeight() - 400) / 2;

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBackground(backgroundColor);
        scrollPane.getViewport().setBackground(backgroundColor);
        scrollPane.setBounds(centerX, centerY, 400, 400);
        scrollPane.setBorder(null);

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
        ideLabel.setForeground(Color.WHITE);
        ideLabel.setBounds(centerX, centerY - 50, 100, 50);
        this.getContentPane().add(ideLabel);

        CustomButton runButton = new CustomButton("Run(-1)", 100, 50, centerX, centerY + 410);
        runButton.addActionListener(new RunActionListener());
        this.getContentPane().add(runButton);

        CustomButton docsButton = new CustomButton("Docs", 100, 50, centerX+300, centerY + 410);
        docsButton.addActionListener(new DocsActionListener());
        this.getContentPane().add(docsButton);

        loadFile();

        textArea.requestFocusInWindow();

        JLabel titleLabel = new JLabel("Prava");
        titleLabel.setFont(EHS);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(325, 10, 100, 50);
        this.getContentPane().add(titleLabel);
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
                if (coinCount.getCurrentCount() - 1 >= 0) {
                    Files.write(Paths.get("prava/source.txt"), textArea.getText().getBytes());

                    ProcessBuilder pb = new ProcessBuilder("python", "prava/main.py");
                    Process p = pb.start();
                    p.waitFor();

                    String javaOutput = new String(Files.readAllBytes(Paths.get("prava/java_output.txt")));

                    OutputFrame outputFrame = new OutputFrame(backgroundColor);
                    outputFrame.setOutput(javaOutput);
                    outputFrame.setVisible(true);

                    try {
                        coinCount.changeCount(-1);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("Error updating the coin count");
                    }
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class DocsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame docsFrame = new JFrame("Docs");
            docsFrame.setSize(700, 700);
            docsFrame.getContentPane().setBackground(backgroundColor);
    
            JTextArea docsTextArea = new JTextArea();
            docsTextArea.setBackground(backgroundColor);
            docsTextArea.setForeground(Color.WHITE);
            docsTextArea.setFont(EHS1);
    
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