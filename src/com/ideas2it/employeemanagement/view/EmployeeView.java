/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;   
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.AddressController;
import com.ideas2it.employeemanagement.controller.EmployeeController;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;

/**
 * The EmployeeView class contains view implementations for create, update,
 * retrieve, delete employee for employee management system.
 *
 * @author  Sivanantham
 * @version 1.6
 */
public class EmployeeView {
    private Scanner inputReader = new Scanner(System.in);
    private EmployeeController employeeController = new EmployeeController();
    private AddressController addressController = new AddressController();
    
    /**
     * Prints the welcome message and starts the employee management 
     * application.
     */   
    public void showWelcomeMessage() {
        StringBuilder title = new StringBuilder(180);
        
        title.append("\n######################################################")
             .append("#######################################################")
			 .append("\n \\^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*************")
			 .append("******************^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")
			 .append("/\n  \\                             W E L C O M E")
			 .append("   T O  E M P L O Y E E   M A N A G E R \t\t\t  /\n")
			 .append("   \\~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..`.")
			 .append(".`..`~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/\n")
			 .append("\t\t                                 ( @     @ ) \n")
			 .append("\t\t\t\t\t\t   ,  ^  ,    \n\t\t\t\t\t\t    .   .    \n")
			 .append("\t\t\t\t\t\t      '\n");
		System.out.print(title);
        printAndGetCRUDChoice(); 
    }
    
