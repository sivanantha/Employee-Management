/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;

/**
 * The EmployeeView class contains view implementations for create, update,
 * retrieve, delete employee for employee management system. It has validation
 * methods for employee details.
 *
 * @author  Sivanantham
 * @version 1.6
 */
public class EmployeeView {
    private Scanner inputReader = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    
    /** 
     * Prints the CRUD choices, asks the user to choose a option to create or 
     * manipulate employee details. Executes the selected option.
     */
    public void showAndGetCRUDChoice() {
        String userChoice;
        StringBuilder options = new StringBuilder(80);
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        
        options.append("\n\t\t\t\t\\ Employees Menu /\n\t\t\t\t ~~~~~~~~~~~~~~")
               .append("~~\n\n\t\t1 => Create Employee\t\t2 => Go To View Menu")
               .append("\n\n\t\t3 => Go To Update Menu\t\t4 => Go To Delete ")
               .append("Menu\n\n\t\t5 => Return to Main Menu\n\n\t\tEnter The")
               .append(" Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    createEmployee();
                    break;
                case "2": 
                    showAndGetViewChoice();
                    break;
                case "3": 
                    showAndGetUpdateChoice();
                    break;
                case "4":
                    showAndGetDeleteChoice();
                    break;                    
                case "5":  
                    break;
                default: 
                    System.out.println(errorMessage); 
                    break;
           }
        } while (!"5".equals(userChoice));
    }
    
    /**
     * Gets employee id from the user for view, updation and deletion
     * operations. Also validates id.
     *
     * @return employee id as integer, only if it is valid.
     */ 
    private int getIdInput() {
        String userInput;
        Integer id = null;
        String message = "\n\t\t\t<<<<<< Please Enter A Valid Non Negative "
                         + "Integer Value And 0 Is Not Allowed! >>>>>>\n";
        
        while (null == id) {
            System.out.print("\n\t\t Enter Employee Id : ");
            userInput = inputReader.nextLine();
            id = employeeController.validateId(userInput);
            
            if (null == id) {
                System.out.println(message);
            }
        }
        return id;
    }
    
    /**
     * Gets employee name from the user for create, update operations and 
     * validates name. Returns the name in lowercase if it is valid.
     *
     * @return employee name as string if valid.
     */
    private String getNameInput() {
        String userInput;
        String name = null;
        String messageForInput = "\n\t\t Enter Employee Name (Middle And Last"
                                 + " Names Are Optional) : ";
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Name! >>>>"
                              + ">>\n";
        
        while (null == name) {
            System.out.print(messageForInput);
            userInput = inputReader.nextLine();
            name = employeeController.validateName(userInput);
            
            if (null == name) {
                System.out.println(errorMessage);
            }
        }
        return name;
    }
    
    /**
     * Gets employee date of birth from the user for create and update 
     * operations and validates date of birth.
     *
     * @return employee's date of birth as LocalDate.
     */
    private LocalDate getDateOfBirthInput() {
        String userInput;
        LocalDate date = null;
        String message = "\n\t\t Enter Employee Date Of Birth (DD-MM-YYYY) "
                         + "(e.g) 06-09-1999 : ";
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Date! Employee"
                              + "'s Age Must Be In Range 18 To 60 >>>>>>\n";
                            
        while (null == date) {
            System.out.print(message);
            userInput = inputReader.nextLine();
            date = employeeController.validateDateOfBirth(userInput);
           
            if (null == date) {
                System.out.println(errorMessage);
            }
        }
        return date;
    }
    
    /**
     * Gets employee gender from the user for create and update operations
     * and also validates gender.
     *
     * @return employee's gender as string if valid.
     */
    private String getGenderInput() {
        String userInput;
        String gender = null;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Gender!"
                              + " >>>>>>\n";
        String message = "\n\t\t Enter Employee Gender (Male/Female/Others) : ";             
   
        while (null == gender) {
            System.out.print(message);
            userInput = inputReader.nextLine();
            gender = employeeController.validateGender(userInput);
            
            if (null == gender) {
                System.out.println(errorMessage);           
            }
        }
        return gender;
    }
    
    /**
     * Gets employee mobile number from the user for create and update 
     * operations and validates mobile number. Also checks for duplication.
     *
     * @return employee's mobile number as long if it is valid.
     */
    private long getMobileNumberInput() {
        String userInput;
        Long mobileNumber = null;
        String errorMessage = "\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>";
        String messageForInvalid = "\n\t\t\t<<<<<< Mobile Number Must Be 10  "
                + "Digits! IST Codes Are Not Allowed. >>>>>>\n";
        String messageForDuplicate = "\n\t\t\t<<<<<< Mobile Number Already "
                                     + "Exist! >>>>>>";
                    
        while (null == mobileNumber) {
            System.out.print("\n\t\t Enter Employee Mobile Number : ");
            userInput = inputReader.nextLine();
            mobileNumber = employeeController.validateMobileNumber(userInput);
            
            try {
                if (null == mobileNumber) {
                    System.out.println(messageForInvalid);
                } else if (employeeController.isMobileNumberExist(mobileNumber))
                         {
                    System.out.println(messageForDuplicate);
                    mobileNumber = null;
                }
            } catch (HibernateException exception) {
                System.out.println(errorMessage);
                mobileNumber = null;
            }
        } 
        return mobileNumber;
    }
    
    /**
     * Gets employee email from the user for create and update operations.
     * Validates email and checks for duplication.
     *
     * @return employee email as string if valid.
     */
    private String getEmailInput() {
        String userInput;
        String email = null;
        String errorMessage = "\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>";
        String messageForDuplicate = "\n\t\t\t<<<<<< Email Already Exist! "
                                     + ">>>>>>\n";
        StringBuilder messageForInvalid = new StringBuilder(50);
        
        messageForInvalid.append("\n\t\t\t<<<<<< Please Enter Valid Email! '-'")
                         .append(", '_', '.' Are Only Allowed Special Characte")
                         .append("rs, Must Start And End With A Letter Or ")
                         .append("Number. There Must Be Atleast 3 Characters ")
                         .append("And Maximum Of 53 Characters. >>>>>>\n");
                           
        while (null == email) {
            System.out.print("\n\t\t Enter Employee's Email : ");
            userInput = inputReader.nextLine();
            email = employeeController.validateEmail(userInput);
            
            try {
                if (null == email) {
                    System.out.println(messageForInvalid);
                } else if (employeeController.isEmailExist(email)) {
                        email = null;
                        System.out.println(messageForDuplicate);    
                }
            } catch (HibernateException exception) {
                System.out.println(errorMessage);
                email = null;
            }   
        }
        return email;
    }
    
    /**
     * Gets employee salary from the user for create and update operations and
     * validates salary.
     *
     * @return employee salary as a float value if valid.
     */
    private float getSalaryInput() {
        String userInput;
        Float salary = null;
        String message = "\n\t\t\t<<<<<< Salary Must Be Atleast 8,000 ! Comma " 
                         + "Separators Are Not Allowed, 1 Or 2 Decimal Points "
                         + "Are Allowed! >>>>>>\n";
        
        while (null == salary) {
            System.out.print("\n\t\t Enter Employee's Salary : ");
            userInput = inputReader.nextLine();
            salary = employeeController.validateSalary(userInput);
            
            if (null == salary) {
                System.out.println(message);
            }
        }
        return salary;
    }
    
    /**
     * Gets employee's date of joining from the user for create and update 
     * operations and validates date.
     *
     * @param dateOfBirth employee's date of birth to validate date of joining.
     * @return employee's date of joining as LocalDate if valid.
     */
    private LocalDate getDateOfJoiningInput() {
        String userInput;
        LocalDate dateOfJoining = null;
        String messageForInput = "\n\t\t Enter Employee Date Of Joining (DD-MM"
                                 + "-YYYY) (e.g) 02-01-2019 : ";
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Date! Future "
                              + "And Dates Older Than 42 Years From Present "
                              + "Date Are Not Allowed! >>>>>>\n";
        
        while (null == dateOfJoining) {
            System.out.print(messageForInput);
            userInput = inputReader.nextLine();
            dateOfJoining = employeeController.validateDateOfJoining(userInput);
            
            if (null == dateOfJoining) {
                System.out.println(errorMessage);    
            }
        }
        return dateOfJoining;
    }
    
    /**
     * Gets the door number from the user for create and update address.
     *
     * @return the door number as a string.
     */
    private String getDoorNumberInput() {
        String doorNumber = null;
        String errorMessage = "\n\t\t\t<<<<<< Invalid Door Number! >>>>>>\n";
        
        while (null == doorNumber) {
            System.out.print("\n\t\t Enter Door Number (e.g)12B, 12/4 : ");
            doorNumber = inputReader.nextLine();
            
            if (employeeController.isValidDoorNumber(doorNumber)) {
                doorNumber = doorNumber.strip();
            } else {
                System.out.println(errorMessage);
                doorNumber = null;
            }
        }
        return doorNumber;
    }
    
    /**
     * Gets the street name from the user for create and update address.
     *
     * @return street name as a string.
     */
    private String getStreetInput() {
        String street = null;
        String errorMessage = "\n\t\t\t<<<<<< Invalid Street Name! >>>>>>\n";
        
        while (null == street) {
            System.out.print("\n\t\t Enter Street Name : ");
            street = inputReader.nextLine();
            
            if (employeeController.isValidStreet(street)) {
                street = street.strip().toLowerCase();
            } else {
                System.out.println(errorMessage);
                street = null;
            }
        }
        return street;
    }
    
    /**
     * Gets the locality from the user for create and update address.
     *
     * @return the locality as a string.
     */
    private String getLocalityInput() {
        String locality = null;
        String errorMessage = "\n\t\t\t<<<<<< Invalid Locality Name! >>>>>>\n";
        
        while (null == locality) {
            System.out.print("\n\t\t Enter Locality/Area/Village : ");
            locality = inputReader.nextLine();
            
            if (employeeController.isValidPlaceName(locality)) {
                locality = locality.strip().toLowerCase();
            } else {
                System.out.println(errorMessage);
                locality = null;
            }
        }
        return locality;
    }
    
    /**
     * Gets the city from the user for create and update address.
     *
     * @return the city as a string.
     */
    private String getCityInput() {
        String city = null;
        String errorMessage = "\n\t\t\t<<<<<< Invalid City Name! >>>>>>\n";
        
        while (null == city) {
            System.out.print("\n\t\t Enter City : ");
            city = inputReader.nextLine();
            
            if (employeeController.isValidPlaceName(city)) {
                city = city.strip().toLowerCase();
            } else {
                System.out.println(errorMessage);
                city = null;
            }
        }
        return city;
    }
    
    /**
     * Gets the state from the user for create and update address.
     *
     * @return the state as a string.
     */ 
    private String getStateInput() {
        String state = null;
        String errorMessage = "\n\t\t\t<<<<<< Invalid State Name! >>>>>>\n";
        
        while (null == state) {
            System.out.print("\n\t\t Enter State : ");
            state = inputReader.nextLine();
            
            if (employeeController.isValidPlaceName(state)) {
                state = state.strip().toLowerCase();
            } else {
                System.out.println(errorMessage);
                state = null;
            }
        }
        return state;
    }
    
    /**
     * Gets the country from the user for create and update address.
     *
     * @return the country as a string.
     */
    private String getCountryInput() {
        String country = null;
        String errorMessage = "\n\t\t\t<<<<<< Invalid Country Name! >>>>>>\n";
        
        while (null == country) {
            System.out.print("\n\t\t Enter Country : ");
            country = inputReader.nextLine();
            
            if (employeeController.isValidPlaceName(country)) {
                country = country.strip().toLowerCase();
            } else {
                System.out.println(errorMessage);
                country = null;
            }
        }
        return country;
    }
    
    /**
     * Gets the postal code from the user for create and update address.
     *
     * @return the postal code as a string.
     */
    private String getPinCodeInput() {
        String pinCode = null;
        String errorMessage = "\n\t\t\t<<<<<< Invalid PinCode! >>>>>>\n";
        
        while (null == pinCode) {
            System.out.print("\n\t\t Enter PinCode : ");
            pinCode = inputReader.nextLine();
            
            if (employeeController.isValidPinCode(pinCode)) {
                pinCode = pinCode.strip();
            } else {
                System.out.println(errorMessage);
                pinCode = null;
            }
        }
        return pinCode;
    }
    
    /**
     * Asks user if they want to add another address.
     * 
     * @return true if user's input is 'y' or false if user input is 'n'.
     */
    private boolean askToAddAnotherAddress() {
        boolean result = false;
        String userInput = null;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter 'Y' To Add Another "
                              + "Address Or 'N' To Submit Details! >>>>>>\n";                
        String message = "\n\t\t Do You Want To Add Another Address? (Y/N) : ";
        
        while (null == userInput) {
            System.out.print(message);
            userInput = inputReader.nextLine().strip().toLowerCase();
            
            if (!("y".equals(userInput) || "n".equals(userInput))) {
                userInput = null;
                System.out.println(errorMessage);
            }
        }
        
        if ("y".equals(userInput)) {
            result = true;
        }
        return result;
    }
    
    /**
     * Gets multiple addresses from the user.
     * 
     * @return a List containing addresses.
     */
    private List<AddressDTO> getAddressesInput() {
        List<AddressDTO> addresses = new ArrayList<>();
        
        System.out.println("\n\t\t\t ~~~~~~~~Address Details~~~~~~~~\n");
        
        do {
            addresses.add(new AddressDTO(getDoorNumberInput(), getStreetInput(),
                    getLocalityInput(), getCityInput(), getStateInput(),
                    getCountryInput(), getPinCodeInput()));
        } while (askToAddAnotherAddress());
        return addresses;
    }
    
    /**
     * Gets employee details from the user, validates and creates a new 
     * employee.
     */
    private void createEmployee() {
        int id;
        String name = getNameInput();
        
        try {
            id = employeeController.createEmployee(new EmployeeDTO(name, 
                         getDateOfBirthInput(), getGenderInput(),
                         getMobileNumberInput(), getEmailInput(),
                         getSalaryInput(), getDateOfJoiningInput(),
                         getAddressesInput()));
            
            System.out.println("\n\t\t\t<<<<<< Employee Created Successfully! "
                               + ">>>>>>\n\n\t\t\t ****** The Employee Id Of < "
                               + name + " > Is --> " + id + " ******");
        } catch (HibernateException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
    }
    
    /**
     * Checks if the employee database is empty.
     *
     * @return true when database is empty or HibernateException occurs, 
     *         otherwise false.
     */ 
    private boolean isEmployeesDatabaseEmpty() {
        boolean isEmpty = false;
        
        try {
            if (employeeController.isEmployeesDatabaseEmpty()) {
                System.out.println("\n\t\t\t<<<<<< Database Is Empty! "
                                   + ">>>>>>\n");
                isEmpty = true;
            } 
        } catch (HibernateException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
            isEmpty = true;
        }
        return isEmpty;
    }
    
    /**
     * Checks if the specified employee exist.
     *
     * @param id the employee's id to be searched.
     * @return false when employee not found or HibernateException occurs,
     *         otherwise true.
     */
    private boolean isEmployeeExist(int id) {
        boolean isEmployeeFound = true;

        try {
            if (!employeeController.isEmployeeExist(id)) {
                System.out.println("\n\n\t\t\t<<<<<< Employee Not Found! "
                                   + ">>>>>>\n");
                isEmployeeFound = false;
            }
        } catch (HibernateException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
            isEmployeeFound = false;
        }
        return isEmployeeFound;
    }
    
    /**
     * Prints available choices for view operation and asks user to choose a
     * option and executes the selected option if database is not empty.
     */
    private void showAndGetViewChoice() {
        String errorMessage;
        String userChoice;
        StringBuilder options;
        
        if (isEmployeesDatabaseEmpty()) {
            return;
        }
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(70);
        options.append("\n\t\t\t\t\\ View Menu /\n\t\t\t\t ~~~~~~~~~~~~\n")
               .append("\n\t\t1 => View Single Employee\t\t2 => View All ")
               .append("Employees\n\n\t\t3 => Return to Employees Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    viewEmployee();
                    break;
                case "2": 
                    viewAllEmployees();
                    break;
                case "3":  
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"3".equals(userChoice)); 
    }
    
    /**
     * Fetches specified employee from the database.
     *
     * @param id the id of the employee to be fetched.
     * @return the employee if found, otherwise null.
     */
    private EmployeeDTO getEmployee(int id) {
        EmployeeDTO employee;
        
        try {
            employee = employeeController.getEmployee(id);
            
            if (null == employee) {
                System.out.println("\n\n\t\t\t<<<<<< Employee Not Found! "
                                   + ">>>>>>\n");
            }
        } catch (HibernateException exception) {
            employee = null;
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
        return employee;
    }
    
    /**
     * Gets employee id from the user and prints employee details if the id is
     * valid.
     */
    private void viewEmployee() {
        int id = getIdInput();
        EmployeeDTO employee = getEmployee(id);
            
        if (null != employee) {
            System.out.println("\n\t\t\t\t ~~~~~~~~EMPLOYEE DETAILS~~~~~~~~\n"
                               + employee);
        }
    }
   
    /** 
     * Checks if database is empty and prints all employee's details if 
     * database is not empty.
     */
    private void viewAllEmployees() {
        List<EmployeeDTO> employees;
        
        try {
            employees = employeeController.getAllEmployees();
            System.out.println("\n\t\t\t\t ~~~~~~ALL EMPLOYEE DETAILS~~~~~~\n");
            
            for (EmployeeDTO employeeDTO : employees) {
                System.out.println(employeeDTO);
                System.out.println("\n\n\t\t\t-------------------------------");
            }
        } catch (HibernateException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
    }
    
    /** 
     * Prints available choices for update operation. gets the choice 
     * from the user and executes the selected if database is not empty.
     */
    private void showAndGetUpdateChoice() {
        int id;
        String errorMessage;
        String userChoice;
        StringBuilder options;
        EmployeeDTO employee;
        
        if (isEmployeesDatabaseEmpty()) {
            return;
        }
        id = getIdInput();
        employee = getEmployee(id);
        
        if (null == employee) {
            return;
        }
        
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(80);
        options.append("\n\t\t\t\t\\ Update Menu /\n\t\t\t\t ~~~~~~~~~~~~~\n")
               .append("\n\t\t1 => Update Single Detail\t\t2 => Update All ")
               .append("Details\n\n\t\t3 => Add New Address\t\t\t4 => Assign ")
               .append("Projects\n\n\t\t5 => UnAssign Projects\t\t6 => Return")
               .append(" to Employees Menu\n\n\t\tEnter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    updateSingleField(employee);
                    break;
                case "2": 
                    showUpdateAllChoices(employee);
                    break;
                case "3":
                    createAddress(employee);
                    break;
                case "4":
                    assignProjects(employee);
                    break;
                case "5":
                    unAssignProjects(employee);
                    break;
                case "6":
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"6".equals(userChoice)); 
    }
    
    /**
     * Prints choices available for update a single employee detail and gets 
     * the choice from the user. Executes the selected choice.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateSingleField(EmployeeDTO employee) {
        String userChoice;
        StringBuilder options = new StringBuilder(80);
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        
        options.append("\n\t\t\t\\ Single Detail Update Menu /\n\t\t\t ~~~~~~")
               .append("~~~~~~~~~~~~~~~~~~~~~\n\t\t1 => Name\t\t\t2 => Date Of")
               .append(" Birth\n\n\t\t3 => Gender\t\t\t4 => Mobile Number\n\n")
               .append("\t\t5 => Email\t\t\t6 => Salary\n\n\t\t7 => Date Of ")
               .append("Joining\t\t8 => Update Address\n\n\t\t9 => ") 
               .append("Return To Previous Menu\n\n\t\t")
               .append("Enter The Option : "); 
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    updateName(employee);
                    break;
                case "2": 
                    updateDateOfBirth(employee);
                    break;
                case "3": 
                    updateGender(employee);
                    break;
                case "4": 
                    updateMobileNumber(employee);
                    break;
                case "5": 
                    updateEmail(employee);
                    break;
                case "6": 
                    updateSalary(employee);
                    break;
                case "7":
                    updateDateOfJoining(employee);
                    break;
                case "8":
                    showUpdateAddressChoices(employee);
                    break;
                case "9":
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"9".equals(userChoice));
    }
    
    /**
     * Gets employee name from the user, validates and updates the employee
     * name.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateName(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            employee.setName(getNameInput());
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Name updated successfully! "
                                   + ">>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee date of birth from the user, validates and updates the   
     * employee date of birth.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateDateOfBirth(EmployeeDTO employee) {     
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            employee.setDateOfBirth(getDateOfBirthInput());
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Date Of Birth Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);    
        }
    }
    
    /**
     * Gets employee gender from the user, validates and updates the   
     * employee gender.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateGender(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            employee.setGender(getGenderInput());
            
            if (employeeController.updateEmployee(employee)) {      
                System.out.println("\n\t\t\t<<<<<< Gender Updated Successfully!"
                                   + " >>>>>>\n");
            } else {
                System.out.println(errorMessage);    
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee mobile number from the user, validates and updates   
     * the employee mobile number.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateMobileNumber(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            employee.setMobileNumber(getMobileNumberInput());
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Mobile Number Updated "
                                   + "Successfully! >>>>>>\n");
            } else { 
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee email from the user, validates and updates   
     * the employee email.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateEmail(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            employee.setEmail(getEmailInput());
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Email Updated Successfully! "
                                   + ">>>>>>\n");
            } else {
                System.out.println(errorMessage);         
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee salary from the user, validates and updates   
     * the employee salary.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateSalary(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            employee.setSalary(getSalaryInput());
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<< Salary Updated Successfully! "
                                   + ">>>>>>\n");    
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee date of joining from the user, validates and updates   
     * the employee date of joining.
     *
     * @param employee the employee whose details to be updated.
     */
    private void updateDateOfJoining(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            employee.setDateOfJoining(getDateOfJoiningInput());
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Date Of Joining Updated " 
                                   + "Successfully! >>>>>>\n");    
            } else {
                System.out.println(errorMessage);        
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Converts list of addresses to text string with serial number.
     *
     * @param addresses a list of addresses to be converted to string.
     * @return a StringBuilder containing addresses as string.
     */
    private StringBuilder getAddressesString(List<AddressDTO> addresses) {
        int count = 1;
        StringBuilder addressText =  new StringBuilder(150);
        
        for (AddressDTO address : addresses) {
             addressText.append("\n\t\t ")
                        .append(count).append(" =>   ")
                        .append(address.getDoorNumber()).append(", ")
                        .append(address.getStreet()).append(",\n\t\t\t")
                        .append(address.getLocality()).append(", ")
                        .append(address.getCity()).append(",\n\t\t\t")
                        .append(address.getState()).append(",\n\t\t\t")
                        .append(address.getCountry()).append(" - ")
                        .append(address.getPinCode()).append("\n\n");       
             count++;
        }
        return addressText;
    }
    
    /**
     * Shows all addresses of the specified employee and returns the selected 
     * address. This method is used for update and delete operations.
     *
     * @param employee the employee whose address to be shown.
     * @return the address or null if an employee does not have 
     *         any address or exit option is selected.
     */
    private AddressDTO showAndGetAddress(EmployeeDTO employee) {
        AddressDTO address = null;
        List<AddressDTO> addresses = employee.getAddresses();
        int size = addresses.size();
        
        if (size > 0) {
            System.out.println(getAddressesString(addresses));
            System.out.println("\n\t\t -1 =>  Go Back\n");
            address = getAddress(addresses);
        } else {
            System.out.println("\n\t\t\t<<<<<< No Address Found! >>>>>>\n");
        }
        return address;
    }
    
    /**
     * Asks the user to select an address for update or delete operation.
     *
     * @param addresses the List of addresses of an employee.
     * @return the address of the selected address or null if exit choice is 
     *         selected.
     */
    private AddressDTO getAddress(List<AddressDTO> addresses) {
        Integer userChoice = null;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        AddressDTO address = null;
        
        while (null == userChoice) {
            System.out.print("\n\t\tEnter The Choice : ");
                
            try {
                userChoice = Integer.parseInt(inputReader.nextLine());
                
                if (userChoice == -1) {
                    address = null;
                } else {
                    address = addresses.get(userChoice - 1);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException 
                     exception) {
                System.out.println(errorMessage);
                userChoice = null;
            }
        }
        return address;
    }
    
    /**
     * Shows the available choices for update single detail of the address and
     * gets the choice and executes the corresponding operation.
     *
     * @param employee the employee whose address to be updated.
     */
    private void showUpdateAddressChoices(EmployeeDTO employee) {
        AddressDTO address = showAndGetAddress(employee);
        String errorMessage;
        String userChoice;
        StringBuilder options;
        
        if (null == address) {
            return;
        }
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                       + ">>>>>>\n";
        options = new StringBuilder(80);
        options.append("\n\t\t\t\\ Address Update Menu /\n\t\t\t ~~~~~~")
               .append("~~~~~~~~~~~~~~~~\n\t\t1 => Door Number\t\t2 => ")
               .append("Street Name\n\n\t\t3 => Locality\t\t\t4 => City\n\n")
               .append("\t\t5 => State\t\t\t6 => Country\n\n\t\t7 => Postal")
               .append("Code\t\t\t8 => Return To Previous Menu\n\n\t\t")
               .append("Enter The Option : "); 
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    updateDoorNumber(address);
                    break;
                case "2": 
                    updateStreet(address);
                    break;
                case "3": 
                    updateLocality(address);
                    break;
                case "4": 
                    updateCity(address);
                    break;
                case "5": 
                    updateState(address);
                    break;
                case "6": 
                    updateCountry(address);
                    break;
                case "7": 
                    updatePinCode(address);
                    break;
                case "8":
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"8".equals(userChoice));
    }
    
    /**
     * Gets door number from the user, validates the door number and updates 
     * the door number of the specified address.
     *
     * @param address the address to be updated.
     */
    private void updateDoorNumber(AddressDTO address) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            address.setDoorNumber(getDoorNumberInput());
            
            if (employeeController.updateAddress(address)) {      
                System.out.println("\n\t\t\t<<<<<< Door Number Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets street from the user, validates street and updates the street of the
     * specified address.
     *
     * @param address the address to be updated.
     */
    private void updateStreet(AddressDTO address) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            address.setStreet(getStreetInput());
            
            if (employeeController.updateAddress(address)) {      
                System.out.println("\n\t\t\t<<<<<< Street Updated Successfully!"
                                   + " >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets locality from the user and updates the locality of the
     * specified address.
     *
     * @param address the address to be updated.
     */
    private void updateLocality(AddressDTO address) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            address.setLocality(getLocalityInput());
            
            if (employeeController.updateAddress(address)) {     
                System.out.println("\n\t\t\t<<<<<< Locality Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets city from the user and updates the city of the
     * specified address.
     *
     * @param address the address to be updated.
     */
    private void updateCity(AddressDTO address) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            address.setCity(getCityInput());
            
            if (employeeController.updateAddress(address)) {      
                System.out.println("\n\t\t\t<<<<<< Locality Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets state from the user and updates the state of the
     * specified address.
     *
     * @param address the address to be updated.
     */
    private void updateState(AddressDTO address) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            address.setState(getStateInput());
            
            if (employeeController.updateAddress(address)) {      
                System.out.println("\n\t\t\t<<<<<< State Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets country from the user and updates the country of the
     * specified address.
     *
     * @param address the address to be updated.
     */
    private void updateCountry(AddressDTO address) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            address.setCountry(getCountryInput());
            
            if (employeeController.updateAddress(address)) {      
                System.out.println("\n\t\t\t<<<<<< Country Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets postal code from the user and updates the postal code of the
     * specified address.
     *
     * @param address the address to be updated.
     */
    private void updatePinCode(AddressDTO address) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            address.setPinCode(getPinCodeInput());
            
            if (employeeController.updateAddress(address)) {      
                System.out.println("\n\t\t\t<<<<<< Pin Code Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Shows all projects and asks user to select projects to assign those to 
     * the employee.
     *
     * @return a set containing selected projects or if there is no projects
     *         in the database then an empty set.
     * @throws HibernateException if database access error occurs.
     */
    private Set<ProjectDTO> getProjectsToAssign() throws HibernateException {
        String[] selectedIds;
        String userInput;
        int count = 1;
        List<ProjectDTO> projects = employeeController.getAllProjects();
        Set<ProjectDTO> selectedProjects = new HashSet<>();
        
        for (ProjectDTO project : projects) {
            System.out.println("\n\t\t" + count + " => " + project.getName());
            count++;
        }
        
        while (selectedProjects.isEmpty() && (!projects.isEmpty())) {
            System.out.print("\n\t\tEnter Projects Numbers To Assign(separated"
                         + " by comma) (e.g) 1, 2, 3 : ");
            userInput = inputReader.nextLine();
            selectedIds = userInput.split(",");
            
            try {
                for (String id : selectedIds) {
                    selectedProjects.add(projects.get(Integer.parseInt(
                            id.strip()) - 1));
                }
            } catch (NumberFormatException | IndexOutOfBoundsException 
                     exception) {
                System.out.println("\n\t\t<<<<<< Invalid Selection! >>>>>>>\n");
                selectedProjects.clear();
            }
        }
        return selectedProjects;
    }
    
    /**
     * Gets projects from the user and assigns them to the specified employee.
     *
     * @param employee the employee to assign projects.
     */
    private void assignProjects(EmployeeDTO employee) {
        Set<ProjectDTO> projects;
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            projects = getProjectsToAssign();
        
            if (projects.isEmpty()) {
                System.out.println("\n\t\t\t<<<<<< No Projects Found To Assign!"
                                   + " >>>>>>\n");
                return;
            }
            employee.getProjects().addAll(projects);
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Projects Assigned "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Shows all projects and asks user to select projects to unassign the 
     * employee from those projects.
     *
     * @param employee the employee to be unassigned from the projects.
     * @return a set containing selected projects or if there is no projects
     *         assigned then an empty set.
     */ 
    private Set<ProjectDTO> getProjectsToUnAssign(EmployeeDTO employee) {
        String[] selectedIds;
        String userInput;
        int count = 1;
        List<ProjectDTO> projects = new ArrayList<>();
        Set<ProjectDTO> selectedProjects = new HashSet<>();
        
        projects.addAll(employee.getProjects());
        
        for (ProjectDTO project : projects) {
            System.out.println("\n\t\t" + count + " => " + project.getName());
            count++;
        }
        
        while (selectedProjects.isEmpty() && (!projects.isEmpty())) {
            System.out.print("\n\t\tEnter Projects Numbers To UnAssign"
                         + "(separated by comma) (e.g) 1, 2, 3 : ");
            userInput = inputReader.nextLine();
            selectedIds = userInput.split(",");
            
            try {
                for (String id : selectedIds) {
                    selectedProjects.add(projects.get(Integer.parseInt(
                            id.strip()) - 1));
                }
            } catch (NumberFormatException | IndexOutOfBoundsException 
                     exception) {
                System.out.println("\n\t\t<<<<<< Invalid Selection! >>>>>>>\n");
                selectedProjects.clear();
            }
        }
        return selectedProjects;
    }
    
    /**
     * Gets projects from the user to unassign the employee from the projects.
     *
     * @param project the project to unassign employees.
     */
    private void unAssignProjects(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        Set<ProjectDTO> projects = getProjectsToUnAssign(employee);

        if (projects.isEmpty()) {
            System.out.println("\n\t\t\t<<<<<< No Projects Found To Unassign!"
                                   + " >>>>>>\n");
            return;
        }
            
        try {
            employee.getProjects().removeAll(projects);
            
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Projects UnAssigned "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Shows the choices available for update all menu and gets the desired 
     * choice from the user. Executes the specified choice.
     *
     * @param employee the employee whose details to be updated.
     */
    private void showUpdateAllChoices(EmployeeDTO employee) {
        StringBuilder options;
        String errorMessage;
        String userChoice;

        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(80);
        options.append("\n\t\t\t\t\\ Update All Details Menu /\n\t\t\t\t ")
               .append("~~~~~~~~~~~~~\n\n\t\t1 => Update Employee's Details")
               .append("\t\t2 => Update Address Details\n\n\t\t")
               .append("3 => Return to Employees Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1":
                    updateAllDetails(employee);
                    break;
                case "2":
                    updateAddressDetails(employee);
                    break;
                case "3":
                    break;
                default:
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"3".equals(userChoice));
    }
    
    /**
     * Gets all details of the employee from the user except address, validates 
     * and updates all details. It does not check for employee existence.
     *
     * @param employee employee to be updated.
     */
    private void updateAllDetails(EmployeeDTO employee) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        employee.setAll(getNameInput(), getDateOfBirthInput(), getGenderInput(), 
                getMobileNumberInput(), getEmailInput(), getSalaryInput(),
                getDateOfJoiningInput());
        
        try {
            if (employeeController.updateEmployee(employee)) {
                System.out.println("\n\t\t\t<<<<<< Employee Details Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Asks user to select an address to update. If selected, gets input from 
     * the user and updates all details of the specified address.
     *
     * @param employee the employee whose address to be updated.
     */
    private void updateAddressDetails(EmployeeDTO employee) {
        String errorMessage;
        AddressDTO address = showAndGetAddress(employee);
       
        if (null == address) {
            return;
        }
        errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        address.setAll(getDoorNumberInput(), getStreetInput(),
                getLocalityInput(), getCityInput(), getStateInput(),
                getCountryInput(), getPinCodeInput());
        
        try {
            if (employeeController.updateAddress(address)) {
                System.out.println("\n\t\t\t<<<<<< Address Updated "         
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets multiple addresses from the user and creates the specified 
     * addresses.
     *
     * @param employee the corresponding employee to add address.
     */
    private void createAddress(EmployeeDTO employee) {
        List<AddressDTO> addresses = getAddressesInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        employee.getAddresses().addAll(addresses);
        
        try {
            if (employeeController.createAddresses(employee)) {
                System.out.println("\n\t\t\t<<<<<< Addresses Created "         
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Prints available choices for delete operation and asks the user to select
     * a choice if database is not empty.
     */
    private void showAndGetDeleteChoice() {
        StringBuilder options = new StringBuilder(80);
        String userChoice;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        
        options.append("\n\t\t\t\t\\ Delete Menu /\n\t\t\t\t ~~~~~~~~~~~~~\n")
               .append("\n\t\t1 => Delete Single Employee\t\t2 => Delete All")
               .append(" Employees\n\n\t\t3 => Delete Only Address Of An ")
               .append("Employee\n\n\t\t4 => Return To Employees Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            if (isEmployeesDatabaseEmpty()) {
                return;
            }
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    deleteEmployee();
                    break;
                case "2": 
                    deleteAllEmployees();
                    break;
                case "3": 
                    deleteAddress(); 
                    break;
                case "4":
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"4".equals(userChoice)); 
    }
    
    /**
     * Asks user for confirmation to delete.
     * 
     * @return true if user's input is yes or false if user input is no.
     */
    private boolean askConfirmationToDelete() {
        boolean result =  false;
        String userInput = null;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter 'Y' To Delete Or 'N'"
                              + " To Cancel And Return To Main Menu >>>>>>\n";
        String messageForAbort = "\n\t\t\t<<<<<< Aborted! Returning To Main"
                                 + " Menu... >>>>>>\n";                
        
        while (null == userInput) {
            System.out.print("\n\t\t Do You Want To Delete ? (Y/N) : ");
            userInput = inputReader.nextLine().strip().toLowerCase();
            
            if (!("y".equals(userInput) || "n".equals(userInput))) {
                userInput = null;
                System.out.println(errorMessage);
            }
        }
        
        if ("y".equals(userInput)) {
            result = true;
        }
        System.out.println(messageForAbort);
        return result;
    }
    
    /**
     * Gets employee id from the user, validates id. Asks user for confirmation 
     * and deletes the employee if found.
     */
    public void deleteEmployee() {
        int id = getIdInput();
        
        if (!isEmployeeExist(id)) {
            return;
        }
        
        try {
            if (askConfirmationToDelete()) {
                if (employeeController.deleteEmployee(id)) {
                    System.out.println("\n\t\t\t<<<<<< Deleted Successfully! "
                                       + ">>>>>>\n");
                } else {
                    System.out.println("\n\t\t\t<<<<<< An Error Occurred! "
                                       + ">>>>>>\n");
                }
            }
        } catch (HibernateException exception) {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
        }
    }
    
    /**
     * Asks user to select the address to be deleted and deletes the specified
     * address.
     */
    private void deleteAddress() {
        AddressDTO address;
        int employeeId = getIdInput();
        EmployeeDTO employee = getEmployee(employeeId);
        
        if (null == employee) {
            return;
        }
        address = showAndGetAddress(employee);
        
        if (null == address) {
            return;
        }

        if (askConfirmationToDelete()) {
            try {
                if (employeeController.deleteAddress(address.getId())) {
                    System.out.println("\n\t\t\t<<<<<< Deleted Successfully"
                                       + "! >>>>>>\n");
                } else {
                    System.out.println("\n\t\t\t<<<<<< Cannot Delete The "
                                       + "Address! >>>>>>\n");
                }
            } catch (HibernateException exception) {
                System.out.println("\n\t\t\t<<<<<< An Error Occurred! "
                                   + ">>>>>>\n");
            }
        }
    }
    
    /** 
     * Asks user for confirmation to delete all employee records and deletes all
     * employee records if database is not empty.
     */
    private void deleteAllEmployees() {
        if (askConfirmationToDelete()) {
            try {
                if (employeeController.deleteAllEmployees()) {
                    System.out.println("\n\t\t\t<<<<<< Deleted Successfully! "
                                       + ">>>>>>\n");
                } else {
                    System.out.println("\n\t\t\t<<<<<< An Error Occurred! "
                                       + ">>>>>>\n");
                }
            } catch (HibernateException exception) {
                System.out.println("\n\t\t\t<<<<<< An Error Occurred! "
                                   + ">>>>>>\n");
            }          
        }
    }
}
