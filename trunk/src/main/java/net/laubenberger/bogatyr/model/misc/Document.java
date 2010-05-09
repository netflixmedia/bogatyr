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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.laubenberger.bogatyr.model.Model;

/**
 * The interface for the document model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100510)
 * @since 0.9.1
 */
@XmlJavaTypeAdapter(DocumentImpl.XmlAdapter.class)
public interface Document extends Model, Url {
	String MEMBER_NAME = "name"; //$NON-NLS-1$
	String MEMBER_VERSION = "version"; //$NON-NLS-1$
	String MEMBER_BUILD = "build"; //$NON-NLS-1$
	String MEMBER_CREATED = "created"; //$NON-NLS-1$
	String MEMBER_UUID = "UUID"; //$NON-NLS-1$
	String MEMBER_ORGANIZATIONS = "organizations"; //$NON-NLS-1$
	String MEMBER_PERSONS = "persons"; //$NON-NLS-1$
	String METHOD_ADD_ORGANIZATION = "addOrganization"; //$NON-NLS-1$
	String METHOD_ADD_PERSON = "addPerson"; //$NON-NLS-1$
	
	String getName();

	void setName(String name);

	BigDecimal getVersion();

	void setVersion(BigDecimal version);

	int getBuild();

	void setBuild(int build);

	Date getCreated();

	void setCreated(Date created);

	List<Organization> getOrganizations();

	void setOrganizations(List<Organization> organizations);

	void addOrganization(Organization organization);
	
	List<Person> getPersons();

	void setPersons(List<Person> persons);

	void addPerson(Person person);
	
	UUID getUUID();

	void setUUID(UUID uuid);
}
