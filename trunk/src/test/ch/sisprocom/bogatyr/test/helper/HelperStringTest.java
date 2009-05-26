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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperString;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090527
 */
public class HelperStringTest {
	@Test
	public void testIsValidString() {
		assertFalse(HelperString.isValid(new StringBuilder()));
		assertFalse(HelperString.isValid(HelperString.EMPTY_STRING)); 
		assertTrue(HelperString.isValid("123")); //$NON-NLS-1$
	}

	@Test
	public void testIsStringNumeric() {
		assertFalse(HelperString.isNumeric(null));
		assertFalse(HelperString.isNumeric(HelperString.EMPTY_STRING)); 
		assertTrue(HelperString.isNumeric("123.0")); //$NON-NLS-1$
		assertTrue(HelperString.isNumeric("123")); //$NON-NLS-1$
		assertTrue(HelperString.isNumeric("123.23")); //$NON-NLS-1$
		assertTrue(HelperString.isNumeric("000123.23")); //$NON-NLS-1$
		assertTrue(HelperString.isNumeric("-123.23")); //$NON-NLS-1$
		assertFalse(HelperString.isNumeric("123.23abc")); //$NON-NLS-1$
		assertFalse(HelperString.isNumeric("123..23")); //$NON-NLS-1$
	}

	@Test
    public void testFillString() {
		assertEquals(10, HelperString.fillString('1', 10).length());
		
		try {
			assertNotNull(HelperString.fillString('1', 0));
            fail("fillLength must be greater than 0"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testReverseString() {
		assertEquals("nafetS", HelperString.reverseString("Stefan")); //$NON-NLS-1$ //$NON-NLS-2$
		
		try {
			HelperString.reverseString(null);
            fail("input is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetValidNumericString() {
        assertNull(HelperString.getValidNumericString(null));

        assertNull(HelperString.getValidNumericString(HelperString.EMPTY_STRING)); 

		assertEquals("123.0", HelperString.getValidNumericString("123.0")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("123.23", HelperString.getValidNumericString("123.23abc")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("-123.23", HelperString.getValidNumericString("--123.23abc")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("123.23", HelperString.getValidNumericString("123.2-3abc")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("123.23", HelperString.getValidNumericString("123..23")); //$NON-NLS-1$ //$NON-NLS-2$

        assertNull(HelperString.getValidNumericString(".")); //$NON-NLS-1$
        assertNull(HelperString.getValidNumericString("-")); //$NON-NLS-1$
	}
}
