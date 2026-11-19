package com.cyberthreat.servlet;

import com.cyberthreat.entity.ThreatReportEntity;
import com.cyberthreat.service.HibernateThreatReportService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet(name = "HibernateTestServlet",
            urlPatterns = {"/hibernate-test"})

public class HibernateTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Check login session
        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        String role =
                (String) session.getAttribute("userRole");

        response.setContentType("text/html;charset=UTF-8");

        try {

            // Get Spring Application Context
            WebApplicationContext context =
                    WebApplicationContextUtils
                            .getRequiredWebApplicationContext(
                                    getServletContext()
                            );

            // Get Hibernate Service from Spring
            HibernateThreatReportService service =
                    context.getBean(
                            "hibernateThreatReportService",
                            HibernateThreatReportService.class
                    );

            List<ThreatReportEntity> reports;

            // Admin can view all reports
            if ("ADMIN".equalsIgnoreCase(role)) {

                reports = service.getAllReports();

            } else {

                reports = service.getReportsByUserId(userId);
            }

            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");

            out.println("<title>Hibernate Test</title>");

            out.println("<style>");

            out.println("body { font-family: Arial; background:#f4f6f8; padding:30px; }");

            out.println("h1 { color:#092b57; }");

            out.println(".card { background:white; padding:25px; border-radius:8px; }");

            out.println("table { width:100%; border-collapse:collapse; margin-top:20px; }");

            out.println("th,td { padding:12px; border:1px solid #ccc; text-align:left; }");

            out.println("th { background:#092b57; color:white; }");

            out.println("tr:nth-child(even) { background:#f2f2f2; }");

            out.println(".success { color:green; font-weight:bold; }");

            out.println(".button { display:inline-block; padding:10px 15px; background:#092b57; color:white; text-decoration:none; border-radius:5px; }");

            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='card'>");

            out.println("<h1>Hibernate ORM Test</h1>");

            out.println("<p class='success'>Hibernate database retrieval successful!</p>");

            out.println("<p>Database: cyber_threat_db</p>");

            out.println("<p>Logged in as: "
                    + escapeHtml(session.getAttribute("userName"))
                    + "</p>");

            out.println("<p>Role: "
                    + escapeHtml(role)
                    + "</p>");

            out.println("<h2>Threat Reports Retrieved Using Hibernate</h2>");

            if (reports != null && !reports.isEmpty()) {

                out.println("<table>");

                out.println("<tr>");

                out.println("<th>Report ID</th>");
                out.println("<th>User ID</th>");
                out.println("<th>Status ID</th>");
                out.println("<th>Threat Type</th>");
                out.println("<th>Description</th>");
                out.println("<th>Priority</th>");
                out.println("<th>Report Date</th>");

                out.println("</tr>");

                for (ThreatReportEntity report : reports) {

                    out.println("<tr>");

                    out.println("<td>" + report.getReportId() + "</td>");

                    out.println("<td>" + report.getUserId() + "</td>");

                    out.println("<td>" + report.getStatusId() + "</td>");

                    out.println("<td>"
                            + escapeHtml(report.getThreatType())
                            + "</td>");

                    out.println("<td>"
                            + escapeHtml(report.getDescription())
                            + "</td>");

                    out.println("<td>"
                            + escapeHtml(report.getPriority())
                            + "</td>");

                    out.println("<td>"
                            + report.getReportDate()
                            + "</td>");

                    out.println("</tr>");
                }

                out.println("</table>");

                out.println("<p>Total reports retrieved: "
                        + reports.size() + "</p>");

            } else {

                out.println("<h3>No reports found.</h3>");
            }

            out.println("<br>");

            out.println("<a class='button' href='dashboard'>Dashboard</a>");

            out.println("&nbsp;");

            out.println("<a class='button' href='reports'>JDBC Reports</a>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {

            getServletContext().log(
                    "HIBERNATE TEST FAILED",
                    e
            );

            response.setStatus(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().println(
                    "<h2>Hibernate Test Failed</h2>"
                    + "<p>Unable to retrieve reports using Hibernate.</p>"
                    + "<p>Check the Tomcat server log for details.</p>"
            );
        }
    }

    // Escape HTML output to prevent HTML injection
    private String escapeHtml(Object value) {

        if (value == null) {
            return "";
        }

        String text = String.valueOf(value);

        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}