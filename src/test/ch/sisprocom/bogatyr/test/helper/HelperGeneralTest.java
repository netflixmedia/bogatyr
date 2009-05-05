/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.Const;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090427
 */
public class HelperGeneralTest { //TODO improve
	@Test
	public void testNewInstance() {
		try {
			assertEquals("", HelperGeneral.createObject(String.class)); //$NON-NLS-1$
			assertEquals(AllBogatyrTests.DATA, HelperGeneral.createObject(String.class, new Class[]{String.class}, new Object[]{AllBogatyrTests.DATA}));
			assertNull(null, null);
		} catch (Exception ex) {fail(ex.getMessage());}
	}

	@Test
	public void testGetLS() {
		assertNotNull(Const.NEW_LINE);
	}
	
	@Test
	public void testIsValidString() {
		assertFalse(HelperGeneral.isValidString(null));
		assertFalse(HelperGeneral.isValidString("")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isValidString("123")); //$NON-NLS-1$
	}

	@Test
	public void testIsStringNumeric() {
		assertFalse(HelperGeneral.isStringNumeric(null));
		assertFalse(HelperGeneral.isStringNumeric("")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("123")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("123.23")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("-123.23")); //$NON-NLS-1$
		assertFalse(HelperGeneral.isStringNumeric("123.23abc")); //$NON-NLS-1$
	}

//	@Test
//	public void testIsValidInt() {
//		assertFalse(HelperGeneral.isValidInt(0));
//		assertTrue(HelperGeneral.isValidInt(1));
//	}
//	
//	@Test
//	public void testIsValidDouble() {
//		assertFalse(HelperGeneral.isValidDouble(0.0D));
//		assertTrue(HelperGeneral.isValidDouble(0.001D));
//	}
//	
//	@Test
//	public void testIsValidLong() {
//		assertFalse(HelperGeneral.isValidLong(0L));
//		assertTrue(HelperGeneral.isValidLong(1L));
//	}
//	
//	@Test
//	public void testIsValidFloat() {
//		assertFalse(HelperGeneral.isValidFloat(0.0F));
//		assertTrue(HelperGeneral.isValidFloat(0.001F));
//	}
	
	@Test
	public void testIsValidObject() {
		assertFalse(HelperGeneral.isValidObject(null));
		assertTrue(HelperGeneral.isValidObject("")); //$NON-NLS-1$
	}
	
	@Test
	public void testIsValidArray() {
		assertFalse(HelperGeneral.isValidArray(null));
		assertFalse(HelperGeneral.isValidArray(new String[0]));
		assertTrue(HelperGeneral.isValidArray(new String[1]));
	}
	
	@Test
	public void testIsValidCollection() {
		final Collection<String> list = new ArrayList<String>();
		assertFalse(HelperGeneral.isValidCollection(null));
		assertFalse(HelperGeneral.isValidCollection(list));
		list.add(""); //$NON-NLS-1$
		assertTrue(HelperGeneral.isValidCollection(list));
	}

	@Test
	public void testGetBytesFromObject() {
		try {
			assertNull(HelperGeneral.getBytesFromObject(null));
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			assertNotNull(HelperGeneral.getBytesFromObject(AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}
	}

	@Test
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
	
	@Test
	public void testRemoveDuplicates() {
		final List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("A");
		list.add("A");
		
		assertEquals(1, HelperGeneral.removeDuplicates(list).size());


		final String[] array = {"A", "A", "A"};

		assertEquals(1, HelperGeneral.removeDuplicates(array).length);
	}
	
	@Test
	public void testGetHashCode() {
		try {
			HelperGeneral.getHashCode(null, null);
			fail("algo is null");
		} catch (Exception ex) {}

		try {
			HelperGeneral.getHashCode("MD5", null);
			fail("data is null");
		} catch (Exception ex) {}

		try {
			assertNotNull(HelperGeneral.getHashCode("MD5", AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testChecksumSha256() {
		try {
			HelperGeneral.getHashCode(null);
			fail("data is null");
		} catch (Exception ex) {}

		try {
			assertNotNull(HelperGeneral.getHashCode(AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}
	}

	@Test
    public void testRandomKey() {
		try {
            HelperGeneral.getRandomKey(16, null);
            fail("data is null");
        } catch (Exception ex) {}

        assertNotNull(HelperGeneral.getRandomKey(16, new char[]{'1','2','3'}));
//        System.out.println(HelperGeneral.getRandomKey(16, new char[]{'1','2','3'}));
    }

	@Test
    public void testRandomKeyDefault() {
		assertNotNull(HelperGeneral.getRandomKey(16));
//        System.out.println(HelperGeneral.getRandomKey(16));
    }

	@Test
    public void testFillString() {
		assertNotNull(HelperGeneral.fillString('1', 0));
		assertEquals(10, HelperGeneral.fillString('1', 10).length());
	}
	
	@Test
	public void testReverseString() {
		assertEquals("nafetS", HelperGeneral.reverseString("Stefan"));
	}
}
