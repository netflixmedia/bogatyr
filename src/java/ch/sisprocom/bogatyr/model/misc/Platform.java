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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.misc;


/**
 * Possible platforms
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091224)
 * @since 0.9.0
 */
public enum Platform {
	ANY("Any platform"), //$NON-NLS-1$
	MAC_OSX("Apple Mac OSX"), //$NON-NLS-1$
	WINDOWS("Microsoft Windows"), //$NON-NLS-1$
	UNIX("UNIX/Linux"); //$NON-NLS-1$

	private final String name;
	
	Platform(final String name) {
		this.name = name;
	}


    public String getName() {
		return name;
	}
}	

