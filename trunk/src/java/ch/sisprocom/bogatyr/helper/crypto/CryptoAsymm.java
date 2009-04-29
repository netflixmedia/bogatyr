/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.crypto;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

/**
 * This is a class for asymmetric cryptology via RSA.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public class CryptoAsymm implements ICryptoAsymm {
	public static final String ALGORITHM = "RSA"; //$NON-NLS-1$
	public static final String XFORM     = "RSA/NONE/PKCS1PADDING"; //$NON-NLS-1$
//	public static final String XFORM     = "RSA/NONE/NoPadding"; //$NON-NLS-1$

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	
	/*
	 * Implemented methods
	 */
	public KeyPair generateKeys(final int keysize) throws NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key-pair
		final KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM, "BC"); //$NON-NLS-1$
		kpg.initialize(keysize);

		return kpg.generateKeyPair();
	}

    public byte[] encrypt(final byte[] input, final PublicKey key, final int keysize) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		final int space = keysize/8 - 11;
		byte[] result = null;
		final byte[] temp = new byte[space];
		
		if (space < input.length) {

            int tempCounter = 0;
            for (int ii = 0; ii < input.length; ii++) {
				
				temp[tempCounter] = input[ii];
				
				if (tempCounter == space - 1) {
					result = HelperGeneral.concatenateByteArrays(result, encryptInternal(temp, key));
					tempCounter = 0;
				} else {
					if (ii == input.length - 1) { // last byte
						final byte[] usedBytes = new byte[tempCounter + 1];
						
						for (int xx = 0; xx <= tempCounter; xx++) {
							usedBytes[xx] = input[ii - tempCounter + xx];
						}
						result = HelperGeneral.concatenateByteArrays(result, encryptInternal(usedBytes, key));
					}
					tempCounter++;
				}
			}
		} else {
			result = encryptInternal(input, key);
		}
		return result;
	}

	public byte[] decrypt(final byte[] input, final PrivateKey key, final int keysize) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		final int space = keysize/8;
		byte[] result = null;
		final byte[] temp = new byte[space];
		
		if (space < input.length) {

            int tempCounter = 0;
            for (final byte data : input) {
				temp[tempCounter] = data;
				
				if (tempCounter == space - 1) {
					result = HelperGeneral.concatenateByteArrays(result, decryptInternal(temp, key));
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

	
	/*
	 * Private methods
	 */
	/**
	 * Encrypt the data with a given {@link PublicKey} (internal).
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key {@link PublicKey} for the encryption
	 * @return Return the encrypted byte-array 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 */
	private static byte[] encryptInternal(final byte[] input, final Key key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, key);

		return cipher.doFinal(input);
	}

	/**
	 * Decrypt the data (internal).
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key {@link PrivateKey} for the decryption
	 * @return Return the decrypted byte-array
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private static byte[] decryptInternal(final byte[] input, final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, key);

		return cipher.doFinal(input);
	}
}
