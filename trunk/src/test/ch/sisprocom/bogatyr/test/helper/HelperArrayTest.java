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
package ch.sisprocom.bogatyr.test.helper;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperArray;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100209
 */
public class HelperArrayTest {
	
	@Test
	public void testIsValid() {
		assertFalse(HelperArray.isValid(HelperArray.EMPTY_ARRAY_STRING));
		assertTrue(HelperArray.isValid(new String[1]));
	}
	
	@Test
	public void testConcatenateObjects() {
    	final String[] a = {"A", "B", "C"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    	final String[] b = {"D", "E"}; //$NON-NLS-1$ //$NON-NLS-2$
    	final String[] c = {"F", "G"}; //$NON-NLS-1$ //$NON-NLS-2$
    	
    	final String[] array = HelperArray.concatenate(a, b, c);

    	assertArrayEquals(new String[]{"A", "B", "C", "D", "E", "F", "G"}, array); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

//    	assertArrayEquals(b, HelperArray.concatenate(null, b));

//    	assertArrayEquals(a, HelperArray.concatenate(a, null));

	}
	
	@Test
	public void testConcatenateBytes() {
    	final byte[] a = {1};
    	final byte[] b = {2};

    	assertArrayEquals(new byte[]{1, 2}, HelperArray.concatenate(a, b));

    	assertArrayEquals(b, HelperArray.concatenate(null, b));

    	assertArrayEquals(a, HelperArray.concatenate(a, null));

	}
	
	@Test
	public void testRemoveDuplicates() {
		final String[] array = {"A", "A", "A"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		assertEquals(1, HelperArray.removeDuplicates(array).length);
		
//		try {
//			HelperArray.removeDuplicates(null);
//			fail("array is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
	
	@Test
	public void testDump() {
		final String[] array = {"A", "A", "A"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		assertNotNull(HelperArray.dump(array));
		
//		try {
//			HelperArray.dump(null);
//			fail("array is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
}
