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

package net.laubenberger.bogatyr.model.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.Date;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.junit.Test;


/**
 * JUnit test for {@link ContextImpl}
 *
 * @author Stefan Laubenberger
 * @version 20101202
 */
public class ContextTest {
	private static final String UNKNOWN_KEY = "blabla"; //$NON-NLS-1$

	private static final String KEY = "1"; //$NON-NLS-1$
	private static final String VALUE = "Silvan"; //$NON-NLS-1$
	private static final String KEY2 = "2"; //$NON-NLS-1$
	private static final String VALUE2 = "Stefan"; //$NON-NLS-1$
	private static final String KEY3 = "3"; //$NON-NLS-1$
	private static final Date VALUE3 = new Date();
	
	@Test
	public void testAddValue() {
		ContextImpl.getInstance().setData(null);
		assertNull(ContextImpl.getInstance().getData());
		
		ContextImpl.getInstance().addValue(KEY, VALUE);
		ContextImpl.getInstance().addValue(KEY2, VALUE2);
		ContextImpl.getInstance().addValue(KEY3, VALUE3);

		//remove a key
		ContextImpl.getInstance().removeValue(KEY);

		assertNull(ContextImpl.getInstance().getValue(KEY));
		assertEquals(VALUE2, ContextImpl.getInstance().getValue(KEY2));
		assertEquals(VALUE3, ContextImpl.getInstance().getValue(KEY3, Date.class));

		//add new value to existing key
		ContextImpl.getInstance().addValue(KEY2, VALUE);

		assertEquals(VALUE, ContextImpl.getInstance().getValue(KEY2));

		try {
			ContextImpl.getInstance().addValue(null, VALUE);
			fail("key is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			ContextImpl.getInstance().addValue(KEY, null);
			fail("value is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testRemoveValue() {
		ContextImpl.getInstance().setData(null);
		assertNull(ContextImpl.getInstance().getData());

		ContextImpl.getInstance().addValue(KEY, VALUE);

		//unknown key
		ContextImpl.getInstance().removeValue(UNKNOWN_KEY);

		assertEquals(VALUE, ContextImpl.getInstance().getValue(KEY));

		//correct key
		ContextImpl.getInstance().removeValue(KEY);

		assertNull(ContextImpl.getInstance().getValue(KEY));

		try {
			ContextImpl.getInstance().removeValue(null);
			fail("key is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	

	@Test
	public void testGetValue() {
		ContextImpl.getInstance().setData(null);
		assertNull(ContextImpl.getInstance().getData());

		ContextImpl.getInstance().addValue(KEY, VALUE);
		ContextImpl.getInstance().addValue(KEY2, VALUE2);
		ContextImpl.getInstance().addValue(KEY3, VALUE3);

		//remove a key
		ContextImpl.getInstance().removeValue(KEY);

		assertNull(ContextImpl.getInstance().getValue(KEY));
		assertEquals(VALUE2, ContextImpl.getInstance().getValue(KEY2));
		assertEquals(VALUE3, ContextImpl.getInstance().getValue(KEY3, Date.class));

		//add new value to existing key
		ContextImpl.getInstance().addValue(KEY2, VALUE);

		assertEquals(VALUE, ContextImpl.getInstance().getValue(KEY2));

		try {
			ContextImpl.getInstance().getValue(null);
			fail("key is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			ContextImpl.getInstance().getValue(null, String.class);
			fail("key is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			ContextImpl.getInstance().getValue(KEY, null);
			fail("clazz is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
