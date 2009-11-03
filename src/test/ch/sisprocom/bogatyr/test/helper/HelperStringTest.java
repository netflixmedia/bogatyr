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
 * @version 20091103
 */
public class HelperStringTest {
	@Test
	public void testIsValid() {
		assertFalse(HelperString.isValid(new StringBuilder()));
		assertFalse(HelperString.isValid(HelperString.EMPTY_STRING)); 
		assertTrue(HelperString.isValid("123")); //$NON-NLS-1$
	}

	@Test
	public void testIsNumeric() {
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
    public void testFill() {
		assertEquals(10, HelperString.fill('1', 10).length());
		
		try {
			assertNotNull(HelperString.fill('1', 0));
            fail("fillLength must be greater than 0"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testReverse() {
		assertEquals("nafetS", HelperString.reverse("Stefan")); //$NON-NLS-1$ //$NON-NLS-2$
		
		try {
			HelperString.reverse(null);
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
	
	@Test
	public void testConcatenate() {
		assertEquals("StefanLaubenberger", HelperString.concatenate("Stefan", "Laubenberger")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		assertEquals("Stefan Laubenberger", HelperString.concatenate(" ", true, "Stefan", "Laubenberger")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		assertNull(HelperString.concatenate(null));
	}
	
	@Test
	public void testStartsWith() {
		assertTrue(HelperString.startsWith("Stefan Laubenberger", "sTe")); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse(HelperString.startsWith("Stefan Laubenberger", null)); //$NON-NLS-1$
		
		try {
			HelperString.startsWith(null, "blabla"); //$NON-NLS-1$
            fail("string is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testEndsWith() {
		assertTrue(HelperString.endsWith("Stefan Laubenberger", "GeR")); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse(HelperString.endsWith("Stefan Laubenberger", null)); //$NON-NLS-1$
		
		try {
			HelperString.endsWith(null, "blabla"); //$NON-NLS-1$
            fail("string is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testContains() {
		assertTrue(HelperString.contains("Stefan Laubenberger", "lAUb")); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse(HelperString.contains("Stefan Laubenberger", null)); //$NON-NLS-1$
		
		try {
			HelperString.contains(null, "blabla"); //$NON-NLS-1$
            fail("string is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
