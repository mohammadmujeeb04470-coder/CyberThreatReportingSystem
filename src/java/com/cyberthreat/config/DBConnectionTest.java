package com.cyberthreat.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DBConnectionTest", urlPatterns = {"/db-test"})
public class DBConnectionTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            try (Connection con = DBConnection.getConnection()) {

                out.println("<html>");
                out.println("<head><title>JDBC Connection Test</title></head>");
                out.println("<body>");

                out.println("<h1>Cyber Threat Reporting & Tracking System</h1>");
                out.println("<h2>JDBC Connection Test</h2>");

                if (con != null && !con.isClosed()) {
                    out.println("<h3>JDBC Connection Successful!</h3>");
                    out.println("<p>Connected to: cyber_threat_db</p>");
                }

                out.println("</body>");
                out.println("</html>");

            } catch (SQLException e) {

                out.println("<h2>Database Connection Failed</h2>");
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }
        }
    }
}