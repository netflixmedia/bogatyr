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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import ch.sisprocom.bogatyr.model.crypto.CryptoAsymmetricAlgo;
import ch.sisprocom.bogatyr.model.crypto.SignatureAlgo;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

/**
 * This is a class for asymmetric cryptology via RSA.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.1.0
 */
public class CryptoAsymmetricImpl extends ServiceAbstract implements CryptoAsymmetric {
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$
	
	private final CryptoAsymmetricAlgo algorithm;
	
	private final Cipher cipher;
	private final KeyPairGenerator kpg;
	
	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}

	public CryptoAsymmetricImpl(final CryptoAsymmetricAlgo algorithm) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        super();
        this.algorithm = algorithm;
        cipher = Cipher.getInstance(algorithm.getXform(), PROVIDER);
        kpg = KeyPairGenerator.getInstance(algorithm.getAlgorithm(), PROVIDER);
    }
	
	/*
	 * Private methods
	 */
	/**
	 * Encrypt the data with a given {@link Key}.
	 * 
	 * @param input data (byte-array) to encrypt
	 * @param key for the encryption
	 * @return encrypted byte-array 
	 * @throws InvalidKeyException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @since 0.1.0
	 */
	private byte[] encryptInternal(final byte[] input, final Key key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.ENCRYPT_MODE, key);

		return cipher.doFinal(input);
	}

	/**
	 * Decrypt the data with a given {@link Key}.
	 * 
	 * @param input data (byte-array) to decrypt
	 * @param key for the decryption
	 * @return decrypted byte-array
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @since 0.1.0
	 */
	private byte[] decryptInternal(final byte[] input, final Key key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		cipher.init(Cipher.DECRYPT_MODE, key);

		return cipher.doFinal(input);
	}

	
	/*
	 * Implemented methods
	 */
	/**
	 * Generates a public and a private {@link KeyPair} with the RSA standard key size of 1024 bits.
	 * 
	 * @return generated key pair
	 * @see KeyPair
	 * @since 0.1.0
	 */
	@Override
    public KeyPair generateKeyPair() { //$JUnit$
		return generateKeyPair(algorithm.getDefaultKeysize());
	}
	
	@Override
    public KeyPair generateKeyPair(final int keySize) { //$JUnit$
		if (0 >= keySize) {
			throw new RuntimeExceptionMustBeGreater("keySize", keySize, 0); //$NON-NLS-1$
		}
		if (0 != keySize % 8) {
            throw new IllegalArgumentException("keySize is not a multiple of 8"); //$NON-NLS-1$
        }
		
		// Generate a key-pair
		kpg.initialize(keySize);

		return kpg.generateKeyPair();
	}

	@Override
	public byte[] generateSignature(final SignatureAlgo algoritm, final byte[] input, final PrivateKey key) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException {
		if (null == algoritm) {
			throw new RuntimeExceptionIsNull("algoritm"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}

		final Signature sig = Signature.getInstance(algoritm.getAlgorithm(), PROVIDER);
        sig.initSign(key);
        sig.update(input);
        
        return sig.sign();
	}

	@Override
	public boolean isValidSignature(final SignatureAlgo algoritm, final byte[] signature, final byte[] input, final PublicKey key) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, NoSuchProviderException {
		if (null == algoritm) {
			throw new RuntimeExceptionIsNull("algoritm"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(signature)) {
			throw new RuntimeExceptionIsNullOrEmpty("signature"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}

		final Signature sig = Signature.getInstance(algoritm.getAlgorithm(), PROVIDER);
        sig.initVerify(key);
        sig.update(input);

        try {
            if (sig.verify(signature)) {
                return true;
            }
        } catch (SignatureException se) {
//            return false;
        }
        return false;
	}

	/**
	 * Encrypt the data (byte-array) with a given {@link PublicKey}.
	 * Use this method only, if the key has the RSA standard key size of 1024 bits. 
	 * 
	 * @param input data to encrypt as a byte-array
	 * @param key for the encryption
     * @return encrypted byte-array
	 * @see PublicKey
	 * @since 0.1.0
	 */
    @Override
    public byte[] encrypt(final byte[] input, final PublicKey key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
    	return encrypt(input, key, algorithm.getDefaultKeysize());
    }
    
    @Override
    public byte[] encrypt(final byte[] input, final PublicKey key, final int keySize) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		if (0 >= keySize) {
			throw new RuntimeExceptionMustBeGreater("keySize", keySize, 0); //$NON-NLS-1$
		}
		if (0 != keySize % 8) {
            throw new IllegalArgumentException("keySize is not a multiple of 8"); //$NON-NLS-1$
        }
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }
   	
    	final int space = keySize/8 - 11;
		byte[] result = HelperArray.EMPTY_ARRAY_BYTE;
		final byte[] temp = new byte[space];
		
		if (space < input.length) {

            int tempCounter = 0;
            for (int ii = 0; ii < input.length; ii++) {
				
				temp[tempCounter] = input[ii];
				
				if (tempCounter == space - 1) {
					result = HelperArray.concatenate(result, encryptInternal(temp, key));
					tempCounter = 0;
				} else {
					if (ii == input.length - 1) { // last byte
						final byte[] usedBytes = new byte[tempCounter + 1];
						
						for (int xx = 0; xx <= tempCounter; xx++) {
							usedBytes[xx] = input[ii - tempCounter + xx];
						}
						result = HelperArray.concatenate(result, encryptInternal(usedBytes, key));
					}
					tempCounter++;
				}
			}
		} else {
			result = encryptInternal(input, key);
		}
		return result;
	}

	/**
	 * Decrypt the data (byte-array) with a given {@link PrivateKey}.
	 * Use this method only, if the key has the RSA standard key size of 1024 bits.
	 * 
	 * @param input encrypted data as a byte-array
	 * @param key for the decryption
     * @return decrypted byte-array
     * @since 0.1.0
	 */
	@Override
    public byte[] decrypt(final byte[] input, final PrivateKey key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		return decrypt(input, key, algorithm.getDefaultKeysize());
	}

	@Override
    public byte[] decrypt(final byte[] input, final PrivateKey key, final int keySize) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException { //$JUnit$
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
		if (null == key) {
			throw new RuntimeExceptionIsNull("key"); //$NON-NLS-1$
		}
		if (0 >= keySize) {
			throw new RuntimeExceptionMustBeGreater("keySize", keySize, 0); //$NON-NLS-1$
		}
		if (0 != keySize % 8) {
            throw new IllegalArgumentException("keySize is not a multiple of 8"); //$NON-NLS-1$
        }
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
        	throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }

		final int space = keySize/8;
		byte[] result = HelperArray.EMPTY_ARRAY_BYTE;
		final byte[] temp = new byte[space];
		
		if (space < input.length) {

            int tempCounter = 0;
            for (final byte data : input) {
				temp[tempCounter] = data;
				
				if (tempCounter == space - 1) {
					result = HelperArray.concatenate(result, decryptInternal(temp, key));
					tempCounter = 0;
				} else {
					tempCounter++;
				}
			}
		} else {
			result = decryptInternal(input, key);
		}
		return result;
	}
}
