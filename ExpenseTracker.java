    
    import java.util.Scanner;
import java.sql.SQLException;

public class ExpenseTracker {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("+-------------------+");
            System.out.println("|  Expense Tracker  |");
            System.out.println("+-------------------+");
            System.out.println("| 1. Insert Expense |");
            System.out.println("| 2. Display Expenses |");
            System.out.println("| 3. Edit Expenses  |");
            System.out.println("| 4. Exit           |");
            System.out.println("+-------------------+");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            try {
                switch (choice) {
                    case 1:
                        insertExpense();
                        break;
                    case 2:
                        displayExpenses();
                        break;
                    case 3:
                        editExpenses();
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void insertExpense() throws SQLException {
        System.out.println("Insert Expense:");
        System.out.print("Enter category (Food, accessories, stationary, transportation, medical, entertainment): ");
        String category = scanner.nextLine();
        System.out.print("Enter name of the expense: ");
        String name = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        DatabaseConnection.insertExpense(category, name, amount);
        System.out.println("Expense inserted successfully!");
    }

    private static void displayExpenses() throws SQLException {
        System.out.println("Display Expenses:");
        DatabaseConnection.displayExpenses();
    }

    private static void editExpenses() throws SQLException {
        System.out.println("Edit Expenses:");
        System.out.println("1. Insert new expense");
        System.out.println("2. Delete expense");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                insertExpense();
                break;
            case 2:
                System.out.print("Enter the record number to delete: ");
                int recordNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                DatabaseConnection.deleteExpense(recordNumber);
                System.out.println("Expense deleted successfully!");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

