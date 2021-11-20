/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.model;

import javax.persistence.AttributeConverter;

/**
 * This enum contains status definition for project. The project can have 3 
 * status. They are DEVELOPMENT, TESTING, LIVE.
 * 
 *
 * @author  Sivanantham
 * @version 1.0
 */
public enum Status {
    
    DEVELOPMENT("D"),
    TESTING("T"),
    LIVE("L");
    
    private String status;
    
    Status(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
    public static Status fromStatus(String status) {
        switch (status) {
            case "D":
                return Status.DEVELOPMENT;
            case "T":
                return Status.TESTING;
            case "L":
                return Status.LIVE;
            default:
                throw new IllegalArgumentException("Invalid Status");
        }
    }
}

/**
 * Converter for Status enumeration. It converts entity enum value to database
 * column value and vice versa.
 *
 * @author  Sivanantham
 * @version 1.0
 */
class StatusConverter implements AttributeConverter<Status, String> {
    
    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getStatus();
    }
    
    @Override
    public Status convertToEntityAttribute(String status) {
        return Status.fromStatus(status);
    }
}
