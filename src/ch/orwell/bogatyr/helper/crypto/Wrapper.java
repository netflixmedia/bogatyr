/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper.crypto;

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
 * @version 20080614
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
//		if (key != null) {
			final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
			cipher.init(Cipher.WRAP_MODE, wrapperKey);
			return cipher.wrap(key);
//		}
//		return null;
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
//		if (wrappedKey != null) {
			final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
			cipher.init(Cipher.UNWRAP_MODE, unwrapperKey);
			return cipher.unwrap(wrappedKey, keyAlgorithm, keyType);
//		}
//		return null;
	}
}
