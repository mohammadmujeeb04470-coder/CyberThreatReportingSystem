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

@WebServlet(name = "TrackReportServlet",
            urlPatterns = {"/track-report"})
public class TrackReportServlet extends HttpServlet {

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

        String reportIdParameter =
                request.getParameter("reportId");

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");

            out.println("<head>");

            out.println("<meta charset='UTF-8'>");

            out.println("<meta name='viewport' "
                    + "content='width=device-width, initial-scale=1.0'>");

            out.println("<title>Track Complaint</title>");

            out.println("<style>");

            out.println("*{"
                    + "margin:0;"
                    + "padding:0;"
                    + "box-sizing:border-box;"
                    + "font-family:Arial,sans-serif;"
                    + "}");

            out.println("body{"
                    + "background:#f4f6f8;"
                    + "color:#222;"
                    + "}");

            out.println(".header{"
                    + "background:#092b57;"
                    + "color:white;"
                    + "padding:22px 6%;"
                    + "}");

            out.println(".header h1{"
                    + "font-size:23px;"
                    + "}");

            out.println(".header p{"
                    + "font-size:13px;"
                    + "color:#dbe7f3;"
                    + "margin-top:5px;"
                    + "}");

            out.println(".nav{"
                    + "background:#12477d;"
                    + "padding:13px 6%;"
                    + "}");

            out.println(".nav a{"
                    + "color:white;"
                    + "text-decoration:none;"
                    + "margin-right:25px;"
                    + "font-size:14px;"
                    + "}");

            out.println(".container{"
                    + "max-width:850px;"
                    + "margin:40px auto;"
                    + "padding:0 20px;"
                    + "}");

            out.println(".card{"
                    + "background:white;"
                    + "padding:35px;"
                    + "border-radius:8px;"
                    + "box-shadow:0 3px 12px rgba(0,0,0,.10);"
                    + "margin-bottom:25px;"
                    + "}");

            out.println("h2{"
                    + "color:#092b57;"
                    + "margin-bottom:10px;"
                    + "}");

            out.println(".description{"
                    + "color:#666;"
                    + "font-size:14px;"
                    + "line-height:1.6;"
                    + "margin-bottom:25px;"
                    + "}");

            out.println("label{"
                    + "display:block;"
                    + "font-weight:bold;"
                    + "font-size:14px;"
                    + "margin-bottom:7px;"
                    + "}");

            out.println("input{"
                    + "width:100%;"
                    + "padding:13px;"
                    + "border:1px solid #ccd3dc;"
                    + "border-radius:5px;"
                    + "font-size:14px;"
                    + "margin-bottom:15px;"
                    + "}");

            out.println("button{"
                    + "background:#092b57;"
                    + "color:white;"
                    + "border:none;"
                    + "padding:13px 22px;"
                    + "border-radius:5px;"
                    + "font-weight:bold;"
                    + "cursor:pointer;"
                    + "}");

            out.println("button:hover{"
                    + "background:#1769aa;"
                    + "}");

            out.println(".result{"
                    + "border-top:4px solid #1769aa;"
                    + "}");

            out.println(".row{"
                    + "display:flex;"
                    + "justify-content:space-between;"
                    + "padding:14px 0;"
                    + "border-bottom:1px solid #eee;"
                    + "gap:20px;"
                    + "}");

            out.println(".label{"
                    + "font-weight:bold;"
                    + "color:#555;"
                    + "}");

            out.println(".value{"
                    + "color:#222;"
                    + "text-align:right;"
                    + "}");

            out.println(".status{"
                    + "font-weight:bold;"
                    + "color:#1769aa;"
                    + "}");

            out.println(".error{"
                    + "background:#fee2e2;"
                    + "color:#991b1b;"
                    + "padding:14px;"
                    + "border-radius:5px;"
                    + "margin-top:20px;"
                    + "}");

            out.println("footer{"
                    + "text-align:center;"
                    + "padding:25px;"
                    + "color:#777;"
                    + "font-size:12px;"
                    + "}");

            out.println("@media(max-width:600px){");

            out.println(".row{"
                    + "flex-direction:column;"
                    + "gap:5px;"
                    + "}");

            out.println(".value{"
                    + "text-align:left;"
                    + "}");

            out.println("}");

            out.println("</style>");

            out.println("</head>");

            out.println("<body>");

            // HEADER
            out.println("<div class='header'>");

            out.println("<h1>");
            out.println("Cyber Threat Reporting & Tracking System");
            out.println("</h1>");

            out.println("<p>Complaint Tracking Portal</p>");

            out.println("</div>");


            // NAVIGATION
            out.println("<div class='nav'>");

            out.println("<a href='dashboard'>Dashboard</a>");

            out.println("<a href='add-report'>Report Threat</a>");

