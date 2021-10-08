/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * This interface provides methods for create, update, view, delete employee  
 * database records in database.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public interface DataAccess {
    
    /**
     * Checks if the database is empty.
     *
     * @return true if database is empty, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    boolean isDatabaseEmpty() throws SQLException;
    
    /**
     * Checks if the specified employee id is exist.
     *
     * @return true if found, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    boolean isEmployeeIdExist(int id) throws SQLException;
    
    /**
     * Checks if the specified mobile number already exist.
     *
     * @param mobileNumber the mobile number to be searched as a long.
     * @return true if found, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    boolean isMobileNumberExist(long mobileNumber) throws SQLException;
    
    /**
     * Checks if the specified email already exist.
     *
     * @param email the email address to be searched as a string.
     * @return true if found, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    boolean isEmailExist(String email) throws SQLException;
    
    /**
     * Creates a employee record in the database.
     *
     * @param employee the employee to be inserted as a Employee object. 
     * @return the employee id as a long.
     * @exception SQLException if a database access error occurs.
     */
    int insertRecord(Employee employee) throws SQLException;
    
    /** 
     * Fetches the specified employee's record.
     *
     * @param id the employee's id as a integer to fetch the record. 
     * @return a resultset.
     * @exception SQLException if a database access error occurs.
     */
    ResultSet selectRecord(int id) throws SQLException;
    
    /** 
     * Fetches all employees record.
     * 
     * @return a resultset.
     * @exception SQLException if a database access error occurs.
     */
    ResultSet selectAllRecord() throws SQLException;
    
    /**
     * Updates specified employee's name.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee name as a string to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateName(int id, String name) throws SQLException;
    
    /**
     * Updates specified employee's date of birth.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee name as a LocalDate to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateDateOfBirth(int id, LocalDate dateOfBirth) throws SQLException;
    
    /**
     * Updates specified employee's gender.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee gender as a string to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateGender(int id, String gender) throws SQLException;
    
    /**
     * Updates specified employee's mobile number.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee mobile number as a long to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateMobileNumber(int id, long mobileNumber) throws SQLException;
    
    /**
     * Updates specified employee's email.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee email as a string to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateEmail(int id, String email) throws SQLException;
    
    /**
     * Updates specified employee's salary.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee salary as a float to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateSalary(int id, float salary) throws SQLException;
    
    /**
     * Updates specified employee's date of joining.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee date of joining as a LocalDate to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateDateOfJoining(int id, LocalDate dateOfJoining) 
            throws SQLException;
    
    /**
     * Updates specified employee's all details.
     * 
     * @param employee the employee to be updated as a Employee object.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateAllColumn(Employee employee) throws SQLException;
    
    /** 
     * Deletes specified employee.
     *
     * @param the employee's id as a int to be deleted.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int deleteRecord(int id) throws SQLException;
    
    /**
     * Deletes all employees in the database.
     * 
     * @return true if deleted successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    boolean deleteAllRecord() throws SQLException;
}
