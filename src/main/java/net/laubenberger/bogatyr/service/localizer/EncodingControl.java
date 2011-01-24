/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.service.localizer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encoding control for the {@link LocalizerFile}.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.5 (20110123)
 * @since 0.9.5
 */
public class EncodingControl extends Control {
	private static final Logger log = LoggerFactory.getLogger(EncodingControl.class);

	private final String encoding;
	
	
	public EncodingControl() {
		this(Constants.ENCODING_DEFAULT);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());

	}
	
	public EncodingControl(final String encoding) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(encoding));

		if (null == encoding) {
			throw new RuntimeExceptionIsNull("encoding"); //$NON-NLS-1$
		}
		
		this.encoding = encoding;
	}

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(baseName, locale, format, loader, reload));

		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, "properties");
		ResourceBundle result = null;
		InputStream stream = null;
		if (reload) {
			URL url = loader.getResource(resourceName);
			if (url != null) {
				URLConnection connection = url.openConnection();
				if (connection != null) {
					connection.setUseCaches(false);
					stream = connection.getInputStream();
				}
			}
		} else {
			stream = loader.getResourceAsStream(resourceName);
		}

		if (stream != null) {
			try {
				result = new PropertyResourceBundle(new InputStreamReader(stream, encoding));
			} finally {
				stream.close();
			}
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}
