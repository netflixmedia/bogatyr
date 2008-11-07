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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.security.KeyPair;

import org.junit.Before;
import org.junit.Test;

import ch.sisprocom.bogatyr.helper.crypto.CryptoAsymm;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20081027
 */
public class CryptoAsymmTest {
	private static final int KEYSIZE = 1024;
	
	private KeyPair keyPair = null;
	  
	
	@Before
	public void setUp() throws Exception {
        keyPair = CryptoAsymm.generateKeys(KEYSIZE);
	}

	@Test
	public void testGenerateKeys() {
		try {
			CryptoAsymm.generateKeys(0);
			fail("keysize is 0");
		} catch (Exception ex) {}

		try {
			CryptoAsymm.generateKeys(-1024);
			fail("keysize is -1024");
		} catch (Exception ex) {}
		
		try {
			CryptoAsymm.generateKeys(3);
			fail("keysize is 3");
		} catch (Exception ex) {}

		try {
			assertNotNull(CryptoAsymm.generateKeys(KEYSIZE));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testEncrypt() {
		try {
			CryptoAsymm.encrypt(null, null, 0);
			fail("input is null");
		} catch (Exception ex) {}

		try {
			CryptoAsymm.encrypt(new byte[0], null, 0);
			fail("input is empty");
		} catch (Exception ex) {}
		
		try {
			CryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), null, 0);
			fail("key is null");
		} catch (Exception ex) {}
		
		try {
			CryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), 0);
			fail("keysize is 0");
		} catch (Exception ex) {}		

		try {
			assertNotNull(CryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), KEYSIZE));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testDecrypt() {
		try {
			CryptoAsymm.decrypt(null, null, 0);
			fail("input is null");
		} catch (Exception ex) {}

		try {
			CryptoAsymm.decrypt(new byte[0], null, 0);
			fail("input is empty");
		} catch (Exception ex) {}
		
		try {
			CryptoAsymm.decrypt(AllBogatyrTests.DATA.getBytes(), null, 0);
			fail("key is null");
		} catch (Exception ex) {}
		
		try {
			CryptoAsymm.decrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPrivate(), 0);
			fail("keysize is 0");
		} catch (Exception ex) {}		

		try {
			assertEquals(AllBogatyrTests.DATA, new String(CryptoAsymm.decrypt(CryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), KEYSIZE), keyPair.getPrivate(), KEYSIZE)));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}


