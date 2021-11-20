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
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.jpa.QueryHints;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.employeemanagement.connection_utils.HibernateUtil;
import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.LoggerFactory;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.utils.Constants;

/**
 * This class provides methods for create, update, view, delete employee  
 * records in database. It also has methods for check if the employee id, mobile
 * number, email already exist in the database.
 *
 * @author  Sivanantham
 * @version 1.4
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public long getEmployeeCount() throws EMSException {
        long count;
        CriteriaBuilder criteria;
        CriteriaQuery<Long> query;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) 
                {
            criteria = session.getCriteriaBuilder();
            query = criteria.createQuery(Long.class);
            query.select(criteria.count(query.from(Employee.class)));
            count = session.createQuery(query).getSingleResult();
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Employee count failed!\n"
                                            + exception);
            throw new EMSException(Constants.ERROR_006);
        }
        LoggerFactory.getLogger()
                     .info("Employees count executed successfully!");
        return count;
    }
            
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int insertEmployee(Employee employee) throws EMSException {
        int employeeId;
        Transaction transaction;

        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            employeeId = (int) session.save(employee);
            transaction.commit();
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Employee insertion failed!\n"
                                            + exception);
            throw new EMSException(Constants.ERROR_003);
        }
        LoggerFactory.getLogger().info("Employee inserted successfully!");
        return employeeId;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean insertAddresses(Employee employee) throws EMSException {
        boolean isInserted = false;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
            isInserted = true;
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Address insertion failed!\n"
                                            + exception);
            throw new EMSException(Constants.ERROR_004);
        }
        LoggerFactory.getLogger().info("Address inserted successfully!");
        return isInserted;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Employee getById(int id) throws EMSException {
        CriteriaBuilder criteria;
        CriteriaQuery<Employee> firstQuery;
        CriteriaQuery<Employee> secondQuery;
        Root<Employee> firstRoot;
        Root<Employee> secondRoot;
        Employee employee;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            criteria = session.getCriteriaBuilder();
        
            /* Here the query is split into two parts to avoid Cartesian join 
             * which degrades performance.                                    
             */
            firstQuery = criteria.createQuery(Employee.class);
            firstRoot = firstQuery.from(Employee.class);
            firstRoot.fetch("addresses", JoinType.LEFT);
            firstQuery.select(firstRoot).where(criteria.equal(
                    firstRoot.get("id"), id));
                    
            secondQuery = criteria.createQuery(Employee.class);
            secondRoot = secondQuery.from(Employee.class);
            secondRoot.fetch("projects", JoinType.LEFT);
            secondQuery.select(secondRoot).where(criteria.equal(
                    secondRoot.get("id"), id)); 
            
            employee = session.createQuery(firstQuery).uniqueResult();
            session.createQuery(secondQuery).uniqueResult();
        } catch (HibernateException exception) {
            LoggerFactory.getLogger()
                    .error("Retrieving employee by id failed!\n" + exception);
            throw new EMSException(Constants.ERROR_005);
        }
        LoggerFactory.getLogger().info("Employee retrieved successfully!");
        return employee;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Employee getByMobileNumber(long mobileNumber) throws 
            EMSException {
        CriteriaBuilder criteria;
        CriteriaQuery<Employee> query;
        Root<Employee> root;
        Employee employee;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) 
                {
            criteria = session.getCriteriaBuilder();
            query = criteria.createQuery(Employee.class);
            root = query.from(Employee.class);
            query.select(root).where(criteria.equal(root.get("mobileNumber"), 
                                                    mobileNumber)); 
            employee = session.createQuery(query).uniqueResult();
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Retrieving employee by mobile "
                    + "number failed!\n" + exception);
            throw new EMSException(Constants.ERROR_001);
        }
        LoggerFactory.getLogger().info("Employee retrieved successfully!");
        return employee;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Employee getByEmail(String email) throws EMSException {
        CriteriaBuilder criteria;
        CriteriaQuery<Employee> query;
        Root<Employee> root;
        Employee employee;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            criteria = session.getCriteriaBuilder();
            query = criteria.createQuery(Employee.class);
            root = query.from(Employee.class);
            query.select(root).where(criteria.equal(root.get("email"), 
                                                    email)); 
            employee = session.createQuery(query).uniqueResult();
        } catch (HibernateException exception) {
            LoggerFactory.getLogger()
                    .error("Retrieve employee by email failed!\n" + exception);
            throw new EMSException(Constants.ERROR_002);
        }
        LoggerFactory.getLogger().info("Employee retrieved successfully!");
        return employee;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<Employee> getAllEmployees() throws EMSException {
        List<Employee> employees;
        TypedQuery<Employee> firstQuery;
        TypedQuery<Employee> secondQuery;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
        
            /* Here the query is split into two parts to avoid Cartesian join 
             * which degrade performance.                                    
             */
            firstQuery = session.createQuery("SELECT DISTINCT emp FROM Employee"
                    + " emp LEFT JOIN FETCH emp.addresses", Employee.class);
            secondQuery = session.createQuery("SELECT DISTINCT em FROM Employee"
                    + " em LEFT JOIN FETCH em.projects", Employee.class);
        
            firstQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false );
            secondQuery.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false );
            employees = firstQuery.getResultList();
            employees = secondQuery.getResultList();
        } catch (HibernateException exception) {
            LoggerFactory.getLogger()
                    .error("Retrieving all employees failed!\n" + exception);
            throw new EMSException(Constants.ERROR_006);
        }
        LoggerFactory.getLogger()
                       .info("All employees retrieved successfully!");
        return employees;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean updateEmployee(Employee employee) throws EMSException
            {
        boolean isUpdated = false;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
            isUpdated = true;
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Employee update failed!\n"
                                            + exception);
            throw new EMSException(Constants.ERROR_007);
        }
        LoggerFactory.getLogger().info("Employee updated successfully!");
        return isUpdated;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean updateAddress(Address address) throws EMSException {
        boolean isUpdated = false;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            session.update(address);
            transaction.commit();
            isUpdated = true;
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Address update failed!\n"
                                            + exception);
            throw new EMSException(Constants.ERROR_008);
        }
        LoggerFactory.getLogger().info("Address updated successfully!");
        return isUpdated;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteEmployee(int id) throws EMSException {
        boolean isDeleted = false;
        CriteriaBuilder criteria;
        CriteriaDelete<Employee> delete;
        Root<Employee> root;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            criteria = session.getCriteriaBuilder();
            delete = criteria.createCriteriaDelete(Employee.class);
            root = delete.from(Employee.class);
        
            delete.where(criteria.equal(root.get("id"), id));
            session.createQuery(delete).executeUpdate();
            transaction.commit();
            isDeleted = true;
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Employee deletion failed!\n"
                                            + exception);
            throw new EMSException(Constants.ERROR_009);
        }
        LoggerFactory.getLogger().info("Employee deleted successfully!");
        return isDeleted;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
     @Override
     public boolean deleteAddress(int addressId) throws EMSException {
        boolean isDeleted = false;
        CriteriaBuilder criteria;
        CriteriaDelete<Address> delete;
        Root<Address> root;
        Transaction transaction;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            criteria = session.getCriteriaBuilder();
            delete = criteria.createCriteriaDelete(Address.class);
            root = delete.from(Address.class);
        
            delete.where(criteria.equal(root.get("id"), addressId));
            session.createQuery(delete).executeUpdate();
            transaction.commit();
            isDeleted = true;
        } catch (HibernateException exception) {
            LoggerFactory.getLogger().error("Address deletion failed!\n"
                                            + exception);
            throw new EMSException(Constants.ERROR_010);
        }
        LoggerFactory.getLogger().info("Address deleted successfully!");
        return isDeleted;
     }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteAllEmployees() throws EMSException {
        boolean isDeleted = false;
        Transaction transaction;
        Query deleteAll;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession())
                {
            transaction = session.beginTransaction();
            deleteAll = session.createQuery("DELETE FROM Employee");
            deleteAll.executeUpdate();
            transaction.commit();
            isDeleted = true;    
        } catch (HibernateException exception) {
            LoggerFactory.getLogger()
                    .error("Deletion of all employees failed!\n" + exception);
            throw new EMSException(Constants.ERROR_011);
        }
        LoggerFactory.getLogger().info("All employees deleted successfully!");
        return isDeleted;
    }
}
