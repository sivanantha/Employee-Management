/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.model.AddressDTO;

/**
 * The AddressService interface provides methods for create, update, view, 
 * delete, validate address details.  
 * The address has the following details: 
 * door number, street name, locality, city, state, country, postal code. 
 *
 * @author  Sivanantham
 * @version 1.0
 */
public interface AddressService {
 
    /**
     * Validates the specified door number.
     * Door number starts with 0 is not allowed. door number can have maximum
     * of 5 digits, can contain single capital letter with or without '-' hyphen
     * single '/' is allowed. Leading trailing spaces are accepted.
     * (e.g) 12, 12B, C1, A-9, 3-F, 1/30, B12/2, 10C/23, D-30/5, 4-F/224.
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
     * Creates a new addresses.
     *
     * @param addressDTO a List containing addresses to create.
     * @return true if given addresses created successfully, else false.
     */
    boolean createAddress(List<AddressDTO> addressesDTO) throws SQLException;
    
    /**
     * Gets specified employee's addresses.
     * 
     * @param employeeId the employee's id whose address to be fetched.
     * @return a List of addresses of a specified employee.
     * @exception SQLException if a database access error occurs.
     */
    List<AddressDTO> getAddresses(int employeeId) throws SQLException;
    
    /**
     * Fetches all employees addresses.
     *
     * @return a Map containing addresses with employee id as key.
     * @exception SQLException if a database access error occurs.
     */
    Map<Integer, List<AddressDTO>> getAllAddresses() throws SQLException;
}
