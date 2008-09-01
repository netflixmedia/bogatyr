/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.sisprocom.bogatyr.helper.logger.Logger;

/**
 * This is a class for symmetric cryptology via AES
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public abstract class CryptoSymm {
	public static final String ALGORITHM = "AES"; //$NON-NLS-1$
	public static final String XFORM     = "AES/CBC/PKCS5Padding"; //$NON-NLS-1$

	
	/**
	 * Generates a key. Sets the intern attribute key to the generated key.
	 * With this generated key, you can encrypt and decrypt Byte-Arrays.
	 * 
	 * @param keysize Size of the key in bits (e.g. 128, 256)
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException 
     * @return generated secret key
	 * @see #encrypt(byte[], Key)
	 * @see #decrypt(byte[], Key)
	 */
	public static SecretKey generateKey(final int keysize) throws NoSuchAlgorithmException, NoSuchProviderException {
		Logger.getInstance().writeMethodEntry(CryptoSymm.class, "generateKey", keysize);  //$NON-NLS-1$

		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key
		final KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM, "BC"); //$NON-NLS-1$
		kg.init(keysize);
		
		final SecretKey sk = kg.generateKey();
		
		Logger.getInstance().writeMethodExit(CryptoSymm.class, "generateKey", sk);  //$NON-NLS-1$
		return sk;
	}
	
	/**
	 * Encrypt the data with a given {@link Key}.
	 * 
	 * @param input data to encrypt as a Byte-Array
	 * @param key for the encryption
	 * @return Return the encrypted Byte-Array 
	 * @throws Exception 
	 * @see Key
	 */
	public static byte[] encrypt(final byte[] input, final Key key) throws Exception {
		Logger.getInstance().writeMethodEntry(CryptoSymm.class, "encrypt", new Object[]{input, key});  //$NON-NLS-1$

		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, key, prepareIv());

		final byte[] result = cipher.doFinal(input);
		
		Logger.getInstance().writeMethodExit(CryptoSymm.class, "encrypt", result);  //$NON-NLS-1$
		return result;
	}

	/**
	 * Decrypt the data with a given {@link Key}.
	 * 
	 * @param input encrypted data as a Byte-Array
	 * @param key for the decryption
	 * @return Return the decrypted Byte-Array
	 * @throws Exception
	 * @see Key
	 */
	public static byte[] decrypt(final byte[] input, final Key key) throws Exception {
		Logger.getInstance().writeMethodEntry(CryptoSymm.class, "decrypt", new Object[]{input, key});  //$NON-NLS-1$

		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, key, prepareIv());

		final byte[] result = cipher.doFinal(input);
		
		Logger.getInstance().writeMethodExit(CryptoSymm.class, "decrypt", result);  //$NON-NLS-1$
		return result;
	}
	
	
	/*
	 * Private methods
	 */
	private static AlgorithmParameterSpec prepareIv() {
		Logger.getInstance().writeMethodEntry(CryptoSymm.class, "prepareIv");  //$NON-NLS-1$

		final int elements = 16;
        final byte[] ivBytes = new byte[elements];
        
        for (int ii = 0; ii < elements; ii++) {
        	ivBytes[ii] = (byte) 0x5a;
        }
        
        final AlgorithmParameterSpec algo = new IvParameterSpec(ivBytes);
        
		Logger.getInstance().writeMethodExit(CryptoSymm.class, "prepareIv", algo);  //$NON-NLS-1$
        return algo;
	}
}
