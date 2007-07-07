/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.crypto;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.orwell.bogatyr.util.GeneralHelper;

/**
 * This is a class for asymmetric cryptology
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public final class AsymmCrypto {
	private static final int KEYSIZE = 1024;
	private static final String ALGORITHM = "RSA"; //$NON-NLS-1$
	private static final String xform = "RSA/ECB/PKCS1PADDING"; //$NON-NLS-1$

	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	/**
	 * Return the generated public key for encryption and decryption use.
	 * 
	 * @return Returns the generated public key.
	 */
	public PublicKey getPublicKey() {
		return this.publicKey;
	}

	/**
	 * Encrypt the Data with a given PublicKey.
	 * 
	 * @param input The Data to encrypt as a Byte-Array
	 * @param key The PublicKey for the encryption
	 * @return Return the encrypted Byte-Array 
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @see PublicKey
	 */
	public byte[] encrypt(byte[] input, PublicKey key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		int space = KEYSIZE/8 - 11;
		byte[] result = new byte[0];
		byte[] temp = new byte[space];
		int tempCounter = 0;
		
		if (space < input.length) {
			
			for (int ii = 0; ii < input.length; ii++) {
				
				temp[tempCounter] = input[ii];
				
				if (tempCounter == space - 1) {
					result = GeneralHelper.appendByteArray(result, encryptInternal(temp, key));
					tempCounter = 0;
				} else {
					if (ii == input.length - 1) { // last byte
						byte[] usedBytes = new byte[tempCounter + 1];
						
						for (int xx = 0; xx <= tempCounter; xx++) {
							usedBytes[xx] = input[(ii - tempCounter) + xx];
						}
						result = GeneralHelper.appendByteArray(result, encryptInternal(usedBytes, key));
					}
					tempCounter++;
					
				}
			}
		} else {
			result = encryptInternal(input, key);
		}
		return result;
	}

	/**
	 * Decrypt the Data.
	 * 
	 * @param input The encrypted Data as a Byte-Array
	 * @return Return the decrypted Byte-Array
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] decrypt(byte[] input) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		int space = KEYSIZE/8;
		byte[] result = new byte[0];
		byte[] temp = new byte[space];
		int tempCounter = 0;
		
		if (space < input.length) {
			
			for (int ii = 0; ii < input.length; ii++) {
				temp[tempCounter] = input[ii];
				
				if (tempCounter == space - 1) {
					result = GeneralHelper.appendByteArray(result, decryptInternal(temp));
					tempCounter = 0;
				} else {
					tempCounter++;
				}
			}
		} else {
			result = decryptInternal(input);
		}
		return result;
	}

	/**
	 * Generates a public and a private KeyPair whith the Class<br>
	 * KeyPairGenerator and saves it to the intern attributes.
	 * @throws NoSuchAlgorithmException
	 * @see java.security.KeyPair
	 * @see java.security.KeyPairGenerator
	 */
	public void generateKeys() throws NoSuchAlgorithmException {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key-pair
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
		kpg.initialize(KEYSIZE);
		KeyPair kp = kpg.generateKeyPair();

		this.publicKey = kp.getPublic();
		this.privateKey = kp.getPrivate();
	}

	/**
	 * Encrypt the Data with a given PublicKey internal.
	 * 
	 * @param input The Data to encrypt as a Byte-Array
	 * @param key The PublicKey for the encryption
	 * @return Return the encrypted Byte-Array 
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private byte[] encryptInternal(byte[] input, PublicKey key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(xform, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(input);
	}

	/**
	 * Decrypt the Data internal.
	 * 
	 * @param input The encrypted Data as a Byte-Array
	 * @return Return the decrypted Byte-Array
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private byte[] decryptInternal(byte[] input) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = Cipher.getInstance(xform, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
		
		return cipher.doFinal(input);
	}
}
