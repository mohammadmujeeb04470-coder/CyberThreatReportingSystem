package com.cyberthreat.servlet;

import com.cyberthreat.dao.ThreatReportDAO;
import com.cyberthreat.model.ThreatReport;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DashboardServlet",
            urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

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

        int userId =
                (Integer) session.getAttribute("userId");

        String name =
                (String) session.getAttribute("userName");

        String role =
                (String) session.getAttribute("userRole");

        ThreatReportDAO dao =
                new ThreatReportDAO();

        List<ThreatReport> reports;

        if ("ADMIN".equalsIgnoreCase(role)) {

            reports = dao.getAllReports();

        } else {

            reports = dao.getReportsByUserId(userId);
        }

        int total = reports.size();

        int pending = 0;
        int investigation = 0;
        int resolved = 0;

        for (ThreatReport report : reports) {

            String status =
                    report.getStatusName();

            if ("Pending".equalsIgnoreCase(status)) {

                pending++;

            } else if ("Under Investigation"
                    .equalsIgnoreCase(status)) {

                investigation++;

            } else if ("Resolved"
                    .equalsIgnoreCase(status)) {

                resolved++;
            }
        }

        response.setContentType(
                "text/html;charset=UTF-8"
        );

        try (PrintWriter out =
                     response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");

            out.println("<head>");

            out.println("<meta charset='UTF-8'>");

            out.println("<meta name='viewport' "
                    + "content='width=device-width, "
                    + "initial-scale=1.0'>");

            out.println("<title>User Dashboard</title>");

            out.println("<style>");

            out.println("*{");
            out.println("margin:0;");
            out.println("padding:0;");
            out.println("box-sizing:border-box;");
            out.println("font-family:Arial,sans-serif;");
            out.println("}");

            out.println("body{");
            out.println("background:#f4f6f8;");
            out.println("color:#222;");
            out.println("}");

            /* HEADER */

            out.println(".header{");
            out.println("background:#092b57;");
            out.println("color:white;");
            out.println("padding:20px 6%;");
            out.println("display:flex;");
            out.println("justify-content:space-between;");
            out.println("align-items:center;");
            out.println("}");

            out.println(".header h1{");
            out.println("font-size:23px;");
            out.println("}");

            out.println(".header p{");
            out.println("font-size:13px;");
            out.println("color:#dbe7f3;");
            out.println("margin-top:5px;");
            out.println("}");

            out.println(".logout{");
            out.println("background:#b91c1c;");
            out.println("color:white;");
            out.println("padding:10px 18px;");
            out.println("border-radius:5px;");
            out.println("text-decoration:none;");
            out.println("font-size:14px;");
            out.println("}");

            /* NAVIGATION */

            out.println(".nav{");
            out.println("background:#12477d;");
            out.println("padding:13px 6%;");
            out.println("}");

            out.println(".nav a{");
            out.println("color:white;");
            out.println("text-decoration:none;");
            out.println("margin-right:25px;");
            out.println("font-size:14px;");
            out.println("}");

            out.println(".nav a:hover{");
            out.println("text-decoration:underline;");
            out.println("}");

            /* MAIN */

            out.println(".container{");
            out.println("max-width:1200px;");
            out.println("margin:auto;");
            out.println("padding:35px 20px;");
            out.println("}");

            out.println(".welcome{");
            out.println("margin-bottom:30px;");
            out.println("}");

            out.println(".welcome h2{");
            out.println("color:#092b57;");
            out.println("font-size:28px;");
            out.println("}");

            out.println(".welcome p{");
            out.println("color:#666;");
            out.println("margin-top:7px;");
            out.println("}");

            /* STATISTICS */

            out.println(".stats{");
            out.println("display:grid;");
            out.println("grid-template-columns:repeat(4,1fr);");
            out.println("gap:18px;");
            out.println("margin-bottom:30px;");
            out.println("}");

            out.println(".stat-card{");
            out.println("background:white;");
            out.println("padding:22px;");
            out.println("border-radius:8px;");
            out.println("box-shadow:0 2px 8px "
                    + "rgba(0,0,0,0.08);");
            out.println("border-left:5px solid #1769aa;");
            out.println("}");

            out.println(".stat-card h3{");
            out.println("color:#666;");
            out.println("font-size:14px;");
            out.println("margin-bottom:10px;");
            out.println("}");

            out.println(".number{");
            out.println("font-size:30px;");
            out.println("font-weight:bold;");
            out.println("color:#092b57;");
            out.println("}");

            /* ACTIONS */

            out.println(".card{");
            out.println("background:white;");
            out.println("padding:25px;");
            out.println("border-radius:8px;");
            out.println("box-shadow:0 2px 8px "
                    + "rgba(0,0,0,0.08);");
            out.println("margin-bottom:25px;");
            out.println("}");

            out.println(".card h3{");
            out.println("color:#092b57;");
            out.println("margin-bottom:20px;");
            out.println("}");

            out.println(".actions{");
            out.println("display:grid;");
            out.println("grid-template-columns:repeat(3,1fr);");
            out.println("gap:15px;");
            out.println("}");

            out.println(".action{");
            out.println("padding:20px;");
            out.println("border:1px solid #ddd;");
            out.println("border-radius:7px;");
            out.println("text-decoration:none;");
            out.println("color:#092b57;");
            out.println("font-weight:bold;");
            out.println("}");

            out.println(".action:hover{");
            out.println("background:#eef3f8;");
            out.println("}");

            out.println(".action span{");
            out.println("display:block;");
            out.println("font-size:27px;");
            out.println("margin-bottom:10px;");
            out.println("}");

            /* INFORMATION */

            out.println(".info{");
            out.println("background:#eef3f8;");
            out.println("border-left:5px solid #1769aa;");
            out.println("padding:18px;");
            out.println("line-height:1.6;");
            out.println("font-size:14px;");
            out.println("}");

            /* FOOTER */

            out.println("footer{");
            out.println("text-align:center;");
            out.println("padding:25px;");
            out.println("color:#777;");
            out.println("font-size:12px;");
            out.println("}");

            /* RESPONSIVE */

            out.println("@media(max-width:800px){");

            out.println(".stats{");
            out.println("grid-template-columns:repeat(2,1fr);");
            out.println("}");

            out.println(".actions{");
            out.println("grid-template-columns:1fr;");
            out.println("}");

            out.println(".header{");
            out.println("flex-direction:column;");
            out.println("align-items:flex-start;");
            out.println("gap:15px;");
            out.println("}");

            out.println("}");

            out.println("</style>");

            out.println("</head>");

            out.println("<body>");

            /* HEADER */

            out.println("<div class='header'>");

            out.println("<div>");

            out.println("<h1>");
            out.println("Cyber Threat Reporting & Tracking System");
            out.println("</h1>");

            out.println("<p>");
            out.println("Secure Cyber Incident Management Portal");
            out.println("</p>");

            out.println("</div>");

            out.println("<a class='logout' href='logout'>");
            out.println("Logout");
            out.println("</a>");

            out.println("</div>");

            /* NAVIGATION */

            out.println("<div class='nav'>");

            out.println("<a href='dashboard'>");
            out.println("Dashboard");
            out.println("</a>");

            out.println("<a href='add-report'>");
            out.println("Report Cyber Threat");
            out.println("</a>");

            out.println("<a href='reports'>");
            out.println("My Reports");
            out.println("</a>");

            if ("ADMIN".equalsIgnoreCase(role)) {

                out.println("<a href='admin-dashboard'>");
                out.println("Admin Portal");
                out.println("</a>");
            }

            out.println("</div>");

            /* MAIN */

            out.println("<div class='container'>");

            out.println("<div class='welcome'>");

            out.println("<h2>");
            out.println("Welcome, " + name + "!");
            out.println("</h2>");

            out.println("<p>");
            out.println(
                    "Manage your cyber threat reports "
                    + "from your dashboard."
            );
            out.println("</p>");

            out.println("</div>");

            /* STATISTICS */

            out.println("<div class='stats'>");

            out.println("<div class='stat-card'>");

            out.println("<h3>Total Reports</h3>");

            out.println("<div class='number'>");
            out.println(total);
            out.println("</div>");

            out.println("</div>");

            out.println("<div class='stat-card'>");

            out.println("<h3>Pending</h3>");

            out.println("<div class='number'>");
            out.println(pending);
            out.println("</div>");

            out.println("</div>");

            out.println("<div class='stat-card'>");

            out.println("<h3>Under Investigation</h3>");

            out.println("<div class='number'>");
            out.println(investigation);
            out.println("</div>");

            out.println("</div>");

            out.println("<div class='stat-card'>");

            out.println("<h3>Resolved</h3>");

            out.println("<div class='number'>");
            out.println(resolved);
            out.println("</div>");

            out.println("</div>");

            out.println("</div>");

            /* QUICK SERVICES */

            out.println("<div class='card'>");

            out.println("<h3>Quick Services</h3>");

            out.println("<div class='actions'>");

            // REPORT
            out.println("<a class='action' "
                    + "href='add-report'>");

            out.println("<span>📝</span>");

            out.println("Report New Cyber Threat");

            out.println("</a>");

            // TRACK COMPLAINT
            out.println("<a class='action' "
                    + "href='track-report'>");

            out.println("<span>🔎</span>");

            out.println("Track Complaint");

            out.println("</a>");

            // HOME
            out.println("<a class='action' "
                    + "href='index.html'>");

            out.println("<span>🏠</span>");

            out.println("Return to Portal Home");

            out.println("</a>");

            out.println("</div>");

            out.println("</div>");

            /* INFORMATION */

            out.println("<div class='card'>");

            out.println("<h3>Important Information</h3>");

            out.println("<div class='info'>");

            out.println("<b>Before submitting a report:</b><br>");

            out.println(
                    "Provide accurate incident details, "
                    + "select the correct threat category "
                    + "and preserve relevant evidence."
            );

            out.println("</div>");

            out.println("</div>");

            out.println("</div>");

            /* FOOTER */

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
}