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
 * This is a class for wrapping and unwrapping a key
 * 
 * @author Stefan Laubenberger
 * @version 20081026
 */
public abstract class Wrapper {
	/**
	 * Wrap the key
	 * @param wrapperKey
     * @param key
     * @return byte array with the wrapped key
     * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchProviderException
	 */
	public static byte[] wrapKey(final Key wrapperKey, final Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, NoSuchProviderException {
		final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.WRAP_MODE, wrapperKey);

		final byte[] result = cipher.wrap(key);
		
		return result;
	}
	
	/**
	 * Unwrap and set the key
	 * @param unwrapperKey
     * @param wrappedKey
     * @param keyAlgorithm
     * @param keyType
     * @return byte array with the unwrapped key
     * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException
	 */
	public static Key unwrapKey(final Key unwrapperKey, final byte[] wrappedKey, final String keyAlgorithm, final int keyType) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
		final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.UNWRAP_MODE, unwrapperKey);

		final Key key = cipher.unwrap(wrappedKey, keyAlgorithm, keyType);
		
		return key;
	}
}
