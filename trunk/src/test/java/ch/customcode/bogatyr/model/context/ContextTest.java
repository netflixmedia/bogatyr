/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.model.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100413
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
}
