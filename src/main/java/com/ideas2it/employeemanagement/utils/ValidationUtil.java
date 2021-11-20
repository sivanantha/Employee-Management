/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.utils;

import java.util.regex.Pattern;

/**
 * This class contains validation utility methods.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class ValidationUtil {
    
    /**
     * Checks if the specified id is a non negative integer
     * (0 excluded). Allows leading and trailing spaces. Leading zeros are not  
     * allowed (e.g) 001. Signed integers are not considered valid (e.g) +1, -9.
     *
     * @param the id to be validated as a string.
     * @return true if specified id is valid, otherwise false. 
     */
    public static boolean isValidId(String id) { 
        return Pattern.matches("(^\\s*[1-9][0-9]*\\s*)$", id);
    }
    
    /**
     * Checks if the specified person name is valid. Middle name and last name 
     * are optional. First name can have 3 to 20 letters, middle and last name
     * can have 2 to 20 letters. Leading and trailing spaces are allowed. 
     *
     * @param name name of the employee to be validated as a string.
     * @return true if specified name is valid, otherwise false.
     */
    public static boolean isValidName(String name) {
        return Pattern.matches("^(\\s*[a-zA-Z]{3,20}\\s*)$|^((\\s*[a-zA-Z]"
                + "{3,20}) ([a-zA-Z]{2,20})\\s*)$|^((\\s*[a-zA-Z]{3,20}) "
                + "([a-zA-Z]{2,20}) ([a-zA-Z]){2,20}\\s*)$", name);
    }
}
