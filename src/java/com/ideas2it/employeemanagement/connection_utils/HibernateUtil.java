/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.connection_utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

/** 
 * This class contains utility method for create and get SessionFactory 
 * instance.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    
    /** 
     * Creates a SessionFactory instance. It creates only one instance
     * per thread.
     *
     * @return a SessionFactory instance.
     * @exception HibernateException if a database access error occurs.
     */
    public static SessionFactory getSessionFactory() throws HibernateException {
         if (sessionFactory == null) {
             Configuration configuration = new Configuration();
             sessionFactory = configuration.configure(
                    "resources/hibernate.cfg.xml").buildSessionFactory(); 
         }
         return sessionFactory;
    }
}
