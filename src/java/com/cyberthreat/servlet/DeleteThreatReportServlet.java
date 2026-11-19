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

@WebServlet(name = "DeleteThreatReportServlet",
            urlPatterns = {"/delete-report"})
public class DeleteThreatReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

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

        try (PrintWriter out =
                     response.getWriter()) {

            String idParameter =
                    request.getParameter("reportId");

            if (idParameter == null ||
                idParameter.trim().isEmpty()) {

                out.println(
                        "<h2>Error: Report ID is required.</h2>"
                );

                out.println(
                        "<a href='reports'>Back to Reports</a>"
                );

                return;
            }

            int reportId =
                    Integer.parseInt(idParameter);

            ThreatReportDAO dao =
                    new ThreatReportDAO();

            // Get the report before deleting
            ThreatReport report =
                    dao.getReportById(reportId);

            if (report == null) {

                out.println(
                        "<h2>Report not found.</h2>"
                );

                out.println(
                        "<a href='reports'>Back to Reports</a>"
                );

                return;
            }

            // USER can delete only their own report
            if (!"ADMIN".equalsIgnoreCase(role) &&
                report.getUserId() != loggedInUserId) {

                out.println(
                        "<h2>Access Denied</h2>"
                );

                out.println(
                        "<p>You cannot delete another user's report.</p>"
                );

                out.println(
                        "<a href='reports'>Back to Reports</a>"
                );

                return;
            }

            boolean deleted =
                    dao.deleteReport(reportId);

            if (deleted) {

                out.println(
                        "<h2>Report Deleted Successfully!</h2>"
                );

                out.println(
                        "<p>Deleted Report ID: "
                        + reportId
                        + "</p>"
                );

                out.println(
                        "<a href='reports'>View Threat Reports</a>"
                );

            } else {

                out.println(
                        "<h2>Report could not be deleted.</h2>"
                );

                out.println(
                        "<a href='reports'>Back to Reports</a>"
                );
            }

        } catch (NumberFormatException e) {

            response.getWriter().println(
                    "<h2>Error: Report ID must be a number.</h2>"
                    + "<a href='reports'>Back to Reports</a>"
            );

        } catch (Exception e) {

            response.getWriter().println(
                    "<h2>Error while deleting report.</h2>"
                    + "<a href='reports'>Back to Reports</a>"
            );
        }
    }
}