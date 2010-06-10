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
 * The interface definition for an address.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.9.1
 */
public interface Address {
	String MEMBER_NAME		= "name"; //$NON-NLS-1$
	String MEMBER_STREET 	= "street"; //$NON-NLS-1$
	String MEMBER_ZIP 		= "zip"; //$NON-NLS-1$
	String MEMBER_CITY 		= "city"; //$NON-NLS-1$
	String MEMBER_COUNTRY 	= "country"; //$NON-NLS-1$

	String getName();

	void setName(String name);

	String getStreet();

	void setStreet(String street);

	String getZip();

	void setZip(String zip);

	String getCity();

	void setCity(String city);

	Country getCountry();

	void setCountry(Country country);
}
