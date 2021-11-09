/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.exceptions;

import com.ideas2it.employeemanagement.utils.Constants;

/**
 * The base exception type for all database and hibernated related exceptions.
 *
 * @author  Sivanantham
 * @version 1.0
 */ 
public class EMSException extends Exception {
    
    /**
	 * Constructs a EMSException using the given error code.
	 *
	 * @param errorCode The error code explaining the reason for the exception.
	 */
    public EMSException(String errorCode) {
        super(Constants.errorCodes.get(errorCode));
    }
}
