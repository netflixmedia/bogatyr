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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.model.ModelAbstract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The implementation of the document model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.6 (20110527)
 * @since 0.9.1
 */
@XmlRootElement(name = "document")
@XmlType(propOrder = {Document.MEMBER_NAME, Document.MEMBER_VERSION, Document.MEMBER_BUILD, Document.MEMBER_CREATED, Document.MEMBER_LANGUAGE, Url.MEMBER_URL, Document.MEMBER_ORGANIZATIONS, Document.MEMBER_PERSONS})
public class DocumentImpl extends ModelAbstract implements Document {
	private static final long serialVersionUID = 5505184629744108815L;

	private static final Logger log = LoggerFactory.getLogger(DocumentImpl.class);

	private String name;
	private BigDecimal version;
	private int build;
	private Date created;
	private Language language;
	private URL url;
	private List<Organization> organizations;
	private List<Person> persons;

	public DocumentImpl() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public DocumentImpl(final String name, final BigDecimal version, final int build,
							  final Date created, final Language language, final URL url, final List<Organization> organizations, final List<Person> persons, final UUID uuid, final Map<String, String> mapTag) {
		super(uuid, mapTag);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, version, build, created, language, url, organizations, persons, uuid, mapTag));

		this.name = name;
		this.version = version;
		this.build = build;
		this.created = created;
		this.language = language;
		this.url = url;
		this.organizations = organizations;
		this.persons = persons;
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
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organizations == null) ? 0 : organizations.hashCode());
		result = prime * result + ((persons == null) ? 0 : persons.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (getClass() != obj.getClass()) return false;
		DocumentImpl other = (DocumentImpl) obj;
		if (build != other.build) return false;
		if (created == null) {
			if (other.created != null) return false;
		} else if (!created.equals(other.created)) return false;
		if (language != other.language) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (organizations == null) {
			if (other.organizations != null) return false;
		} else if (!organizations.equals(other.organizations)) return false;
		if (persons == null) {
			if (other.persons != null) return false;
		} else if (!persons.equals(other.persons)) return false;
		if (url == null) {
			if (other.url != null) return false;
		} else if (!url.equals(other.url)) return false;
		if (version == null) {
			if (other.version != null) return false;
		} else if (!version.equals(other.version)) return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[instantiated=" + getInstantiated() +  //$NON-NLS-1$
			", isNotifyEnabled=" + isNotifyEnabled() + //$NON-NLS-1$
			", uuid=" + getUUID() +  //$NON-NLS-1$
			", mapTag=" + getTags() +  //$NON-NLS-1$
			", name=" + name + //$NON-NLS-1$ 
			", version=" + version + //$NON-NLS-1$ 
			", build=" + build + //$NON-NLS-1$ 
			", created=" + created + //$NON-NLS-1$ 
			", language=" + language + //$NON-NLS-1$ 
			", url=" + url + //$NON-NLS-1$ 
			", organizations=" + organizations + //$NON-NLS-1$ 
			", persons=" + persons + //$NON-NLS-1$ 
			']'; 
	}

	
	/*
	 * Implemented methods
	 */

	@Override
	@XmlElement
	public int getBuild() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(build));
		return build;
	}

	@Override
	@XmlElement
	public Date getCreated() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(created));
		return created;
	}

	@Override
	@XmlElement
	public Language getLanguage() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(language));
		return language;
	}
	
	@Override
	@XmlElement
	public String getName() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(name));
		return name;
	}

	@Override
	@XmlElement
	public BigDecimal getVersion() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(version));
		return version;
	}
	
	@Override
	@XmlElement
	public URL getUrl() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(url));
		return url;
	}
	
	@Override
	public void setBuild(final int build) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(build));

		if (build != this.build) {
			this.build = build;
			setChanged();
			notifyObservers(MEMBER_BUILD);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setCreated(final Date created) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(created));

		if (!HelperObject.isEquals(created, this.created)) {
			this.created = created;
			setChanged();
			notifyObservers(MEMBER_CREATED);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setLanguage(final Language language) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(language));

		if (!HelperObject.isEquals(language, this.language)) {
			this.language = language;
			setChanged();
			notifyObservers(MEMBER_LANGUAGE);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	@Override
	public void setName(final String name) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(name));

		if (!HelperObject.isEquals(name, this.name)) {
			this.name = name;
			setChanged();
			notifyObservers(MEMBER_NAME);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setVersion(final BigDecimal version) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(version));

		if (!HelperObject.isEquals(version, this.version)) {
			this.version = version;
			setChanged();
			notifyObservers(MEMBER_VERSION);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setUrl(final URL url) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(url));

		if (!HelperObject.isEquals(this.url, url)) {
			this.url = url;
			setChanged();
			notifyObservers(MEMBER_URL);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	@XmlElement
	public List<Organization> getOrganizations() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(organizations));
		return organizations;
	}

	@Override
	public void setOrganizations(final List<Organization> organizations) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(organizations));

		if (!HelperObject.isEquals(this.organizations, organizations)) {
			this.organizations = organizations;
			setChanged();
			notifyObservers(MEMBER_ORGANIZATIONS);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	@XmlElement
	public List<Person> getPersons() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(persons));
		return persons;
	}

	@Override
	public void setPersons(final List<Person> persons) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(persons));

		if (!HelperObject.isEquals(this.persons, persons)) {
			this.persons = persons;
			setChanged();
			notifyObservers(MEMBER_PERSONS);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	@Override
	public void addOrganization(final Organization organization) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(organization));
		if (null == organization) {
			throw new RuntimeExceptionIsNull("organization"); //$NON-NLS-1$
		}

		if (null == organizations) {
			organizations = new ArrayList<Organization>();
		}
		organizations.add(organization);
		setChanged();
		notifyObservers(METHOD_ADD_ORGANIZATION);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void addPerson(final Person person) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(person));
		if (null == person) {
			throw new RuntimeExceptionIsNull("person"); //$NON-NLS-1$
		}

		if (null == persons) {
			persons = new ArrayList<Person>();
		}
		persons.add(person);
		setChanged();
		notifyObservers(METHOD_ADD_PERSON);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
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