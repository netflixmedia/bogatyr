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
package ch.sisprocom.bogatyr.service.crypto;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.service.ServiceAbstract;
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
 * @version 0.9.0 (20091111)
 * @since 0.1.0
 */
public class CryptoRSA extends ServiceAbstract implements CryptoAsymmetric {
	public static final String ALGORITHM = "RSA"; //$NON-NLS-1$
	public static final String XFORM     = "RSA/NONE/PKCS1PADDING"; //$NON-NLS-1$
//	public static final String XFORM     = "RSA/NONE/NoPadding"; //$NON-NLS-1$
	public static final int DEFAULT_KEY_SIZE = 1024;

	
	/*
	 * Private methods
	 */
	/**
	 * Encrypt the data with a given {@link Key}.
	 * 
	 * @param input data (byte-array) to encrypt
	 * @param key for the encryption
	 * @return encrypted byte-array 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @since 0.1.0
	 */
	private static byte[] encryptInternal(final byte[] input, final Key key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, key);

		return cipher.doFinal(input);
	}

	/**
	 * Decrypt the data with a given {@link Key}.
	 * 
	 * @param input data (byte-array) to decrypt
	 * @param key for the decryption
	 * @return decrypted byte-array
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @since 0.1.0
	 */
	private static byte[] decryptInternal(final byte[] input, final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, key);

		return cipher.doFinal(input);
	}

	
	/*
	 * Implemented methods
	 */
	/**
	 * Generates a public and a private {@link KeyPair} with the RSA standard key size of 1024 bits.
	 * 
	 * @return generated key pair
	 * @see KeyPair
	 * @since 0.1.0
	 */
	@Override
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException { //$JUnit$
		return generateKeyPair(DEFAULT_KEY_SIZE);
	}
	
	@Override
    public KeyPair generateKeyPair(final int keySize) throws NoSuchAlgorithmException, NoSuchProviderException { //$JUnit$
		if (0 >= keySize || 0 != keySize % HelperNumber.INT_16) {
			throw new IllegalArgumentException("keySize is invalid: " + keySize); //$NON-NLS-1$
		}
		
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key-pair
		final KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM, "BC"); //$NON-NLS-1$
		kpg.initialize(keySize);

		return kpg.generateKeyPair();
	}

	/**
	 * Encrypt the data (byte-array) with a given {@link PublicKey}.
	 * Use this method only, if the key has the RSA standard key size of 1024 bits. 
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
     * @return encrypted byte-array
	 * @see PublicKey
	 * @since 0.1.0
	 */
    @Override
    public byte[] encrypt(final byte[] input, final PublicKey key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException { //$JUnit$
    	return encrypt(input, key, DEFAULT_KEY_SIZE);
    }
    
    @Override
    public byte[] encrypt(final byte[] input, final PublicKey key, final int keySize) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException { //$JUnit$
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
    	if (0 >= keySize || 0 != keySize % HelperNumber.INT_16) {
			throw new IllegalArgumentException("keySize is invalid: " + keySize); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("the doubled input (" + input.length * 2 + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
   	
    	final int space = keySize/8 - 11;
		byte[] result = null;
		final byte[] temp = new byte[space];
		
		if (space < input.length) {

            int tempCounter = 0;
            for (int ii = 0; ii < input.length; ii++) {
				
				temp[tempCounter] = input[ii];
				
				if (tempCounter == space - 1) {
					result = HelperArray.concatenate(result, encryptInternal(temp, key));
					tempCounter = 0;
				} else {
					if (ii == input.length - 1) { // last byte
						final byte[] usedBytes = new byte[tempCounter + 1];
						
						for (int xx = 0; xx <= tempCounter; xx++) {
							usedBytes[xx] = input[ii - tempCounter + xx];
						}
						result = HelperArray.concatenate(result, encryptInternal(usedBytes, key));
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
	 * Decrypt the data (byte-array) with a given {@link PrivateKey}.
	 * Use this method only, if the key has the RSA standard key size of 1024 bits.
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
     * @return decrypted byte-array
     * @since 0.1.0
	 */
	@Override
    public byte[] decrypt(final byte[] input, final PrivateKey key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		return decrypt(input, key, DEFAULT_KEY_SIZE);
	}

	@Override
    public byte[] decrypt(final byte[] input, final PrivateKey key, final int keySize) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
    	if (0 >= keySize || 0 != keySize % HelperNumber.INT_16) {
			throw new IllegalArgumentException("keySize is invalid: " + keySize); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("the doubled input (" + input.length * 2 + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }

		final int space = keySize/8;
		byte[] result = null;
		final byte[] temp = new byte[space];
		
		if (space < input.length) {

            int tempCounter = 0;
            for (final byte data : input) {
				temp[tempCounter] = data;
				
				if (tempCounter == space - 1) {
					result = HelperArray.concatenate(result, decryptInternal(temp, key));
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
}
