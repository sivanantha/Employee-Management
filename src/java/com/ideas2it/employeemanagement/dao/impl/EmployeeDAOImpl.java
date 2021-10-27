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
     * Calcultes the employee count.
     *
     * @return the employee count.
     * @throws HibernateException if a database access error occurs.
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
     * Gets the employee having specified mobile number.
     *
     * @param mobileNumber the mobile number to be searched as a long.
     * @return a list containing the employee having specified mobileNumber if
     *         found.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public List<Employee> getByMobileNumber(long mobileNumber) throws 
            HibernateException {
        List<Employee> employees;
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteria.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        
        query.select(root).where(criteria.equal(root.get("mobileNumber"), 
                                                mobileNumber)); 
        employees = session.createQuery(query).getResultList();
        session.close();
        return employees;
    }
    
    /**
     * Gets the employee having specified email.
     *
     * @param email the email address to be searched as a string.
     * @return a list containing 
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public List<Employee> getByEmail(String email) throws HibernateException {
        List<Employee> employees;
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteria.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        
        query.select(root).where(criteria.equal(root.get("email"), 
                                                email)); 
        employees = session.createQuery(query).getResultList();
        session.close();
        return employees;
    }
    
    /**
     * Creates a new employee record in the database.
     *
     * @param employee the employee to be inserted as a Employee object. 
     * @return the employee id as a int.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public int insertEmployee(Employee employee) throws HibernateException {
        int employeeId;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        employeeId = (int) session.save(employee);
        
        for (Address address : employee.getAddresses()) {
            address.setEmployee(employee);
            session.save(address);
        }
        transaction.commit();
        session.close();
        return employeeId;
    }
    
    /**
     * Inserts the given list of addresses in the database.
     *
     * @param employee the employee whose address to be inserted as a         
     *        Employee object. 
     * @return the count of inserted addresses.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public int insertAddresses(Employee employee) throws HibernateException {
        int count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee oldEmployee = session.get(Employee.class, employee.getId());
        
        oldEmployee.getAddresses().addAll(employee.getAddresses());
        
        for (Address address : employee.getAddresses()) {
            session.save(address);
            count++;
        }
        transaction.commit();
        session.close();
        return count;
    }
    
    /** 
     * Fetches the specified employee's record.
     *
     * @param id the employee's id as a integer to fetch the record. 
     * @return a List containing the specified employee.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public List<Employee> getById(int id) throws HibernateException {
        List<Employee> employees;
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteria.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);
        ListJoin<Object, Object> addresses = (ListJoin<Object, Object>) 
                root.fetch("addresses", JoinType.LEFT);
        
        query.select(root).where(criteria.equal(root.get("id"), id)); 
        employees = session.createQuery(query).getResultList();
        session.close();
        return employees;
    }
    
    /** 
     * Fetches all employees record.
     * 
     * @return a List containing all employees.
     * @throws HibernateException if a database access error occurs.
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
     * Updates specified employee's all details.
     * 
     * @param employee the employee to be updated as a Employee object.
     * @return the updated Employee.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public Employee updateEmployee(Employee employee) throws HibernateException
            {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee oldEmployee = session.load(Employee.class, employee.getId());
        Employee newEmployee = (Employee) session.merge(employee);
        
        transaction.commit();
        session.close();
        return newEmployee;
    }
    
    /**
     * Updates specified employee's address.
     * 
     * @param address the address to be updated as a Address instance.
     * @return the updated Address.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public Address updateAddress(Address address) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Address oldAddress = session.load(Address.class, address.getId());
        Address newAddress = (Address) session.merge(address);
        
        transaction.commit();
        session.close();
        return newAddress;
    }
    
    /** 
     * Deletes specified employee.
     *
     * @param the employee's id as a int to be deleted.
     * @return true if specified employee deleted, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public boolean deleteEmployee(int id) throws HibernateException {
        int entitiesAffected;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaDelete<Address> deleteAddress = criteria.createCriteriaDelete(
                Address.class);
        CriteriaDelete<Employee> deleteEmployee = criteria.createCriteriaDelete(
                Employee.class);
        Root<Address> AddressRoot = deleteAddress.from(Address.class);
        Root<Employee> EmployeeRoot = deleteEmployee.from(Employee.class);
        
        deleteAddress.where(criteria.equal(AddressRoot.get("employee"), id));
        deleteEmployee.where(criteria.equal(EmployeeRoot.get("id"), id));
        session.createQuery(deleteAddress).executeUpdate();
        entitiesAffected = session.createQuery(deleteEmployee).executeUpdate();
        transaction.commit();
        session.close();
        return (0 != entitiesAffected);
    }
    
    /**
     * Deletes specified address of an employee.
     *
     * @param addressId the id of the address to be deleted
     * @return true if deleted successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
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
     * Deletes all employees in the database.
     * 
     * @return true if deleted successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    @Override
    public boolean deleteAllEmployees() throws HibernateException {
        int entitiesAffected;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query deleteAllAddress = session.createQuery("DELETE FROM Address");
        Query deleteAllEmployee = session.createQuery("DELETE FROM Employee");
        
        deleteAllAddress.executeUpdate();
        entitiesAffected = deleteAllEmployee.executeUpdate();
        transaction.commit();    
        session.close();
        return true;
    }
}
