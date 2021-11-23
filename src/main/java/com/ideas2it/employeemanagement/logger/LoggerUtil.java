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
 * @author Sivanantham
 * @version 1.0
 */
public class LoggerUtil {
    private static Logger logger = getLogger();

    /**
     * Gets the logger instance if already instantiated, otherwise configures
     * logger and instantiate the logger.
     *
     * @return the logger instance.
     */
    public static Logger getLogger() {
        return LogManager.getLogger(LoggerUtil.class);
    }

    /**
     * Logs the specified message as a debug level message.
     * 
     * @param message the message to be logged.
     */
    public static void debug(String message) {
        logger.info(message);
    }

    /**
     * Logs the specified message as a info level message.
     * 
     * @param message the message to be logged.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs the specified message as a warn level message.
     * 
     * @param message the message to be logged.
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Logs the specified message as a error level message.
     * 
     * @param message the message to be logged.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs the specified exception as a error level message.
     * 
     * @param message   the message to be logged.
     * @param throwable the Throwable object to be logged.
     */
    public static void error(String message, Throwable throwable) {
        logger.error(throwable);
    }

    /**
     * Logs the specified message as a fatal level message.
     * 
     * @param message the message to be logged.
     */
    public static void fatal(String message) {
        logger.fatal(message);
    }

    /**
     * Logs the specified exception as a fatal level message.
     * 
     * @param message   the message to be logged.
     * @param throwable the the Throwable object to be logged.
     */
    public static void fatal(String message, Throwable throwable) {
        logger.fatal(message, throwable);
    }
}