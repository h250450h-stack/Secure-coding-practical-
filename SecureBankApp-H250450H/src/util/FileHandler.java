package util;

import model.Account;
import model.Transaction;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles data persistence using text files.
 * Demonstrates the Single Responsibility Principle (OOP).
 */
public class FileHandler {
    private static final String USER_FILE = "data/users.txt";
    private static final String ACCOUNT_FILE = "data/accounts.txt";
    private static final String TRANSACTION_FILE = "data/transactions.txt";

    // Initialize files if they don't exist
    static {
        createFileIfNotExists(USER_FILE);
        createFileIfNotExists(ACCOUNT_FILE);
        createFileIfNotExists(TRANSACTION_FILE);
    }

    private static void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Create data directory
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Could not create file: " + filePath);
            }
        }
    }

    // --- User Operations ---
    public static User findUser(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return new User(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users file.");
        }
        return null;
    }

    public static void saveUser(User user) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            bw.write(user.toFileString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error saving user.");
        }
    }

    // --- Account Operations ---
    public static Account findAccount(String accountNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(accountNumber)) {
                    return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts file.");
        }
        return null;
    }

    public static void saveAccount(Account account) {
        List<Account> accounts = getAllAccounts();
        boolean found = false;
        
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(account.getAccountNumber())) {
                accounts.set(i, account);
                found = true;
                break;
            }
        }
        if (!found) {
            accounts.add(account);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account acc : accounts) {
                bw.write(acc.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving account.");
        }
    }

    public static List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    accounts.add(new Account(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts.");
        }
        return accounts;
    }

    // --- Transaction Operations ---
    public static void saveTransaction(Transaction transaction) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            bw.write(transaction.toFileString());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error saving transaction.");
        }
    }

    public static List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equals(accountNumber)) {
                    Transaction tx = new Transaction(parts[0], parts[1], Double.parseDouble(parts[2]));
                    transactions.add(tx);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading transactions.");
        }
        return transactions;
    }
}