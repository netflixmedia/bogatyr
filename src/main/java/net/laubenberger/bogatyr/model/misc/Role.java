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

package net.laubenberger.bogatyr.model.misc;


/**
 * Possible roles in a software development project
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100509)
 * @since 0.9.2
 */
public enum Role {
	DEVELOPER("Developer"), //$NON-NLS-1$
	TESTER("Tester"), //$NON-NLS-1$
	CONTRIBUTOR("Contributor"), //$NON-NLS-1$
	ARCHITECT("Architect"), //$NON-NLS-1$
	PROJECT_MANAGER("Project manager"), //$NON-NLS-1$
	DESIGNER("Designer"), //$NON-NLS-1$
	WRITER("Writer"), //$NON-NLS-1$
	REVIEWER("Reviewer"), //$NON-NLS-1$
	ADMINISTRATOR("Administrator"), //$NON-NLS-1$
	USER("User"), //$NON-NLS-1$
	SPONSOR("Sponsor"), //$NON-NLS-1$
	CLIENT("Client"), //$NON-NLS-1$
	MANUFACTURER("Manufacturer"), //$NON-NLS-1$
	PUBLISHER("Publisher"), //$NON-NLS-1$
	OWNER("Owner"); //$NON-NLS-1$

	private final String name;

	Role(final String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}
}	

