/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.dao;

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
     * Creates a new project record in the database.
     *
     * @param project the project to be inserted.
     * @return the project id.
     * @throws HibernateException if database access error occurs.
     */
    int insertProject(Project project) throws HibernateException;
}

