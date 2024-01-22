import java.io.*;

public class GradeCount { // class declaration
    private String filePath; // private instance variable

    public GradeCount(String filePath) { // constructor
        this.filePath = filePath; // assigning parameter value to instance variable
    }

    public void changeGrade(int change) throws IOException { // method declaration with parameter
        int currentGrade = getCurrentGrade(); // calling getCurrentGrade() method and assigning the returned value to a local variable
        int newGrade = currentGrade + change; // performing addition

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) { // try-with-resources statement
            writer.write(String.valueOf(newGrade)); // writing the new grade to the file
        }
    }
    
    public int getCurrentGrade() throws IOException { // method declaration
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { // try-with-resources statement
            String line = reader.readLine(); // reading a line from the file
            return line != null ? Integer.parseInt(line) : 0; // returning the parsed integer value or 0 if line is null
        }
    }
}