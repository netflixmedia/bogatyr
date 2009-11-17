/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is a class for obfuscating data with CFB.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091116)
 * @since 0.3.0
 */
public class ScramblerImpl extends ServiceAbstract implements Scrambler {

	
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
		if (!HelperArray.isValid(input)) {
			throw new IllegalArgumentException("input is null or empty!"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("the doubled input (" + input.length * 2 + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }

		final byte[] result = new byte[input.length];
		
		result[0] = (byte)(input[0] ^ (int) pattern);
		for (int ii = 1; ii < input.length; ii++) {
			result[ii] = (byte)(input[ii] ^ (int) result[ii-1]);
		}
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
		if (!HelperArray.isValid(input)) {
			throw new IllegalArgumentException("input is null or empty!"); //$NON-NLS-1$
		}
        if (input.length * 2 > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("the doubled input (" + input.length * 2 + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }

		final byte[] result = new byte[input.length];
		
		result[0] = (byte)(input[0] ^ (int) pattern);
		for (int ii = 1; ii < input.length; ii++) {
			result[ii] = (byte)(input[ii] ^ (int) input[ii-1]);
		}
		return result;
	}

	private static void obfuscate(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
        if (null == input) {
            throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
        }
		if (!input.exists()) {
			throw new IllegalArgumentException("input doesn't exists: " + input); //$NON-NLS-1$
		}
		if (null == output) {
            throw new IllegalArgumentException("output is null!"); //$NON-NLS-1$
        }
		if (input.equals(output)) {
			throw new IllegalArgumentException("input is equals to output!"); //$NON-NLS-1$
		}
        if (1 > bufferSize) {
            throw new IllegalArgumentException("bufferSize (" + bufferSize + ") must be greater than 1"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (bufferSize > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("bufferSize (" + bufferSize + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
		
        final byte[] buffer = new byte[bufferSize];

        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(input);
            os = new FileOutputStream(output);
            int len;
            byte lastByte = pattern;
            while (0 < (len = is.read(buffer))) {
                            	
            	os.write(obfuscate(buffer, lastByte), 0, len);
            	
            	lastByte = buffer[buffer.length -1];
            }
            os.flush();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
	}
	

	private static void unobfuscate(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
        if (null == input) {
            throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
        }
		if (!input.exists()) {
			throw new IllegalArgumentException("input doesn't exists: " + input); //$NON-NLS-1$
		}
		if (null == output) {
            throw new IllegalArgumentException("output is null!"); //$NON-NLS-1$
        }
		if (input.equals(output)) {
			throw new IllegalArgumentException("input is equals to output!"); //$NON-NLS-1$
		}
        if (1 > bufferSize) {
            throw new IllegalArgumentException("bufferSize (" + bufferSize + ") must be greater than 1"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (bufferSize > HelperEnvironment.getMemoryHeapFree()) {
            throw new IllegalArgumentException("bufferSize (" + bufferSize + ") exceeds the free VM heap memory (" + HelperEnvironment.getMemoryHeapFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }

		final byte[] buffer = new byte[bufferSize];
		
		InputStream is = null;
		OutputStream os =  null;

		try {
            is = new FileInputStream(input);
            os = new FileOutputStream(output);
            int len;
            byte lastByte = pattern;
            while (0 < (len = is.read(buffer))) {
                            	
            	os.write(unobfuscate(buffer, lastByte), 0, len);
            	
            	lastByte = buffer[buffer.length -1];
            }
            os.flush();
        } finally {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
        }
	}

	
	/*
	 * Implemented methods
	 */
	@Override
    public byte[] scramble(final byte[] input, final byte pattern) { //$JUnit$
		return obfuscate(input, pattern);
	}

	@Override
    public byte[] unscramble(final byte[] input, final byte pattern) { //$JUnit$
		return unobfuscate(input, pattern);
	}

	@Override
	public void scramble(final File input, final File output, final byte pattern) throws IOException {
		obfuscate(input, output, pattern, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

	@Override
	public void scramble(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
		obfuscate(input, output, pattern, bufferSize);
	}

	@Override
	public void unscramble(final File input, final File output, final byte pattern) throws IOException {
		unobfuscate(input, output, pattern, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}
	
	@Override
	public void unscramble(final File input, final File output, final byte pattern, final int bufferSize) throws IOException {
		unobfuscate(input, output, pattern, bufferSize);
	}
}