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

@WebServlet(name = "RegisterServlet",
            urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            showMessage(response,
                    "Registration Failed",
                    "All fields are required.",
                    "register.jsp");

            return;
        }

        name = name.trim();
        email = email.trim();

        // Basic email validation
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            showMessage(response,
                    "Registration Failed",
                    "Please enter a valid email address.",
                    "register.jsp");

            return;
        }

        // Password validation
        if (password.length() < 6) {

            showMessage(response,
                    "Registration Failed",
                    "Password must contain at least 6 characters.",
                    "register.jsp");

            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            // Check duplicate email
            String checkSql =
                    "SELECT user_id FROM users WHERE email=?";

            try (PreparedStatement checkPs =
                         con.prepareStatement(checkSql)) {

                checkPs.setString(1, email);

                try (ResultSet rs =
                             checkPs.executeQuery()) {

                    if (rs.next()) {

                        showMessage(response,
                                "Registration Failed",
                                "Email address is already registered.",
                                "register.jsp");

                        return;
                    }
                }
            }

            // Insert new user
            String insertSql =
                    "INSERT INTO users "
                    + "(name, email, password, role) "
                    + "VALUES (?, ?, ?, 'USER')";

            try (PreparedStatement ps =
                         con.prepareStatement(insertSql)) {

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, password);

                int result = ps.executeUpdate();

                if (result > 0) {

                    showMessage(response,
                            "Registration Successful",
                            "Your account has been created successfully.",
                            "login.jsp");

                } else {

                    showMessage(response,
                            "Registration Failed",
                            "Unable to create your account.",
                            "register.jsp");
                }
            }

        } catch (Exception e) {

            showMessage(response,
                    "Registration Error",
                    "Unable to process registration.",
                    "register.jsp");
        }
    }

    private void showMessage(HttpServletResponse response,
                             String title,
                             String message,
                             String link)
            throws IOException {

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>" + title + "</title>");

            out.println("<style>");
            out.println("body {");
            out.println("font-family: Arial, sans-serif;");
            out.println("background-color: #f4f6f8;");
            out.println("padding: 50px;");
            out.println("}");

            out.println(".card {");
            out.println("background: white;");
            out.println("max-width: 500px;");
            out.println("margin: auto;");
            out.println("padding: 30px;");
            out.println("border-radius: 8px;");
            out.println("box-shadow: 0 2px 8px #ccc;");
            out.println("}");

            out.println("a {");
            out.println("color: #1f3c88;");
            out.println("font-weight: bold;");
            out.println("}");

            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class='card'>");

            out.println("<h2>" + title + "</h2>");
            out.println("<p>" + message + "</p>");

            out.println("<a href='" + link + "'>Continue</a>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("register.jsp");
    }
}