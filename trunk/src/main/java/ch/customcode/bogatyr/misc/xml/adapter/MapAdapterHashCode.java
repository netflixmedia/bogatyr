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
package ch.customcode.bogatyr.misc.xml.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import ch.customcode.bogatyr.misc.xml.XmlEntry;
import ch.customcode.bogatyr.misc.xml.XmlMap;
import ch.customcode.bogatyr.model.crypto.HashCodeAlgo;

/**
 * Map adapter for the key {@link HashCodeAlgo} and value {@link String}.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.9.0
 */
public class MapAdapterHashCode extends XmlAdapter<XmlMap, Map<HashCodeAlgo, String>> {

	@Override
	public XmlMap marshal(final Map<HashCodeAlgo, String> map) throws Exception {
		final XmlMap xmlMap = new XmlMap();
        for (final Map.Entry<HashCodeAlgo, String> entry : map.entrySet() ) {
        	xmlMap.getEntries().add(new XmlEntry(entry.getKey().name(), entry.getValue()));
        }
        return xmlMap;
	}

	@Override
	public Map<HashCodeAlgo, String> unmarshal(final XmlMap xmlMap) throws Exception {
		final Map<HashCodeAlgo, String> map = new HashMap<HashCodeAlgo, String>(xmlMap.getEntries().size());
        for (final XmlEntry entry : xmlMap.getEntries() ) {
            map.put(HashCodeAlgo.valueOf(entry.getKey()), entry.getValue());
        }
        return map;
	}
}
