package com.cyberthreat.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Hibernate Entity
 * Maps Java objects to the threat_reports MySQL table.
 */
@Entity
@Table(name = "threat_reports")
public class ThreatReportEntity {

    // Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;

    // User ID
    @Column(name = "user_id", nullable = false)
    private int userId;

    // Threat Status ID
    @Column(name = "status_id", nullable = false)
    private int statusId;

    // Threat Type
    @Column(name = "threat_type", length = 100, nullable = false)
    private String threatType;

    // Incident Description
    @Column(name = "description", columnDefinition = "TEXT",
            nullable = false)
    private String description;

    // Priority
    @Column(name = "priority", length = 20, nullable = false)
    private String priority;

    // Report Date
    @Column(name = "report_date", nullable = false)
    private java.sql.Date reportDate;

    // Default Constructor
    public ThreatReportEntity() {
    }

    // Getters and Setters

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getThreatType() {
        return threatType;
    }

    public void setThreatType(String threatType) {
        this.threatType = threatType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public java.sql.Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(java.sql.Date reportDate) {
        this.reportDate = reportDate;
    }
}