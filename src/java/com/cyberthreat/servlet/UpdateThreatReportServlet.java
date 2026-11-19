package com.cyberthreat.servlet;

import com.cyberthreat.dao.ThreatReportDAO;
import com.cyberthreat.model.ThreatReport;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateThreatReportServlet",
            urlPatterns = {"/update-report"})
public class UpdateThreatReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check login
        if (session == null ||
            session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int loggedInUserId =
                (Integer) session.getAttribute("userId");

        String role =
                (String) session.getAttribute("userRole");

        String reportIdParam =
                request.getParameter("reportId");

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            if (reportIdParam == null ||
                reportIdParam.trim().isEmpty()) {

                out.println("<h2>Report ID is required.</h2>");
                out.println("<a href='reports'>Back to Reports</a>");
                return;
            }

            int reportId =
                    Integer.parseInt(reportIdParam);

            ThreatReportDAO dao =
                    new ThreatReportDAO();

            ThreatReport report =
                    dao.getReportById(reportId);

            if (report == null) {

                out.println("<h2>Report not found.</h2>");
                out.println("<a href='reports'>Back to Reports</a>");
                return;
            }

            // USER can edit only their own report
            if (!"ADMIN".equalsIgnoreCase(role) &&
                report.getUserId() != loggedInUserId) {

                out.println("<h2>Access Denied</h2>");
                out.println("<p>You cannot edit another user's report.</p>");
                out.println("<a href='reports'>Back to Reports</a>");
                return;
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Update Threat Report</title>");

            out.println("<style>");

            out.println("body {");
            out.println("font-family: Arial, sans-serif;");
            out.println("background-color: #f4f6f8;");
            out.println("margin: 0;");
            out.println("}");

            out.println(".header {");
            out.println("background-color: #1f3c88;");
            out.println("color: white;");
            out.println("padding: 25px;");
            out.println("}");

            out.println(".container {");
            out.println("padding: 30px;");
            out.println("}");

            out.println(".card {");
            out.println("background-color: white;");
            out.println("padding: 30px;");
            out.println("max-width: 650px;");
            out.println("margin: auto;");
            out.println("border-radius: 8px;");
            out.println("box-shadow: 0 2px 8px #ccc;");
            out.println("}");

            out.println("label {");
            out.println("display: block;");
            out.println("font-weight: bold;");
            out.println("margin-top: 15px;");
            out.println("}");

            out.println("input, textarea, select {");
            out.println("width: 100%;");
            out.println("padding: 10px;");
            out.println("margin-top: 6px;");
            out.println("box-sizing: border-box;");
            out.println("}");

            out.println(".button {");
            out.println("margin-top: 20px;");
            out.println("padding: 12px 20px;");
            out.println("background-color: #1f3c88;");
            out.println("color: white;");
            out.println("border: none;");
            out.println("border-radius: 4px;");
            out.println("cursor: pointer;");
            out.println("}");

            out.println("a {");
            out.println("color: blue;");
            out.println("text-decoration: none;");
            out.println("}");

            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            out.println("<div class='header'>");
            out.println("<h1>Cyber Threat Reporting & Tracking System</h1>");
            out.println("</div>");

            out.println("<div class='container'>");
            out.println("<div class='card'>");

            out.println("<h2>Update Threat Report</h2>");

            out.println("<form method='post' action='update-report'>");

            out.println("<input type='hidden' name='report_id' value='"
                    + report.getReportId() + "'>");

            out.println("<label>Status:</label>");

            out.println("<select name='status_id' required>");

            out.println("<option value='1' "
                    + (report.getStatusId() == 1 ? "selected" : "")
                    + ">Pending</option>");

            out.println("<option value='2' "
                    + (report.getStatusId() == 2 ? "selected" : "")
                    + ">Under Investigation</option>");

            out.println("<option value='3' "
                    + (report.getStatusId() == 3 ? "selected" : "")
                    + ">Resolved</option>");

            out.println("<option value='4' "
                    + (report.getStatusId() == 4 ? "selected" : "")
                    + ">Rejected</option>");

            out.println("</select>");

            out.println("<label>Threat Type:</label>");

            out.println("<input type='text' name='threat_type' value='"
                    + report.getThreatType()
                    + "' required>");

            out.println("<label>Description:</label>");

            out.println("<textarea name='description' rows='5' required>"
                    + report.getDescription()
                    + "</textarea>");

            out.println("<label>Priority:</label>");

            out.println("<select name='priority' required>");

            out.println("<option value='Low' "
                    + ("Low".equals(report.getPriority()) ? "selected" : "")
                    + ">Low</option>");

            out.println("<option value='Medium' "
                    + ("Medium".equals(report.getPriority()) ? "selected" : "")
                    + ">Medium</option>");

            out.println("<option value='High' "
                    + ("High".equals(report.getPriority()) ? "selected" : "")
                    + ">High</option>");

            out.println("<option value='Critical' "
                    + ("Critical".equals(report.getPriority()) ? "selected" : "")
                    + ">Critical</option>");

            out.println("</select>");

            out.println("<label>Report Date:</label>");

            out.println("<input type='date' name='report_date' value='"
                    + report.getReportDate()
                    + "' required>");

            out.println("<input class='button' "
                    + "type='submit' "
                    + "value='Update Report'>");

            out.println("</form>");

            out.println("<br>");

            out.println("<a href='reports'>Back to Reports</a>");

            out.println("</div>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check login
        if (session == null ||
            session.getAttribute("userId") == null) {

            response.sendRedirect("login.jsp");
            return;
        }

        int loggedInUserId =
                (Integer) session.getAttribute("userId");

        String role =
                (String) session.getAttribute("userRole");

        response.setContentType("text/html;charset=UTF-8");

        try {

            int reportId =
                    Integer.parseInt(
                            request.getParameter("report_id"));

            ThreatReportDAO dao =
                    new ThreatReportDAO();

            // Get original report
            ThreatReport existingReport =
                    dao.getReportById(reportId);

            if (existingReport == null) {

                response.getWriter().println(
                        "<h2>Report not found.</h2>"
                        + "<a href='reports'>Back to Reports</a>"
                );

                return;
            }

            // USER can update only their own report
            if (!"ADMIN".equalsIgnoreCase(role) &&
                existingReport.getUserId() != loggedInUserId) {

                response.getWriter().println(
                        "<h2>Access Denied</h2>"
                        + "<p>You cannot update another user's report.</p>"
                        + "<a href='reports'>Back to Reports</a>"
                );

                return;
            }

            ThreatReport report =
                    new ThreatReport();

            report.setReportId(reportId);

            report.setStatusId(
                    Integer.parseInt(
                            request.getParameter("status_id")
                    )
            );

            report.setThreatType(
                    request.getParameter("threat_type")
            );

            report.setDescription(
                    request.getParameter("description")
            );

            report.setPriority(
                    request.getParameter("priority")
            );

            report.setReportDate(
                    request.getParameter("report_date")
            );

            boolean updated =
                    dao.updateReport(report);

            try (PrintWriter out =
                         response.getWriter()) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Update Result</title>");
                out.println("</head>");
                out.println("<body>");

                if (updated) {

                    out.println(
                            "<h2>Threat Report Updated Successfully!</h2>"
                    );

                    out.println(
                            "<a href='reports'>View Threat Reports</a>"
                    );

                } else {

                    out.println(
                            "<h2>Report Update Failed.</h2>"
                    );

                    out.println(
                            "<a href='reports'>Back to Reports</a>"
                    );
                }

                out.println("</body>");
                out.println("</html>");
            }

        } catch (Exception e) {

            response.getWriter().println(
                    "<h2>Error while updating report</h2>"
                    + "<p>Please enter valid report information.</p>"
                    + "<a href='reports'>Back to Reports</a>"
            );
        }
    }
}