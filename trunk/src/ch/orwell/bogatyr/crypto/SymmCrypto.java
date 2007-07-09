/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * This is a class for symmetric cryptology
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public final class SymmCrypto {
	private static final int KEYSIZE = 128;
	private static final String ALGORITHM = "AES"; //$NON-NLS-1$

	private SecretKey key;
	
	/**
	 * Return the generated {@link SecretKey} for encryption and decryption use.
	 * 
	 * @return Returns the generated key.
	 */
	public SecretKey getKey() {
		return this.key;
	}
	
	/**
	 * Encrypt the data with a given {@link Key}.
	 * 
	 * @param input The data to encrypt as a Byte-Array
	 * @param encryptionKey The key for the encryption
	 * @return Return the encrypted Byte-Array 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @see java.security.Key
	 */
	public byte[] encrypt(byte[] input, Key encryptionKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey);
		
		return cipher.doFinal(input);
	}

	/**
	 * Decrypt the data with a given {@link Key}.
	 * 
	 * @param input The encrypted data as a Byte-Array
	 * @param decryptionKey The key for the decryption
	 * @return Return the decrypted Byte-Array
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @see java.security.Key
	 */
	public byte[] decrypt(byte[] input, Key decryptionKey) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, decryptionKey);
		
		return cipher.doFinal(input);
	}
	
	/**
	 * Generates a key. Sets the intern attribute key to the generated key.
	 * With this generated key, you can encrypt and decrypt Byte-Arrays.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @see #encrypt(byte[], Key)
	 * @see #key
	 * @see #decrypt(byte[], Key)
	 */
	public void generateKey() throws NoSuchAlgorithmException {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key
		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		kg.init(KEYSIZE);
		
		this.key  = kg.generateKey();
	}
}
