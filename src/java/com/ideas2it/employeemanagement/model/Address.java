/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.model;

/**
 * The Address class is a container for address details.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public class Address {
    private int id;
    private String city;
    private String country;
    private String doorNumber;
    private String locality;
    private String pinCode;
    private String state;
    private String street;
    private Employee employee;  
    
    /** No parameter constructor. */
    public Address() {
    
    }
    
    /**
     * Constructor for the address
     *
     * @param id the address id.
     * @param doorNumber the door number as a string.
     * @param street the street name.
     * @param locality the locality/area/village name.
     * @param city the city/district name.
     * @param state the state name.
     * @param country the country name.
     * @param pinCode the postal code as a string.
     * @param employee the corresponding Employee instance.
     */
    public Address(int id, String doorNumber, String street, String locality,
                   String city, String state, String country, String pinCode,
                   Employee employee) {
        this.id = id;
        this.doorNumber = doorNumber;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
        this.employee = employee;
    }
    
    /**
     * Constructor for the address
     *
     * @param id the address id.
     * @param doorNumber the door number as a string.
     * @param street the street name.
     * @param locality the locality/area/village name.
     * @param city the city/district name.
     * @param state the state name.
     * @param country the country name.
     * @param pinCode the postal code as a string.
     */
    public Address(int id, String doorNumber, String street, String locality,
                   String city, String state, String country, String pinCode) {
        this.id = id;
        this.doorNumber = doorNumber;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }
    
    public Address(String doorNumber, String street, String locality,
                   String city, String state, String country, String pinCode) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
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
     * Fetches the corresponding employee.
     *
     * @return the corresponding employee.
     */
    public Employee getEmployee() {
        return employee;
    }
    
    /** 
     * Assigns the specified employee to the address.
     *
     * @param employee the corresponding Employee instance.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
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
}