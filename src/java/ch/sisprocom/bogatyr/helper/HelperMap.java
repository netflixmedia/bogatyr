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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


/**
 * This is a helper class for maps.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
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
     * Get all keys from a {@link Map}.
     * 
     * @param map to dump
     * @return keys from a {@link Map}
     * @see Map
     * @since 0.9.1
     */
    public static <K, V> Collection<K> getKeys(final Map<K, V> map) {
		if (null != map) {
	        
			Collection<K> set = new ArrayList<K>(map.size());

			for (final Map.Entry<K, V> pair : map.entrySet()) {
				set.add(pair.getKey());
			}
	        return set;
		}
		return null;
    }
   
    /**
     * Get all values from a {@link Map}.
     * 
     * @param map to dump
     * @return values from a {@link Map}
     * @see Map
     * @since 0.9.1
     */
    public static <K, V> Collection<V> getValues(final Map<K, V> map) {
		if (null != map) {
	        
			Collection<V> set = new ArrayList<V>(map.size());

			for (final Map.Entry<K, V> pair : map.entrySet()) {
				set.add(pair.getValue());
			}
	        return set;
		}
		return null;
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
		if (null != map) {
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
		return null;
    }
}