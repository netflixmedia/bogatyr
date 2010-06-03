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

package net.laubenberger.bogatyr.misc.extendedObject;

import java.util.Date;

import net.laubenberger.bogatyr.helper.HelperObject;

/**
 * This is the skeleton for all extended objects.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100603)
 * @since 0.9.0
 */
public abstract class ExtendedObjectAbstract implements ExtendedObject {
	private final Date instantiationDate = new Date();


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instantiationDate == null) ? 0 : instantiationDate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		ExtendedObjectAbstract other = (ExtendedObjectAbstract) obj;
		if (instantiationDate == null) {
			if (other.instantiationDate != null) return false;
		} else if (!instantiationDate.equals(other.instantiationDate)) return false;
		return true;
	}

	
	/*
	 * Implemented methods
	 */

	@Override
	public Date getInstantiationDate() {
		return instantiationDate;
	}
}