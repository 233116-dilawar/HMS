package Controller;

import DAO.PatientDAO;
import Model.Patient;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {

    private PatientDAO patientDAO;

    public void init() {
        patientDAO = new PatientDAO();
    }

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
            throws ServletException, IOException {

        String query = request.getParameter("search");
        List<Patient> patients;

        if (query != null && !query.trim().isEmpty()) {
            patients = patientDAO.searchPatients(query);
        } else {
            patients = patientDAO.getAllPatients();
        }

        request.setAttribute("patients", patients);
        request.getRequestDispatcher("adminPatientList.jsp").forward(request, response);
    }

    private void addPatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

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

        Patient patient = new Patient();
        patient.setFname(fname);
        patient.setLname(lname);
        patient.setGender(gender);
        patient.setMobile(phone);
        patient.setCity(city);
        patient.setEmail(email);
        patient.setAge(age);
        patient.setAddress(address);
        patient.setDate(date);

        boolean success = patientDAO.addPatient(patient);

        if (success) {
            response.sendRedirect("PatientServlet?action=list");
        } else {
            // Redirect back to add form with error (you might want to implement a better
            // error handling mechanism later)
            response.sendRedirect("addpatient.jsp?error=Duplicate entry or database error");
        }
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("Mobile");
        String city = request.getParameter("City");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String address = request.getParameter("address");

        // Note: The logic here assumes 'Mobile' is the key identifier based on previous
        // implementation
        // ideally we should use ID, but sticking to existing logic for safety
        String oldEmail = request.getParameter("email"); // This logic seems flawed in original code, but sticking to
                                                         // email as per DAO

        Patient patient = new Patient();
        patient.setFname(fname);
        patient.setLname(lname);
        patient.setGender(gender);
        patient.setMobile(phone);
        patient.setCity(city);
        patient.setEmail(email);
        patient.setAge(age);
        patient.setAddress(address);

        // In the original DAO updatePatient uses email as key.
        // We will pass the email. If the logic requires 'oldEmail' distinct from new
        // email,
        // the form should provide it. For now, we assume email is the key.
        patientDAO.updatePatient(patient, email);

        response.sendRedirect("PatientServlet?action=list");
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String mob = request.getParameter("mob");
        // Using the newly added method to delete by mobile
        patientDAO.deletePatientByMobile(mob);

        response.sendRedirect("PatientServlet?action=list");
    }
}
