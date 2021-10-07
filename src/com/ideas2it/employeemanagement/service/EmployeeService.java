/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.employeemanagement.model.Employee;

/**
 * The EmployeeServiceInterface interface provides methods for validations and
 * create, update, retrieve, delete operations for employee management system.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public interface EmployeeService {
    
    /**
     * Searches for the specified employee id.
     * 
     * 
     * @param id  the id of the employee to be searched as a integer.
     * @return true if employee found, otherwise false.
     */
    boolean isEmployeeExist(int id);
    
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
     */
    boolean isMobileNumberExist(long mobileNumber);
    
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
     */ 
    boolean isEmailExist(String email);
    
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
     * @return true if spcified date is valid else false.
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
     */
    boolean isEmployeesDatabaseEmpty();
    
    /**
     * Retrieves the specified employee from the database.
     * 
     * @param id the employee id to be retrieved as a int.
     * @return a List containing the specified employee.
     */
    List<Employee> getEmployee(int id);
    
    /** 
     * Retrieves all employees from the database.
     *
     * @return a List containing all employees.
     */
    List<Employee> getAllEmployees();
   
    /**
     * Creates a new employee with specified details and stores in the database.
     * 
     * @return true if employee created successfully else false.
     */
    boolean createEmployee (int id,String name,LocalDate dateOfBirth,
            String gender, long mobileNumber, String email, float salary, 
            LocalDate dateOfJoining);
   
    /**
     * Updates specified employee's name and stores in the database.
     *
     * @param id employee id to be updated as a int.
     * @param name the employee's new name to update.
     * @return true if employee name updated successfully else false.
     */
    boolean updateName(int id, String name);
   
    /**
     * Updates specified employee's date of birth and stores in the database.
     *
     * @param id employee id to be updated.
     * @param dateOfBirth the employee's new date of birth to update.
     * @return true if employee date of birth updated successfully else false.
     */
    boolean updateDateOfBirth(int id, LocalDate dateOfBirth);
   
    /**
     * Updates specified employee's gender and stores in the database.
     *
     * @param id employee id to be updated.
     * @param gender the employee's gender to update.
     * @return true if employee gender updated successfully else false.
     */
    boolean updateGender(int id, String gender);
   
    /**
     * Updates specified employee's mobile number and stores in the database.
     *
     * @param id employee id to be updated.
     * @param mobileNumber the employee's new moible number to update.
     * @return true if employee mobile number updated successfully else false.
     */
    boolean updateMobileNumber(int id, long mobileNumber);
   
    /**
     * Updates specified employee's email and stores in the database.
     *
     * @param id employee id to be updated.
     * @param email the employee's new email to update.
     * @return true if employee email updated successfully else false.
     */
    boolean updateEmail(int id, String email);
   
    /**
     * Updates specified employee's salary and stores in the database.
     *
     * @param id employee id to be updated.
     * @param salary the employee's new salary to update.
     * @return true if employee salary updated successfully else false.
     */
    boolean updateSalary(int id, float salary);
   
    /**
     * Updates specified employee's date of joining and stores in the database.
     *
     * @param id employee id to be updated.
     * @param dateOfJoining the employee's new date of joining to update.
     * @return true if employee date of joining updated successfully else false.
     */
   boolean updateDateOfJoining(int id, LocalDate dateOfJoining);
   
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
     */
     boolean updateAllDetails(int id, String name,LocalDate dateOfBirth,
             String gender, long mobileNumber, String email, float salary, 
             LocalDate dateOfJoining);
            
    /**
     * Deletes the specified employee.
     *
     * @param id employee id to be deleted.
     * @return true if employee deleted successfully else false.
     */
    boolean deleteEmployee(int id);
   
    /** Deletes all employees from the database. */
    void deleteAllEmployee();
}
