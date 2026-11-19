package com.cyberthreat.dao;

import com.cyberthreat.config.HibernateUtil;
import com.cyberthreat.entity.ThreatReportEntity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * Hibernate DAO
 * Performs CRUD operations using Hibernate ORM.
 */
public class HibernateThreatReportDAO {

    // CREATE
    public Integer addReport(ThreatReportEntity report) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Integer reportId = (Integer) session.save(report);

            transaction.commit();

            return reportId;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Error while adding report using Hibernate", e
            );
        }
    }

    // READ - GET REPORT BY ID
    public ThreatReportEntity getReportById(int reportId) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.get(
                    ThreatReportEntity.class,
                    reportId
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error while retrieving report", e
            );
        }
    }

    // READ - GET ALL REPORTS
    public List<ThreatReportEntity> getAllReports() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Query<ThreatReportEntity> query =
                    session.createQuery(
                            "FROM ThreatReportEntity ORDER BY reportId",
                            ThreatReportEntity.class
                    );

            return query.list();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error while retrieving all reports", e
            );
        }
    }

    // READ - GET REPORTS BY USER ID
    public List<ThreatReportEntity> getReportsByUserId(int userId) {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            Query<ThreatReportEntity> query =
                    session.createQuery(
                            "FROM ThreatReportEntity "
                            + "WHERE userId = :userId "
                            + "ORDER BY reportId",
                            ThreatReportEntity.class
                    );

            query.setParameter("userId", userId);

            return query.list();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error while retrieving user reports", e
            );
        }
    }

    // UPDATE
    public boolean updateReport(ThreatReportEntity report) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            ThreatReportEntity existingReport =
                    session.get(
                            ThreatReportEntity.class,
                            report.getReportId()
                    );

            if (existingReport == null) {
                transaction.rollback();
                return false;
            }

            existingReport.setUserId(report.getUserId());
            existingReport.setStatusId(report.getStatusId());
            existingReport.setThreatType(report.getThreatType());
            existingReport.setDescription(report.getDescription());
            existingReport.setPriority(report.getPriority());
            existingReport.setReportDate(report.getReportDate());

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Error while updating report using Hibernate", e
            );
        }
    }

    // DELETE
    public boolean deleteReport(int reportId) {

        Transaction transaction = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            ThreatReportEntity report =
                    session.get(
                            ThreatReportEntity.class,
                            reportId
                    );

            if (report == null) {
                transaction.rollback();
                return false;
            }

            session.delete(report);

            transaction.commit();

            return true;

        } catch (Exception e) {

            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            throw new RuntimeException(
                    "Error while deleting report using Hibernate", e
            );
        }
    }
}