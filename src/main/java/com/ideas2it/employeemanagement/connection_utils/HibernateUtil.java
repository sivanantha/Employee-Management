/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.connection_utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;
import com.ideas2it.employeemanagement.utils.Constants;

/**
 * This class contains utility method for create and get SessionFactory
 * instance.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static EMSLogger logger = new EMSLogger(HibernateUtil.class);

    /**
     * Creates a SessionFactory instance. It creates only one instance per
     * thread.
     *
     * @return a SessionFactory instance.
     * @throws EMSException if a database access error occurs.
     */
    public static SessionFactory getSessionFactory() throws EMSException {
        Configuration configuration;

        try {
            if (null == sessionFactory) {
                configuration = new Configuration();
                sessionFactory = configuration
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();
                logger.info("Hibernate Connection Established!");
            }
        } catch (HibernateException exception) {
            logger.fatal(Constants.ERROR_018, exception);
            throw new EMSException(Constants.ERROR_018);
        }
        return sessionFactory;
    }
}