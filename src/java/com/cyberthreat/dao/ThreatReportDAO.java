package com.cyberthreat.dao;

import com.cyberthreat.config.DBConnection;
import com.cyberthreat.model.ThreatReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThreatReportDAO {

    // CREATE
    public boolean addReport(ThreatReport report) {

        String sql = "INSERT INTO threat_reports "
                + "(user_id, status_id, threat_type, description, priority, report_date) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, report.getUserId());
            ps.setInt(2, report.getStatusId());
            ps.setString(3, report.getThreatType());
            ps.setString(4, report.getDescription());
            ps.setString(5, report.getPriority());
            ps.setString(6, report.getReportDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error while adding report: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // READ - GET ALL REPORTS
    public List<ThreatReport> getAllReports() {

        List<ThreatReport> reports = new ArrayList<>();

        String sql = "SELECT tr.report_id, tr.user_id, tr.status_id, "
                + "u.name AS user_name, "
                + "ts.status_name, "
                + "tr.threat_type, tr.description, "
                + "tr.priority, tr.report_date "
                + "FROM threat_reports tr "
                + "JOIN users u ON tr.user_id = u.user_id "
                + "JOIN threat_status ts ON tr.status_id = ts.status_id "
                + "ORDER BY tr.report_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                ThreatReport report = new ThreatReport();

                report.setReportId(rs.getInt("report_id"));
                report.setUserId(rs.getInt("user_id"));
                report.setStatusId(rs.getInt("status_id"));

                report.setUserName(rs.getString("user_name"));
                report.setStatusName(rs.getString("status_name"));

                report.setThreatType(rs.getString("threat_type"));
                report.setDescription(rs.getString("description"));
                report.setPriority(rs.getString("priority"));
                report.setReportDate(rs.getString("report_date"));

                reports.add(report);
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while reading reports: "
                    + e.getMessage()
            );
        }

        return reports;
    }

    // READ - GET REPORTS BY USER ID
    public List<ThreatReport> getReportsByUserId(int userId) {

        List<ThreatReport> reports = new ArrayList<>();

        String sql = "SELECT tr.report_id, tr.user_id, tr.status_id, "
                + "u.name AS user_name, "
                + "ts.status_name, "
                + "tr.threat_type, tr.description, "
                + "tr.priority, tr.report_date "
                + "FROM threat_reports tr "
                + "JOIN users u ON tr.user_id = u.user_id "
                + "JOIN threat_status ts ON tr.status_id = ts.status_id "
                + "WHERE tr.user_id = ? "
                + "ORDER BY tr.report_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    ThreatReport report = new ThreatReport();

                    report.setReportId(rs.getInt("report_id"));
                    report.setUserId(rs.getInt("user_id"));
                    report.setStatusId(rs.getInt("status_id"));

                    report.setUserName(rs.getString("user_name"));
                    report.setStatusName(rs.getString("status_name"));

                    report.setThreatType(rs.getString("threat_type"));
                    report.setDescription(rs.getString("description"));
                    report.setPriority(rs.getString("priority"));
                    report.setReportDate(rs.getString("report_date"));

                    reports.add(report);
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while reading user reports: "
                    + e.getMessage()
            );
        }

        return reports;
    }

    // UPDATE
    public boolean updateReport(ThreatReport report) {

        String sql = "UPDATE threat_reports SET "
                + "status_id = ?, "
                + "threat_type = ?, "
                + "description = ?, "
                + "priority = ?, "
                + "report_date = ? "
                + "WHERE report_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, report.getStatusId());
            ps.setString(2, report.getThreatType());
            ps.setString(3, report.getDescription());
            ps.setString(4, report.getPriority());
            ps.setString(5, report.getReportDate());
            ps.setInt(6, report.getReportId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error while updating report: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // DELETE
    public boolean deleteReport(int reportId) {

        String sql = "DELETE FROM threat_reports "
                + "WHERE report_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reportId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            System.out.println(
                    "Error while deleting report: "
                    + e.getMessage()
            );

            return false;
        }
    }

    // GET REPORT BY ID
    public ThreatReport getReportById(int reportId) {

        String sql = "SELECT report_id, user_id, status_id, "
                + "threat_type, description, priority, report_date "
                + "FROM threat_reports "
                + "WHERE report_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reportId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    ThreatReport report = new ThreatReport();

                    report.setReportId(rs.getInt("report_id"));
                    report.setUserId(rs.getInt("user_id"));
                    report.setStatusId(rs.getInt("status_id"));
                    report.setThreatType(
                            rs.getString("threat_type")
                    );
                    report.setDescription(
                            rs.getString("description")
                    );
                    report.setPriority(
                            rs.getString("priority")
                    );
                    report.setReportDate(
                            rs.getString("report_date")
                    );

                    return report;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Error while getting report: "
                    + e.getMessage()
            );
        }

        return null;
    }
    // GET COMPLETE REPORT DETAILS BY ID
  public ThreatReport getReportDetailsById(int reportId) {

    String sql = "SELECT tr.report_id, tr.user_id, tr.status_id, "
            + "u.name AS user_name, "
            + "ts.status_name, "
            + "tr.threat_type, tr.description, "
            + "tr.priority, tr.report_date "
            + "FROM threat_reports tr "
            + "JOIN users u ON tr.user_id = u.user_id "
            + "JOIN threat_status ts ON tr.status_id = ts.status_id "
            + "WHERE tr.report_id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, reportId);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                ThreatReport report = new ThreatReport();

                report.setReportId(rs.getInt("report_id"));
                report.setUserId(rs.getInt("user_id"));
                report.setStatusId(rs.getInt("status_id"));

                report.setUserName(rs.getString("user_name"));
                report.setStatusName(rs.getString("status_name"));

                report.setThreatType(rs.getString("threat_type"));
                report.setDescription(rs.getString("description"));
                report.setPriority(rs.getString("priority"));
                report.setReportDate(rs.getString("report_date"));

                return report;
            }
        }

    } catch (Exception e) {

        System.out.println(
                "Error while tracking report: "
                + e.getMessage()
        );
    }

    return null;
  }
}