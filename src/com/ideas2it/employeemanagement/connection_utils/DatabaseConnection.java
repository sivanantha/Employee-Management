/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.connection_utils;

import java.sql.DriverManager;
import java.sql.Connection;

/** 
 * This class contains utility method for establish connection to database.
 */
public class DatabaseConnection {
    private Connection databaseConnection = null;
    
    /** 
     * Establishes connection to the database and returns the 
     * connection object. It establish only single connection per instance.
     *
     * @return a database connection.
     */
    public Connection getConnection() {
         if (databaseConnection == null) {
             try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 databaseConnection = DriverManager.getConnection("jdbc:mysql:"
                         + "//localhost:3306/employee_management","root", 
                         "9159");
             } catch (Exception exception) {
                 exception.printStackTrace();
             }
         }
         return databaseConnection;
    }
}
