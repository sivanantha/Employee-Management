/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.util.List;

import org.hibernate.HibernateException;

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
 * @version 1.0
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
     * Creates a new project record in the database.
     *
     * @param project the project to be inserted.
     * @return the project id.
     * @throws HibernateException if database access error occurs.
     */
    public int createProject(ProjectDTO projectDTO) throws HibernateException {
        return projectService.createProject(projectDTO);
    }
}
