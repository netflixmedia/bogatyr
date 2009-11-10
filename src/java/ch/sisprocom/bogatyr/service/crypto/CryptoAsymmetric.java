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
package ch.sisprocom.bogatyr.service.crypto;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import ch.sisprocom.bogatyr.service.Service;

/**
 * This is an interface for asymmetric cryptology.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.6.0
 */
public interface CryptoAsymmetric extends Service { //$Example$
	/**
	 * Generates a public and a private {@link KeyPair} with the algorithms standard key size.
	 * 
	 * @return generated {@link KeyPair}
	 * @throws Exception
	 * @see KeyPair
	 * @since 0.6.0
	 */
	KeyPair generateKeyPair() throws Exception;

	/**
	 * Generates a public and a private {@link KeyPair} with a given key size.
	 * 
	 * @param keysize in bits (normally modulo 16 = 0, e.g. 1024, 2048)
	 * @return generated {@link KeyPair}
	 * @throws Exception
	 * @see KeyPair
	 * @since 0.6.0
	 */
	KeyPair generateKeyPair(int keysize) throws Exception;

	/**
	 * Encrypt the data (byte-array) with a given {@link PublicKey} and the implementations algorithm.
	 * Use this method only, if the key has the algorithms standard key size. 
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
     * @return encrypted byte-array
	 * @throws Exception  
	 * @see PublicKey
	 * @since 0.6.0
	 */
	byte[] encrypt(byte[] input, PublicKey key) throws Exception;
	
	/**
	 * Encrypt the data (byte-array) with a given {@link PublicKey}, key size and the implementations algorithm.
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
	 * @param keySize in bits (normally modulo 16 = 0, e.g. 1024, 2048)
     * @return encrypted byte-array
	 * @throws Exception  
	 * @see PublicKey
	 * @since 0.6.0
	 */
	byte[] encrypt(byte[] input, PublicKey key, int keySize) throws Exception;

	/**
	 * Decrypt the data (byte-array) with a given {@link PrivateKey} and the implementations algorithm.
	 * Use this method only, if the key has the algorithms standard key size. 
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
     * @return decrypted byte-array
	 * @throws Exception 
	 * @see PrivateKey
	 * @since 0.6.0
	 */
	byte[] decrypt(byte[] input, PrivateKey key) throws Exception;
	
	/**
	 * Decrypt the data (byte-array) with a given {@link PrivateKey}, key size and the implementations algorithm.
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
	 * @param keySize in bits (normally modulo 16 = 0, e.g. 1024, 2048)
     * @return decrypted byte-array
	 * @throws Exception
	 * @see PrivateKey 
	 * @since 0.6.0
	 */
	byte[] decrypt(byte[] input, PrivateKey key, int keySize) throws Exception;

}
