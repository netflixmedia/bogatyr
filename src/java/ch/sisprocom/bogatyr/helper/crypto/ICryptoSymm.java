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

import java.security.Key;

import javax.crypto.SecretKey;

/**
 * This is an interface for symmetric cryptology.
 * 
 * @author Stefan Laubenberger
 * @version 20090402
 */
public interface ICryptoSymm {
	/**
	 * Generates a key. Sets the intern attribute key to the generated key.
	 * With this generated key, you can encrypt and decrypt Byte-Arrays.
	 * 
	 * @param keysize Size of the key in bits (must be 128, 192 or 256)
     * @return generated secret key
	 * @throws Exception 
	 * @see #encrypt(byte[], Key)
	 * @see #decrypt(byte[], Key)
	 */
	SecretKey generateKey(final int keysize) throws Exception;
	
	/**
	 * Encrypt the data with a given {@link Key}.
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
	 * @return Return the encrypted byte-array 
	 * @throws Exception 
	 * @see Key
	 */
	byte[] encrypt(final byte[] input, final Key key) throws Exception;
	
	/**
	 * Decrypt the data with a given {@link Key}.
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
	 * @return Return the decrypted byte-array
	 * @throws Exception 
	 * @see Key
	 */
	byte[] decrypt(final byte[] input, final Key key) throws Exception;
}
