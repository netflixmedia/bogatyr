/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;

import ch.sisprocom.bogatyr.controller.ControllerAbstract;


/**
 * This is the properties class for file and stream access.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090612)
 * @since 0.1.0
 */
public class PropertyStream extends ControllerAbstract implements IProperty {
	private final Properties properties;

	
    public PropertyStream(final InputStream inputStream) throws IOException {
        super();
        properties = new Properties();
        properties.load(inputStream);
    }

    public PropertyStream(final File file) throws IOException {
    	this(new FileInputStream(file));
    } 

	
	/*
	 * Implemented methods
	 */
    public Properties getProperties() {
		return properties;
	}
    
    public String getValue(final String key) {
        return properties.getProperty(key);
    }

    public Boolean getBooleanValue(final String key) {
        return Boolean.valueOf(properties.getProperty(key));
    }

	public BigDecimal getBigDecimalValue(final String key) {
		return new BigDecimal(properties.getProperty(key));
	}

	public BigInteger getBigIntegerValue(final String key) {
		return new BigInteger(properties.getProperty(key));
	}

	public Byte getByteValue(final String key) {
		return Byte.valueOf(properties.getProperty(key));	
	}

//	public Date getDateValue(final String key) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public Double getDoubleValue(final String key) {
		return Double.valueOf(properties.getProperty(key));
	}

	public Float getFloatValue(final String key) {
		return Float.valueOf(properties.getProperty(key));
	}

	public Integer getIntegerValue(final String key) {
		return Integer.valueOf(properties.getProperty(key));
	}

	public Long getLongValue(final String key) {
		return Long.valueOf(properties.getProperty(key));
	}

	public Short getShortValue(final String key) {
		return Short.valueOf(properties.getProperty(key));
	}
}   