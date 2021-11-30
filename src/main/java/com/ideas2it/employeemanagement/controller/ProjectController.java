/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.model.Status;
import com.ideas2it.employeemanagement.service.ProjectService;

/**
 * The ProjectController servlet class contains CRUD implementations for project
 * management system. It has useful methods for create, update, retrieve, delete
 * and validate project details.
 *
 * @author Sivanantham
 * @version 1.2
 */
@Controller
@SessionAttributes("project")
public class ProjectController {
    private ProjectService projectService;
    private static EMSLogger logger = new EMSLogger(ProjectController.class);

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Validates the project id.
     *
     * @param id the project id to be validated.
     * @return the id if it is valid, otherwise null.
     */
    private Integer validateId(String id) {
        logger.debug("Validating Project Id . . .");
        return projectService.validateId(id);
    }

    /**
     * Validates the project name.
     *
     * @param name the project name to be validated.
     * @return the name if it is valid, otherwise null.
     */
    private String validateName(String name) {
        logger.debug("Validating Project Name . . .");
        return projectService.validateName(name);
    }

    /**
     * Validates the project description.
     *
     * @param description the project description to be validated.
     * @return the description if it is valid, otherwise null.
     */
    private String validateDescription(String description) {
        logger.debug("Validating Project Description . . .");
        return projectService.validateDescription(description);
    }

    /**
     * Validates the project manager name.
     *
     * @param manager the project manager name to be validated.
     * @return the project manager name if it is valid, otherwise null.
     */
    private String validateManager(String manager) {
        logger.debug("Validating Project Manager Name . . .");
        return projectService.validateManager(manager);
    }

    /**
     * Validates the project status.
     *
     * @param status the project status to be validated.
     * @return the project status if it is valid, otherwise null.
     */
    private Status validateStatus(String status) {
        logger.debug("Validating Project Status . . .");
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
    @GetMapping("createProjectForm")
    public String sendProjectCreateForm(Model model)
            throws ServletException, IOException {
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("formAction", "createProject");
        return "ProjectForm.jsp";
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
    @PostMapping("createProject")
    public String createProject(@ModelAttribute("project") ProjectDTO project,
            RedirectAttributes redirect) throws EMSException {
        int id = projectService.createProject(project);

        if (0 != id) {
            redirect.addAttribute("redirectUrl", "index.jsp");
            redirect.addAttribute("successMessage",
                    "Project Created Successfully! The Project ID of "
                            + project.getName() + " is " + id
                            + " Redirecting To Home Page . . .");
            return "redirect:/Success.jsp";
        } else {
            redirect.addAttribute("errorMessage",
                    "Project Creation Failed! Please Try Again After Some Time!");
            return "redirect:/Error.jsp";
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
        logger.debug("Retrieving Specified Project . . .");
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
    @GetMapping("viewAllProjects")
    public ModelAndView getAllProjects(WebRequest request) throws EMSException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projects", projectService.getAllProjects());
        modelAndView.setViewName("ViewAllProjects.jsp");
        return modelAndView;

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
    @GetMapping("updateProjectForm")
    public String sendProjectUpdateForm(@RequestParam int id, Model model,
            RedirectAttributes redirect) throws EMSException {
        ProjectDTO project = getProject(id);

        if (null == project) {
            redirect.addAttribute("errorMessage", "Project Not Found!");
            return "redirect:/Error.jsp";
        }
        model.addAttribute("formAction", "updateProject");
        model.addAttribute("project", project);
        return "ProjectForm.jsp";
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
    @PostMapping("updateProject")
    public String updateProject(@ModelAttribute("project") ProjectDTO project,
            RedirectAttributes redirect) throws EMSException {

        if (projectService.updateProject(project)) {
            redirect.addAttribute("redirectUrl", "viewAllProjects");
            redirect.addAttribute("successMessage", "Project "
                    + project.getName()
                    + " Updated Successfully! Redirecting To Employees Page . . .");
            return "redirect:/Success.jsp";
        } else {
            redirect.addAttribute("errorMessage",
                    "Project " + project.getName() + " Update Failed!");
            return "redirect:/Error.jsp";
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
        logger.debug("Retrieving All Employees . . .");
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
    @GetMapping("assignEmployeesForm")
    public String sendAssignEmployeesForm(@RequestParam int id, Model model,
            RedirectAttributes redirect)
            throws ServletException, IOException, EMSException {
        List<EmployeeDTO> employees;
        ProjectDTO project = getProject(id);

        if (null == project) {
            redirect.addAttribute("errorMessage", "Project Not Found!");
            return "redirect:/Error.jsp";
        }
        employees = getAllEmployees();
        employees.removeAll(project.getEmployees());
        model.addAttribute(project);
        model.addAttribute("employees", employees);
        model.addAttribute("formAction", "assignEmployees");
        return "AssignOrUnAssignEmployeesForm.jsp";
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
    @GetMapping()
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
    @GetMapping("deleteProject")
    public String deleteProject(@RequestParam int id,
            RedirectAttributes redirect) throws EMSException {

        if (projectService.deleteProject(id)) {
            redirect.addAttribute("redirectUrl", "viewAllProjects");
            redirect.addAttribute("successMessage",
                    "Project Deleted Successfully! Redirecting To Projects Page . . .");
            return "redirect:/Success.jsp";
        } else {
            redirect.addAttribute("errorMessage", "Project Delete Failed!");
            return "redirect:/Error.jsp";
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
    @GetMapping("deleteAllProjects")
    public String deleteAllProjects(RedirectAttributes redirect)
            throws EMSException, ServletException, IOException {

        if (projectService.deleteAllProjects()) {
            redirect.addAttribute("redirectUrl", "index.jsp");
            redirect.addAttribute("successMessage",
                    "Projects Deleted Successfully! Redirecting To Home Page . . .");
            return "redirect:/Success.jsp";
        } else {
            redirect.addAttribute("errorMessage", "Project Delete Failed!");
            return "redirec:/Error.jsp";
        }
    }
}