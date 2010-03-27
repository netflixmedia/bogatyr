/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.service.crypto;

import java.security.Key;

import javax.crypto.Cipher;

import ch.customcode.bogatyr.service.Service;


/**
 * This is an interface for wrapping and unwrapping a crypto key.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.6.0
 */
public interface KeyWrapper extends Service {
	/**
	 * Wrap the {@link Key} with a wrapper {@link Key}.
	 * 
	 * @param wrapperKey e.g RSA public-key
     * @param key e.g. AES-key
     * @return byte-array with the wrapped {@link Key}
     * @throws Exception
     * @see Key
     * @since 0.6.0
	 */
	byte[] wrap(Key wrapperKey, Key key) throws Exception;
	
	/**
	 * Unwrap and return the {@link Key}.
	 * 
	 * @param wrapperKey e.g. RSA private-key
     * @param wrappedKey as byte-array
     * @param keyAlgorithm e.g. "AES"
     * @param keyType e.g. Cipher.SECRET_KEY
     * @return unwrapped {@link Key}
     * @throws Exception
     * @see Key
     * @see Cipher
     * @since 0.6.0
	 */
	Key unwrap(Key wrapperKey, byte[] wrappedKey, String keyAlgorithm, int keyType) throws Exception;
}
