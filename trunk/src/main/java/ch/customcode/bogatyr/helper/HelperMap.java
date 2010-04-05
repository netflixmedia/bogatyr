/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * This is a helper class for maps.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.9.0
 */
public abstract class HelperMap {
	private static final Logger log = LoggerFactory.getLogger(HelperMap.class);
	
	/**
     * Checks if a {@link Map} is valid.
     * 
     * @param arg to check
     * @return true/false
     * @see Map
     * @since 0.9.0
     */
	public static boolean isValid(final Map<?, ?> arg) { //$JUnit$
		log.debug(HelperLog.methodStart(arg));
		
		final boolean result = !(null == arg || arg.isEmpty());
		
		log.debug(HelperLog.methodExit(result));
		return result;
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
    	log.debug(HelperLog.methodStart(map));
		if (null == map) {
			throw new RuntimeExceptionIsNull("map"); //$NON-NLS-1$
		}

		final Collection<K> result = new ArrayList<K>(map.size());

		for (final Map.Entry<K, V> pair : map.entrySet()) {
			result.add(pair.getKey());
		}
		log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(map));
		if (null == map) {
			throw new RuntimeExceptionIsNull("map"); //$NON-NLS-1$
		}

		final Collection<V> result = new ArrayList<V>(map.size());

		for (final Map.Entry<K, V> pair : map.entrySet()) {
			result.add(pair.getValue());
		}
		log.debug(HelperLog.methodExit(result));
        return result;
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
    	log.debug(HelperLog.methodStart(map));
		if (null == map) {
			throw new RuntimeExceptionIsNull("map"); //$NON-NLS-1$
		}
        
		final StringBuilder sb = new StringBuilder();

        for (final Map.Entry<?, ?> pair : map.entrySet()) {
            if (0 < sb.length()) {
                sb.append(HelperString.NEW_LINE);
            }
            sb.append(pair.getKey());
            sb.append('=');
            sb.append(pair.getValue());
        }

        final String result = sb.toString();
        
        log.debug(HelperLog.methodExit(result));
        return result;
    }
}