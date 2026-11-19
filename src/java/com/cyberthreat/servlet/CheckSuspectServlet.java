package com.cyberthreat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CheckSuspectServlet",
            urlPatterns = {"/check-suspect"})
public class CheckSuspectServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter();

        String search = request.getParameter("search");
        String result = null;

        if (search != null && !search.trim().isEmpty()) {

            result = "No matching suspect record found in the system.";
        }

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Check Suspect</title>");

        out.println("<style>");
        out.println("body{font-family:Arial;margin:0;background:#f4f6f8;}");
        out.println(".header{background:#1f3c88;color:white;padding:25px;}");
        out.println(".container{padding:40px;max-width:800px;margin:auto;}");
        out.println(".card{background:white;padding:30px;border-radius:10px;");
        out.println("box-shadow:0 2px 10px #ccc;}");
        out.println("input{width:100%;padding:12px;margin:10px 0;");
        out.println("box-sizing:border-box;border:1px solid #ccc;border-radius:5px;}");
        out.println("button{padding:12px 20px;background:#1f3c88;color:white;");
        out.println("border:0;border-radius:5px;cursor:pointer;}");
        out.println(".back{display:inline-block;margin-top:20px;color:#1f3c88;");
        out.println("text-decoration:none;}");
        out.println(".result{margin-top:25px;padding:18px;background:#f1f5ff;");
        out.println("border-left:5px solid #1f3c88;}");
        out.println("</style>");

        out.println("</head>");
        out.println("<body>");

        out.println("<div class='header'>");
        out.println("<h1>Check Suspect</h1>");
        out.println("<p>Search for a suspicious identifier</p>");
        out.println("</div>");

        out.println("<div class='container'>");
        out.println("<div class='card'>");

        out.println("<h2>Check Suspect Details</h2>");
        out.println("<p>");
        out.println("Enter a mobile number, email address, URL, or other");
        out.println("identifier to check.");
        out.println("</p>");

        out.println("<form method='get' action='check-suspect'>");

        out.println("<input type='text' name='search' ");
        out.println("placeholder='Enter mobile number, email, URL or email'");
        out.println(" required>");

        out.println("<button type='submit'>Check Suspect</button>");

        out.println("</form>");

        if (result != null) {

            out.println("<div class='result'>");
            out.println("<b>Search Result:</b><br>");
            out.println(result);
            out.println("</div>");
        }

        out.println("<a class='back' href='dashboard'>");
        out.println("← Back to Dashboard");
        out.println("</a>");

        out.println("</div>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}