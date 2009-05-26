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
package ch.sisprocom.bogatyr.helper.control;

import ch.sisprocom.bogatyr.helper.HelperString;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * This control displays an URI in the default browser.
 *
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.20
 */
public abstract class ControlBrowser {
	
	/**
	 * Displays an {@link URI} in the default browser application.
	 *
	 * @param uri for the browser (e.g. "http://www.sisprocom.ch/")
	 * @throws IOException 
	 */
	public static void browse(final URI uri) throws IOException { //$JUnit
		if (Desktop.isDesktopSupported()) {
			if (null == uri) {
				throw new IllegalArgumentException("uri is null!"); //$NON-NLS-1$
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
	 */
	public static void browse(final String url) throws IOException, URISyntaxException { //$JUnit
		if (!HelperString.isValid(url)) {
			throw new IllegalArgumentException("url is null or empty!"); //$NON-NLS-1$
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
