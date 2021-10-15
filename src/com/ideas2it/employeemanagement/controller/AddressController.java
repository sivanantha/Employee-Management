/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.service.AddressService;
import com.ideas2it.employeemanagement.service.impl.AddressServiceImpl;

/**
 * The AddressController class contains CRUD implementations for employee 
 * management system. It has useful methods for create, update, retrieve, 
 * delete and validate employee address details.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public class AddressController {
    private AddressService addressService = new AddressServiceImpl();
    
    /**
     * Validates the specified door number.
     *
     * @param doorNumber the door number to be validated.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidDoorNumber(String doorNumber) {
        return addressService.isValidDoorNumber(doorNumber);
    }
    
    /**
     * Validates the specified street name.
     * 
     * @param street the street name to be validated.
     * @return true if it is valid, otherwise false.
     */  
    public boolean isValidStreet(String street) {
        return addressService.isValidStreet(street);
    }
    
    /**
     * Validates the specified locality/city/state/country name.
     *
     * @param locality the name of the locality/city/state/country to be 
     *        validated.
     * @return true if valid, otherwise false.
     */
    public boolean isValidPlaceName(String name) {
        return addressService.isValidPlaceName(name);
    }
    
    /**
     * Validates the specified pin code.
     *
     * @param pinCode the pin code to be validated as a string.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidPinCode(String pinCode) {
        return addressService.isValidPinCode(pinCode);
    }
    
    /**
     * Creates a new specified addresses.
     *
     * @param addresses the list of addresses to create.
     * @return true if addresses created successfully, otherwise false.
     */ 
    public boolean createAddress(List<AddressDTO> addresses) 
            throws SQLException {
        return addressService.createAddress(addresses);
    }
    
    /**
     * Fetches the specified employee's addresses.
     *
     * @param employeeId the employee's id to fetch the addresses.
     * @return a List of specified employee's addresses.
     */
    public List<AddressDTO> getAddresses(int employeeId) throws SQLException {
        return addressService.getAddresses(employeeId);
    }
    
    /**
     * Fetches all employee's addresses.
     * 
     * @return a Map containing List of addresses with employee id as the key.
     */ 
    public Map<Integer, List<AddressDTO>> getAllAddresses() throws 
            SQLException {
        return addressService.getAllAddresses();
    }
}
