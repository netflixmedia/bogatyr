/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.dao;

import java.util.ArrayList;

import ch.orwell.bogatyr.exception.ValidationException;


/**
 * Defines the methods for the implementation of the validator for all value objects.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public interface ValidaterInterface {

	/**
     * Validate the object
     * 
     * @throws ValidationException
     */
	public void validate() throws ValidationException;
	
	/**
     * Validate a string
     * 
      * @param variable Name of the variable
     * @param arg String
     * @throws ValidationException
     */
	public void validateString(String variable, String arg) throws ValidationException;

	/**
     * Validate an integer
     * 
     * @param variable Name of the variable
     * @param arg int
     * @throws ValidationException
     */
	public void validateInt(String variable, int arg) throws ValidationException;

	/**
     * Validate a long
     * 
     * @param variable Name of the variable
     * @param arg int
     * @throws ValidationException
     */
	public void validateLong(String variable, long arg) throws ValidationException;

	/**
     * Validate an object
     * 
     * @param variable Name of the variable
     * @param arg Object
     * @throws ValidationException
     */
	public void validateObject(String variable, Object arg) throws ValidationException;

	/**
     * Validate an object-array
     * 
     * @param variable Name of the variable
     * @param arg Object-array
     * @throws ValidationException
     */
	public void validateArray(String variable, Object[] arg) throws ValidationException;

	/**
     * Validate an ArrayList
     * 
     * @param variable Name of the variable
     * @param arg ArrayList
     * @throws ValidationException
     */
	@SuppressWarnings("unchecked")
	public void validateArrayList(String variable, ArrayList arg) throws ValidationException;
}
