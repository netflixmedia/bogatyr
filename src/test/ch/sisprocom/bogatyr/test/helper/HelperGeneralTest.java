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
import java.util.UUID;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public class HelperGeneralTest {
	@Test
	public void testCreateInstance() {
		try {
			assertEquals(Const.EMPTY_STRING, HelperGeneral.createInstance(String.class));
			assertEquals(AllBogatyrTests.DATA, HelperGeneral.createInstance(String.class, new Class[]{String.class}, new Object[]{AllBogatyrTests.DATA}));
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperGeneral.createInstance(null);
			fail("class is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			HelperGeneral.createInstance(null, new Class[]{String.class}, new Object[]{AllBogatyrTests.DATA});
			fail("class is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			HelperGeneral.createInstance(String.class, Const.EMPTY_ARRAY_CLASS, new Object[]{AllBogatyrTests.DATA});
			fail("paramClazzes is empty!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	
		try {
			HelperGeneral.createInstance(String.class, new Class[]{String.class}, Const.EMPTY_ARRAY_OBJECT);
			fail("params is empty!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}	
	}

	
	@Test
	public void testIsValidString() {
		assertFalse(HelperGeneral.isValid(new StringBuilder()));
		assertFalse(HelperGeneral.isValid(Const.EMPTY_STRING)); 
		assertTrue(HelperGeneral.isValid("123")); //$NON-NLS-1$
	}

	@Test
	public void testIsStringNumeric() {
		assertFalse(HelperGeneral.isStringNumeric(null));
		assertFalse(HelperGeneral.isStringNumeric(Const.EMPTY_STRING)); 
		assertTrue(HelperGeneral.isStringNumeric("123.0")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("123")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("123.23")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("000123.23")); //$NON-NLS-1$
		assertTrue(HelperGeneral.isStringNumeric("-123.23")); //$NON-NLS-1$
		assertFalse(HelperGeneral.isStringNumeric("123.23abc")); //$NON-NLS-1$
		assertFalse(HelperGeneral.isStringNumeric("123..23")); //$NON-NLS-1$
	}

	
	@Test
	public void testIsValidArray() {
		assertFalse(HelperGeneral.isValid(Const.EMPTY_ARRAY_STRING));
		assertTrue(HelperGeneral.isValid(new String[1]));
	}
	
	@Test
	public void testIsValidCollection() {
		final Collection<String> list = new ArrayList<String>();

		assertFalse(HelperGeneral.isValid(list));
		list.add("Hi"); //$NON-NLS-1$
		assertTrue(HelperGeneral.isValid(list));
	}

	@Test
	public void testSerialize() {
		try {
			assertNotNull(HelperGeneral.serialize(AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperGeneral.serialize(null);
			fail("Object is null!"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}

	@Test
	public void testDeserialize() {
		try {
			assertEquals(AllBogatyrTests.DATA, HelperGeneral.deserialize(HelperGeneral.serialize(AllBogatyrTests.DATA)));
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			HelperGeneral.deserialize(null);
			fail("byte[] is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			HelperGeneral.deserialize(Const.EMPTY_ARRAY_BYTE);
			fail("byte[] is empty"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testConcatenateObjects() {
    	final Object[] a = {"A"}; //$NON-NLS-1$
    	final Object[] b = {"B"}; //$NON-NLS-1$

    	assertArrayEquals(new String[]{"A", "B"}, HelperGeneral.concatenate(a, b)); //$NON-NLS-1$ //$NON-NLS-2$

    	assertArrayEquals(b, HelperGeneral.concatenate(null, b));

    	assertArrayEquals(a, HelperGeneral.concatenate(a, null));

	}
	
	@Test
	public void testConcatenateBytes() {
    	final byte[] a = {1};
    	final byte[] b = {2};

    	assertArrayEquals(new byte[]{1, 2}, HelperGeneral.concatenate(a, b));

    	assertArrayEquals(b, HelperGeneral.concatenate(null, b));

    	assertArrayEquals(a, HelperGeneral.concatenate(a, null));

	}
	
	@Test
	public void testRemoveDuplicates() {
		final Collection<String> list = new ArrayList<String>();

		assertEquals(0, HelperGeneral.removeDuplicates(list).size());
		
		list.add("A"); //$NON-NLS-1$
		list.add("A"); //$NON-NLS-1$
		list.add("A"); //$NON-NLS-1$
		
		assertEquals(1, HelperGeneral.removeDuplicates(list).size());


		final String[] array = {"A", "A", "A"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		assertEquals(1, HelperGeneral.removeDuplicates(array).length);
	}
	
	@Test
	public void testGetHashCode() {
		try {
			assertNotNull(HelperGeneral.getHashCode("MD5", AllBogatyrTests.DATA)); //$NON-NLS-1$
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			HelperGeneral.getHashCode(null, null);
			fail("algo is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			HelperGeneral.getHashCode("MD5", null); //$NON-NLS-1$
			fail("data is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testGetHashCodeDefault() {
		try {
			assertNotNull(HelperGeneral.getHashCode(AllBogatyrTests.DATA));
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			HelperGeneral.getHashCode(null);
			fail("data is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
	}

	@Test
    public void testGetRandomKey() {
        assertNotNull(HelperGeneral.getRandomKey(Const.VALUE_16, new char[]{'1','2','3'}));

        try {
            HelperGeneral.getRandomKey(Integer.MIN_VALUE, new char[]{'1','2','3'});
            fail("digits must be greater than 0"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}

        try {
            HelperGeneral.getRandomKey(Const.VALUE_16, null);
            fail("data is null"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}
    }

	@Test
    public void testGetRandomKeyDefault() {
		assertNotNull(HelperGeneral.getRandomKey(Const.VALUE_16));

		try {
            HelperGeneral.getRandomKey(Integer.MIN_VALUE);
            fail("digits must be greater than 0"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}
    }
	
	@Test
    public void testGetUUID() {
		final UUID a = HelperGeneral.getUUID();
		final UUID b = HelperGeneral.getUUID();
		
		assertNotNull(a);
		assertNotNull(b);
		
		assertNotSame(a, b);
    }

	@Test
    public void testFillString() {
		assertEquals(10, HelperGeneral.fillString('1', 10).length());
		
		try {
			assertNotNull(HelperGeneral.fillString('1', 0));
            fail("fillLength must be greater than 0"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testReverseString() {
		assertEquals("nafetS", HelperGeneral.reverseString("Stefan")); //$NON-NLS-1$ //$NON-NLS-2$
		
		try {
			HelperGeneral.reverseString(null);
            fail("input is null!"); //$NON-NLS-1$
        } catch (Exception ex) {/*nothing to do*/}
	}
	
	@Test
	public void testGetValidNumericString() {
        assertNull(HelperGeneral.getValidNumericString(null));

        assertNull(HelperGeneral.getValidNumericString(Const.EMPTY_STRING)); 

		assertEquals("123.0", HelperGeneral.getValidNumericString("123.0")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("123.23", HelperGeneral.getValidNumericString("123.23abc")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("-123.23", HelperGeneral.getValidNumericString("--123.23abc")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("123.23", HelperGeneral.getValidNumericString("123.2-3abc")); //$NON-NLS-1$ //$NON-NLS-2$
		assertEquals("123.23", HelperGeneral.getValidNumericString("123..23")); //$NON-NLS-1$ //$NON-NLS-2$

        assertNull(HelperGeneral.getValidNumericString(".")); //$NON-NLS-1$
        assertNull(HelperGeneral.getValidNumericString("-")); //$NON-NLS-1$
	}
}
