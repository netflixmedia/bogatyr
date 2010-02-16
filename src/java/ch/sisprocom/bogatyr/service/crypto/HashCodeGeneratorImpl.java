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

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.misc.Constants;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import ch.sisprocom.bogatyr.model.crypto.HashCodeAlgo;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is an implementation for hash code generation.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.9.0
 */
public class HashCodeGeneratorImpl extends ServiceAbstract implements HashCodeGenerator {
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$

	private final MessageDigest md;
	
	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}
	
	public HashCodeGeneratorImpl(final HashCodeAlgo algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        super();
        md = MessageDigest.getInstance(algorithm.getAlgorithm(), PROVIDER);
    }
	
	/*
	 * Implemented methods
	 */
    @Override
	public byte[] getHash(final byte[] input) {
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}

		md.reset();
		md.update(input);
		
		return md.digest();
	}

	@Override
	public byte[] getHash(final File input) throws IOException {
		return getHash(input, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

    @Override
    public byte[] getHash(final File input, final int bufferSize) throws IOException {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }

        BufferedInputStream bis = null;

        try {
        	bis = new BufferedInputStream(new FileInputStream(input));
        	return getHash(bis, bufferSize);
        } finally {
        	if (null != bis) {
        		bis.close();
        	}
        }
    }

	@Override
	public byte[] getHash(final InputStream is, final int bufferSize) throws IOException {
        if (null == is) {
            throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
        }
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
        }

		md.reset();
		
		final byte[] buffer = new byte[bufferSize];
		
		int offset = is.read(buffer);
		while (0 < offset) {
			md.update(buffer, 0, offset);
			offset = is.read(buffer);
		}
		
		return md.digest();
	}

	@Override
	public byte[] getHash(final InputStream is) throws IOException  {
		return getHash(is, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

	@Override
	public byte[] getFastHash(final byte[] input, final int parts, final int partSize) {
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
        if (0 > parts) {
            throw new RuntimeExceptionMustBeGreater("parts", parts, 0); //$NON-NLS-1$
        }
        if (0 > partSize) {
            throw new RuntimeExceptionMustBeGreater("partSize", partSize, 0); //$NON-NLS-1$
        }

		
		if (input.length < parts * partSize) {
			return getHash(input); 
		}
		
		byte[] result = Integer.toString(input.length).getBytes();
		final int offset = input.length / parts - partSize;
		int position = 0;
		
		for (int ii = 0; ii < parts; ii++) {
			
			result = HelperArray.concatenate(result, Arrays.copyOfRange(input, position, position + partSize));

			position += offset;
		}
		
		return getHash(result);
	}
	
	@Override
	public byte[] getFastHash(final byte[] input) {
		return getFastHash(input, 16, 2048);
	}

	@Override
	public byte[] getFastHash(final File input, final int parts, final int partSize) throws IOException {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }
        if (0 > parts) {
            throw new RuntimeExceptionMustBeGreater("parts", parts, 0); //$NON-NLS-1$
        }
        if (0 > partSize) {
            throw new RuntimeExceptionMustBeGreater("partSize", partSize, 0); //$NON-NLS-1$
        }

		
		if (input.length() < parts * partSize) {
			return getHash(input); 
		}
		
		final byte[] buffer = new byte[partSize];
		byte[] result = Long.toString(input.length()).getBytes();
		final int offset = (int) (input.length() / parts - partSize);
		
		RandomAccessFile raf = null; 

		try {
			raf = new RandomAccessFile(input, "r");  //$NON-NLS-1$

			for (int ii = 0; ii < parts; ii++) {
				raf.read(buffer);
				
				result = HelperArray.concatenate(result, buffer);
				
				raf.seek(offset);
			}
		} finally {
			if (null != raf) {
				raf.close();
			}
		}
		return getHash(result);
	}
	
	@Override
	public byte[] getFastHash(final File input) throws IOException {
		return getFastHash(input, 16, 2048);
	}
}