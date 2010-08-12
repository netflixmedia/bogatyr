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

import java.awt.Dimension;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import net.laubenberger.bogatyr.misc.xml.XmlEntry;
import net.laubenberger.bogatyr.misc.xml.XmlMap;

/**
 * Map adapter for {@link Dimension}.
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100813)
 * @since 0.9.3
 */
public class AdapterDimension extends XmlAdapter<XmlMap, Dimension> {

	/*
	 * Overridden methods
	 */

	@Override
	public XmlMap marshal(final Dimension dim) throws Exception {
		if (null != dim) {
			final XmlMap xmlMap = new XmlMap();

			xmlMap.getEntries().add(new XmlEntry("x", String.valueOf(dim.width))); //$NON-NLS-1$
			xmlMap.getEntries().add(new XmlEntry("y", String.valueOf(dim.height))); //$NON-NLS-1$

			return xmlMap;
		}
		return null;
	}

	@Override
	public Dimension unmarshal(final XmlMap xmlMap) throws Exception {
		if (null != xmlMap && xmlMap.getEntries().size() == 2) {
			
			return new Dimension(Integer.valueOf(xmlMap.getEntries().get(0).getValue()), Integer.valueOf(xmlMap.getEntries().get(1).getValue()));
		}
		return null;
	}
}
