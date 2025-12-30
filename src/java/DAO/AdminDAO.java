package DAO;

import Database.DatabaseConnection;
import Model.Admin;
import java.sql.*;

public class AdminDAO {

    /**
     * Validate admin login credentials
     */
    public Admin validateAdmin(String username, String password) {
        String query = "SELECT * FROM adminreg WHERE username = ? AND password = ?";
        Admin admin = null;

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return admin;
    }

    /**
     * Register a new admin
     */
    public boolean registerAdmin(Admin admin) {
        String query = "INSERT INTO adminreg (username, password) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, admin.getUsername());
            pst.setString(2, admin.getPassword());

            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check if admin username already exists
     */
    public boolean adminExists(String username) {
        String query = "SELECT * FROM adminreg WHERE username = ?";

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
