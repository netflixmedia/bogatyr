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

import ch.sisprocom.bogatyr.helper.crypto.CryptoAsymm;
import ch.sisprocom.bogatyr.helper.crypto.ICryptoAsymm;
import ch.sisprocom.bogatyr.test.AllBogatyrTests;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090508
 */
public class CryptoAsymmTest {
	private static final int KEYSIZE = 512;
	
	private KeyPair keyPair;
	private KeyPair keyPairDefault;
	private ICryptoAsymm cryptoAsymm;
	
	
	@Before
	public void setUp() throws Exception {
		cryptoAsymm = new CryptoAsymm();
        keyPair = cryptoAsymm.generateKeyPair(KEYSIZE);
        keyPairDefault = cryptoAsymm.generateKeyPair();
	}

	@Test
	public void testGenerateKeyPair() {
		try {
			cryptoAsymm.generateKeyPair(0);
			fail("keysize is 0"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			cryptoAsymm.generateKeyPair(Integer.MIN_VALUE);
			fail("keysize is -1024"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			cryptoAsymm.generateKeyPair(3);
			fail("keysize is 3"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			assertNotNull(cryptoAsymm.generateKeyPair(KEYSIZE));
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			assertNotNull(cryptoAsymm.generateKeyPair());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testEncrypt() {
		try {
			cryptoAsymm.encrypt(null, null, 0);
			fail("input is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			cryptoAsymm.encrypt(new byte[0], null, 0);
			fail("input is empty"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), null, 0);
			fail("key is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), 0);
			fail("keysize is 0"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}		

		try {
			assertNotNull(cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), KEYSIZE));
		} catch (Exception ex) {fail(ex.getMessage());}

		try {
			assertNotNull(cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPairDefault.getPublic()));
		} catch (Exception ex) {fail(ex.getMessage());}
	}
	
	@Test
	public void testDecrypt() {
		try {
			cryptoAsymm.decrypt(null, null, 0);
			fail("input is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}

		try {
			cryptoAsymm.decrypt(new byte[0], null, 0);
			fail("input is empty"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			cryptoAsymm.decrypt(AllBogatyrTests.DATA.getBytes(), null, 0);
			fail("key is null"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}
		
		try {
			cryptoAsymm.decrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPrivate(), 0);
			fail("keysize is 0"); //$NON-NLS-1$
		} catch (Exception ex) {/*nothing to do*/}		

		try {
			assertEquals(AllBogatyrTests.DATA, new String(cryptoAsymm.decrypt(cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), KEYSIZE), keyPair.getPrivate(), KEYSIZE)));
		} catch (Exception ex) {fail(ex.getMessage());}
		
		try {
			assertEquals(AllBogatyrTests.DATA, new String(cryptoAsymm.decrypt(cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPairDefault.getPublic()), keyPairDefault.getPrivate())));
		} catch (Exception ex) {fail(ex.getMessage());}

	}
}


