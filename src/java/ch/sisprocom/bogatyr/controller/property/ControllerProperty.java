/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is the properties class for file and stream access.
 * 
 * @author Stefan Laubenberger
 * @version 20090306
 */
public class ControllerProperty implements IControllerProperty { //TODO document in Wiki!
//	private Properties properties;
	private final Properties properties;

//  public ControllerProperty() {
//	  this.properties = properties;
//  
//  }

//	public void setProperties(final Property properties) {
//		this.properties = properties;
//
//}
	
//	@Required
//	public void setProperties(final InputStream inputStream)  throws IOException{
//        properties = new Properties();
////        properties.load(inputStream);
//
//	}

    public ControllerProperty(final InputStream inputStream) throws IOException {
        super();
        properties = new Properties();
        properties.load(inputStream);
    }

    public ControllerProperty(final File file) throws IOException {
    	this(new FileInputStream(file));
    }

	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	} 

	
	/*
	 * Implemented methods
	 */
    public Properties getProperties() {
		return properties;
	}
    
    public String getProperty(final String propertyName) {
        return properties.getProperty(propertyName);
    }

    public double getPropertyDouble(final String propertyName) {
    	String value = properties.getProperty(propertyName);
    	return HelperGeneral.isStringNumeric(value) ? Double.parseDouble(value) : 0.0D;
    }

    public int getPropertyInt(final String propertyName) {
    	String value = properties.getProperty(propertyName);
    	return HelperGeneral.isStringNumeric(value) ? Integer.parseInt(value) : 0;
    }

    public boolean getPropertyBoolean(final String propertyName) {
        return properties.getProperty(propertyName) != null && Boolean.valueOf(properties.getProperty(propertyName));
    }
}   
