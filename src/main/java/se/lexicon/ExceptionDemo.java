package se.lexicon;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ExceptionDemo {

    void main() {
        // Checked Exceptions
        // Files.newBufferedReader(Paths.get("names.txt"));
        // DriverManager.getConnection("");


        // Unchecked Exceptions
        // int[] numbers = {1,2,3};
        // numbers[4] = 5;
        // System.out.println("Done");

        // Ex 1
        // double decimalInput =  takeDecimalInput("Enter a decimal number: ");
        // IO.println("You entered " + decimalInput);


        // Ex 2
        // LocalDate date = takeDateInput();
        // IO.println("Today is " + date);

        // Ex 3
        // readTextFile();

        // Ex 4
        // readImageAndCopy();

        // Ex 5
        // writeTextToFile();
        // writeTextToFileNew();

        // Ex 6

        BankAccount bankAccount1 = new BankAccount(1001, 100);
        IO.println(bankAccount1);
        try {
            //bankAccount1.deposit(-200);
            IO.println(bankAccount1);

            bankAccount1.withdraw(150);
            IO.println(bankAccount1);

        } catch (IllegalArgumentException e) {
            IO.println("Error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            IO.println("Error: " + e);
        }

    }


    public static double takeDecimalInput(String prompt) {
        while (true) {
            try {
                // Code that may throw an exception
                String input = IO.readln(prompt);
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                // Code that handles the exception
                IO.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }


    public static LocalDate takeDateInput() {
        while (true) {
            try {
                String input = IO.readln("Enter date (yyyy-MM-dd): ");
                return LocalDate.parse(input); // risky line
            } catch (DateTimeParseException e) {
                IO.println("Invalid date format. Please enter a valid date (yyyy-MM-dd).");
                // IO.println("Exception Message: " + e.getMessage());
                // e.printStackTrace(); // it is used for debugging
            }
        }
    }


    // Handel Checked Exceptions
    public static void readTextFile() {
        // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
        // Files: provides static methods for working with files and file systems.
        try {
            // Relative Path: Defined relative to the current working directory. "dir/lastnames.txt"
            // Absolute Path: Defined from the root of the file system.
            // - "D:\lexicon\g59\g59-exceptions\dir\lastnames.txt" (Windows)
            // - "/home/Lexicon/lastnames.txt" (Linux/Mac)

            List<String> names = Files.readAllLines(Path.of("dir/lastnames.txt"));
            IO.println(names);
        } catch (IOException e) {
            IO.println("File not found or invalid path.");
        }
    }


    // Read Image and Copy
    public static void readImageAndCopy() {
        try {
            Path sourceFile = Path.of("source/java_logo.png");
            Path destinationFile = Path.of("destination/copy_java_logo.png");
            Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (NoSuchFileException | FileAlreadyExistsException e) {
            IO.println("File already exists or does not exist.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Write Text to File and Close the allocated resources using finally block
    public static void writeTextToFile() {
        BufferedWriter writer = null;
        try {

            writer = Files.newBufferedWriter(
                    Path.of("dir/lastnames.txt"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            writer.append("TestName");
            writer.append(",");
            // writer.newLine();

            IO.println("Data written successfully.");


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    // Write Text to File and Close the allocated resources using try-with-resources
    public static void writeTextToFileNew() {
        try (
                BufferedWriter writer = Files.newBufferedWriter(
                        Path.of("dir/lastnames.txt"),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND
                );


        ) {

            writer.append("NewTestName");
            writer.append(",");

            IO.println("Data written successfully.");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
