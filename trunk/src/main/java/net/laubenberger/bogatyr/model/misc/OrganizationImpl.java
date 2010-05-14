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


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * The implementation of the organization model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
 * @since 0.9.2
 */
@XmlRootElement(name = "organization")
@XmlType(propOrder = {"name", "street", "zip", "city", "country", "phoneNumber", "faxNumber", "email", "url", "persons", "roles"})
public class OrganizationImpl extends ModelAbstract implements Organization {
	private static final long serialVersionUID = -6819817877075750182L;

	private static final Logger log = LoggerFactory.getLogger(OrganizationImpl.class);

	private String name;
	private String street;
	private String zip;
	private String city;
	private Country country;
	private String phoneNumber;
	private String faxNumber;
	private String email;
	private URL url;
	private List<Person> listPerson;
	private List<Role> listRole;
	
	
	public OrganizationImpl() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}


	public OrganizationImpl(final String name, final String street, final String zip,
									final String city, final Country country, final String phoneNumber, final String faxNumber, final String email, final URL website, final List<Person> listPerson, final List<Role> listRole, final Map<String, String> mapTag) {
		super(mapTag);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, street, zip, city, country, phoneNumber, faxNumber, email, website, listPerson, listRole, mapTag));
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.email = email;
		this.url = website;
		this.listPerson = listPerson;
		this.listRole = listRole;
	}


	/*
	 * Overridden methods
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((faxNumber == null) ? 0 : faxNumber.hashCode());
		result = prime * result + ((listPerson == null) ? 0 : listPerson.hashCode());
		result = prime * result + ((listRole == null) ? 0 : listRole.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
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
		OrganizationImpl other = (OrganizationImpl) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (faxNumber == null) {
			if (other.faxNumber != null)
				return false;
		} else if (!faxNumber.equals(other.faxNumber))
			return false;
		if (listPerson == null) {
			if (other.listPerson != null)
				return false;
		} else if (!listPerson.equals(other.listPerson))
			return false;
		if (listRole == null) {
			if (other.listRole != null)
				return false;
		} else if (!listRole.equals(other.listRole))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}


	/*
	 * Implemented methods
	 */

	@Override
	@XmlElement
	public String getName() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(name));
		return name;
	}

	@Override
	@XmlElement
	public String getCity() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(city));
		return city;
	}

	@Override
	@XmlElement
	public Country getCountry() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(country));
		return country;
	}

	@Override
	@XmlElement
	public String getStreet() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(street));
		return street;
	}

	@Override
	@XmlElement
	public String getZip() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(zip));
		return zip;
	}

	@Override
	@XmlElement
	public String getPhoneNumber() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(phoneNumber));
		return phoneNumber;
	}
	
	@Override
	@XmlElement
	public String getFaxNumber() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(faxNumber));
		return faxNumber;
	}
	
	@Override
	@XmlElement
	public String getEmail() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(email));
		return email;
	}

	@Override
	@XmlElement
	public URL getUrl() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(url));
		return url;
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
	public void setCity(final String city) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(city));

		if (!HelperObject.isEquals(city, this.city)) {
			this.city = city;
			setChanged();
			notifyObservers(MEMBER_CITY);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setCountry(final Country country) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(country));

		if (!HelperObject.isEquals(country, this.country)) {
			this.country = country;
			setChanged();
			notifyObservers(MEMBER_COUNTRY);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setStreet(final String street) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(street));

		if (!HelperObject.isEquals(street, this.street)) {
			this.street = street;
			setChanged();
			notifyObservers(MEMBER_STREET);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setZip(final String zip) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(zip));

		if (!HelperObject.isEquals(zip, this.zip)) {
			this.zip = zip;
			setChanged();
			notifyObservers(MEMBER_ZIP);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setPhoneNumber(final String phoneNumber) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(phoneNumber));

		if (!HelperObject.isEquals(this.phoneNumber, phoneNumber)) {
			this.phoneNumber = phoneNumber;
			setChanged();
			notifyObservers(MEMBER_PHONE_NUMBER);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setFaxNumber(final String faxNumber) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(faxNumber));

		if (!HelperObject.isEquals(this.faxNumber, faxNumber)) {
			this.faxNumber = faxNumber;
			setChanged();
			notifyObservers(MEMBER_FAX_NUMBER);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	@Override
	public void setEmail(final String mail) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(mail));

		if (!HelperObject.isEquals(mail, email)) {
			email = mail;
			setChanged();
			notifyObservers(MEMBER_EMAIL);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setUrl(final URL url) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(url));

		if (!HelperObject.isEquals(this.url, url)) {
			this.url = url;
			setChanged();
			notifyObservers(MEMBER_WEBSITE);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	@XmlElement
	public List<Person> getPersons() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(listPerson));
		return listPerson;
	}

	@Override
	public void setPersons(final List<Person> persons) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(persons));

		if (!HelperObject.isEquals(persons, listPerson)) {
			listPerson = persons;
			setChanged();
			notifyObservers(MEMBER_PERSONS);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	@XmlElement
	public List<Role> getRoles() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(listRole));
		return listRole;
	}

	@Override
	public void setRoles(final List<Role> roles) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(roles));

		if (!HelperObject.isEquals(roles, listRole)) {
			listRole = roles;
			setChanged();
			notifyObservers(MEMBER_ROLES);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}	
	
	@Override
	public void addPerson(Person person) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(person));
		if (null == person) {
			throw new RuntimeExceptionIsNull("person"); //$NON-NLS-1$
		}

		if (null == listPerson) {
			listPerson = new ArrayList<Person>();
		}
		listPerson.add(person);
		setChanged();
		notifyObservers(METHOD_ADD_PERSON);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	@Override
	public void addRole(Role role) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(role));
		if (null == role) {
			throw new RuntimeExceptionIsNull("role"); //$NON-NLS-1$
		}

		if (null == listRole) {
			listRole = new ArrayList<Role>();
		}
		listRole.add(role);
		setChanged();
		notifyObservers(METHOD_ADD_ROLE);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	
	/*
	 * Inner classes
	 */

	public static class XmlAdapter extends javax.xml.bind.annotation.adapters.XmlAdapter<OrganizationImpl, Organization> {

		@Override
		public OrganizationImpl marshal(final Organization model) {
			return (OrganizationImpl) model;
		}

		@Override
		public Organization unmarshal(final OrganizationImpl model) {
			return model;
		}
	}
}