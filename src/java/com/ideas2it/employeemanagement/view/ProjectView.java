/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.controller.ProjectController;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.Status;

/**
 * The ProjectView class contains view implementations for create, update,
 * retrieve, delete employee for project management. It has validation
 * methods for project details.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class ProjectView {
    private Scanner inputReader = new Scanner(System.in);
    private ProjectController projectController = new ProjectController();
    
    /**
     * Displays the available choices for project management and gets the choice
     * from the user. Executes the selected choice.
     */
    public void showAndGetProjectChoice() {
        String userChoice;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        StringBuilder choices = new StringBuilder(200).append("\n\t\t\t\t\\ ")
                .append("Projects Menu /\n\t\t\t\t ~~~~~~~~~~~\n\n\t\t1 => ")
                .append("Create Project\t\t2 => Go To View Menu\n\n\t\t3 => ")
                .append("Go To Update Menu\t\t4 => Go To Delete Menu\n\n\t\t")
                .append("5 => Exit\n\n\t\tEnter The Choice : ");
        
        do {
            System.out.print(choices);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1":
                    createProject();
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
     * Gets the project id from the user and validates the user input.
     *
     * @return the project id.
     */
    private int getIdInput() {
        String userInput;
        Integer id = null;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter A Valid Non Negative"
                              + " Integer Value And 0 Is Not Allowed! >>>>>>\n";
                              
        while (null == id) {
            System.out.print("\n\t\t Enter Project Id : ");
            userInput = inputReader.nextLine();
            id = projectController.validateId(userInput);
            
            if (null == id) {
                System.out.println(errorMessage);
            }
        }
        return id;
    }
    
    /**
     * Gets project name from the user for create, update operations and 
     * validates name. Returns the name in lowercase if it is valid.
     *
     * @return project name as string if valid.
     */
    private String getNameInput() {
        String userInput;
        String name = null;
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Name! >>>>"
                              + ">>\n";
        
        while (null == name) {
            System.out.print("\n\t\t Enter Project Name : ");
            userInput = inputReader.nextLine();
            name = projectController.validateName(userInput);
            
            if (null == name) {
                System.out.println(errorMessage);
            }
        }
        return name;
    }
    
    /**
     * Gets project description from the user and validates the description.
     *
     * @return the project description.
     */
    private String getDescriptionInput() {
        String userInput;
        String description = null;
        String errorMessage = "\n\t\t\t<<<<<< Description Must Be Atleast 10 "
                              + "Characters! >>>>>>\n";
        
        while (null == description) {
            System.out.print("\n\t\t Enter Project Description : ");
            userInput = inputReader.nextLine();
            description = projectController.validateDescription(userInput);
            
            if (null == description) {
                System.out.println(errorMessage);
            }
        }
        return description;
    }
    
    /**
     * Gets the project manager name from the user and validates the name.
     *
     * @return the project manager name.
     */
    private String getManagerInput() {
        String userInput;
        String manager = null;
        String errorMessage = "\n\t\t<<<<<< Please Enter Valid Name! >>>>>>\n";
        
        while (null == manager) {
            System.out.print("\n\t\t Enter Project Manager Name : ");
            userInput = inputReader.nextLine();
            manager = projectController.validateManager(manager);
            
            if (null == manager) {
                System.out.println(errorMessage);
            }
        }
        return manager;
    }
    
    /**
     * Gets project status from the user and validates the status.
     *
     * @return the project status.
     */
    private Status getStatusInput() {
        String userInput;
        Status status = null;
        String errorMessage = "\n\t\t<<<<<< Please Enter Valid Status! >>>>>"
                              + ">\n";
        
        while (null == status) {
            System.out.println("\n\t\t Enter Project Status : ");
            userInput = inputReader.nextLine();
            
            if (null == status) {
                System.out.println(errorMessage);
            }
        }
        return status;
    }
    
    /**
     * Shows all employees and asks user to select employees to assign them to 
     * the project.
     *
     * @return a set containing selected employees or if there is no employees
     *         in the database then an empty set.
     */ 
    private Set<EmployeeDTO> getEmployeesInput() {
        String[] selectedIds;
        String userInput;
        int count = 1;
        List<EmployeeDTO> employees = projectController.getAllEmployees();
        Set<EmployeeDTO> selectedEmployees = new HashSet<>();
        
        for (EmployeeDTO employee : employees) {
            System.out.println("\n\t\t" + count + " => " + employee.getName());
        }
        
        while (selectedEmployees.isEmpty() && (!employees.isEmpty())) {
            System.out.print("\n\t\tEnter Employees Numbers To Assign(separated"
                         + " by comma) (e.g) 1, 2, 3 : ");
            userInput = inputReader.nextLine();
            selectedIds = userInput.split(",");
            
            try {
                for (String id : selectedIds) {
                    selectedEmployees.add(employees.get(Integer.parseInt(
                            id.strip())));
                }
            } catch (NumberFormatException | IndexOutOfBoundsException 
                     exception) {
                System.out.println("\n\t\t<<<<<< Invalid Selection! >>>>>>>\n");
                selectedEmployees.clear();
            }
        }
        return selectedEmployees;
    }
    
    /**
     * Fetches all employees from the database.
     *
     * @return a list containing all employees if database is not empty, 
               otherwise an empty list.
     */
    private List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employees;
        
        try {
            employees = projectController.getAllEmployees();
            
            if (employees.isEmpty()) {
                System.out.println("\n\n\t\t\t<<<<<< No Employees Found To "
                                   + "Assign! >>>>>>\n");
            }
        } catch (HibernateException exception) {
            employees = new ArrayList<>();
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
        return employees;
    }
    
    /**
     * Gets project details from the user, validates and creates a new 
     * project.
     */
    private void createProject() {
        int id;
        String name = getNameInput();
        
        try {
            id = projectController.createProject(new ProjectDTO(name,
                            getDescriptionInput(), getManagerInput(),
                            getStatusInput(), getEmployeesInput()));
            
            System.out.println("\n\t\t\t<<<<<< Project Created Successfully! "
                               + ">>>>>>\n\n\t\t\t ****** The Project Id Of < "
                               + name + " > Is --> " + id + " ******");
       } catch (HibernateException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
       } 
    }
    
    /**
     * Checks if the project database is empty.
     *
     * @return true when database is empty or HibernateException occurs, 
     *         otherwise false.
     */
    private boolean isProjectDatabaseEmpty() {
        boolean isEmpty = false;
        
        try {
            if (projectController.isProjectDatabaseEmpty()) {
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
     * Checks if the specified project exist.
     *
     * @param id the project id to be searched.
     * @return false when project not found or HibernateException occurs,
     *         otherwise true.
     */
    private boolean isProjectExist(int id) {
        boolean isProjectFound = true;

        try {
            if (!projectController.isProjectExist(id)) {
                System.out.println("\n\n\t\t\t<<<<<< Employee Not Found! "
                                   + ">>>>>>\n");
                isProjectFound = false;
            }
        } catch (HibernateException exception) {
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
            isProjectFound = false;
        }
        return isProjectFound;
    }
    
    /**
     * Prints available choices for view operation and asks user to choose a
     * option and executes the selected option if database is not empty.
     */
    private void showAndGetViewChoice() {
        String errorMessage;
        String userChoice;
        StringBuilder options;
        
        if (isProjectDatabaseEmpty()) {
            return;
        }
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(70);
        options.append("\n\t\t\t\t\\ View Menu /\n\t\t\t\t ~~~~~~~~~~~~\n")
               .append("\n\t\t1 => View Single Project\t\t2 => View All ")
               .append("Projects\n\n\t\t3 => Return to Main Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    viewProject();
                    break;
                case "2": 
                    viewAllProjects();
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
     * Fetches specified project from the database.
     *
     * @param id the id of the project to be fetched.
     * @return the project if found, otherwise null.
     */
    private ProjectDTO getProject(int id) {
        ProjectDTO project;
        
        try {
            project = projectController.getProject(id);
            
            if (null == project) {
                System.out.println("\n\n\t\t\t<<<<<< Project Not Found! "
                                   + ">>>>>>\n");
            }
        } catch (HibernateException exception) {
            project = null;
            System.out.println("\n\n\t\t\t<<<<<< An Error Occurred! >>>>>>");
        }
        return project;
    }
    
    /**
     * Gets project id from the user and prints project details if the id is
     * valid.
     */
    private void viewProject() {
        int id = getIdInput();
        ProjectDTO project = getProject(id);
            
        if (null != project) {
            System.out.println("\n\t\t\t\t ~~~~~~~~PROJECT DETAILS~~~~~~~~\n"
                               + project);
        }
    }
   
    /** 
     * Checks if database is empty and prints all projects details if 
     * database is not empty.
     */
   private void viewAllProjects() {
        try {
            List<ProjectDTO> projects = projectController.getAllProjects();
            
            System.out.println("\n\t\t\t\t ~~~~~~ALL PROJECT DETAILS~~~~~~\n");
            
            for (ProjectDTO projectDTO : projects) {
                System.out.println(projectDTO);
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
        ProjectDTO project;
        
        if (isProjectDatabaseEmpty()) {
            return;
        }
        id = getIdInput();
        project = getProject(id);
        
        if (null == project) {
            return;
        }
        
        errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! >>>>>>\n";
        options = new StringBuilder(80);
        options.append("\n\t\t\t\t\\ Update Menu /\n\t\t\t\t ~~~~~~~~~~~~~\n")
               .append("\n\t\t1 => Update Single Detail\t\t2 => Update All ")
               .append("Details\n\n\t\t3 => Assign Employees\t\t\t4 => ")
               .append("Unassign Employees\n\n\t\t5 => Return to Main Menu\n\n")
               .append("\t\tEnter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1":
                    updateSingleField(project);
                    break;
                case "2": 
                    updateAllDetails(project);
                    break;
                case "3":
                    assignEmployees(project);
                    break;
                case "4":
                    unAssignEmployees(project);
                    break;
                default:  
                    System.out.println(errorMessage);
                    break;
            }
        } while (!"5".equals(userChoice)); 
    }
    
    /**
     * Prints choices available for update a project's single detail and gets 
     * the choice from the user. Executes the selected choice.
     *
     * @param project the project whose details to be updated.
     */
    private void updateSingleField(ProjectDTO project) {
        String userChoice;
        StringBuilder options = new StringBuilder(80);
        String errorMessage = "\n\t\t\t<<<<<< Please Enter Valid Option! "
                              + ">>>>>>\n";
        
        options.append("\n\t\t\t\\ Single Detail Update Menu /\n\t\t\t ~~~~~~")
               .append("~~~~~~~~~~~~~~~~~~~~~\n\t\t1 => Name\t\t\t2 => ")
               .append("Description\n\n\t\t3 => Manager\t\t\t4 => Status\n\n")
               .append("\t\t5 => Return To Previous Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    updateName(project);
                    break;
                case "2": 
                    updateDescription(project);
                    break;
                case "3": 
                    updateManager(project);
                    break;
                case "4":
                    updateStatus(project);
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
     * Gets project name from the user, validates and updates the project
     * name.
     *
     * @param project the project whose name to be updated.
     */
    private void updateName(ProjectDTO project) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            project.setName(getNameInput());
            
            if (projectController.updateProject(project)) {
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
     * Gets project description from the user, validates and updates the project
     * description.
     *
     * @param project the project whose description to be updated.
     */
    private void updateDescription(ProjectDTO project) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            project.setDescription(getDescriptionInput());
            
            if (projectController.updateProject(project)) {
                System.out.println("\n\t\t\t<<<<<< Description updated "
                                   + "successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets project manager name from the user, validates and updates the
     * project manager name.
     *
     * @param project the project whose manager name to be updated.
     */
    private void updateManager(ProjectDTO project) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            project.setManager(getManagerInput());
            
            if (projectController.updateProject(project)) {
                System.out.println("\n\t\t\t<<<<<< Manager updated "
                                   + "successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets project status from the user, validates and updates the
     * project status.
     *
     * @param project the project whose status to be updated.
     */
    private void updateStatus(ProjectDTO project) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            project.setStatus(getStatusInput());
            
            if (projectController.updateProject(project)) {
                System.out.println("\n\t\t\t<<<<<< Status updated "
                                   + "successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employees from the user to assign them to the specified project.
     *
     * @param project the project to assign employees.
     */
    private void assignEmployees(ProjectDTO project) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            project.setEmployees(getEmployeesInput());
            
            if (projectController.updateProject(project)) {
                System.out.println("\n\t\t\t<<<<<< Manager updated "
                                   + "successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets employees from the user to unassign them from the specified project.
     *
     * @param project the project to unassign employees.
     */
    private void unAssignEmployees(ProjectDTO project) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        try {
            project.setEmployees(getEmployeesInput());
            
            if (projectController.updateProject(project)) {
                System.out.println("\n\t\t\t<<<<<< Manager updated "
                                   + "successfully! >>>>>>\n");
            } else {
                System.out.println(errorMessage);
            }
        } catch (HibernateException exception) {
            System.out.println(errorMessage);
        }
    }
    
    /**
     * Gets all details of the project from the user validates 
     * and updates all details.
     *
     * @param project the project to be updated.
     */
    private void updateAllDetails(ProjectDTO project) {
        String errorMessage = "\n\t\t\t<<<<<< An Error Occurred! >>>>>>\n";
        
        project.setAll(getNameInput(), getDescriptionInput(), getManagerInput(), 
                getStatusInput());
        
        try {
            if (projectController.updateProject(project)) {
                System.out.println("\n\t\t\t<<<<<< Project Details Updated "
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
               .append("\n\t\t1 => Delete Single Project\t\t2 => Delete All")
               .append(" Projects\n\n\t\t3 => Return To Main Menu\n\n\t\t")
               .append("Enter The Option : ");
        
        do {
            if (isProjectDatabaseEmpty()) {
                return;
            }
            System.out.print(options);
            userChoice = inputReader.nextLine().strip();
            
            switch (userChoice) {
                case "1": 
                    deleteProject();
                    break;
                case "2": 
                    deleteAllProjects();
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
     * Gets project id from the user, validates id. Asks user for confirmation 
     * and deletes the project if confirmed.
     */
    public void deleteProject() {
        int id = getIdInput();
        
        if (!isProjectExist(id)) {
            return;
        }
        
        try {
            if (askConfirmationToDelete()) {
                if (projectController.deleteProject(id)) {
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
     * Asks user for confirmation to delete all employee records and deletes all
     * employee records if confirmed.
     */
    private void deleteAllProjects() {
        if (askConfirmationToDelete()) {
            try {
                if (projectController.deleteAllProjects()) {
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
