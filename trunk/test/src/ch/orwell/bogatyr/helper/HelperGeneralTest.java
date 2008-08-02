/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import ch.orwell.bogatyr.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080725
 */
public class HelperGeneralTest extends TestCase {
	
	public void testCreateObject() {
		try {
			assertEquals("", HelperGeneral.createObject("java.lang.String", null)); //$NON-NLS-1$ //$NON-NLS-2$
			assertEquals(AllBogatyrTests.DATA, HelperGeneral.createObject("java.lang.String", new Object[]{AllBogatyrTests.DATA})); //$NON-NLS-1$
			assertNull(null, null);
		} catch (Exception ex) {fail(ex.getMessage());}
	}

	public void testIsValidString() {
		assertFalse(HelperGeneral.isValidString(null));
		assertFalse(HelperGeneral.isValidString("")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isValidString("123")); //$NON-NLS-1$
	}

	public void testIsStringNumeric() {
		assertFalse(HelperGeneral.isStringNumeric(null));
		assertFalse(HelperGeneral.isStringNumeric("")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("123")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("123.23")); //$NON-NLS-1$
		assertFalse(HelperGeneral.isStringNumeric("123.23abc")); //$NON-NLS-1$
	}

	public void testIsValidInt() {
		assertFalse(HelperGeneral.isValidInt(0));
		assertTrue(HelperGeneral.isValidInt(1));
	}
	
	public void testIsValidDouble() {
		assertFalse(HelperGeneral.isValidDouble(0.0D));
		assertTrue(HelperGeneral.isValidDouble(0.001D));
	}
	
	public void testIsValidLong() {
		assertFalse(HelperGeneral.isValidLong(0L));
		assertTrue(HelperGeneral.isValidLong(1L));
	}
	
	public void testIsValidFloat() {
		assertFalse(HelperGeneral.isValidFloat(0.0F));
		assertTrue(HelperGeneral.isValidFloat(0.001F));
	}
	
	public void testIsValidObject() {
		assertFalse(HelperGeneral.isValidObject(null));
		assertTrue(HelperGeneral.isValidObject("")); //$NON-NLS-1$
	}
	
	public void testIsValidArray() {
		assertFalse(HelperGeneral.isValidArray(null));
		assertFalse(HelperGeneral.isValidArray(new String[0]));
		assertTrue(HelperGeneral.isValidArray(new String[1]));
	}
	
	public void testIsValidList() {
		final List<String> list = new ArrayList<String>();
		assertFalse(HelperGeneral.isValidList(null));
		assertFalse(HelperGeneral.isValidList(list));
		list.add(""); //$NON-NLS-1$
		assertTrue(HelperGeneral.isValidList(list));
	}

	public void testGetBytesFromObject() {
		try {
			assertNull(HelperGeneral.getBytesFromObject(null));
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			assertNotNull(HelperGeneral.getBytesFromObject(AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}
	}

	public void testGetObjectFromBytes() {
		try {
			HelperGeneral.getObjectFromBytes(null);
			fail("byte[] is null");
		} catch (Exception ex) {}

		try {
			HelperGeneral.getObjectFromBytes(new byte[0]);
			fail("byte[] is empty");
		} catch (Exception ex) {}
		
		try {
			assertEquals(AllBogatyrTests.DATA, HelperGeneral.getObjectFromBytes(HelperGeneral.getBytesFromObject(AllBogatyrTests.DATA)));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	public void testRemoveDuplicates() {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("A");
		list.add("A");
		
		assertEquals(1, HelperGeneral.removeDuplicates(list).size());


		String[] array = new String[]{"A", "A", "A"};

		assertEquals(1, HelperGeneral.removeDuplicates(array).length);
	}
	
	public void testGetChecksum() {
		try {
			HelperGeneral.getChecksum(null, null);
			fail("algo is null");
		} catch (Exception ex) {}

		try {
			HelperGeneral.getChecksum("MD5", null);
			fail("data is null");
		} catch (Exception ex) {}

		try {
			assertNotNull(HelperGeneral.getChecksum("MD5", AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	public void testChecksumSha256() {
		try {
			HelperGeneral.getChecksum(null);
			fail("data is null");
		} catch (Exception ex) {}

		try {
			assertNotNull(HelperGeneral.getChecksum(AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}
	}

    public void testRandomKey() {
		try {
            HelperGeneral.getRandomKey(16, null);
            fail("data is null");
        } catch (Exception ex) {}

        assertNotNull(HelperGeneral.getRandomKey(16, new char[]{'1','2','3'}));
//        System.out.println(HelperGeneral.getRandomKey(16, new char[]{'1','2','3'}));
    }

    public void testRandomKeyDefault() {
		assertNotNull(HelperGeneral.getRandomKey(16));
//        System.out.println(HelperGeneral.getRandomKey(16));
    }

    public void testFillString() {
		assertNotNull(HelperGeneral.fillString('1', 0));
		assertEquals(10, HelperGeneral.fillString('1', 10).length());
	}
	
	public void testReverseString() {
		assertEquals("nafetS", HelperGeneral.reverseString("Stefan"));
	}
	//TODO complete tests
}


