/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.misc.xml.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import ch.sisprocom.bogatyr.misc.xml.XmlEntry;
import ch.sisprocom.bogatyr.misc.xml.XmlMap;
import ch.sisprocom.bogatyr.model.crypto.HashCode;

/**
 * Map adapter for the key {@link HashCode} and value {@link String}.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.9.0
 */
public class MapAdapterHashCode extends XmlAdapter<XmlMap, Map<HashCode, String>> {

	@Override
	public XmlMap marshal(final Map<HashCode, String> map) throws Exception {
		final XmlMap xmlMap = new XmlMap();
        for (final Map.Entry<HashCode, String> entry : map.entrySet() ) {
        	xmlMap.getEntries().add(new XmlEntry(entry.getKey().name(), entry.getValue()));
        }
        return xmlMap;
	}

	@Override
	public Map<HashCode, String> unmarshal(final XmlMap xmlMap) throws Exception {
		final Map<HashCode, String> map = new HashMap<HashCode, String>(xmlMap.getEntries().size());
        for (final XmlEntry entry : xmlMap.getEntries() ) {
            map.put(HashCode.valueOf(entry.getKey()), entry.getValue());
        }
        return map;
	}
}
