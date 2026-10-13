package model;


public class Account {
    private String accountNumber;
    private String username;
    private double balance;

    public Account(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getUsername() { return username; }
    public double getBalance() { return balance; }
    
    public void setBalance(double balance) { this.balance = balance; }

    // Format for saving to text file: accountNumber,username,balance
    public String toFileString() {
        return accountNumber + "," + username + "," + balance;
    }
}