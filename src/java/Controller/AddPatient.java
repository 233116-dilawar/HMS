
package Controller;

import DAO.PatientDAO;
import Model.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet {

    private PatientDAO patientDAO;

    public void init() {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Date todaysDate = new Date();
            DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("Mobile");
            String city = request.getParameter("City");
            String email = request.getParameter("email");
            String age = request.getParameter("age");
            String address = request.getParameter("address");

            String DateAndTime = df2.format(todaysDate);

            Patient patient = new Patient();
            patient.setFname(fname);
            patient.setLname(lname);
            patient.setGender(gender);
            patient.setMobile(phone);
            patient.setCity(city);
            patient.setEmail(email);
            patient.setAge(age);
            patient.setAddress(address);
            patient.setDate(DateAndTime);

            boolean success = patientDAO.addPatient(patient);

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            if (success) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Patient Added Successfully..!');");
                pw.println("window.location.href = \"UserHome.jsp\";");
                pw.println("</script>");
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Failed! Duplicate entry or Database Error.');");
                pw.println("window.location.href = \"addpatient.jsp\";");
                pw.println("</script>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("addpatient.jsp?error=Unexpected error: " + ex.getMessage());
        }
    }
}
