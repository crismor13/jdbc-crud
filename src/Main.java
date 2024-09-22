import Model.User;
import crud.UserDAO;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        UserDAO userDAO = new UserDAO();

        // Create
        User newUser = new User(0, "new_user", "new_password");
        userDAO.addUser(newUser);

        // Read all users
        for (User user : userDAO.getAllUsers()) {
            System.out.println(user);
        }

        // Read a user by ID
        User user = userDAO.getUserById(1);
        if (user != null) {
            System.out.println("User found: " + user);
        }

        // Update a user
        user.setUsername("updated_user");
        user.setPassword("updated_password");
        userDAO.updateUser(user);

        // Delete a user
        userDAO.deleteUser(1);
    }
}