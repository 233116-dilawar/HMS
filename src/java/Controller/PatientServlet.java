package Controller;

import Database.DatabaseConnection;
import Model.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    addPatient(request, response);
                    break;
                case "update":
                    updatePatient(request, response);
                    break;
                case "delete":
                    deletePatient(request, response);
                    break;
                case "list":
                default:
                    listPatients(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listPatients(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {

        Connection con = DatabaseConnection.initializeDatabase();
        Statement st = con.createStatement();
        String sql = "SELECT * FROM patient";

        // Search functionality
        String query = request.getParameter("search");
        if (query != null && !query.trim().isEmpty()) {
            sql += " WHERE fname LIKE '%" + query + "%' OR lname LIKE '%" + query + "%'";
        }

        ResultSet rs = st.executeQuery(sql);
        List<Patient> patients = new ArrayList<>();

        while (rs.next()) {
            Patient p = new Patient();
            p.setFname(rs.getString("fname"));
            p.setLname(rs.getString("lname"));
            p.setGender(rs.getString("gender"));
            p.setCity(rs.getString("city"));
            p.setEmail(rs.getString("email"));
            p.setAge(rs.getString("age"));
            p.setAddress(rs.getString("address"));
            p.setDate(rs.getString("date"));
            p.setMobile(rs.getString("mobile"));
            patients.add(p);
        }

        con.close();

        request.setAttribute("patients", patients);
        request.getRequestDispatcher("adminPatientList.jsp").forward(request, response);
    }

    private void addPatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, IOException {

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("Mobile");
        String city = request.getParameter("City");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String address = request.getParameter("address");

        Date todaysDate = new Date();
        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = df2.format(todaysDate);

        Connection con = DatabaseConnection.initializeDatabase();
        PreparedStatement pst = con.prepareStatement(
                "INSERT INTO patient (fname, lname, gender, city, email, age, address, date, mobile) VALUES (?,?,?,?,?,?,?,?,?)");
        pst.setString(1, fname);
        pst.setString(2, lname);
        pst.setString(3, gender);
        pst.setString(4, city);
        pst.setString(5, email);
        pst.setString(6, age);
        pst.setString(7, address);
        pst.setString(8, date);
        pst.setString(9, phone);

        pst.executeUpdate();
        con.close();

        response.sendRedirect("PatientServlet?action=list");
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, IOException {

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("Mobile");
        String city = request.getParameter("City");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String address = request.getParameter("address");

        Connection con = DatabaseConnection.initializeDatabase();
        PreparedStatement pst = con.prepareStatement(
                "UPDATE patient SET fname=?, lname=?, gender=?, city=?, email=?, age=?, address=? WHERE mobile=?");
        pst.setString(1, fname);
        pst.setString(2, lname);
        pst.setString(3, gender);
        pst.setString(4, city);
        pst.setString(5, email);
        pst.setString(6, age);
        pst.setString(7, address);
        pst.setString(8, phone);

        pst.executeUpdate();
        con.close();

        response.sendRedirect("PatientServlet?action=list");
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, IOException {

        String mob = request.getParameter("mob");

        Connection con = DatabaseConnection.initializeDatabase();
        PreparedStatement pst = con.prepareStatement("DELETE FROM patient WHERE mobile=?");
        pst.setString(1, mob);

        pst.executeUpdate();
        con.close();

        response.sendRedirect("PatientServlet?action=list");
    }
}
