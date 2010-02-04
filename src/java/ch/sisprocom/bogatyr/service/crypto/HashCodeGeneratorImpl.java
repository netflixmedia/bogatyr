/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.encoder.EncoderHex;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentExceedsVmMemory;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentMustBeGreaterThanOne;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentMustBePositive;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionFileNotFound;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is an implementation for hash code generation.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100203)
 * @since 0.9.0
 */
public class HashCodeGeneratorImpl extends ServiceAbstract implements HashCodeGenerator {
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$

	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}
	
	/*
	 * Implemented methods
	 */
    @Override
	public String getHash(final byte[] input, final HashCode hashCode) throws NoSuchAlgorithmException, NoSuchProviderException {
        if (null == input) {
            throw new RuntimeExceptionArgumentIsNull("input"); //$NON-NLS-1$
        }
        if (null == hashCode) {
            throw new RuntimeExceptionArgumentIsNull("hashCode"); //$NON-NLS-1$
        }

    	final MessageDigest md = MessageDigest.getInstance(hashCode.getAlgorithm(), PROVIDER);

		md.reset();
		md.update(input);
		
		return EncoderHex.encode(md.digest());
	}

	@Override
	public String getHash(final File input, final HashCode hashCode) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
		return getHash(input, hashCode, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

    @Override
    public String getHash(final File input, final HashCode hashCode, final int bufferSize) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        if (null == input) {
            throw new RuntimeExceptionArgumentIsNull("input"); //$NON-NLS-1$
        }
		if (!input.exists()) {
			throw new RuntimeExceptionFileNotFound(input);
		}
        if (null == hashCode) {
            throw new RuntimeExceptionArgumentIsNull("hashCode"); //$NON-NLS-1$
        }

        return getHash(new BufferedInputStream(new FileInputStream(input)), hashCode, bufferSize);
    }

	@Override
	public String getHash(final InputStream is, final HashCode hashCode, final int bufferSize) throws NoSuchAlgorithmException, IOException, NoSuchProviderException {
        if (null == is) {
            throw new RuntimeExceptionArgumentIsNull("is"); //$NON-NLS-1$
        }
        if (null == hashCode) {
            throw new RuntimeExceptionArgumentIsNull("hashCode"); //$NON-NLS-1$
        }
        if (1 > bufferSize) {
            throw new RuntimeExceptionArgumentMustBeGreaterThanOne("bufferSize", bufferSize); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionArgumentExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
        }

        final MessageDigest md = MessageDigest.getInstance(hashCode.getAlgorithm(), PROVIDER);
		md.reset();
		
		final byte[] buffer = new byte[bufferSize];
		
		int offset = is.read(buffer);
		while (0 < offset) {
			md.update(buffer, 0, offset);
			offset = is.read(buffer);
		}
		
		return EncoderHex.encode(md.digest());
	}

	@Override
	public String getHash(final InputStream is, final HashCode hashCode) throws NoSuchAlgorithmException, IOException, NoSuchProviderException  {
		return getHash(is, hashCode, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

	@Override
	public String getFastHash(final byte[] input, final HashCode hashCode, final int parts, final int partSize) throws Exception {
        if (null == input) {
            throw new RuntimeExceptionArgumentIsNull("input"); //$NON-NLS-1$
        }
        if (null == hashCode) {
            throw new RuntimeExceptionArgumentIsNull("hashCode"); //$NON-NLS-1$
        }
        if (0 > parts) {
            throw new RuntimeExceptionArgumentMustBePositive("parts", parts); //$NON-NLS-1$
        }
        if (0 > partSize) {
            throw new RuntimeExceptionArgumentMustBePositive("partSize", partSize); //$NON-NLS-1$
        }

		
		if (input.length < parts * partSize) {
			return getHash(input, hashCode); 
		}
		
		byte[] result = null;
		final int offset = (int) (input.length / parts - partSize);
		int position = 0;
		
		for (int ii = 0; ii < parts; ii++) {
			
			result = HelperArray.concatenate(result, Arrays.copyOfRange(input, position, position + partSize));

			position += offset;
		}
		
		return getHash(result, hashCode);
	}
	
	@Override
	public String getFastHash(final byte[] input, final HashCode hashCode) throws Exception {
		return getFastHash(input, hashCode, 8, HelperNumber.INT_1024);
	}

	@Override
	public String getFastHash(final File input, final HashCode hashCode, final int parts, final int partSize) throws Exception {
        if (null == input) {
            throw new RuntimeExceptionArgumentIsNull("input"); //$NON-NLS-1$
        }
        if (!input.exists()) {
        	throw new RuntimeExceptionFileNotFound(input);
        }
        if (null == hashCode) {
            throw new RuntimeExceptionArgumentIsNull("hashCode"); //$NON-NLS-1$
        }
        if (0 > parts) {
            throw new RuntimeExceptionArgumentMustBePositive("parts", parts); //$NON-NLS-1$
        }
        if (0 > partSize) {
            throw new RuntimeExceptionArgumentMustBePositive("partSize", partSize); //$NON-NLS-1$
        }

		
		if (input.length() < parts * partSize) {
			return getHash(input, hashCode); 
		}
		
		final byte[] buffer = new byte[partSize];
		byte[] result = null;
		final int offset = (int) (input.length() / parts - partSize);
		
		final RandomAccessFile raf = new RandomAccessFile(input, "r");  //$NON-NLS-1$
		
		for (int ii = 0; ii < parts; ii++) {
			raf.read(buffer);
			
			result = HelperArray.concatenate(result, buffer);
			
			raf.seek(offset);
		}
		
		return getHash(result, hashCode);
	}
	
	@Override
	public String getFastHash(final File input, final HashCode hashCode) throws Exception {
		return getFastHash(input, hashCode, 8, HelperNumber.INT_1024);
	}
}