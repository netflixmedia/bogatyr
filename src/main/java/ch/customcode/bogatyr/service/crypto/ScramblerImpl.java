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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.service.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.misc.Constants;
import ch.customcode.bogatyr.helper.HelperArray;
import ch.customcode.bogatyr.helper.HelperEnvironment;
import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsEquals;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * This is a class for obfuscating data with CFB.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100413)
 * @since 0.3.0
 */
public class ScramblerImpl extends ServiceAbstract implements Scrambler {
	private static final Logger log = LoggerFactory.getLogger(ScramblerImpl.class);
	
	
	public ScramblerImpl() {
        super();
        log.trace(HelperLog.constructor());
    }
	
	
	/*
     * Private methods
     */
	/**
	 * Obfuscate the data.
	 * 
	 * @param input data (byte-array) to obfuscate
	 * @param pattern for obfuscating (region: -128 - 127)
     * @return obfuscated data
     * @since 0.3.0
	 */
	private static byte[] obfuscate(final byte[] input, final byte pattern) {
		log.trace(HelperLog.methodStart(input, pattern));
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }

		final byte[] result = new byte[input.length];
		
		result[0] = (byte)(input[0] ^ (int) pattern);
		for (int ii = 1; ii < input.length; ii++) {
			result[ii] = (byte)(input[ii] ^ (int) result[ii-1]);
		}
		log.trace(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Unobfuscate the data.
	 * 
	 * @param input data (byte-array) to unobfuscate
	 * @param pattern for unobfuscating (region: -128 - 127)
     * @return unobfuscated data
     * @since 0.3.0
	 */
	private static byte[] unobfuscate(final byte[] input, final byte pattern) {
		log.trace(HelperLog.methodStart(input, pattern));
		if (!HelperArray.isValid(input)) {
			throw new RuntimeExceptionIsNullOrEmpty("input"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("input", input.length * 2); //$NON-NLS-1$
        }

		final byte[] result = new byte[input.length];
		
		result[0] = (byte)(input[0] ^ (int) pattern);
		for (int ii = 1; ii < input.length; ii++) {
			result[ii] = (byte)(input[ii] ^ (int) input[ii-1]);
		}
		log.trace(HelperLog.methodExit(result));
		return result;
	}

	private static void obfuscate(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
		log.trace(HelperLog.methodStart(input, output, pattern, bufferSize));
		if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }
		if (null == output) {
            throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
        }
		if (HelperObject.isEquals(input, output)) {
			throw new RuntimeExceptionIsEquals("input", "output"); //$NON-NLS-1$ //$NON-NLS-2$
		}
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
        }
		
        final byte[] buffer = new byte[bufferSize];

        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(input);
            os = new FileOutputStream(output);
            int offset;
            byte lastByte = pattern;
            while (0 < (offset = is.read(buffer))) {

                final byte[] result = obfuscate(buffer, lastByte);
            	
            	os.write(result, 0, offset);

            	lastByte = result[result.length - 1];
            }
            os.flush();
        } finally {
            if (null != is) {
                is.close();
            }
            if (null != os) {
                os.close();
            }
        }
        log.trace(HelperLog.methodExit());
	}
	

	private static void unobfuscate(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
		log.trace(HelperLog.methodStart(input, output, pattern, bufferSize));
		if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }
		if (null == output) {
            throw new RuntimeExceptionIsNull("output"); //$NON-NLS-1$
        }
		if (HelperObject.isEquals(input, output)) {
			throw new RuntimeExceptionIsEquals("input", "output"); //$NON-NLS-1$ //$NON-NLS-2$
		}
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
        }

		final byte[] buffer = new byte[bufferSize];
		
		InputStream is = null;
		OutputStream os =  null;

		try {
            is = new FileInputStream(input);
            os = new FileOutputStream(output);
            int offset;
            byte lastByte = pattern;
            while (0 < (offset = is.read(buffer))) {
                
            	os.write(unobfuscate(buffer, lastByte), 0, offset);
            	
            	lastByte = buffer[buffer.length - 1];
            }
            os.flush();
        } finally {
            if (null != is) {
                is.close();
            }
            if (null != os) {
                os.close();
            }
        }
        log.trace(HelperLog.methodExit());
	}

	
	/*
	 * Implemented methods
	 */
	@Override
    public byte[] scramble(final byte[] input, final byte pattern) { //$JUnit$
		log.debug(HelperLog.methodStart(input, pattern));
		
		final byte[] result = obfuscate(input, pattern);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
    public byte[] unscramble(final byte[] input, final byte pattern) { //$JUnit$
		log.debug(HelperLog.methodStart(input, pattern));
		
		final byte[] result = unobfuscate(input, pattern);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public void scramble(final File input, final File output, final byte pattern) throws IOException {
		log.debug(HelperLog.methodStart(input, output, pattern));
		
		obfuscate(input, output, pattern, Constants.DEFAULT_FILE_BUFFER_SIZE);
		
		log.debug(HelperLog.methodExit());
	}

	@Override
	public void scramble(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
		log.debug(HelperLog.methodStart(input, output, pattern, bufferSize));
		
		obfuscate(input, output, pattern, bufferSize);
		
		log.debug(HelperLog.methodExit());
	}

	@Override
	public void unscramble(final File input, final File output, final byte pattern) throws IOException {
		log.debug(HelperLog.methodStart(input, output, pattern));
		
		unobfuscate(input, output, pattern, Constants.DEFAULT_FILE_BUFFER_SIZE);
		
		log.debug(HelperLog.methodExit());
	}
	
	@Override
	public void unscramble(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
		log.debug(HelperLog.methodStart(input, output, pattern, bufferSize));
		
		unobfuscate(input, output, pattern, bufferSize);
		
		log.debug(HelperLog.methodExit());
	}
}
