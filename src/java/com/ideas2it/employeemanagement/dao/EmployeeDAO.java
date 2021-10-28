/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * This interface provides methods for create, update, view, delete employee  
 * details in the database.
 *
 * @author  Sivanantham
 * @version 1.3
 */
public interface EmployeeDAO {
    
    /**
     * Calculates the employee count.
     *
     * @return the employee count.
     * @throws HibernateException if a database access error occurs.
     */
    long getEmployeeCount() throws HibernateException;
    
    /**
     * Gets the employee having specified mobile number.
     *
     * @param mobileNumber the mobile number to be searched as a long.
     * @return the employee having specified mobileNumber if
     *         found, otherwise null.
     * @throws HibernateException if a database access error occurs.
     */
    Employee getByMobileNumber(long mobileNumber) throws 
            HibernateException;
    
    /**
     * Gets the employee having specified email.
     *
     * @param email the email address to be searched as a string.
     * @return the employee having specified email if
     *         found, otherwise null.
     * @throws HibernateException if a database access error occurs.
     */
    Employee getByEmail(String email) throws HibernateException;
    
    /**
     * Creates a new employee record in the database.
     *
     * @param employee the employee to be inserted as a Employee object. 
     * @return the employee id as a int.
     * @throws HibernateException if a database access error occurs.
     */
    int insertEmployee(Employee employee) throws HibernateException;
    
    /**
     * Inserts the given list of addresses in the database.
     *
     * @param employee the employee whose address to be inserted as a         
     *        Employee object. 
     * @return true if created successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean insertAddresses(Employee employee) throws HibernateException;
    
    /** 
     * Fetches the specified employee's record.
     *
     * @param id the employee's id as a integer to fetch the record. 
     * @return the specified employee if found, otherwise null.
     * @throws HibernateException if a database access error occurs.
     */
    Employee getById(int id) throws HibernateException;
    
    /** 
     * Fetches all employees record.
     * 
     * @return a List containing all employees.
     * @throws HibernateException if a database access error occurs.
     */
    List<Employee> getAllEmployees() throws HibernateException;
    
    /**
     * Updates specified employee's all details.
     * 
     * @param employee the employee to be updated as a Employee object.
     * @return the updated Employee.
     * @throws HibernateException if a database access error occurs.
     */
    Employee updateEmployee(Employee employee) throws HibernateException;
    
    /**
     * Updates specified employee's address.
     * 
     * @param address the address to be updated as a Address instance.
     * @return the updated Address.
     * @throws HibernateException if a database access error occurs.
     */
    Address updateAddress(Address address) throws HibernateException;
    
    /** 
     * Deletes specified employee.
     *
     * @param the employee's id as a int to be deleted.
     * @return true if specified employee deleted, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean deleteEmployee(int id) throws HibernateException;
    
    /**
     * Deletes specified address of an employee.
     *
     * @param addressId the id of the address to be deleted
     * @return true if deleted successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
     boolean deleteAddress(int addressId) throws HibernateException;
    
    /**
     * Deletes all employees in the database.
     * 
     * @return true if deleted successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean deleteAllEmployees() throws HibernateException;
}
