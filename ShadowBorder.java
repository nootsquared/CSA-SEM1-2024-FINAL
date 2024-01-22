import java.awt.*;
import javax.swing.border.AbstractBorder;

public class ShadowBorder extends AbstractBorder {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height); // calling the parent class's paintBorder method
        Graphics2D g2d = (Graphics2D) g; // creating a Graphics2D object
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // setting rendering hint for antialiasing
        g2d.setColor(Color.BLACK); // setting color to black
        g2d.drawRoundRect(x, y, width - 3, height - 3, 15, 15); // drawing a rounded rectangle border
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f)); // setting composite for transparency
        g2d.fillRoundRect(x, y, width, height, 15, 15); // filling the rounded rectangle
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(5, 5, 5, 5); // returning insets for border spacing
    }
}