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
