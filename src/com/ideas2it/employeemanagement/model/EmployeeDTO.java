/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a data transfer object for employee details.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class EmployeeDTO {
    private int id;
    private float salary;
    private long mobileNumber;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String email;
    private String gender;
    private String name;
    private List<AddressDTO> addresses;
    
    /** Initializes all fields to default values. */
    public EmployeeDTO() {
    
    }
    
    /**
     * Initializes fields with specified values.
     *
     * @param id a integer value which represents employee id
     * @param name the name of the employee
     * @param gender the gender of the employee
     * @param dateOfBirth the date of birth of the employee
     * @param mobileNumber the mobile number of the employee
     * @param email the email address of the employee
     * @param salary the salary of the employee
     * @param dateOfJoining the employee's date of joining
     * @param addresses the List of addresses of the employee
     */
    public EmployeeDTO(int id, String name, LocalDate dateOfBirth, 
            String gender, long mobileNumber, String email, float salary,
            LocalDate dateOfJoining, List<AddressDTO> addresses) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.salary = salary;
        this.dateOfJoining = dateOfJoining;
        this.addresses = addresses;
    }
    
    /**
     * Gets the employee id
     *
     * @return the id of the employee
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Assigns the specified integer to the id field.
     *
     * @param id a integer value which represent employee id
     */
    public void setId(int id) {
        this.id = id;     
    }
    
    /**
     * Gets the employee name.
     *
     * @return a string representing the employee name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Assigns the specified string to the name field.
     *
     * @param name a string value which represents employee name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the employee gender
     *
     * @return a string representing the employee gender
     */
    public String getGender() {
        return this.gender;
    }
    
    /** 
     * Assigns the specified string to the gender field.
     *
     * @param gender a string value representing employee gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Gets the employee date of birth.
     *
     * @return a LocalDate type representing employee date of birth
     */
    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    /** 
     * Assigns the specified date to the dateOfBirth field.
     *
     * @param dateOfBirth a LocalDate representing employee date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    /**
     * Gets the employee mobile number.
     *
     * @return a long value representing employee mobile number
     */
    public long getMobileNumber() {
        return this.mobileNumber;
    }
    
    /** 
     * Assigns the specified string to the mobileNumber field.
     *
     * @param mobileNumber a long value representing employee mobile number
     */
    public void setMobileNumber (long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    /**
     * Gets the employee email.
     *
     * @return a string value representing employee email
     */
    public String getEmail() {
        return this.email;
    }
    
    /** 
     * Assigns the specified string value to the email field.
     *
     * @param email a string value representing employee email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Gets the employee salary
     *
     * @return a string value representing employee salary
     */
    public float getSalary() {
        return this.salary;
    }
    
    /** 
     * Assigns the specified float value to the salary field.
     *
     * @param salary a float value representing employee salary
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }
    
    /**
     * Gets the employee date of joining.
     *
     * @return a LocalDate type representing employee date of joining
     */
    public LocalDate getDateOfJoining() {
        return this.dateOfJoining;
    }
    
    /** 
     * Assigns the specified date to the dateOfJoining field.
     *
     * @param dateOfJoining a LocalDate value representing employee date of 
     *        joining
     */
    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    
    /**
     * Gets the employee date of joining.
     *
     * @return a List containing addresses of employee
     */
    public List<AddressDTO> getAddresses() {
        return addresses;
    }
    
    /** 
     * Assigns the specified date to the dateOfJoining field.
     *
     * @param addresses the List of addresses of the employee
     */
    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * Provides this object in human readable form.
     *
     * @return a string which is description of the object
     */ 
    @Override 
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder addressTexts = new StringBuilder(150);
        
        for (AddressDTO address : addresses) {
            addressTexts.append(address.toString());
        }
        return new StringBuilder(300).append("\n\t\t Employee Id     : ")
                                     .append(id)
                                     .append("\n\n\t\t Name            : ")
                                     .append(name)
                                     .append("\n\n\t\t Date of birth   : ")
                                     .append(dateOfBirth.format(formatter))
                                     .append("\n\n\t\t Gender          : ")
                                     .append(gender)
                                     .append("\n\n\t\t Mobile Number   : ")
                                     .append(mobileNumber)
                                     .append("\n\n\t\t Email           : ")
                                     .append(email)
                                     .append("\n\n\t\t Salary          : ")
                                     .append(String.format("%.2f", salary))
                                     .append("\n\n\t\t Date Of joining : ")
                                     .append(dateOfJoining.format(formatter))
                                     .append(addressTexts)
                                     .append("\n")
                                     .toString();
    }      
}
