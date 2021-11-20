/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;
import com.ideas2it.employeemanagement.service.impl.EmployeeServiceImpl;

/**
 * The EmployeeController servlet class contains CRUD implementations for
 * employee management system. It has useful methods for create, update,
 * retrieve, delete and validate employee details.
 *
 * @author Sivanantham
 * @version 1.9
 */
public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = -2737822833855695206L;
    private EmployeeService employeeService = new EmployeeServiceImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        try {

            switch (path) {
                case "/createEmployeeForm":
                    sendEmployeeCreateForm(request, response);
                    break;
                case "/updateEmployeeForm":
                    sendEmployeeUpdateForm(request, response);
                    break;
                case "/viewAllEmployees":
                    getAllEmployees(request, response);
                    break;
                case "/assignProjectsForm":
                    sendAssignProjectsForm(request, response);
                    break;
                case "/unAssignProjectsForm":
                    sendUnAssignProjectsForm(request, response);
                    break;
                case "/deleteEmployee":
                    deleteEmployee(request, response);
                    break;
                case "/deleteAllEmployees":
                    deleteAllEmployees(request, response);
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
                case "/createEmployee":
                    createEmployee(request, response);
                    break;
                case "/updateEmployee":
                    updateEmployee(request, response);
                    break;
                case "/assignProjects":
                    assignProjects(request, response);
                    break;
                case "/unAssignProjects":
                    unAssignProjects(request, response);
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
        } catch (NullPointerException | NumberFormatException
                | DateTimeParseException exception) {
            request.setAttribute("errorMessage",
                    "Cannot Process The Request Due To Invalid Data!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }

    /**
     * Validates and parses given employee id.
     * 
     * @param id the employee id to be validated.
     * @return employee id as a Integer if it is valid else null.
     */
    public Integer validateId(String id) {
        return employeeService.validateId(id);
    }

    /**
     * Validates the specified employee name and converts it to lowercase.
     * 
     * @param name the employee name to be validated.
     * @return employee name as a string if it is valid else null.
     */
    public String validateName(String name) {
        return employeeService.validateName(name);
    }

    /**
     * Validates and parses given employee date of birth.
     * 
     * @param dateOfBirth the employee date of birth to be validated.
     * @return employee date of birth as a LocalDate if it is valid else null.
     */
    public LocalDate validateDateOfBirth(String dateOfBirth) {
        return employeeService.validateDateOfBirth(dateOfBirth);
    }

    /**
     * Validates the specified employee gender and converts it to lowercase.
     * 
     * @param gender the employee gender to be validated.
     * @return employee gender as a string if it is valid else null.
     */
    public String validateGender(String gender) {
        return employeeService.validateGender(gender);
    }

    /**
     * Validates and parses given employee mobile number.
     * 
     * @param mobileNumber the employee mobile number to be validated.
     * @return employee mobile number as a Long if it is valid else null.
     */
    public Long validateMobileNumber(String mobileNumber) {
        return employeeService.validateMobileNumber(mobileNumber);
    }

    /**
     * Checks if the specified mobile number is exist.
     * 
     * @param mobileNumber the employee mobile number to be searched.
     * @return true if the mobile number found, else false.
     * @throws EMSException if a database access error occurs.
     */
    public boolean isMobileNumberExist(long mobileNumber) throws EMSException {
        return employeeService.isMobileNumberExist(mobileNumber);
    }

    /**
     * Validates the given employee email.
     * 
     * @param email the employee email to be validated.
     * @return employee email as a string if it is valid else null.
     */
    public String validateEmail(String email) {
        return employeeService.validateEmail(email);
    }

    /**
     * Checks if the specified email is exist.
     * 
     * @param email the employee email to be searched.
     * @return true if the email found, else false.
     * @throws EMSException if a database access error occurs.
     */
    public boolean isEmailExist(String email) throws EMSException {
        return employeeService.isEmailExist(email);
    }

    /**
     * Validates and parses given employee salary.
     * 
     * @param salary the employee salary to be validated.
     * @return employee salary as a Float if it is valid else null.
     */
    public Float validateSalary(String salary) {
        return employeeService.validateSalary(salary);
    }

    /**
     * Validates and parses given employee date of joining.
     * 
     * @param dateOfJoining the employee date of joining to be validated.
     * @return employee date of joining as a LocalDate if it is valid else null.
     */
    public LocalDate validateDateOfJoining(String dateOfJoining) {
        return employeeService.validateDateOfJoining(dateOfJoining);
    }

    /**
     * Validates the specified door number.
     *
     * @param doorNumber the door number to be validated.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidDoorNumber(String doorNumber) {
        return employeeService.isValidDoorNumber(doorNumber);
    }

    /**
     * Validates the specified street name.
     * 
     * @param street the street name to be validated.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidStreet(String street) {
        return employeeService.isValidStreet(street);
    }

    /**
     * Validates the specified locality/city/state/country name.
     *
     * @param locality the name of the locality/city/state/country to be
     *                 validated.
     * @return true if valid, otherwise false.
     */
    public boolean isValidPlaceName(String name) {
        return employeeService.isValidPlaceName(name);
    }

    /**
     * Validates the specified pin code.
     *
     * @param pinCode the pin code to be validated as a string.
     * @return true if it is valid, otherwise false.
     */
    public boolean isValidPinCode(String pinCode) {
        return employeeService.isValidPinCode(pinCode);
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
    public void sendEmployeeCreateForm(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("formAction", "createEmployee");
        request.getRequestDispatcher("EmployeeForm.jsp").forward(request,
                response);
    }

    /**
     * Creates a new AddressDTO with specified details received from the
     * request.
     *
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @return a List containing the AddressDTO instances.
     * @throws EMSException     if database access error occurs.
     * @throws IOException      if an input or output error occurs while the
     *                          servlet is handling the request.
     * @throws ServletException if the request cannot be handled.
     */
    private List<AddressDTO> createAddressList(HttpServletRequest request,
            HttpServletResponse response) throws EMSException {
        List<AddressDTO> addresses = new ArrayList<>();
        String addressIdString = request.getParameter("addressId");
        int addressId = (null == addressIdString) ? 0
                : Integer.parseInt(addressIdString.strip());
        String doorNumber = request.getParameter("doorNumber").strip();
        String street = request.getParameter("street").strip();
        String locality = request.getParameter("locality").strip();
        String city = request.getParameter("city").strip();
        String state = request.getParameter("state").strip();
        String country = request.getParameter("country").strip();
        String pinCode = request.getParameter("pinCode").strip();

        addresses.add(new AddressDTO(addressId, doorNumber, street, locality,
                city, state, country, pinCode));
        return addresses;
    }

    /**
     * Creates a new employee with specified details received from the request.
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
    public void createEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        int id;
        EmployeeDTO employeeDTO = new EmployeeDTO(
                validateName(request.getParameter("name")),
                validateDateOfBirth(request.getParameter("dateOfBirth")),
                validateGender(request.getParameter("gender")),
                validateMobileNumber(request.getParameter("mobileNumber")),
                validateEmail(request.getParameter("email")),
                validateSalary(request.getParameter("salary")),
                validateDateOfJoining(request.getParameter("dateOfJoining")),
                createAddressList(request, response));
        id = employeeService.createEmployee(employeeDTO);

        if (0 != id) {
            request.setAttribute("redirectUrl", "index.jsp");
            request.setAttribute("successMessage",
                    "Employee Created Successfully!\nThe Id of "
                            + employeeDTO.getName() + " is " + id + ".");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "Employee Creation Failed! Please Try Again After Some Time!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }

    /**
     * Retrieves the specified employee.
     * 
     * @param id the employee id to be retrieved as a int.
     * @return the specified employee if found, otherwise null.
     * @throws EMSException     if a database access error occurs.
     * @throws IOException
     * @throws ServletException
     */
    private EmployeeDTO getEmployee(int id)
            throws EMSException, ServletException, IOException {
        return employeeService.getEmployee(id);
    }

    /**
     * Handles the view all employees request.Retrieves all employees and sends
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
    public void getAllEmployees(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        request.setAttribute("employees", employeeService.getAllEmployees());
        request.getRequestDispatcher("ViewAllEmployees.jsp").forward(request,
                response);
    }

    /**
     * Sends the employee update form with specified employee's details as the
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
    public void sendEmployeeUpdateForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        int id = Integer.parseInt(request.getParameter("id"));
        EmployeeDTO employee = employeeService.getEmployee(id);

        if (null == employee) {
            request.setAttribute("errorMessage", "Employee Not Found!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        request.getSession().setAttribute("employeeToUpdate", employee);
        request.setAttribute("employee", employee);
        request.setAttribute("formAction", "updateEmployee");
        request.getRequestDispatcher("EmployeeForm.jsp").forward(request,
                response);
    }

    /**
     * Updates the specified employee with the details received from the
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
    public void updateEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        EmployeeDTO employee = (EmployeeDTO) request.getSession()
                .getAttribute("employeeToUpdate");

        if (null == employee) {
            request.setAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        employee.setName(validateName(request.getParameter("name")));
        employee.setDateOfBirth(
                validateDateOfBirth(request.getParameter("dateOfBirth")));
        employee.setGender(validateGender(request.getParameter("gender")));
        employee.setMobileNumber(
                validateMobileNumber(request.getParameter("mobileNumber")));
        employee.setEmail(validateEmail(request.getParameter("email")));
        employee.setSalary(validateSalary(request.getParameter("salary")));
        employee.setDateOfJoining(
                validateDateOfJoining(request.getParameter("dateOfJoining")));
        employee.setAddresses(createAddressList(request, response));

        if (employeeService.updateEmployee(employee)) {
            request.setAttribute("redirectUrl", "viewAllEmployees");
            request.setAttribute("successMessage",
                    "Employee Updated Successfully! Redirecting To Employees Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "Employee Update Failed! Please Try Again After Some Time!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }

    /**
     * Fetches all available projects.
     *
     * @return a List containing all available projects.
     * @throws EMSException if a database access error occurs.
     */
    public List<ProjectDTO> getAllProjects() throws EMSException {
        return employeeService.getAllProjects();
    }

    /**
     * Sends the assign projects form as the response for the requested
     * employee.
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
    public void sendAssignProjectsForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        List<ProjectDTO> projects;
        EmployeeDTO employee = getEmployee(
                Integer.parseInt(request.getParameter("id")));

        if (null == employee) {
            request.setAttribute("errorMessage", "Employee Not Found!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        request.getSession().setAttribute("employeeToAssign", employee);
        projects = getAllProjects();
        projects.removeAll(employee.getProjects());
        request.setAttribute("projects", projects);
        request.setAttribute("formAction", "assignProjects");
        request.getRequestDispatcher("AssignOrUnAssignProjectsForm.jsp")
                .forward(request, response);
    }

    /**
     * Gets the selected projects from the request and returns the selected
     * projects in a list.
     * 
     * @param request  the HttpServletRequest object that contains the request
     *                 the client made of the servlet.
     * @param response the HttpServletResponse object that contains the response
     *                 the servlet returns to the client.
     * @return a list containing selected employees, if no employee is selected
     *         then an empty list.
     */
    private List<ProjectDTO> getSelectedProjects(HttpServletRequest request,
            HttpServletResponse response) {
        ProjectDTO project;
        String[] projectIds = request.getParameterValues("selectedProjects");
        List<ProjectDTO> projects = new ArrayList<>(
                (null == projectIds) ? 0 : projectIds.length);

        for (String projectId : projectIds) {
            project = new ProjectDTO();
            project.setId(Integer.parseInt(projectId));
            projects.add(project);
        }
        return projects;
    }

    /**
     * Assigns the specified employee to the selected projects in the request.
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
    public void assignProjects(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        HttpSession session = request.getSession();
        EmployeeDTO employee = (EmployeeDTO) session
                .getAttribute("employeeToAssign");

        if (null == employee) {
            request.setAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        employee.getProjects().addAll(getSelectedProjects(request, response));

        if (employeeService.updateEmployee(employee)) {
            request.setAttribute("redirectUrl", "viewAllEmployees");
            request.setAttribute("successMessage",
                    "Projects Assigned Successfully! Redirecting To Employees page. . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "Assigning Projects To The Employee Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
        session.removeAttribute("employeeToAssign");
    }

    /**
     * Sends the unAssign projects form as the response for the requested
     * employee.
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
    public void sendUnAssignProjectsForm(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        EmployeeDTO employee = getEmployee(
                Integer.parseInt(request.getParameter("id")));

        if (null == employee) {
            request.setAttribute("errorMessage", "Employee Not Found!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        request.getSession().setAttribute("employeeToUnAssign", employee);
        request.setAttribute("projects", employee.getProjects());
        request.setAttribute("formAction", "unAssignProjects");
        request.getRequestDispatcher("AssignOrUnAssignProjectsForm.jsp")
                .forward(request, response);
    }

    /**
     * UnAssigns the specified employee from the selected projects in the
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
    public void unAssignProjects(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        HttpSession session = request.getSession();
        EmployeeDTO employee = (EmployeeDTO) session
                .getAttribute("employeeToUnAssign");

        if (null == employee) {
            request.setAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
            return;
        }
        employee.getProjects()
                .removeAll(getSelectedProjects(request, response));

        if (employeeService.updateEmployee(employee)) {
            request.setAttribute("redirectUrl", "viewAllEmployees");
            request.setAttribute("successMessage",
                    "Projects UnAssigned Successfully! Redirecting To Employees page. . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage",
                    "UnAssigning Employee From The Projects Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
        session.removeAttribute("employeeToUnAssign");
    }

    /**
     * Deletes the Employee specified in the request.
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
    public void deleteEmployee(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {
        int id = Integer.parseInt(request.getParameter("id"));

        if (employeeService.deleteEmployee(id)) {
            request.setAttribute("redirectUrl", "viewAllEmployees");
            request.setAttribute("successMessage",
                    "Employee Deleted Successfully! Redirecting To Employees Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage", "Employee Delete Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }

    /**
     * Deletes all the employees as specified by the client request.
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
    public void deleteAllEmployees(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, EMSException {

        if (employeeService.deleteAllEmployees()) {
            request.setAttribute("redirectUrl", "index.jsp");
            request.setAttribute("successMessage",
                    "Employees Deleted Successfully! Redirecting To Home Page . . .");
            request.getRequestDispatcher("Success.jsp").forward(request,
                    response);
        } else {
            request.setAttribute("errorMessage", "Employee Delete Failed!");
            request.getRequestDispatcher("Error.jsp").forward(request,
                    response);
        }
    }
}
