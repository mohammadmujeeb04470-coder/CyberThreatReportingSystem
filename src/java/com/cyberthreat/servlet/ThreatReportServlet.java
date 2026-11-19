package com.cyberthreat.servlet;

import com.cyberthreat.dao.ThreatReportDAO;
import com.cyberthreat.model.ThreatReport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ThreatReportServlet",
            urlPatterns = {"/reports"})
public class ThreatReportServlet extends HttpServlet {

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
        String role = (String) session.getAttribute("userRole");

        try {

            ThreatReportDAO dao = new ThreatReportDAO();

            List<ThreatReport> reports;

            // Admin can view all reports
            if ("ADMIN".equalsIgnoreCase(role)) {

                reports = dao.getAllReports();

            } else {

                // Normal user can view only their reports
                reports = dao.getReportsByUserId(userId);
            }

            request.setAttribute("reports", reports);

            request.getRequestDispatcher("reports.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            response.setContentType(
                    "text/html;charset=UTF-8"
            );

            response.getWriter().println(
                    "<h2>Error while loading reports</h2>"
                    + "<p>Unable to load threat reports.</p>"
                    + "<a href='dashboard'>Back to Dashboard</a>"
            );
        }
    }
}