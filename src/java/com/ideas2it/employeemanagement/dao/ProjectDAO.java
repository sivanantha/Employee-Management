/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao;

import java.util.List;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.Project;

/**
 * This interface provides methods for create, update, view, delete, assign 
 * employees for project in database.
 * 
 * @author  Sivanantham
 * @version 1.1
 */
public interface ProjectDAO {
    
    /**
     * Calculates the project count.
     *
     * @return the project count.
     * @throws EMSException if a database access error occurs.
     */
    long getProjectCount() throws EMSException;
    
    /**
     * Creates a new project record in the database.
     *
     * @param project the project to be inserted.
     * @return the project id.
     * @throws EMSException if database access error occurs.
     */
    int insertProject(Project project) throws EMSException;
    
    /** 
     * Fetches the specified project's details.
     *
     * @param id the project's id as a integer to fetch the record. 
     * @return the specified project if found, otherwise null.
     * @throws EMSException if a database access error occurs.
     */
    Project getById(int id) throws EMSException;
    
    /** 
     * Fetches all projects details.
     * 
     * @return a List containing all projects.
     * @throws EMSException if a database access error occurs.
     */
    List<Project> getAllProjects() throws EMSException;
    
    /**
     * Updates the specified project.
     *
     * @param project the project with updated details.
     * @return true if updated successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    boolean updateProject(Project project) throws EMSException;
    
    /**
     * Deletes the specified project.
     *
     * @param id the project's id to be deleted.
     * @return true if deleted successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    boolean deleteById(int id) throws EMSException;
    
    /**
     * Deletes all the projects.
     *
     * @return true if deleted successfully, otherwise false.
     * @throws EMSException if a database access error occurs.
     */
    boolean deleteAllProjects() throws EMSException;
}
