/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.model;

import java.util.Set;

/**
 * This class is a contains the project details.
 *
 * @author  Sivanantham
 * @version 1.0
 */
 public class ProjectDTO {
    private int id;
    private Status status;
    private String name;
    private String description;
    private String manager;
    private Set<EmployeeDTO> employees;
    
    /** No parameter constructor  */
    public ProjectDTO() {
    
    }
    
    /**
     * Initializes the instance with specified values.
     *
     * @param id the project id.
     * @param name the project name.
     * @param description the project description.
     * @param manager the project manager name.
     * @param status the status of the project(DEVELOPMENT/TESTING/LIVE).
     * @param employees a Set of employees working in the specified project.
     */ 
    public ProjectDTO(int id, String name, String description, String manager,
                   Status status, Set<EmployeeDTO> employees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.status = status;
        this.employees = employees;
    }
    
    /**
     * Initializes the instance with specified values.
     *
     * @param name the project name.
     * @param description the project description.
     * @param manager the project manager name.
     * @param status the status of the project(DEVELOPMENT/TESTING/LIVE).
     * @param employees a Set of employees working in the specified project.
     */ 
    public ProjectDTO(String name, String description, String manager,
                      Status status, Set<EmployeeDTO> employees) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.status = status;
        this.employees = employees;
    }
    
    /**
     * Fetches the project id.
     *
     * @return the id of the project.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Assigns the project id.
     *
     * @param id the id to be assigned to the project.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Fetches the project name.
     *
     * @return the project name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Assigns the project name.
     *
     * @param name the project name.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Fetches the project description.
     *
     * @return the project description.
     */
    public String getDescription(String description) {
        return description;
    }
    
    /**
     * Assigns the project description.
     *
     * @param description the project description.
     */
    public void setDescription() {
        this.description = description;
    }
    
    /**
     * Fetches the project manager name.
     *
     * @return the project manager name.
     */
    public String getManager() {
        return manager;
    }
    
    /**
     * Assigns the project manager.
     *
     * @param manager the project manager name.
     */
    public void setManager(String manager) {
        this.manager = manager;
    }
    
    /**
     * Fetches the project status.
     *
     * @return the of the project status.
     */
    public Status getStatus() {
        return status;
    }
    
    /**
     * Assigns the project status.
     *
     * @param status the status of the project(DEVELOPMENT/TESTING/LIVE).
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    /**
     * Fetches the employees in the project.
     *
     * @return a Set of employees in the project.
     */
    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }
    
    /**
     * Assigns the employees in the project.
     *
     * @param employees  a set of employees to be assigned to the project.
     */
    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }
    
    /**
     * Provides this object in human readable form.
     *
     * @return a string which is description of the object
     */
    @Override
    public String toString() {
        StringBuilder description = new StringBuilder(300);
        
        description.append("\n\t\t Project Id    : ").append(id)
                   .append("\n\t\t\t Project Name : ").append(name)
                   .append("\n\n\t\t Description  : ").append(description)
                   .append("\n\n\t\t Manager      : ").append(manager)
                   .append("\n\n\t\t Status       : ").append(status)
                   .append("\n\n\t\t Employees    : ");
       
       for (EmployeeDTO employeeDTO : employees) {
            description.append(employeeDTO.getName()).append("\n\n\t\t\t\t\t ");
       }
       return description.append("\n").toString();
    }
}
