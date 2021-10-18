/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package  com.ideas2it.employeemanagement.utils;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.model.Employee;
import com.ideas2it.employeemanagement.model.EmployeeDTO;

/**
 * This mapper class maps employee model to employee DTO, Address to 
 * AddressDTO and vice versa.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public final class Mapper {
    
    /**
     * Maps Employee object to EmployeeDTO object.
     * 
     * @param employee the Employee object to be mapped.
     * @return the mapped EmployeeDTO instance.
     */
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee.getId(), employee.getName(),
                employee.getDateOfBirth(), employee.getGender(),
                employee.getMobileNumber(), employee.getEmail(), 
                employee.getSalary(), employee.getDateOfJoining());    
    }
    
    /**
     * Maps EmployeeDTO object to Employee object.
     * 
     * @param employeeDTO the EmployeeDTO object to be mapped.
     * @return the mapped Employee instance.
     */
    public static Employee toEmployee(EmployeeDTO employeeDTO) {
       return new Employee(employeeDTO.getId(), employeeDTO.getName(),
                employeeDTO.getDateOfBirth(), employeeDTO.getGender(),
                employeeDTO.getMobileNumber(), employeeDTO.getEmail(), 
                employeeDTO.getSalary(), employeeDTO.getDateOfJoining()); 
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
                address.getState(), address.getCountry(), address.getPinCode(),
                address.getEmployeeId());
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
                addressDTO.getCountry(), addressDTO.getPinCode(),
                addressDTO.getEmployeeId());
    }
}

