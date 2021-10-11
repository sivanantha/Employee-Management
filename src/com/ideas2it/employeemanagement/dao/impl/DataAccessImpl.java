/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.connection_utils.DatabaseConnection;
import com.ideas2it.employeemanagement.dao.DataAccess;
import com.ideas2it.employeemanagement.model.Employee;

/**
 * This class provides methods for create, update, view, delete employee  
 * records in database.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public class DataAccessImpl implements DataAccess {
    
    /**
     * Checks if the database is empty.
     *
     * @return true if database is empty, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean isDatabaseEmpty() throws SQLException {
        boolean isEmpty;
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        
        ResultSet resultSet = statement.executeQuery("SELECT NOT EXISTS(SELECT"
                                      + " 1 FROM employee)");
        resultSet.next();
        isEmpty = resultSet.getBoolean(1);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return isEmpty;
    }
    
    /**
     * Checks if the specified employee id is exist.
     *
     * @return true if found, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean isEmployeeIdExist(int id) throws SQLException {
        boolean isEmployeeFound;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT EXISTS(SELECT 1 FROM employee WHERE id = ?)");
        
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        resultSet.next();
        isEmployeeFound = resultSet.getBoolean(1);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return isEmployeeFound;
    }
    
    /**
     * Checks if the specified mobile number already exist.
     *
     * @param mobileNumber the mobile number to be searched as a long.
     * @return true if found, else false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean isMobileNumberExist(long mobileNumber) throws SQLException {
        boolean isMobileNumberFound;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT "
                + "EXISTS (SELECT 1 FROM employee WHERE mobile_number = ?)");
        
        statement.setLong(1, mobileNumber);
        resultSet = statement.executeQuery();
        resultSet.next();
        isMobileNumberFound = resultSet.getBoolean(1);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return isMobileNumberFound;
    }
    
    /**
     * Checks if the specified email already exist.
     *
     * @param email the email address to be searched as a string.
     * @return true if found, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean isEmailExist(String email) throws SQLException {
        boolean isEmailFound;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT "
                + "EXISTS (SELECT 1 FROM employee WHERE email = ?)");
        
        statement.setString(1, email);
        resultSet = statement.executeQuery();
        resultSet.next();
        isEmailFound = resultSet.getBoolean(1);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return isEmailFound;
    }
    
    /**
     * Creates a employee record in the database.
     *
     * @param employee the employee to be inserted as a Employee object. 
     * @return the employee id as a long.
     * @exception SQLException if a database access error occurs.
     */
    public int insertRecord(Employee employee) throws SQLException {
        int employeeId;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        String sql = "INSERT INTO employee VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, 
                                      Statement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, employee.getName());
        statement.setDate(2, Date.valueOf(employee.getDateOfBirth()));
        statement.setString(3, employee.getGender());
        statement.setLong(4, employee.getMobileNumber());
        statement.setString(5, employee.getEmail());
        statement.setFloat(6, employee.getSalary());
        statement.setDate(7, Date.valueOf(employee.getDateOfJoining()));
        
        statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        resultSet.next();
        employeeId = resultSet.getInt(1);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return employeeId;
    }
    
    /**
     * Retrieves data from the resultset and creates a employee object and 
     * stores in a list.
     *
     * @param resultSet a ResultSet object containing employee details.
     * @return a list containing employee details.
     * @exception SQLException if a database access error occurs.
     */
    private List<Employee> fillEmployeesList(ResultSet resultSet) 
            throws SQLException {
        Employee employee;
        List<Employee> employees = new ArrayList<Employee>();
        
        while (resultSet.next()) {
            employee = new Employee(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getDate(3).toLocalDate(),
                                    resultSet.getString(4),
                                    resultSet.getLong(5),
                                    resultSet.getString(6),
                                    resultSet.getFloat(7),
                                    resultSet.getDate(8).toLocalDate());
            employees.add(employee);
        }
        return employees;
    }
    
    /** 
     * Fetches the specified employee's record.
     *
     * @param id the employee's id as a integer to fetch the record. 
     * @return a List containing the specified employee.
     * @exception SQLException if a database access error occurs.
     */
    public List<Employee> selectRecord(int id) throws SQLException {
        List<Employee> employees;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setInt(1, id);
        resultSet = statement.executeQuery();
        employees = fillEmployeesList(resultSet);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return employees;
    }
    
    /** 
     * Fetches all employees record.
     * 
     * @return a List containing all employees.
     * @exception SQLException if a database access error occurs.
     */
    public List<Employee> selectAllRecord() throws SQLException {
        List<Employee> employees;
        ResultSet resultSet;
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        
        resultSet = statement.executeQuery("SELECT * FROM employee");
        employees = fillEmployeesList(resultSet);
        
        resultSet.close();
        statement.close();
        DatabaseConnection.closeConnection();
        
        return employees;
    }
    
    /**
     * Updates specified employee's name.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee name as a string to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateName(int id, String name) throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET name = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, name);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Updates specified employee's date of birth.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee name as a LocalDate to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateDateOfBirth(int id, LocalDate dateOfBirth) 
            throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET date_of_birth = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setDate(1, Date.valueOf(dateOfBirth));
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Updates specified employee's gender.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee gender as a string to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateGender(int id, String gender) throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET gender = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, gender);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Updates specified employee's mobile number.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee mobile number as a long to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateMobileNumber(int id, long mobileNumber) 
            throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET mobile_number = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setLong(1, mobileNumber);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Updates specified employee's email.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee email as a string to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateEmail(int id, String email) throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET email = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, email);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Updates specified employee's salary.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee salary as a float to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateSalary(int id, float salary) throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET salary = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setFloat(1, salary);
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Updates specified employee's date of joining.
     * 
     * @param id the employee's id as a integer.
     * @param name the employee date of joining as a LocalDate to update.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateDateOfJoining(int id, LocalDate dateOfJoining) 
            throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET date_of_joining = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setDate(1, Date.valueOf(dateOfJoining));
        statement.setInt(2, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Updates specified employee's all details.
     * 
     * @param employee the employee to be updated as a Employee object.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int updateAllColumn(Employee employee) throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "UPDATE employee SET name = ?, date_of_birth = ?, "
                       + "gender = ?, mobile_number = ?, email = ?, salary ="
                       + "?, date_of_joining = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setString(1, employee.getName());
        statement.setDate(2, Date.valueOf(employee.getDateOfBirth()));
        statement.setString(3, employee.getGender());
        statement.setLong(4, employee.getMobileNumber());
        statement.setString(5, employee.getEmail());
        statement.setFloat(6, employee.getSalary());
        statement.setDate(7, Date.valueOf(employee.getDateOfJoining()));
        statement.setInt(8, employee.getId());
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /** 
     * Deletes specified employee.
     *
     * @param the employee's id as a int to be deleted.
     * @return the number of rows affected.
     * @exception SQLException if a database access error occurs.
     */
    public int deleteRecord(int id) throws SQLException {
        int rowsAffected;
        Connection connection = DatabaseConnection.getConnection();
        String query = "DELETE FROM employee WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        
        statement.setInt(1, id);
        rowsAffected = statement.executeUpdate();
        
        statement.close();
        DatabaseConnection.closeConnection();
        
        return rowsAffected;
    }
    
    /**
     * Deletes all employees in the database.
     * 
     * @return true if deleted successfully, otherwise false.
     * @exception SQLException if a database access error occurs.
     */
    public boolean deleteAllRecord() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        
        statement.execute("TRUNCATE TABLE employee");
        DatabaseConnection.closeConnection();
        return isDatabaseEmpty();
    }   
}
