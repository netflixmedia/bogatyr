/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This launcher starts the system mail application.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.7.0
 */
public abstract class LauncherMail {

	/**
	 * Opens the default mail application.
	 *
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void mail() throws IOException { //$JUnit$
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
	 * @see URI
	 * @since 0.7.0
	 */
	public static void mail(final URI uri) throws IOException { //$JUnit$
		if (Desktop.isDesktopSupported()) {
			if (null == uri) {
				throw new RuntimeExceptionIsNull("uri"); //$NON-NLS-1$
			}

			Desktop.getDesktop().mail(uri);
		} else {
			throw new RuntimeException("Mail application not supported by your machine!"); //$NON-NLS-1$
		}
	}
	
	/**
	 * Opens an email with a given subject, body and addresses with the default mail application.
	 *
	 * @param subject for the mail
	 * @param body for the mail
	 * @param emailAddresses for the mail (e.g. "yourname@yourmail.com")
	 * @throws IOException 
	 * @throws URISyntaxException
	 * @since 0.7.0
	 */
	public static void mail(final String subject, final String body, final String... emailAddresses) throws IOException, URISyntaxException { //$JUnit$
		if (!HelperArray.isValid(emailAddresses)) {
			throw new RuntimeExceptionIsNullOrEmpty("emailAddresses"); //$NON-NLS-1$
		}

        final StringBuilder sb = new StringBuilder();
        int ii = 0;
        for (String address : emailAddresses) {
            if (0 < ii) {
                sb.append(HelperString.COMMA);
            }
            sb.append(address);
            ii++;

        }
        sb.append("?subject="); //$NON-NLS-1$
        sb.append(getValidText(subject));
        sb.append("&body="); //$NON-NLS-1$
        sb.append(getValidText(body).replace(HelperString.NEW_LINE, "%0A")); //$NON-NLS-1$
        
        final String addresses = sb.toString();

		final String prefix = "mailto:"; //$NON-NLS-1$
		
		mail(new URI(prefix + addresses.replaceAll(" ", "%20")));   //$NON-NLS-1$//$NON-NLS-2$
	}
	
	private static String getValidText(final String input) {
	    if (null != input) {
			final StringBuffer sb = new StringBuffer(input.length());
	
		    for (char c : input.toCharArray()) {
		    	int ci = 0xffff & c;
	            if (ci < 160 ) {
	                // nothing special only 7 Bit
	                sb.append(c);
	            } else {
	                // Not 7 Bit use the unicode system
	                sb.append("?"); //$NON-NLS-1$
	            }
            }
		    return sb.toString();
		}
	    return null;
	}
}
