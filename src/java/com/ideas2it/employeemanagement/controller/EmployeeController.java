/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * The EmployeeController class contains CRUD implementations for employee 
 * management system. It has useful methods for create, update, retrieve, 
 * delete and validate employee details.
 *
 * @author  Sivanantham
 * @version 1.6
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    /**
     * Checks if the specified employee exist.
     *
     * @param id the employee id to be searched.
     * @return true if specified employee found, else false.
     * @throws HibernateException if a database access error occurs.
     */
    public boolean isEmployeeExist(int id) throws HibernateException {
        return employeeService.isEmployeeExist(id);
    }
    
    /**
     * Checks if the employees database is empty.
     * 
     * @return true if database is empty, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    public boolean isEmployeesDatabaseEmpty() throws HibernateException {
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
     * @throws HibernateException if a database access error occurs.
     */   
    public boolean isMobileNumberExist(long mobileNumber) throws 
            HibernateException {
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
     * @throws HibernateException if a database access error occurs.
     */
    public boolean isEmailExist(String email) throws HibernateException {
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
     * Validates the specified door number.
     *
     * @param doorNumber the door number to be validated.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidDoorNumber(String doorNumber) {
        return employeeService.isValidDoorNumber(doorNumber);
    }
    
    /**
     * Validates the specified street name.
     * 
     * @param street the street name to be validated.
     * @return true if it is valid, otherwise false.
     */  
    public boolean isValidStreet(String street) {
        return employeeService.isValidStreet(street);
    }
    
    /**
     * Validates the specified locality/city/state/country name.
     *
     * @param locality the name of the locality/city/state/country to be 
     *                 validated.
     * @return true if valid, otherwise false.
     */
    public boolean isValidPlaceName(String name) {
        return employeeService.isValidPlaceName(name);
    }
    
    /**
     * Validates the specified pin code.
     *
     * @param pinCode the pin code to be validated as a string.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidPinCode(String pinCode) {
        return employeeService.isValidPinCode(pinCode);
    }
    
    /**
     * Creates a new employee with specified details and stores in the database.
     *
     * @param employeeDTO the EmployeeDTO instance with employee details.
     * @return the employee's id as a int.
     * @throws HibernateException if a database access error occurs.
     */
    public int createEmployee (EmployeeDTO employeeDTO) throws
            HibernateException {
        return employeeService.createEmployee(employeeDTO);
    }
    
    /**
     * Creates new addresses with specified details and stores in the 
     * database.
     *
     * @param employeeDTO the EmployeeDTO instance with address details.
     * @return true if addresses created successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    public boolean createAddresses(EmployeeDTO employeeDTO) throws 
            HibernateException {
        return employeeService.createAddresses(employeeDTO);
    }
    
    /**
     * Retrieves the specified employee.
     * 
     * @param id the employee id to be retrieved as a int.
     * @return the specified employee if found, otherwise null.
     * @throws HibernateException if a database access error occurs.
     */
    public EmployeeDTO getEmployee(int id) throws HibernateException {
        return employeeService.getEmployee(id);
    }
    
    /** 
     * Retrieves all employees.
     *
     * @return a List containing all employees.
     * @throws HibernateException if a database access error occurs.
     */
    public List<EmployeeDTO> getAllEmployees() throws HibernateException {
        return employeeService.getAllEmployees();
    }
       
    /** 
     * Updates all details of the specified employee and stores in the database.
     *
     * @param employeeDTO the EmployeeDTO instance with employee details.
     * @return true if employee updated successfully otherwise false.
     * @throws HibernateException if a database access error occurs.
     */    
     public boolean updateEmployee(EmployeeDTO employeeDTO)
             throws HibernateException {
         return employeeService.updateEmployee(employeeDTO);
     }
     
     /** 
      * Updates employee's address details and stores in the database.
      *
      * @param addressDTO the addressDTO instance with address details.
      * @return true if address updated successfully otherwise false.
      * @throws HibernateException if a database access error occurs.
      */
     public boolean updateAddress(AddressDTO addressDTO) throws 
            HibernateException {
         return employeeService.updateAddress(addressDTO);
    }
    
    /**
     * Fetches all available projects.
     *
     * @return a List containing all available projects.
     * @throws HibernateException if a database  access error occurs.
     */
    public List<ProjectDTO> getAllProjects() throws HibernateException {
        return employeeService.getAllProjects();
    }
    
   /**
    * Deletes the specified employee.
    *
    * @param id employee id to be deleted.
    * @return true if employee deleted successfully else false.
    * @throws HibernateException if a database access error occurs.
    */    
    public boolean deleteEmployee(int id) throws HibernateException {
        return employeeService.deleteEmployee(id);
    }
    
    /**
     * Deletes the specified address of an employee.
     *
     * @param addressId id of the address to be deleted.
     * @return true if address deleted successfully else false.
     * @throws HibernateException if a database access error occurs.
     */
    public boolean deleteAddress(int addressId) throws HibernateException {
         return employeeService.deleteAddress(addressId);
    }
    
    /** 
     * Deletes all employees from the database. 
     *
     * @return true if deleted successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    public boolean deleteAllEmployees() throws HibernateException {
        return employeeService.deleteAllEmployees();
    }
}
