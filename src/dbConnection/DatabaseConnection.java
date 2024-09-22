package dbConnection;

import java.sql.*;

public class DatabaseConnection {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/jdbc_practice";
    private static final String username = "root";
    private static final String password = "0000";

    // Method to establish the connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
