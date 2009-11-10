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
package ch.sisprocom.bogatyr.service.property;

import ch.sisprocom.bogatyr.service.ServiceAbstract;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Properties;


/**
 * This is the properties class for file and stream access.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091027)
 * @since 0.1.0
 */
public class PropertyStream extends ServiceAbstract implements Property {
	private final Properties properties;

	
    public PropertyStream(final InputStream inputStream) throws IOException {
        super();
        properties = new Properties();
        properties.load(inputStream);
    }

    public PropertyStream(final File file) throws IOException {
    	this(new BufferedInputStream(new FileInputStream(file)));
    } 

	
	/*
	 * Implemented methods
	 */
    @Override
    public Properties getProperties() {
		return properties;
	}
    
    @Override
    public String getValue(final String key) {
        return properties.getProperty(key);
    }

    @Override
    public Boolean getBooleanValue(final String key) {
        return Boolean.valueOf(properties.getProperty(key));
    }

	@Override
    public BigDecimal getBigDecimalValue(final String key) {
		return new BigDecimal(properties.getProperty(key));
	}

	@Override
    public BigInteger getBigIntegerValue(final String key) {
		return new BigInteger(properties.getProperty(key));
	}

	@Override
    public Byte getByteValue(final String key) {
		return Byte.valueOf(properties.getProperty(key));	
	}

	@Override
    public Double getDoubleValue(final String key) {
		return Double.valueOf(properties.getProperty(key));
	}

	@Override
    public Float getFloatValue(final String key) {
		return Float.valueOf(properties.getProperty(key));
	}

	@Override
    public Integer getIntegerValue(final String key) {
		return Integer.valueOf(properties.getProperty(key));
	}

	@Override
    public Long getLongValue(final String key) {
		return Long.valueOf(properties.getProperty(key));
	}

	@Override
    public Short getShortValue(final String key) {
		return Short.valueOf(properties.getProperty(key));
	}
}   