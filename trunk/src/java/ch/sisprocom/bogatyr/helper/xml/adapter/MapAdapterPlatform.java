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
package ch.sisprocom.bogatyr.helper.xml.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import ch.sisprocom.bogatyr.helper.xml.XmlEntry;
import ch.sisprocom.bogatyr.helper.xml.XmlMap;
import ch.sisprocom.bogatyr.model.misc.Platform;

/**
 * Map adapter for the key {@link Platform} and value {@link String}.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091205)
 * @since 0.9.0
 */
public class MapAdapterPlatform extends XmlAdapter<XmlMap, Map<Platform, String>> {

	@Override
	public XmlMap marshal(Map<Platform, String> map) throws Exception {
		XmlMap myMap = new XmlMap();
        List<XmlEntry> list = myMap.getEntrys();
        for (Map.Entry<Platform, String> entry : map.entrySet() ) {
            list.add(new XmlEntry(entry.getKey().toString(), entry.getValue()));
        }
        return myMap;
	}

	@Override
	public Map<Platform, String> unmarshal(XmlMap myMap) throws Exception {
        Map<Platform, String> map = new HashMap<Platform, String>();
        for (XmlEntry entry : myMap.getEntrys() ) {
            map.put(Platform.valueOf(entry.getKey()), entry.getValue());
        }
        return map;
	}
}
