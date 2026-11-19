package com.cyberthreat.servlet;

import com.cyberthreat.config.DBConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddThreatReportServlet",
            urlPatterns = {"/add-report"})
public class AddThreatReportServlet extends HttpServlet {

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

        String today = LocalDate.now().toString();

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");

            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' "
                    + "content='width=device-width, initial-scale=1.0'>");

            out.println("<title>Report Cyber Threat</title>");

            out.println("<style>");

            out.println("* {");
            out.println("box-sizing: border-box;");
            out.println("margin: 0;");
            out.println("padding: 0;");
            out.println("font-family: Arial, sans-serif;");
            out.println("}");

            out.println("body {");
            out.println("background: #f4f6f8;");
            out.println("color: #222;");
            out.println("}");

            /* HEADER */

            out.println(".header {");
            out.println("background: #092b57;");
            out.println("color: white;");
            out.println("padding: 20px 6%;");
            out.println("}");

            out.println(".header h1 {");
            out.println("font-size: 24px;");
            out.println("margin-bottom: 6px;");
            out.println("}");

            out.println(".header p {");
            out.println("font-size: 13px;");
            out.println("color: #dbe7f3;");
            out.println("}");

            /* NAV */

            out.println(".nav {");
            out.println("background: #12477d;");
            out.println("padding: 12px 6%;");
            out.println("}");

            out.println(".nav a {");
            out.println("color: white;");
            out.println("text-decoration: none;");
            out.println("margin-right: 25px;");
            out.println("font-size: 14px;");
            out.println("}");

            /* CONTAINER */

            out.println(".container {");
            out.println("max-width: 950px;");
            out.println("margin: 35px auto;");
            out.println("padding: 0 20px;");
            out.println("}");

            out.println(".card {");
            out.println("background: white;");
            out.println("padding: 35px;");
            out.println("border-radius: 8px;");
            out.println("box-shadow: 0 3px 12px rgba(0,0,0,0.10);");
            out.println("}");

            out.println(".title {");
            out.println("color: #092b57;");
            out.println("margin-bottom: 8px;");
            out.println("}");

            out.println(".description {");
            out.println("color: #666;");
            out.println("font-size: 14px;");
            out.println("margin-bottom: 25px;");
            out.println("line-height: 1.6;");
            out.println("}");

            /* NOTICE */

            out.println(".notice {");
            out.println("background: #fff4d6;");
            out.println("border-left: 5px solid #d99a00;");
            out.println("padding: 15px;");
            out.println("margin-bottom: 25px;");
            out.println("font-size: 14px;");
            out.println("line-height: 1.5;");
            out.println("}");

            /* FORM */

            out.println(".form-group {");
            out.println("margin-bottom: 20px;");
            out.println("}");

            out.println("label {");
            out.println("display: block;");
            out.println("font-weight: bold;");
            out.println("font-size: 14px;");
            out.println("margin-bottom: 7px;");
            out.println("color: #333;");
            out.println("}");

            out.println("select, textarea, input {");
            out.println("width: 100%;");
            out.println("padding: 12px;");
            out.println("border: 1px solid #ccd3dc;");
            out.println("border-radius: 5px;");
            out.println("font-size: 14px;");
            out.println("outline: none;");
            out.println("}");

            out.println("select:focus, textarea:focus, input:focus {");
            out.println("border-color: #1769aa;");
            out.println("}");

            out.println("textarea {");
            out.println("resize: vertical;");
            out.println("min-height: 140px;");
            out.println("}");

            out.println(".required {");
            out.println("color: #b91c1c;");
            out.println("}");

            /* BUTTONS */

            out.println(".buttons {");
            out.println("margin-top: 25px;");
            out.println("}");

            out.println(".button {");
            out.println("display: inline-block;");
            out.println("padding: 12px 22px;");
            out.println("border: none;");
            out.println("border-radius: 5px;");
            out.println("text-decoration: none;");
            out.println("cursor: pointer;");
            out.println("font-weight: bold;");
            out.println("font-size: 14px;");
            out.println("}");

            out.println(".submit {");
            out.println("background: #092b57;");
            out.println("color: white;");
            out.println("}");

            out.println(".submit:hover {");
            out.println("background: #1769aa;");
            out.println("}");

            out.println(".cancel {");
            out.println("background: #e5e7eb;");
            out.println("color: #333;");
            out.println("margin-left: 8px;");
            out.println("}");

            /* FOOTER */

            out.println("footer {");
            out.println("text-align: center;");
            out.println("padding: 25px;");
            out.println("color: #777;");
            out.println("font-size: 12px;");
            out.println("}");

            out.println("@media(max-width:600px) {");

            out.println(".card {");
            out.println("padding: 25px 18px;");
            out.println("}");

            out.println(".nav a {");
            out.println("display: inline-block;");
            out.println("margin-bottom: 8px;");
            out.println("}");

            out.println("}");

            out.println("</style>");

            out.println("</head>");
            out.println("<body>");

            /* HEADER */

            out.println("<div class='header'>");
            out.println("<h1>Cyber Threat Reporting & Tracking System</h1>");
            out.println("<p>Cyber Incident Complaint Registration</p>");
            out.println("</div>");

            /* NAV */

            out.println("<div class='nav'>");

            out.println("<a href='dashboard'>Dashboard</a>");
            out.println("<a href='reports'>My Reports</a>");
            out.println("<a href='add-report'>Report Threat</a>");
            out.println("<a href='logout'>Logout</a>");

            out.println("</div>");

            /* FORM */

            out.println("<div class='container'>");

            out.println("<div class='card'>");

            out.println("<h2 class='title'>Report a Cyber Threat</h2>");

            out.println("<p class='description'>");
            out.println("Provide accurate information about the cyber incident. "
                    + "The submitted report will be recorded in the system "
                    + "for tracking and administrative review.");
            out.println("</p>");

            out.println("<div class='notice'>");
            out.println("<b>Important:</b> "
                    + "Do not submit false or misleading information. "
                    + "Provide as much accurate information as possible.");
            out.println("</div>");

            out.println("<form method='post' action='add-report'>");

            /* THREAT TYPE */

            out.println("<div class='form-group'>");

            out.println("<label>");
            out.println("Threat Type <span class='required'>*</span>");
            out.println("</label>");

            out.println("<select name='threat_type' required>");

            out.println("<option value=''>-- Select Threat Type --</option>");
            out.println("<option value='Financial Fraud'>Financial Fraud</option>");
            out.println("<option value='Phishing'>Phishing</option>");
            out.println("<option value='Malware'>Malware</option>");
            out.println("<option value='Ransomware'>Ransomware</option>");
            out.println("<option value='Unauthorized Access'>Unauthorized Access</option>");
            out.println("<option value='Social Media Crime'>Social Media Crime</option>");
            out.println("<option value='Identity Theft'>Identity Theft</option>");
            out.println("<option value='Cyber Harassment'>Cyber Harassment</option>");
            out.println("<option value='Other Cyber Crime'>Other Cyber Crime</option>");

            out.println("</select>");

            out.println("</div>");

            /* DESCRIPTION */

            out.println("<div class='form-group'>");

            out.println("<label>");
            out.println("Incident Description <span class='required'>*</span>");
            out.println("</label>");

            out.println("<textarea "
                    + "name='description' "
                    + "placeholder='Describe what happened, when it happened, and any relevant details...' "
                    + "required></textarea>");

            out.println("</div>");

            /* PRIORITY */

            out.println("<div class='form-group'>");

            out.println("<label>");
            out.println("Priority <span class='required'>*</span>");
            out.println("</label>");

            out.println("<select name='priority' required>");

            out.println("<option value=''>-- Select Priority --</option>");
            out.println("<option value='Low'>Low</option>");
            out.println("<option value='Medium'>Medium</option>");
            out.println("<option value='High'>High</option>");
            out.println("<option value='Critical'>Critical</option>");

            out.println("</select>");

            out.println("</div>");

            /* DATE */

            out.println("<div class='form-group'>");

            out.println("<label>");
            out.println("Report Date <span class='required'>*</span>");
            out.println("</label>");

            out.println("<input type='date' "
                    + "name='report_date' "
                    + "value='" + today + "' "
                    + "required>");

            out.println("</div>");

            /* BUTTONS */

            out.println("<div class='buttons'>");

            out.println("<button type='submit' "
                    + "class='button submit'>");
            out.println("Submit Cyber Threat Report");
            out.println("</button>");

            out.println("<a href='dashboard' "
                    + "class='button cancel'>");
            out.println("Cancel");
            out.println("</a>");

            out.println("</div>");

            out.println("</form>");

            out.println("</div>");
            out.println("</div>");

            /* FOOTER */

            out.println("<footer>");
            out.println("© 2026 Cyber Threat Reporting & Tracking System | Academic Project");
            out.println("</footer>");

            out.println("</body>");
            out.println("</html>");
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

        String threatType = request.getParameter("threat_type");
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");
        String reportDate = request.getParameter("report_date");

        if (threatType == null || threatType.trim().isEmpty()
                || description == null || description.trim().isEmpty()
                || priority == null || priority.trim().isEmpty()
                || reportDate == null || reportDate.trim().isEmpty()) {

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                    "<h2>Invalid Report</h2>"
                    + "<p>All required fields must be completed.</p>"
                    + "<a href='add-report'>Go Back</a>"
            );

            return;
        }

        String sql = "INSERT INTO threat_reports "
                + "(user_id, status_id, threat_type, description, priority, report_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            // 1 = Pending
            ps.setInt(2, 1);

            ps.setString(3, threatType);
            ps.setString(4, description);
            ps.setString(5, priority);
            ps.setString(6, reportDate);

            int result = ps.executeUpdate();

            if (result > 0) {

                response.sendRedirect("reports");

            } else {

                response.setContentType("text/html;charset=UTF-8");

                response.getWriter().println(
                        "<h2>Report Submission Failed</h2>"
                        + "<p>Unable to save the report.</p>"
                        + "<a href='add-report'>Try Again</a>"
                );
            }

        } catch (SQLException e) {

            response.setContentType("text/html;charset=UTF-8");

            response.getWriter().println(
                    "<h2>Database Error</h2>"
                    + "<p>Unable to submit the report.</p>"
                    + "<a href='add-report'>Go Back</a>"
            );
        }
    }
}