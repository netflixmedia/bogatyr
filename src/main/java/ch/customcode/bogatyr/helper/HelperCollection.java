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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;


/**
 * This is a helper class for collections.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100405)
 * @since 0.7.0
 */
public abstract class HelperCollection {
	private static final Logger log = LoggerFactory.getLogger(HelperCollection.class);
	
	/**
     * Returns a array of the given {@link Collection}.
     * 
     * @param collection for the array
     * @return array with the collection content
     * @see Collection
     * @since 0.9.0
     */
	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(final Collection<E> collection) { //$JUnit$
		log.debug(HelperLog.methodStart(collection));
		if (!isValid(collection)) {
			throw new RuntimeExceptionIsNullOrEmpty("collection"); //$NON-NLS-1$
		}

		final E[] result = collection.toArray((E[]) Array.newInstance(collection.iterator().next().getClass(), collection.size()));
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }
	
	/**
     * Returns a {@link List} with the given elements.
     * 
     * @param elements for the {@link List}
     * @return {@link List} with the given elements
     * @see List
     * @since 0.9.0
     */
	public static <E> List<E> getList(final E...elements) { //$JUnit$
		log.debug(HelperLog.methodStart(elements));
		if (!HelperArray.isValid(elements)) {
			throw new RuntimeExceptionIsNullOrEmpty("elements"); //$NON-NLS-1$
		}

		final List<E> result = Arrays.asList(elements);
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

	/**
     * Returns a {@link Set} with the given elements.
     * 
     * @param elements for the {@link Set}
     * @return {@link Set} with the given elements
     * @see Set
     * @since 0.9.0
     */
	public static <E> Set<E> getSet(final E...elements) { //$JUnit$
		log.debug(HelperLog.methodStart(elements));
		if (!HelperArray.isValid(elements)) {
			throw new RuntimeExceptionIsNullOrEmpty("elements"); //$NON-NLS-1$
		}

		final Set<E> result = new HashSet<E>(elements.length);

        result.addAll(Arrays.asList(elements));

		log.debug(HelperLog.methodExit(result));
		return result;
    }
	
	/**
     * Checks if a {@link Collection} is valid.
     * 
     * @param collection to check
     * @return true/false
     * @see Collection
     * @since 0.7.0
     */
	public static boolean isValid(final Collection<?> collection) { //$JUnit$
		log.debug(HelperLog.methodStart(collection));
		
		final boolean result = !(null == collection || collection.isEmpty());

		log.debug(HelperLog.methodExit(result));
		return result;
    }
    
	/**
	 * Removes duplicate objects from {@link Collection}.
	 * 
	 * @param collection containing duplicate objects
	 * @return {@link Collection} without duplicates
	 * @see Collection
	 * @since 0.7.0
	 */
    public static <E> Collection<E> removeDuplicates(final Collection<E> collection) { //$JUnit$
    	log.debug(HelperLog.methodStart(collection));
    	if (null == collection) {
			throw new RuntimeExceptionIsNull("collection"); //$NON-NLS-1$
		}

		final Set<E> result = new HashSet<E>(collection);

		log.debug(HelperLog.methodExit(result));
		return result;
    }
    
    /**
     * Dump an {@link Iterable}.
     * 
     * @param iterable to dump
     * @return dump string
     * @see Iterable
     * @since 0.7.0
     */
    public static String dump(final Iterable<?> iterable) { //$JUnit$
    	log.debug(HelperLog.methodStart(iterable));
    	if (null == iterable) {
			throw new RuntimeExceptionIsNull("iterable"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder();

        for (final Object element : iterable) {
            if (0 < sb.length()) {
                sb.append(HelperString.NEW_LINE);
            }
        	sb.append(element);
        }
        
        final String result = sb.toString();
        
        log.debug(HelperLog.methodExit(result));
        return result;
    }
}