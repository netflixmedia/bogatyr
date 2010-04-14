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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */

package ch.customcode.bogatyr.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperCollection;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100216
 */
public class HelperCollectionTest {
	@Test
	public void testIsValid() {
		final Collection<String> list = new ArrayList<String>();

		assertFalse(HelperCollection.isValid(list));
		list.add("Hi"); //$NON-NLS-1$
		assertTrue(HelperCollection.isValid(list));
	}
	
	@Test
	public void testRemoveDuplicates() {
		Collection<String> list = new ArrayList<String>();

		assertEquals(0, HelperCollection.removeDuplicates(list).size());

		list = HelperCollection.getList("A", "A", "A"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
		
		assertEquals(1, HelperCollection.removeDuplicates(list).size());

//		try {
//			HelperCollection.removeDuplicates(null);
//			fail("collection is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
	
	@Test
	public void testDump() {
		final Collection<String> list = HelperCollection.getList("A", "A", "A"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		assertNotNull(HelperCollection.dump(list));
		
//		try {
//			HelperCollection.dump(null);
//			fail("iterable is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
	
	@Test
	public void testGetList() {
//		Collection<String> list = HelperCollection.getList(null);
//
//		assertEquals(0, list.size());

		final Collection<String> list = HelperCollection.getList("A", "A", "A"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		assertEquals(3, list.size());
	}
	
	@Test
	public void testGetSet() {
//		Collection<String> list = HelperCollection.getSet(null);
//		assertEquals(0, list.size());
		
		final Collection<String> list = HelperCollection.getSet("A", "A", "A"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		assertEquals(1, list.size());
	}
	
	
	@Test
	public void testToArray() {
		final String[] array = HelperCollection.toArray(HelperCollection.getList("A", "A", "A")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		assertTrue(Arrays.equals(new String[]{"A", "A", "A"}, array)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		try {
			HelperCollection.toArray(null);
			fail("collection is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
