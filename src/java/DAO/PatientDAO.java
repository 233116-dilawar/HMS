package DAO;

import Database.DatabaseConnection;
import Model.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    /**
     * Add a new patient to the database
     */
    public boolean addPatient(Patient patient) {
        String query = "INSERT INTO patient (fname, lname, gender, city, email, age, address, date, mobile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, patient.getFname());
            pst.setString(2, patient.getLname());
            pst.setString(3, patient.getGender());
            pst.setString(4, patient.getCity());
            pst.setString(5, patient.getEmail());
            pst.setString(6, patient.getAge());
            pst.setString(7, patient.getAddress());
            pst.setString(8, patient.getDate());
            pst.setString(9, patient.getMobile());

            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get all patients from the database
     */
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patient";

        try (Connection con = DatabaseConnection.initializeDatabase();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setFname(rs.getString("fname"));
                patient.setLname(rs.getString("lname"));
                patient.setGender(rs.getString("gender"));
                patient.setCity(rs.getString("city"));
                patient.setEmail(rs.getString("email"));
                patient.setAge(rs.getString("age"));
                patient.setAddress(rs.getString("address"));
                patient.setDate(rs.getString("date"));
                patient.setMobile(rs.getString("mobile"));
                patients.add(patient);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return patients;
    }

    /**
     * Get a patient by email
     */
    public Patient getPatientByEmail(String email) {
        String query = "SELECT * FROM patient WHERE email = ?";
        Patient patient = null;

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                patient = new Patient();
                patient.setFname(rs.getString("fname"));
                patient.setLname(rs.getString("lname"));
                patient.setGender(rs.getString("gender"));
                patient.setCity(rs.getString("city"));
                patient.setEmail(rs.getString("email"));
                patient.setAge(rs.getString("age"));
                patient.setAddress(rs.getString("address"));
                patient.setDate(rs.getString("date"));
                patient.setMobile(rs.getString("mobile"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return patient;
    }

    /**
     * Update patient information
     */
    public boolean updatePatient(Patient patient, String oldEmail) {
        String query = "UPDATE patient SET fname=?, lname=?, gender=?, city=?, email=?, age=?, address=?, mobile=? WHERE email=?";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, patient.getFname());
            pst.setString(2, patient.getLname());
            pst.setString(3, patient.getGender());
            pst.setString(4, patient.getCity());
            pst.setString(5, patient.getEmail());
            pst.setString(6, patient.getAge());
            pst.setString(7, patient.getAddress());
            pst.setString(8, patient.getMobile());
            pst.setString(9, oldEmail);

            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a patient by email
     */
    public boolean deletePatient(String email) {
        String query = "DELETE FROM patient WHERE email = ?";

        try (Connection con = DatabaseConnection.initializeDatabase();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, email);
            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
