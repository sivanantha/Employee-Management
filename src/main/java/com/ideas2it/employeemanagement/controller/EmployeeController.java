/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ideas2it.employeemanagement.exceptions.EMSException;
import com.ideas2it.employeemanagement.logger.EMSLogger;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.ProjectDTO;
import com.ideas2it.employeemanagement.service.EmployeeService;

/**
 * The EmployeeController servlet class contains CRUD implementations for
 * employee management system. It has useful methods for create, update,
 * retrieve, delete and validate employee details.
 *
 * @author Sivanantham
 * @version 1.9
 */
@Controller
@SessionAttributes("employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private static EMSLogger logger = new EMSLogger(EmployeeController.class);

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String index() {
        return "index.jsp";
    }

    /**
     * Validates and parses given employee id.
     * 
     * @param id the employee id to be validated.
     * @return employee id as a Integer if it is valid else null.
     */
    private Integer validateId(String id) {
        logger.debug("Validating Employee Id. . .");
        return employeeService.validateId(id);
    }

    /**
     * Validates the specified employee name and converts it to lowercase.
     * 
     * @param name the employee name to be validated.
     * @return employee name as a string if it is valid else null.
     */
    private String validateName(String name) {
        logger.debug("Validating Employee Name. . .");
        return employeeService.validateName(name);
    }

    /**
     * Validates and parses given employee date of birth.
     * 
     * @param dateOfBirth the employee date of birth to be validated.
     * @return employee date of birth as a LocalDate if it is valid else null.
     */
    private LocalDate validateDateOfBirth(String dateOfBirth) {
        logger.debug("Validating Employee Date Of Birth. . .");
        return employeeService.validateDateOfBirth(dateOfBirth);
    }

    /**
     * Validates the specified employee gender and converts it to lowercase.
     * 
     * @param gender the employee gender to be validated.
     * @return employee gender as a string if it is valid else null.
     */
    private String validateGender(String gender) {
        logger.debug("Validating Employee Gender. . .");
        return employeeService.validateGender(gender);
    }

    /**
     * Validates and parses given employee mobile number.
     * 
     * @param mobileNumber the employee mobile number to be validated.
     * @return employee mobile number as a Long if it is valid else null.
     */
    private Long validateMobileNumber(String mobileNumber) {
        logger.debug("Validating Employee Mobile Number. . .");
        return employeeService.validateMobileNumber(mobileNumber);
    }

    /**
     * Checks if the specified mobile number is exist.
     * 
     * @param mobileNumber the employee mobile number to be searched.
     * @return true if the mobile number found, else false.
     * @throws EMSException if a database access error occurs.
     */
    private boolean isMobileNumberExist(long mobileNumber) throws EMSException {
        logger.debug("Checking If Employee Mobile Number Is Unique . . .");
        return employeeService.isMobileNumberExist(mobileNumber);
    }

    /**
     * Validates the given employee email.
     * 
     * @param email the employee email to be validated.
     * @return employee email as a string if it is valid else null.
     */
    private String validateEmail(String email) {
        logger.debug("Validating Employee Email. . .");
        return employeeService.validateEmail(email);
    }

    /**
     * Checks if the specified email is exist.
     * 
     * @param email the employee email to be searched.
     * @return true if the email found, else false.
     * @throws EMSException if a database access error occurs.
     */
    private boolean isEmailExist(String email) throws EMSException {
        logger.debug("Checking If Employee Email Is Unique. . .");
        return employeeService.isEmailExist(email);
    }

    /**
     * Validates and parses given employee salary.
     * 
     * @param salary the employee salary to be validated.
     * @return employee salary as a Float if it is valid else null.
     */
    private Float validateSalary(String salary) {
        logger.debug("Validating Employee Salary. . .");
        return employeeService.validateSalary(salary);
    }

    /**
     * Validates and parses given employee date of joining.
     * 
     * @param dateOfJoining the employee date of joining to be validated.
     * @return employee date of joining as a LocalDate if it is valid else null.
     */
    private LocalDate validateDateOfJoining(String dateOfJoining) {
        logger.debug("Validating Employee Date Of Joining. . .");
        return employeeService.validateDateOfJoining(dateOfJoining);
    }

    /**
     * Validates the specified door number.
     *
     * @param doorNumber the door number to be validated.
     * @return true if it is valid, otherwise false.
     */
    private boolean isValidDoorNumber(String doorNumber) {
        logger.debug("Validating Door Number. . .");
        return employeeService.isValidDoorNumber(doorNumber);
    }

    /**
     * Validates the specified street name.
     * 
     * @param street the street name to be validated.
     * @return true if it is valid, otherwise false.
     */
    private boolean isValidStreet(String street) {
        logger.debug("Validating Street Name. . .");
        return employeeService.isValidStreet(street);
    }

    /**
     * Validates the specified locality/city/state/country name.
     *
     * @param locality the name of the locality/city/state/country to be
     *                 validated.
     * @return true if valid, otherwise false.
     */
    private boolean isValidPlaceName(String name) {
        logger.debug("Validating Place Names. . .");
        return employeeService.isValidPlaceName(name);
    }

    /**
     * Validates the specified pin code.
     *
     * @param pinCode the pin code to be validated as a string.
     * @return true if it is valid, otherwise false.
     */
    private boolean isValidPinCode(String pinCode) {
        logger.debug("Validating Pin Code. . .");
        return employeeService.isValidPinCode(pinCode);
    }

    /**
     * Sends the project create form as the response to the requested client.
     * 
     * @param model the Model object that contains the request attributes the
     *              client made of the servlet.
     * @return a String representing response jsp page name.
     */
    @GetMapping("createEmployeeForm")
    public String sendEmployeeCreateForm(Model model) {
        model.addAttribute("employee", new EmployeeDTO());
        model.addAttribute("formAction", "createEmployee");
        return "EmployeeForm.jsp";
    }

    /**
     * Creates a new employee with specified details received from the request.
     *
     * @param request     the WebRequest object that contains the request the
     *                    client made of the servlet.
     * @param employeeDTO the EmployeeDTO instance containing employee details
     *                    to create.
     * @return a String representing the response jsp page name.
     */
    @PostMapping("createEmployee")
    public String createEmployee(WebRequest request,
            @ModelAttribute("employee") EmployeeDTO employeeDTO) {
        int id;
        String response = "redirect:/Error.jsp";

        try {
            id = employeeService.createEmployee(employeeDTO);

            if (0 != id) {
                request.setAttribute("redirectUrl", "index.jsp",
                        RequestAttributes.SCOPE_REQUEST);
                request.setAttribute("successMessage",
                        "Employee Created Successfully!\nThe Id of "
                                + employeeDTO.getName() + " is " + id + ".",
                        RequestAttributes.SCOPE_REQUEST);
                response = "forward:/Success.jsp";
            } else {
                request.setAttribute("errorMessage",
                        "Employee Creation Failed! Please Try Again After Some Time!",
                        RequestAttributes.SCOPE_REQUEST);
            }
        } catch (EMSException exception) {
            logger.error("Employee Creation Failed!", exception);
            request.setAttribute("errorMessage",
                    "Employee Creation Failed! Please Try Again After Some Time!",
                    RequestAttributes.SCOPE_REQUEST);
        }
        return response;
    }

    /**
     * Retrieves the specified employee.
     * 
     * @param id the employee id to be retrieved as a int.
     * @return the specified employee if found, otherwise null.
     * @throws EMSException if a database access error occurs.
     */
    private EmployeeDTO getEmployee(int id) throws EMSException {
        logger.debug("Retrieving Specificied Employee . . .");
        return employeeService.getEmployee(id);
    }

    /**
     * Handles the view all employees request.Retrieves all employees and sends
     * the response.
     *
     * @param model    the Model object that contains the request parameters the
     *                 client made of the servlet.
     * @param redirect the RedirecctAttributes instance for adding error
     *                 message.
     * @return a String representing the response jsp page name.
     */
    @GetMapping("viewAllEmployees")
    public String getAllEmployees(Model model, RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";

        try {
            model.addAttribute("employees", employeeService.getAllEmployees());
            response = "ViewAllEmployees.jsp";
        } catch (EMSException exception) {
            logger.error("Retrieving Employees Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * Sends the employee update form with specified employee's details as the
     * response.
     * 
     * @param id       the id of the employee to be updated.
     * @param model    the Model object that contains the request parameters the
     *                 client made of the servlet.
     * @param redirect the RedirecctAttributes instance for adding error
     *                 message.
     * @return a String representing the response jsp page name.
     */
    @GetMapping("updateEmployeeForm")
    public String sendEmployeeUpdateForm(@RequestParam int id, Model model,
            RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";
        EmployeeDTO employee;

        try {
            employee = employeeService.getEmployee(id);

            if (null == employee) {
                redirect.addAttribute("errorMessage", "Employee Not Found!");
            }
            model.addAttribute("employee", employee);
            model.addAttribute("formAction", "updateEmployee");
            response = "EmployeeForm.jsp";
        } catch (EMSException exception) {
            logger.error("Update Employee Form Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * Updates the specified employee with the details received from the
     * request.
     *
     * @param employee the EmployeeDTO instance to be updated.
     * @param redirect the RedirecctAttributes instance for adding error or
     *                 success message.
     * @return a String representing the response jsp page name.
     */
    @PostMapping("updateEmployee")
    public String updateEmployee(
            @ModelAttribute("employee") EmployeeDTO employee,
            RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";

        try {

            if (employeeService.updateEmployee(employee)) {
                redirect.addAttribute("redirectUrl", "viewAllEmployees");
                redirect.addAttribute("successMessage",
                        "Employee Updated Successfully! Redirecting To Employees Page . . .");
                response = "redirect:/Success.jsp";
            } else {
                redirect.addAttribute("errorMessage",
                        "Employee Update Failed! Please Try Again After Some Time!");
            }
        } catch (EMSException exception) {
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * Fetches all available projects.
     *
     * @return a List containing all available projects.
     * @throws EMSException if a database access error occurs.
     */
    private List<ProjectDTO> getAllProjects() throws EMSException {
        logger.debug("Retrieving All Projects . . .");
        return employeeService.getAllProjects();
    }

    /**
     * Sends the assign projects form as the response for the requested
     * employee.
     *
     * @param id       the id of the employee to assign projects.
     * @param model    the Model object that contains the request parameters the
     *                 client made of the servlet.
     * @param redirect the RedirecctAttributes instance for adding error
     *                 message.
     * @return a String representing the response jsp page name.
     */
    @GetMapping("assignProjectsForm")
    public String sendAssignProjectsForm(@RequestParam int id, Model model,
            RedirectAttributes redirect) {
        List<ProjectDTO> projects;
        EmployeeDTO employee;
        String response = "redirect:/Error.jsp";

        try {
            employee = getEmployee(id);

            if (null == employee) {
                redirect.addAttribute("errorMessage", "Employee Not Found!");
            }
            model.addAttribute("employee", employee);
            projects = getAllProjects();
            projects.removeAll(employee.getProjects());
            model.addAttribute("projects", projects);
            model.addAttribute("formAction", "assignProjects");
            response = "AssignOrUnAssignProjectsForm.jsp";
        } catch (EMSException exception) {
            logger.error("Assign Employees Form Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * Assigns the specified employee to the selected projects in the request.
     *
     * @param projectIds a array of int containing selected project's id in the
     *                   request parameter.
     * @param employee   the EmployeeDTO instance to assign projects.
     * @param redirect   the RedirecctAttributes instance for adding error or
     *                   success message.
     * @return a String representing the response jsp page name.
     */
    @PostMapping("assignProjects")
    public String assignProjects(
            @RequestParam(name = "selectedProjects", required = false) int[] projectIds,
            @ModelAttribute("employee") EmployeeDTO employee,
            RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";

        if (null == employee) {
            redirect.addAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            response = "redirect:/Error.jsp";
        }
        employee.getProjects()
                .addAll(employeeService.getSelectedProjects(projectIds));

        try {

            if (employeeService.updateEmployee(employee)) {
                redirect.addAttribute("redirectUrl", "viewAllEmployees");
                redirect.addAttribute("successMessage",
                        "Projects Assigned Successfully! Redirecting To Employees page. . .");
                response = "redirect:/Success.jsp";
            } else {
                redirect.addAttribute("errorMessage",
                        "Assigning Projects To The Employee Failed!");
            }
        } catch (EMSException exception) {
            logger.error("Assign Projects Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * Sends the unAssign projects form as the response for the requested
     * employee.
     *
     * @param id       the id of the employee to unAssign projects.
     * @param model    the Model object that contains the request parameters the
     *                 client made of the servlet.
     * @param redirect the RedirecctAttributes instance for adding error
     *                 message.
     * @return a String representing the response jsp page name.
     */
    @GetMapping("unAssignProjectsForm")
    public String sendUnAssignProjectsForm(@RequestParam int id, Model model,
            RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";
        EmployeeDTO employee;

        try {
            employee = getEmployee(id);

            if (null == employee) {
                redirect.addAttribute("errorMessage", "Employee Not Found!");
            }
            model.addAttribute("employee", employee);
            model.addAttribute("projects", employee.getProjects());
            model.addAttribute("formAction", "unAssignProjects");
            response = "AssignOrUnAssignProjectsForm.jsp";
        } catch (EMSException exception) {
            logger.error("UnAssign Projects Form Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * UnAssigns the specified employee from the selected projects in the
     * request.
     * 
     * @param projectIds a array of int containing selected project's id in the
     *                   request parameter.
     * @param employee   the EmployeeDTO instance to unAssign projects.
     * @param redirect   the RedirecctAttributes instance for adding error or
     *                   success message.
     * @return a String representing the response jsp page name.
     */
    @PostMapping("unAssignProjects")
    public String unAssignProjects(
            @RequestParam(name = "selectedProjects", required = false) int[] projectIds,
            @ModelAttribute("employee") EmployeeDTO employee,
            RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";

        if (null == employee) {
            redirect.addAttribute("errorMessage",
                    "Session Expired! Please Try Again.");
            return "redirect:/Error.jsp";
        }

        try {
            employee.getProjects()
                    .removeAll(employeeService.getSelectedProjects(projectIds));

            if (employeeService.updateEmployee(employee)) {
                redirect.addAttribute("redirectUrl", "viewAllEmployees");
                redirect.addAttribute("successMessage",
                        "Projects UnAssigned Successfully! Redirecting To Employees page. . .");
                return "redirect:/Success.jsp";
            } else {
                redirect.addAttribute("errorMessage",
                        "UnAssigning Employee From The Projects Failed!");
            }
        } catch (EMSException exception) {
            logger.error("UnAssign Projects Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * Deletes the Employee specified in the request.
     *
     * @param id       the id of the employee to be deleted.
     * @param redirect the RedirecctAttributes instance for adding error or
     *                 success message.
     * @return a String representing the response jsp page name.
     */
    @GetMapping("deleteEmployee")
    public String deleteEmployee(@RequestParam int id,
            RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";

        try {

            if (employeeService.deleteEmployee(id)) {
                redirect.addAttribute("redirectUrl", "viewAllEmployees");
                redirect.addAttribute("successMessage",
                        "Employee Deleted Successfully! Redirecting To Employees Page . . .");
                response = "redirect:/Success.jsp";
            } else {
                redirect.addAttribute("errorMessage",
                        "Employee Delete Failed!");
            }
        } catch (EMSException exception) {
            logger.error("Delete Employee Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }

    /**
     * Deletes all the employees as specified by the client request.
     *
     * @param redirect the RedirecctAttributes instance for adding error or
     *                 success message.
     * @return a String representing the response jsp page name.
     */
    @GetMapping("deleteAllEmployees")
    public String deleteAllEmployees(RedirectAttributes redirect) {
        String response = "redirect:/Error.jsp";

        try {

            if (employeeService.deleteAllEmployees()) {
                redirect.addAttribute("redirectUrl", "index.jsp");
                redirect.addAttribute("successMessage",
                        "Employees Deleted Successfully! Redirecting To Home Page . . .");
                response = "redirect:/Success.jsp";
            } else {
                redirect.addAttribute("errorMessage",
                        "Employee Delete Failed!");
            }
        } catch (EMSException exception) {
            logger.error("Delete All Employees Failed!", exception);
            redirect.addAttribute("errorMessage", "Oops! An Error Occured!");
        }
        return response;
    }
}