package se.lexicon.exercises_solution.exercise8;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriterApp {
    void main() {
        Path path = Paths.get("output.txt");
        String content = "Hello, this is a test string written using try-with-resources!";

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(content);
            System.out.println("Successfully wrote to the file: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("An I/O error occurred: " + e.getMessage());
        }
    }
}
