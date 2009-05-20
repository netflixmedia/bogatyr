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

import ch.sisprocom.bogatyr.helper.HelperCollection;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public class HelperCollectionTest {
	@Test
	public void testIsValidCollection() {
		final Collection<String> list = new ArrayList<String>();

		assertFalse(HelperCollection.isValid(list));
		list.add("Hi"); //$NON-NLS-1$
		assertTrue(HelperCollection.isValid(list));
	}
	
	@Test
	public void testRemoveDuplicates() {
		final Collection<String> list = new ArrayList<String>();

		assertEquals(0, HelperCollection.removeDuplicates(list).size());
		
		list.add("A"); //$NON-NLS-1$
		list.add("A"); //$NON-NLS-1$
		list.add("A"); //$NON-NLS-1$
		
		assertEquals(1, HelperCollection.removeDuplicates(list).size());
	}
}
