/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
import java.util.Locale;


/**
 * This control opens an email address with the default mail application.
 *
 * @author Stefan Laubenberger
 * @version 20090520
 */
public abstract class ControlMail {

	/**
	 * Opens the default mail application.
	 *
	 * @throws IOException 
	 */
	public static void mail() throws IOException { //$JUnit
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().mail();
		} else {
			throw new RuntimeException("Mail application not supported by your machine!"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Opens an email address given as {@link URI} with the default mail application.
	 *
	 * @param uri for the mail application (e.g. "mailto:yourname@yourmail.com"). It supports CC, BCC, SUBJECT, and BODY.
	 * @throws IOException 
	 */
	public static void mail(final URI uri) throws IOException { //$JUnit
		if (Desktop.isDesktopSupported()) {
			if (null == uri) {
				throw new IllegalArgumentException("uri is null!"); //$NON-NLS-1$
			}

			Desktop.getDesktop().mail(uri);
		} else {
			throw new RuntimeException("Mail application not supported by your machine!"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Opens an email address given as {@link String} with the default mail application.
	 *
	 * @param emailAddress for the mail application (e.g. "yourname@yourmail.com")
	 * @throws IOException 
	 * @throws URISyntaxException 
	 */
	public static void mail(final String emailAddress) throws IOException, URISyntaxException { //$JUnit
		if (!HelperString.isValid(emailAddress)) {
			throw new IllegalArgumentException("emailAddress is null or empty!"); //$NON-NLS-1$
		}
		
		final String prefix = "mailto:"; //$NON-NLS-1$
		
		if (emailAddress.toLowerCase(Locale.getDefault()).startsWith(prefix)) {
			mail(new URI(emailAddress));
		} else {
			mail(new URI(prefix + emailAddress));
		}
	}
}