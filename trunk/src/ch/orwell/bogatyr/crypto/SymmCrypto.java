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

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * This is a class for symmetric cryptology via AES
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080225
 */
public final class SymmCrypto {
	public static final String ALGORITHM     = "AES"; //$NON-NLS-1$
	public static final String XFORM         = "AES/CBC/PKCS5Padding"; //$NON-NLS-1$
	
	public SymmCrypto() {
		super();
	}

	/**
	 * Encrypt the data with a given {@link Key}.
	 * 
	 * @param input The data to encrypt as a Byte-Array
	 * @param encryptionKey The key for the encryption
	 * @return Return the encrypted Byte-Array 
	 * @throws Exception 
	 * @see java.security.Key
	 */
	public byte[] encrypt(byte[] input, Key encryptionKey, int keysize) throws Exception {
        Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, encryptionKey, prepareIv(keysize));
		
		return cipher.doFinal(input);
	}

	/**
	 * Decrypt the data with a given {@link Key}.
	 * 
	 * @param input The encrypted data as a Byte-Array
	 * @param decryptionKey The key for the decryption
	 * @return Return the decrypted Byte-Array
	 * @throws Exception
	 * @see java.security.Key
	 */
	public byte[] decrypt(byte[] input, Key decryptionKey, int keysize) throws Exception {
		Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, decryptionKey, prepareIv(keysize));
		
		return cipher.doFinal(input);
	}

	/**
	 * Generates a key. Sets the intern attribute key to the generated key.
	 * With this generated key, you can encrypt and decrypt Byte-Arrays.
	 * 
	 * @param keysize Size of the key in bits (e.g. 128, 256)
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException 
	 * @see #encrypt(byte[], Key)
	 * @see #key
	 * @see #decrypt(byte[], Key)
	 */
	public SecretKey generateKey(int keysize) throws NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key
		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM, "BC"); //$NON-NLS-1$
		kg.init(keysize);
		
		return kg.generateKey();
	}
	
	
	/*
	 * Private methods
	 */
	private IvParameterSpec prepareIv(int keysize) {
		int elements = keysize/16;
        byte[] ivBytes = new byte[elements];
        
        for (int ii = 0; ii < elements; ii++) {
        	ivBytes[ii] = (byte) 0x5a;
        }
        
        return new IvParameterSpec(ivBytes);
	}
}
