/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

/**
 * This is a class for symmetric cryptology via AES.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public class CryptoSymm implements ICryptoSymm {
	public static final String ALGORITHM = "AES"; //$NON-NLS-1$
	public static final String XFORM     = "AES/CBC/PKCS5Padding"; //$NON-NLS-1$

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	
	/*
	 * Implemented methods
	 */
	public SecretKey generateKey(final int keysize) throws NoSuchAlgorithmException, NoSuchProviderException {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key
		final KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM, "BC"); //$NON-NLS-1$
		kg.init(keysize);
		
		return kg.generateKey();
	}
	
	public byte[] encrypt(final byte[] input, final Key key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, key, prepareIv());

		return cipher.doFinal(input);
	}

	public byte[] decrypt(final byte[] input, final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, key, prepareIv());

		return cipher.doFinal(input);
	}
	
	
	/*
	 * Private methods
	 */
	private static AlgorithmParameterSpec prepareIv() {
		final int elements = 16;
        final byte[] ivBytes = new byte[elements];
        
        for (int ii = 0; elements > ii; ii++) {
        	ivBytes[ii] = (byte) 0x5a;
        }
        return new IvParameterSpec(ivBytes);
	}
}
