/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
import java.util.Scanner;
 
import com.ideas2it.employeemanagement.view.EmployeeView;
import com.ideas2it.employeemanagement.view.ProjectView;

/**
 * The EmployeeManagement class contains CRUD implementations for employee 
 * management and project management system. An employee can have following   
 * details. A unique employee id, name, date of birth, a unique mobile number
 * and email, salary, date of joining, address, assigned projects.
 * Once a Employee id created it cannot be changed.
 *
 * @author  Sivanantham
 * @version 1.5
 */
public class EmployeeManagement {
    
    /**
     * Prints the welcome message and starts the employee management 
     * application.
     */   
    public static void showWelcomeMessage() {
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
    }
    
    /**
     * Prints available services in employee management application and asks 
     * the user to choose a service and executes the selected service.
     */
    private static void showServices() {
        String errorMessage;
        String userChoice;
        StringBuilder options;
        Scanner inputReader = new Scanner(System.in);
        
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(70);
        options.append("\n\t\t\t\t\\ Main Menu /\n\t\t\t\t ~~~~~~~~~~~~\n")
               .append("\n\t\t1 => Employees Menu\t\t2 => Projects ")
               .append("Menu\n\n\t\t3 => Exit\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    EmployeeView employeeView = new EmployeeView();
                    employeeView.showAndGetCRUDChoice();            
                    break;
                case "2": 
                    ProjectView projectView = new ProjectView();
                    projectView.showAndGetCRUDChoice();
                    break;
                case "3":  
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"3".equals(userChoice)); 
    }
    
    public static void main(String[] args) {
        showWelcomeMessage();
        showServices();
    }
}
