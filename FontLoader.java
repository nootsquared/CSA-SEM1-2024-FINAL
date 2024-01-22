import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    // Class: FontLoader

    public static Font loadFont(String path, float size) {
        // Method: loadFont
        // Parameters: String path, float size

        try {
            InputStream is = FontLoader.class.getResourceAsStream(path);
            // Object: InputStream
            // Method calling: FontLoader.class.getResourceAsStream(path)

            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            // Object: Font
            // Method calling: Font.createFont(Font.TRUETYPE_FONT, is)

            return font.deriveFont(size);
            // Method calling: font.deriveFont(size)
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Method calling: e.printStackTrace()

            return new Font("Arial", Font.PLAIN, 14);
            // Object: Font
            // Method calling: new Font("Arial", Font.PLAIN, 14)
        }
    }
}