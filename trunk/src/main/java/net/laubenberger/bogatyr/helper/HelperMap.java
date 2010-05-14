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

package net.laubenberger.bogatyr.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is a helper class for maps.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(arg));

		final boolean result = !(null == arg || arg.isEmpty());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Get a {@link List} of all keys from a {@link Map}.
	 *
	 * @param map to dump
	 * @return {@link List} containing all keys from a {@link Map}
	 * @see Map
	 * @since 0.9.1
	 */
	public static <K, V> List<K> getKeys(final Map<K, V> map) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(map));
		if (null == map) {
			throw new RuntimeExceptionIsNull("map"); //$NON-NLS-1$
		}

		final List<K> result = new ArrayList<K>(map.size());

		for (final Map.Entry<K, V> pair : map.entrySet()) {
			result.add(pair.getKey());
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Get  a {@link List} of all values from a {@link Map}.
	 *
	 * @param map to dump
	 * @return {@link List} containing all values from a {@link Map}
	 * @see Map
	 * @since 0.9.1
	 */
	public static <K, V> List<V> getValues(final Map<K, V> map) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(map));
		if (null == map) {
			throw new RuntimeExceptionIsNull("map"); //$NON-NLS-1$
		}

		final List<V> result = new ArrayList<V>(map.size());

		for (final Map.Entry<K, V> pair : map.entrySet()) {
			result.add(pair.getValue());
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
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
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(map));
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

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}