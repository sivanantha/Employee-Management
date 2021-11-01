/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.model.Project;

/**
 * This interface provides methods for create, update, view, delete, assign 
 * employees for project in database.
 * 
 *
 * @author  Sivanantham
 * @version 1.0
 */
public interface ProjectDAO {
    
    /**
     * Calculates the project count.
     *
     * @return the project count.
     * @throws HibernateException if a database access error occurs.
     */
    long getProjectCount() throws HibernateException;
    
    /**
     * Creates a new project record in the database.
     *
     * @param project the project to be inserted.
     * @return the project id.
     * @throws HibernateException if database access error occurs.
     */
    int insertProject(Project project) throws HibernateException;
    
    /** 
     * Fetches the specified project's details.
     *
     * @param id the project's id as a integer to fetch the record. 
     * @return the specified project if found, otherwise null.
     * @throws HibernateException if a database access error occurs.
     */
    Project getById(int id) throws HibernateException;
    
    /** 
     * Fetches all projects details.
     * 
     * @return a List containing all projects.
     * @throws HibernateException if a database access error occurs.
     */
    List<Project> getAllProjects() throws HibernateException;
}

