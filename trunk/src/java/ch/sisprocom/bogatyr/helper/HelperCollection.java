/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;


/**
 * This is a helper class for collections.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.70 (20090527)
 * @since 0.70
 */
public abstract class HelperCollection {
	
	/**
     * Checks if a {@link Collection} is valid.
     * 
     * @param arg to check
     * @return true/false
     */
	public static boolean isValid(final Collection<?> arg) { //$JUnit
        return !(null == arg || arg.isEmpty());
    }
    
	/**
	 * Removes duplicate objects from {@link Collection}.
	 * 
	 * @param list containing duplicate objects
	 * @return collection without duplicates
	 */
    public static <T> Collection<T> removeDuplicates(final Collection<T> list) { //$JUnit
//		return new ArrayList<T>(new HashSet<T>(list));
		return new HashSet<T>(list);
    }
    
    /**
     * Dump an {@link Iterable}.
     * 
     * @param list to dump
     * @return dump string
     */
    public static String dump(final Iterable<?> list) {
        final StringBuilder sb = new StringBuilder();

        for (final Object value : list) {
            sb.append(value);
            sb.append(HelperString.NEW_LINE);
        }
        return sb.toString();
    }

    /**
     * Dump a {@link Map}.
     * 
     * @param map to dump
     * @return dump string
     */
    public static String dump(final Map<?, ?> map) {
        final StringBuilder sb = new StringBuilder();

        for (final Map.Entry<?, ?> pair : map.entrySet()) {
            sb.append(pair.getKey());
            sb.append('=');
            sb.append(pair.getValue());
            sb.append(HelperString.NEW_LINE);
        }
        return sb.toString();
    }
}