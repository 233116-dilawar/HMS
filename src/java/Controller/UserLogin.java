
package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = response.getWriter();

        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // Validate input
            if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Please enter both username and password!');");
                pw.println("window.location.href = 'index.jsp';");
                pw.println("</script>");
                return;
            }

            // Use DAO to validate user
            User user = userDAO.validateUser(username, password);

            if (user != null) {
                // Create Session
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", "user");
                session.setMaxInactiveInterval(30 * 60); // 30 minutes

                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Login Successfully!');");
                pw.println("window.location.href = 'UserHome.jsp';");
                pw.println("</script>");
            } else {
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Username or Password is Incorrect!');");
                pw.println("window.location.href = 'index.jsp';");
                pw.println("</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<script type=\"text/javascript\">");
            pw.println("alert('An error occurred. Please try again later.');");
            pw.println("window.location.href = 'index.jsp';");
            pw.println("</script>");
        } finally {
            pw.close();
        }
    }

}
