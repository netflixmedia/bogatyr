/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.misc;


import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.model.ModelAbstract;


/**
 * The implementation of the document model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100217)
 * @since 0.9.1
 */
@XmlRootElement(name = "document")
@XmlType(propOrder={"name", "version", "build", "created", "manufacturer", "owner", "publisher", "UUID"})
public class DocumentImpl extends ModelAbstract implements Document {
	private static final long serialVersionUID = 5505184629744108815L;

	private String name;
	private BigDecimal version;
	private int build;
	private Date created;
	private Manufacturer manufacturer;
	private Owner owner;
	private Publisher publisher;
	private UUID uuid;

    public DocumentImpl() {
        super();
    }

	public DocumentImpl(final String name, final BigDecimal version, final int build,
			final Date created, final Manufacturer manufacturer, final Owner owner,
			final Publisher publisher, final UUID uuid) {
		super();
		this.name = name;
		this.version = version;
		this.build = build;
		this.created = created;
		this.manufacturer = manufacturer;
		this.owner = owner;
		this.publisher = publisher;
		this.uuid = uuid;
	}


	/*
     * Overridden methods
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + build;
		result = prime * result + ((null == created) ? 0 : created.hashCode());
		result = prime * result
				+ ((null == manufacturer) ? 0 : manufacturer.hashCode());
		result = prime * result + ((null == name) ? 0 : name.hashCode());
		result = prime * result + ((null == owner) ? 0 : owner.hashCode());
		result = prime * result
				+ ((null == publisher) ? 0 : publisher.hashCode());
		result = prime * result + ((null == uuid) ? 0 : uuid.hashCode());
		result = prime * result + ((null == version) ? 0 : version.hashCode());
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
		final DocumentImpl other = (DocumentImpl) obj;
        if (build != other.build) {
            return false;
        }
		if (null == created) {
            if (null != other.created) {
                return false;
            }
		} else if (!created.equals(other.created)) {
            return false;
        }
		if (null == manufacturer) {
            if (null != other.manufacturer) {
                return false;
            }
		} else if (!manufacturer.equals(other.manufacturer)) {
            return false;
        }
		if (null == name) {
            if (null != other.name) {
                return false;
            }
		} else if (!name.equals(other.name)) {
            return false;
        }
		if (null == owner) {
            if (null != other.owner) {
                return false;
            }
		} else if (!owner.equals(other.owner)) {
            return false;
        }
		if (null == publisher) {
            if (null != other.publisher) {
                return false;
            }
		} else if (!publisher.equals(other.publisher)) {
            return false;
        }
		if (null == uuid) {
            if (null != other.uuid) {
                return false;
            }
		} else if (!uuid.equals(other.uuid)) {
            return false;
        }
		if (null == version) {
            if (null != other.version) {
                return false;
            }
		} else if (!version.equals(other.version)) {
            return false;
        }
		return true;
	}


	/*
     * Implemented methods
     */
	@Override
    @XmlElement
	public int getBuild() {
		return build;
	}

	@Override
    @XmlElement
	public Date getCreated() {
		return created;
	}

	@Override
    @XmlElement
//    @XmlAnyElement
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	@Override
    @XmlElement
	public Owner getOwner() {
		return owner;
	}

	@Override
    @XmlElement
	public Publisher getPublisher() {
		return publisher;
	}

	@Override
    @XmlElement
	public UUID getUUID() {
		return uuid;
	}
    
	@Override
    @XmlElement
	public String getName() {
		return name;
	}

	@Override
    @XmlElement
	public BigDecimal getVersion() {
		return version;
	}

	@Override
	public void setBuild(final int build) {
        if (build != this.build) {
            this.build = build;
            setChanged();
            notifyObservers(MEMBER_BUILD);
        }
	}

	@Override
	public void setCreated(final Date created) {
        if (!HelperObject.isEquals(created, this.created)) {
            this.created = created;
            setChanged();
            notifyObservers(MEMBER_CREATED);
        }
	}

	@Override
	public void setManufacturer(final Manufacturer manufacturer) {
        if (!HelperObject.isEquals(manufacturer, this.manufacturer)) {
            this.manufacturer = manufacturer;
            setChanged();
            notifyObservers(MEMBER_MANUFACTURER);
        }
	}

	@Override
	public void setOwner(final Owner owner) {
        if (!HelperObject.isEquals(owner, this.owner)) {
            this.owner = owner;
            setChanged();
            notifyObservers(MEMBER_OWNER);
        }
	}

	@Override
	public void setPublisher(final Publisher publisher) {
        if (!HelperObject.isEquals(publisher, this.publisher)) {
            this.publisher = publisher;
            setChanged();
            notifyObservers(MEMBER_PUBLISHER);
        }
	}

	@Override
	public void setUUID(final UUID uuid) {
        if (!HelperObject.isEquals(uuid, this.uuid)) {
            this.uuid = uuid;
            setChanged();
            notifyObservers(MEMBER_UUID);
        }
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
	public void setVersion(final BigDecimal version) {
        if (!HelperObject.isEquals(version, this.version)) {
            this.version = version;
            setChanged();
            notifyObservers(MEMBER_VERSION);
        }
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