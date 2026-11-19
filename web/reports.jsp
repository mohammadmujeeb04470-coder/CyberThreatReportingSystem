<%@page import="com.cyberthreat.model.ThreatReport"%>
<%@page import="java.util.List"%>

<%
    // Use the JSP's built-in session object
    if (session == null ||
        session.getAttribute("userId") == null) {

        response.sendRedirect("login.jsp");
        return;
    }

    String role = (String) session.getAttribute("userRole");
    String userName = (String) session.getAttribute("userName");

    List<ThreatReport> reports =
            (List<ThreatReport>) request.getAttribute("reports");
%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Threat Reports</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f4f6f8;
        }

        .header {
            background-color: #1f3c88;
            color: white;
            padding: 25px;
        }

        .header h1 {
            margin: 0;
        }

        .header p {
            margin-bottom: 0;
        }

        .container {
            padding: 30px;
        }

        .card {
            background-color: white;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px #ccc;
            overflow-x: auto;
        }

        .top-bar {
            margin-bottom: 20px;
        }

        .button {
            display: inline-block;
            padding: 10px 15px;
            margin-right: 8px;
            background-color: #1f3c88;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }

        .button:hover {
            background-color: #162d66;
        }

        .logout {
            background-color: #b91c1c;
        }

        .logout:hover {
            background-color: #8f1515;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        th,
        td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #e8e8e8;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .action {
            color: blue;
            text-decoration: none;
        }

        .delete {
            color: #b91c1c;
        }

        .empty {
            text-align: center;
            padding: 20px;
        }

    </style>

</head>

<body>

    <div class="header">

        <h1>Cyber Threat Reporting & Tracking System</h1>

        <p>
            Logged in as:
            <b><%= userName %></b>
            |
            Role:
            <b><%= role %></b>
        </p>

    </div>

    <div class="container">

        <div class="card">

            <% if ("ADMIN".equalsIgnoreCase(role)) { %>

                <h2>All Threat Reports</h2>

            <% } else { %>

                <h2>My Threat Reports</h2>

            <% } %>


            <div class="top-bar">

                <a class="button" href="add-report">
                    Report New Threat
                </a>

                <a class="button" href="dashboard">
                    Dashboard
                </a>

                <% if ("ADMIN".equalsIgnoreCase(role)) { %>

                    <a class="button" href="admin-dashboard">
                        Admin Dashboard
                    </a>

                <% } %>

                <a class="button logout" href="logout">
                    Logout
                </a>

            </div>


            <% if (reports != null && !reports.isEmpty()) { %>

                <table>

                    <tr>
                        <th>Report ID</th>
                        <th>Reported By</th>
                        <th>Status</th>
                        <th>Threat Type</th>
                        <th>Description</th>
                        <th>Priority</th>
                        <th>Report Date</th>
                        <th>Actions</th>
                    </tr>


                    <% for (ThreatReport report : reports) { %>

                        <tr>

                            <td>
                                <%= report.getReportId() %>
                            </td>

                            <td>
                                <%= report.getUserName() %>
                            </td>

                            <td>
                                <%= report.getStatusName() %>
                            </td>

                            <td>
                                <%= report.getThreatType() %>
                            </td>

                            <td>
                                <%= report.getDescription() %>
                            </td>

                            <td>
                                <%= report.getPriority() %>
                            </td>

                            <td>
                                <%= report.getReportDate() %>
                            </td>

                            <td>

                                <a class="action"
                                   href="update-report?reportId=<%= report.getReportId() %>">
                                    Edit
                                </a>

                                |

                                <a class="action delete"
                                   href="delete-report?reportId=<%= report.getReportId() %>"
                                   onclick="return confirm('Are you sure you want to delete this report?');">
                                    Delete
                                </a>

                            </td>

                        </tr>

                    <% } %>

                </table>

            <% } else { %>

                <div class="empty">

                    <h3>No Threat Reports Found</h3>

                    <p>
                        There are currently no reports to display.
                    </p>

                </div>

            <% } %>

        </div>

    </div>

</body>

</html>