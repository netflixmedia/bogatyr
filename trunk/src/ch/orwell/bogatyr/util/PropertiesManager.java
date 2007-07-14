/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import ch.orwell.bogatyr.Application;


/**
 * PropertiesManager
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public final class PropertiesManager {
    private Properties properties;

	/**
	 * Constructs a PropertiesManager.
	 * 
	 * @param propertiesFile The property file to manage.
	 */
    public PropertiesManager(String propertiesFile) {
    	this.properties = new Properties();
    	try {
    		this.properties.load(new FileInputStream(propertiesFile));
		} catch (IOException ex) {
			ex.printStackTrace();
			Application.exit(500);
		}
    }
    
	/**
     * Returns the property-value as String
     * 
     * @param propertyName Input-String
     * @return String-value
     */	
    public String getProperty(String propertyName) {
        String propertyValue = this.properties.getProperty(propertyName);
        return propertyValue;
    }

    /**
     * Returns the property-value as double-value
     * 
     * @param propertyName Input-String
     * @return double-value
     */	
    public double getPropertyDouble(String propertyName) {
        return Double.parseDouble(this.properties.getProperty(propertyName));
    }

    /**
     * Returns the property-value as int-value
     * 
     * @param propertyName Input-String
     * @return int-value
     */	
    public int getPropertyInt(String propertyName) {
        return Integer.parseInt(this.properties.getProperty(propertyName));
    }

    /**
     * Returns the property-value as boolean-value
     * 
     * @param propertyName Input-String
     * @return boolean-value
     */	
    public boolean getPropertyBoolean(String propertyName) {
        return Boolean.valueOf(this.properties.getProperty(propertyName)).booleanValue();
    }
    
    /**
     * Writes all properties to the system-out
     */	
    @SuppressWarnings("unchecked")
	public void diagProperties() {
     	Enumeration<String> enumeration = (Enumeration<String>) this.properties.propertyNames();
     	String propertyName;
     	System.out.println("## Properties ##"); //$NON-NLS-1$
        
        while (enumeration.hasMoreElements()) {
        	propertyName = enumeration.nextElement(); 
        	System.out.println(propertyName + ": " + this.properties.getProperty(propertyName)); //$NON-NLS-1$
        }
    }
}   

