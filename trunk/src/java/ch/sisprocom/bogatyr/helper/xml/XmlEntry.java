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
package ch.sisprocom.bogatyr.helper.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * XML entry representation for a map entry.
 * 
 * @author Stefan Laubenberger
 * @author Roman Wuersch
 * @version 0.9.0 (20091210)
 * @since 0.9.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"key", "value"})
@XmlRootElement(name = "entry")
public class XmlEntry {
    private final String key;
    private final String value;
    
    public XmlEntry() {
    	key = null;
    	value = null;
    }

    public XmlEntry(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    @XmlElement(name = "key", required = true)
    public String getKey() {
        return key;
    }

    @XmlElement(name = "value", required = true)
    public String getValue() {
        return value;
    }
}
