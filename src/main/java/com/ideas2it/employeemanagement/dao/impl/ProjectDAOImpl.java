/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.connection_utils.HibernateUtil;
import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.LoggerUtil;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.utils.Constants;

/**
 * This class provides methods for create, update, view, delete, assign,
 * unassign employees for project in database.
 * 
 * @author  Sivanantham
 * @version 1.1
 */
public class ProjectDAOImpl implements ProjectDAO {
    
    /** 
     * {@inheritDoc}
     * 
     */
    @Override
    public long getProjectCount() throws EMSException {
        long count;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) 
                {
            count = session.createQuery("SELECT COUNT(p) FROM Project p", 
                                        Long.class).getSingleResult();
        } catch (HibernateException exception) {
            LoggerUtil.error("Projects count failed!", exception);
            throw new EMSException(Constants.ERROR_012);
        }
        LoggerUtil.info("Project count executed successfully!");
        return count;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int insertProject(Project project) throws EMSException {
        int id;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            id = (int) session.save(project);
            transaction.commit();
        } catch (HibernateException exception) {
            LoggerUtil.error("Project insertion failed!", exception);
            throw new EMSException(Constants.ERROR_013);
        }
        LoggerUtil.info("Project inserted successfully!");
        return id;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Project getById(int id) throws EMSException {
        CriteriaBuilder criteria;
        CriteriaQuery<Project> query;
        Root<Project> root;
        Project project;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            criteria = session.getCriteriaBuilder();
            query = criteria.createQuery(Project.class);
            root = query.from(Project.class);
            root.fetch("employees", JoinType.LEFT);
        
            query.select(root).where(criteria.equal(root.get("id"), id)); 
            project = session.createQuery(query).uniqueResult();
        } catch (HibernateException exception) {
            LoggerUtil.error("Retrieving project by id failed!", exception);
            throw new EMSException(Constants.ERROR_014);
        }
        LoggerUtil.info("Project retrieved successfully!");
        return project;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<Project> getAllProjects() throws EMSException {
        List<Project> projects;
        Query<Project> query;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            query = session.createQuery("SELECT DISTINCT p FROM Project p LEFT"
                    + " JOIN FETCH p.employees", Project.class);
            query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false );
            projects = query.getResultList();
        } catch (HibernateException exception) {
            LoggerUtil
                    .error("Retrieving all projects failed!", exception);
            throw new EMSException(Constants.ERROR_012);
        }
        LoggerUtil.info("All projects retrieved successfully!");
        return projects;
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean updateProject(Project project) throws EMSException {
        boolean isUpdated = false;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            session.merge(project);
            transaction.commit();
            isUpdated = true;
        } catch (HibernateException exception) {
            LoggerUtil.error("Project update failed!", exception);
            throw new EMSException(Constants.ERROR_015);
        }
        LoggerUtil.info("Project updated successfully!");
        return isUpdated; 
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteById(int id) throws EMSException {
        boolean isDeleted = false;
        CriteriaBuilder criteria;
        CriteriaDelete<Project> delete;
        Root<Project> root;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            criteria = session.getCriteriaBuilder();
            delete = criteria.createCriteriaDelete(Project.class);
            root = delete.from(Project.class);
        
            delete.where(criteria.equal(root.get("id"), id));
            session.createQuery(delete).executeUpdate();
            transaction.commit();
            isDeleted = true;
        } catch (HibernateException exception) {
            LoggerUtil.error("Project deletion failed!", exception);
            throw new EMSException(Constants.ERROR_016);
        }
        LoggerUtil.info("Project deleted successfully!");
        return isDeleted;
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteAllProjects() throws EMSException {
        boolean isDeleted = false;
        Query query;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            query = session.createQuery("DELETE FROM Project");
            query.executeUpdate();
            transaction.commit();
            isDeleted = true;
        } catch (HibernateException exception) {
            LoggerUtil.error("Deletion all projects failed!", exception);
            throw new EMSException(Constants.ERROR_017);
        }
        LoggerUtil.info("All projects deleted successfully!");
        return isDeleted;
    }
}
