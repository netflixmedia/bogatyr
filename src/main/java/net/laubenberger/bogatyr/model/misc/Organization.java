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

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.laubenberger.bogatyr.model.Model;
import net.laubenberger.bogatyr.model.misc.OrganizationImpl.XmlAdapter;

/**
 * The interface for the organization model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101227)
 * @since 0.9.2
 */
@XmlJavaTypeAdapter(XmlAdapter.class)
public interface Organization extends Model, Address, Phone, Fax, Email, Url {
	String MEMBER_PERSONS 		= "persons"; //$NON-NLS-1$
	String MEMBER_ROLES 			= "roles"; //$NON-NLS-1$
	String METHOD_ADD_PERSON 	= "addPerson"; //$NON-NLS-1$
	String METHOD_ADD_ROLE 		= "addRole"; //$NON-NLS-1$

	/**
	 * Returns all {@link Person} of the organization.
	 *
	 * @return {@link List} all related {@link Person} of the organization
	 * @since 0.9.2
	 */
	List<Person> getPersons();

	/**
	 * Sets all {@link Person}.
	 *
	 * @param persons {@link List} containing all related {@link Person} of the organization
	 * @since 0.9.2
	 */
	void setPersons(List<Person> persons);

	void addPerson(Person person);
	
	/**
	 * Returns all {@link Role} of the organization.
	 *
	 * @return {@link List} all related {@link Role} of the organization
	 * @since 0.9.2
	 */
	List<Role> getRoles();

	/**
	 * Sets all {@link Role}.
	 *
	 * @param roles {@link List} containing all related {@link Role} of the organization
	 * @since 0.9.2
	 */
	void setRoles(List<Role> roles);

	void addRole(Role role);
}
