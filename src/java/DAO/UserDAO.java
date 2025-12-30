package DAO;

import Database.DatabaseConnection;
import Model.User;
import java.sql.*;

public class UserDAO {

    /**
     * Validate user login credentials
     */
    public User validateUser(String username, String password) {
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        User user = null;

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Register a new user
     */
    public boolean registerUser(User user) {
        String query = "INSERT INTO login (username, password, email, mobile) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getMobile());

            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if username already exists
     */
    public boolean userExists(String username) {
        String query = "SELECT * FROM login WHERE username = ?";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            return rs.next();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
