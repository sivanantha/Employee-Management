/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */

package com.ideas2it.employeemanagement.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.employeemanagement.controller.EmployeeController;

/**
 * The EmployeeView class contains view implementations for create, update,
 * retrieve, delete employee for employee management system.
 *
 * @author  Sivanantham
 * @version 1.4
 */
public class EmployeeView {
    private Scanner inputReader = new Scanner(System.in);
    private static EmployeeController employeeController = 
            new EmployeeController();
    
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
     * Gets employee details from the user, validates and creates a new 
     * employee.
     */
    private void createEmployee() {
        float salary;
        long mobileNumber;
        LocalDate dateOfBirth;
        LocalDate dateOfJoining;
        String email;
        String gender;
        String name;
        int id = getIdInput();
        
        if (employeeController.isEmployeeExist(id)) {
            System.out.println("\n\t\t\t<<<<<< Employee Id Already Exist! "
                               + ">>>>>\n");
            return; 
        }
        name = getNameInput();
        gender = getGenderInput();
        dateOfBirth = getDateOfBirthInput();
        mobileNumber = getMobileNumberInput();
        email = getEmailInput();
        salary = getSalaryInput();
        dateOfJoining = getDateOfJoiningInput();
        
        if (employeeController.createEmployee(id, name, dateOfBirth, gender, 
            mobileNumber, email, salary, dateOfJoining)) {      
            System.out.println("\n\t\t\t<<<<<< Employee Created Successfully! "
                               + ">>>>>>\n");
        } else {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
    }

    
    /**
     * Gets employee id from the user for creation, view, updation and deletion.
     * Also validates id.
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
        String messageForInvalid = "\n\t\t\t<<<<<< Mobile Number Must Be 10  "
                + "Digits! IST Codes Are Not Allowed. >>>>>>\n";
        String messageForDuplicate = "\n\t\t\t<<<<<< Mobile Number Already "
                                     + "Exist! >>>>>>";
                    
        while (null == mobileNumber) {
            System.out.print("\n\t\t Enter Employee Mobile Number : ");
            userInput = inputReader.nextLine();
            mobileNumber = employeeController.validateMobileNumber(userInput);
            
            if (null == mobileNumber) {
                System.out.println(messageForInvalid);
            } else if (employeeController.isMobileNumberExist(mobileNumber)) {
                System.out.println(messageForDuplicate);
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
            
            if (null == email) {
                System.out.println(messageForInvalid);
            } else if (employeeController.isEmailExist(email)) {
                email = null;
                System.out.println(messageForDuplicate);    
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
     * Prints available choices for view operation and asks user to choose a
     * option and executes the selected option if database is not empty.
     */
    private void showAndGetViewChoice() {
        StringBuilder options;
        String errorMessage;
        String userChoice;
        
        if (employeeController.isEmployeesDatabaseEmpty()) {
            System.out.println("\n\t\t\t<<<<<< Database Is Empty! >>>>>>\n");
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
        int id = getIdInput();
        
        if (employeeController.isEmployeeExist(id)) {
            System.out.println("\n\t\t\t\t ~~~~~~~EMPLOYEE DETAILS~~~~~~~\n"
                               + employeeController.getEmployee(id).get(0));
        } else { 
            System.out.println("\n\t\t\t<<<<<< Employee Not Found! >>>>>>\n");
        }
    }
    
    /** 
     * Checks if database is empty and prints all employee's details if 
     * database is not empty.
     */
    private void viewAllEmployee() {
        List employees = employeeController.getAllEmployees();
        
        System.out.println("\n\t\t\t\t ~~~~~~~ALL EMPLOYEE DETAILS~~~~~~~\n");
        
        for (int index = 0; index < employees.size(); index++) {
            System.out.println(employees.get(index));
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
        
        if (employeeController.isEmployeesDatabaseEmpty()) {
            System.out.println("\n\t\t\t<<<<<< Database Is Empty! >>>>>>\n");
            return;
        }
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(80);
        options.append("\n\t\t\t\t\\ Update Menu /\n\t\t\t\t ~~~~~~~~~~~~~\n")
               .append("\n\t\t1 => Update Single Detail\t\t2 => Update All ")
               .append("Details\n\n\t\t3 => Return to Main Menu\n\n\t\t")
               .append("Enter The Option : ");
        id = getIdInput();
        
        if (!employeeController.isEmployeeExist(id)) {
            System.out.println("\n<<<<<< Employee Not Found! >>>>>>\n");
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
                    updateAllDetails(id);
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
               .append("Joining\t\t8 => Return To Previous Menu\n\n\t\t")
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
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"8".equals(userChoice));
    }
    
    /**
     * Gets employee id, name from the user, validates and updates the employee
     * name if id is found.
     */
    private void updateName(int id) {
        String name = getNameInput();
            
        if (employeeController.updateName(id, name)) {
            System.out.println("\n\t\t\t<<<<<< Name updated successfully! "
                               + ">>>>>>\n");
        } else {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
        }
    }
    
    /**
     * Gets employee id, date of birth from the user, validates and updates the   
     * employee date of birth if employee id is found.
     */
    private void updateDateOfBirth(int id) { 
        LocalDate dateOfBirth = getDateOfBirthInput();    
        
        if (employeeController.updateDateOfBirth(id, dateOfBirth)) {
            System.out.println("\n\t\t\t<<<<<< Date Of Birth Updated "
                               + "Successfully! >>>>>>\n");
        } else {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
        }
    }
    
    /**
     * Gets employee id, gender from the user, validates and updates the   
     * employee gender if employee id is found.
     */
    private void updateGender(int id) {
        String gender = getGenderInput();

        if (employeeController.updateGender(id, gender)) {      
            System.out.println("\n\t\t\t<<<<<< Gender Updated Successfully!"
                               + " >>>>>>\n");
        } else {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");    
        }
    }
    
    /**
     * Gets employee id, mobile number from the user, validates and updates   
     * the employee gender if id is found.
     */
    private void updateMobileNumber(int id) {
        long mobileNumber = getMobileNumberInput();
            
        if (employeeController.updateMobileNumber(id, mobileNumber)) {
            System.out.println("\n\t\t\t<<<<<< Mobile Number Updated "
                                   + "Successfully! >>>>>>\n");
        } else { 
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
        }
    }
    
    /**
     * Gets employee id, email from the user, validates and updates   
     * the employee email if id is found.
     */
    private void updateEmail(int id) {
        String email = getEmailInput();
            
        if (employeeController.updateEmail(id, email)) {
            System.out.println("\n\t\t\t<<<<<< Email Updated Successfully! "
                               + ">>>>>>\n");
        } else {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");         
        }
    }
    
    /**
     * Gets employee id, salary from the user, validates and updates   
     * the employee salary if id is found.
     */
    private void updateSalary(int id) {
        float salary = getSalaryInput();
            
        if (employeeController.updateSalary(id, salary)) {
            System.out.println("\n\t\t\t<<<<< Salary Updated Successfully! "
                               + ">>>>>>\n");    
        } else {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
        }
    }
    
    /**
     * Gets employee id, date of joining from the user, validates and updates   
     * the employee date of joining if id is found.
     */
    private void updateDateOfJoining(int id) {
        LocalDate date = getDateOfJoiningInput();
            
        if (employeeController.updateDateOfJoining(id, date)) {
            System.out.println("\n\t\t\t<<<<<< Date Of Joining Updated " 
                               + "Successfully! >>>>>>\n");    
        } else {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");        
        }
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
        
        if (employeeController.updateAllDetails(id, name, dateOfBirth, gender, 
                mobileNumber, email, salary, dateOfJoining)) {
            System.out.println("\n\t\t\t<<<<<< Employee Details Updated "
                               + "Successfully! >>>>>>\n");
        } else {
            System.out.println("\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n");
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
               .append(" Employees\n\n\t\t3 => Return To Main Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
       
            if (employeeController.isEmployeesDatabaseEmpty()) {
                System.out.println("\n\t\t\t<<<<<< Database Is Empty!>>>>>>\n");
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
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"3".equals(userChoice)); 
    }
    
    /**
     * Gets employee id from the user, validates id. Asks user for confirmation 
     * and deletes the employee if found.
     */
    public void deleteEmployee() {
        int id = getIdInput();
        
        if (!employeeController.isEmployeeExist(id)) {
             System.out.println("\n\t\t\t<<<<<< Employee Not Found! >>>>>>\n");
             return;
        }

        if (askConfirmationToDelete()) {
             employeeController.deleteEmployee(id);
            System.out.println("\n\t\t\t<<<<<< Deleted Successfully! >>>>>>\n");
        }
    }
    
    /**
     * Asks user for confirmation to delete.
     * 
     * @return true if user's input is yes or false if user input is no.
     */
    private boolean askConfirmationToDelete() {
        String confirmationToDelete = null;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter 'Y' To Delete Or 'N'"
                              + " To Cancel And Return To Main Menu >>>>>>\n";
        String messageForAbort = "\n\t\t\t<<<<<< Aborted! Returning To Main"
                                 + " Menu... >>>>>>\n";                
        
        while (null == confirmationToDelete) {
            System.out.print("\n\t\t Do You Want To Delete ? (Y/N) ");
            confirmationToDelete = inputReader.nextLine().strip()
                                                         .toLowerCase();
            
            if (!("y".equals(confirmationToDelete)
                    || "n".equals(confirmationToDelete))) {
                confirmationToDelete = null;
                System.out.println(errorMessage);
            }
        }
        
        if ("y".equals(confirmationToDelete)) {
            return true;
        }
        System.out.println(messageForAbort);
        return false;
    }
    
    /** 
     * Asks user for confirmation to delete all employee records and deletes all
     * employee records if database is not empty.
     */
    public void deleteAllEmployee() {
       if (askConfirmationToDelete()) {
           employeeController.deleteAllEmployee();
           System.out.println("\n\t\t\t<<<<<< Deleted Successfully! >>>>>>\n");
       }
    }
}
