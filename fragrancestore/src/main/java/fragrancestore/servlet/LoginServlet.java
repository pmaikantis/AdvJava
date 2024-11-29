package fragrancestore.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.fragrancestore.connection.DbCon;
import com.fragrancestore.model.User;
import fragrancestore.dao.UserDao;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("login-email");
        String password = request.getParameter("login-password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {

            HttpSession session = request.getSession();
            session.setAttribute("popupMessage", "Email and password cannot be empty.");
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            UserDao udao = new UserDao(DbCon.getConnection());
            User user = udao.userLogin(email, password);

            if (user != null) {

                request.getSession().setAttribute("auth", user);
                response.sendRedirect("index.jsp");
            } else {

                HttpSession session = request.getSession();
                session.setAttribute("popupMessage", "Invalid email or password. Please try again.");
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = request.getSession();
            session.setAttribute("popupMessage", "An unexpected error occurred. Please try again later.");
            response.sendRedirect("login.jsp");
        }
    }
}
