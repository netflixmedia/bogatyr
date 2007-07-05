package ch.orwell.bogatyr.net.common.dto;

import java.util.ArrayList;


/**
 * Defines the methods for the implementation of the validator for all value objects.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070705
 */
public interface ValidaterInterface {

	/**
     * Validate the object
     * @throws Exception
     */
	public void validate() throws Exception;
	
	/**
     * Validate a string
      * @param variable Name of the variable
     * @param arg String
     * @throws Exception
     */
	public void validateString(String variable, String arg) throws Exception;

	/**
     * Validate an integer
     * @param variable Name of the variable
     * @param arg int
     * @throws Exception
     */
	public void validateInt(String variable, int arg) throws Exception;

	/**
     * Validate a long
     * @param variable Name of the variable
     * @param arg int
     * @throws Exception
     */
	public void validateLong(String variable, long arg) throws Exception;

	/**
     * Validate an object
     * @param variable Name of the variable
     * @param arg Object
     * @throws Exception
     */
	public void validateObject(String variable, Object arg) throws Exception;

	/**
     * Validate an object-array
     * @param variable Name of the variable
     * @param arg Object-array
     * @throws Exception
     */
	public void validateArray(String variable, Object[] arg) throws Exception;

	/**
     * Validate an ArrayList
     * @param variable Name of the variable
     * @param arg ArrayList
     * @throws Exception
     */
	public void validateArrayList(String variable, ArrayList arg) throws Exception;
}
