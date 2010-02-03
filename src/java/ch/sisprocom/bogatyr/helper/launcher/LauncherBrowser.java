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
package ch.sisprocom.bogatyr.helper.launcher;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNullOrEmpty;


/**
 * This launcher starts the system browser and displays an URI.
 *
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090610)
 * @since 0.2.0
 */
public abstract class LauncherBrowser {
	
	/**
	 * Displays an {@link URI} in the default browser application.
	 *
	 * @param uri for the browser (e.g. "http://www.sisprocom.ch/")
	 * @throws IOException 
	 * @see URI
	 * @since 0.2.0
	 */
	public static void browse(final URI uri) throws IOException { //$JUnit$
		if (Desktop.isDesktopSupported()) {
			if (null == uri) {
				throw new RuntimeExceptionArgumentIsNull("uri"); //$NON-NLS-1$
			}

			Desktop.getDesktop().browse(uri);
		} else {
			throw new RuntimeException("Browser not supported by your machine!"); //$NON-NLS-1$
		}
	}	
	
	/**
	 * Displays an {@link String} in the default browser application.
	 *
	 * @param url for the browser (e.g. "www.sisprocom.ch")
	 * @throws IOException 
	 * @throws URISyntaxException
	 * @since 0.2.0
	 */
	public static void browse(final String url) throws IOException, URISyntaxException { //$JUnit$
		if (!HelperString.isValid(url)) {
			throw new RuntimeExceptionArgumentIsNullOrEmpty("url"); //$NON-NLS-1$
		}
		
		final String prefix = "://"; //$NON-NLS-1$
		
		if (url.contains(prefix)) {
			browse(new URI(url));
		} else {
			//best guess as protocol is http
			browse(new URI("http://" + url)); //$NON-NLS-1$
		}
	}	
}
