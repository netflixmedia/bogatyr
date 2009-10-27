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
import ch.sisprocom.bogatyr.helper.encoder.EncoderHex;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * This is an abstract implementation for hash code generation.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091027)
 * @since 0.9.0
 */
public abstract class HashCodeGeneratorImpl extends ServiceAbstract implements HashCodeGenerator {
	private static final int DEFAULT_BUFFER_SIZE = HelperNumber.VALUE_1024;

	private final HashCode hashCode;
	
	
	public HashCodeGeneratorImpl(final HashCode hashCode) {
		super();
		this.hashCode = hashCode;
	}

	
	/*
	 * Implemented methods
	 */
    @Override
	public String getHash(final byte[] input) throws NoSuchAlgorithmException {
        if (null == input) {
            throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
        }

    	final MessageDigest md = MessageDigest.getInstance(hashCode.getAlgorithm());

		md.reset();
		md.update(input);
		
		return EncoderHex.encode(md.digest());
	}

	@Override
	public String getHash(final File input) throws NoSuchAlgorithmException, IOException {
		return getHash(input, DEFAULT_BUFFER_SIZE);
	}

    @Override
    public String getHash(final File input, final int bufferSize) throws NoSuchAlgorithmException, IOException {
        if (null == input) {
            throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
        }

        return getHash(new BufferedInputStream(new FileInputStream(input)), bufferSize);
    }

	@Override
	public String getHash(final InputStream is, final int bufferSize) throws NoSuchAlgorithmException, IOException {
        if (null == is) {
            throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
        }
        if (bufferSize < 1) {
            throw new IllegalArgumentException("bufferSize (" + bufferSize + ") must be greater than 1"); //$NON-NLS-1$ //$NON-NLS-2$
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
	public String getHash(final InputStream is) throws NoSuchAlgorithmException, IOException  {
		return getHash(is, DEFAULT_BUFFER_SIZE);
	}
}