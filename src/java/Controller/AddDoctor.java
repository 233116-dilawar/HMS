
package Controller;

import DAO.DoctorDAO;
import Model.Doctor;
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

@WebServlet("/AddDoctor")
public class AddDoctor extends HttpServlet {

    private DoctorDAO doctorDAO;

    public void init() {
        doctorDAO = new DoctorDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Date todaysDate = new Date();
            DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            String sid = request.getParameter("id");
            int id = Integer.parseInt(sid);
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("Mobile");
            String city = request.getParameter("City");
            String email = request.getParameter("email");
            String age = request.getParameter("age");
            String address = request.getParameter("address");
            String qualification = request.getParameter("qualification");

            String DateAndTime = df2.format(todaysDate);

            Doctor doctor = new Doctor();
            doctor.setId(id);
            doctor.setFname(fname);
            doctor.setLname(lname);
            doctor.setGender(gender);
            doctor.setMobile(phone);
            doctor.setCity(city);
            doctor.setEmail(email);
            doctor.setAge(age);
            doctor.setAddress(address);
            doctor.setRegistrationDate(DateAndTime);
            doctor.setQualification(qualification);

            boolean success = doctorDAO.addDoctor(doctor);

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            if (success) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Data Added Successfully..!');");
                pw.println("window.location.href = \"AdminHome.jsp\";");
                pw.println("</script>");
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Failed !!!! Duplicate entry or Database Error. Try Again Later!');");
                pw.println("window.location.href = \"addDoctor.jsp\";");
                pw.println("</script>");
            }
        } catch (NumberFormatException ex) {
            response.sendRedirect("addDoctor.jsp?error=Invalid ID format");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("addDoctor.jsp?error=Unexpected error: " + ex.getMessage());
        }
    }
}
