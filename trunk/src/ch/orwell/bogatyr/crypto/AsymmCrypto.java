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
package ch.orwell.bogatyr.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.orwell.bogatyr.util.GeneralHelper;

/**
 * This is a class for asymmetric cryptology via RSA
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080225
 */
public final class AsymmCrypto {
	public static final String ALGORITHM     = "RSA"; //$NON-NLS-1$
	public static final String XFORM         = "RSA/NONE/PKCS1PADDING"; //$NON-NLS-1$

	/**
	 * Encrypt the data with a given {@link PublicKey}.
	 * 
	 * @param input The data to encrypt as a Byte-Array
	 * @param key The {@link PublicKey} for the encryption
	 * @return Return the encrypted Byte-Array 
	 * @throws Exception
	 * @see PublicKey
	 */
	public byte[] encrypt(byte[] input, PublicKey key, int keysize) throws Exception {
		int space = keysize/8 - 11;
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
	 * Decrypt the data.
	 * 
	 * @param input The encrypted data as a Byte-Array
	 * @param key The {@link PrivateKey} for the decryption
	 * @return Return the decrypted Byte-Array
	 * @throws Exception
	 */
	public byte[] decrypt(byte[] input, PrivateKey key, int keysize) throws Exception {
		int space = keysize/8;
		byte[] result = new byte[0];
		byte[] temp = new byte[space];
		int tempCounter = 0;
		
		if (space < input.length) {
			
			for (int ii = 0; ii < input.length; ii++) {
				temp[tempCounter] = input[ii];
				
				if (tempCounter == space - 1) {
					result = GeneralHelper.appendByteArray(result, decryptInternal(temp, key));
					tempCounter = 0;
				} else {
					tempCounter++;
				}
			}
		} else {
			result = decryptInternal(input, key);
		}
		return result;
	}

	/**
	 * Generates a public and a private KeyPair with the Class
	 * KeyPairGenerator and saves it to the internal attributes.
	 * 
	 * @param keysize Size of the key in bits (e.g. 1024, 2048, 4096)
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException 
	 * @see java.security.KeyPair
	 * @see java.security.KeyPairGenerator
	 */
	public KeyPair generateKeys(int keysize) throws NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key-pair
		KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM, "BC"); //$NON-NLS-1$
		kpg.initialize(keysize);
		
		return kpg.generateKeyPair();
	}

	/*
	 * Private methods
	 */
	/**
	 * Encrypt the data with a given {@link PublicKey} (internal).
	 * 
	 * @param input The data to encrypt as a Byte-Array
	 * @param key The {@link PublicKey} for the encryption
	 * @return Return the encrypted Byte-Array 
	 * @throws Exception
	 */
	private byte[] encryptInternal(byte[] input, PublicKey key) throws Exception {
//		Cipher cipher = Cipher.getInstance(xform, "BC"); //$NON-NLS-1$
		Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, key);
		
		return cipher.doFinal(input);
	}

	/**
	 * Decrypt the data (internal).
	 * 
	 * @param input The encrypted data as a Byte-Array
	 * @param key The {@link PrivateKey} for the decryption
	 * @return Return the decrypted Byte-Array
	 * @throws Exception
	 */
	private byte[] decryptInternal(byte[] input, PrivateKey key) throws Exception {
//		Cipher cipher = Cipher.getInstance(xform, "BC"); //$NON-NLS-1$
		Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		return cipher.doFinal(input);
	}
}
