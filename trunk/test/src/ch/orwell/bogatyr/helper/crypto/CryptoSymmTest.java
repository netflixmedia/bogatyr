/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper.crypto;

import javax.crypto.SecretKey;

import junit.framework.TestCase;
import ch.orwell.bogatyr.AllBogatyrTests;
import ch.orwell.bogatyr.helper.crypto.CryptoSymm;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080525
 */
public class CryptoSymmTest extends TestCase {
	private static final int KEYSIZE = 128;
	
	private SecretKey secretKey = null;
	  

	public void testGenerateKey() {
		try {
			CryptoSymm.generateKey(0);
			fail("keysize is 0");
		} catch (Exception ex) {}

		try {
			CryptoSymm.generateKey(-1024);
			fail("keysize is -1024");
		} catch (Exception ex) {}

		try {
			assertNotNull(CryptoSymm.generateKey(KEYSIZE));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	public void testEncrypt() {
		try {
			CryptoSymm.encrypt(null, null);
			fail("input is null");
		} catch (Exception ex) {}

		try {
			CryptoSymm.encrypt(new byte[0], null);
			fail("input is empty");
		} catch (Exception ex) {}
		
		try {
			CryptoSymm.encrypt(AllBogatyrTests.DATA.getBytes(), null);
			fail("key is null");
		} catch (Exception ex) {}
		
		try {
			assertNotNull(CryptoSymm.encrypt(AllBogatyrTests.DATA.getBytes(), secretKey));
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	
	public void testDecrypt() {
		try {
			CryptoSymm.decrypt(null, null);
			fail("input is null");
		} catch (Exception ex) {}

		try {
			CryptoSymm.decrypt(new byte[0], null);
			fail("input is empty");
		} catch (Exception ex) {}
		
		try {
			CryptoSymm.decrypt(AllBogatyrTests.DATA.getBytes(), null);
			fail("key is null");
		} catch (Exception ex) {}
		
		try {
			CryptoSymm.decrypt(AllBogatyrTests.DATA.getBytes(), secretKey);
			fail("keysize is 0");
		} catch (Exception ex) {}		

		try {
			assertEquals(AllBogatyrTests.DATA, new String(CryptoSymm.decrypt(CryptoSymm.encrypt(AllBogatyrTests.DATA.getBytes(), secretKey), secretKey)));
//			assertEquals(DATA.getBytes(), CryptoSymm.decrypt(CryptoSymm.encrypt(DATA.getBytes(), this.secretKey), this.secretKey)); //TODO why does this test not work?
		} catch (Exception ex) {fail(ex.getMessage());}
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public void setUp() throws Exception {
        secretKey = CryptoSymm.generateKey(KEYSIZE);
	}
}


