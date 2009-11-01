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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;import java.util.Arrays;


/**
 * This is a helper class for collections.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20091101)
 * @since 0.7.0
 */
public abstract class HelperCollection {

	/**
     * Returns a array of the given {@link Collection}.
     * 
     * @param collection for the array
     * @return array with the collection content
     * @see Collection
     * @since 0.9.0
     */
	@SuppressWarnings("unchecked")
	public static <E> E[] toArray(final Collection<E> collection) {
		if (!isValid(collection)) {
			throw new IllegalArgumentException("collection is null or empty!"); //$NON-NLS-1$
		}
		
		return collection.toArray((E[]) Array.newInstance(collection.iterator().next().getClass(), collection.size()));
    }
	
	/**
     * Returns a {@link List} with the given elements.
     * 
     * @param elements for the {@link List}
     * @return {@link List} with the given elements
     * @see List
     * @since 0.9.0
     */
	public static <E> List<E> getList(final E...elements) {
        final List<E> list = new ArrayList<E>();

        list.addAll(Arrays.asList(elements));
        
		return list;
    }

	/**
     * Returns a {@link Set} with the given elements.
     * 
     * @param elements for the {@link Set}
     * @return {@link Set} with the given elements
     * @see Set
     * @since 0.9.0
     */
	public static <E> Set<E> getSet(final E...elements) {
        final Set<E> set = new HashSet<E>();

        set.addAll(Arrays.asList(elements));
        
		return set;
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
        return !(null == collection || collection.isEmpty());
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
		return new HashSet<E>(collection);
    }
    
    /**
     * Dump an {@link Iterable}.
     * 
     * @param iterable to dump
     * @return dump string
     * @see Iterable
     * @since 0.7.0
     */
    public static <E> String dump(final Iterable<E> iterable) { //$JUnit$
        final StringBuilder sb = new StringBuilder();

        int ii = 0;
        for (final E element : iterable) {
            if (0 < ii) {
                sb.append(HelperString.NEW_LINE);
            }
        	sb.append(element);
        	ii++;
        }
        return sb.toString();
    }
}