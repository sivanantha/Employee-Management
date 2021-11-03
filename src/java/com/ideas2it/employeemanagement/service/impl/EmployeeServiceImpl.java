/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service.impl;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.dao.EmployeeDAO;
import com.ideas2it.employeemanagement.dao.impl.EmployeeDAOImpl;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.utils.Mapper;
import com.ideas2it.employeemanagement.utils.ValidationUtil;

/**
 * The EmployeeServiceImpl class contains validations and implementations for 
 * create, update, retrieve, delete operations for employee management system.
 *
 * @author  Sivanantham
 * @version 1.6
 */
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isEmployeeExist(int id) throws HibernateException {
        return (null == employeeDAO.getById(id)) ? false : true;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Integer validateId(String id) {
        Integer parsedId = null;
        
        if (ValidationUtil.isValidId(id)) {
            try {
                parsedId = Integer.parseInt(id.strip());          
            } catch (NumberFormatException exception) {
                parsedId = null;     
            }
        }
        return parsedId;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public String validateName(String name) {
        return ValidationUtil.isValidName(name) ? name.strip().toLowerCase() 
                                                : null;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidDateOfBirth(LocalDate dateOfBirth) {
        int age = dateOfBirth.until(LocalDate.now()).getYears();
        
        return ((18 <= age) && (60 >= age));
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public LocalDate validateDateOfBirth(String dateOfBirth) {
         LocalDate parsedDateOfBirth = parseDate(dateOfBirth.strip());

         return ((null != parsedDateOfBirth) 
                 && (isValidDateOfBirth(parsedDateOfBirth))) ? parsedDateOfBirth 
                                                             : null;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override 
    public boolean isValidGender(String gender) {
        return ("male".equals(gender) || "female".equals(gender) 
                || "others".equals(gender));
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public String validateGender(String gender) {
        gender = gender.strip().toLowerCase();
        return isValidGender(gender) ? gender : null;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidMobileNumber(String mobileNumber) {
        return Pattern.matches("^(\\s*[6-9][0-9]{9}\\s*)$", mobileNumber);
    }
    
    /**
     * {@inheritDoc}
     * 
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
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isMobileNumberExist(long mobileNumber) throws 
            HibernateException {
        return (null == employeeDAO.getByMobileNumber(mobileNumber)) ? false 
                                                                     : true;
    }
    
    
    /**
     * {@inheritDoc}
     * 
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
     * {@inheritDoc}
     * 
     */
    @Override
    public String validateEmail(String email) {
         return isValidEmail(email) ? email.strip() : null;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override 
    public boolean isEmailExist(String email) throws HibernateException {
        return (null == employeeDAO.getByEmail(email)) ? false : true;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidSalary(String salary) {
        return Pattern.matches("^\\s*(([8-9][0-9]{3}|[1-9][0-9]{4,})(\\.[0-9]"
                               + "{1,2})?)\\s*$", salary);
    }
    
    /**
     * {@inheritDoc}
     * 
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
     * {@inheritDoc}
     * 
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
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidDateOfJoining(LocalDate dateOfJoining) {
        Period experience = calculateExperience(dateOfJoining);
        
        return ((43 > experience.getYears()) 
                && (!dateOfJoining.isAfter(LocalDate.now())));
    }
    
    /**
     * {@inheritDoc}
     * 
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
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidDoorNumber(String doorNumber) {
        return Pattern.matches("^[\\s]*([1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/]"
                       + "[1-9][0-9]{0,4}[A-Z]?|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])"
                       + "?|([A-Z]|[A-Z][-])?[1-9][0-9]{0,4}|([A-Z]|[A-Z][-])"
                       + "[1-9][0-9]{0,4}[/][A-Z]?[1-9][0-9]{0,4}|[1-9][0-9]"
                       + "{0,4}([-][A-Z]|[A-Z])?[/][A-Z]?[1-9][0-9]{0,4})[\\s]*"
                       + "$", doorNumber);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidStreet(String street) {
        return Pattern.matches("^[\\s]*(([1-9][0-9]{0,4})?([ ]?[A-Za-z][\\.]?|"
                + "[A-Za-z][ ]?){4,50}([1-9][0-9]{0,4})?)[\\s]*$", street);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidPlaceName(String name) {
        return Pattern.matches("^[\\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2})"
                               + "{1,2})[\\s]*$", name);
    }
   
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidPinCode(String pinCode) {
        return Pattern.matches("^[\\s]*([0-9]{3,9})[\\s]*$", pinCode);
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isEmployeesDatabaseEmpty() throws HibernateException {
        return (0 == employeeDAO.getEmployeeCount());
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int createEmployee (EmployeeDTO employeeDTO) throws
            HibernateException {
        return employeeDAO.insertEmployee(Mapper.toEmployee(employeeDTO));
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean createAddresses(EmployeeDTO employeeDTO) throws 
            HibernateException {
        return employeeDAO.insertAddresses(Mapper.toEmployee(employeeDTO));
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
     * {@inheritDoc}
     * 
     */
    @Override
    public EmployeeDTO getEmployee(int id) throws HibernateException {
        Employee employee = employeeDAO.getById(id);
        
        return (null == employee) ? null : Mapper.toEmployeeDTO(employee);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public List<EmployeeDTO> getAllEmployees() throws HibernateException {
        List<Employee> employees = employeeDAO.getAllEmployees();
        
        return toEmployeeDTO(employees);
    }
   
    /**
     * {@inheritDoc}
     * 
     */
     @Override
     public boolean updateEmployee(EmployeeDTO employeeDTO) throws 
            HibernateException {
         return (null != employeeDAO.updateEmployee(Mapper.toEmployee(
                            employeeDTO)));
    }
    
    /**
     * {@inheritDoc}
     * 
     */
     @Override
     public boolean updateAddress(AddressDTO addressDTO) throws 
            HibernateException {
         return (null != employeeDAO.updateAddress(Mapper.toAddress(
                            addressDTO)));
    }
    
    /**
     * {@inheriDoc}
     *
     */
    @Override
    public List<ProjectDTO> getAllProjects() throws HibernateException {
        return new ProjectServiceImpl().getAllProjects();
    }
            
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteEmployee(int id) throws HibernateException {
         return employeeDAO.deleteEmployee(id);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteAddress(int addressId) throws HibernateException {
         return employeeDAO.deleteAddress(addressId);
    }
   
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean deleteAllEmployees() throws HibernateException {
         return employeeDAO.deleteAllEmployees();  
    }
}
