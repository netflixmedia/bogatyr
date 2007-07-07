/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
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
 * @version 20070707
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
     * @param propertyName Input-String
     * @return String-value
     */	
    public String getProperty(String propertyName) {
        String propertyValue = this.properties.getProperty(propertyName);
        return propertyValue;
    }

    /**
     * Returns the property-value as double-value
     * @param propertyName Input-String
     * @return double-value
     */	
    public double getPropertyDouble(String propertyName) {
        return Double.parseDouble(this.properties.getProperty(propertyName));
    }

    /**
     * Returns the property-value as int-value
     * @param propertyName Input-String
     * @return int-value
     */	
    public int getPropertyInt(String propertyName) {
        return Integer.parseInt(this.properties.getProperty(propertyName));
    }

    /**
     * Returns the property-value as boolean-value
     * @param propertyName Input-String
     * @return boolean-value
     */	
    public boolean getPropertyBoolean(String propertyName) {
        return Boolean.valueOf(this.properties.getProperty(propertyName)).booleanValue();
    }
    
    /**
     * writes all properties to the system-out
     */	
    public void diagProperties() {
     	Enumeration enumeration = this.properties.propertyNames();
     	String propertyName;
     	System.out.println("## Properties ##"); //$NON-NLS-1$
        
        while (enumeration.hasMoreElements()) {
        	propertyName = (String)enumeration.nextElement(); 
        	System.out.println(propertyName + ": " + this.properties.getProperty(propertyName)); //$NON-NLS-1$
        }
    }
}   

