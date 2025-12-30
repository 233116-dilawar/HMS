
package Controller;

import DAO.AdminDAO;
import Model.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String username = request.getParameter("your_name");
            String password = request.getParameter("your_pass");

            // Validate input
            if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Please enter both username and password!');");
                out.println("window.location.href = 'adminLogin.jsp';");
                out.println("</script>");
                return;
            }

            // Use DAO to validate admin
            Admin admin = adminDAO.validateAdmin(username, password);

            if (admin != null) {
                // Create Session
                HttpSession session = request.getSession();
                session.setAttribute("username", admin.getUsername());
                session.setAttribute("role", "admin");
                session.setMaxInactiveInterval(30 * 60); // 30 minutes

                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login Successfully!');");
                out.println("window.location.href = 'AdminHome.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username or Password is Incorrect!');");
                out.println("window.location.href = 'adminLogin.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('An error occurred. Please try again later.');");
            out.println("window.location.href = 'adminLogin.jsp';");
            out.println("</script>");
        } finally {
            out.close();
        }
    }
}
