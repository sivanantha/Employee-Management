/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * This class provides methods for create, update, view, delete employee  
 * records in database. It also has methods for check if the employee id, mobile
 * number, email already exist in the database.
 *
 * @author  Sivanantham
 * @version 1.3
 */
public class EmployeeDAOImpl implements EmployeeDAO {
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public long getEmployeeCount() throws HibernateException {
        long count;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteria.createQuery(Long.class);
        
        query.select(criteria.count(query.from(Employee.class)));
        count = session.createQuery(query).getSingleResult();
        session.close();
        return count;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public Employee getByMobileNumber(long mobileNumber) throws 
            HibernateException {
        Employee employee;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteria.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        
        query.select(root).where(criteria.equal(root.get("mobileNumber"), 
                                                mobileNumber)); 
        employee = session.createQuery(query).uniqueResult();
        session.close();
        return employee;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public Employee getByEmail(String email) throws HibernateException {
        Employee employee;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteria.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        
        query.select(root).where(criteria.equal(root.get("email"), 
                                                email)); 
        employee = session.createQuery(query).uniqueResult();
        session.close();
        return employee;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public int insertEmployee(Employee employee) throws HibernateException {
        int employeeId;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        employeeId = (int) session.save(employee);
        transaction.commit();
        session.close();
        return employeeId;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public boolean insertAddresses(Employee employee) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Employee newEmployee = (Employee) session.merge(employee);
        transaction.commit();
        session.close();
        return (null != newEmployee);
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public Employee getById(int id) throws HibernateException {
        Employee employee;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteria.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        ListJoin<Object, Object> addresses = (ListJoin<Object, Object>) 
                root.fetch("addresses", JoinType.LEFT);
        
        query.select(root).where(criteria.equal(root.get("id"), id)); 
        employee = session.createQuery(query).uniqueResult();
        session.close();
        return employee;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public List<Employee> getAllEmployees() throws HibernateException {
        List<Employee> employees;
        Session session = HibernateUtil.getSessionFactory().openSession();
        TypedQuery<Employee> query = session.createQuery("SELECT DISTINCT "
                + "emp FROM Employee emp LEFT JOIN FETCH emp.addresses",
                Employee.class);
        
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false );
        employees = query.getResultList();
        session.close();
        return employees;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public Employee updateEmployee(Employee employee) throws HibernateException
            {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee newEmployee = (Employee) session.merge(employee);
        
        transaction.commit();
        session.close();
        return newEmployee;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public Address updateAddress(Address address) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Address newAddress = (Address) session.merge(address);
        
        transaction.commit();
        session.close();
        return newAddress;
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public boolean deleteEmployee(int id) throws HibernateException {
        int entitiesAffected;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaDelete<Employee> delete = criteria.createCriteriaDelete(
                Employee.class);
        Root<Employee> root = delete.from(Employee.class);
        
        delete.where(criteria.equal(root.get("id"), id));
        entitiesAffected = session.createQuery(delete).executeUpdate();
        transaction.commit();
        session.close();
        return (0 != entitiesAffected);
    }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
     @Override
     public boolean deleteAddress(int addressId) throws HibernateException {
        int entitiesAffected;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaDelete<Address> delete = criteria.createCriteriaDelete(
                Address.class);
        Root<Address> root = delete.from(Address.class);
        
        delete.where(criteria.equal(root.get("id"), addressId));
        entitiesAffected = session.createQuery(delete).executeUpdate();
        transaction.commit();
        session.close();
        return (0 != entitiesAffected);
     }
    
    /**
     * 
     * {@inheriDoc}
     * 
     */
    @Override
    public boolean deleteAllEmployees() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query deleteAll = session.createQuery("DELETE FROM Employee");
        
        deleteAll.executeUpdate();
        transaction.commit();    
        session.close();
        return true;
    }
}
