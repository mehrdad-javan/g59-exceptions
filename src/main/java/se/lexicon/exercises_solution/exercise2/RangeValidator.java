package se.lexicon.exercises_solution.exercise2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RangeValidator {
    void main() {
        try {
            int number = readIntegerFromUser(1, 100);
            System.out.println("Valid input: " + number);
        } catch (InputMismatchException e) {
            System.err.println("Error: Invalid input. Please enter an integer.");
        } catch (InvalidRangeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static int readIntegerFromUser(int min, int max) throws InvalidRangeException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer between " + min + " and " + max + ": ");
        int input;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw e;
        }

        if (input < min || input > max) {
            throw new InvalidRangeException("Value " + input + " is out of range (" + min + "-" + max + ").");
        }
        return input;
    }
}
