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

package net.laubenberger.bogatyr.misc.xml.adapter;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import net.laubenberger.bogatyr.misc.xml.XmlEntry;
import net.laubenberger.bogatyr.misc.xml.XmlMap;
import net.laubenberger.bogatyr.model.misc.Platform;

/**
 * Map adapter for the key {@link Platform} and value {@link String}.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public class MapAdapterPlatform extends XmlAdapter<XmlMap, Map<Platform, String>> {

	/*
	 * Overridden methods
	 */

	@Override
	public XmlMap marshal(final Map<Platform, String> map) throws Exception {
		if (map != null) {
			final XmlMap xmlMap = new XmlMap();
		
			for (final Map.Entry<Platform, String> entry : map.entrySet()) {
				xmlMap.getEntries().add(new XmlEntry(entry.getKey().name(), entry.getValue()));
			}
			return xmlMap;
		}
		return null;
	}

	@Override
	public Map<Platform, String> unmarshal(final XmlMap xmlMap) throws Exception {
		if (xmlMap != null) {
			final Map<Platform, String> map = new HashMap<Platform, String>(xmlMap.getEntries().size());
			for (final XmlEntry entry : xmlMap.getEntries()) {
				map.put(Platform.valueOf(entry.getKey()), entry.getValue());
			}
			return map;
		}
		return null;
	}
}
