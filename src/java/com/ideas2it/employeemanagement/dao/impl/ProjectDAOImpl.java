/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.connection_utils.HibernateUtil;
import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * This class provides methods for create, update, view, delete, assign 
 * employees for project in database.
 * 
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class ProjectDAOImpl implements ProjectDAO {
    
    /**
     * {@inheriDoc}
     * 
     */
    public int insertProject(Project project) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(project);
        
        transaction.commit();
        session.close();
        return id;
    }
}
