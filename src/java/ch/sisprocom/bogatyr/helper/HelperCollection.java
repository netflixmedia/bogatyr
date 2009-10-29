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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * This is a helper class for collections.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20091028)
 * @since 0.7.0
 */
public abstract class HelperCollection {
	
	/**
     * Returns a {@link List} with the given elements.
     * 
     * @param es elements for the list
     * @return true/false
     * @since 0.9.0
     * @see List
     */
	public static <E> List<E> getList(final E...es) { //$JUnit$
        List<E> list = new ArrayList<E>();
        
        for (E e : es) {
        	list.add(e);
        }
        
		return list;
    }

	/**
     * Returns a {@link Set} with the given elements.
     * 
     * @param es elements for the set
     * @return true/false
     * @since 0.9.0
     * @see Set
     */
	public static <E> Set<E> getSet(final E...es) { //$JUnit$
        Set<E> set = new HashSet<E>();
        
        for (E e : es) {
        	set.add(e);
        }
        
		return set;
    }
	
	/**
     * Checks if a {@link Collection} is valid.
     * 
     * @param arg to check
     * @return true/false
     * @since 0.7.0
     * @see Collection
     */
	public static boolean isValid(final Collection<?> arg) { //$JUnit$
        return !(null == arg || arg.isEmpty());
    }
    
	/**
	 * Removes duplicate objects from {@link Collection}.
	 * 
	 * @param list containing duplicate objects
	 * @return collection without duplicates
	 * @since 0.7.0
	 * @see Collection
	 */
    public static <T> Collection<?> removeDuplicates(final Collection<T> list) { //$JUnit$
//		return new ArrayList<T>(new HashSet<T>(list));
		return new HashSet<T>(list);
    }
    
    /**
     * Dump an {@link Iterable}.
     * 
     * @param list to dump
     * @return dump string
     * @since 0.7.0
     * @see Iterable
     */
    public static String dump(final Iterable<?> list) {
        final StringBuilder sb = new StringBuilder();

        for (final Object value : list) {
            sb.append(value);
            sb.append(HelperString.NEW_LINE);
        }
        return sb.toString();
    }
}