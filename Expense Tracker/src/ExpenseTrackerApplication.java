import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Expense {
    private String category;
    private double amount;
    private String description;

    public Expense(String category, double amount, String description) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            System.out.println("Expenses:");
            for (Expense expense : expenses) {
                System.out.println("Category: " + expense.getCategory());
                System.out.println("Amount: $" + expense.getAmount());
                System.out.println("Description: " + expense.getDescription());
                System.out.println("----------------------");
            }
        }
    }

    public double calculateTotalExpenses() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }
}

public class ExpenseTrackerApplication {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Expense Tracker Application");
            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Calculate Total Expenses");
            System.out.println("4. Exit");
            System.out.println("");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    Expense expense = new Expense(category, amount, description);
                    expenseTracker.addExpense(expense);
                    System.out.println("Expense added successfully.");
                    System.out.println();
                    break;
                case 2:
                    expenseTracker.displayExpenses();
                    System.out.println();
                    break;
                case 3:
                    double totalExpenses = expenseTracker.calculateTotalExpenses();
                    System.out.println("Total Expenses: $" + totalExpenses);
                    System.out.println();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println();
                    break;
            }
        }

        System.out.println("Expense Tracker Application terminated.");
    }
}
