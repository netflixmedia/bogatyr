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

import javax.crypto.SecretKey;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;

/**
 * This is an interface for symmetric cryptology.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.6.0
 */
public interface CryptoSymmetric {
	/**
	 * Generates a {@link SecretKey} with the algorithms standard key size.
	 * 
     * @return generated secret key
	 * @throws Exception 
	 * @see SecretKey 
	 * @since 0.6.0
	 */
	SecretKey generateKey() throws Exception;
	
	/**
	 * Generates a {@link SecretKey} with a given key size.
	 * 
	 * @param keySize in bits (e.g. 128, 192 or 256)
     * @return generated secret key
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
	 * Encrypt an input stream {@link InputStream} to an output stream {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is input stream to encrypt
	 * @param os output stream for the encrypted data
	 * @param key for the encryption
	 * @throws Exception 
	 * @see InputStream
	 * @see OutputStream
	 * @see Key
	 * @since 0.8.0
	 */
	void encrypt(InputStream is, OutputStream os, Key key) throws Exception;

	/**
	 * Encrypt an input stream {@link InputStream} to an output stream {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is input stream to encrypt
	 * @param os output stream for the encrypted data
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
	 * Decrypt an input stream {@link InputStream} to an output stream {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is input stream to decrypt
	 * @param os output stream for the decrypted data
	 * @param key for the decryption
	 * @throws Exception 
	 * @see InputStream
	 * @see OutputStream
	 * @see Key
	 * @since 0.8.0
	 */
    void decrypt(InputStream is, OutputStream os, Key key) throws Exception;

	/**
	 * Decrypt an input stream {@link InputStream} to an output stream {@link OutputStream} with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param is input stream to decrypt
	 * @param os output stream for the decrypted data
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
	 * Encrypt an input file {@link File} to an output file with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input file to encrypt
	 * @param output file for the encrypted data
	 * @param key for the encryption
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
	void encrypt(File input, File output, Key key) throws Exception;

	/**
	 * Encrypt an input file {@link File} to an output file with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input file to encrypt
	 * @param output file for the encrypted data
	 * @param key for the encryption
	 * @param bufferSize in bytes
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
    void encrypt(File input, File output, Key key, int bufferSize) throws Exception;

	/**
	 * Decrypt an input file {@link File} to an output file with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input file to decrypt
	 * @param output file for the decrypted data
	 * @param key for the decryption
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
    void decrypt(File input, File output, Key key) throws Exception;

	/**
	 * Decrypt an input file {@link File} to an output file with a given {@link Key} and the implementations algorithm.
	 * 
	 * @param input file to decrypt
	 * @param output file for the decrypted data
	 * @param key for the decryption
	 * @param bufferSize in bytes
	 * @throws Exception 
	 * @see File
	 * @see Key
	 * @since 0.8.0
	 */
    void decrypt(File input, File output, Key key, int bufferSize) throws Exception;
}
