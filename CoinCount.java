import java.io.*;

public class CoinCount {
    private String filePath;

    public CoinCount(String filePath) {
        this.filePath = filePath;
    }

    public void changeCount(int change) throws IOException {
        int currentCount = getCurrentCount();
        int newCount = currentCount + change;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(newCount));
        }
    }
    
    public int getCurrentCount() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        }
    }

    
}