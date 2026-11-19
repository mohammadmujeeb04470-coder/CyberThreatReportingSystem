package com.cyberthreat.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility Class
 *
 * Creates and manages a single SessionFactory
 * for the Cyber Threat Reporting System.
 */
public final class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY =
            buildSessionFactory();

    private HibernateUtil() {
        // Prevent object creation
    }

    /**
     * Initialize Hibernate SessionFactory.
     */
    private static SessionFactory buildSessionFactory() {

        try {

            // Read database configuration from environment variables
            String url = System.getenv("CYBER_DB_URL");
            String username = System.getenv("CYBER_DB_USER");
            String password = System.getenv("CYBER_DB_PASSWORD");

            // Validate environment variables
            if (url == null || username == null || password == null
                    || url.trim().isEmpty()
                    || username.trim().isEmpty()
                    || password.isEmpty()) {

                throw new IllegalStateException(
                        "Database environment variables are missing. "
                        + "Configure CYBER_DB_URL, CYBER_DB_USER "
                        + "and CYBER_DB_PASSWORD."
                );
            }

            // Load Hibernate configuration
            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");

            // Set database connection properties
            configuration.setProperty(
                    "hibernate.connection.url", url
            );

            configuration.setProperty(
                    "hibernate.connection.username", username
            );

            configuration.setProperty(
                    "hibernate.connection.password", password
            );

            // Build SessionFactory
            return configuration.buildSessionFactory();

        } catch (Throwable exception) {

            System.err.println(
                    "Hibernate SessionFactory initialization failed!"
            );

            exception.printStackTrace();

            throw new ExceptionInInitializerError(exception);
        }
    }

    /**
     * Return the SessionFactory instance.
     */
    public static SessionFactory getSessionFactory() {

        return SESSION_FACTORY;
    }

    /**
     * Close Hibernate SessionFactory.
     */
    public static void shutdown() {

        if (SESSION_FACTORY != null
                && !SESSION_FACTORY.isClosed()) {

            SESSION_FACTORY.close();
        }
    }
}