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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.model.misc;


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

