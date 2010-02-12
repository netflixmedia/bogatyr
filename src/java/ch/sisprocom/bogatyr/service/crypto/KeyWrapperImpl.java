/*******************************************************************************
 * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

/**
 * This is a class for wrapping and unwrapping a crypto key.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.3.0
 */
public class KeyWrapperImpl extends ServiceAbstract implements KeyWrapper {
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$

	private final Cipher cipher;
	
	public KeyWrapperImpl() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        super();
        cipher = Cipher.getInstance(CryptoRSA.XFORM, PROVIDER);
    }
	
	
	/*
	 * Implemented methods
	 */
	@Override
    public byte[] wrap(final Key wrapperKey, final Key key) throws InvalidKeyException, IllegalBlockSizeException {
		if (null == wrapperKey) {
			throw new RuntimeExceptionIsNull("wrapperKey"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		
		cipher.init(Cipher.WRAP_MODE, wrapperKey);

		return cipher.wrap(key);
	}

	@Override
    public Key unwrap(final Key wrapperKey, final byte[] wrappedKey, final String keyAlgorithm, final int keyType) throws InvalidKeyException, NoSuchAlgorithmException {
		if (null == wrapperKey) {
			throw new RuntimeExceptionIsNull("wrapperKey"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(wrappedKey)) {
			throw new RuntimeExceptionIsNullOrEmpty("wrappedKey"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(keyAlgorithm)) {
			throw new RuntimeExceptionIsNullOrEmpty("keyAlgorithm"); //$NON-NLS-1$
		}
		if (0 >= keyType) {
			throw new IllegalArgumentException("keyType is invalid: " + keyType); //$NON-NLS-1$
		}
		
		cipher.init(Cipher.UNWRAP_MODE, wrapperKey);

		return cipher.unwrap(wrappedKey, keyAlgorithm, keyType);
	}
}
