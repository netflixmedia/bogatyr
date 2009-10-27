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
package ch.sisprocom.bogatyr.service.crypto;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.service.ServiceAbstract;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * @version 0.9.0 (20091027)
 * @since 0.1.0
 */
public class CryptoAES  extends ServiceAbstract implements CryptoSymmetric {
	public static final String ALGORITHM    = "AES"; //$NON-NLS-1$
	public static final String XFORM        = "AES/CBC/PKCS5Padding"; //$NON-NLS-1$
	public static final int DEFAULT_KEY_SIZE = 128;

    private static final int DEFAULT_BUFFER_SIZE = HelperNumber.VALUE_1024;
	
	/*
	 * Private methods
	 */
	private static AlgorithmParameterSpec prepareIv() {
        final byte[] ivBytes = new byte[HelperNumber.VALUE_16];
        
        for (int ii = 0; ivBytes.length > ii; ii++) {
        	ivBytes[ii] = (byte) 0x5a;
        }
        return new IvParameterSpec(ivBytes);
	}
	
	private static Cipher getCipherEncrypt(final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.ENCRYPT_MODE, key, prepareIv());
		return cipher;
	}

	private static Cipher getCipherDecrypt(final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		final Cipher cipher = Cipher.getInstance(XFORM, "BC"); //$NON-NLS-1$
		cipher.init(Cipher.DECRYPT_MODE, key, prepareIv());
		return cipher;
	}

	
	/*
	 * Implemented methods
	 */
	/**
	 * Generates a {@link SecretKey} with the AES standard key size of 128 bits.
	 * 
     * @return generated secret key
	 * @see SecretKey
	 * @since 0.1.0
	 */
	@Override
    public SecretKey generateKey() throws NoSuchAlgorithmException, NoSuchProviderException { //$JUnit$
		return generateKey(DEFAULT_KEY_SIZE);
	}
	
	@Override
    public SecretKey generateKey(final int keySize) throws NoSuchAlgorithmException, NoSuchProviderException { //$JUnit$
    	if (0 >= keySize) {
			throw new IllegalArgumentException("keySize is invalid: " + keySize); //$NON-NLS-1$
		}

    	Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers

		// Generate a key
		final KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM, "BC"); //$NON-NLS-1$
		kg.init(keySize);
		
		return kg.generateKey();
	}
	
	@Override
    public byte[] encrypt(final byte[] input, final Key key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException { //$JUnit$
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}

		return getCipherEncrypt(key).doFinal(input);
	}

	@Override
    public byte[] decrypt(final byte[] input, final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
		}

		return getCipherDecrypt(key).doFinal(input);
	}

    @Override
    public void encrypt(final InputStream is, OutputStream os, final Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
    	encrypt(is, os, key, DEFAULT_BUFFER_SIZE);
    }

    @Override
    public void encrypt(final InputStream is, OutputStream os, final Key key, final int bufferSize) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        if (null == is) {
            throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
        }
        if (null == os) {
            throw new IllegalArgumentException("os is null!"); //$NON-NLS-1$
        }
        if (null == key) {
            throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
        }
        if (bufferSize < 1) {
            throw new IllegalArgumentException("bufferSize (" + bufferSize + ") must be greater than 1"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        final byte[] buffer = new byte[bufferSize];

        os = new CipherOutputStream(os, getCipherEncrypt(key));

        int offset  ;
        while (0 <= (offset = is.read(buffer))) {
        	os.write(buffer, 0, offset);
        }
        os.close();
    }

    @Override
    public void decrypt(final InputStream is, final OutputStream os, final Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
    	decrypt(is, os, key, DEFAULT_BUFFER_SIZE);
    }

    @Override
    public void decrypt(final InputStream is, final OutputStream os, final Key key, final int bufferSize) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        if (null == is) {
            throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
        }
        if (null == os) {
            throw new IllegalArgumentException("os is null!"); //$NON-NLS-1$
        }
        if (null == key) {
            throw new IllegalArgumentException("key is null!"); //$NON-NLS-1$
        }
        if (bufferSize < 1) {
            throw new IllegalArgumentException("bufferSize (" + bufferSize + ") must be greater than 1"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        final byte[] buffer = new byte[bufferSize];

        try {
        	CipherInputStream cis = new CipherInputStream(is, getCipherDecrypt(key));
	
	        int offset  ;
	        while (0 <= (offset = cis.read(buffer))) {
	        	os.write(buffer, 0, offset);
	        }
        } finally {
        	os.close();
        }
    }

	@Override
    public void encrypt(final File input, final File output, final Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
		encrypt(input, output, key, DEFAULT_BUFFER_SIZE);
	}

	@Override
    public void encrypt(final File input, final File output, final Key key, final int bufferSize) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
        if (null == input) {
            throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
        }
        if (null == output) {
            throw new IllegalArgumentException("output is null!"); //$NON-NLS-1$
        }

		encrypt(new BufferedInputStream(new FileInputStream(input)), new BufferedOutputStream(new FileOutputStream(output)), key, bufferSize);
	}

	@Override
    public void decrypt(final File input, final File output, final Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
        decrypt(input, output, key, DEFAULT_BUFFER_SIZE);
	}
	
	@Override
    public void decrypt(final File input, final File output, final Key key, final int bufferSize) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
        if (null == input) {
            throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
        }
        if (null == output) {
            throw new IllegalArgumentException("output is null!"); //$NON-NLS-1$
        }

		decrypt(new BufferedInputStream(new FileInputStream(input)), new BufferedOutputStream(new FileOutputStream(output)), key, bufferSize);
	}
}
