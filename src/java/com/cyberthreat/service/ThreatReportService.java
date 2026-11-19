package com.cyberthreat.service;

import com.cyberthreat.dao.ThreatReportDAO;
import com.cyberthreat.model.ThreatReport;

import java.util.List;

/**
 * Service layer for Cyber Threat Reporting System.
 * Uses Spring Constructor Dependency Injection.
 */
public class ThreatReportService {

    private final ThreatReportDAO threatReportDAO;

    // Constructor Injection
    public ThreatReportService(ThreatReportDAO threatReportDAO) {
        this.threatReportDAO = threatReportDAO;
    }

    // CREATE
    public boolean addReport(ThreatReport report) {
        return threatReportDAO.addReport(report);
    }

    // READ - All reports
    public List<ThreatReport> getAllReports() {
        return threatReportDAO.getAllReports();
    }

    // READ - Reports by user
    public List<ThreatReport> getReportsByUserId(int userId) {
        return threatReportDAO.getReportsByUserId(userId);
    }

    // READ - Report by ID
    public ThreatReport getReportById(int reportId) {
        return threatReportDAO.getReportById(reportId);
    }

    // READ - Complete report details
    public ThreatReport getReportDetailsById(int reportId) {
        return threatReportDAO.getReportDetailsById(reportId);
    }

    // UPDATE
    public boolean updateReport(ThreatReport report) {
        return threatReportDAO.updateReport(report);
    }

    // DELETE
    public boolean deleteReport(int reportId) {
        return threatReportDAO.deleteReport(reportId);
    }
}