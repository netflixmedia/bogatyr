/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU General Public License for more details:
 * ----------------------------------------------------
 * <http://www.gnu.org/licenses>
 * 
 * This distribution is available at:
 * ----------------------------------
 * <http://code.google.com/p/bogatyr/>
 * <http://www.sisprocom.ch/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * SiSprocom GmbH
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.misc;


import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.model.ModelAbstract;


/**
 * The implementation of the manufacturer model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091206)
 * @since 0.9.0
 */
@XmlType(propOrder={"name", "URL", "mail"})
@XmlRootElement(name = "manufacturer")
public class ManufacturerImpl extends ModelAbstract implements Manufacturer {
	private static final long serialVersionUID = -6819817877075750182L;
	
	private String name;
	private String mail;
	private URL url;
    
	
    public ManufacturerImpl() {
		super();
	}

	public ManufacturerImpl(String name, String mail, URL url) {
		super();
		this.name = name;
		this.mail = mail;
		this.url = url;
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
	public String getMail() {
		return mail;
	}

	@Override
    @XmlElement
	public URL getURL() {
		return url;
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
	public void setMail(String mail) {
        if (!HelperObject.isEquals(mail, this.mail)) {
            this.mail = mail;
            setChanged();
            notifyObservers(MEMBER_MAIL);
        }
	}

	@Override
	public void setURL(final URL url) {
        if (!HelperObject.isEquals(url, this.url)) {
            this.url = url;
            setChanged();
            notifyObservers(MEMBER_URL);
        }
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