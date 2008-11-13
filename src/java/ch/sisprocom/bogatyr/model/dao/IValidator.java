/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
 * <http://www.sisprocom.ch/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * SiSprocom GmbH
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.dao;

import java.util.Collection;

import ch.sisprocom.bogatyr.helper.exception.ExceptionValidation;


/**
 * Defines the methods for the implementation of the validator for all value objects.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public interface IValidator {
	/**
     * Validates the object.
     * 
     * @throws ExceptionValidation
     */
    void validate() throws ExceptionValidation;
	
	/**
     * Validates a string.
     * 
     * @param variable Name of the variable
     * @param arg String for validation
     * @throws ExceptionValidation
     */
    void validateString(String variable, String arg) throws ExceptionValidation;

	/**
     * Validates an integer.
     * 
     * @param variable Name of the variable
     * @param arg int for validation
     * @throws ExceptionValidation
     */
    void validateInt(String variable, int arg) throws ExceptionValidation;

	/**
     * Validates a double.
     * 
     * @param variable Name for validation of the variable
     * @param arg double for validation
     * @throws ExceptionValidation
     */
    void validateDouble(String variable, double arg) throws ExceptionValidation;

	/**
     * Validates a long.
     * 
     * @param variable Name of the variable
     * @param arg long for validation
     * @throws ExceptionValidation
     */
    void validateLong(String variable, long arg) throws ExceptionValidation;
	
	/**
     * Validates a float.
     * 
     * @param variable Name of the variable
     * @param arg float for validation
     * @throws ExceptionValidation
     */
    void validateFloat(String variable, float arg) throws ExceptionValidation;

	/**
     * Validates an object.
     * 
     * @param variable Name of the variable
     * @param arg Object for validation
     * @throws ExceptionValidation
     */
    void validateObject(String variable, Object arg) throws ExceptionValidation;

	/**
     * Validates an object-array.
     * 
     * @param variable Name of the variable
     * @param arg Object-array for validation
     * @throws ExceptionValidation
     */
    void validateArray(String variable, Object[] arg) throws ExceptionValidation;

	/**
     * Validates an collection.
     * 
     * @param variable Name of the variable
     * @param arg Collection for validation
     * @throws ExceptionValidation
     */
    void validateCollection(String variable, Collection<?> arg) throws ExceptionValidation;
}
