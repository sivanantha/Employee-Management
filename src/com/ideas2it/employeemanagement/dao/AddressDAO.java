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
}
