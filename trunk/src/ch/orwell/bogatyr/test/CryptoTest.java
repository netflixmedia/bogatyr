/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.test;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.SecretKey;

import junit.framework.TestCase;
import ch.orwell.bogatyr.crypto.AsymmCrypto;
import ch.orwell.bogatyr.crypto.SymmCrypto;
import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * Tests the AsymmCrypro and SymmCrypto
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public class CryptoTest extends TestCase {
	
	private AsymmCrypto asymmCrypto;
	private SymmCrypto symmCrypto;
	private String toEncrypt = new String();

    @Override
	protected void setUp() {
    	this.asymmCrypto = new AsymmCrypto();
    	this.symmCrypto = new SymmCrypto();
    }
    
    public void testAsymmCrypto(){
    	
    	// No key was generated yet
    	PublicKey publicKey = this.asymmCrypto.getPublicKey();
    	assertTrue(publicKey == null);
    	
    	// Generate key
    	try {
    		this.asymmCrypto.generateKeys();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// No a key have to exist
		publicKey = this.asymmCrypto.getPublicKey();
		assertTrue(publicKey != null);
		
		
		// Generate random string to crypt (Dirty way...)
		this.toEncrypt += Math.random();
		
		// Encrypt it
		byte[] toEncryptInBytes = null;
		byte[] encrypted = null;
		try {
			toEncryptInBytes = GeneralHelper.getBytes(this.toEncrypt);
			encrypted = this.asymmCrypto.encrypt(toEncryptInBytes, publicKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Simple tests
		assertTrue(toEncryptInBytes != encrypted);
		
		// Test decrypting with public key
		byte[] decrypted = null;
		String expectedObject = new String();
		try {
			decrypted = this.asymmCrypto.decrypt(encrypted);
			expectedObject = (String)GeneralHelper.getObject(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Back testing
		assertTrue(this.toEncrypt.equals(expectedObject));
    }
    
    public void testSymmCrypto(){
    	
    	// No key was generated yet
    	SecretKey secretKey = this.symmCrypto.getKey();
    	assertTrue(secretKey == null);
    	
    	// Generate key
    	try {
    		this.symmCrypto.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// No a key have to exist
		secretKey = this.symmCrypto.getKey();
		assertTrue(secretKey != null);
		
		
		// Generate random string to crypt (Dirty way...)
		this.toEncrypt += Math.random();
		
		// Encrypt it
		byte[] toEncryptInBytes = null;
		byte[] encrypted = null;
		try {
			toEncryptInBytes = GeneralHelper.getBytes(this.toEncrypt);
			encrypted = this.symmCrypto.encrypt(toEncryptInBytes, secretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Simple tests
		assertTrue(toEncryptInBytes != encrypted);
		
		// Test decrypting with public key
		byte[] decrypted = null;
		String expectedObject = new String();
		try {
			decrypted = this.symmCrypto.decrypt(encrypted, secretKey);
			expectedObject = (String)GeneralHelper.getObject(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Back testing
		assertTrue(this.toEncrypt.equals(expectedObject));
    }
}
