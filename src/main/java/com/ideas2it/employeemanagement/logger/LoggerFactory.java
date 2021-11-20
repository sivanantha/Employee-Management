/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.logger;

import java.io.File;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This class contains logger utility methods.
 *
 * @author  Sivanantham
 * @version 1.0
 */
public class LoggerFactory {
    private static Logger logger;
    
    /**
     * Gets the logger instance if already instantiated, otherwise configures
     * logger and instantiate the logger.
     *
     * @return the logger instance.
     */
    public static Logger getLogger() {
        File configuration;
        LoggerContext loggerContext;
        
        if (logger == null) {
            configuration = new File("resources/log4j2-config.xml");
            loggerContext = (LoggerContext) LogManager.getContext(false);
            loggerContext.setConfigLocation(configuration.toURI());
            logger = LogManager.getLogger();
        }
        return logger;
    }
}
