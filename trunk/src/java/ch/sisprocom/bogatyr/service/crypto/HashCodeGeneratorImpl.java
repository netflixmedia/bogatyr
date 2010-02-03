/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.encoder.EncoderHex;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentExceedsVmMemory;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionArgumentMustBeGreaterThanOne;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionFileNotFound;
import ch.sisprocom.bogatyr.model.crypto.HashCode;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is an abstract implementation for hash code generation.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091115)
 * @since 0.9.0
 */
public abstract class HashCodeGeneratorImpl extends ServiceAbstract implements HashCodeGenerator {
	/*
	 * Implemented methods
	 */
    @Override
	public String getHash(final byte[] input, final HashCode hashCode) throws NoSuchAlgorithmException {
        if (null == input) {
            throw new RuntimeExceptionArgumentIsNull("input"); //$NON-NLS-1$
        }
        if (null == hashCode) {
            throw new RuntimeExceptionArgumentIsNull("hashCode"); //$NON-NLS-1$
        }

    	final MessageDigest md = MessageDigest.getInstance(hashCode.getAlgorithm());

		md.reset();
		md.update(input);
		
		return EncoderHex.encode(md.digest());
	}

	@Override
	public String getHash(final File input, final HashCode hashCode) throws NoSuchAlgorithmException, IOException {
		return getHash(input, hashCode, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

    @Override
    public String getHash(final File input, final HashCode hashCode, final int bufferSize) throws NoSuchAlgorithmException, IOException {
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
	public String getHash(final InputStream is, final HashCode hashCode, final int bufferSize) throws NoSuchAlgorithmException, IOException {
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

        final MessageDigest md = MessageDigest.getInstance(hashCode.getAlgorithm());
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
	public String getHash(final InputStream is, final HashCode hashCode) throws NoSuchAlgorithmException, IOException  {
		return getHash(is, hashCode, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}
}