/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * The EmployeeController class contains CRUD implementations for employee 
 * management system. It has useful methods for create, update, retrieve, 
 * delete and validate employee details.
 *
 * @author  Sivanantham
 * @version 1.5
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    /**
     * Checks if the specified employee exist.
     *
     * @param id the employee id to be searched.
     * @return true if specified employee found, else false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean isEmployeeExist(int id) throws SQLException {
        return employeeService.isEmployeeExist(id);
    }
    
    /**
     * Checks if the employees database is empty.
     * 
     * @return true if database is empty, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean isEmployeesDatabaseEmpty() throws SQLException {
        return employeeService.isEmployeesDatabaseEmpty();
    }
    
    /**
     * Validates and parses given employee id.
     * 
     * @param id the employee id to be validated.
     * @return employee id as a Integer if it is valid else null.
     */
    public Integer validateId(String id) {
        return employeeService.validateId(id);
    }
    
    /**
     * Validates the specified employee name and converts it to lowercase.
     * 
     * @param name the employee name to be validated.
     * @return employee name as a string if it is valid else null.
     */
    public String validateName(String name) {
        return employeeService.validateName(name);
    }
    
    /**
     * Validates and parses given employee date of birth.
     * 
     * @param dateOfBirth the employee date of birth to be validated.
     * @return employee date of birth as a LocalDate if it is valid else null.
     */
    public LocalDate validateDateOfBirth(String dateOfBirth) {
        return employeeService.validateDateOfBirth(dateOfBirth);
    }
    
    /**
     * Validates the specified employee gender and converts it to lowercase.
     * 
     * @param gender the employee gender to be validated.
     * @return employee gender as a string if it is valid else null.
     */
    public String validateGender(String gender) {
        return employeeService.validateGender(gender);
    }
    
    /**
     * Validates and parses given employee mobile number.
     * 
     * @param mobileNumber the employee mobile number to be validated.
     * @return employee mobile number as a Long if it is valid else null.
     */
    public Long validateMobileNumber(String mobileNumber) {
         return employeeService.validateMobileNumber(mobileNumber);
    }
    
    /**
     * Checks if the specified mobile number is exist.
     * 
     * @param mobileNumber the employee mobile number to be searched.
     * @return true if the mobile number found, else false.
     * @exception SQLException if a database access error occurs.
     */   
    public boolean isMobileNumberExist(long mobileNumber) throws SQLException {
         return employeeService.isMobileNumberExist(mobileNumber);
    }
    
    /**
     * Validates the given employee email.
     * 
     * @param email the employee email to be validated.
     * @return employee email as a string if it is valid else null.
     */
    public String validateEmail(String email) {
         return employeeService.validateEmail(email);
    }
    
    /**
     * Checks if the specified email is exist.
     * 
     * @param email the employee email to be searched.
     * @return true if the email found, else false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean isEmailExist(String email) throws SQLException {
         return employeeService.isEmailExist(email);
    }
    
    /**
     * Validates and parses given employee salary.
     * 
     * @param salary the employee salary to be validated.
     * @return employee salary as a Float if it is valid else null.
     */
    public Float validateSalary(String salary) {
         return employeeService.validateSalary(salary);
    }
    
    /**
     * Validates and parses given employee date of joining.
     * 
     * @param dateOfJoining the employee date of joining to be validated.
     * @return employee date of joining as a LocalDate if it is valid else null.
     */
    public LocalDate validateDateOfJoining(String dateOfJoining) {
        return employeeService.validateDateOfJoining(dateOfJoining);
    }
    
    /**
     * Creates a new employee with specified details and stores in the database.
     *
     * @param name the name of the employee.
     * @param gender the gender of the employee.
     * @param dateOfBirth the date of birth of the employee.
     * @param mobileNumber the mobile number of the employee.
     * @param email the email address of the employee.
     * @param salary the salary of the employee.
     * @param dateOfJoining the employee's date of joining. 
     * @return the employee's id as a int.
     * @exception SQLException if a database access error occurs.
     */
    public int createEmployee (String name, LocalDate dateOfBirth, 
            String gender, long mobileNumber, String email, float salary, 
            LocalDate dateOfJoining) throws SQLException {
       
        return employeeService.createEmployee(name, dateOfBirth, gender, 
                       mobileNumber, email, salary, dateOfJoining);
    }
    
    /**
     * Retrieves the specified employee.
     * 
     * @param id the employee id to be retrieved as a int.
     * @return a List containing the specified employee.
     * @exception SQLException if a database access error occurs.
     */
    public List getEmployee(int id) throws SQLException {
        return employeeService.getEmployee(id);
    }
    
    /** 
     * Retrieves all employees.
     *
     * @return a List containing all employees.
     * @exception SQLException if a database access error occurs.
     */
    public List getAllEmployees() throws SQLException {
        return employeeService.getAllEmployees();
    }
    
    /**
     * Updates specified employee's name and stores in the database.
     *
     * @param id employee id as a int.
     * @param name the employee's new name to update.
     * @return true if employee name updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateName(int id, String name) throws SQLException {
        return employeeService.updateName(id, name);
    }
    
    /**
     * Updates specified employee's date of birth and stores in the database.
     *
     * @param id employee id.
     * @param dateOfBirth the employee's new date of birth to update.
     * @return true if employee date of birth updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateDateOfBirth(int id, LocalDate dateOfBirth) 
            throws SQLException {
        return employeeService.updateDateOfBirth(id, dateOfBirth);
    }
    
    /**
     * Updates specified employee's gender and stores in the database.
     *
     * @param id employee id.
     * @param gender the employee's gender to update.
     * @return true if employee gender updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateGender(int id, String gender) throws SQLException {
        return employeeService.updateGender(id, gender);
    }
    
    /**
     * Updates specified employee's mobile number and stores in the database.
     *
     * @param id employee id.
     * @param mobileNumber the employee's new mobile number to update.
     * @return true if employee mobile number updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateMobileNumber(int id, long mobileNumber) 
            throws SQLException {
        return employeeService.updateMobileNumber(id, mobileNumber);
    }
    
   /**
    * Updates specified employee's email and stores in the database.
    *
    * @param id employee id.
    * @param email the employee's new email to update.
    * @return true if employee email updated successfully else false.
    * @exception SQLException if a database access error occurs.
    */    
    public boolean updateEmail(int id, String email) throws SQLException {
        return employeeService.updateEmail(id, email);
    }
    
   /**
    * Updates specified employee's salary and stores in the database.
    *
    * @param id employee id.
    * @param salary the employee's new salary to update.
    * @return true if employee salary updated successfully else false.
    * @exception SQLException if a database access error occurs.
    */    
    public boolean updateSalary(int id, float salary) throws SQLException {
        return employeeService.updateSalary(id, salary);
    }
    
   /**
    * Updates specified employee's date of joining and stores in the database.
    *
    * @param id employee id.
    * @param dateOfJoining the employee's new date of joining to update.
    * @return true if employee date of joining updated successfully else false.
    * @exception SQLException if a database access error occurs.
    */
    public boolean updateDateOfJoining(int id, LocalDate dateOfJoining) 
            throws SQLException {
        return employeeService.updateDateOfJoining(id, dateOfJoining);
    }
    
   /** 
    * Updates all details of the specified employee and stores in the database.
    *
    * @param id employee's id to be updated.
    * @param name the name of the employee to update.
    * @param gender the gender of the employee to update.
    * @param dateOfBirth the date of birth of the employee to update.
    * @param mobileNumber the mobile number of the employee to update.
    * @param email the email address of the employee to update.
    * @param salary the salary of the employee to update.
    * @param dateOfJoining the employee's date of joining to update.
    * @return true if employee updated successfully otherwise false.
    * @exception SQLException if a database access error occurs.
    */    
    public boolean updateAllDetails(int id, String name,LocalDate dateOfBirth,
            String gender, long mobileNumber, String email, float salary, 
            LocalDate dateOfJoining) throws SQLException {
            
        return employeeService.updateAllDetails(id, name, dateOfBirth, gender,
                       mobileNumber, email, salary, dateOfJoining);
    }
    
   /**
    * Deletes the specified employee.
    *
    * @param id employee id to be deleted.
    * @return true if employee deleted successfully else false.
    * @exception SQLException if a database access error occurs.
    */    
    public boolean deleteEmployee(int id) throws SQLException {
        return employeeService.deleteEmployee(id);
    }
    
    /** 
     * Deletes all employees from the database. 
     *
     * @return true if deleted successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean deleteAllEmployee() throws SQLException {
        return employeeService.deleteAllEmployee();
    }
}
