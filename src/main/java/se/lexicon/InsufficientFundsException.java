package se.lexicon;

public class InsufficientFundsException extends Exception {

    private double amount;
    private int accountNumber;

    public InsufficientFundsException(String message, double amount, int accountNumber) {
    super(message);
    this.amount = amount;
    this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String toString() {
        return "Insufficient funds for withdrawal. Account: " + accountNumber + ", Amount: " + amount;
    }
}
