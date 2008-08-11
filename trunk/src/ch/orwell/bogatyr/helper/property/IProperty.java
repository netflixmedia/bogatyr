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
package ch.orwell.bogatyr.helper.property;


/**
 * Defines the methods for the implementation of the properties
 * 
 * @author Stefan Laubenberger
 * @version 20080526
 */
public interface IProperty {
	/**
     * Returns the property-value as String
     * 
     * @param propertyName Input-String
     * @return String-value
     */
    String getProperty(String propertyName);

    /**
     * Returns the property-value as double-value
     * 
     * @param propertyName Input-String
     * @return double-value
     */
    double getPropertyDouble(String propertyName);

    /**
     * Returns the property-value as int-value
     * 
     * @param propertyName Input-String
     * @return int-value
     */
    int getPropertyInt(String propertyName);

    /**
     * Returns the property-value as boolean-value
     * 
     * @param propertyName Input-String
     * @return boolean-value
     */
    boolean getPropertyBoolean(String propertyName);
}   
