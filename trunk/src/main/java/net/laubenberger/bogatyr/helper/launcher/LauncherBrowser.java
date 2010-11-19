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

package net.laubenberger.bogatyr.helper.launcher;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This launcher starts the system browser and displays an URI.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101119)
 * @since 0.2.0
 */
public abstract class LauncherBrowser {
	private static final Logger log = LoggerFactory.getLogger(LauncherBrowser.class);

	/**
	 * Displays an {@link URI} in the default browser application.
	 *
	 * @param uri for the browser (e.g. "http://www.laubenberger.net/")
	 * @throws IOException
	 * @see URI
	 * @since 0.2.0
	 */
	public static void browse(final URI uri) throws IOException { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(uri));
		if (null == uri) {
			throw new RuntimeExceptionIsNull("uri"); //$NON-NLS-1$
		}

		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().browse(uri);
		} else {
			throw new RuntimeException("Browser not supported by your machine"); //$NON-NLS-1$
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Displays an {@link String} in the default browser application.
	 *
	 * @param url for the browser (e.g. "www.laubenberger.net")
	 * @throws IOException
	 * @throws URISyntaxException
	 * @since 0.2.0
	 */
	public static void browse(final String url) throws IOException, URISyntaxException { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(url));
		if (null == url) {
			throw new RuntimeExceptionIsNull("url"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(url)) {
			throw new RuntimeExceptionIsEmpty("url"); //$NON-NLS-1$
		}

		final String prefix = "://"; //$NON-NLS-1$

		if (url.contains(prefix)) {
			browse(new URI(url));
		} else {
			//best guess as protocol is http
			browse(new URI("http://" + url)); //$NON-NLS-1$
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}
