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
package ch.sisprocom.bogatyr.test.helper.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.converter.ConverterHex;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090508
 */
public class ConverterHexTest {
	@Test
	public void testEncodeAndDecode() {
		final String encoded = ConverterHex.encode(AllBogatyrTests.DATA.getBytes());
		final byte[] decoded = ConverterHex.decode(encoded);
		
		assertEquals(AllBogatyrTests.DATA, new String(decoded));
		
		try {
			ConverterHex.encode(null);
			fail("byte[] is null!");
		} catch (Exception ex) {}

		try {
			ConverterHex.encode(new byte[0]);
			fail("byte[] is empty!");
		} catch (Exception ex) {}

		try {
			ConverterHex.decode(null);
			fail("String is null!");
		} catch (Exception ex) {}

		try {
			ConverterHex.decode(new String());
			fail("String is empty!");
		} catch (Exception ex) {}
	}
}


