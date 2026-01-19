package se.lexicon;

public class BankAccount {

    private final int accountNumber;
    private double balance;

    public BankAccount(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        deposit(initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    /**
     *
     * @param amount :
     * @throws IllegalArgumentException :
     * @throws InsufficientFundsException :
     */
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.", amount, accountNumber);
        }
        balance -= amount;
        System.out.println("✅ Withdrawal successful. New balance: " + balance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                '}';
    }
}
