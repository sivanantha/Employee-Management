/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service;

import java.util.List;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.Status;

/**
 * The ProjectService interface provides methods for validations and
 * create, update, retrieve, delete operations for project management system.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public interface ProjectService {
    
    /**
     * Validates the project id.
     *
     * @param id the project id to be validated.
     * @return the id if it is valid, otherwise null.
     */
    Integer validateId(String id);
    
    /**
     * Checks if the specified project name is valid.
     *
     * @return true if project name is valid, otherwise false.
     */
    boolean isValidName(String name);
    
    /**
     * Validates the project name. If the name is valid removes the leading and
     * trailing spaces in the name.
     *
     * @param name the project name to be validated.
     * @return the name if it is valid, otherwise null.
     */
    String validateName(String name);
    
    /**
     * Checks if the specified description is valid. description must have 
     * at least 10 letters. maximum of 300 characters are allowed. all special
     * characters are considered as valid.
     *
     * @return true if description is valid, otherwise false.
     */
    boolean isValidDescription(String description);
    
    /**
     * Validates the project description.
     *
     * @param description the project description to be validated.
     * @return the description if it is valid, otherwise null.
     */
    String validateDescription(String description);
    
    /**
     * Validates the project manager name.
     *
     * @param manager the project manager name to be validated.
     * @return the project manager name if it is valid, otherwise null.
     */
    String validateManager(String manager);
    
    /**
     * Validates the project status.
     *
     * @param status the project status to be validated.
     * @return the project status if it is valid, otherwise null.
     */
    Status validateStatus(String status);
    
    /**
     * Checks if the project database is empty.
     *
     * @return true if project database is empty else false.
     * @throws EMSException if a database access error occurs.
     */
    boolean isProjectDatabaseEmpty() throws EMSException;
    
    /**
     * Searches for the specified project id.
     * 
     * @param id the id of the project to be searched as a integer.
     * @return true if project found, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    boolean isProjectExist(int id) throws EMSException;
    
    /**
     * @see EmployeeService#getAllEmployees()
     *
     */
    List<EmployeeDTO> getAllEmployees() throws EMSException;
    
    /**
     * Creates a new project record in the database.
     *
     * @param project the project to be inserted.
     * @return the project id.
     * @throws EMSException if database access error occurs.
     */
    int createProject(ProjectDTO projectDTO) throws EMSException;
    
    /**
     * Retrieves the specified project from the database.
     * 
     * @param id the project id to be retrieved as a int.
     * @return the specified project if found otherwise null.
     * @throws EMSException if a database access error occurs.
     */
     ProjectDTO getProject(int id) throws EMSException;
    
    /** 
     * Retrieves all projects from the database.
     *
     * @return a List containing all projects.
     * @throws EMSException if a database access error occurs.
     */
    List<ProjectDTO> getAllProjects() throws EMSException;
    
    /**
     * Updates the specified project.
     *
     * @param projectDTO the ProjectDTO instance with updated details.
     * @return true if updated successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    boolean updateProject(ProjectDTO projectDTO) throws EMSException;
    
    /**
     * Deletes the specified project.
     *
     * @param id the project's id to be deleted.
     * @return true if deleted successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    boolean deleteProject(int id) throws EMSException;
    
    /**
     * Deletes all the projects.
     *
     * @return true if deleted successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    boolean deleteAllProjects() throws EMSException;
}
