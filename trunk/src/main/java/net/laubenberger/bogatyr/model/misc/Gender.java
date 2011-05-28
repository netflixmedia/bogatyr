/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.model.misc;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Possible genders
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100817)
 * @since 0.9.1
 */
@XmlRootElement(name = "gender")
public enum Gender {
	MALE("male"), //$NON-NLS-1$
	FEMALE("female"); //$NON-NLS-1$

	private final String gender;

	Gender(final String gender) {
		this.gender = gender;
	}


	public String getGender() {
		return gender;
	}
}	

