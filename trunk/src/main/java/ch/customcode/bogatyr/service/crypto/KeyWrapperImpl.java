/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.service.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperArray;
import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.customcode.bogatyr.model.crypto.CryptoAlgo;
import ch.customcode.bogatyr.service.ServiceAbstract;

/**
 * This is a class for wrapping and unwrapping a crypto key.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.3.0
 */
public class KeyWrapperImpl extends ServiceAbstract implements KeyWrapper {
	private static final Logger log = LoggerFactory.getLogger(KeyWrapperImpl.class);
	
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$

	private final Cipher cipher;
	
	public KeyWrapperImpl(final CryptoAlgo wrapperAlgorithm) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        super();
        log.trace(HelperLog.constructor(wrapperAlgorithm));
        
        cipher = Cipher.getInstance(wrapperAlgorithm.getXform(), PROVIDER);
    }
	
	
	/*
	 * Implemented methods
	 */
	@Override
    public byte[] wrap(final Key wrapperKey, final Key key) throws InvalidKeyException, IllegalBlockSizeException {
		log.debug(HelperLog.methodStart(wrapperKey, key));
		if (null == wrapperKey) {
			throw new RuntimeExceptionIsNull("wrapperKey"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		
		cipher.init(Cipher.WRAP_MODE, wrapperKey);

		final byte[] result = cipher.wrap(key);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public Key unwrap(final Key wrapperKey, final byte[] wrappedKey, final String keyAlgorithm, final int keyType) throws InvalidKeyException, NoSuchAlgorithmException {
		log.debug(HelperLog.methodStart(wrapperKey, wrappedKey, keyAlgorithm, keyType));
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
		
		final Key result = cipher.unwrap(wrappedKey, keyAlgorithm, keyType);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
}
