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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;


/**
 * This control sends an URI to the default mail application.
 *
 * @author Stefan Laubenberger
 * @version 20090516
 */
public abstract class ControlMail {
	
	/**
	 * Send an {@link URI} to the default mail application.
	 *
	 * @param uri for the mail application (e.g. "yourname@yourmail.com")
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
}
