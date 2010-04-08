/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.model.misc;


import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.model.ModelAbstract;


/**
 * The implementation of the publisher model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100301)
 * @since 0.9.0
 */
@XmlRootElement(name = "publisher")
@XmlType(propOrder={"name", "street", "zip", "city", "country", "email", "website"})
public class PublisherImpl extends ModelAbstract implements Publisher {
	private static final long serialVersionUID = 3542143128600081015L;

	private String name;
	private String street;
	private String zip;
	private String city;
	private String country;
	private String email;
	private URL website;
    
	
    public PublisherImpl() {
		super();
	}

	public PublisherImpl(final String name, final String street, final String zip, final String city,
			final String country, final String email, final URL website) {
		super();
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.email = email;
		this.website = website;
	}


	/*
     * Overridden methods
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((null == city) ? 0 : city.hashCode());
		result = prime * result + ((null == country) ? 0 : country.hashCode());
		result = prime * result + ((null == email) ? 0 : email.hashCode());
		result = prime * result + ((null == name) ? 0 : name.hashCode());
		result = prime * result + ((null == street) ? 0 : street.hashCode());
		result = prime * result + ((null == website) ? 0 : website.hashCode());
		result = prime * result + ((null == zip) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
		final PublisherImpl other = (PublisherImpl) obj;
		if (null == city) {
            if (null != other.city) {
                return false;
            }
		} else if (!city.equals(other.city)) {
            return false;
        }
		if (null == country) {
            if (null != other.country) {
                return false;
            }
		} else if (!country.equals(other.country)) {
            return false;
        }
		if (null == email) {
            if (null != other.email) {
                return false;
            }
		} else if (!email.equals(other.email)) {
            return false;
        }
		if (null == name) {
            if (null != other.name) {
                return false;
            }
		} else if (!name.equals(other.name)) {
            return false;
        }
		if (null == street) {
            if (null != other.street) {
                return false;
            }
		} else if (!street.equals(other.street)) {
            return false;
        }
		if (null == website) {
            if (null != other.website) {
                return false;
            }
		} else if (!website.equals(other.website)) {
            return false;
        }
		if (null == zip) {
            if (null != other.zip) {
                return false;
            }
		} else if (!zip.equals(other.zip)) {
            return false;
        }
		return true;
	}

	
	/*
     * Implemented methods
     */
	@Override
    @XmlElement
	public String getName() {
		return name;
	}

	@Override
	@XmlElement
	public String getEmail() {
		return email;
	}

	@Override
    @XmlElement
	public URL getWebsite() {
		return website;
	}
	
	@Override
	public void setName(final String name) {
        if (!HelperObject.isEquals(name, this.name)) {
            this.name = name;
            setChanged();
            notifyObservers(MEMBER_NAME);
        }
	}

	@Override
	public void setEmail(final String mail) {
        if (!HelperObject.isEquals(mail, email)) {
            email = mail;
            setChanged();
            notifyObservers(MEMBER_EMAIL);
        }
	}

	@Override
	public void setWebsite(final URL url) {
        if (!HelperObject.isEquals(url, website)) {
            website = url;
            setChanged();
            notifyObservers(MEMBER_WEBSITE);
        }
	}
	
	@Override
	@XmlElement
	public String getCity() {
		return city;
	}

	@Override
	@XmlElement
	public String getCountry() {
		return country;
	}

	@Override
	@XmlElement
	public String getStreet() {
		return street;
	}

	@Override
	@XmlElement
	public String getZip() {
		return zip;
	}

	@Override
	public void setCity(final String city) {
        if (!HelperObject.isEquals(city, this.city)) {
            this.city = city;
            setChanged();
            notifyObservers(MEMBER_CITY);
        }
	}

	@Override
	public void setCountry(final String country) {
        if (!HelperObject.isEquals(country, this.country)) {
            this.country = country;
            setChanged();
            notifyObservers(MEMBER_COUNTRY);
        }
	}

	@Override
	public void setStreet(final String street) {
        if (!HelperObject.isEquals(street, this.street)) {
            this.street = street;
            setChanged();
            notifyObservers(MEMBER_STREET);
        }
	}

	@Override
	public void setZip(final String zip) {
        if (!HelperObject.isEquals(zip, this.zip)) {
            this.zip = zip;
            setChanged();
            notifyObservers(MEMBER_ZIP);
        }
	}
	
	
	/*
	 * Inner classes
	 */
	public static class XmlAdapter extends javax.xml.bind.annotation.adapters.XmlAdapter<PublisherImpl, Publisher> {

		@Override
		public PublisherImpl marshal(final Publisher model) {
			return (PublisherImpl) model;
		}

		@Override
		public Publisher unmarshal(final PublisherImpl model) {
			return model;
		}
	}
}