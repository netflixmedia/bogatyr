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

import net.laubenberger.bogatyr.AllBogatyrTests;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test for {@link HelperObject}
 * 
 * @author Stefan Laubenberger
 * @version 20101227
 */
public class HelperObjectTest
{
    @Test
    public void testCreateInstance() {
        try {
            assertEquals(HelperString.EMPTY_STRING, HelperObject.createInstance(String.class));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            assertEquals(
                    AllBogatyrTests.DATA,
                    HelperObject.createInstance(String.class, HelperArray.getArray(String.class),
                            HelperArray.getArray(AllBogatyrTests.DATA)));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.createInstance(null);
            fail("class is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.createInstance(null, new Class[]{String.class}, new Object[]{AllBogatyrTests.DATA});
            fail("class is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.createInstance(String.class, HelperArray.EMPTY_ARRAY_CLASS, new Object[]{AllBogatyrTests.DATA});
            fail("paramClazzes is empty"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsEmpty ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.createInstance(String.class, new Class[]{String.class}, HelperArray.EMPTY_ARRAY_OBJECT);
            fail("params is empty"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsEmpty ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testSerialize() {
        try {
            // System.err.println(HelperObject.serialize(AllBogatyrTests.DATA).length);
            assertEquals(1183, HelperObject.serialize(AllBogatyrTests.DATA).length);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.serialize(null);
            fail("object is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
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
            assertEquals(AllBogatyrTests.DATA,
                    HelperObject.deserialize(String.class, HelperObject.serialize(AllBogatyrTests.DATA)));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.deserialize(null, AllBogatyrTests.DATA.getBytes());
            fail("clazz is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.deserialize(null);
            fail("data is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.deserialize(HelperArray.EMPTY_ARRAY_BYTE);
            fail("data is empty"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsEmpty ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testClone() {
        try {
            assertEquals(AllBogatyrTests.DATA, HelperObject.clone(AllBogatyrTests.DATA));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
			  assertNotSame(AllBogatyrTests.DATA, HelperObject.clone(AllBogatyrTests.DATA));
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.deserialize(null);
            fail("original is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testIsMethodAvailable() {
        assertTrue(HelperObject.isMethodAvailable(String.class, "indexOf")); //$NON-NLS-1$
        assertFalse(HelperObject.isMethodAvailable(String.class, "blabla")); //$NON-NLS-1$

        try {
            HelperObject.isMethodAvailable(null, "indexOf"); //$NON-NLS-1$
            fail("clazz is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }

        try {
            HelperObject.isMethodAvailable(String.class, null);
            fail("methodName is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testIsEquals() {
        assertTrue(HelperObject.isEquals("A", "A")); //$NON-NLS-1$//$NON-NLS-2$
        assertTrue(HelperObject.isEquals(null, null));
        assertFalse(HelperObject.isEquals("A", "B")); //$NON-NLS-1$ //$NON-NLS-2$
        assertFalse(HelperObject.isEquals("A", null)); //$NON-NLS-1$
        assertFalse(HelperObject.isEquals(null, "B")); //$NON-NLS-1$
    }

    @Test
    public void testQuote() {
        assertEquals("'A'", HelperObject.quote("A")); //$NON-NLS-1$//$NON-NLS-2$

        try {
            HelperObject.quote(null);
            fail("object is null"); //$NON-NLS-1$
        } catch (RuntimeExceptionIsNull ex) {
            // nothing to do
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }
}
