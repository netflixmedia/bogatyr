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
package ch.sisprocom.bogatyr.test.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperCollection;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091101
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
	}
	
	@Test
	public void testDump() {
		Collection<String> list = HelperCollection.getList("A", "A", "A"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$

		assertNotNull(HelperCollection.dump(list));
	}
}
