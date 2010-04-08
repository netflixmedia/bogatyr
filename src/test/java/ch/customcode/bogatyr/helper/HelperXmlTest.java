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
package ch.customcode.bogatyr.helper;

import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperXml;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100216
 */
public class HelperXmlTest {
	private static final String INVALID_XML = new String(new byte[]{(byte)0x26});
	
	@Test
	public void testGetValidXmlString() {
//		assertEquals(HelperString.EMPTY_STRING, HelperXml.getValidXmlString(null)); 
		assertNotSame(INVALID_XML, HelperXml.getValidXmlString(INVALID_XML));
	}
}

