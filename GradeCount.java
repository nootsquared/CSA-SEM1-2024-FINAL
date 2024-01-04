import java.io.*;

public class GradeCount {
    private String filePath;

    public GradeCount(String filePath) {
        this.filePath = filePath;
    }

    public void changeGrade(int change) throws IOException {
        int currentGrade = getCurrentGrade();
        int newGrade = currentGrade + change;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(newGrade));
        }
    }
    
    public int getCurrentGrade() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            return line != null ? Integer.parseInt(line) : 0;
        }
    }
}