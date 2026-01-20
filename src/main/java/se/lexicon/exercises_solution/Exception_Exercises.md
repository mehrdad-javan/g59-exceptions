![Lexicon Logo](https://lexicongruppen.se/media/wi5hphtd/lexicon-logo.svg)

# Exception – Exercises

## Exercise 1: Basic Exception Handling
**Task:**  
Write a program that asks the user to input two integers and then divides the first number by the second.  
Implement exception handling to manage the scenario where the user inputs zero as the second number.

**Hint:**  
Use `try-catch` to handle `ArithmeticException`.

---

## Exercise 2: Multiple Exception Types
**Task:**  
Create a method that reads an integer from the user and checks whether it is within a certain range (e.g., 1 to 100).

Handle exceptions for:
- Invalid input (non-integer input)
- Out-of-range values

**Hint:**  
Use `try-catch` to handle `InputMismatchException` and a **custom exception** for out-of-range values.

---

## Exercise 3: Custom Exception
**Task:**  
Define a custom exception `InsufficientBalanceException` that is thrown when a withdrawal amount exceeds the account balance.

Implement a simple banking system that allows deposits and withdrawals, and handles the custom exception appropriately.

**Hint:**  
Create a `BankAccount` class and handle the custom exception using `throw` and `throws`.

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

---

## Exercise 5: finally Block
**Task:**  
Modify the program from Exercise 1 to include a `finally` block that always executes.

**Hint:**  
The `finally` block should print a message to the console and execute whether an exception occurs or not.

---

## Exercise 6: Throwing Exceptions
**Task:**  
Write a method that takes a string as input and checks if it is a valid email address.

If the input is invalid, throw an `IllegalArgumentException` with an appropriate message.

**Hint:**  
Use the `throw` keyword to manually throw the exception.

---

## Exercise 7: Custom Exceptions for List Operations
**Task:**  
Create a program that manages a list of names.

Implement:
- A method to find a name in the list  
  - Throw `NameNotFoundException` if the name is not found
- A method to add a name to the list  
  - Throw `DuplicateNameException` if the name already exists

---

## Exercise 8: Try-with-Resources
**Task:**  
Create a Java application that writes a string of text to a file.

Use the **try-with-resources** statement to ensure resources are automatically closed after the operation is complete.

**Hint:**  
Try-with-resources works with classes that implement `AutoCloseable`.
