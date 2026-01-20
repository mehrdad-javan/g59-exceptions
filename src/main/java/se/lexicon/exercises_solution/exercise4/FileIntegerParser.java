package se.lexicon.exercises_solution.exercise4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileIntegerParser {
    void main() {
        Path path = Paths.get("integers.txt");
        
        // Ensure file exists for demonstration if needed, 
        // but the exercise asks to handle NoSuchFileException.
        
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                try {
                    int value = Integer.parseInt(line.trim());
                    System.out.println("Parsed integer: " + value);
                } catch (NumberFormatException e) {
                    System.err.println("Error: Could not parse '" + line + "' as an integer.");
                }
            }
        } catch (NoSuchFileException e) {
            System.err.println("Error: File not found: " + e.getFile());
        } catch (IOException e) {
            System.err.println("Error: An I/O error occurred: " + e.getMessage());
        }
    }
}
