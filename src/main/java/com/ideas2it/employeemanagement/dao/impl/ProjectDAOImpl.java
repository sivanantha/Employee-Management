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
import com.ideas2it.employeemanagement.logger.EMSLogger;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.utils.Constants;

/**
 * This class provides methods for create, update, view, delete, assign,
 * unAssign employees for project in database.
 * 
 * @author Sivanantham
 * @version 1.1
 */
public class ProjectDAOImpl implements ProjectDAO {
    private static EMSLogger logger = new EMSLogger(ProjectDAOImpl.class);

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Project insertProject(Project project) throws EMSException {
        int id;
        Transaction transaction;

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            id = (int) session.save(project);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Project insertion failed!", exception);
            throw new EMSException(Constants.ERROR_013);
        }
        logger.info("Project inserted successfully!");
        return project;
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

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            criteria = session.getCriteriaBuilder();
            query = criteria.createQuery(Project.class);
            root = query.from(Project.class);
            root.fetch("employees", JoinType.LEFT);

            query.select(root).where(criteria.equal(root.get("id"), id));
            project = session.createQuery(query).uniqueResult();
        } catch (HibernateException exception) {
            logger.error("Retrieving project by id failed!", exception);
            throw new EMSException(Constants.ERROR_014);
        }
        logger.info("Project retrieved successfully!");
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

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            query = session.createQuery("SELECT DISTINCT p FROM Project p LEFT"
                    + " JOIN FETCH p.employees", Project.class);
            query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
            projects = query.getResultList();
        } catch (HibernateException exception) {
            logger.error("Retrieving all projects failed!", exception);
            throw new EMSException(Constants.ERROR_012);
        }
        logger.info("All projects retrieved successfully!");
        return projects;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Project updateProject(Project project) throws EMSException {
        Transaction transaction;

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            project = (Project) session.merge(project);
            transaction.commit();
        } catch (HibernateException exception) {
            logger.error("Project update failed!", exception);
            throw new EMSException(Constants.ERROR_015);
        }
        logger.info("Project updated successfully!");
        return project;
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

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            criteria = session.getCriteriaBuilder();
            delete = criteria.createCriteriaDelete(Project.class);
            root = delete.from(Project.class);

            delete.where(criteria.equal(root.get("id"), id));
            session.createQuery(delete).executeUpdate();
            transaction.commit();
            isDeleted = true;
        } catch (HibernateException exception) {
            logger.error("Project deletion failed!", exception);
            throw new EMSException(Constants.ERROR_016);
        }
        logger.info("Project deleted successfully!");
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

        try (Session session = HibernateUtil.getSessionFactory()
                .openSession()) {
            transaction = session.beginTransaction();
            query = session.createQuery("DELETE FROM Project");
            query.executeUpdate();
            transaction.commit();
            isDeleted = true;
        } catch (HibernateException exception) {
            logger.error("Deletion all projects failed!", exception);
            throw new EMSException(Constants.ERROR_017);
        }
        logger.info("All projects deleted successfully!");
        return isDeleted;
    }
}
