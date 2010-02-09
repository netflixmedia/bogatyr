/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionFileNotFound;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsEquals;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

/**
 * This is a class for symmetric cryptology via AES.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100209)
 * @since 0.1.0
 */
public class CryptoAES  extends ServiceAbstract implements CryptoSymmetric {
	public static final String ALGORITHM    = "AES"; //$NON-NLS-1$
	public static final String XFORM        = "AES/CBC/PKCS5Padding"; //$NON-NLS-1$
	public static final int DEFAULT_KEY_SIZE = 128;
    
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$

	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}

	
	/*
	 * Private methods
	 */
	private static AlgorithmParameterSpec prepareIv() {
        final byte[] ivBytes = new byte[HelperNumber.INT_16];
        
        for (int ii = 0; ivBytes.length > ii; ii++) {
        	ivBytes[ii] = (byte) 0x5a;
        }
        return new IvParameterSpec(ivBytes);
	}
	
	private static Cipher getCipherEncrypt(final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		final Cipher cipher = Cipher.getInstance(XFORM, PROVIDER);
		cipher.init(Cipher.ENCRYPT_MODE, key, prepareIv());
		return cipher;
	}

	private static Cipher getCipherDecrypt(final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
		final Cipher cipher = Cipher.getInstance(XFORM, PROVIDER);
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
			throw new RuntimeExceptionMustBeGreater("keySize", keySize, 0); //$NON-NLS-1$
		}

		// Generate a key
		final KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM, PROVIDER);
		kg.init(keySize);
		
		return kg.generateKey();
	}
	
	@Override
    public byte[] encrypt(final byte[] input, final Key key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException { //$JUnit$
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }

		return getCipherEncrypt(key).doFinal(input);
	}

	@Override
    public byte[] decrypt(final byte[] input, final Key key) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }

		return getCipherDecrypt(key).doFinal(input);
	}

    @Override
    public void encrypt(final InputStream is, final OutputStream os, final Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
    	encrypt(is, os, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
    }

    @Override
    public void encrypt(final InputStream is, OutputStream os, final Key key, final int bufferSize) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        if (null == is) {
            throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
        }
        if (null == os) {
            throw new RuntimeExceptionIsNull("os"); //$NON-NLS-1$
        }
        if (null == key) {
            throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
        }
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
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
    	decrypt(is, os, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
    }

    @Override
    public void decrypt(final InputStream is, final OutputStream os, final Key key, final int bufferSize) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        if (null == is) {
            throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
        }
        if (null == os) {
            throw new RuntimeExceptionIsNull("os"); //$NON-NLS-1$
        }
        if (null == key) {
            throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
        }
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
        }

        final byte[] buffer = new byte[bufferSize];
        CipherInputStream cis = null;

        try {
        	cis = new CipherInputStream(is, getCipherDecrypt(key));
	
	        int offset  ;
	        while (0 <= (offset = cis.read(buffer))) {
	        	os.write(buffer, 0, offset);
	        }
        } finally {
        	os.close();
            if (null != cis) {
                cis.close();
            }
        }
    }

	@Override
    public void encrypt(final File input, final File output, final Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
		encrypt(input, output, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

	@Override
    public void encrypt(final File input, final File output, final Key key, final int bufferSize) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }
//		if (!input.exists()) {
//			throw new RuntimeExceptionFileNotFound(input);
//		}
		if (null == output) {
            throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
        }
		if (input.equals(output)) {
			throw new RuntimeExceptionIsEquals("input", "output"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		encrypt(new BufferedInputStream(new FileInputStream(input)), new BufferedOutputStream(new FileOutputStream(output)), key, bufferSize);
	}

	@Override
    public void decrypt(final File input, final File output, final Key key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
        decrypt(input, output, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}
	
	@Override
    public void decrypt(final File input, final File output, final Key key, final int bufferSize) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }
//		if (!input.exists()) {
//			throw new RuntimeExceptionFileNotFound(input);
//		}
        if (null == output) {
            throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
        }
		if (input.equals(output)) {
			throw new RuntimeExceptionIsEquals("input", "output"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		decrypt(new BufferedInputStream(new FileInputStream(input)), new BufferedOutputStream(new FileOutputStream(output)), key, bufferSize);
	}
}