    /** 
     * Prints the CRUD choices, asks the user to choose a option to create or 
     * manipulate employee details. Executes the selected option.
     */
    private void printAndGetCRUDChoice() {
        StringBuilder options = new StringBuilder(80);
        String userChoice;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        
        options.append("\n\t\t\t\t\\ Main Menu /\n\t\t\t\t ~~~~~~~~~~~\n")
               .append("\n\t\t1 => Create Employee\t\t2 => Go To View Menu\n\n")
               .append("\t\t3 => Go To Update Menu\t\t4 => Go To Delete Menu\n")
               .append("\n\t\t5 => Exit\n\n\t\tEnter The Option : ");
        
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
        Integer id = null;
        String userInput;
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
        String name = null;
        String userInput;
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
        LocalDate date = null;
        String userInput;
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
        String gender = null;
        String userInput;
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
        Long mobileNumber = null;
        String userInput;
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
            } catch (SQLException exception) {
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
        StringBuilder messageForInvalid = new StringBuilder(50);
        String email = null;
        String userInput;
        String errorMessage = "\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>";
        String messageForDuplicate = "\n\t\t\t<<<<<< Email Already Exist! "
                                     + ">>>>>>\n";
        
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
            } catch (SQLException exception) {
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
        Float salary = null;
        String userInput;
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
            System.out.print("\n\t\t Enter Door Number (e.g)12B, 12/4: ");
            doorNumber = inputReader.nextLine();
            
            if (addressController.isValidDoorNumber(doorNumber)) {
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
            
            if (addressController.isValidStreet(street)) {
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
            
            if (addressController.isValidPlaceName(locality)) {
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
            
            if (addressController.isValidPlaceName(city)) {
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
            
            if (addressController.isValidPlaceName(state)) {
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
            
            if (addressController.isValidPlaceName(country)) {
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
            
            if (addressController.isValidPinCode(pinCode)) {
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
            return true;
        }
        return false;
    }
    
    /**
     * Gets multiple addresses from the user.
     * 
     * @return a List containing addresses.
     */
    private List<AddressDTO> getAddressesInput(int employeeId) {
        String city;
        String country;
        String doorNumber;
        String locality;
        String pinCode;
        String state;
        String street;
        List<AddressDTO> addresses = new ArrayList<>();
        
        do {
            doorNumber = getDoorNumberInput();
            street = getStreetInput();
            locality = getLocalityInput();
            city = getCityInput();
            state = getStateInput();
            country = getCountryInput();
            pinCode = getPinCodeInput();
        
            addresses.add(new AddressDTO(doorNumber, street, locality, city, 
                                         state, country, pinCode, employeeId));
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
        String gender = getGenderInput();
        LocalDate dateOfBirth = getDateOfBirthInput();
        long mobileNumber = getMobileNumberInput();
        String email = getEmailInput();
        float salary = getSalaryInput();
        LocalDate dateOfJoining = getDateOfJoiningInput();
        
        try {
            id = employeeController.createEmployee(new EmployeeDTO(name, 
                         dateOfBirth, gender, mobileNumber, email, salary,
                         dateOfJoining));
            addressController.createAddress(getAddressesInput(id));
            
            System.out.println("\n\t\t\t<<<<<< Employee Created Successfully! "
                               + ">>>>>>\n\n\t\t\t ****** The Employee Id Of < "
                               + name + " > Is --> " + id + " ******");
        } catch (SQLException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
    }
    
    /**
     * Checks if the employee database is empty.
     *
     * @return true when database is empty or SQLException occurs, 
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
        } catch (SQLException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
            isEmpty = true;
        }
        return isEmpty;
    }
    
    /**
     * Checks if the specified employee exist.
     *
     * @param id the employee's id to be searched.
     * @return false when employee not found or SQLException occurs,
               otherwise true.
     */
    private boolean isEmployeeExist(int id) {
        boolean isEmployeeFound = true;
       
        try {
            if (!employeeController.isEmployeeExist(id)) {
                System.out.println("\n\n\t\t\t<<<<<< Employee Not Found! "
                                   + ">>>>>>\n");
                isEmployeeFound = false;
            }
        } catch (SQLException exception) {
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
        StringBuilder options;
        String errorMessage;
        String userChoice;
        
        if (isEmployeesDatabaseEmpty()) {
            return;
        }
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(70);
        options.append("\n\t\t\t\t\\ View Menu /\n\t\t\t\t ~~~~~~~~~~~~\n")
               .append("\n\t\t1 => View Single Employee\t\t2 => View All ")
               .append("Employees\n\n\t\t3 => Return to Main Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    viewEmployee();
                    break;
                case "2": 
                    viewAllEmployee();
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
     * Gets employee id from the user and prints employee details if the id is
     * valid.
     */
    private void viewEmployee() {
        StringBuilder employeeDetails;
        int id = getIdInput();
        
        try {
            if (isEmployeeExist(id)) {
                employeeDetails = new StringBuilder(300);
                employeeDetails.append("\n\t\t\t\t ~~~~~~~EMPLOYEE DETAILS~~~~")
                               .append("~~~\n")
                               .append(employeeController.getEmployee(id).get(0)
                                                         .toString());
                
                for (AddressDTO addressDTO : addressController
                                             .getAddresses(id)) {
                    employeeDetails.append(addressDTO.toString());
                    
                }
                System.out.println(employeeDetails);
            }
        } catch (SQLException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>"); 
        }
    }
    
    /** 
     * Checks if database is empty and prints all employee's details if 
     * database is not empty.
     */
    private void viewAllEmployee() {
        try {
            List<AddressDTO> addresses;
            List<EmployeeDTO> employees = employeeController.getAllEmployees();
            Map<Integer, List<AddressDTO>> addressesDTO = addressController
                                             .getAllAddresses();
            
            System.out.println("\n\t\t\t\t ~~~~~~ALL EMPLOYEE DETAILS~~~~~~\n");
            
            for (EmployeeDTO employeeDTO : employees) {
                System.out.println(employeeDTO);
                addresses = addressesDTO.get(employeeDTO.getId());
                
                for (AddressDTO address : addresses) {
                    System.out.println(address);
                }
                System.out.println("\n\n\t\t\t-------------------------------");
            }
        } catch (SQLException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
    }
    
    /** 
     * Prints available choices for update operation. gets the choice 
     * from the user and executes the selected if database is not empty.
     */
    private void showAndGetUpdateChoice() {
        int id;
        StringBuilder options;
        String errorMessage;
        String userChoice;
        
        if (isEmployeesDatabaseEmpty()) {
            return;
        }     
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(80);
        options.append("\n\t\t\t\t\\ Update Menu /\n\t\t\t\t ~~~~~~~~~~~~~\n")
               .append("\n\t\t1 => Update Single Detail\t\t2 => Update All ")
               .append("Details\n\n\t\t3 => Return to Main Menu\n\n\t\t")
               .append("Enter The Option : ");
        id = getIdInput();
        
        if (!isEmployeeExist(id)) {
            return;
        }
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    updateSingleField(id);
                    break;
                case "2": 
                    showUpdateAllChoices(id);
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
     * Prints choices available for update a single employee detail and gets 
     * the choice from the user. Executes the selected choice.  
     */
    private void updateSingleField(int id) {
        StringBuilder options = new StringBuilder(80);
        String userChoice;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        options.append("\n\t\t\t\\ Single Detail Update Menu /\n\t\t\t ~~~~~~")
               .append("~~~~~~~~~~~~~~~~~~~~~\n\t\t1 => Name\t\t\t2 => Date Of")
               .append(" Birth\n\n\t\t3 => Gender\t\t\t4 => Mobile Number\n\n")
               .append("\t\t5 => Email\t\t\t6 => Salary\n\n\t\t7 => Date Of ")
               .append("Joining\t\t8 => Update Address\n\n\t\t 9 => Return To") 
               .append(" Previous Menu\n\n\t\t")
               .append("Enter The Option : "); 
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    updateName(id);
                    break;
                case "2": 
                    updateDateOfBirth(id);
                    break;
                case "3": 
                    updateGender(id);
                    break;
                case "4": 
                    updateMobileNumber(id);
                    break;
                case "5": 
                    updateEmail(id);
                    break;
                case "6": 
                    updateSalary(id);
                    break;
                case "7": 
                    updateDateOfJoining(id);
                    break;
                case "8":
                    showUpdateAddressChoices(id);
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
     * Gets employee id, name from the user, validates and updates the employee
     * name if id is found.
     */
    private void updateName(int id) {
        String name = getNameInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {    
            if (employeeController.updateName(id, name)) {
                System.out.println("\n\t\t\t<<<<<< Name updated successfully! "
                                   + ">>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee id, date of birth from the user, validates and updates the   
     * employee date of birth if employee id is found.
     */
    private void updateDateOfBirth(int id) { 
        LocalDate dateOfBirth = getDateOfBirthInput();    
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (employeeController.updateDateOfBirth(id, dateOfBirth)) {
                System.out.println("\n\t\t\t<<<<<< Date Of Birth Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);    
        }
    }
    
    /**
     * Gets employee id, gender from the user, validates and updates the   
     * employee gender if employee id is found.
     */
    private void updateGender(int id) {
        String gender = getGenderInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (employeeController.updateGender(id, gender)) {      
                System.out.println("\n\t\t\t<<<<<< Gender Updated Successfully!"
                                   + " >>>>>>\n");
            } else {
                System.out.println(errorMessage);    
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee id, mobile number from the user, validates and updates   
     * the employee gender if id is found.
     */
    private void updateMobileNumber(int id) {
        long mobileNumber = getMobileNumberInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (employeeController.updateMobileNumber(id, mobileNumber)) {
                System.out.println("\n\t\t\t<<<<<< Mobile Number Updated "
                                   + "Successfully! >>>>>>\n");
            } else { 
                System.out.println(errorMessage);
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee id, email from the user, validates and updates   
     * the employee email if id is found.
     */
    private void updateEmail(int id) {
        String email = getEmailInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (employeeController.updateEmail(id, email)) {
                System.out.println("\n\t\t\t<<<<<< Email Updated Successfully! "
                                   + ">>>>>>\n");
            } else {
                System.out.println(errorMessage);         
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee id, salary from the user, validates and updates   
     * the employee salary if id is found.
     */
    private void updateSalary(int id) {
        float salary = getSalaryInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {   
            if (employeeController.updateSalary(id, salary)) {
                System.out.println("\n\t\t\t<<<<< Salary Updated Successfully! "
                                   + ">>>>>>\n");    
            } else {
                System.out.println(errorMessage);
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employee id, date of joining from the user, validates and updates   
     * the employee date of joining if id is found.
     */
    private void updateDateOfJoining(int id) {
        LocalDate date = getDateOfJoiningInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {   
            if (employeeController.updateDateOfJoining(id, date)) {
                System.out.println("\n\t\t\t<<<<<< Date Of Joining Updated " 
                                   + "Successfully! >>>>>>\n");    
            } else {
                System.out.println(errorMessage);        
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Shows all addresses of the specified employee and returns the selected 
     * address id.
     *
     * @param employeeId the id of the employee whose address to be shown.
     * @return the id of selected the address or 0 if an employee does not have 
     * any address or -1 if exit option is selected.
     */
    private int showAndGetAddress(int employeeId) {
        int addressId = 0;
        int size = 0;
        int count = 1;
        StringBuilder addressText;
        List<AddressDTO> addresses;
        
        try {
            addresses = addressController.getAddresses(employeeId);
            size = addresses.size();
            
            if (size > 0) {
                for (AddressDTO addressDTO : addresses) {
                addressText =  new StringBuilder(150).append("\n\t\t ")
                                          .append(count).append(" =>   ")
                                          .append(addressDTO.getDoorNumber())
                                          .append(", ")
                                          .append(addressDTO.getStreet())
                                          .append(",\n\t\t\t")
                                          .append(addressDTO.getLocality())
                                          .append(", ")
                                          .append(addressDTO.getCity())
                                          .append(",\n\t\t\t")
                                          .append(addressDTO.getState())
                                          .append(",\n\t\t\t")
                                          .append(addressDTO.getCountry())
                                          .append(" - ")
                                          .append(addressDTO.getPinCode());
                    System.out.println(addressText);
                    count++;
                }
                System.out.println("\n\t\t -1 =>  Go Back\n");
                addressId = getAddressId(addresses);
            } else {
                System.out.println("\n\t\t\t<<<<<< No Address Found! >>>>>>\n");
            }
        } catch (SQLException exception) {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
            addressId = 0;
        }
        return addressId;
    }
    
    /**
     * Shows all the addresses of an employee and asks the user to select an 
     * address then gets the address id of the selected address.
     *
     * @param addresses the List of addresses of an employee.
     * @return the addressId of the selected address or -1 if exit choice is 
     *         selected.
     */
    private int getAddressId(List<AddressDTO> addresses) {
        Integer addressId = null;
        Integer userChoice;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        
        while (null == addressId) {
            System.out.print("\n\t\t\tEnter The Choice : ");
                
            try {
                userChoice = Integer.parseInt(inputReader.nextLine());
                if (userChoice == -1) {
                    addressId = -1;   
                } else {
                    addressId = addresses.get(userChoice - 1).getId();
                }
            } catch (NumberFormatException | IndexOutOfBoundsException 
                     exception) {
                System.out.println(errorMessage);
                addressId = null;
            }
        }
        return addressId;
    }
    
    /**
     * Shows the available choices for update single detail of the address and
     * gets the choice and executes the corresponding operation.
     *
     * @param employeeId the id of the employee whose address to be updated.
     */
    private void showUpdateAddressChoices(int employeeId) {
        int addressId = showAndGetAddress(employeeId);
        String errorMessage;
        String userChoice;
        StringBuilder options;
        
        if (0 >= addressId) {
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
                    updateDoorNumber(addressId);
                    break;
                case "2": 
                    updateStreet(addressId);
                    break;
                case "3": 
                    updateLocality(addressId);
                    break;
                case "4": 
                    updateCity(addressId);
                    break;
                case "5": 
                    updateState(addressId);
                    break;
                case "6": 
                    updateCountry(addressId);
                    break;
                case "7": 
                    updatePinCode(addressId);
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
     * Gets door number from the user and updates the door number of the
     * specified address.
     *
     * @param addressId the id of the address to be updated.
     */
    private void updateDoorNumber(int addressId) {
        String doorNumber = getDoorNumberInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (addressController.updateDoorNumber(addressId, doorNumber)) {      
                System.out.println("\n\t\t\t<<<<<< Door Number Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets street from the user and updates the street of the
     * specified address.
     *
     * @param addressId the id of the address to be updated.
     */
    private void updateStreet(int addressId) {
        String street = getStreetInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (addressController.updateStreet(addressId, street)) {      
                System.out.println("\n\t\t\t<<<<<< Street Updated Successfully!"
                                   + " >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets locality from the user and updates the locality of the
     * specified address.
     *
     * @param addressId the id of the address to be updated.
     */
    private void updateLocality(int addressId) {
        String locality = getLocalityInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (addressController.updateLocality(addressId, locality)) {      
                System.out.println("\n\t\t\t<<<<<< Locality Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets city from the user and updates the city of the
     * specified address.
     *
     * @param addressId the id of the address to be updated.
     */
    private void updateCity(int addressId) {
        String city = getCityInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (addressController.updateCity(addressId, city)) {      
                System.out.println("\n\t\t\t<<<<<< Locality Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets state from the user and updates the state of the
     * specified address.
     *
     * @param addressId the id of the address to be updated.
     */
    private void updateState(int addressId) {
        String state = getStateInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (addressController.updateState(addressId, state)) {      
                System.out.println("\n\t\t\t<<<<<< State Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets country from the user and updates the country of the
     * specified address.
     *
     * @param addressId the id of the address to be updated.
     */
    private void updateCountry(int addressId) {
        String country = getCountryInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (addressController.updateCountry(addressId, country)) {      
                System.out.println("\n\t\t\t<<<<<< Country Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets postal code from the user and updates the postal code of the
     * specified address.
     *
     * @param addressId the id of the address to be updated.
     */
    private void updatePinCode(int addressId) {
        String pinCode = getPinCodeInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (addressController.updatePinCode(addressId, pinCode)) {      
                System.out.println("\n\t\t\t<<<<<< Pin Code Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);  
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Shows the choices available for update all menu and gets the desired 
     * choice from the user. Executes the specified choice.
     *
     * @param employeeId the id of the employee whose details to be updated.
     */
    private void showUpdateAllChoices(int employeeId) {
        StringBuilder options;
        String errorMessage;
        String userChoice;

        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(80);
        options.append("\n\t\t\t\t\\ Update All Details Menu /\n\t\t\t\t ")
               .append("~~~~~~~~~~~~~\n\n\t\t1 => Update Employee's Details")
               .append("\t\t2 => Update Address Details\n\n\t\t")
               .append("3 => Return to Main Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1":
                    updateAllDetails(employeeId);
                    break;
                case "2":
                    updateAddressDetails(employeeId);
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
     * Gets all details of the employee from the user, validates and updates
     * all details. It does not check for employee existence.
     *
     * @param id employee id to be updated.
     */
    private void updateAllDetails(int id) {
        String name = getNameInput();
        String gender = getGenderInput();
        LocalDate dateOfBirth = getDateOfBirthInput();
        long mobileNumber = getMobileNumberInput();
        String email = getEmailInput();
        float salary = getSalaryInput();
        LocalDate dateOfJoining = getDateOfJoiningInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            if (employeeController.updateAllDetails(new EmployeeDTO(id, name, 
                    dateOfBirth, gender,mobileNumber, email, salary, 
                    dateOfJoining))) {
                System.out.println("\n\t\t\t<<<<<< Employee Details Updated "
                                   + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (SQLException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Asks user to select an address to update. If selected gets input from 
     * the user and updates all details of the specified address.
     *
     * @param employeeId the id of the employee whose address to be updated.
     */
    private void updateAddressDetails(int employeeId) {
        int addressId = showAndGetAddress(employeeId);
       
        if (0 >= addressId) {
            return;
        }
        String doorNumber = getDoorNumberInput();
        String street = getStreetInput();
        String locality = getLocalityInput();
        String city = getCityInput();
        String state = getStateInput();
        String country = getCountryInput();
        String pinCode = getPinCodeInput();
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        AddressDTO addressDTO = new AddressDTO(addressId, doorNumber, street, 
                                            locality, city, state, country, 
                                            pinCode, employeeId);
        try {
            if (addressController.updateAllDetails(addressDTO)) {
                System.out.println("\n\t\t\t<<<<<< Address Updated "         
                                       + "Successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (SQLException exception) {
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
               .append("Employee\n\n\t\t4 => Return To Main Menu\n\n\t\t")
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
                    deleteAllEmployee();
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
            return true;
        }
        System.out.println(messageForAbort);
        return false;
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
        } catch (SQLException exception) {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
        }
    }
    
    /**
     * Asks user to select the address to be deleted and deletes the specified
     * address.
     */
    private void deleteAddress() {
        int addressId;
        int employeeId = getIdInput();
        
        if (!isEmployeeExist(employeeId)) {
            return;
        }
        addressId = showAndGetAddress(employeeId);
        
        if (0 <= addressId) {
            if (askConfirmationToDelete()) {
                try {
                    if (addressController.deleteAddress(addressId)) {
                        System.out.println("\n\t\t\t<<<<<< Deleted Successfully"
                                           + "! >>>>>>\n");
                    } else {
                        System.out.println("\n\t\t\t<<<<<< Cannot Delete The "
                                           + "Address! >>>>>>\n");
                    }
                } catch (SQLException exception) {
                    System.out.println("\n\t\t\t<<<<<< An Error Occurred! "
                                       + ">>>>>>\n");
                }
            }
        }
    }
    
    /** 
     * Asks user for confirmation to delete all employee records and deletes all
     * employee records if database is not empty.
     */
    public void deleteAllEmployee() {
        if (askConfirmationToDelete()) {
            try {
                if (employeeController.deleteAllEmployee()) {
                    System.out.println("\n\t\t\t<<<<<< Deleted Successfully! "
                                       + ">>>>>>\n");
                } else {
                    System.out.println("\n\t\t\t<<<<<< An Error Occurred! "
                                       + ">>>>>>\n");
                }
            } catch (SQLException exception) {
                System.out.println("\n\t\t\t<<<<<< An Error Occurred! "
                                   + ">>>>>>\n");
            }          
        }
    }
}
