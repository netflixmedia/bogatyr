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
package ch.sisprocom.bogatyr.test.helper.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.crypto.Scrambler;
import ch.sisprocom.bogatyr.helper.crypto.ScramblerImpl;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20091015
 */
public class ObfuscatorTest {
	@Test
	public void testEncryptAndDecrypt() {
		final Scrambler obfuscator = new ScramblerImpl();
		assertEquals(AllBogatyrTests.DATA, new String(obfuscator.unscramble(obfuscator.scramble(AllBogatyrTests.DATA.getBytes()))));
		assertEquals(AllBogatyrTests.DATA, new String(obfuscator.unscramble(obfuscator.scramble(AllBogatyrTests.DATA.getBytes(), (byte)0x6F), (byte)0x6F)));
		assertNotSame(AllBogatyrTests.DATA, new String(obfuscator.unscramble(obfuscator.scramble(AllBogatyrTests.DATA.getBytes(), (byte)0x6F), (byte)0x5F)));
		
		try {
			obfuscator.scramble(null);
			fail("byte[] is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			obfuscator.scramble(HelperArray.EMPTY_ARRAY_BYTE);
			fail("byte[] is empty!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			obfuscator.unscramble(null);
			fail("byte[] is null!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			obfuscator.unscramble(HelperArray.EMPTY_ARRAY_BYTE);
			fail("byte[] is empty!"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
