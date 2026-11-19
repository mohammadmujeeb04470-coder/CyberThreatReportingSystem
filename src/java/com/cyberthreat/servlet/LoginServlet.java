package com.cyberthreat.servlet;

import com.cyberthreat.config.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            response.sendRedirect("login.jsp?error=empty");
            return;
        }

        email = email.trim();

        String sql = "SELECT user_id, name, role "
                + "FROM users "
                + "WHERE email=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    int userId = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String role = rs.getString("role");

                    // Create session after successful login
                    HttpSession session = request.getSession(true);

                    session.setAttribute("userId", userId);
                    session.setAttribute("userName", name);
                    session.setAttribute("userRole", role);

                    response.sendRedirect("dashboard");

                } else {

                    response.sendRedirect("login.jsp?error=1");
                }
            }

        } catch (Exception e) {

            try (PrintWriter out = response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login Error</title>");
                out.println("</head>");
                out.println("<body>");

                out.println("<h2>Login Error</h2>");
                out.println(
                        "<p>Unable to process login.</p>"
                );

                out.println(
                        "<a href='login.jsp'>Back to Login</a>"
                );

                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("login.jsp");
    }
}