import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");

        // Demo: just print; later you can store in DB
        response.getWriter().println("User Registered Successfully!");
        response.getWriter().println("Username: " + user);
        response.getWriter().println("Email: " + email);
    }
}
