/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.ideas2it.employeemanagement.dao.AddressDAO;
import com.ideas2it.employeemanagement.dao.impl.AddressDAOImpl;
import com.ideas2it.employeemanagement.model.Address;
import com.ideas2it.employeemanagement.model.AddressDTO;
import com.ideas2it.employeemanagement.service.AddressService;
import com.ideas2it.employeemanagement.utils.Mapper;

/**
 * The AddressServiceImpl class contains validations and implementations for 
 * address details. The address has the following details: 
 * door number, street name, locality, city, state, country, postal code. 
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class AddressServiceImpl implements AddressService {
    private AddressDAO addressDAO = new AddressDAOImpl();
    
    /**
     * 
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidDoorNumber(String doorNumber) {
        return Pattern.matches("^[\\s]*([1-9][0-9]{0,4}([-][A-Z]|[A-Z])?[/]"
                       + "[1-9][0-9]{0,4}[A-Z]?|[1-9][0-9]{0,4}([-][A-Z]|[A-Z])"
                       + "?|([A-Z]|[A-Z][-])?[1-9][0-9]{0,4}|([A-Z]|[A-Z][-])"
                       + "[1-9][0-9]{0,4}[/][A-Z]?[1-9][0-9]{0,4}|[1-9][0-9]"
                       + "{0,4}([-][A-Z]|[A-Z])?[/][A-Z]?[1-9][0-9]{0,4})[\\s]*"
                       + "$", doorNumber);
    }
    
    /**
     * 
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidStreet(String street) {
        return Pattern.matches("^[\\s]*(([1-9][0-9]{0,4})?([ ]?[A-Za-z][\\.]?|"
                + "[A-Za-z][ ]?){4,50}([1-9][0-9]{0,4})?)[\\s]*$", street);
    }
    
    /**
     * 
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidPlaceName(String name) {
        return Pattern.matches("^[\\s]*(([a-zA-Z]{3,50}[ ]?|[ ][a-zA-Z]{2})"
                               + "{1,2})[\\s]*$", name);
    }
   
    /**
     * 
     * {@inheritDoc}
     * 
     */
    @Override
    public boolean isValidPinCode(String pinCode) {
        return Pattern.matches("^[\\s]*([0-9]{3,9})[\\s]*$", pinCode);
    }
    
    /**
     * Maps AddressDTO instances to Address instances.
     * 
     * @param addresses a list of AddressDTO instances to be mapped.
     * @return a List containing mapped Address instances.
     */
    private List<Address> toAddress(List<AddressDTO> addressesDTO) {
        List<Address> addresses = new ArrayList<>(addressesDTO.size());
        
        for (AddressDTO addressDTO : addressesDTO) {
            addresses.add(Mapper.toAddress(addressDTO));
        }
        return addresses;
    }
    
    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean createAddress(List<AddressDTO> addressesDTO)  
            throws SQLException {
        List<Address> addresses = toAddress(addressesDTO);
        
        return (0 != addressDAO.insertRecord(addresses));
    }
    
    /**
     * Maps Address instances to AddressDTO instances.
     * 
     * @param addresses a list of Address instances to be mapped.
     * @return a List containing mapped AddressDTO instances.
     */
    private List<AddressDTO> toAddressDTO(List<Address> addresses) {
        List<AddressDTO> addressesDTO = new ArrayList<>(addresses.size());
        
        for (Address address : addresses) {
            addressesDTO.add(Mapper.toAddressDTO(address));
        }
        return addressesDTO;
    }
    
    /**
     *
     * {@inheritDoc}
     *
     */
    @Override
    public List<AddressDTO> getAddresses(int employeeId) throws SQLException {
        List<Address> addresses = addressDAO.selectRecord(employeeId);
        
        return toAddressDTO(addresses);
    }
    
    /**
     * Groups the addresses by employee id.
     *
     * @param addressDTO a list containing address details.
     * @return a Map containing addresses with employee id as key.
     */
    private Map<Integer, List<AddressDTO>> groupByEmployee(
            List<AddressDTO> addressesDTO) {
        int employeeId;
        List<AddressDTO> addresses;
        Map<Integer, List<AddressDTO>> groupedAddresses = new HashMap<>();
        
        for (AddressDTO addressDTO : addressesDTO) {
            employeeId = addressDTO.getEmployeeId();
            
            if (groupedAddresses.containsKey(employeeId)) {
                addresses = groupedAddresses.get(employeeId);
                addresses.add(addressDTO);
                groupedAddresses.put(employeeId, addresses);         
            } else {
                addresses = new ArrayList<AddressDTO>();
                addresses.add(addressDTO);
                groupedAddresses.put(employeeId, addresses);
            }
        }
        return groupedAddresses;
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public Map<Integer, List<AddressDTO>> getAllAddresses() throws 
            SQLException {
        List<Address> addresses = addressDAO.selectAllRecord();
        List<AddressDTO> addressesDTO = toAddressDTO(addresses);
        
        return groupByEmployee(addressesDTO);
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updateDoorNumber(int id, String doorNumber) throws 
            SQLException {
        return (1 == addressDAO.updateDoorNumber(id, doorNumber));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updateStreet(int id, String street) throws SQLException {
        return (1 == addressDAO.updateStreet(id, street));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updateLocality(int id, String locality) throws SQLException {
        return (1 == addressDAO.updateLocality(id, locality));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updateCity(int id, String city) throws SQLException {
        return (1 == addressDAO.updateCity(id, city));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updateState(int id, String state) throws SQLException {
        return (1 == addressDAO.updateState(id, state));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updateCountry(int id, String country) throws SQLException {
        return (1 == addressDAO.updateCountry(id, country));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updatePinCode(int id, String pinCode) throws SQLException {
        return (1 == addressDAO.updatePinCode(id, pinCode));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean updateAllDetails(AddressDTO addressDTO) throws SQLException {
        return (1 == addressDAO.updateAllColumn(Mapper.toAddress(addressDTO)));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean deleteAddress(int id) throws SQLException {
        return (1 == addressDAO.deleteRecord(id));
    }
    
    /**
     * {@inheritDoc} 
     *
     */
    @Override
    public boolean deleteAllAddress() throws SQLException {
        return (0 != addressDAO.deleteAllRecord());
    } 
}
