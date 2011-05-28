/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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
import java.util.UUID;

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
 * @version 0.9.6 (20110517)
 * @since 0.9.1
 */
@XmlRootElement(name = "person")
@XmlType(propOrder = {Address.MEMBER_NAME, Person.MEMBER_FORENAME, Person.MEMBER_BIRTHDAY, Person.MEMBER_GENDER, Person.MEMBER_LANGUAGE, Address.MEMBER_STREET, Address.MEMBER_ZIP, Address.MEMBER_CITY, Address.MEMBER_COUNTRY, Phone.MEMBER_PHONE_NUMBER, Fax.MEMBER_FAX_NUMBER, Email.MEMBER_EMAIL, Url.MEMBER_URL, Person.MEMBER_ORGANIZATIONS, Person.MEMBER_ROLES})
public class PersonImpl extends ModelAbstract implements Person {
	private static final long serialVersionUID = -6819817877075750182L;

	private static final Logger log = LoggerFactory.getLogger(PersonImpl.class);

	private String name;
	private String forename;
	private Date birthday;
	private Gender gender;
	private Language language;
	private String street;
	private String zip;
	private String city;
	private Country country;
	private String phoneNumber;
	private String faxNumber;
	private String email;
	private URL url;
	private List<Organization> organizations;
	private List<Role> roles;


	public PersonImpl() {
		super();
	}

	public PersonImpl(final String name, final String forename, final Date birthday,
							final Gender gender, final Language language, final String street, final String zip, final String city,
							final Country country, final String phoneNumber, final String faxNumber, final String email, final URL url, final List<Organization> organizations, final List<Role> roles, final UUID uuid, final Map<String, String> mapTag) {
		super(uuid, mapTag);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, forename, birthday, gender, language, street, zip, city, country, phoneNumber, faxNumber, email, url, organizations, roles, uuid, mapTag));
		this.name = name;
		this.forename = forename;
		this.birthday = birthday;
		this.gender = gender;
		this.language = language;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.faxNumber = faxNumber;
		this.email = email;
		this.url = url;
		this.organizations = organizations;
		this.roles = roles;
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return getClass().getName() + "[instantiated=" + getInstantiated() +  //$NON-NLS-1$
			", isNotifyEnabled=" + isNotifyEnabled() + //$NON-NLS-1$
			", uuid=" + getUUID() +  //$NON-NLS-1$
			", mapTag=" + getTags() +  //$NON-NLS-1$
			", name=" + name + //$NON-NLS-1$ 
			", forename=" + forename + //$NON-NLS-1$ 
			", birthday=" + birthday + //$NON-NLS-1$ 
			", gender=" + gender + //$NON-NLS-1$ 
			", language=" + language + //$NON-NLS-1$ 
			", street=" + street + //$NON-NLS-1$ 
			", zip=" + zip + //$NON-NLS-1$ 
			", city=" + city + //$NON-NLS-1$ 
			", country=" + country + //$NON-NLS-1$ 
			", phoneNumber=" + phoneNumber + //$NON-NLS-1$ 
			", faxNumber=" + faxNumber + //$NON-NLS-1$ 
			", email=" + email + //$NON-NLS-1$ 
			", url=" + url + //$NON-NLS-1$ 
			", organizations=" + organizations + //$NON-NLS-1$ 
			", roles=" + roles + //$NON-NLS-1$ 
			']'; 
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
	@XmlElement
	public Date getBirthday() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(birthday));
		return birthday;
	}

	@Override
	@XmlElement
	public String getForename() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(forename));
		return forename;
	}

	@Override
	@XmlElement
	public Gender getGender() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(gender));
		return gender;
	}

	@Override
	@XmlElement
	public Language getLanguage() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(language));
		return language;
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
			notifyObservers(MEMBER_URL);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setBirthday(final Date birthday) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(birthday));

		if (!HelperObject.isEquals(birthday, this.birthday)) {
			this.birthday = birthday;
			setChanged();
			notifyObservers(MEMBER_BIRTHDAY);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setForename(final String forename) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(forename));

		if (!HelperObject.isEquals(forename, this.forename)) {
			this.forename = forename;
			setChanged();
			notifyObservers(MEMBER_FORENAME);
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setGender(final Gender gender) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(gender));

		if (gender != this.gender) {
			this.gender = gender;
			setChanged();
			notifyObservers(MEMBER_GENDER);
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
	public List<Role> getRoles() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(roles));
		return roles;
	}

	@Override
	public void setRoles(final List<Role> roles) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(roles));

		if (!HelperObject.isEquals(this.roles, roles)) {
			this.roles = roles;
			setChanged();
			notifyObservers(MEMBER_ROLES);
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
	public void addRole(final Role role) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(role));
		if (null == role) {
			throw new RuntimeExceptionIsNull("role"); //$NON-NLS-1$
		}

		if (null == roles) {
			roles = new ArrayList<Role>();
		}
		roles.add(role);
		setChanged();
		notifyObservers(METHOD_ADD_ROLE);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	
	/*
	 * Inner classes
	 */

	public static class XmlAdapterPerson extends javax.xml.bind.annotation.adapters.XmlAdapter<PersonImpl, Person> {

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