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
package ch.customcode.bogatyr.helper.launcher;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This launcher starts the system browser and displays an URI.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.2.0
 */
public abstract class LauncherBrowser {
	private static final Logger log = LoggerFactory.getLogger(LauncherBrowser.class);
	
	/**
	 * Displays an {@link URI} in the default browser application.
	 *
	 * @param uri for the browser (e.g. "http://www.customcode.ch/")
	 * @throws IOException 
	 * @see URI
	 * @since 0.2.0
	 */
	public static void browse(final URI uri) throws IOException { //$JUnit$
		log.debug(HelperLog.methodStart(uri));
		
		if (Desktop.isDesktopSupported()) {
			if (null == uri) {
				throw new RuntimeExceptionIsNull("uri"); //$NON-NLS-1$
			}

			Desktop.getDesktop().browse(uri);
		} else {
			throw new RuntimeException("Browser not supported by your machine!"); //$NON-NLS-1$
		}
		log.debug(HelperLog.methodExit());
	}	
	
	/**
	 * Displays an {@link String} in the default browser application.
	 *
	 * @param url for the browser (e.g. "www.customcode.ch")
	 * @throws IOException 
	 * @throws URISyntaxException
	 * @since 0.2.0
	 */
	public static void browse(final String url) throws IOException, URISyntaxException { //$JUnit$
		log.debug(HelperLog.methodStart(url));
		
		if (!HelperString.isValid(url)) {
			throw new RuntimeExceptionIsNullOrEmpty("url"); //$NON-NLS-1$
		}
		
		final String prefix = "://"; //$NON-NLS-1$
		
		if (url.contains(prefix)) {
			browse(new URI(url));
		} else {
			//best guess as protocol is http
			browse(new URI("http://" + url)); //$NON-NLS-1$
		}
		log.debug(HelperLog.methodExit());
	}	
}
