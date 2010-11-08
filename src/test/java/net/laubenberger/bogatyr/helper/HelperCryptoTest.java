/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.UUID;

import net.laubenberger.bogatyr.model.crypto.CryptoAsymmetricAlgo;
import net.laubenberger.bogatyr.model.crypto.CryptoSymmetricAlgo;
import net.laubenberger.bogatyr.model.crypto.HashCodeAlgo;
import net.laubenberger.bogatyr.model.crypto.HmacAlgo;
import net.laubenberger.bogatyr.model.crypto.SignatureAlgo;

import org.junit.Test;


/**
 * JUnit test for {@link HelperCrypto}
 *
 * @author Stefan Laubenberger
 * @version 20101108
 */
public class HelperCryptoTest {

	@Test
	public void testGetRandomKey() {
		assertEquals(16, HelperCrypto.getRandomKey(16, '1', '2', '3', '4', '5', '6').length());
		assertNotSame(HelperCrypto.getRandomKey(16, '1', '2', '3', '4', '5', '6'), HelperCrypto.getRandomKey(16, '1', '2', '3', '4', '5', '6'));

		try {
			HelperCrypto.getRandomKey(0, '1', '2', '3');
			fail("digits must be greater than 0"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			HelperCrypto.getRandomKey(16, null);
			fail("data is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			HelperCrypto.getRandomKey(16, HelperArray.EMPTY_ARRAY_CHAR);
			fail("data is empty"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetRandomKeyDefault() {
		assertEquals(16, HelperCrypto.getRandomKey(16).length());
		assertNotSame(HelperCrypto.getRandomKey(16), HelperCrypto.getRandomKey(16));

		try {
			HelperCrypto.getRandomKey(0);
			fail("digits must be greater than 0"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetUUID() {
		final UUID a = HelperCrypto.getUUID();
		final UUID b = HelperCrypto.getUUID();

		assertNotNull(a);
		assertNotNull(b);

		assertNotSame(a, b);
	}
	
	@Test
	public void testGetProviders() {
//		System.err.println(HelperCrypto.getProviders().size());
		assertTrue(HelperCrypto.getProviders().size() >= 11);
		assertTrue(HelperCrypto.getProviders().contains(HelperCrypto.DEFAULT_PROVIDER));
	}
	
	@Test
	public void testGetCiphers() {
//		System.err.println(HelperCrypto.getCiphers(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getCiphers(HelperCrypto.DEFAULT_PROVIDER).size() >= 75);
		
		for (CryptoSymmetricAlgo algo : CryptoSymmetricAlgo.values()) {
			assertTrue(HelperCrypto.getCiphers(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}
		for (CryptoAsymmetricAlgo algo : CryptoAsymmetricAlgo.values()) {
			assertTrue(HelperCrypto.getCiphers(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}		
		
		assertFalse(HelperCrypto.getCiphers(HelperCrypto.DEFAULT_PROVIDER).contains(HashCodeAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getCiphers(HelperCrypto.DEFAULT_PROVIDER).contains(HmacAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getCiphers(HelperCrypto.DEFAULT_PROVIDER).contains(SignatureAlgo.SHA256_WITH_RSA.getAlgorithm()));
		
		try {
			HelperCrypto.getCiphers(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetKeyAgreements() {
//		System.err.println(HelperCrypto.getKeyAgreements(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getKeyAgreements(HelperCrypto.DEFAULT_PROVIDER).size() >= 4);
		assertTrue(HelperCrypto.getKeyAgreements(HelperCrypto.DEFAULT_PROVIDER).contains("DH")); //$NON-NLS-1$
		
		try {
			HelperCrypto.getKeyAgreements(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetMacs() {
//		System.err.println(HelperCrypto.getMacs(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getMacs(HelperCrypto.DEFAULT_PROVIDER).size() >= 33);
		
		for (HmacAlgo algo : HmacAlgo.values()) {
			assertTrue(HelperCrypto.getMacs(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}
		
		assertFalse(HelperCrypto.getMacs(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoSymmetricAlgo.AES.getAlgorithm()));
		assertFalse(HelperCrypto.getMacs(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoAsymmetricAlgo.ELGAMAL.getAlgorithm()));
		assertFalse(HelperCrypto.getMacs(HelperCrypto.DEFAULT_PROVIDER).contains(HashCodeAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getMacs(HelperCrypto.DEFAULT_PROVIDER).contains(SignatureAlgo.SHA256_WITH_RSA.getAlgorithm()));
		
		try {
			HelperCrypto.getMacs(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetMessageDigests() {
//		System.err.println(HelperCrypto.getMessageDigests(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getMessageDigests(HelperCrypto.DEFAULT_PROVIDER).size() >=15);
		
		for (HashCodeAlgo algo : HashCodeAlgo.values()) {
			assertTrue(HelperCrypto.getMessageDigests(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}
		
		assertFalse(HelperCrypto.getMessageDigests(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoSymmetricAlgo.AES.getAlgorithm()));
		assertFalse(HelperCrypto.getMessageDigests(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoAsymmetricAlgo.ELGAMAL.getAlgorithm()));
		assertFalse(HelperCrypto.getMessageDigests(HelperCrypto.DEFAULT_PROVIDER).contains(HmacAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getMessageDigests(HelperCrypto.DEFAULT_PROVIDER).contains(SignatureAlgo.SHA256_WITH_RSA.getAlgorithm()));
		
		try {
			HelperCrypto.getMessageDigests(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetSignatures() {
//		System.err.println(HelperCrypto.getSignatures(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getSignatures(HelperCrypto.DEFAULT_PROVIDER).size() >= 45);
		
		for (SignatureAlgo algo : SignatureAlgo.values()) {
			assertTrue(HelperCrypto.getSignatures(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}
		
		assertFalse(HelperCrypto.getSignatures(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoSymmetricAlgo.AES.getAlgorithm()));
		assertFalse(HelperCrypto.getSignatures(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoAsymmetricAlgo.ELGAMAL.getAlgorithm()));
		assertFalse(HelperCrypto.getSignatures(HelperCrypto.DEFAULT_PROVIDER).contains(HashCodeAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getSignatures(HelperCrypto.DEFAULT_PROVIDER).contains(HmacAlgo.SHA256.getAlgorithm()));
		
		try {
			HelperCrypto.getSignatures(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}

	@Test
	public void testGetKeyPairGenerators() {
//		System.err.println(HelperCrypto.getKeyPairGenerators(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getKeyPairGenerators(HelperCrypto.DEFAULT_PROVIDER).size() >= 12);
		
		for (CryptoAsymmetricAlgo algo : CryptoAsymmetricAlgo.values()) {
			assertTrue(HelperCrypto.getKeyPairGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}		

		assertFalse(HelperCrypto.getKeyPairGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoSymmetricAlgo.AES.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyPairGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(HashCodeAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyPairGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(HmacAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyPairGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(SignatureAlgo.SHA256_WITH_RSA.getAlgorithm()));
		
		try {
			HelperCrypto.getKeyPairGenerators(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetKeyFactories() {
//		System.err.println(HelperCrypto.getKeyFactories(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getKeyFactories(HelperCrypto.DEFAULT_PROVIDER).size() >= 13);
		
		for (CryptoAsymmetricAlgo algo : CryptoAsymmetricAlgo.values()) {
			assertTrue(HelperCrypto.getKeyFactories(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}		
		
		assertFalse(HelperCrypto.getKeyFactories(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoSymmetricAlgo.AES.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyFactories(HelperCrypto.DEFAULT_PROVIDER).contains(HashCodeAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyFactories(HelperCrypto.DEFAULT_PROVIDER).contains(HmacAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyFactories(HelperCrypto.DEFAULT_PROVIDER).contains(SignatureAlgo.SHA256_WITH_RSA.getAlgorithm()));
		
		try {
			HelperCrypto.getKeyFactories(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGetKeyGenerators() {
//		System.err.println(HelperCrypto.getKeyGenerators(HelperCrypto.DEFAULT_PROVIDER).size());
		assertTrue(HelperCrypto.getKeyGenerators(HelperCrypto.DEFAULT_PROVIDER).size() >= 41);
		
		for (CryptoSymmetricAlgo algo : CryptoSymmetricAlgo.values()) {
			assertTrue(HelperCrypto.getKeyGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}		
		for (HmacAlgo algo : HmacAlgo.values()) {
			assertTrue(HelperCrypto.getKeyGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(algo.getAlgorithm()));
		}	
		
		assertFalse(HelperCrypto.getKeyGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(CryptoAsymmetricAlgo.ELGAMAL.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(HashCodeAlgo.SHA256.getAlgorithm()));
		assertFalse(HelperCrypto.getKeyGenerators(HelperCrypto.DEFAULT_PROVIDER).contains(SignatureAlgo.SHA256_WITH_RSA.getAlgorithm()));
		
		try {
			HelperCrypto.getKeyGenerators(null);
			fail("provider is null"); //$NON-NLS-1$
		} catch (IllegalArgumentException ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
