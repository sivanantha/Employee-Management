/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.util.List;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.Status;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImpl;

/**
 * The ProjectController class contains CRUD implementations for project 
 * management system. It has useful methods for create, update, retrieve, 
 * delete and validate project details.
 *
 * @author  Sivanantham
 * @version 1.1
 */
public class ProjectController {
    private ProjectService projectService = new ProjectServiceImpl();
    
    /**
     * Validates the project id.
     *
     * @param id the project id to be validated.
     * @return the id if it is valid, otherwise null.
     */
    public Integer validateId(String id) {
        return projectService.validateId(id);
    }
    
    /**
     * Validates the project name.
     *
     * @param name the project name to be validated.
     * @return the name if it is valid, otherwise null.
     */
    public String validateName(String name) {
        return projectService.validateName(name);
    }
    
    /**
     * Validates the project description.
     *
     * @param description the project description to be validated.
     * @return the description if it is valid, otherwise null.
     */
    public String validateDescription(String description) {
        return projectService.validateDescription(description);
    }
    
    /**
     * Validates the project manager name.
     *
     * @param manager the project manager name to be validated.
     * @return the project manager name if it is valid, otherwise null.
     */
    public String validateManager(String manager) {
        return projectService.validateManager(manager);
    }
    
    /**
     * Validates the project status.
     *
     * @param status the project status to be validated.
     * @return the project status if it is valid, otherwise null.
     */
    public Status validateStatus(String status) {
        return projectService.validateStatus(status);
    }
    
    /**
     * Checks if the specified project exist.
     *
     * @param id the project id to be searched.
     * @return true if specified project found, else false.
     * @throws EMSException if a database access error occurs.
     */
    public boolean isProjectExist(int id) throws EMSException {
        return projectService.isProjectExist(id);
    }
    
    /**
     * Checks if the project database is empty.
     * 
     * @return true if database is empty, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    public boolean isProjectDatabaseEmpty() throws EMSException {
        return projectService.isProjectDatabaseEmpty();
    }
    
    /**
     * Fetches all employees from the database.
     *
     * @return a list containing all employees if database is not empty, 
     *         otherwise an empty list.
     * @throws EMSException if a database access error occurs.
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        return projectService.getAllEmployees();
    }
    
    /**
     * Creates a new project record in the database.
     *
     * @param project the project to be inserted.
     * @return the project id.
     * @throws EMSException if database access error occurs.
     */
    public int createProject(ProjectDTO projectDTO) throws EMSException {
        return projectService.createProject(projectDTO);
    }
    
    /**
     * Retrieves the specified project.
     * 
     * @param id the project id to be retrieved as a int.
     * @return the specified project if found, otherwise null.
     * @throws EMSException if a database access error occurs.
     */
    public ProjectDTO getProject(int id) throws EMSException {
        return projectService.getProject(id);
    }
    
    /** 
     * Retrieves all projects.
     *
     * @return a List containing all projects.
     * @throws EMSException if a database access error occurs.
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        return projectService.getAllProjects();
    }
    
    /**
     * Updates the specified project.
     *
     * @param project the ProjectDTO instance with updated details.
     * @return true if updated successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    public boolean updateProject(ProjectDTO project) throws EMSException {
        return projectService.updateProject(project);
    }
    
    /**
     * Deletes the specified project.
     *
     * @param id the project's id to be deleted.
     * @return true if deleted successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    public boolean deleteProject(int id) throws EMSException {
        return projectService.deleteProject(id);
    }
    
    /**
     * Deletes all the projects.
     *
     * @return true if deleted successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    public boolean deleteAllProjects() throws EMSException {
        return projectService.deleteAllProjects();
    }
}
