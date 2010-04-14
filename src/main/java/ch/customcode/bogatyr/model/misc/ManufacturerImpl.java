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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.model.misc;


import java.net.URL;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.model.ModelAbstract;


/**
 * The implementation of the manufacturer model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100414)
 * @since 0.9.0
 */
@XmlRootElement(name = "manufacturer")
@XmlType(propOrder={"name", "street", "zip", "city", "country", "email", "website"})
public class ManufacturerImpl extends ModelAbstract implements Manufacturer {
	private static final long serialVersionUID = -6819817877075750182L;
	
	private static final Logger log = LoggerFactory.getLogger(ManufacturerImpl.class);
	
	private String name;
	private String street;
	private String zip;
	private String city;
	private String country;
	private String email;
	private URL website;
    
	
    public ManufacturerImpl() {
		super();
		log.trace(HelperLog.constructor());
	}


	
	public ManufacturerImpl(final String name, final String street, final String zip,
			final String city, final String country, final String email, final URL website, final Map<String, String> mapTag) {
		super(mapTag);
		log.trace(HelperLog.constructor(name, street, zip, city, country, email, website, mapTag));
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
		final ManufacturerImpl other = (ManufacturerImpl) obj;
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
	public String getCountry() {
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
	public String getEmail() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(email));
		return email;
	}

	@Override
    @XmlElement
	public URL getWebsite() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(website));
		return website;
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
	public void setCountry(final String country) {
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
	public void setWebsite(final URL url) {
		log.debug(HelperLog.methodStart(url));
		
        if (!HelperObject.isEquals(url, website)) {
            website = url;
            setChanged();
            notifyObservers(MEMBER_WEBSITE);
        }
        
        log.debug(HelperLog.methodExit());
	}
	
	
	/*
	 * Inner classes
	 */
	public static class XmlAdapter extends javax.xml.bind.annotation.adapters.XmlAdapter<ManufacturerImpl, Manufacturer> {

		@Override
		public ManufacturerImpl marshal(final Manufacturer model) {
			return (ManufacturerImpl) model;
		}

		@Override
		public Manufacturer unmarshal(final ManufacturerImpl model) {
			return model;
		}
	}
}