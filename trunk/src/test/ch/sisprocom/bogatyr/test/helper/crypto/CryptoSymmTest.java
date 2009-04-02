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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.crypto.SecretKey;

import org.junit.Before;
import org.junit.Test;

import ch.sisprocom.bogatyr.helper.crypto.CryptoSymm;
import ch.sisprocom.bogatyr.helper.crypto.ICryptoSymm;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090402
 */
public class CryptoSymmTest {
	private static final int KEYSIZE = 128;
	
	private SecretKey secretKey;
	private ICryptoSymm cryptoSymm = new CryptoSymm();
	
	@Before
	public void setUp() throws Exception {
        secretKey = cryptoSymm.generateKey(KEYSIZE);
	}

	@Test
	public void testGenerateKey() {
		try {
			cryptoSymm.generateKey(0);
			fail("keysize is 0");
		} catch (Exception ex) {}

		try {
			cryptoSymm.generateKey(-1024);
			fail("keysize is -1024");
		} catch (Exception ex) {}

		try {
			assertNotNull(cryptoSymm.generateKey(KEYSIZE));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testEncrypt() {
		try {
			cryptoSymm.encrypt(null, null);
			fail("input is null");
		} catch (Exception ex) {}

		try {
			cryptoSymm.encrypt(new byte[0], null);
			fail("input is empty");
		} catch (Exception ex) {}
		
		try {
			cryptoSymm.encrypt(AllBogatyrTests.DATA.getBytes(), null);
			fail("key is null");
		} catch (Exception ex) {}
		
		try {
			assertNotNull(cryptoSymm.encrypt(AllBogatyrTests.DATA.getBytes(), secretKey));
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
	}
	
	@Test
	public void testDecrypt() {
		try {
			cryptoSymm.decrypt(null, null);
			fail("input is null");
		} catch (Exception ex) {}

		try {
			cryptoSymm.decrypt(new byte[0], null);
			fail("input is empty");
		} catch (Exception ex) {}
		
		try {
			cryptoSymm.decrypt(AllBogatyrTests.DATA.getBytes(), null);
			fail("key is null");
		} catch (Exception ex) {}
		
		try {
			cryptoSymm.decrypt(AllBogatyrTests.DATA.getBytes(), secretKey);
			fail("keysize is 0");
		} catch (Exception ex) {}		

		try {
			assertEquals(AllBogatyrTests.DATA, new String(cryptoSymm.decrypt(cryptoSymm.encrypt(AllBogatyrTests.DATA.getBytes(), secretKey), secretKey)));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}


