/*******************************************************************************
 * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.test.model.context;

import ch.sisprocom.bogatyr.model.context.ContextImpl;
import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100209
 */
public class ContextTest {
	private static final String UNKNOWN_KEY = "lalala"; //$NON-NLS-1$
	
	@Test
	public void testAddData() {
		final String key = "Stefan"; //$NON-NLS-1$
		final String value = "Laubenberger"; //$NON-NLS-1$
		final String key2 = "Silvan"; //$NON-NLS-1$
		final String value2 = "Spross"; //$NON-NLS-1$
		
		//add key/value pairs
		ContextImpl.getInstance().addValue(key, value);
		ContextImpl.getInstance().addValue(key2, value2);
		
		//remove a key
		ContextImpl.getInstance().removeValue(key);
		
		assertNull(ContextImpl.getInstance().getValue(key));
		assertEquals(value2, ContextImpl.getInstance().getValue(key2));
		
		//add new value to existing key
		ContextImpl.getInstance().addValue(key2, value);
		
		assertEquals(value, ContextImpl.getInstance().getValue(key2));
		
		//null key
		try {
			ContextImpl.getInstance().addValue(null, value);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testRemoveValue() {
		final String key = "Roman"; //$NON-NLS-1$
		final String value = "Wuersch"; //$NON-NLS-1$
		
		ContextImpl.getInstance().addValue(key, value);
		
		//null key
		try {
			ContextImpl.getInstance().removeValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		//unknown key
		ContextImpl.getInstance().removeValue(UNKNOWN_KEY);
		
		assertEquals(value, ContextImpl.getInstance().getValue(key));
		
		//correct key
		ContextImpl.getInstance().removeValue(key);
		
		assertNull(ContextImpl.getInstance().getValue(key));
	}
	
	@Test
	public void testGetBooleanValue() {
		final String key = "boolean"; //$NON-NLS-1$
		final Boolean value = true;
		
		ContextImpl.getInstance().addValue(key, value);
		
		try {
			ContextImpl.getInstance().getBoolean(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getBoolean(UNKNOWN_KEY));
		assertTrue(ContextImpl.getInstance().getBoolean(key));
	}
	
	@Test
	public void testGetDoubleValue() {
		final String key = "double"; //$NON-NLS-1$
		final Double value = 1.0D;
		
		ContextImpl.getInstance().addValue(key, value);
		
		try {
			ContextImpl.getInstance().getDouble(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getDouble(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getDouble(key));
	}
	
	@Test
	public void testGetIntegerValue() {
		final String key = "integer"; //$NON-NLS-1$
		final Integer value = 1;
		
		ContextImpl.getInstance().addValue(key, value);
		
		try {
			ContextImpl.getInstance().getInteger(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getInteger(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getInteger(key));
	}
	
	@Test
	public void testGetFloatValue() {
		final String key = "float"; //$NON-NLS-1$
		final Float value = 1.0F;
		
		ContextImpl.getInstance().addValue(key, value);
		
		try {
			ContextImpl.getInstance().getFloat(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getFloat(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getFloat(key));
	}
	
	@Test
	public void testGetByteValue() {
		final String key = "byte"; //$NON-NLS-1$
		final Byte value = 1;
		
		ContextImpl.getInstance().addValue(key, value);
		
		try {
			ContextImpl.getInstance().getByte(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getByte(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getByte(key));
	}
	
	@Test
	public void testGetLongValue() {
		final String key = "long"; //$NON-NLS-1$
		final Long value = 1L;
		
		ContextImpl.getInstance().addValue(key, value);
		
		try {
			ContextImpl.getInstance().getLong(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getLong(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getLong(key));
	}
	
	@Test
	public void testGetShortValue() {
		final String key = "short"; //$NON-NLS-1$
		final Short value = 1;
		
		ContextImpl.getInstance().addValue(key, value);

		try {
			ContextImpl.getInstance().getShort(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getShort(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getShort(key));
	}
	
	@Test
	public void testGetBigIntegerValue() {
		final String key = "BigInteger"; //$NON-NLS-1$
		final BigInteger value = new BigInteger("1"); //$NON-NLS-1$
		
		ContextImpl.getInstance().addValue(key, value);

		try {
			ContextImpl.getInstance().getBigInteger(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getBigInteger(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getBigInteger(key));
	}
	
	@Test
	public void testGetBigDecimalValue() {
		final String key = "BigDecimal"; //$NON-NLS-1$
		final BigDecimal value = new BigDecimal("1.0"); //$NON-NLS-1$
		
		ContextImpl.getInstance().addValue(key, value);

		try {
			ContextImpl.getInstance().getBigDecimal(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getBigDecimal(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getBigDecimal(key));
	}
	
	@Test
	public void testGetDateValue() {
		final String key = "Date"; //$NON-NLS-1$
		final Date value = new Date();
		
		ContextImpl.getInstance().addValue(key, value);

		try {
			ContextImpl.getInstance().getDate(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(ContextImpl.getInstance().getDate(UNKNOWN_KEY));
		assertEquals(value, ContextImpl.getInstance().getDate(key));
	}
}
