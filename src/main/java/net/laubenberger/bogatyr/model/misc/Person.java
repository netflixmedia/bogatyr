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

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.laubenberger.bogatyr.model.Model;

/**
 * The interface for the person model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.1
 */
@XmlJavaTypeAdapter(PersonImpl.XmlAdapter.class)
public interface Person extends Model, Address, Email, Website {
	String MEMBER_FORENAME = "forename"; //$NON-NLS-1$
	String MEMBER_BIRTHDAY = "birthday"; //$NON-NLS-1$
	String MEMBER_GENDER = "gender"; //$NON-NLS-1$

	String getForename();

	void setForename(String forename);

	Date getBirthday();

	void setBirthday(Date birthday);

	Gender getGender();

	void setGender(Gender gender);
}
