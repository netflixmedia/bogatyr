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
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.model.ModelAbstract;


/**
 * The implementation of the person model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100510)
 * @since 0.9.1
 */
@XmlRootElement(name = "person")
@XmlType(propOrder = {"name", "forename", "birthday", "gender", "street", "zip", "city", "country", "phoneNumber", "faxNumber", "email", "url", "organizations", "roles"})
public class PersonImpl extends ModelAbstract implements Person {
	private static final long serialVersionUID = -6819817877075750182L;

	private static final Logger log = LoggerFactory.getLogger(PersonImpl.class);

	private String name;
	private String forename;
	private Date birthday;
	private Gender gender;
	private String street;
	private String zip;
	private String city;
	private Country country;
	private String phoneNumber;
	private String faxNumber;
	private String email;
	private URL url;
	private List<Organization> listOrganization;
	private List<Role> listRole;


	public PersonImpl() {
		super();
	}

	public PersonImpl(final String name, final String forename, final Date birthday,
							final Gender gender, final String street, final String zip, final String city,
							final Country country, final String phoneNumber, final String faxNumber, final String email, final URL website, final List<Organization> listOrganization, final List<Role> listRole, final Map<String, String> mapTag) {
		super(mapTag);
		log.trace(HelperLog.constructor(name, forename, birthday, gender, street, zip, city, country, phoneNumber, faxNumber, email, website, mapTag));
		this.name = name;
		this.forename = forename;
		this.birthday = birthday;
		this.gender = gender;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.email = email;
		this.url = website;
		this.listOrganization = listOrganization;
		this.listRole = listRole;
	}


	/*
	 * Overridden methods
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((faxNumber == null) ? 0 : faxNumber.hashCode());
		result = prime * result + ((forename == null) ? 0 : forename.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((listOrganization == null) ? 0 : listOrganization.hashCode());
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
		PersonImpl other = (PersonImpl) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
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
		if (forename == null) {
			if (other.forename != null)
				return false;
		} else if (!forename.equals(other.forename))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (listOrganization == null) {
			if (other.listOrganization != null)
				return false;
		} else if (!listOrganization.equals(other.listOrganization))
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
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(name));
		return name;
	}

	@Override
	@XmlElement
	public String getCity() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(city));
		return city;
	}

	@Override
	@XmlElement
	public Country getCountry() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(country));
		return country;
	}

	@Override
	@XmlElement
	public String getStreet() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(street));
		return street;
	}

	@Override
	@XmlElement
	public String getZip() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(zip));
		return zip;
	}

	@Override
	@XmlElement
	public String getPhoneNumber() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(phoneNumber));
		return phoneNumber;
	}
	
	@Override
	@XmlElement
	public String getFaxNumber() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(faxNumber));
		return faxNumber;
	}
	
	@Override
	@XmlElement
	public String getEmail() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(email));
		return email;
	}

	@Override
	@XmlElement
	public URL getUrl() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(url));
		return url;
	}

	@Override
	@XmlElement
	public Date getBirthday() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(birthday));
		return birthday;
	}

	@Override
	@XmlElement
	public String getForename() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(forename));
		return forename;
	}

	@Override
	@XmlElement
	public Gender getGender() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(gender));
		return gender;
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
	public void setCity(final String city) {
		log.debug(HelperLog.methodStart(city));

		if (!HelperObject.isEquals(city, this.city)) {
			this.city = city;
			setChanged();
			notifyObservers(MEMBER_CITY);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setCountry(final Country country) {
		log.debug(HelperLog.methodStart(country));

		if (!HelperObject.isEquals(country, this.country)) {
			this.country = country;
			setChanged();
			notifyObservers(MEMBER_COUNTRY);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setStreet(final String street) {
		log.debug(HelperLog.methodStart(street));

		if (!HelperObject.isEquals(street, this.street)) {
			this.street = street;
			setChanged();
			notifyObservers(MEMBER_STREET);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setZip(final String zip) {
		log.debug(HelperLog.methodStart(zip));

		if (!HelperObject.isEquals(zip, this.zip)) {
			this.zip = zip;
			setChanged();
			notifyObservers(MEMBER_ZIP);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setPhoneNumber(final String phoneNumber) {
		log.debug(HelperLog.methodStart(phoneNumber));

		if (!HelperObject.isEquals(this.phoneNumber, phoneNumber)) {
			this.phoneNumber = phoneNumber;
			setChanged();
			notifyObservers(MEMBER_PHONE_NUMBER);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setFaxNumber(final String faxNumber) {
		log.debug(HelperLog.methodStart(faxNumber));

		if (!HelperObject.isEquals(this.faxNumber, faxNumber)) {
			this.faxNumber = faxNumber;
			setChanged();
			notifyObservers(MEMBER_FAX_NUMBER);
		}

		log.debug(HelperLog.methodExit());
	}
	
	@Override
	public void setEmail(final String mail) {
		log.debug(HelperLog.methodStart(mail));

		if (!HelperObject.isEquals(mail, email)) {
			email = mail;
			setChanged();
			notifyObservers(MEMBER_EMAIL);
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
	public void setBirthday(final Date birthday) {
		log.debug(HelperLog.methodStart(birthday));

		if (!HelperObject.isEquals(birthday, this.birthday)) {
			this.birthday = birthday;
			setChanged();
			notifyObservers(MEMBER_BIRTHDAY);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setForename(final String forename) {
		log.debug(HelperLog.methodStart(forename));

		if (!HelperObject.isEquals(forename, this.forename)) {
			this.forename = forename;
			setChanged();
			notifyObservers(MEMBER_FORENAME);
		}

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void setGender(final Gender gender) {
		log.debug(HelperLog.methodStart(gender));

		if (gender != this.gender) {
			this.gender = gender;
			setChanged();
			notifyObservers(MEMBER_GENDER);
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
	public List<Role> getRoles() {
		log.debug(HelperLog.methodStart());

		log.debug(HelperLog.methodExit(listRole));
		return listRole;
	}

	@Override
	public void setRoles(final List<Role> roles) {
		log.debug(HelperLog.methodStart(roles));

		if (!HelperObject.isEquals(roles, listRole)) {
			listRole = roles;
			setChanged();
			notifyObservers(MEMBER_ROLES);
		}

		log.debug(HelperLog.methodExit());
	}	
	
	@Override
	public void addOrganization(Organization organization) {
		log.debug(HelperLog.methodStart(organization));
		if (null == organization) {
			throw new RuntimeExceptionIsNull("organization"); //$NON-NLS-1$
		}

		if (null == listOrganization) {
			listOrganization = new ArrayList<Organization>();
		}
		listOrganization.add(organization);
		setChanged();
		notifyObservers(METHOD_ADD_ORGANIZATION);

		log.debug(HelperLog.methodExit());
	}

	@Override
	public void addRole(Role role) {
		log.debug(HelperLog.methodStart(role));
		if (null == role) {
			throw new RuntimeExceptionIsNull("role"); //$NON-NLS-1$
		}

		if (null == listRole) {
			listRole = new ArrayList<Role>();
		}
		listRole.add(role);
		setChanged();
		notifyObservers(METHOD_ADD_ROLE);

		log.debug(HelperLog.methodExit());
	}
	
	
	/*
	 * Inner classes
	 */

	public static class XmlAdapter extends javax.xml.bind.annotation.adapters.XmlAdapter<PersonImpl, Person> {

		@Override
		public PersonImpl marshal(final Person model) {
			return (PersonImpl) model;
		}

		@Override
		public Person unmarshal(final PersonImpl model) {
			return model;
		}
	}
}