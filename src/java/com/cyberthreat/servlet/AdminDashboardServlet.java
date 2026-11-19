package com.cyberthreat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminDashboardServlet",
            urlPatterns = {"/admin-dashboard"})
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check whether the user is logged in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get logged-in user's role
        String role = (String) session.getAttribute("userRole");

        // Allow only ADMIN users
        if (!"ADMIN".equalsIgnoreCase(role)) {
            response.sendRedirect("dashboard");
            return;
        }

        String name = (String) session.getAttribute("userName");

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Admin Dashboard</title>");

            out.println("<style>");

            out.println("body {");
            out.println("font-family: Arial, sans-serif;");
            out.println("margin: 0;");
            out.println("background-color: #f4f6f8;");
            out.println("}");

            out.println(".header {");
            out.println("background-color: #1f3c88;");
            out.println("color: white;");
            out.println("padding: 25px;");
            out.println("}");

            out.println(".header h1 {");
            out.println("margin: 0;");
            out.println("}");

            out.println(".header h2 {");
            out.println("margin-top: 10px;");
            out.println("font-weight: normal;");
            out.println("}");

            out.println(".container {");
            out.println("padding: 30px;");
            out.println("}");

            out.println(".card {");
            out.println("background-color: white;");
            out.println("padding: 30px;");
            out.println("border-radius: 8px;");
            out.println("box-shadow: 0 2px 8px #ccc;");
            out.println("max-width: 800px;");
            out.println("}");

            out.println(".button {");
            out.println("display: inline-block;");
            out.println("padding: 12px 18px;");
            out.println("margin: 8px 8px 8px 0;");
            out.println("background-color: #1f3c88;");
            out.println("color: white;");
            out.println("text-decoration: none;");
            out.println("border-radius: 4px;");
            out.println("}");

            out.println(".button:hover {");
            out.println("background-color: #162d66;");
            out.println("}");

            out.println(".logout {");
            out.println("background-color: #b91c1c;");
            out.println("}");

            out.println(".logout:hover {");
            out.println("background-color: #8f1515;");
            out.println("}");

            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            // Header
            out.println("<div class='header'>");
            out.println("<h1>Cyber Threat Reporting & Tracking System</h1>");
            out.println("<h2>Administrator Dashboard</h2>");
            out.println("</div>");

            // Main content
            out.println("<div class='container'>");

            out.println("<div class='card'>");

            out.println("<h2>Welcome, " + name + "!</h2>");

            out.println("<p>");
            out.println("You are logged in as an <b>Administrator</b>.");
            out.println("</p>");

            out.println("<p>");
            out.println("Manage and monitor reported cyber threats using the options below.");
            out.println("</p>");

            // Dashboard options
            out.println("<h3>Admin Options</h3>");

            out.println("<a class='button' href='reports'>");
            out.println("View All Threat Reports");
            out.println("</a>");

            out.println("<a class='button' href='add-report'>");
            out.println("Add Threat Report");
            out.println("</a>");

            out.println("<br>");

            out.println("<a class='button logout' href='logout'>");
            out.println("Logout");
            out.println("</a>");

            out.println("</div>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}