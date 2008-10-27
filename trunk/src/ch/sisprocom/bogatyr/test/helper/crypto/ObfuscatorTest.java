/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.test.helper.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.crypto.Obfuscator;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20081027
 */
public class ObfuscatorTest {
	@Test
	public void testEncryptAndDecrypt() {
		assertEquals(AllBogatyrTests.DATA, new String(Obfuscator.decrypt(Obfuscator.encrypt(AllBogatyrTests.DATA.getBytes()))));
		assertEquals(AllBogatyrTests.DATA, new String(Obfuscator.decrypt(Obfuscator.encrypt(AllBogatyrTests.DATA.getBytes(), (byte)0x6F), (byte)0x6F)));
		assertNotSame(AllBogatyrTests.DATA, new String(Obfuscator.decrypt(Obfuscator.encrypt(AllBogatyrTests.DATA.getBytes(), (byte)0x6F), (byte)0x5F)));
	}
}
