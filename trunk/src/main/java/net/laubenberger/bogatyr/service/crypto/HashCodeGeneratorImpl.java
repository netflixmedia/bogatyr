/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperEnvironment;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.model.crypto.HashCodeAlgo;
import net.laubenberger.bogatyr.service.ServiceAbstract;


/**
 * This is an implementation for hash code generation.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
 * @since 0.9.0
 */
public class HashCodeGeneratorImpl extends ServiceAbstract implements HashCodeGenerator {
	private static final Logger log = LoggerFactory.getLogger(HashCodeGeneratorImpl.class);

	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$
	private static final int DEFAULT_PARTS = 16;
	private static final int DEFAULT_PARTSIZE = 2048;

	private final MessageDigest md;

	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}

	public HashCodeGeneratorImpl(final HashCodeAlgo algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(algorithm));

		md = MessageDigest.getInstance(algorithm.getAlgorithm(), PROVIDER);
	}

	/*
	 * Implemented methods
	 */

	@Override
	public byte[] getHash(final byte[] input) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(input));
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}

		md.reset();
		md.update(input);

		final byte[] result = md.digest();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public byte[] getHash(final File input) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(input));

		final byte[] result = getHash(input, Constants.DEFAULT_FILE_BUFFER_SIZE);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public byte[] getHash(final File input, final int bufferSize) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(input, bufferSize));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}

		BufferedInputStream bis = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(input));

			final byte[] result = getHash(bis, bufferSize);

			if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
			return result;
		} finally {
			if (null != bis) {
				bis.close();
			}
		}
	}

	@Override
	public byte[] getHash(final InputStream is, final int bufferSize) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(is, bufferSize));
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

		final byte[] result = md.digest();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public byte[] getHash(final InputStream is) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(is));

		final byte[] result = getHash(is, Constants.DEFAULT_FILE_BUFFER_SIZE);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public byte[] getFastHash(final byte[] input, final int parts, final int partSize) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(input, parts, partSize));
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

		byte[] temp = Integer.toString(input.length).getBytes();
		final int offset = input.length / parts - partSize;
		int position = 0;

		for (int ii = 0; ii < parts; ii++) {

			temp = HelperArray.concatenate(temp, Arrays.copyOfRange(input, position, position + partSize));

			position += offset;
		}
		final byte[] result = getHash(temp);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public byte[] getFastHash(final byte[] input) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(input));

		final byte[] result = getFastHash(input, DEFAULT_PARTS, DEFAULT_PARTSIZE);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public byte[] getFastHash(final File input, final int parts, final int partSize) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(input, parts, partSize));

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
		byte[] temp = Long.toString(input.length()).getBytes();
		final int offset = (int) (input.length() / parts - partSize);

		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(input, "r");  //$NON-NLS-1$

			for (int ii = 0; ii < parts; ii++) {
				raf.read(buffer);

				temp = HelperArray.concatenate(temp, buffer);

				raf.seek(offset);
			}
		} finally {
			if (null != raf) {
				raf.close();
			}
		}
		final byte[] result = getHash(temp);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public byte[] getFastHash(final File input) throws IOException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(input));

		final byte[] result = getFastHash(input, DEFAULT_PARTS, DEFAULT_PARTSIZE);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}