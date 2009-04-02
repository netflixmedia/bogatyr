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
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * This is an interface for asymmetric cryptology.
 * 
 * @author Stefan Laubenberger
 * @version 20090402
 */
public interface ICryptoAsymm {
	/**
	 * Generates a public and a private KeyPair with the Class
	 * KeyPairGenerator and saves it to the internal attributes.
	 * 
	 * @param keysize Size of the key in bits (modulo 16 = 0, e.g. 1024, 2048)
	 * @return generated key pair
	 * @throws Exception
	 * @see KeyPair
	 * @see KeyPairGenerator
	 */
	KeyPair generateKeys(final int keysize) throws Exception;
	
	/**
	 * Encrypt the data with a given {@link PublicKey}.
	 * 
	 * @param input The data to encrypt as a byte-array
	 * @param key {@link PublicKey} for the encryption
	 * @param keysize Size of the key in bits (modulo 16 = 0, e.g. 1024, 2048)
     * @return Return the encrypted byte-array
	 * @throws Exception  
	 * @see PublicKey
	 */
	byte[] encrypt(final byte[] input, final PublicKey key, final int keysize) throws Exception;
	
	/**
	 * Decrypt the data.
	 * 
	 * @param input The encrypted data as a byte-array
	 * @param key {@link PrivateKey} for the decryption
	 * @param keysize of the key in bits (modulo 16 = 0, e.g. 1024, 2048)
     * @return Return the decrypted byte-array
	 * @throws Exception 
	 */
	byte[] decrypt(final byte[] input, final PrivateKey key, final int keysize) throws Exception;

}
