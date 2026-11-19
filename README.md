# Cyber Threat Reporting and Tracking System

## Project Description
The Cyber Threat Reporting and Tracking System is a web-based application developed to report, manage, and track cyber threats. It allows users to register, log in, submit cyber threat reports, and track their report status. Administrators can manage and monitor submitted reports.

## Technologies Used
- Frontend: HTML, CSS, JavaScript, JSP
- Backend: Java, Servlets
- Frameworks: Spring Framework, Hibernate
- Database: MySQL / TiDB Cloud
- Server: Apache Tomcat 9
- IDE: NetBeans
- Build Tool: Apache Ant

## Main Features
- User Registration and Login
- Session Management
- Cyber Threat Reporting
- View Submitted Reports
- Track Report Status
- Update and Delete Reports
- Admin Dashboard
- JDBC Database Connectivity
- Hibernate CRUD Operations
- Spring Dependency Injection

## Requirements
- JDK 8
- Apache NetBeans
- Apache Tomcat 9
- MySQL Server 8 (for local setup)
- MySQL Workbench

## Installation and Execution

1. Download or clone this repository.
2. Open the project in NetBeans.
3. Configure Apache Tomcat 9.
4. Create the MySQL database using the SQL script in the `Database` folder.
5. Configure the database environment variables:
   - `CYBER_DB_URL`
   - `CYBER_DB_USER`
   - `CYBER_DB_PASSWORD`
6. Restart NetBeans after setting the environment variables.
7. Clean and Build the project.
8. Run the project using Tomcat.

## Database Setup

1. Open MySQL Workbench.
2. Connect to your MySQL server.
3. Open `Database/cyber_threat_db.sql`.
4. Execute the SQL script.
5. Verify that the `cyber_threat_db` database and its tables are created.

For the deployed application, TiDB Cloud is used as the MySQL-compatible database.

## Project Structure

```text
CyberThreatReportingSystem/
│
├── Database/
│   └── cyber_threat_db.sql
│
├── lib/
├── nbproject/
├── src/
│   └── java/
├── web/
│   ├── WEB-INF/
│   ├── login.jsp
│   ├── register.jsp
│   ├── reports.jsp
│   └── index.html
│
├── build.xml
├── Dockerfile
└── README.md
```

## GitHub Repository

https://github.com/mohammadmujeeb04470-coder/CyberThreatReportingSystem

## Live Deployment

**Application URL:**
https://cyberthreatreportingsystem.onrender.com

**Hosting Platform:** Render

**Cloud Database:** TiDB Cloud

The application has been successfully deployed and tested online.

Note: The free hosting instance may take some time to respond after a period of inactivity.

## Academic Project

**Degree:** B.Tech

**Branch:** Computer Science and Engineering – Cyber Security

**Subject:** Advanced Java Technology (AJT)

**University:** Marwadi University
