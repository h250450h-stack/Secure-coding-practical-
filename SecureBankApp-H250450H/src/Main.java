import service.AuthService;
import service.BankService;

import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AuthService authService = new AuthService();
    private static final BankService bankService = new BankService();
    private static String loggedInUser = null;

    public static void main(String[] args) {
        System.out.println("  Welcome to Secure Bank App  ");
        while (true) {
            if (loggedInUser == null) {
                showAuthMenu();
            } else {
                showBankingMenu();
            }
        }
    }

    private static void showAuthMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter username: ");
                String loginUser = scanner.nextLine();
                System.out.print("Enter password: ");
                String loginPass = scanner.nextLine();
                if (authService.login(loginUser, loginPass)) {
                    loggedInUser = loginUser;
                }
                break;
            case "2":
                System.out.print("Enter new username: ");
                String regUser = scanner.nextLine();
                System.out.print("Enter new password: ");
                String regPass = scanner.nextLine();
                authService.register(regUser, regPass);
                break;
            case "3":
                System.out.println("Thank you for using Secure Bank App. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void showBankingMenu() {
        System.out.println("\n--- Banking Menu ---");
        System.out.println("1. Create New Account");
        System.out.println("2. View Account Balance");
        System.out.println("3. Deposit Funds");
        System.out.println("4. Withdraw Funds");
        System.out.println("5. View Transaction History");
        System.out.println("6. Logout");
        System.out.print("Choose an option: ");

        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1":
                    bankService.createAccount(loggedInUser);
                    break;
                case "2":
                    System.out.print("Enter Account Number: ");
                    String accNumBal = scanner.nextLine();
                    bankService.viewBalance(accNumBal);
                    break;
                case "3":
                    System.out.print("Enter Account Number: ");
                    String accNumDep = scanner.nextLine();
                    System.out.print("Enter Deposit Amount: ");
                    double depAmount = Double.parseDouble(scanner.nextLine());
                    bankService.deposit(accNumDep, depAmount);
                    break;
                case "4":
                    System.out.print("Enter Account Number: ");
                    String accNumWith = scanner.nextLine();
                    System.out.print("Enter Withdrawal Amount: ");
                    double withAmount = Double.parseDouble(scanner.nextLine());
                    bankService.withdraw(accNumWith, withAmount);
                    break;
                case "5":
                    System.out.print("Enter Account Number: ");
                    String accNumHist = scanner.nextLine();
                    bankService.viewTransactionHistory(accNumHist);
                    break;
                case "6":
                    loggedInUser = null;
                    System.out.println(" Logged out successfully.");
                    break;
                default:
                    System.out.println(" Invalid option. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid input. Please enter a valid number for amounts.");
        }
    }
}