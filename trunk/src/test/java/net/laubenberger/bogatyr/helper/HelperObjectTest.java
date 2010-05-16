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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import net.laubenberger.bogatyr.AllBogatyrTests;

import org.junit.Test;


/**
 * Junit test
 *
 * @author Stefan Laubenberger
 * @version 20100416
 */
public class HelperObjectTest {
	@Test
	public void testCreateInstance() {
		try {
			assertEquals(HelperString.EMPTY_STRING, HelperObject.createInstance(String.class));
			assertEquals(AllBogatyrTests.DATA, HelperObject.createInstance(String.class, HelperArray.getArray(String.class), HelperArray.getArray(AllBogatyrTests.DATA)));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperObject.createInstance(null);
			fail("class is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperObject.createInstance(null, new Class[]{String.class}, new Object[]{AllBogatyrTests.DATA});
			fail("class is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperObject.createInstance(String.class, HelperArray.EMPTY_ARRAY_CLASS, new Object[]{AllBogatyrTests.DATA});
			fail("paramClazzes is empty!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperObject.createInstance(String.class, new Class[]{String.class}, HelperArray.EMPTY_ARRAY_OBJECT);
			fail("params is empty!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testSerialize() {
		try {
			assertNotNull(HelperObject.serialize(AllBogatyrTests.DATA));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperObject.serialize(null);
			fail("Object is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testDeserialize() {
		try {
			assertEquals(AllBogatyrTests.DATA, HelperObject.deserialize(HelperObject.serialize(AllBogatyrTests.DATA)));
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperObject.deserialize(null);
			fail("byte[] is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperObject.deserialize(HelperArray.EMPTY_ARRAY_BYTE);
			fail("byte[] is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testIsEquals() {
		assertTrue(HelperObject.isEquals("A", "A"));  //$NON-NLS-1$//$NON-NLS-2$
		assertTrue(HelperObject.isEquals(null, null));
		assertFalse(HelperObject.isEquals("A", "B")); //$NON-NLS-1$ //$NON-NLS-2$
		assertFalse(HelperObject.isEquals("A", null)); //$NON-NLS-1$
		assertFalse(HelperObject.isEquals(null, "B")); //$NON-NLS-1$
	}


	@Test
	public void testIsMethodAvailable() {
		assertTrue(HelperObject.isMethodAvailable(String.class, "indexOf"));  //$NON-NLS-1$
//		assertFalse(HelperObject.isMethodAvailable(String.class, null));
		assertFalse(HelperObject.isMethodAvailable(String.class, "blabla"));  //$NON-NLS-1$


		try {
			assertTrue(HelperObject.isMethodAvailable(null, "indexOf"));  //$NON-NLS-1$
			fail("clazz is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
