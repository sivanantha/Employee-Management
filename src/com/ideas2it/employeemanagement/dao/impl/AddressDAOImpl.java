/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.connection_utils.DatabaseConnection;
import com.ideas2it.employeemanagement.dao.AddressDAO;
import com.ideas2it.employeemanagement.model.Address;

/**
 * This class provides methods for create, update, view, delete address  
 * details in database.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public class AddressDAOImpl implements AddressDAO {
    
    /**
     * 
     * {@inhertiDoc}
     * 
     */
    @Override
    public int insertRecord(List<Address> addresses) throws SQLException {
        int rowsAffected = 0;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO address VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        
        for (Address address : addresses) {
            statement.setString(1, address.getDoorNumber());
            statement.setString(2, address.getStreet());
            statement.setString(3, address.getLocality());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getState());
            statement.setString(6, address.getCountry());
            statement.setString(7, address.getPinCode());
            statement.setInt(8, address.getEmployeeId());
            rowsAffected += statement.executeUpdate();
        }
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Retrieves data from the resultset and creates a address object and 
     * stores in a list.
     *
     * @param resultSet a ResultSet object containing address details.
     * @return a list containing address details.
     * @exception SQLException if a database access error occurs.
     */
    private List<Address> fillAddressesList(ResultSet resultSet) 
            throws SQLException {
        Address address;
        List<Address> addresses = new ArrayList<>();
        
        while (resultSet.next()) {
            address = new Address(resultSet.getInt(1),
                                  resultSet.getString(2),
                                  resultSet.getString(3),
                                  resultSet.getString(4),
                                  resultSet.getString(5),
                                  resultSet.getString(6),
                                  resultSet.getString(7),
                                  resultSet.getString(8),
                                  resultSet.getInt(9));
            addresses.add(address);
        }
        return addresses;
    }
    
    /**
     * 
     * {@inhertiDoc}
     * 
     */
    @Override
    public List<Address> selectRecord(int employeeId) throws SQLException {
        List<Address> employees;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM address WHERE employee_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setInt(1, employeeId);
        resultSet = statement.executeQuery();
        employees = fillAddressesList(resultSet);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return employees;
    }
    
    /**
     * 
     * {@inhertiDoc}
     * 
     */
    @Override
    public List<Address> selectAllRecord() throws SQLException {
        List<Address> addresses;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        
        resultSet = statement.executeQuery("SELECT * FROM address");
        addresses = fillAddressesList(resultSet);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return addresses;
    }
}
