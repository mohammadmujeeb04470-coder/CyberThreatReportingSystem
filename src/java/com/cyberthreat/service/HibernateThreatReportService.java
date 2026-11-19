package com.cyberthreat.service;

import com.cyberthreat.dao.HibernateThreatReportDAO;
import com.cyberthreat.entity.ThreatReportEntity;

import java.util.List;

/**
 * Spring-managed service for Hibernate operations.
 * Uses Constructor Dependency Injection.
 */
public class HibernateThreatReportService {

    private final HibernateThreatReportDAO hibernateDAO;

    // Constructor Injection
    public HibernateThreatReportService(
            HibernateThreatReportDAO hibernateDAO) {

        this.hibernateDAO = hibernateDAO;
    }

    // CREATE
    public Integer addReport(ThreatReportEntity report) {

        return hibernateDAO.addReport(report);
    }

    // READ - All reports
    public List<ThreatReportEntity> getAllReports() {

        return hibernateDAO.getAllReports();
    }

    // READ - Report by ID
    public ThreatReportEntity getReportById(int reportId) {

        return hibernateDAO.getReportById(reportId);
    }

    // READ - Reports by user
    public List<ThreatReportEntity> getReportsByUserId(int userId) {

        return hibernateDAO.getReportsByUserId(userId);
    }

    // UPDATE
    public boolean updateReport(ThreatReportEntity report) {

        return hibernateDAO.updateReport(report);
    }

    // DELETE
    public boolean deleteReport(int reportId) {

        return hibernateDAO.deleteReport(reportId);
    }
}