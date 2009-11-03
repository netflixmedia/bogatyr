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
package ch.sisprocom.bogatyr.helper;

import java.util.Map;


/**
 * This is a helper class for maps.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091102)
 * @since 0.9.0
 */
public abstract class HelperMap {
	/**
     * Checks if a {@link Map} is valid.
     * 
     * @param arg to check
     * @return true/false
     * @see Map
     * @since 0.9.0
     */
	public static boolean isValid(final Map<?, ?> arg) { //$JUnit$
        return !(null == arg || arg.isEmpty());
    }
	
    /**
     * Dump a {@link Map}.
     * 
     * @param map to dump
     * @return dump string
     * @see Map
     * @since 0.7.0
     */
    public static String dump(final Map<?, ?> map) { //$JUnit$
		if (null == map) {
			throw new IllegalArgumentException("map is null!"); //$NON-NLS-1$
		}

        final StringBuilder sb = new StringBuilder();

        int ii = 0;
        for (final Map.Entry<?, ?> pair : map.entrySet()) {
            if (0 < ii) {
                sb.append(HelperString.NEW_LINE);
            }
            sb.append(pair.getKey());
            sb.append('=');
            sb.append(pair.getValue());
        	ii++;
        }
        return sb.toString();
    }
}