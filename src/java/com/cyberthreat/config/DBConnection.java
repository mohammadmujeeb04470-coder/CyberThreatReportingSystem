package com.cyberthreat.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            System.getenv().getOrDefault(
                    "CYBER_DB_URL",
                    "jdbc:mysql://localhost:3306/cyber_threat_db"
            );

    private static final String USER = System.getenv("CYBER_DB_USER");
    private static final String PASSWORD = System.getenv("CYBER_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        if (USER == null || PASSWORD == null) {
            throw new SQLException(
                    "Database credentials are not configured. "
                    + "Please set CYBER_DB_USER and CYBER_DB_PASSWORD."
            );
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}