/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;

import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.Status;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.utils.Mapper;
import com.ideas2it.employeemanagement.utils.ValidationUtil;

/**
 * The ProjectServiceImpl class contains validations and implementations for 
 * create, update, retrieve, delete operations for project management system.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDAO projectDAO = new ProjectDAOImpl();
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Integer validateId(String id) {
        Integer parsedId = null;
        
        if (ValidationUtil.isValidId(id)) {
            try {
                parsedId = Integer.parseInt(id.strip());            
            } catch (NumberFormatException exception) {
                parsedId = null;     
            }
        }
        return parsedId;
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isValidName(String name) {
        return Pattern.matches("^[\\s]*([a-zA-Z]{3,60})[\\s]*$|^[\\s]*([a-zA-Z]"
                + "{3,30} [a-zA-Z]{2,30}){1,5}[\\s]*$", name);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public String validateName(String name) {
        return isValidName(name) ? name.strip() : null;
    }
    
    /**
     * {@inheritDoc}
     *
     */
    public boolean isValidDescription(String description) {
        return Pattern.matches("^(.{0,145}[A-Za-z]{10}.{0,145})$", description);
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public String validateDescription(String description) {
        return isValidDescription(description) ? description.strip() : null;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public String validateManager(String manager) {
        return ValidationUtil.isValidName(manager)
               ? manager.strip().toLowerCase() : null;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Status validateStatus(String status) {
        Status enumeratedStatus;
        
        try {
            enumeratedStatus = Status.fromStatus(status);
        } catch (IllegalArgumentException exception) {
            enumeratedStatus = null;
        }
        return enumeratedStatus;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isProjectExist(int id) throws HibernateException {
        return (null == projectDAO.getById(id)) ? false : true;
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isProjectDatabaseEmpty() throws HibernateException {
        return (0 == projectDAO.getProjectCount());
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    public List<EmployeeDTO> getAllEmployees() throws HibernateException {
        return new EmployeeServiceImpl().getAllEmployees();
    }
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int createProject(ProjectDTO projectDTO) throws HibernateException {
        return projectDAO.insertProject(Mapper.toProject(projectDTO));
    }
    
    /** 
     * {@inheritDoc}
     * 
     */
    @Override
    public ProjectDTO getProject(int id) throws HibernateException {
        Project project = projectDAO.getById(id);
        
        return (null == project) ? null : Mapper.toProjectDTO(project);
    }
    
    /**
     * Maps list of Project instances to ProjectDTO instances.
     *
     * @param projects a List containing projects.
     * @return a List containing mapped ProjectDTO instances.
     */
    private List<ProjectDTO> toProjectDTO(List<Project> projects) {
        List<ProjectDTO> projectsDTO = new ArrayList<>(projects.size());
        
        for (Project project : projects) {
            projectsDTO.add(Mapper.toProjectDTO(project));
        }
        return projectsDTO;
    }
    
    /** 
     * {@inheritDoc}
     * 
     */
    @Override
    public List<ProjectDTO> getAllProjects() throws HibernateException {
        List<Project> projects = projectDAO.getAllProjects();
        
        return toProjectDTO(projects);
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean updateProject(ProjectDTO projectDTO) throws 
            HibernateException {
        return projectDAO.updateProject(Mapper.toProject(projectDTO));
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteProject(int id) throws HibernateException {
        return projectDAO.deleteById(id);
    }
    
    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteAllProjects() throws HibernateException {
        return projectDAO.deleteAllProjects();
    }
}
