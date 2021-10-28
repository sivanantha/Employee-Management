/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;

/**
 * The EmployeeService interface provides methods for validations and
 * create, update, retrieve, delete operations for employee management system.
 *
 * @author  Sivanantham
 * @version 1.3
 */
public interface EmployeeService {
    
    /**
     * Searches for the specified employee id.
     * 
     * 
     * @param id  the id of the employee to be searched as a integer.
     * @return true if employee found, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean isEmployeeExist(int id) throws HibernateException;
    
    /**
     * Checks if the specified employee id is a non negative integer
     * (0 excluded). Allows leading and trailing spaces. Leading zeros are not  
     * allowed (e.g) 001. Signed integers are not considered valid (e.g) +1, -9. 
     * It does not check for id duplication.
     *
     * @param the employee id to be validated as a string.
     * @return true if specified employee id is valid, otherwise false. 
     */
    boolean isValidId(String id);
    
    /**
     * Validates and parses given employee id.
     * 
     * @param id the employee id to be validated.
     * @return employee id as a Integer if it is valid else null.
     */
    Integer validateId(String id);
    
    /**
     * Checks if the specified name is valid. Middle name and last name are 
     * optional. First name can have 3 to 20 letters, middle and last name
     * can have 2 to 20 letters. Leading and trailing spaces are allowed. 
     *
     * @param name name of the employee to be validated as a string.
     * @return true if specified name is valid, otherwise false.
     */
    boolean isValidName(String name);
    
    /**
     * Validates the specified employee name and converts it to lowercase.
     * 
     * @param name the employee name to be validated.
     * @return employee name as a string if it is valid else null.
     */
    String validateName(String name);
    
    /**
     * Checks if the employee's age is above 18 (inclusive) and below 
     * 60 (inclusive).
     * 
     * @param dateOfBirth the employee's date of birth as a LocalDate.
     * @return true if specified date of birth is valid otherwise false.
     */
    boolean isValidDateOfBirth(LocalDate dateOfBirth);
    
    /**
     * Validates and parses given employee date of birth.
     * 
     * @param dateOfBirth the employee date of birth to be validated.
     * @return employee date of birth as a LocalDate if it is valid else null.
     */
    LocalDate validateDateOfBirth(String dateOfBirth);
    
    /**
     * Checks if the specified gender is valid(male/female/others).
     * 
     * @param gender employee's gender as string value.
     * @return true if specified gender is valid, otherwise false.
     */ 
    boolean isValidGender(String gender);
    
    /**
     * Validates the specified employee gender and converts it to lowercase.
     * 
     * @param gender the employee gender to be validated.
     * @return employee gender as a string if it is valid else null.
     */
    String validateGender(String gender);
    
    /**
     * Checks if the given mobile number is a 10 digit non negative integer
     * Starting digit must be in range 6 to 9. 
     *
     * @param mobileNumber the employee mobile number as string value.
     * @return true if specified mobile number is valid otherwise false.
     * 
     */
    boolean isValidMobileNumber(String mobileNumber);
    
    /**
     * Validates and parses given employee mobile number.
     * 
     * @param mobileNumber the employee mobile number to be validated.
     * @return employee mobile number as a Long if it is valid else null.
     */
    Long validateMobileNumber(String mobileNumber);
    
    /** 
     * Checks if the specified mobile number already exist in the database.
     *
     * @param the mobile number to be searched as a long.
     * @return true if mobile number already exist, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean isMobileNumberExist(long mobileNumber) throws HibernateException;
    
    /**
     * Checks if the given email is valid. Capital letters are not allowed.
     * '-', '_', '.' are allowed special characters. Same type of special
     * characters cannot be consecutive (e.g) siva..3@gmail.com. Email address
     * must start and end with a letter or number. There must be atleast 3 
     * characters and maximum 53 characters. Leading and Trailing spaces are
     * allowed. After '@' atleast 1 domain name and atmost 3 domain names 
     * allowed.
     * 
     * @param email employee's email to be validated as string value.
     * @return true if specified email is valid, otherwise false.
     */
    boolean isValidEmail(String email);
    
    /**
     * Validates the given employee email.
     * 
     * @param email the employee email to be validated.
     * @return employee email as a string if it is valid else null.
     */
    String validateEmail(String email);
 
    /**
     * Checks if the specified email is already exist in the database.
     *
     * @param email employee's email to be searched as string value.
     * @return true if specified email is found, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */ 
    boolean isEmailExist(String email) throws HibernateException;
    
    /**
     * Checks if the specified salary is non negative and atleast 8,000.
     * Commas are not allowed. Allows one or two decimal points. (e.g) 8000.50,
     * 30000.6. Trailing and leading spaces are allowed.
     *
     * @param salary employee's salary to be validated, as string.
     * @return true if specified salary is valid, otherwise false.
     */
    boolean isValidSalary(String salary);
    
