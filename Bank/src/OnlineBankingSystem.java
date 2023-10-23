import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bank {
    private Map<String, Double> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, double initialBalance) {
        accounts.put(accountNumber, initialBalance);
    }

    public double getBalance(String accountNumber) {
        return accounts.getOrDefault(accountNumber, 0.0);
    }

    public void deposit(String accountNumber, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        double currentBalance = accounts.getOrDefault(accountNumber, 0.0);
        accounts.put(accountNumber, currentBalance + amount);
        System.out.println("Deposit successful.");
    }

    public void withdraw(String accountNumber, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }
        double currentBalance = accounts.getOrDefault(accountNumber, 0.0);
        if (currentBalance >= amount) {
            accounts.put(accountNumber, currentBalance - amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

public class OnlineBankingSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("------------------");
            System.out.println("Banking Simulator");
            System.out.println("------------------");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Exit");
            System.out.println("------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character

                    bank.createAccount(accountNumber, initialBalance);
                    System.out.println("Account created successfully.");
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();

                    double balance = bank.getBalance(accountNumber);
                    System.out.println("Account Balance: $" + balance);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character

                    bank.deposit(accountNumber, depositAmount);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character

                    bank.withdraw(accountNumber, withdrawalAmount);
                    System.out.println();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
                    break;
            }
        }

        System.out.println("Online Banking System terminated.");
    }
}

