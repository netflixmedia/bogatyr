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
package ch.sisprocom.bogatyr.model.updater.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import ch.sisprocom.bogatyr.helper.xml.XmlEntry;
import ch.sisprocom.bogatyr.helper.xml.XmlMap;
import ch.sisprocom.bogatyr.model.crypto.HashCode;

/**
 * Map adapter for the key {@link HashCode} and value {@link String}.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091205)
 * @since 0.9.0
 */
public class MapAdapterHashCode extends XmlAdapter<XmlMap, Map<HashCode, String>> {

	@Override
	public XmlMap marshal(Map<HashCode, String> map) throws Exception {
		XmlMap myMap = new XmlMap();
        List<XmlEntry> list = myMap.getEntrys();
        for (Map.Entry<HashCode, String> entry : map.entrySet() ) {
            list.add(new XmlEntry(entry.getKey().toString(), entry.getValue()));
        }
        return myMap;
	}

	@Override
	public Map<HashCode, String> unmarshal(XmlMap myMap) throws Exception {
        Map<HashCode, String> map = new HashMap<HashCode, String>();
        for (XmlEntry entry : myMap.getEntrys() ) {
            map.put(HashCode.valueOf(entry.getKey()), entry.getValue());
        }
        return map;
	}
}