    /**
     * Validates and parses given employee salary.
     * 
     * @param salary the employee salary to be validated.
     * @return employee salary as a Float if it is valid else null.
     */
    Float validateSalary(String salary);
    
    /**
     * Checks if the specified date of joining is valid. Future dates are
     * not allowed. it does not accept dates that are older than 43 years 
     * (excluded) from the present date.
     * 
     * @param dateOfJoining employee date of joining to be validated as a 
     *        LocalDate.
     * @return true if specified date is valid else false.
     */
    boolean isValidDateOfJoining(LocalDate dateOfJoining);
    
    /**
     * Validates and parses given employee date of joining.
     * 
     * @param dateOfJoining the employee date of joining to be validated.
     * @return employee date of joining as a LocalDate if it is valid else null.
     */
    LocalDate validateDateOfJoining(String dateOfJoining);
    
    /**
     * Checks if the employee database is empty.
     * Helper function for view, update and delete operations.
     *
     * @return true if employee database is empty else false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean isEmployeesDatabaseEmpty() throws HibernateException;
    
    /**
     * Validates the specified door number.
     * Door number starts with 0 is not allowed. door number can have maximum
     * of 5 digits, can contain single capital letter with or without '-' hyphen
     * single '/' is allowed. Leading trailing spaces are accepted.
     * (e.g) 12, 12B, C1, A-9, 3-F, 1/30, B12/2C, 10C/23, D-30/5, 4-F/224, 
     * 12/B5
     *
     * @param doorNumber the door number to be validated.
     * @return true if it is valid, otherwise false.
     */
    boolean isValidDoorNumber(String doorNumber);
    
    /**
     * Validates the specified street name.
     * capital and small letters are accepted.
     * minimum of 4 and maximum of 55 characters are accepted. single '.' 
     * between letters is accepted. maximum of 5 digits are accepted. Leading, 
     * trailing spaces are accepted.
     * (e.g) 1st st, street no.34, k.k st.
     * 
     * @param street the street name to be validated.
     * @return true if it is valid, otherwise false.
     */  
    boolean isValidStreet(String street);
    
    /**
     * Validates the specified locality/city/state/country name.
     * only single space is accepted between words. Leading and trailing spaces
     * are accepted. The name can have total of 100 characters. Only alphabets
     * are allowed (can be capital or small letters).
     *
     * @param locality the name of the locality/city/state/country to be 
     *        validated.
     * @return true if valid, otherwise false.
     */
    boolean isValidPlaceName(String name);
    
    /**
     * Validates the specified pin code.
     * Space is not accepted between digits. Leading and trailing spaces are 
     * allowed. pin code can be 3 to 9 digits long. Numbers only allowed.
     *
     * @param pinCode the pin code to be validated as a string.
     * @return true if it is valid, otherwise false.
     */
    boolean isValidPinCode(String pinCode);
    
    /**
     * Creates a new employee with specified details and stores in the database.
     *
     * @param employee the EmployeeDTO instance with employee details.
     * @return the employee's id as a int.
     * @throws HibernateException if a database access error occurs.
     */
    int createEmployee (EmployeeDTO employee) throws HibernateException;
    
    /**
     * Creates new addresses with specified details and stores in the 
     * database.
     *
     * @param employeeDTO the EmployeeDTO instance with address details.
     * @return true if addresses created successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean createAddresses(EmployeeDTO employeeDTO) throws HibernateException;
    
    /**
     * Retrieves the specified employee from the database.
     * 
     * @param id the employee id to be retrieved as a int.
     * @return the specified employee if found otherwise null.
     * @throws HibernateException if a database access error occurs.
     */
     EmployeeDTO getEmployee(int id) throws HibernateException;
    
    /** 
     * Retrieves all employees from the database.
     *
     * @return a List containing all employees.
     * @throws HibernateException if a database access error occurs.
     */
    List<EmployeeDTO> getAllEmployees() throws HibernateException;
   
    /** 
     * Updates all details of the specified employee and stores in the database.
     *
     * @param employeeDTO the EmployeeDTO instance with employee details.
     * @return true if employee updated successfully otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean updateEmployee(EmployeeDTO employeeDTO) throws HibernateException;
    
    /** 
     * Updates employee's address details and stores in the database.
     *
     * @param addressDTO the addressDTO instance with address details.
     * @return true if address updated successfully otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
     boolean updateAddress(AddressDTO addressDTO) throws HibernateException;
            
    /**
     * Deletes the specified employee.
     *
     * @param id employee id to be deleted.
     * @return true if employee deleted successfully else false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean deleteEmployee(int id) throws HibernateException;
    
    /**
     * Deletes the specified address of an employee.
     *
     * @param addressId id of the address to be deleted.
     * @return true if address deleted successfully else false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean deleteAddress(int addressId) throws HibernateException;
    
    /** 
     * Deletes all employees from the database. 
     *
     * @return true if deleted successfully, otherwise false.
     * @throws HibernateException if a database access error occurs.
     */
    boolean deleteAllEmployees() throws HibernateException;
}
