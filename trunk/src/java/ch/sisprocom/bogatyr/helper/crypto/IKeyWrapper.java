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


/**
 * This is an interface for wrapping and unwrapping a crypto key.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090527)
 * @since 0.6.0
 */
public interface IKeyWrapper {
	/**
	 * Wrap the {@link Key} with a wrapper {@link Key}.
	 * 
	 * @param wrapperKey e.g RSA public-key
     * @param key e.g. AES-key
     * @return byte-array with the wrapped key
     * @throws Exception
     * @since 0.6.0
	 */
	byte[] wrap(final Key wrapperKey, final Key key) throws Exception;
	
	/**
	 * Unwrap and return the {@link Key}.
	 * 
	 * @param wrapperKey e.g. RSA private-key
     * @param wrappedKey as byte-array
     * @param keyAlgorithm e.g. "AES"
     * @param keyType e.g. Cipher.SECRET_KEY
     * @return unwrapped key
     * @throws Exception
     * @since 0.6.0
	 */
	Key unwrap(final Key wrapperKey, final byte[] wrappedKey, final String keyAlgorithm, final int keyType) throws Exception;
}
