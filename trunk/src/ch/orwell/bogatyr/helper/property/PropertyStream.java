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
package ch.orwell.bogatyr.helper.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.orwell.bogatyr.helper.HelperGeneral;


/**
 * This is the properties class for stream access
 * 
 * @author Stefan Laubenberger
 * @version 20080612
 */
public class PropertyStream implements IProperty {
	private final Properties properties;


    public PropertyStream(final InputStream inputStream) throws IOException {
        super();
        properties = new Properties();
        properties.load(inputStream);
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
    public String getProperty(final String propertyName) {
        return properties.getProperty(propertyName);
    }

    public double getPropertyDouble(final String propertyName) {
    	if (properties.getProperty(propertyName) != null) {
    		return Double.parseDouble(properties.getProperty(propertyName));
    	}
    	return 0.0;
    }

    public int getPropertyInt(final String propertyName) {
    	if (properties.getProperty(propertyName) != null) {
    		return Integer.parseInt(properties.getProperty(propertyName));
    	}
    	return 0;
    }

    public boolean getPropertyBoolean(final String propertyName) {
        return properties.getProperty(propertyName) != null && Boolean.valueOf(properties.getProperty(propertyName));
    }
}   
