/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * This is a class for wrapping and unwrapping a crypto key.
 * 
 * @author Stefan Laubenberger
 * @version 20081205
 */
public abstract class Wrapper {
	/**
	 * Wrap the key with a wrapper key.
	 * 
	 * @param wrapperKey (e.g RSA public-key)
     * @param key (e.g. AES-key)
     * @return byte array with the wrapped key
     * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchProviderException
	 */
	public static synchronized byte[] wrap(final Key wrapperKey, final Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, NoSuchProviderException {
		final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.WRAP_MODE, wrapperKey);

		return cipher.wrap(key);
	}
	
	/**
	 * Unwrap and return the key.
	 * 
	 * @param wrapperKey (e.g. RSA private-key)
     * @param wrappedKey
     * @param keyAlgorithm (e.g. "AES")
     * @param keyType (e.g. Cipher.SECRET_KEY)
     * @return the unwrapped key
     * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException
	 */
	public static synchronized Key unwrap(final Key wrapperKey, final byte[] wrappedKey, final String keyAlgorithm, final int keyType) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
		final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.UNWRAP_MODE, wrapperKey);

		return cipher.unwrap(wrappedKey, keyAlgorithm, keyType);
	}
}
