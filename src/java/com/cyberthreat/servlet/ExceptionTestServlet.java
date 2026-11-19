package com.cyberthreat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ExceptionTestServlet", urlPatterns = {"/exception-test"})
public class ExceptionTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<h1>Cyber Threat Reporting & Tracking System</h1>");
            out.println("<h2>Exception Handling Test</h2>");

            try {
                String value = request.getParameter("value");

                if (value == null || value.trim().isEmpty()) {
                    throw new IllegalArgumentException("Value is required.");
                }

                int number = Integer.parseInt(value);

                out.println("<h3>No Exception Occurred</h3>");
                out.println("<p>Entered value: " + number + "</p>");

            } catch (NumberFormatException e) {

                out.println("<h3>Exception Handled Successfully</h3>");
                out.println("<p>Error: Please enter a valid number.</p>");

            } catch (IllegalArgumentException e) {

                out.println("<h3>Exception Handled Successfully</h3>");
                out.println("<p>Error: " + e.getMessage() + "</p>");

            } catch (Exception e) {

                out.println("<h3>Exception Handled Successfully</h3>");
                out.println("<p>Error: " + e.getMessage() + "</p>");
            }
        }
    }
}