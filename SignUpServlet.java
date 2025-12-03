import java.io.IOException;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users (username, email, password) VALUES (?, ?, ?)"
            );
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, hashedPassword);

            ps.executeUpdate();
            response.getWriter().println("Registration successful! <a href='index.html'>Login here</a>");

        } catch (SQLException e) {
            response.getWriter().println("User already exists or error occurred!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
