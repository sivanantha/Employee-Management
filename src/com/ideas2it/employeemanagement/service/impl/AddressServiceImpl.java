/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service.impl;

import java.sql.SQLException;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.model.Address;

/**
 * The AddressServiceImpl class contains validations and implementations for 
 * address details. The address has the following details: 
 * door number, street name, locality, city, state, country, postal code. 
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class AddressServiceImpl {

    
    /**
     * Validates the specified door number.
     * Door number starts with 0 is not allowed. door number can have maximum
     * of 5 digits, can contain single capital letter with or without '-' hyphen
     * single '/' is allowed. Leading trailing spaces are accepted.
     * (e.g) 12, 12B, C1, A-9, 3-F, 1/30, B12/2, 10C/23, D-30/5, 4-F/224.
     *
     * @param doorNumber the door number to be validated.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidDoorNumber(String doorNumber) {
        return Pattern.matches("^[\\s]*([1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/]"
                + "[1-9][0-9]{0,4}|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])?|([A-Z]|"
                + "[A-Z][-])?[1-9][0-9]{0,4}|([A-Z]|[A-Z][-])[1-9][0-9]{0,4}"
                + "[/][1-9][0-9]{0,4})[\\s]*$", doorNumber);
    }
    
    /**
     * Validates the specified street name.
     * capital and small letters are accepted.
     * minimum of 4 and maximum of 55 characters are accepted. single '.' 
     * between letters is accepted. maximum of 5 digits are accepted. Leading, trailing
     * spaces are accepted.
     * (e.g) 1st st, street no.34, k.k st.
     * 
     * @param street the street name to be validated.
     * @return true if it is valid, otherwise false.
     */  
    public boolean isValidStreet(String street) {
        return Pattern.matches("^[\\s]*(([1-9][0-9]{0,4})?([ ]?[A-Za-z][\\.]?|"
                + "[A-Za-z][ ]?){4,50}([1-9][0-9]{0,4})?)[\\s]*$", street);
    }
    
    /**
     * Validates the specified locality/city/state/country name.
     * only single space is accepted between words. Leading and trailing spaces
     * are accepted. The name can have total of 100 characters. Only alphabets
     * are allowed (can be capital or small letters).
     *
     * @param locality the name of the locality/city/state/country to be 
     *        validated.
     * @return true if valid, otherwise false.
     */
    public boolean isValidPlaceName(String name) {
        return Pattern.matches("^[\\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2})"
                               + "{1,2})[\\s]*$", name);
    }
    
    /**
     * Validates the specified pin code.
     * Space is not accepted between digits. Leading and trailing spaces are 
     * allowed. pin code can be 3 to 9 digits long. Numbers only allowed.
     *
     * @param pinCode the pin code to be validated as a string.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidPinCode(String pinCode) {
        return Pattern.matches("^[\\s]*([0-9]{3,9})[\\s]*$", pinCode);
    }
}
