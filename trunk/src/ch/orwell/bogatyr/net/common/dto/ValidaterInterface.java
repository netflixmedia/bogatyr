package ch.orwell.bogatyr.net.common.dto;

import java.util.ArrayList;


/**
 * Defines the methods for the implementation of the validator for all value objects.
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070618
 */
public interface ValidaterInterface {

	/**
     * Validate the object
     * @throws Exception
     */
	public void validate() throws Exception;
	
	/**
     * Validate a string
     * @param className Call object 
     * @param variable Name of the variable
     * @param arg String
     * @throws Exception
     */
	public void validateString(String className, String variable, String arg) throws Exception;

	/**
     * Validate an integer
     * @param className Call object
     * @param variable Name of the variable
     * @param arg int
     * @throws Exception
     */
	public void validateInt(String className, String variable, int arg) throws Exception;

	/**
     * Validate a long
     * @param className Call object
     * @param variable Name of the variable
     * @param arg int
     * @throws Exception
     */
	public void validateLong(String className, String variable, long arg) throws Exception;

	/**
     * Validate an object
     * @param className Call object 
     * @param variable Name of the variable
     * @param arg Object
     * @throws Exception
     */
	public void validateObject(String className, String variable, Object arg) throws Exception;

	/**
     * Validate an object-array
     * @param className Call object 
     * @param variable Name of the variable
     * @param arg Object-array
     * @throws Exception
     */
	public void validateArray(String className, String variable, Object[] arg) throws Exception;

	/**
     * Validate an ArrayList
     * @param className Call object 
     * @param variable Name of the variable
     * @param arg ArrayList
     * @throws Exception
     */
	public void validateArrayList(String className, String variable, ArrayList arg) throws Exception;
}

