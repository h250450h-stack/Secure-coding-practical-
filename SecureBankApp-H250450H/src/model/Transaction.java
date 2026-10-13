package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transaction {
    private String accountNumber;
    private String type;
    private double amount;
    private String timestamp;

    public Transaction(String accountNumber, String type, double amount) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        // Record the exact time of the transaction
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getTimestamp() { return timestamp; }

    public String toFileString() {
        return accountNumber + "," + type + "," + amount + "," + timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: $%.2f", timestamp, type, amount);
    }
}