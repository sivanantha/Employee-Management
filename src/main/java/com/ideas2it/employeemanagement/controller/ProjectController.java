/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.Status;
import com.ideas2it.employeemanagement.service.ProjectService;
import com.ideas2it.employeemanagement.service.impl.ProjectServiceImpl;

/**
 * The ProjectController servlet class contains CRUD implementations for project
 * management system. It has useful methods for create, update, retrieve, delete
 * and validate project details.
 *
 * @author Sivanantham
 * @version 1.2
 */
public class ProjectController extends HttpServlet {
    private static final long serialVersionUID = 4671134463547248220L;
    private ProjectService projectService = new ProjectServiceImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        try {

            switch (path) {
                case "/createProjectForm":
                    sendProjectCreateForm(request, response);
                    break;
                case "/updateProjectForm":
                    sendProjectUpdateForm(request, response);
                    break;
                case "/viewAllProjects":
                    getAllProjects(request, response);
                    break;
                case "/assignEmployeesForm":
                    sendAssignEmployeesForm(request, response);
                    break;
                case "/unAssignEmployeesForm":
                    sendUnAssignEmployeesForm(request, response);
                    break;
                case "/deleteProject":
                    deleteProject(request, response);
                    break;
                case "/deleteAllProjects":
                    deleteAllProjects(request, response);
                    break;
                default:
                    request.getRequestDispatcher("Error.jsp").forward(request,
                            response);
                    break;
            }
        } catch (EMSException exception) {
            request.setAttribute("exception", exception);
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        } catch (NullPointerException | NumberFormatException exception) {
            request.setAttribute("errorMessage",
                    "Cannot Process The Request Due To Invalid Data!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        try {

            switch (path) {
                case "/createProject":
                    createProject(request, response);
                    break;
                case "/updateProject":
                    updateProject(request, response);
                    break;
                case "/assignEmployees":
                    assignEmployees(request, response);
                    break;
                case "/unAssignEmployees":
                    unAssignEmployees(request, response);
                    break;
                default:
                    request.getRequestDispatcher("Error.jsp").forward(request,
                            response);
                    break;
            }
        } catch (EMSException exception) {
            request.setAttribute("exception", exception);
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        } catch (NullPointerException | NumberFormatException exception) {
            request.setAttribute("errorMessage",
                    "Cannot Process The Request Due To Invalid Data!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }

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
     * Sends the project create form as the response to the requested client.
     * 
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void sendProjectCreateForm(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("formAction", "createProject");
        request.getRequestDispatcher("ProjectForm.jsp").forward(request,
                response);
    }

    /**
     * Creates a new project with specified details received from the request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void createProject(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        ProjectDTO project = new ProjectDTO(
                validateName(request.getParameter("name")),
                validateDescription(request.getParameter("description")),
                validateManager(request.getParameter("manager")),
                validateStatus(request.getParameter("status")));
        int id = projectService.createProject(project);

        if (0 != id) {
            request.setAttribute("redirectUrl", "index.jsp");
            request.setAttribute("successMessage",
                    "Project Created Successfully! The Project ID of "
                            + project.getName() + " is " + id
                            + " Redirecting To Home Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "Project Creation Failed! Please Try Again After Some Time!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
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
     * Handles the view all projects request.Retrieves all projects and sends
     * the response.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void getAllProjects(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        request.setAttribute("projects", projectService.getAllProjects());
        request.getRequestDispatcher("ViewAllProjects.jsp").forward(request,
                response);

    }

    /**
     * Sends the project update form with specified project's details as the
     * response.
     * 
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void sendProjectUpdateForm(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProjectDTO project = getProject(id);

        if (null == project) {
            request.setAttribute("errorMessage", "Project Not Found!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        request.getSession().setAttribute("projectToUpdate", project);
        request.setAttribute("formAction", "updateProject");
        request.setAttribute("project", project);
        request.getRequestDispatcher("ProjectForm.jsp").forward(request,
                response);
    }

    /**
     * Updates the specified project with the details received from the request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void updateProject(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {
        ProjectDTO project = (ProjectDTO) request.getSession()
                .getAttribute("projectToUpdate");

        if (null == project) {
            request.setAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        project.setName(validateName(request.getParameter("name")));
        project.setDescription(
                validateDescription(request.getParameter("description")));
        project.setManager(validateManager(request.getParameter("manager")));
        project.setStatus(validateStatus(request.getParameter("status")));

        if (projectService.updateProject(project)) {
            request.setAttribute("redirectUrl", "viewAllProjects");
            request.setAttribute("successMessage", "Project "
                    + project.getName()
                    + " Updated Successfully! Redirecting To Employees Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "Project " + project.getName() + " Update Failed!");
        }
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
     * Sends the assign employees form as the response for the requested
     * project.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void sendAssignEmployeesForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        List<EmployeeDTO> employees;
        HttpSession session = request.getSession();
        ProjectDTO project = getProject(
                Integer.parseInt(request.getParameter("id")));

        if (null == project) {
            request.setAttribute("errorMessage", "Project Not Found!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        session.setAttribute("projectToAssign", project);
        employees = getAllEmployees();
        employees.removeAll(project.getEmployees());
        request.setAttribute("employees", employees);
        request.setAttribute("formAction", "assignEmployees");
        request.getRequestDispatcher("AssignOrUnAssignEmployeesForm.jsp")
                .forward(request, response);
    }

    /**
     * Gets the selected employees from the request and returns the selected
     * employees in a list.
     * 
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @return a list containing selected employees, if no employee is selected
     *         then an empty list.
     */
    private List<EmployeeDTO> getSelectedEmployees(HttpServletRequest request,
            HttpServletResponse response) {
        EmployeeDTO employee;
        String[] employeeIds = request.getParameterValues("selectedEmployees");
        List<EmployeeDTO> employees = new ArrayList<>(
                (null == employeeIds) ? 0 : employeeIds.length);
       
        if (null != employeeIds) {
            for (String employeeId : employeeIds) {
                employee = new EmployeeDTO();
                employee.setId(Integer.parseInt(employeeId));
                employees.add(employee);
            }
        }
        return employees;
    }

    /**
     * Assigns the selected employees to the project specified in the request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void assignEmployees(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        HttpSession session = request.getSession();
        ProjectDTO project = (ProjectDTO) session
                .getAttribute("projectToAssign");

        if (null == project) {
            request.setAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        project.getEmployees().addAll(getSelectedEmployees(request, response));

        if (projectService.updateProject(project)) {
            request.setAttribute("redirectUrl", "viewAllProjects");
            request.setAttribute("successMessage",
                    "Employees Assigned Successfully! Redirecting To Projects page. . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "Assigning Employees To The Project Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
        session.removeAttribute("employeeToAssign");
    }

    /**
     * Sends the unassign employees form as the response for the requested
     * project.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void sendUnAssignEmployeesForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        ProjectDTO project;
        HttpSession session = request.getSession();
        project = getProject(Integer.parseInt(request.getParameter("id")));

        if (null == project) {
            request.setAttribute("errorMessage", "Project Not Found!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        session.setAttribute("projectToUnAssign", project);
        request.setAttribute("employees", project.getEmployees());
        request.setAttribute("formAction", "unAssignEmployees");
        request.getRequestDispatcher("AssignOrUnAssignEmployeesForm.jsp")
                .forward(request, response);
    }

    /**
     * UnAssigns the selected employees from the project specified in the
     * request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void unAssignEmployees(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        HttpSession session = request.getSession();
        ProjectDTO project = (ProjectDTO) session
                .getAttribute("projectToUnAssign");

        if (null == project) {
            request.setAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        project.getEmployees()
                .removeAll(getSelectedEmployees(request, response));

        if (projectService.updateProject(project)) {
            request.setAttribute("redirectUrl", "viewAllProjects");
            request.setAttribute("successMessage",
                    "Employees UnAssigned Successfully! Redirecting To Projects Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "UnAssigning Employees From The Project Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
        session.removeAttribute("projectToUnAssign");
    }

    /**
     * Deletes the project specified in the request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void deleteProject(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, IOException, ServletException {
        Integer id = validateId(request.getParameter("id"));

        if (null != id && projectService.deleteProject(id)) {
            request.setAttribute("redirectUrl", "viewAllProjects");
            request.setAttribute("successMessage",
                    "Project Deleted Successfully! Redirecting To Projects Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage", "Project Delete Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }

    /**
     * Deletes all the projects as specified by the client request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    public void deleteAllProjects(HttpServletRequest request,
            HttpServletResponse response)
            throws EMSException, ServletException, IOException {

        if (projectService.deleteAllProjects()) {
            request.setAttribute("redirectUrl", "index.jsp");
            request.setAttribute("successMessage",
                    "Projects Deleted Successfully! Redirecting To Home Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage", "Project Delete Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }
}