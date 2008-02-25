/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.crypto;

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
 * @author Silvan Spross
 * @version 20080225
 */
public final class Wrapper {
	/**
	 * Wrap the key
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchProviderException 
	 */
	public static byte[] wrapKey(Key wrapperKey, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, NoSuchProviderException {
		if (key != null) {
			Cipher cipher = Cipher.getInstance(AsymmCrypto.XFORM, "BC"); //$NON-NLS-1$
			cipher.init(Cipher.WRAP_MODE, wrapperKey);
			return cipher.wrap(key);
		}
		return null;
	}
	
	/**
	 * Unwrap and set the key
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws IllegalBlockSizeException 
	 */
	public static Key unwrapKey(Key unwrapperKey, byte[] wrappedKey, String keyAlgorithm, int keyType) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
		if (wrappedKey != null) {
			Cipher cipher = Cipher.getInstance(AsymmCrypto.XFORM, "BC"); //$NON-NLS-1$
			cipher.init(Cipher.UNWRAP_MODE, unwrapperKey);
			return cipher.unwrap(wrappedKey, keyAlgorithm, keyType);
		}
		return null;
	}
}
