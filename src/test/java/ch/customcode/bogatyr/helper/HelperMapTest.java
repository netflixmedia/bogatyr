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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */

package ch.customcode.bogatyr.helper;

import ch.customcode.bogatyr.helper.HelperMap;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100209
 */
public class HelperMapTest {
	@Test
	public void testIsValid() {
		final Map<String, String> map = new HashMap<String, String>();

		assertFalse(HelperMap.isValid(map));
		map.put("Hi", "Stefan"); //$NON-NLS-1$ //$NON-NLS-2$
		assertTrue(HelperMap.isValid(map));
	}
	
	@Test
	public void testDump() {
		final Map<String, String> map = new HashMap<String, String>();

		map.put("1", "Silvan"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("2", "Roman"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("3", "Stefan"); //$NON-NLS-1$ //$NON-NLS-2$

		assertNotNull(HelperMap.dump(map));
		
//		try {
//			HelperMap.dump(null);
//			fail("map is null!"); //$NON-NLS-1$
//		} catch (IllegalArgumentException ex) {
//			//nothing to do
//		} catch (Exception ex) {
//			fail(ex.getMessage());
//		}
	}
}
