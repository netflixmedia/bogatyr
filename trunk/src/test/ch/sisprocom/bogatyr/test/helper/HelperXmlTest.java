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
package ch.sisprocom.bogatyr.test.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.helper.HelperXml;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public class HelperXmlTest {
	private static final String INVALID_XML = new String(new byte[]{(byte)0x26});
	
	@Test
	public void testGetValidXmlString() {
		assertEquals(HelperString.EMPTY_STRING, HelperXml.getValidXmlString(null)); 
		assertNotSame(INVALID_XML, HelperXml.getValidXmlString(INVALID_XML));
	}
}


