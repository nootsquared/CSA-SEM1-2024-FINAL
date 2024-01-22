import java.io.*;

public class CoinCount {
    private String filePath;

    /**
     * Constructs a CoinCount object with the specified file path.
     */
    public CoinCount(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Updates the count of coins by the specified amount and saves the new count to the file.
     * @param change the amount by which to change the count of coins
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void changeCount(int change) throws IOException {
        int currentCount = getCurrentCount(); // Calling the getCurrentCount() method
        int newCount = currentCount + change;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(newCount));
        }
    }
    
    /**
     * Retrieves the current count of coins from the file.
     * @return the current count of coins
     * @throws IOException if an I/O error occurs while reading the file
     */
    public int getCurrentCount() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        }
    }
}