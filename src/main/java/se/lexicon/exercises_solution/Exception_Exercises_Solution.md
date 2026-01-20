![Lexicon Logo](https://lexicongruppen.se/media/wi5hphtd/lexicon-logo.svg)

# Exception – Exercises

## Exercise 1: Basic Exception Handling
**Task:**  
Write a program that asks the user to input two integers and then divides the first number by the second.  
Implement exception handling to manage the scenario where the user inputs zero as the second number.

**Hint:**  
Use `try-catch` to handle `ArithmeticException`.

**Solution:**
```java
package se.lexicon.exercises_solution.exercise1;

import java.util.Scanner;

public class DivisionApp {
    void main() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter first integer: ");
            int num1 = scanner.nextInt();
            System.out.print("Enter second integer: ");
            int num2 = scanner.nextInt();

            int result = num1 / num2;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.err.println("Error: Cannot divide by zero.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            System.out.println("Operation finished.");
            scanner.close();
        }
    }
}
```

---

## Exercise 2: Multiple Exception Types
**Task:**  
Create a method that reads an integer from the user and checks whether it is within a certain range (e.g., 1 to 100).

Handle exceptions for:
- Invalid input (non-integer input)
- Out-of-range values

**Hint:**  
Use `try-catch` to handle `InputMismatchException` and a **custom exception** for out-of-range values.

**Solution:**

**InvalidRangeException.java**
```java
package se.lexicon.exercises_solution.exercise2;

public class InvalidRangeException extends Exception {
    public InvalidRangeException(String message) {
        super(message);
    }
}
```

**RangeValidator.java**
```java
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
```

---

## Exercise 3: Custom Exception
**Task:**  
Define a custom exception `InsufficientBalanceException` that is thrown when a withdrawal amount exceeds the account balance.

Implement a simple banking system that allows deposits and withdrawals, and handles the custom exception appropriately.

**Hint:**  
Create a `BankAccount` class and handle the custom exception using `throw` and `throws`.

**Solution:**

**InsufficientBalanceException.java**
```java
package se.lexicon.exercises_solution.exercise3;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

**BankAccount.java**
```java
package se.lexicon.exercises_solution.exercise3;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + ". New balance: " + balance);
        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance. Current balance: " + balance + ", attempted withdrawal: " + amount);
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount + ". New balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}
```

**BankApp.java**
```java
package se.lexicon.exercises_solution.exercise3;

public class BankApp {
    void main() {
        BankAccount account = new BankAccount(1000);

        try {
            account.deposit(500);
            account.withdraw(200);
            account.withdraw(2000); // This should throw exception
        } catch (InsufficientBalanceException e) {
            System.err.println("Transaction failed: " + e.getMessage());
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}
```

---

## Exercise 4: Nested try-catch Blocks
**Task:**  
Write a program that attempts to read a file and parse its contents as integers.

Implement nested `try-catch` blocks to handle:
- `NoSuchFileException`
- `IOException`
- `NumberFormatException`

**Hint:**  
Use an outer `try-catch` for file-related exceptions and an inner `try-catch` for parsing-related exceptions.

**Solution:**
```java
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
```

---

## Exercise 5: finally Block
**Task:**  
Modify the program from Exercise 1 to include a `finally` block that always executes.

**Hint:**  
The `finally` block should print a message to the console and execute whether an exception occurs or not.

**Solution:**
Refer to the **finally** block in the solution for **Exercise 1**.

---

## Exercise 6: Throwing Exceptions
**Task:**  
Write a method that takes a string as input and checks if it is a valid email address.

If the input is invalid, throw an `IllegalArgumentException` with an appropriate message.

**Hint:**  
Use the `throw` keyword to manually throw the exception.

**Solution:**
```java
package se.lexicon.exercises_solution.exercise6;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    void main() {
        try {
            validateEmail("test@example.com");
            System.out.println("test@example.com is valid.");
            validateEmail("invalid-email");
        } catch (IllegalArgumentException e) {
            System.err.println("Validation failed: " + e.getMessage());
        }
    }

    public static void validateEmail(String email) {
        if (email == null || !pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }
}
```

---

## Exercise 7: Custom Exceptions for List Operations
**Task:**  
Create a program that manages a list of names.

Implement:
- A method to find a name in the list  
  - Throw `NameNotFoundException` if the name is not found
- A method to add a name to the list  
  - Throw `DuplicateNameException` if the name already exists

**Solution:**

**NameNotFoundException.java**
```java
package se.lexicon.exercises_solution.exercise7;

public class NameNotFoundException extends Exception {
    public NameNotFoundException(String message) {
        super(message);
    }
}
```

**DuplicateNameException.java**
```java
package se.lexicon.exercises_solution.exercise7;

public class DuplicateNameException extends Exception {
    public DuplicateNameException(String message) {
        super(message);
    }
}
```

**NameManagerApp.java**
```java
package se.lexicon.exercises_solution.exercise7;

import java.util.ArrayList;
import java.util.List;

public class NameManagerApp {
    private List<String> names = new ArrayList<>();

    void main() {
        NameManagerApp manager = new NameManagerApp();
        try {
            manager.addName("John");
            manager.addName("Jane");
            manager.addName("John"); // Should throw DuplicateNameException
        } catch (DuplicateNameException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            manager.findName("Alice"); // Should throw NameNotFoundException
        } catch (NameNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void addName(String name) throws DuplicateNameException {
        if (names.contains(name)) {
            throw new DuplicateNameException("Name '" + name + "' already exists in the list.");
        }
        names.add(name);
        System.out.println("Added name: " + name);
    }

    public String findName(String name) throws NameNotFoundException {
        if (!names.contains(name)) {
            throw new NameNotFoundException("Name '" + name + "' not found in the list.");
        }
        return name;
    }
}
```

---

## Exercise 8: Try-with-Resources
**Task:**  
Create a Java application that writes a string of text to a file.

Use the **try-with-resources** statement to ensure resources are automatically closed after the operation is complete.

**Hint:**  
Try-with-resources works with classes that implement `AutoCloseable`.

**Solution:**
```java
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
```
