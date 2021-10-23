/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.model;

/**
 * The AddressDTO class is a data transfer object for address details.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class AddressDTO {
    int id;
    int employeeId;
    String city;
    String country;
    String doorNumber;
    String locality;
    String pinCode;
    String state;
    String street;
    
    /** No parameter constructor. */
    public AddressDTO() {
    
    }
    
    /**
     * Constructor for the address.
     *
     * @param id the address id.
     * @param doorNumber the door number as a string.
     * @param street the street name.
     * @param locality the locality/area/village name.
     * @param city the city/district name.
     * @param state the state name.
     * @param country the country name.
     * @param pinCode the postal code as a string.
     * @param employeeId the corresponding employee's id.
     */
    public AddressDTO(int id, String doorNumber, String street, String locality,
                   String city, String state, String country, String pinCode,
                   int employeeId) {
        this.id = id;
        this.doorNumber = doorNumber;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.employeeId = employeeId;
    }
    
    /**
     * Constructor for the address
     *
     * @param doorNumber the door number as a string.
     * @param street the street name.
     * @param locality the locality/area/village name.
     * @param city the city/district name.
     * @param state the state name.
     * @param country the country name.
     * @param pinCode the postal code as a string.
     * @param employeeId the corresponding employee's id.
     */
    public AddressDTO(String doorNumber, String street, String locality,
                   String city, String state, String country, String pinCode, 
                   int employeeId) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.employeeId = employeeId;
    }

    
    /**
     * Fetches the id of the address.
     *
     * @return the id of the address as a int.
     */
    public int getId() {
        return id;
    }
    
    /** 
     * Assigns the specified id to the address.
     *
     * @param id the id of the address as a int.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Fetches the id of the corresponding employee.
     *
     * @return the id of the corresponding employee as a int.
     */
    public int getEmployeeId() {
        return employeeId;
    }
    
    /** 
     * Assigns the specified employee id to the address.
     *
     * @param employeeId the id of corresponding employee as a int.
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    
    /**
     * Fetches the doorNumber of the address.
     *
     * @return the doorNumber of the address as a string.
     */
    public String getDoorNumber() {
        return doorNumber;
    }
    
    /** 
     * Assigns the specified doorNumber to the address.
     *
     * @param doorNumber the doorNumber of the address as a string.
     */
    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }
    
    /**
     * Fetches the street of the address.
     *
     * @return the street of the address as a string.
     */
    public String getStreet() {
        return street;
    }
    
    /** 
     * Assigns the specified street to the address.
     *
     * @param street the street of the address as a string.
     */
    public void setStreet(String street) {
        this.street = street;
    }
    
    /**
     * Fetches the locality of the address.
     *
     * @return the locality of the address as a string.
     */
    public String getLocality() {
        return locality;
    }
    
    /** 
     * Assigns the specified locality to the address.
     *
     * @param locality the locality of the address as a string.
     */
    public void setLocality(String locality) {
        this.locality = locality;
    }
    
    /**
     * Fetches the city of the address.
     *
     * @return the city of the address as a string.
     */
    public String getCity() {
        return city;
    }
    
    /** 
     * Assigns the specified city to the address.
     *
     * @param city the city of the address as a string.
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /** 
     * Fetches the specified state to the address.
     *
     * @return the state of the address as a string.
     */
    public String getState() {
        return state;
    }
    
    /** 
     * Assigns the specified state to the address.
     *
     * @param state the state of the address as a string.
     */
    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * Fetches the country of the address.
     *
     * @return the country of the address as a string.
     */
    public String getCountry() {
        return country;
    }
    
    /** 
     * Assigns the specified country to the address.
     *
     * @param country the country of the address as a string.
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Fetches the pin code of the address.
     *
     * @return a string representing pin code.
     */
    public String getPinCode() {
        return pinCode;
    }
    
    /** 
     * Assigns the specified pin code to the address.
     *
     * @param pinCode the pin code of the address as a string.
     */
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
    
    /**
     * Provides this object in human readable form.
     *
     * @return a string which is description of the object
     */
    @Override
    public String toString() {
        return new StringBuilder(150).append("\n\t\t Address         : ")
                                     .append(doorNumber).append(", ")
                                     .append(street).append(",\n\t\t\t\t   ")
                                     .append(locality).append(", ")
                                     .append(city).append(",\n\t\t\t\t   ")
                                     .append(state).append(",\n\t\t\t\t   ")
                                     .append(country).append(" - ")
                                     .append(pinCode).append(".\n")
                                     .toString();
    }
}
