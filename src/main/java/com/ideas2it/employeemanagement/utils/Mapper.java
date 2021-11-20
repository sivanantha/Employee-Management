/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;
import com.ideas2it.employeemanagement.model.Project;
import com.ideas2it.employeemanagement.model.ProjectDTO;

/**
 * This mapper class maps employee model to employee DTO, Address to 
 * AddressDTO and vice versa.
 *
 * @author  Sivanantham
 * @version 1.2
 */
public final class Mapper {
    
    /**
     * Maps Employee object to EmployeeDTO object.
     * 
     * @param employee the Employee object to be mapped.
     * @return the mapped EmployeeDTO instance.
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        ProjectDTO projectDTO;
        Set<ProjectDTO> projectsDTO = new HashSet<>();
        Set<Project> projects = employee.getProjects();
        
        if (null != projects) { 
            for (Project project : projects) {
                projectDTO = new ProjectDTO();
                projectDTO.setId(project.getId());
                projectDTO.setName(project.getName());
                projectsDTO.add(projectDTO);
            }
        }
        return new EmployeeDTO(employee.getId(), employee.getName(),
                employee.getDateOfBirth(), employee.getGender(),
                employee.getMobileNumber(), employee.getEmail(), 
                employee.getSalary(), employee.getDateOfJoining(),
                toAddressDTO(employee.getAddresses()), projectsDTO);  
    }
    
    /**
     * Maps EmployeeDTO object to Employee object.
     * 
     * @param employeeDTO the EmployeeDTO object to be mapped.
     * @return the mapped Employee instance.
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
        Project project;
        Set<Project> projects = new HashSet<>();
        Set<ProjectDTO> projectsDTO = employeeDTO.getProjects();
        
        if (null != projectsDTO) { 
            for (ProjectDTO projectDTO : projectsDTO) {
                project = new Project();
                project.setId(projectDTO.getId());
                project.setName(projectDTO.getName());
                projects.add(project);
            }
        }
       return new Employee(employeeDTO.getId(), employeeDTO.getName(),
                employeeDTO.getDateOfBirth(), employeeDTO.getGender(),
                employeeDTO.getMobileNumber(), employeeDTO.getEmail(), 
                employeeDTO.getSalary(), employeeDTO.getDateOfJoining(),
                toAddress(employeeDTO.getAddresses()), projects); 
    }
    
    /**
     * Maps Address object to AddressDTO object.
     * 
     * @param address the Address object to be mapped.
     * @return the mapped AddressDTO instance.
     */
    public static AddressDTO  toAddressDTO(Address address) {
        return new AddressDTO(address.getId(), address.getDoorNumber(), 
                address.getStreet(), address.getLocality(), address.getCity(),
                address.getState(), address.getCountry(), address.getPinCode());
    }
    
    /**
     * Maps a list of Address objects to a list of AddressDTO objects.
     * 
     * @param addresses a list of Address objects to be mapped.
     * @return a list of mapped AddressDTO instances.
     */
    public static List<AddressDTO> toAddressDTO(List<Address> addresses) {
        List<AddressDTO> addressesDTO = new ArrayList<>();
        
        if (null != addresses) {
            for (Address address : addresses) {
                addressesDTO.add(toAddressDTO(address));
            }
        }
        return addressesDTO;
    }
    
    /**
     * Maps AddressDTO object to Address object.
     * 
     * @param addressDTO the AddressDTO object to be mapped.
     * @return the mapped Address instance.
     */
    public static Address toAddress(AddressDTO addressDTO) {
        return new Address(addressDTO.getId(), addressDTO.getDoorNumber(), 
                addressDTO.getStreet(), addressDTO.getLocality(), 
                addressDTO.getCity(), addressDTO.getState(),
                addressDTO.getCountry(), addressDTO.getPinCode());
    }
    
    /**
     * Maps a list of AddressDTO objects to list of Address objects.
     * 
     * @param addressesDTO a list containing AddressDTO objects to be mapped.
     * @return a list containing the mapped Address instances.
     */
    public static List<Address> toAddress(List<AddressDTO> addressesDTO) {
        List<Address> addresses = new ArrayList<>();
        
        if (null != addressesDTO) {
            for (AddressDTO addressDTO : addressesDTO) {
                addresses.add(toAddress(addressDTO));
            }
        }
        return addresses;
    }
    
    /**
     * Maps Project object to ProjectDTO object.
     * 
     * @param project the Project object to be mapped.
     * @return the mapped ProjectDTO instance.
     */
    public static ProjectDTO toProjectDTO(Project project) {
        EmployeeDTO employeeDTO;
        Set<EmployeeDTO> employeesDTO = new HashSet<>();
        Set<Employee> employees = project.getEmployees();
        
        if (null != employees) {
            for (Employee employee : employees) {
                employeeDTO = new EmployeeDTO();
                employeeDTO.setId(employee.getId());
                employeeDTO.setName(employee.getName());
                employeesDTO.add(employeeDTO);
            }
        }
        return new ProjectDTO(project.getId(), project.getName(),
                project.getDescription(), project.getManager(), 
                project.getStatus(), employeesDTO);
    }
    
    /**
     * Maps ProjectDTO object to Project object.
     * 
     * @param ProjectDTO the ProjectDTO object to be mapped.
     * @return the mapped Project instance.
     */
    public static Project toProject(ProjectDTO projectDTO) {
        Employee employee;
        Set<Employee> employees = new HashSet<>();
        Set<EmployeeDTO> employeesDTO = projectDTO.getEmployees();
        
        if (null != employeesDTO) {
            for (EmployeeDTO employeeDTO : employeesDTO) {
                employee = new Employee();
                employee.setId(employeeDTO.getId());
                employee.setName(employeeDTO.getName());
                employees.add(employee);
            }
        }
        return new Project(projectDTO.getId(), projectDTO.getName(),
                projectDTO.getDescription(), projectDTO.getManager(), 
                projectDTO.getStatus(), employees);
    }
}
