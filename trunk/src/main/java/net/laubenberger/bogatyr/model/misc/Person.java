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
import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.laubenberger.bogatyr.model.Model;

/**
 * The interface for the person model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100510)
 * @since 0.9.1
 */
@XmlJavaTypeAdapter(PersonImpl.XmlAdapter.class)
public interface Person extends Model, Address, Phone, Fax, Email, Url {
	String MEMBER_FORENAME = "forename"; //$NON-NLS-1$
	String MEMBER_BIRTHDAY = "birthday"; //$NON-NLS-1$
	String MEMBER_GENDER = "gender"; //$NON-NLS-1$
	String MEMBER_ORGANIZATIONS = "organizations"; //$NON-NLS-1$
	String MEMBER_ROLES = "roles"; //$NON-NLS-1$
	String METHOD_ADD_ORGANIZATION = "addOrganization"; //$NON-NLS-1$
	String METHOD_ADD_ROLE = "addRole"; //$NON-NLS-1$

	String getForename();

	void setForename(String forename);

	Date getBirthday();

	void setBirthday(Date birthday);

	Gender getGender();

	void setGender(Gender gender);
	
	/**
	 * Returns all {@link Organization} of the person.
	 *
	 * @return {@link List} all related {@link Organization} of the person
	 * @since 0.9.2
	 */
	List<Organization> getOrganizations();

	/**
	 * Sets all {@link Organization}.
	 *
	 * @param organizations {@link List} containing all related {@link Organization} of the person
	 * @since 0.9.2
	 */
	void setOrganizations(List<Organization> organizations);

	void addOrganization(Organization organization);
	
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
