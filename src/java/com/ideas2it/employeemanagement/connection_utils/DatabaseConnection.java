/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.connection_utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** 
 * This class contains utility method for establish connection to database.
 */
public class DatabaseConnection {
    private static Connection databaseConnection = null;
    
    /** 
     * Establishes connection to the database and returns the 
     * connection object. It establishes only single connection per thread.
     *
     * @return a database connection.
     * @exception SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
         if (databaseConnection == null) {
             databaseConnection = DriverManager.getConnection("jdbc:mysql://"
                    + "localhost:3306/employee_management", "root", "9159");
         }
         return databaseConnection;
    }
    
    /** 
     * Closes the database connection.
     *
     * @exception SQLException if a database access error occurs.
     */
    public static void closeConnection() throws SQLException {
        if (databaseConnection != null) {
            databaseConnection.close();
            databaseConnection = null;
        }
    }
}
