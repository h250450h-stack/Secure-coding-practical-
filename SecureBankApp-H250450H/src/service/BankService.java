package service;

import model.Account;
import model.Transaction;
import util.FileHandler;

import java.util.List;
import java.util.Random;


public class BankService {

    private String generateAccountNumber() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public Account createAccount(String username) {
        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(accountNumber, username, 0.0);
        FileHandler.saveAccount(newAccount);
        System.out.println("Account created successfully! Your Account Number is: " + accountNumber);
        return newAccount;
    }

    public void viewBalance(String accountNumber) {
        Account account = FileHandler.findAccount(accountNumber);
        if (account != null) {
            System.out.printf("Current Balance for Account %s: $%.2f%n", accountNumber, account.getBalance());
        } else {
            System.out.println(" Account not found.");
        }
    }

    public void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            System.out.println(" Deposit amount must be greater than zero.");
            return;
        }

        Account account = FileHandler.findAccount(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            FileHandler.saveAccount(account);
            
            Transaction tx = new Transaction(accountNumber, "DEPOSIT", amount);
            FileHandler.saveTransaction(tx);
            
            System.out.printf(" Successfully deposited $%.2f. New Balance: $%.2f%n", amount, account.getBalance());
        } else {
            System.out.println(" Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            System.out.println(" Withdrawal amount must be greater than zero.");
            return;
        }

        Account account = FileHandler.findAccount(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                FileHandler.saveAccount(account);
                
                Transaction tx = new Transaction(accountNumber, "WITHDRAW", amount);
                FileHandler.saveTransaction(tx);
                
                System.out.printf("Successfully withdrew $%.2f. New Balance: $%.2f%n", amount, account.getBalance());
            } else {
                System.out.println(" Insufficient funds. Current Balance: $" + account.getBalance());
            }
        } else {
            System.out.println(" Account not found.");
        }
    }

    public void viewTransactionHistory(String accountNumber) {
        List<Transaction> transactions = FileHandler.getTransactions(accountNumber);
        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
        } else {
            System.out.println("\n--- Transaction History ---");
            for (Transaction tx : transactions) {
                System.out.println(tx.toString());
            }
            System.out.println("---------------------------\n");
        }
    }
}