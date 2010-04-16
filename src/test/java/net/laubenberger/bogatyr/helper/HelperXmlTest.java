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

package net.laubenberger.bogatyr.helper;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import net.laubenberger.bogatyr.helper.HelperXml;


/**
 * Junit test
 *
 * @author Stefan Laubenberger
 * @version 20100416
 */
public class HelperXmlTest {
	private static final String INVALID_XML = new String(new byte[]{(byte) 0x26});

	@Test
	public void testGetValidXmlString() {
//		assertEquals(HelperString.EMPTY_STRING, HelperXml.getValidXmlString(null)); 
		assertNotSame(INVALID_XML, HelperXml.getValidXmlString(INVALID_XML));
	}
}


