/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperNumber;
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
 * @version 0.9.0 (20100129)
 * @since 0.1.0
 */
public class PropertyImpl extends ServiceAbstract implements Property {
	private final Properties properties;

	
    public PropertyImpl(final InputStream is) throws IOException {
        super();
       
        if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}

        properties = new Properties();
        properties.load(is);
    }

    public PropertyImpl(final File file) throws IOException {
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
        return null == properties.getProperty(key) ? null : Boolean.valueOf(properties.getProperty(key));
    }

	@Override
    public BigDecimal getBigDecimalValue(final String key) {
		return HelperNumber.getBigDecimal(properties.getProperty(key));
	}

	@Override
    public BigInteger getBigIntegerValue(final String key) {
		return HelperNumber.getBigInteger(properties.getProperty(key));
	}

	@Override
    public Byte getByteValue(final String key) {
		return HelperNumber.getByte(properties.getProperty(key));
	}

	@Override
    public Double getDoubleValue(final String key) {
		return HelperNumber.getDouble(properties.getProperty(key));
	}

	@Override
    public Float getFloatValue(final String key) {
		return HelperNumber.getFloat(properties.getProperty(key));
	}

	@Override
    public Integer getIntegerValue(final String key) {
		return HelperNumber.getInteger(properties.getProperty(key));
	}

	@Override
    public Long getLongValue(final String key) {
		return HelperNumber.getLong(properties.getProperty(key));
	}

	@Override
    public Short getShortValue(final String key) {
		return HelperNumber.getShort(properties.getProperty(key));
	}
}   
