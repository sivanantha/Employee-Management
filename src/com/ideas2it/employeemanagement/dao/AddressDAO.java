/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao;

import java.sql.SQLException;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;

/**
 * This interface provides methods for create, update, view, delete address  
 * details in database.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public interface AddressDAO {
    
    /**
     * Creates a address record in the database.
     *
     * @param addresses a List containing addresses to be inserted. 
     * @return the number of rows affected as a int.
     * @exception SQLException if a database access error occurs.
     */
    int insertRecord(List<Address> addresses) throws SQLException;
    
    /**
     * Fetches the specified employee's addresses.
     *
     * @param employeeId the employee's id to fetch the addresses.
     * @return a List of specified employee's addresses.
     * @exception SQLException if a database access error occurs.
     */
    List<Address> selectRecord(int employeeId) throws SQLException;
    
    /**
     * Fetches all employee's addresses.
     * 
     * @return a List containing all employee's addresses.
     * @exception SQLException if a database access error occurs.
     */
    List<Address> selectAllRecord() throws SQLException;
    
    /**
     * Updates the specified address door number.
     *
     * @param id the id of the address to be updated.
     * @param doorNumber the door number as a string.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateDoorNumber(int id, String doorNumber) throws SQLException;
    
    /**
     * Updates the specified address street.
     * 
     * @param id the id of the address to be updated.
     * @param street the street name as a string.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateStreet(int id, String street) throws SQLException;
    
    /**
     * Updates the specified address locality.
     *
     * @param id the id of the address to be updated.
     * @param locality the locality name as a string.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateLocality(int id, String locality) throws SQLException;
    
    /**
     * Updates the specified address city.
     *
     * @param id the id of the address to be updated.
     * @param city the city name as a string.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateCity(int id, String city) throws SQLException;
    
    /**
     * Updates the specified address state.
     *
     * @param id the id of the address to be updated.
     * @param state the state name as a string.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateState(int id, String state) throws SQLException;
    
    /**
     * Updates the specified address country.
     *
     * @param id the id of the address to be updated.
     * @param country the country name as a string.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateCountry(int id, String country) throws SQLException;
    
    /**
     * Updates the specified address postal code.
     *
     * @param id the id of the address to be updated.
     * @param pinCode the postal code as a string.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updatePinCode(int id, String pinCode) throws SQLException;
    
    /**
     * Updates the specified address's all details.
     *
     * @param id the id of the address to be updated.
     * @param address the Address instance containing address details.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int updateAllColumn(Address address) throws SQLException;
    
    /**
     * Deletes the specified address.
     *
     * @param id the id of the address to be deleted.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int deleteRecord(int id) throws SQLException;
    
    /**
     * Deletes all addresses.
     *
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    int deleteAllRecord() throws SQLException;
}
