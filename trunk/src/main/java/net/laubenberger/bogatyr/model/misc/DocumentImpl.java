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
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.model.ModelAbstract;


/**
 * The implementation of the document model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100509)
 * @since 0.9.1
 */
@XmlRootElement(name = "document")
@XmlType(propOrder = {"name", "version", "build", "created", "UUID", "url", "organizations", "persons"})
public class DocumentImpl extends ModelAbstract implements Document {
	private static final long serialVersionUID = 5505184629744108815L;

	private static final Logger log = LoggerFactory.getLogger(DocumentImpl.class);

	private String name;
	private BigDecimal version;
	private int build;
	private Date created;
	private UUID uuid;
	private URL url;
	private List<Organization> listOrganization;
	private List<Person> listPerson;

	public DocumentImpl() {
		super();
		log.trace(HelperLog.constructor());
	}

	public DocumentImpl(final String name, final BigDecimal version, final int build,
							  final Date created, final UUID uuid, final URL url, final List<Organization> listOrganization, final List<Person> listPerson, final Map<String, String> mapTag) {
		super(mapTag);
		log.trace(HelperLog.constructor(name, version, build, created, uuid, url, listOrganization, listPerson, mapTag));

		this.name = name;
		this.version = version;
		this.build = build;
		this.created = created;
		this.uuid = uuid;
		this.url = url;
		this.listOrganization = listOrganization;
		this.listPerson = listPerson;
	}


	/*
	 * Overridden methods
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + build;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((listOrganization == null) ? 0 : listOrganization.hashCode());
		result = prime * result + ((listPerson == null) ? 0 : listPerson.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentImpl other = (DocumentImpl) obj;
		if (build != other.build)
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (listOrganization == null) {
			if (other.listOrganization != null)
				return false;
		} else if (!listOrganization.equals(other.listOrganization))
			return false;
		if (listPerson == null) {
			if (other.listPerson != null)
				return false;
		} else if (!listPerson.equals(other.listPerson))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	

	/*
	 * Implemented methods
	 */

	@Override
	@XmlElement
	public int getBuild() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(build));
		return build;
	}

	@Override
	@XmlElement
	public Date getCreated() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(created));
		return created;
	}

	@Override
	@XmlElement
	public UUID getUUID() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(uuid));
		return uuid;
	}

	@Override
	@XmlElement
	public String getName() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(name));
		return name;
	}

	@Override
	@XmlElement
	public BigDecimal getVersion() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(version));
		return version;
	}

	@Override
	@XmlElement
	public URL getUrl() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(url));
		return url;
	}
	
	@Override
	public void setBuild(final int build) {
		log.debug(HelperLog.methodStart(build));

		if (build != this.build) {
			this.build = build;
			setChanged();
			notifyObservers(MEMBER_BUILD);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setCreated(final Date created) {
		log.debug(HelperLog.methodStart(created));

		if (!HelperObject.isEquals(created, this.created)) {
			this.created = created;
			setChanged();
			notifyObservers(MEMBER_CREATED);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setUUID(final UUID uuid) {
		log.debug(HelperLog.methodStart(uuid));

		if (!HelperObject.isEquals(uuid, this.uuid)) {
			this.uuid = uuid;
			setChanged();
			notifyObservers(MEMBER_UUID);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setName(final String name) {
		log.debug(HelperLog.methodStart(name));

		if (!HelperObject.isEquals(name, this.name)) {
			this.name = name;
			setChanged();
			notifyObservers(MEMBER_NAME);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setVersion(final BigDecimal version) {
		log.debug(HelperLog.methodStart(version));

		if (!HelperObject.isEquals(version, this.version)) {
			this.version = version;
			setChanged();
			notifyObservers(MEMBER_VERSION);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setUrl(final URL url) {
		log.debug(HelperLog.methodStart(url));

		if (!HelperObject.isEquals(this.url, url)) {
			this.url = url;
			setChanged();
			notifyObservers(MEMBER_WEBSITE);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	@XmlElement
	public List<Organization> getOrganizations() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(listOrganization));
		return listOrganization;
	}

	@Override
	public void setOrganizations(final List<Organization> organizations) {
		log.debug(HelperLog.methodStart(organizations));

		if (!HelperObject.isEquals(organizations, listOrganization)) {
			listOrganization = organizations;
			setChanged();
			notifyObservers(MEMBER_ORGANIZATIONS);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	@XmlElement
	public List<Person> getPersons() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(listPerson));
		return listPerson;
	}

	@Override
	public void setPersons(final List<Person> persons) {
		log.debug(HelperLog.methodStart(persons));

		if (!HelperObject.isEquals(persons, listPerson)) {
			listPerson = persons;
			setChanged();
			notifyObservers(MEMBER_PERSONS);
		}

		log.debug(HelperLog.methodExit());
	}
	
	
	/*
	 * Inner classes
	 */

	public static class XmlAdapter extends javax.xml.bind.annotation.adapters.XmlAdapter<DocumentImpl, Document> {

		@Override
		public DocumentImpl marshal(final Document model) {
			return (DocumentImpl) model;
		}

		@Override
		public Document unmarshal(final DocumentImpl model) {
			return model;
		}
	}
}