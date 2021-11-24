/*
 * Copyright (c) 2021 Ideas2IT Technologies. All rights reserved.
 */
package com.ideas2it.employeemanagement.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * This class contains methods for logging.
 *
 * @author Sivanantham
 * @version 1.0
 */
public class EMSLogger {
    public Logger logger;

    /**
     * Creates a logger using the fully qualified name of this Class..
     */
    public EMSLogger() {
        logger = LogManager.getLogger();
        logger.info("Logger Started!");
    }

    /**
     * Creates a Logger using the fully qualified name of the specified Class as
     * the Logger name.
     * 
     * @param className The Class whose name should be used as the Logger name.
     */
    public EMSLogger(Class<?> className) {
        logger = LogManager.getLogger(className);
        logger.info("Logger Started!");
    }

    /**
     * Gets the logger instance if already instantiated, otherwise configures
     * logger and instantiate the logger.
     *
     * @return the logger instance.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Logs the specified message as a debug level message.
     * 
     * @param message the message to be logged.
     */
    public void debug(String message) {
        logger.info(message);
    }

    /**
     * Logs the specified message as a info level message.
     * 
     * @param message the message to be logged.
     */
    public void info(String message) {
        logger.info(message);
    }

    /**
     * Logs the specified message as a warn level message.
     * 
     * @param message the message to be logged.
     */
    public void warn(String message) {
        logger.warn(message);
    }

    /**
     * Logs the specified message as a error level message.
     * 
     * @param message the message to be logged.
     */
    public void error(String message) {
        logger.error(message);
    }

    /**
     * Logs the specified exception as a error level message.
     * 
     * @param message   the message to be logged.
     * @param throwable the Throwable object to be logged.
     */
    public void error(String message, Throwable throwable) {
        logger.error(throwable);
    }

    /**
     * Logs the specified message as a fatal level message.
     * 
     * @param message the message to be logged.
     */
    public void fatal(String message) {
        logger.fatal(message);
    }

    /**
     * Logs the specified exception as a fatal level message.
     * 
     * @param message   the message to be logged.
     * @param throwable the the Throwable object to be logged.
     */
    public void fatal(String message, Throwable throwable) {
        logger.fatal(message, throwable);
    }
}