/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.orwell.bogatyr.helper.dao;

import java.util.List;

import ch.orwell.bogatyr.helper.exception.ExceptionValidation;


/**
 * Defines the methods for the implementation of the validator for all value objects.
 * 
 * @author Stefan Laubenberger
 * @version 20080724
 */
public interface IValidator {
	/**
     * Validate the object
     * 
     * @throws ExceptionValidation
     */
    void validate() throws ExceptionValidation;
	
	/**
     * Validate a string
     * 
      * @param variable Name of the variable
     * @param arg String for validation
     * @throws ExceptionValidation
     */
    void validateString(String variable, String arg) throws ExceptionValidation;

	/**
     * Validate an integer
     * 
     * @param variable Name of the variable
     * @param arg int for validation
     * @throws ExceptionValidation
     */
    void validateInt(String variable, int arg) throws ExceptionValidation;

	/**
     * Validate a double
     * 
     * @param variable Name for validation of the variable
     * @param arg double
     * @throws ExceptionValidation
     */
    void validateDouble(String variable, double arg) throws ExceptionValidation;

	/**
     * Validate a long
     * 
     * @param variable Name of the variable
     * @param arg long for validation
     * @throws ExceptionValidation
     */
    void validateLong(String variable, long arg) throws ExceptionValidation;
	
	/**
     * Validate a float
     * 
     * @param variable Name of the variable
     * @param arg float for validation
     * @throws ExceptionValidation
     */
    void validateFloat(String variable, float arg) throws ExceptionValidation;

	/**
     * Validate an object
     * 
     * @param variable Name of the variable
     * @param arg Object for validation
     * @throws ExceptionValidation
     */
    void validateObject(String variable, Object arg) throws ExceptionValidation;

	/**
     * Validate an object-array
     * 
     * @param variable Name of the variable
     * @param arg Object-array for validation
     * @throws ExceptionValidation
     */
    void validateArray(String variable, Object[] arg) throws ExceptionValidation;

	/**
     * Validate an List
     * 
     * @param variable Name of the variable
     * @param list List for validation
     * @throws ExceptionValidation
     */
    void validateList(String variable, List<?> list) throws ExceptionValidation;
}
