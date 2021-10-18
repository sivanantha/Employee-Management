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
    
    /**
     * Updates the specified address door number.
     *
     * @param id the id of the address to be updated.
     * @param doorNumber the door number as a string.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateDoorNumber(int id, String doorNumber) throws 
            SQLException {
        return addressService.updateDoorNumber(id, doorNumber);   
    }
    
    /**
     * Updates the specified address street.
     * 
     * @param id the id of the address to be updated.
     * @param street the street name as a string.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateStreet(int id, String street) throws SQLException {
        return addressService.updateStreet(id, street);
    }
    
    /**
     * Updates the specified address locality.
     *
     * @param id the id of the address to be updated.
     * @param locality the locality name as a string.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateLocality(int id, String locality) throws SQLException {
        return addressService.updateLocality(id, locality);
    }
    
    /**
     * Updates the specified address city.
     *
     * @param id the id of the address to be updated.
     * @param city the city name as a string.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateCity(int id, String city) throws SQLException {
        return addressService.updateCity(id, city);
    }
    
    /**
     * Updates the specified address state.
     *
     * @param id the id of the address to be updated.
     * @param state the state name as a string.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateState(int id, String state) throws SQLException {
        return addressService.updateState(id, state);
    }
    
    /**
     * Updates the specified address country.
     *
     * @param id the id of the address to be updated.
     * @param country the country name as a string.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updateCountry(int id, String country) throws SQLException {
        return addressService.updateCountry(id, country);
    }
    
    /**
     * Updates the specified address postal code.
     *
     * @param id the id of the address to be updated.
     * @param pinCode the postal code as a string.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean updatePinCode(int id, String pinCode) throws SQLException {
        return addressService.updatePinCode(id, pinCode);
    }
    
    /**
     * Updates the specified address's all details.
     *
     * @param id the id of the address to be updated.
     * @param address the AddressDTO instance containing address details.
     * @return true if updated successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
     public boolean updateAllDetails(AddressDTO addressDTO) throws
            SQLException {
        return addressService.updateAllDetails(addressDTO);
     }
     
     /**
      * Deletes the specified address.
      *
      * @param id the id of the address to be updated.
      * @return true if deleted successfully, otherwise false.
      * @exception SQLException if a database access error occurs.
      */
     public boolean deleteAddress(int id) throws SQLException {
        return addressService.deleteAddress(id);
     }
     
     /**
      * Deletes all addresses.
      *
      * @return true if deleted successfully, otherwise false.
      * @exception SQLException if a database access error occurs.
      */
     public boolean deleteAllAddress() throws SQLException {
        return addressService.deleteAllAddress();
     }
}
