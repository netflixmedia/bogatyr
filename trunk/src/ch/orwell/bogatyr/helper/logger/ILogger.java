/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU General Public License for more details:
 * ----------------------------------------------------
 * <http://www.gnu.org/licenses>
 * 
 * This distribution is available at:
 * ----------------------------------
 * <http://code.google.com/p/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper.logger;


/**
 * Defines the methods for the implementation of the logger
 *  
 * @author Stefan Laubenberger
 * @version 20080811
 */
public interface ILogger {
	/**
     * Writes to the WARNING-file
     * 
     * @param clazz caller class
     * @param method caller method
     * @param logEntry object for log entry
     */
    void writeWarning(Class<?> clazz, String method, Object logEntry);
	
	/**
     * Writes to the DEBUG-file
     * 
     * @param clazz caller class
     * @param method caller method
     * @param logEntry object for log entry
     */
    void writeDebug(Class<?> clazz, String method, Object logEntry);

	/**
     * Writes to the EXCEPTION-file
     * 
     * @param clazz caller class
     * @param method caller method
     * @param logEntry object for log entry
     * @param ex Exception
     */
    void writeException(Class<?> clazz, String method, Object logEntry, Exception ex);

	/**
     * Writes to the Log-file
     * 
     * @param clazz caller class
     * @param method caller method
     * @param logEntry object for log entry
     */
    void writeLog(Class<?> clazz, String method, Object logEntry);
    
	/**
     * Writes an method entry to the DEBUG-file
     * 
     * @param clazz caller class
     * @param method caller method
     * @param methodInput input objects (parameters) for the method
     */
    void writeMethodEntry(Class<?> clazz, String method, Object[] methodInput);

    /**
     * Writes an method entry to the DEBUG-file
     * 
     * @param clazz caller class
     * @param method caller method
     * @param methodInput input object (parameter) for the method
     */
    void writeMethodEntry(Class<?> clazz, String method, Object methodInput);

    /**
     * Writes an method entry (without parameters) to the DEBUG-file
     * 
     * @param clazz caller class
     * @param method caller method
     */
    void writeMethodEntry(Class<?> clazz, String method);
    
	/**
     * Writes an method exit to the DEBUG-file
     * 
     * @param clazz caller class
     * @param method caller method
     * @param methodOutput result object from the method
     */
    void writeMethodExit(Class<?> clazz, String method, Object methodOutput);
    
	/**
     * Writes an method exit (without result object) to the DEBUG-file
     * 
     * @param clazz caller class
     * @param method caller method
     */
    void writeMethodExit(Class<?> clazz, String method);
}
