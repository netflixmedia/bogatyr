/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.service.property;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperNumber;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.service.ServiceAbstract;


/**
 * This is the properties class for file and stream access.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101106)
 * @since 0.1.0
 */
public class PropertyImpl extends ServiceAbstract implements Property {
	private static final Logger log = LoggerFactory.getLogger(PropertyImpl.class);

	private final Properties properties;


	public PropertyImpl(final InputStream is) throws IOException {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(is));

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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(properties));
		return properties;
	}

	@Override
	public String getValue(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		final String result = properties.getProperty(key);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public Boolean getBoolean(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		final String value = properties.getProperty(key);
		final Boolean result = null == value ? null : Boolean.parseBoolean(value);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public BigDecimal getNumber(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		final BigDecimal result = HelperNumber.getNumber(properties.getProperty(key));

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public File getFile(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		final String value = properties.getProperty(key);
		final File result = null == value ? null : new File(value);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public URL getURL(final String key) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(key));

		final String value = properties.getProperty(key);
		URL result = null;

		if (null != value) {
			try {
				result = new URL(value);
			} catch (MalformedURLException ex) {
				//do nothing
//				if (log.isInfoEnabled()) log.info("URL invalid", ex); //$NON-NLS-1$
			}
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}   
