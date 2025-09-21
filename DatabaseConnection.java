import java.sql.*;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/expense_tracker";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
    private static Connection connection;

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void insertExpense(String category, String name, double amount) throws SQLException {
        connect();
        String query = "INSERT INTO expenses (category, name, amount) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, amount);
            preparedStatement.executeUpdate();
        } finally {
            connection.close();
        }
    }

    public static void displayExpenses() throws SQLException {
        connect();
        String query = "SELECT * FROM expenses";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int recordNumber = 1;
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                String name = resultSet.getString("name");
                double amount = resultSet.getDouble("amount");
                System.out.println("Record No: " + recordNumber++ + ", Category: " + category + ", Name: " + name + ", Amount: " + amount);
            }
        } finally {
            connection.close();
        }
    }

    public static void deleteExpense(int recordId) throws SQLException {
        connect();
        String query = "DELETE FROM expenses WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, recordId);
            preparedStatement.executeUpdate();
        } finally {
            connection.close();
        }
    }
}