            out.println("<a href='reports'>My Reports</a>");

            out.println("<a href='index.html'>Home</a>");

            out.println("</div>");


            // MAIN CONTAINER
            out.println("<div class='container'>");


            // SEARCH CARD
            out.println("<div class='card'>");

            out.println("<h2>Track Your Complaint</h2>");

            out.println("<p class='description'>");

            out.println("Enter your Report ID to view the current "
                    + "status and details of your cyber threat complaint.");

            out.println("</p>");

            out.println("<form method='get' action='track-report'>");

            out.println("<label for='reportId'>Report ID</label>");

            out.println("<input type='number' "
                    + "id='reportId' "
                    + "name='reportId' "
                    + "placeholder='Enter Report ID' "
                    + "min='1' "
                    + "required>");

            out.println("<button type='submit'>");

            out.println("Track Complaint");

            out.println("</button>");

            out.println("</form>");


            // SEARCH RESULT
            if (reportIdParameter != null &&
                !reportIdParameter.trim().isEmpty()) {

                try {

                    int reportId =
                            Integer.parseInt(reportIdParameter);

                    ThreatReportDAO dao =
                            new ThreatReportDAO();

                    ThreatReport report =
                            dao.getReportDetailsById(reportId);


                    // REPORT NOT FOUND
                    if (report == null) {

                        out.println("<div class='error'>");

                        out.println(
                                "No complaint found with Report ID: "
                                + reportId
                        );

                        out.println("</div>");

                    }

                    // USER ACCESS DENIED
                    else if (!"ADMIN".equalsIgnoreCase(role)
                            && report.getUserId() != loggedInUserId) {

                        out.println("<div class='error'>");

                        out.println(
                                "Access denied. You can track only "
                                + "your own complaints."
                        );

                        out.println("</div>");

                    }

                    // REPORT FOUND
                    else {

                        out.println("<div class='card result'>");

                        out.println("<h2>Complaint Details</h2>");


                        // REPORT ID
                        out.println("<div class='row'>");

                        out.println(
                                "<span class='label'>Report ID</span>"
                        );

                        out.println(
                                "<span class='value'>"
                                + report.getReportId()
                                + "</span>"
                        );

                        out.println("</div>");


                        // REPORTED BY
                        out.println("<div class='row'>");

                        out.println(
                                "<span class='label'>Reported By</span>"
                        );

                        out.println(
                                "<span class='value'>"
                                + escapeHtml(report.getUserName())
                                + "</span>"
                        );

                        out.println("</div>");


                        // THREAT TYPE
                        out.println("<div class='row'>");

                        out.println(
                                "<span class='label'>Threat Type</span>"
                        );

                        out.println(
                                "<span class='value'>"
                                + escapeHtml(report.getThreatType())
                                + "</span>"
                        );

                        out.println("</div>");


                        // DESCRIPTION
                        out.println("<div class='row'>");

                        out.println(
                                "<span class='label'>Description</span>"
                        );

                        out.println(
                                "<span class='value'>"
                                + escapeHtml(report.getDescription())
                                + "</span>"
                        );

                        out.println("</div>");


                        // PRIORITY
                        out.println("<div class='row'>");

                        out.println(
                                "<span class='label'>Priority</span>"
                        );

                        out.println(
                                "<span class='value'>"
                                + escapeHtml(report.getPriority())
                                + "</span>"
                        );

                        out.println("</div>");


                        // STATUS
                        out.println("<div class='row'>");

                        out.println(
                                "<span class='label'>Status</span>"
                        );

                        out.println(
                                "<span class='value status'>"
                                + escapeHtml(report.getStatusName())
                                + "</span>"
                        );

                        out.println("</div>");


                        // REPORT DATE
                        out.println("<div class='row'>");

                        out.println(
                                "<span class='label'>Report Date</span>"
                        );

                        out.println(
                                "<span class='value'>"
                                + escapeHtml(report.getReportDate())
                                + "</span>"
                        );

                        out.println("</div>");


                        out.println("</div>");
                    }


                } catch (NumberFormatException e) {

                    out.println("<div class='error'>");

                    out.println(
                            "Please enter a valid Report ID."
                    );

                    out.println("</div>");

                } catch (Exception e) {

                    out.println("<div class='error'>");

                    out.println(
                            "Unable to track the complaint. "
                            + "Please try again later."
                    );

                    out.println("</div>");
                }
            }


            out.println("</div>");


            // FOOTER
            out.println("<footer>");

            out.println(
                    "© 2026 Cyber Threat Reporting & Tracking System "
                    + "| Academic Project"
            );

            out.println("</footer>");

            out.println("</body>");

            out.println("</html>");
        }
    }


    // HTML ESCAPING
    private String escapeHtml(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}