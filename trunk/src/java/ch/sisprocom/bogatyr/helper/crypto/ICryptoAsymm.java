/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * This is an interface for asymmetric cryptology.
 * 
 * @author Stefan Laubenberger
 * @version 20090508
 */
public interface ICryptoAsymm {
	/**
	 * Generates a public and a private {@link KeyPair} with the algorithms standard key size.
	 * 
	 * @return generated key pair
	 * @throws Exception
	 * @see KeyPair
	 */
	KeyPair generateKeyPair() throws Exception;

	/**
	 * Generates a public and a private {@link KeyPair} with a given key size.
	 * 
	 * @param keysize in bits (normally modulo 16 = 0, e.g. 1024, 2048)
	 * @return generated key pair
	 * @throws Exception
	 * @see KeyPair
	 */
	KeyPair generateKeyPair(final int keysize) throws Exception;

	/**
	 * Encrypt the data (byte-array) with a given {@link PublicKey}.
	 * Use this method only, if the key has the algorithms standard key size. 
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
     * @return encrypted byte-array
	 * @throws Exception  
	 * @see PublicKey
	 */
	byte[] encrypt(final byte[] input, final PublicKey key) throws Exception;
	
	/**
	 * Encrypt the data (byte-array) with a given {@link PublicKey} and key size.
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
	 * @param keysize in bits (normally modulo 16 = 0, e.g. 1024, 2048)
     * @return encrypted byte-array
	 * @throws Exception  
	 * @see PublicKey
	 */
	byte[] encrypt(final byte[] input, final PublicKey key, final int keysize) throws Exception;

	/**
	 * Decrypt the data (byte-array) with a given {@link PrivateKey}.
	 * Use this method only, if the key has the algorithms standard key size. 
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
     * @return decrypted byte-array
	 * @throws Exception 
	 */
	byte[] decrypt(final byte[] input, final PrivateKey key) throws Exception;
	
	/**
	 * Decrypt the data (byte-array) with a given {@link PrivateKey} and key size.
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
	 * @param keysize in bits (normally modulo 16 = 0, e.g. 1024, 2048)
     * @return decrypted byte-array
	 * @throws Exception 
	 */
	byte[] decrypt(final byte[] input, final PrivateKey key, final int keysize) throws Exception;

}
