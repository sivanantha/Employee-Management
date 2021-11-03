/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.SetJoin;
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

/**
 * This class provides methods for create, update, view, delete, assign,
 * unassign employees for project in database.
 * 
 * @author  Sivanantham
 * @version 1.0
 */
public class ProjectDAOImpl implements ProjectDAO {
    
    /** 
     * {@inheritDoc}
     * 
     */
    @Override
    public long getProjectCount() throws HibernateException {
        long count;
        Session session = HibernateUtil.getSessionFactory().openSession();

        count = session.createQuery("SELECT COUNT(p) FROM Project p", 
                                    Long.class).getSingleResult();
        session.close();
        return count;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int insertProject(Project project) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save(project);
        
        transaction.commit();
        session.close();
        return id;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Project getById(int id) throws HibernateException {
        Project project;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Project> query = criteria.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        SetJoin<Object, Object> employees = (SetJoin<Object, Object>) 
                root.fetch("employees", JoinType.LEFT);
        
        query.select(root).where(criteria.equal(root.get("id"), id)); 
        project = session.createQuery(query).uniqueResult();
        session.close();
        return project;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<Project> getAllProjects() throws HibernateException {
        List<Project> projects;
        Session session = HibernateUtil.getSessionFactory().openSession();
        TypedQuery<Project> query = session.createQuery("SELECT DISTINCT "
                + "p FROM Project p LEFT JOIN FETCH p.employees",
                Project.class);
        
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false );
        projects = query.getResultList();
        session.close();
        return projects;
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean updateProject(Project project) throws HibernateException {
        boolean isUpdated = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        if (null != session.merge(project)) {
            isUpdated = true;
        }
        transaction.commit();
        session.close();
        return isUpdated; 
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteById(int id) throws HibernateException {
        int entitiesAffected;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaDelete<Project> delete = criteria.createCriteriaDelete(
                Project.class);
        Root<Project> root = delete.from(Project.class);
        
        delete.where(criteria.equal(root.get("id"), id));
        entitiesAffected = session.createQuery(delete).executeUpdate();
        transaction.commit();
        session.close();
        return (0 != entitiesAffected);
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteAllProjects() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Query query = session.createQuery("DELETE FROM Project");
        
        query.executeUpdate();
        transaction.commit();
        session.close();
        return true;
    }
}
