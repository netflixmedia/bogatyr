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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.property;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is the properties class for file and stream access.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.1.0
 */
public class PropertyImpl extends ServiceAbstract implements Property {
	private final Properties properties;

	
    public PropertyImpl(final InputStream is) throws IOException {
        super();
       
        if (null == is) {
			throw new RuntimeExceptionArgumentIsNull("is"); //$NON-NLS-1$
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
    public Boolean getBoolean(final String key) {
        return null == properties.getProperty(key) ? null : Boolean.valueOf(properties.getProperty(key));
    }

	@Override
    public BigDecimal getBigDecimal(final String key) {
		return HelperNumber.getBigDecimal(properties.getProperty(key));
	}

	@Override
    public BigInteger getBigInteger(final String key) {
		return HelperNumber.getBigInteger(properties.getProperty(key));
	}

	@Override
    public Byte getByte(final String key) {
		return HelperNumber.getByte(properties.getProperty(key));
	}

	@Override
    public Double getDouble(final String key) {
		return HelperNumber.getDouble(properties.getProperty(key));
	}

	@Override
    public Float getFloat(final String key) {
		return HelperNumber.getFloat(properties.getProperty(key));
	}

	@Override
    public Integer getInteger(final String key) {
		return HelperNumber.getInteger(properties.getProperty(key));
	}

	@Override
    public Long getLong(final String key) {
		return HelperNumber.getLong(properties.getProperty(key));
	}

	@Override
    public Short getShort(final String key) {
		return HelperNumber.getShort(properties.getProperty(key));
	}

	@Override
    public File getFile(final String key) {
		final String file = properties.getProperty(key);
		
		return null == file ? null : new File(file);
	}

	@Override
    public URL getURL(final String key) {
		final String url = properties.getProperty(key);
		
		if (null != url) {
			try {
				return new URL(url);
			} catch (MalformedURLException e) {
				// do nothing
			}
		}
		return null;
	}
}   
