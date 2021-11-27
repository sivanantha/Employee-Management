/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.ProjectDAO;
import com.ideas2it.employeemanagement.dao.impl.ProjectDAOImpl;
import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;
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
 * @author Sivanantham
 * @version 1.1
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDAO projectDAO = new ProjectDAOImpl();
    private EMSLogger logger = new EMSLogger(ProjectService.class);

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
        return Pattern.matches("^(.{0,145}([A-Za-z] ?){10}.{0,145})$",
                description);
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
                ? manager.strip().toLowerCase()
                : null;
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public Status validateStatus(String status) {
        Status enumeratedStatus;

        try {
            enumeratedStatus = Status.fromStatus(status.toUpperCase());
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
    public boolean isProjectExist(int id) throws EMSException {
        logger.info("Checking if the project exist");
        return (null == projectDAO.getById(id)) ? false : true;
    }

    /**
     * {@inheritDoc}
     * 
     */
    public List<EmployeeDTO> getAllEmployees() throws EMSException {
        return new EmployeeServiceImpl().getAllEmployees();
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public int createProject(ProjectDTO projectDTO) throws EMSException {
        return projectDAO.insertProject(Mapper.toProject(projectDTO));
    }

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public ProjectDTO getProject(int id) throws EMSException {
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
    public List<ProjectDTO> getAllProjects() throws EMSException {
        List<Project> projects = projectDAO.getAllProjects();

        return toProjectDTO(projects);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean updateProject(ProjectDTO projectDTO) throws EMSException {
        return projectDAO.updateProject(Mapper.toProject(projectDTO));
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteProject(int id) throws EMSException {
        return projectDAO.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean deleteAllProjects() throws EMSException {
        return projectDAO.deleteAllProjects();
    }
}