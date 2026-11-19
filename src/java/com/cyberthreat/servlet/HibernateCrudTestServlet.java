package com.cyberthreat.servlet;

import com.cyberthreat.entity.ThreatReportEntity;
import com.cyberthreat.service.HibernateThreatReportService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet(name = "HibernateCrudTestServlet",
            urlPatterns = {"/hibernate-crud-test"})

public class HibernateCrudTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<html><head><title>Hibernate CRUD Test</title></head>");
            out.println("<body style='font-family:Arial;padding:30px;'>");

            out.println("<h1>Hibernate CRUD Testing</h1>");

            out.println("<p>This test creates a temporary report, "
                    + "reads it, updates it and deletes it.</p>");

            out.println("<p>Your existing reports will not be modified.</p>");

            out.println("<form method='post' action='hibernate-crud-test'>");

            out.println("<button type='submit' "
                    + "style='padding:12px 20px;cursor:pointer;'>");

            out.println("Run Hibernate CRUD Test");

            out.println("</button>");

            out.println("</form>");

            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        WebApplicationContext context =
                WebApplicationContextUtils
                        .getRequiredWebApplicationContext(
                                getServletContext()
                        );

        HibernateThreatReportService service =
                context.getBean(
                        "hibernateThreatReportService",
                        HibernateThreatReportService.class
                );

        Integer reportId = null;

        boolean createPassed = false;
        boolean readPassed = false;
        boolean updatePassed = false;
        boolean deletePassed = false;

        String errorMessage = null;

        try {

            // CREATE
            ThreatReportEntity report =
                    new ThreatReportEntity();

            report.setUserId(userId);
            report.setStatusId(1);

            report.setThreatType("AJT Hibernate Test");

            report.setDescription(
                    "Temporary report created for Hibernate CRUD testing."
            );

            report.setPriority("Low");

            report.setReportDate(
                    Date.valueOf(LocalDate.now())
            );

            reportId = service.addReport(report);

            createPassed = reportId != null;

            if (!createPassed) {
                throw new RuntimeException(
                        "Hibernate CREATE operation failed."
                );
            }

            // READ
            ThreatReportEntity retrievedReport =
                    service.getReportById(reportId);

            readPassed = retrievedReport != null
                    && retrievedReport.getReportId() == reportId;

            if (!readPassed) {
                throw new RuntimeException(
                        "Hibernate READ operation failed."
                );
            }

            // UPDATE
            retrievedReport.setDescription(
                    "Updated temporary report - Hibernate testing."
            );

            retrievedReport.setPriority("Medium");

            updatePassed = service.updateReport(retrievedReport);

            ThreatReportEntity updatedReport =
                    service.getReportById(reportId);

            updatePassed = updatePassed
                    && updatedReport != null
                    && "Medium".equals(updatedReport.getPriority())
                    && updatedReport.getDescription().startsWith("Updated");

            if (!updatePassed) {
                throw new RuntimeException(
                        "Hibernate UPDATE operation failed."
                );
            }

            // DELETE
            deletePassed = service.deleteReport(reportId);

            if (!deletePassed) {
                throw new RuntimeException(
                        "Hibernate DELETE operation failed."
                );
            }

            // Verify deletion
            ThreatReportEntity deletedReport =
                    service.getReportById(reportId);

            deletePassed = deletedReport == null;

            if (!deletePassed) {
                throw new RuntimeException(
                        "Hibernate DELETE verification failed."
                );
            }

            reportId = null;

        } catch (Exception e) {

            errorMessage = e.getMessage();

            getServletContext().log(
                    "HIBERNATE CRUD TEST ERROR",
                    e
            );

        } finally {

            // Cleanup if a test fails before deletion
            if (reportId != null) {

                try {
                    service.deleteReport(reportId);

                } catch (Exception cleanupException) {

                    getServletContext().log(
                            "HIBERNATE TEST CLEANUP FAILED",
                            cleanupException
                    );
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<html>");
            out.println("<head><title>Hibernate CRUD Results</title></head>");

            out.println("<body style='font-family:Arial;padding:30px;'>");

            out.println("<h1>Hibernate CRUD Test Results</h1>");

            out.println("<table border='1' cellpadding='12' "
                    + "cellspacing='0'>");

            out.println("<tr><th>Operation</th><th>Result</th></tr>");

            out.println("<tr><td>CREATE</td><td>"
                    + (createPassed ? "PASSED" : "FAILED")
                    + "</td></tr>");

            out.println("<tr><td>READ</td><td>"
                    + (readPassed ? "PASSED" : "FAILED")
                    + "</td></tr>");

            out.println("<tr><td>UPDATE</td><td>"
                    + (updatePassed ? "PASSED" : "FAILED")
                    + "</td></tr>");

            out.println("<tr><td>DELETE</td><td>"
                    + (deletePassed ? "PASSED" : "FAILED")
                    + "</td></tr>");

            out.println("</table>");

            if (deletePassed) {

                out.println("<h2 style='color:green;'>"
                        + "All Hibernate CRUD operations passed!"
                        + "</h2>");

                out.println("<p>The temporary test record was deleted.</p>");

            } else {

                out.println("<h2 style='color:red;'>"
                        + "Hibernate CRUD testing failed."
                        + "</h2>");

                out.println("<p>Check the Tomcat server log for details.</p>");
            }

            out.println("<a href='dashboard'>Back to Dashboard</a>");

            out.println("</body></html>");
        }
    }
}