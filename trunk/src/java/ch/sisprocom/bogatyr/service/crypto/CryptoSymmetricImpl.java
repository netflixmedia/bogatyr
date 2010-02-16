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
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.misc.Constants;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsEquals;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeSmaller;
import ch.sisprocom.bogatyr.model.crypto.CryptoSymmetricAlgo;
import ch.sisprocom.bogatyr.model.crypto.HashCodeAlgo;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

/**
 * This is a class for symmetric cryptology via AES.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.1.0
 */
public class CryptoSymmetricImpl extends ServiceAbstract implements CryptoSymmetric {
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$

	private final CryptoSymmetricAlgo algorithm;
	
	private final Cipher cipher;
	private final KeyGenerator kg;
	private final HashCodeGenerator hcg;
	
	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}

	public CryptoSymmetricImpl(final CryptoSymmetricAlgo algorithm) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        super();
        this.algorithm = algorithm;
        cipher = Cipher.getInstance(algorithm.getXform(), PROVIDER);
        kg = KeyGenerator.getInstance(algorithm.getAlgorithm(), PROVIDER);
        hcg = new HashCodeGeneratorImpl(HashCodeAlgo.SHA512);
    }
	
	/*
	 * Private methods
	 */
	private AlgorithmParameterSpec prepareIv() {
        final byte[] ivBytes = new byte[algorithm.getIvSize()];
        
        for (int ii = 0; algorithm.getIvSize() > ii; ii++) {
        	ivBytes[ii] = (byte) 0x5a;
        }
        return new IvParameterSpec(ivBytes);
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
    public SecretKey generateKey() { //$JUnit$
		return generateKey(algorithm.getDefaultKeysize());
	}
	
	@Override
    public SecretKey generateKey(final int keySize) { //$JUnit$
		if (0 >= keySize) {
			throw new RuntimeExceptionMustBeGreater("keySize", keySize, 0); //$NON-NLS-1$
		}
		if (0 != keySize % 8) {
            throw new IllegalArgumentException("keySize is not a multiple of 8"); //$NON-NLS-1$
        }

		// Generate a key
		kg.init(keySize);
		
		return kg.generateKey();
	}
	
	@Override
	public SecretKey generateKey(final byte[] password) {
		return generateKey(password, algorithm.getDefaultKeysize());
	}

	@Override
	public SecretKey generateKey(final byte[] password, final int keySize) {
		if (0 >= keySize) {
			throw new RuntimeExceptionMustBeGreater("keySize", keySize, 0); //$NON-NLS-1$
		}
		if (512 < keySize) {
			throw new RuntimeExceptionMustBeSmaller("keySize", keySize, 512); //$NON-NLS-1$
		}
		if (0 != keySize % 8) {
            throw new IllegalArgumentException("keySize is not a multiple of 8"); //$NON-NLS-1$
        }

		return new SecretKeySpec(Arrays.copyOfRange(hcg.getHash(password), 0, keySize / 8), algorithm.getAlgorithm());
	}

	@Override
    public byte[] encrypt(final byte[] input, final Key key) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException { //$JUnit$
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }
        
        cipher.init(Cipher.ENCRYPT_MODE, key, prepareIv());
		return cipher.doFinal(input);
	}

	@Override
    public byte[] decrypt(final byte[] input, final Key key) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }

        cipher.init(Cipher.DECRYPT_MODE, key, prepareIv());
		return cipher.doFinal(input);
	}

    @Override
    public void encrypt(final InputStream is, final OutputStream os, final Key key) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
    	encrypt(is, os, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
    }

    @Override
    public void encrypt(final InputStream is, OutputStream os, final Key key, final int bufferSize) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
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

        cipher.init(Cipher.ENCRYPT_MODE, key, prepareIv());
        os = new CipherOutputStream(os, cipher);

        int offset;
        while (0 <= (offset = is.read(buffer))) {
        	os.write(buffer, 0, offset);
        }
        os.close();
    }

    @Override
    public void decrypt(final InputStream is, final OutputStream os, final Key key) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
    	decrypt(is, os, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
    }

    @Override
    public void decrypt(final InputStream is, final OutputStream os, final Key key, final int bufferSize) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
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
        	cipher.init(Cipher.DECRYPT_MODE, key, prepareIv());
        	cis = new CipherInputStream(is, cipher);
	
	        int offset;
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
    public void encrypt(final File input, final File output, final Key key) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
		encrypt(input, output, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

	@Override
    public void encrypt(final File input, final File output, final Key key, final int bufferSize) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }
		if (null == output) {
            throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
        }
		if (input.equals(output)) {
			throw new RuntimeExceptionIsEquals("input", "output"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
        try {
        	bis = new BufferedInputStream(new FileInputStream(input));
        	bos = new BufferedOutputStream(new FileOutputStream(output));
        	encrypt(bis, bos, key, bufferSize);
        } finally {
        	if (null != bos) {
        		bos.close();
        	}
        	if (null != bis) {
        		bis.close();
        	}
        }
	}

	@Override
    public void decrypt(final File input, final File output, final Key key) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        decrypt(input, output, key, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}
	
	@Override
    public void decrypt(final File input, final File output, final Key key, final int bufferSize) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }
        if (null == output) {
            throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
        }
		if (input.equals(output)) {
			throw new RuntimeExceptionIsEquals("input", "output"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
        try {
        	bis = new BufferedInputStream(new FileInputStream(input));
        	bos = new BufferedOutputStream(new FileOutputStream(output));
        	decrypt(bis, bos, key, bufferSize);
        } finally {
        	if (null != bos) {
        		bos.close();
        	}
        	if (null != bis) {
        		bis.close();
        	}
        }
	}
}
