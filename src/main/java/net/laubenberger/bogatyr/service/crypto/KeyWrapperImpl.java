/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.service.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperCrypto;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.service.ServiceAbstract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a class for wrapping and unwrapping a crypto key.
 *
 * @author Stefan Laubenberger
 * @version 0.9.6 (20110601)
 * @since 0.3.0
 */
public class KeyWrapperImpl extends ServiceAbstract implements KeyWrapper {
	private static final Logger log = LoggerFactory.getLogger(KeyWrapperImpl.class);

	
	/*
	 * Implemented methods
	 */

	@Override
	public byte[] wrap(final Key wrapperKey, final Key key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(wrapperKey, key));
		if (null == wrapperKey) {
			throw new RuntimeExceptionIsNull("wrapperKey"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
	
		final Cipher cipher  = Cipher.getInstance(wrapperKey.getAlgorithm(), HelperCrypto.DEFAULT_PROVIDER);
		cipher.init(Cipher.WRAP_MODE, wrapperKey);
		
		final byte[] result = cipher.wrap(key);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public Key unwrap(final Key wrapperKey, final byte[] wrappedKey, final String keyAlgorithm, final int keyType) throws InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(wrapperKey, wrappedKey, keyAlgorithm, keyType));
		if (null == wrapperKey) {
			throw new RuntimeExceptionIsNull("wrapperKey"); //$NON-NLS-1$
		}
		if (null == wrappedKey) {
			throw new RuntimeExceptionIsNull("wrappedKey"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(wrappedKey)) {
			throw new RuntimeExceptionIsEmpty("wrappedKey"); //$NON-NLS-1$
		}
		if (null == keyAlgorithm) {
			throw new RuntimeExceptionIsNull("keyAlgorithm"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(keyAlgorithm)) {
			throw new RuntimeExceptionIsEmpty("keyAlgorithm"); //$NON-NLS-1$
		}
		if (0 >= keyType) {
			throw new IllegalArgumentException("keyType is invalid: " + keyType); //$NON-NLS-1$
		}

		final Cipher cipher  = Cipher.getInstance(wrapperKey.getAlgorithm(), HelperCrypto.DEFAULT_PROVIDER);

		cipher.init(Cipher.UNWRAP_MODE, wrapperKey);

		final Key result = cipher.unwrap(wrappedKey, keyAlgorithm, keyType);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}
