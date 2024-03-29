/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.service.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.security.KeyPair;

import net.laubenberger.bogatyr.AllBogatyrTests;
import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.model.crypto.CryptoAsymmetricAlgo;

import org.junit.Test;


/**
 * JUnit test for {@link CryptoAsymmetricImpl}
 *
 * @author Stefan Laubenberger
 * @version 20110213
 */
public class CryptoAsymmetricTest {
	private static final int KEYSIZE = 1024;
//	private static final int KEYSIZE = 512;

	@Test
	public void testGenerateKeyPair() {
		
		for (final CryptoAsymmetricAlgo algo : CryptoAsymmetricAlgo.values()) {
			try {
				final CryptoAsymmetric cryptoAsymm = new CryptoAsymmetricImpl(algo);
				try {
					assertNotNull(cryptoAsymm.generateKeyPair(KEYSIZE));
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					assertNotNull(cryptoAsymm.generateKeyPair());
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
				
				try {
					cryptoAsymm.generateKeyPair(0);
					fail("keysize is 0"); //$NON-NLS-1$
				} catch (RuntimeExceptionMustBeGreater ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.generateKeyPair(Integer.MIN_VALUE);
					fail("keysize is -1024"); //$NON-NLS-1$
				} catch (RuntimeExceptionMustBeGreater ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.generateKeyPair(3);
					fail("keysize is 3"); //$NON-NLS-1$
				} catch (IllegalArgumentException ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			} catch (Exception ex) {
				fail(ex.getMessage());
			}
		}
	}
	
	@Test
	public void testEncrypt() {
		for (final CryptoAsymmetricAlgo algo : CryptoAsymmetricAlgo.values()) {
			try {
				final CryptoAsymmetric cryptoAsymm = new CryptoAsymmetricImpl(algo);
				final KeyPair keyPair = cryptoAsymm.generateKeyPair();
				
				try {
					cryptoAsymm.encrypt(null, null, 0);
					fail("input is null"); //$NON-NLS-1$
				} catch (RuntimeExceptionIsNull ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.encrypt(HelperArray.EMPTY_ARRAY_BYTE, null, 0);
					fail("input is empty"); //$NON-NLS-1$
				} catch (RuntimeExceptionIsEmpty ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), null, 0);
					fail("key is null"); //$NON-NLS-1$
				} catch (RuntimeExceptionIsNull ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), 0);
					fail("keysize is 0"); //$NON-NLS-1$
				} catch (RuntimeExceptionMustBeGreater ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}

			} catch (Exception ex) {
				fail(ex.getMessage());
			}			
		}
	}

	@Test
	public void testDecrypt() {
		for (final CryptoAsymmetricAlgo algo : CryptoAsymmetricAlgo.values()) {
			try {
				final CryptoAsymmetric cryptoAsymm = new CryptoAsymmetricImpl(algo);
				final KeyPair keyPair = cryptoAsymm.generateKeyPair();
				
				try {
					assertEquals(AllBogatyrTests.DATA, new String(cryptoAsymm.decrypt(cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPublic(), algo.getDefaultKeysize()), keyPair.getPrivate(), algo.getDefaultKeysize())));
				} catch (Exception ex) {
					ex.printStackTrace();
					fail(ex.getMessage());
				}
		
//				try {
//					assertEquals(AllBogatyrTests.DATA, new String(cryptoAsymm.decrypt(cryptoAsymm.encrypt(AllBogatyrTests.DATA.getBytes(), keyPairDefault.getPublic()), keyPairDefault.getPrivate())));
//				} catch (Exception ex) {
//					fail(ex.getMessage());
//				}
				
				try {
					cryptoAsymm.decrypt(null, null, 0);
					fail("input is null"); //$NON-NLS-1$
				} catch (RuntimeExceptionIsNull ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.decrypt(HelperArray.EMPTY_ARRAY_BYTE, null, 0);
					fail("input is empty"); //$NON-NLS-1$
				} catch (RuntimeExceptionIsEmpty ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.decrypt(AllBogatyrTests.DATA.getBytes(), null, 0);
					fail("key is null"); //$NON-NLS-1$
				} catch (RuntimeExceptionIsNull ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
		
				try {
					cryptoAsymm.decrypt(AllBogatyrTests.DATA.getBytes(), keyPair.getPrivate(), 0);
					fail("keysize is 0"); //$NON-NLS-1$
				} catch (RuntimeExceptionMustBeGreater ex) {
					//nothing to do
				} catch (Exception ex) {
					fail(ex.getMessage());
				}
			} catch (Exception ex) {
				fail(ex.getMessage());
			}			
		}
	}
}


