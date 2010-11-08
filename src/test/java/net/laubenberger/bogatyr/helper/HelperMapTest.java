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

import net.laubenberger.bogatyr.helper.HelperMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * JUnit test for {@link HelperMap}
 *
 * @author Stefan Laubenberger
 * @version 20101108
 */
public class HelperMapTest {
	@Test
	public void testIsValid() {
		final Map<String, String> map = new HashMap<String, String>();

		assertFalse(HelperMap.isValid(map));
		map.put("Hi", "Stefan"); //$NON-NLS-1$ //$NON-NLS-2$
		assertTrue(HelperMap.isValid(map));
		
		assertFalse(HelperMap.isValid(null));
	}

	@Test
	public void testGetKeys() {
		final Map<String, String> map = new HashMap<String, String>();

		assertEquals(0, HelperMap.getKeys(map).size());
		
		map.put("1", "Silvan"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("2", "Roman"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("3", "Stefan"); //$NON-NLS-1$ //$NON-NLS-2$

		assertEquals(5, HelperCollection.dump(HelperMap.getKeys(map)).length());
		
		try {
			HelperMap.getKeys(null);
			fail("map is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetValues() {
		final Map<String, String> map = new HashMap<String, String>();

		assertEquals(0, HelperMap.getValues(map).size());
		
		map.put("1", "Silvan"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("2", "Roman"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("3", "Stefan"); //$NON-NLS-1$ //$NON-NLS-2$

		assertEquals(19, HelperCollection.dump(HelperMap.getValues(map)).length());
		
		try {
			HelperMap.getValues(null);
			fail("map is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testDump() {
		final Map<String, String> map = new HashMap<String, String>();

		map.put("1", "Silvan"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("2", "Roman"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("3", "Stefan"); //$NON-NLS-1$ //$NON-NLS-2$

		assertEquals(25, HelperMap.dump(map).length());

		try {
			HelperMap.dump(null);
			fail("map is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
