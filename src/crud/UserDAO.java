package crud;

import Model.User;
import dbConnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(User user) {

        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("User inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                users.add(new User(id, username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Retrieve a specific user by ID
    public User getUserById(int userId) {

        User user = null;
        String query = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    user = new User(id, username, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, password = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("User updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                System.out.println("User deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
