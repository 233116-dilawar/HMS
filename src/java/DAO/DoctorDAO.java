package DAO;

import Database.DatabaseConnection;
import Model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    /**
     * Add a new doctor to the database
     */
    public boolean addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctor (id, fname, lname, gender, mobile, city, email, age, address, date, qualification) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, doctor.getId());
            pst.setString(2, doctor.getFname());
            pst.setString(3, doctor.getLname());
            pst.setString(4, doctor.getGender());
            pst.setString(5, doctor.getMobile());
            pst.setString(6, doctor.getCity());
            pst.setString(7, doctor.getEmail());
            pst.setString(8, doctor.getAge());
            pst.setString(9, doctor.getAddress());
            pst.setString(10, doctor.getRegistrationDate());
            pst.setString(11, doctor.getQualification());

            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all doctors from the database
     */
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";

        try (Connection con = DatabaseConnection.initializeDatabase();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setFname(rs.getString("fname"));
                doctor.setLname(rs.getString("lname"));
                doctor.setGender(rs.getString("gender"));
                doctor.setMobile(rs.getString("mobile"));
                doctor.setCity(rs.getString("city"));
                doctor.setEmail(rs.getString("email"));
                doctor.setAge(rs.getString("age"));
                doctor.setAddress(rs.getString("address"));
                doctor.setRegistrationDate(rs.getString("date"));
                doctor.setQualification(rs.getString("qualification"));
                doctors.add(doctor);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    /**
     * Get a doctor by ID
     */
    public Doctor getDoctorById(int id) {
        String query = "SELECT * FROM doctor WHERE id = ?";
        Doctor doctor = null;

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                doctor = new Doctor();
                doctor.setId(rs.getInt("id"));
                doctor.setFname(rs.getString("fname"));
                doctor.setLname(rs.getString("lname"));
                doctor.setGender(rs.getString("gender"));
                doctor.setMobile(rs.getString("mobile"));
                doctor.setCity(rs.getString("city"));
                doctor.setEmail(rs.getString("email"));
                doctor.setAge(rs.getString("age"));
                doctor.setAddress(rs.getString("address"));
                doctor.setRegistrationDate(rs.getString("date"));
                doctor.setQualification(rs.getString("qualification"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    /**
     * Update doctor information
     */
    public boolean updateDoctor(Doctor doctor) {
        String query = "UPDATE doctor SET fname=?, lname=?, gender=?, mobile=?, city=?, email=?, age=?, address=?, qualification=? WHERE id=?";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, doctor.getFname());
            pst.setString(2, doctor.getLname());
            pst.setString(3, doctor.getGender());
            pst.setString(4, doctor.getMobile());
            pst.setString(5, doctor.getCity());
            pst.setString(6, doctor.getEmail());
            pst.setString(7, doctor.getAge());
            pst.setString(8, doctor.getAddress());
            pst.setString(9, doctor.getQualification());
            pst.setInt(10, doctor.getId());

            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a doctor by ID
     */
    public boolean deleteDoctor(int id) {
        String query = "DELETE FROM doctor WHERE id = ?";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
