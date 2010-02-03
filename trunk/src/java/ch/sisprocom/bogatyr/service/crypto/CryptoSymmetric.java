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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.crypto;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

import javax.crypto.SecretKey;

import ch.sisprocom.bogatyr.service.Service;

/**
 * This is an interface for symmetric cryptology.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.6.0
 */
public interface CryptoSymmetric extends Service { //$Example$
	/**
	 * Generates a {@link SecretKey} with the algorithms standard key size.
	 * 
     * @return generated {@link SecretKey}
	 * @throws Exception 
	 * @see SecretKey 
	 * @since 0.6.0
	 */
	SecretKey generateKey() throws Exception;
	
	/**
	 * Generates a {@link SecretKey} with a given key size.
	 * 
	 * @param keySize in bits (e.g. 128, 192 or 256)
     * @return generated {@link SecretKey}
	 * @throws Exception 
	 * @see SecretKey 
	 * @since 0.6.0
	 */
	SecretKey generateKey(int keySize) throws Exception;
	
	/**
	 * Encrypt the data (byte-array) with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
	 * @return encrypted byte-array 
	 * @throws Exception 
	 * @see Key
	 * @since 0.6.0
	 */
	byte[] encrypt(byte[] input, Key key) throws Exception;
	
	/**
	 * Decrypt the data with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
	 * @return decrypted byte-array
	 * @throws Exception 
	 * @see Key
	 * @since 0.6.0
	 */
	byte[] decrypt(byte[] input, Key key) throws Exception;
	
	/**
	 * Encrypt an {@link InputStream} to an {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is {@link InputStream} to encrypt
	 * @param os {@link OutputStream} for the encrypted data
	 * @param key for the encryption
	 * @throws Exception 
	 * @see InputStream
	 * @see OutputStream
	 * @see Key
	 * @since 0.8.0
	 */
	void encrypt(InputStream is, OutputStream os, Key key) throws Exception;

	/**
	 * Encrypt an {@link InputStream} to an {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is {@link InputStream} to encrypt
	 * @param os {@link OutputStream} for the encrypted data
	 * @param key for the encryption
	 * @param bufferSize in bytes
	 * @throws Exception 
	 * @see InputStream
	 * @see OutputStream
	 * @see Key
	 * @since 0.8.0
	 */
    void encrypt(InputStream is, OutputStream os, Key key, int bufferSize) throws Exception;

	/**
	 * Decrypt an {@link InputStream} to an {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is {@link InputStream} to decrypt
	 * @param os {@link OutputStream} for the decrypted data
	 * @param key for the decryption
	 * @throws Exception 
	 * @see InputStream
	 * @see OutputStream
	 * @see Key
	 * @since 0.8.0
	 */
    void decrypt(InputStream is, OutputStream os, Key key) throws Exception;

	/**
	 * Decrypt an {@link InputStream} to an {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is {@link InputStream} to decrypt
	 * @param os {@link OutputStream} for the decrypted data
	 * @param key for the decryption
	 * @param bufferSize in bytes
	 * @throws Exception 
	 * @see InputStream
	 * @see OutputStream
	 * @see Key
	 * @since 0.8.0
	 */
    void decrypt(InputStream is, OutputStream os, Key key, int bufferSize) throws Exception;
    
	/**
	 * Encrypt an input {@link File} to an output {@link File} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input {@link File} to encrypt
	 * @param output {@link File} for the encrypted data
	 * @param key for the encryption
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
	void encrypt(File input, File output, Key key) throws Exception;

	/**
	 * Encrypt an input {@link File} to an output {@link File} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input {@link File} to encrypt
	 * @param output {@link File} for the encrypted data
	 * @param key for the encryption
	 * @param bufferSize in bytes
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
    void encrypt(File input, File output, Key key, int bufferSize) throws Exception;

	/**
	 * Decrypt an input {@link File} to an output {@link File} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input {@link File} to decrypt
	 * @param output {@link File} for the decrypted data
	 * @param key for the decryption
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
    void decrypt(File input, File output, Key key) throws Exception;

	/**
	 * Decrypt an input {@link File} to an output {@link File} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input {@link File} to decrypt
	 * @param output {@link File} for the decrypted data
	 * @param key for the decryption
	 * @param bufferSize in bytes
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
    void decrypt(File input, File output, Key key, int bufferSize) throws Exception;
}
