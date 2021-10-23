/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service.impl;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDAOImpl;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.utils.Mapper;

/**
 * The EmployeeServiceImpl class contains validations and implementations for 
 * create, update, retrieve, delete operations for employee management system.
 *
 * @author  Sivanantham
 * @version 1.5
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    
    /**
     * Searches for the specified employee id.
     * 
     * 
     * @param id  the id of the employee to be searched as a integer.
     * @return true if employee found, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean isEmployeeExist(int id) throws SQLException {
        return employeeDAO.isEmployeeIdExist(id);
    }
    
    /**
     * Checks if the specified employee id is a non negative integer
     * (0 excluded). Allows leading and trailing spaces. Leading zeros are not  
     * allowed (e.g) 001. Signed integers are not considered valid (e.g) +1, -9. 
     * It does not check for id duplication.
     *
     * @param the employee id to be validated as a string.
     * @return true if specified employee id is valid, otherwise false. 
     */
    @Override
    public boolean isValidId(String id) { 
        return Pattern.matches("(^\\s*[1-9][0-9]*\\s*)$", id);
    }
    
    /**
     * Validates and parses given employee id.
     * 
     * @param id the employee id to be validated.
     * @return employee id as a Integer if it is valid else null.
     */
    @Override
    public Integer validateId(String id) {
        Integer parsedId = null;
        
        if (isValidId(id)) {  
            try {
                parsedId = Integer.parseInt(id.strip());            
            } catch (NumberFormatException exception) {
                parsedId = null;     
            }
        }
        return parsedId;
    }
    
    /**
     * Checks if the specified name is valid. Middle name and last name are 
     * optional. First name can have 3 to 20 letters, middle and last name
     * can have 2 to 20 letters. Leading and trailing spaces are allowed. 
     *
     * @param name name of the employee to be validated as a string.
     * @return true if specified name is valid, otherwise false.
     */
    @Override
    public boolean isValidName(String name) {
        return Pattern.matches("^(\\s*[a-zA-Z]{3,20}\\s*)$|^((\\s*[a-zA-Z]"
                + "{3,20}) ([a-zA-Z]{2,20})\\s*)$|^((\\s*[a-zA-Z]{3,20}) "
                + "([a-zA-Z]{2,20}) ([a-zA-Z]){2,20}\\s*)$", name);
    }
    
    /**
     * Validates the specified employee name and converts it to lowercase.
     * 
     * @param name the employee name to be validated.
     * @return employee name as a string if it is valid else null.
     */
    @Override
    public String validateName(String name) {
        return isValidName(name) ? name.strip().toLowerCase() : null;
    }
    
    /**
     * Checks if the employee's age is above 18 (inclusive) and below 
     * 60 (inclusive).
     * 
     * @param dateOfBirth the employee's date of birth as a LocalDate.
     * @return true if specified date of birth is valid otherwise false.
     */
    @Override
    public boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        int age = dateOfBirth.until(LocalDate.now()).getYears();
        
        return ((18 <= age) && (60 >= age));
    }
    
    /**
     * Validates and parses given employee date of birth.
     * 
     * @param dateOfBirth the employee date of birth to be validated.
     * @return employee date of birth as a LocalDate if it is valid else null.
     */
    @Override
    public LocalDate validateDateOfBirth(String dateOfBirth) {
         LocalDate parsedDateOfBirth = parseDate(dateOfBirth.strip());

         return ((null != parsedDateOfBirth) 
                 && (isValidDateOfBirth(parsedDateOfBirth))) ? parsedDateOfBirth 
                                                             : null;
    }
    
    /**
     * Checks if the specified gender is valid(male/female/others).
     * 
     * @param gender employee's gender as string value.
     * @return true if specified gender is valid, otherwise false.
     */
    @Override 
    public boolean isValidGender(String gender) {
        return ("male".equals(gender) || "female".equals(gender) 
                || "others".equals(gender));
    }
    
    /**
     * Validates the specified employee gender and converts it to lowercase.
     * 
     * @param gender the employee gender to be validated.
     * @return employee gender as a string if it is valid else null.
     */
    @Override
    public String validateGender(String gender) {
        gender = gender.strip().toLowerCase();
        return isValidGender(gender) ? gender : null;
    }
    
    /**
     * Checks if the given mobile number is a 10 digit non negative integer
     * Starting digit must be in range 6 to 9. 
     *
     * @param mobileNumber the employee mobile number as string value.
     * @return true if specified mobile number is valid otherwise false.
     * 
     */
    @Override
    public boolean isValidMobileNumber(String mobileNumber) {
        return Pattern.matches("^(\\s*[6-9][0-9]{9}\\s*)$", mobileNumber);
    }
    
    /**
     * Validates and parses given employee mobile number.
     * 
     * @param mobileNumber the employee mobile number to be validated.
     * @return employee mobile number as a Long if it is valid else null.
     */
    @Override
    public Long validateMobileNumber(String mobileNumber) {
         Long parsedMobileNumber = null;
         
         if (isValidMobileNumber(mobileNumber)) {
             try {
                 parsedMobileNumber = Long.parseLong(mobileNumber.strip());
             } catch (NumberFormatException exception) {
                 parsedMobileNumber = null;
             }
         }
         return parsedMobileNumber;
    }
    
    /** 
     * Checks if the specified mobile number already exist in the database.
     *
     * @param the mobile number to be searched as a long.
     * @return true if mobile number already exist, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean isMobileNumberExist(long mobileNumber) throws SQLException {
        return employeeDAO.isMobileNumberExist(mobileNumber);
    }
    
    
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
    @Override
    public boolean isValidEmail(String email) {
        return Pattern.matches(new StringBuilder().append("^\\s*(([a-z0-9]")
                .append("[\\.]?[\\-]?[_]?([a-z0-9][\\.]?[\\-]?[_]?){0,50}")
                .append("[a-z0-9]@[a-z0-9][a-z0-9_\\-]{0,30}[a-z0-9]([\\.]")
                .append("[a-z]{2,30}){1,3})\\s*$)") 
                .toString(), email);
    }
    
    /**
     * Validates the given employee email.
     * 
     * @param email the employee email to be validated.
     * @return employee email as a string if it is valid else null.
     */
    @Override
    public String validateEmail(String email) {
         return isValidEmail(email) ? email.strip() : null;
    }
    
    /**
     * Checks if the specified email is already exist in the database.
     *
     * @param email employee's email to be searched as string value.
     * @return true if specified email is found, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    @Override 
    public boolean isEmailExist(String email) throws SQLException {
        return employeeDAO.isEmailExist(email);
    }
    
    /**
     * Checks if the specified salary is non negative and atleast 8,000.
     * Commas are not allowed. Allows one or two decimal points. (e.g) 8000.50,
     * 30000.6. Trailing and leading spaces are allowed.
     *
     * @param salary employee's salary to be validated, as string.
     * @return true if specified salary is valid, otherwise false.
     */
    @Override
    public boolean isValidSalary(String salary) {
        return Pattern.matches("^\\s*(([8-9][0-9]{3}|[1-9][0-9]{4,})(\\.[0-9]"
                               + "{1,2})?)\\s*$", salary);
    }
    
    /**
     * Validates and parses given employee salary.
     * 
     * @param salary the employee salary to be validated.
     * @return employee salary as a Float if it is valid else null.
     */
    @Override
    public Float validateSalary(String salary) {
         Float parsedSalary = null;
         
         if (isValidSalary(salary)) {
             try {
                 parsedSalary = Float.parseFloat(salary.strip());
             } catch (NumberFormatException exception) {
                 parsedSalary = null;
             }
         }
         return parsedSalary;
    }
    
    /**
     * Checks if the specified date is in dd-mm-yyyy format and parses the date.
     *
     * @param date the date as a string to be parsed .
     * @return the specified date as LocalDate if it is valid otherwise null.
     */
    private LocalDate parseDate(String date) {
        LocalDate parsedDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        try {
            parsedDate = LocalDate.parse(date, formatter);
        } catch (DateTimeParseException exception) {
            parsedDate = null;        
        }
        return parsedDate;
    }
    
    /**
     * Checks if the specified date of joining is valid. Future dates are
     * not allowed. it does not accept dates that are older than 43 years 
     * (excluded) from the present date.
     * 
     * @param dateOfJoining employee date of joining to be validated as a 
     *        LocalDate.
     * @return true if specified date is valid else false.
     */
    @Override
    public boolean isValidDateOfJoining(LocalDate dateOfJoining) {
        Period experience = calculateExperience(dateOfJoining);
        
        return ((43 > experience.getYears()) 
                && (!dateOfJoining.isAfter(LocalDate.now())));
    }
    
    /**
     * Validates and parses given employee date of joining.
     * 
     * @param dateOfJoining the employee date of joining to be validated.
     * @return employee date of joining as a LocalDate if it is valid else null.
     */
    @Override
    public LocalDate validateDateOfJoining(String dateOfJoining) {
        LocalDate parsedDateOfJoining = parseDate(dateOfJoining.strip());
        
        return ((null != parsedDateOfJoining) 
                && (isValidDateOfJoining(parsedDateOfJoining))) 
               ? parsedDateOfJoining : null;
    }
    
    /**
     * Calculates experience of the employee from date of joining.
     *
     * @param dateOfJoining employee's date of joining as LocalDate.
     * @return employee's experience as Period.
     */
    private Period calculateExperience(LocalDate dateOfJoining) {
        return dateOfJoining.until(LocalDate.now());
    }
    
    /**
     * Checks if the employee database is empty.
     * Helper function for view, update and delete operations.
     *
     * @return true if employee database is empty else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean isEmployeesDatabaseEmpty() throws SQLException {
        return employeeDAO.isDatabaseEmpty();
    }
    
    /**
     * Creates a new employee with specified details and stores in the database.
     *
     * @param employeeDTO the EmployeeDTO instance with employee details.
     * @return the employee's id as a int.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public int createEmployee (EmployeeDTO employeeDTO) throws SQLException {
        return employeeDAO.insertRecord(Mapper.toEmployee(employeeDTO));
    }
    
    /**
     * Maps list of Employee instances to EmployeeDTO instances.
     *
     * @param employees a List containing employees.
     * @return a List containing mapped EmployeeDTO instances.
     */
    private List<EmployeeDTO> toEmployeeDTO(List<Employee> employees) {
        List<EmployeeDTO> employeesDTO = new ArrayList<>(employees.size());
        
        for (Employee employee : employees) {
            employeesDTO.add(Mapper.toEmployeeDTO(employee));
        }
        return employeesDTO;
    }
    
    /**
     * Retrieves the specified employee from the database.
     * 
     * @param id the employee id to be retrieved as a int.
     * @return a List containing the specified employee.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public List<EmployeeDTO> getEmployee(int id) throws SQLException {
        List<Employee> employees = employeeDAO.selectRecord(id);
        
        return toEmployeeDTO(employees);
    }
    
    /** 
     * Retrieves all employees from the database.
     *
     * @return a List containing all employees.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() throws SQLException {
        List<Employee> employees = employeeDAO.selectAllRecord();
        
        return toEmployeeDTO(employees);
    }
   
    /**
     * Updates specified employee's name and stores in the database.
     *
     * @param id employee id to be updated as a int.
     * @param name the employee's new name to update.
     * @return true if employee name updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean updateName(int id, String name) throws SQLException {
         return (1 == employeeDAO.updateName(id, name));
    }
   
    /**
     * Updates specified employee's date of birth and stores in the database.
     *
     * @param id employee id to be updated.
     * @param dateOfBirth the employee's new date of birth to update.
     * @return true if employee date of birth updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean updateDateOfBirth(int id, LocalDate dateOfBirth) 
            throws SQLException {
         return (1 == employeeDAO.updateDateOfBirth(id, dateOfBirth));
    }
   
    /**
     * Updates specified employee's gender and stores in the database.
     *
     * @param id employee id to be updated.
     * @param gender the employee's gender to update.
     * @return true if employee gender updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean updateGender(int id, String gender) throws SQLException {
         return (1 == employeeDAO.updateGender(id, gender));
    }
   
    /**
     * Updates specified employee's mobile number and stores in the database.
     *
     * @param id employee id to be updated.
     * @param mobileNumber the employee's new mobile number to update.
     * @return true if employee mobile number updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean updateMobileNumber(int id, long mobileNumber) 
            throws SQLException { 
         return (1 == employeeDAO.updateMobileNumber(id, mobileNumber));
    }
   
    /**
     * Updates specified employee's email and stores in the database.
     *
     * @param id employee id to be updated.
     * @param email the employee's new email to update.
     * @return true if employee email updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean updateEmail(int id, String email) throws SQLException {
         return (1 == employeeDAO.updateEmail(id, email));
    }
   
    /**
     * Updates specified employee's salary and stores in the database.
     *
     * @param id employee id to be updated.
     * @param salary the employee's new salary to update.
     * @return true if employee salary updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean updateSalary(int id, float salary) throws SQLException {
         return (1 == employeeDAO.updateSalary(id, salary));
    }
   
    /**
     * Updates specified employee's date of joining and stores in the database.
     *
     * @param id employee id to be updated.
     * @param dateOfJoining the employee's new date of joining to update.
     * @return true if employee date of joining updated successfully else false.
     * @exception SQLException if a database access error occurs.
     */
   @Override
   public boolean updateDateOfJoining(int id, LocalDate dateOfJoining) 
           throws SQLException { 
         return (1 == employeeDAO.updateDateOfJoining(id, dateOfJoining));
    }
   
    /** 
     * Updates all details of the specified employee and stores in the database.
     *
     * @param employeeDTO the EmployeeDTO instance with employee details.
     * @return true if employee updated successfully otherwise false.
     * @exception SQLException if a database access error occurs.
     */
     @Override
     public boolean updateAllDetails(EmployeeDTO employeeDTO)
            throws SQLException {
         return (1 == employeeDAO.updateAllColumn(Mapper.toEmployee(
                            employeeDTO)));
    }
            
    /**
     * Deletes the specified employee.
     *
     * @param id employee id to be deleted.
     * @return true if employee deleted successfully else false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean deleteEmployee(int id) throws SQLException {
         return (1 == employeeDAO.deleteRecord(id));
    }
   
    /** 
     * Deletes all employees from the database. 
     *
     * @return true if deleted successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    @Override
    public boolean deleteAllEmployee() throws SQLException {
         return employeeDAO.deleteAllRecord();  
    }
}
