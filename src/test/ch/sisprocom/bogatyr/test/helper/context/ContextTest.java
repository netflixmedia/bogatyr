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
package ch.sisprocom.bogatyr.test.helper.context;

import ch.sisprocom.bogatyr.helper.context.Context;
import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090527
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
		Context.getInstance().addValue(key, value);
		Context.getInstance().addValue(key2, value2);
		
		//remove a key
		Context.getInstance().removeValue(key);
		
		assertNull(Context.getInstance().getValue(key));
		assertEquals(value2, Context.getInstance().getValue(key2));
		
		//add new value to existing key
		Context.getInstance().addValue(key2, value);
		
		assertEquals(value, Context.getInstance().getValue(key2));
		
		//null key
		try {
			Context.getInstance().addValue(null, value);
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
		
		Context.getInstance().addValue(key, value);
		
		//null key
		try {
			Context.getInstance().removeValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		//unknown key
		Context.getInstance().removeValue(UNKNOWN_KEY);
		
		assertEquals(value, Context.getInstance().getValue(key));
		
		//correct key
		Context.getInstance().removeValue(key);
		
		assertNull(Context.getInstance().getValue(key));
	}
	
	@Test
	public void testGetBooleanValue() {
		final String key = "boolean"; //$NON-NLS-1$
		final Boolean value = true;
		
		Context.getInstance().addValue(key, value);
		
		try {
			Context.getInstance().getBooleanValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getBooleanValue(UNKNOWN_KEY));
		assertTrue(Context.getInstance().getBooleanValue(key));
	}
	
	@Test
	public void testGetDoubleValue() {
		final String key = "double"; //$NON-NLS-1$
		final Double value = 1.0D;
		
		Context.getInstance().addValue(key, value);
		
		try {
			Context.getInstance().getDoubleValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getDoubleValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getDoubleValue(key));
	}
	
	@Test
	public void testGetIntegerValue() {
		final String key = "integer"; //$NON-NLS-1$
		final Integer value = 1;
		
		Context.getInstance().addValue(key, value);
		
		try {
			Context.getInstance().getIntegerValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getIntegerValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getIntegerValue(key));
	}
	
	@Test
	public void testGetFloatValue() {
		final String key = "float"; //$NON-NLS-1$
		final Float value = 1.0F;
		
		Context.getInstance().addValue(key, value);
		
		try {
			Context.getInstance().getFloatValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getFloatValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getFloatValue(key));
	}
	
	@Test
	public void testGetByteValue() {
		final String key = "byte"; //$NON-NLS-1$
		final Byte value = 1;
		
		Context.getInstance().addValue(key, value);
		
		try {
			Context.getInstance().getByteValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getByteValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getByteValue(key));
	}
	
	@Test
	public void testGetLongValue() {
		final String key = "long"; //$NON-NLS-1$
		final Long value = 1L;
		
		Context.getInstance().addValue(key, value);
		
		try {
			Context.getInstance().getLongValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getLongValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getLongValue(key));
	}
	
	@Test
	public void testGetShortValue() {
		final String key = "short"; //$NON-NLS-1$
		final Short value = 1;
		
		Context.getInstance().addValue(key, value);

		try {
			Context.getInstance().getShortValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getShortValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getShortValue(key));
	}
	
	@Test
	public void testGetBigIntegerValue() {
		final String key = "BigInteger"; //$NON-NLS-1$
		final BigInteger value = new BigInteger("1"); //$NON-NLS-1$
		
		Context.getInstance().addValue(key, value);

		try {
			Context.getInstance().getBigIntegerValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getBigIntegerValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getBigIntegerValue(key));
	}
	
	@Test
	public void testGetBigDecimalValue() {
		final String key = "BigDecimal"; //$NON-NLS-1$
		final BigDecimal value = new BigDecimal("1.0"); //$NON-NLS-1$
		
		Context.getInstance().addValue(key, value);

		try {
			Context.getInstance().getBigDecimalValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getBigDecimalValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getBigDecimalValue(key));
	}
	
	@Test
	public void testGetDateValue() {
		final String key = "Date"; //$NON-NLS-1$
		final Date value = new Date();
		
		Context.getInstance().addValue(key, value);

		try {
			Context.getInstance().getDateValue(null);
			fail("key is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		assertNull(Context.getInstance().getDateValue(UNKNOWN_KEY));
		assertEquals(value, Context.getInstance().getDateValue(key));
	}
}
