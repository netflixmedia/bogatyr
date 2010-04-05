/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.service.property;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperNumber;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * This is the properties class for file and stream access.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.1.0
 */
public class PropertyImpl extends ServiceAbstract implements Property {
	private static final Logger log = LoggerFactory.getLogger(PropertyImpl.class);
	
	private final Properties properties;

	
    public PropertyImpl(final InputStream is) throws IOException {
        super();
        log.trace(HelperLog.constructor(is));
        
        if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
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
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(properties));
		return properties;
	}
    
    @Override
    public String getValue(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final String result = properties.getProperty(key);
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

    @Override
    public Boolean getBoolean(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Boolean result = null == properties.getProperty(key) ? null : Boolean.valueOf(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

	@Override
    public BigDecimal getBigDecimal(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final BigDecimal result = HelperNumber.getBigDecimal(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public BigInteger getBigInteger(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final BigInteger result = HelperNumber.getBigInteger(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Byte getByte(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Byte result = HelperNumber.getByte(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Double getDouble(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Double result = HelperNumber.getDouble(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Float getFloat(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Float result = HelperNumber.getFloat(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Integer getInteger(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Integer result = HelperNumber.getInteger(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Long getLong(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Long result = HelperNumber.getLong(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Short getShort(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final Short result = HelperNumber.getShort(properties.getProperty(key));
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public File getFile(final String key) {
		log.debug(HelperLog.methodStart(key));
		
		final String file = properties.getProperty(key);
		final File result = null == file ? null : new File(file);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public URL getURL(final String key) {
		log.debug(HelperLog.methodStart(key));

		final String url = properties.getProperty(key);
		URL result = null;
		
		if (null != url) {
			try {
				result =  new URL(url);
			} catch (MalformedURLException ex) {
				log.info("URL invalid", ex); //$NON-NLS-1$
			}
		}
		log.debug(HelperLog.methodExit(result));
		return result;
	}
}   
