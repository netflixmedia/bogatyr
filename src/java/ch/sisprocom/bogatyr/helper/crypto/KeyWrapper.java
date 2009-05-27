/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.HelperString;

/**
 * This is a class for wrapping and unwrapping a crypto key.
 * 
 * @author Stefan Laubenberger
 * @version 0.7.0 (20090527)
 * @since 0.3.0
 */
public class KeyWrapper implements IKeyWrapper {
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
	
	/*
	 * Implemented methods
	 */
	public byte[] wrap(final Key wrapperKey, final Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, NoSuchProviderException {
		if (null == wrapperKey) {
			throw new IllegalArgumentException("wrapperKey is null!"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}
		
		final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.WRAP_MODE, wrapperKey);

		return cipher.wrap(key);
	}

	public Key unwrap(final Key wrapperKey, final byte[] wrappedKey, final String keyAlgorithm, final int keyType) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
		if (null == wrapperKey) {
			throw new IllegalArgumentException("wrapperKey is null!"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(wrappedKey)) {
			throw new IllegalArgumentException("wrappedKey is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(keyAlgorithm)) {
			throw new IllegalArgumentException("keyAlgorithm is null or empty!"); //$NON-NLS-1$
		}
		if (0 >= keyType) {
			throw new IllegalArgumentException("keyType is invalid: " + keyType); //$NON-NLS-1$
		}
		
		final Cipher cipher = Cipher.getInstance(CryptoAsymm.XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.UNWRAP_MODE, wrapperKey);

		return cipher.unwrap(wrappedKey, keyAlgorithm, keyType);
	}
}
