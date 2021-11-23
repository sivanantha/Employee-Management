/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains constants and error message for corresponding errors
 * for employee management system.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public final class Constants {
    public static final String ERROR_001 = "001";
    public static final String ERROR_002 = "002";
    public static final String ERROR_003 = "003";
    public static final String ERROR_004 = "004";
    public static final String ERROR_005 = "005";
    public static final String ERROR_006 = "006";
    public static final String ERROR_007 = "007";
    public static final String ERROR_008 = "008";
    public static final String ERROR_009 = "009";
    public static final String ERROR_010 = "010";
    public static final String ERROR_011 = "011";
    public static final String ERROR_012 = "012";
    public static final String ERROR_013 = "013";
    public static final String ERROR_014 = "014";
    public static final String ERROR_015 = "015";
    public static final String ERROR_016 = "016";
    public static final String ERROR_017 = "017";
    public static final String ERROR_018 = "018";
    public static final Map<String, String> errorCodes = getErrors();
    
    /**
     * Returns a map containing error codes and messages.
     *
     * @return a map containing error codes and messages.
     */
    private static Map<String, String> getErrors() {
        Map<String, String> errors = new HashMap<>();
        
        errors.put(ERROR_001, "Unable To Check For Mobile Number Existence");
        errors.put(ERROR_002, "Unable To Check For Email Address Existence");
        errors.put(ERROR_003, "Unable To Create Employee");
        errors.put(ERROR_004, "Unable To Create Address");
        errors.put(ERROR_005, "Unable To Access Employee");
        errors.put(ERROR_006, "Unable To Access Employees");
        errors.put(ERROR_007, "Unable To Update Employee");
        errors.put(ERROR_008, "Unable To Update Address");
        errors.put(ERROR_009, "Unable To Delete Employee");
        errors.put(ERROR_010, "Unable To Delete Address");
        errors.put(ERROR_011, "Unable To Delete All Employees");
        errors.put(ERROR_012, "Unable To Access Projects");
        errors.put(ERROR_013, "Unable To Create Project");
        errors.put(ERROR_014, "Unable To Access Project");
        errors.put(ERROR_015, "Unable To Update Project");
        errors.put(ERROR_016, "Unable To Delete Project");
        errors.put(ERROR_017, "Unable To Delete All Projects");
        errors.put(ERROR_018, "Unable To Get Session Factory");
        return errors;
    }
}

