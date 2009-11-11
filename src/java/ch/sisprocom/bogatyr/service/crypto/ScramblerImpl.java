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

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is a class for obfuscating data with CFB.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091111)
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
		for (int ii = 1; ii < input.length; ii++ ) {
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
		for (int ii = 1; ii < input.length; ii++ ) {
			result[ii] = (byte)(input[ii] ^ (int) input[ii-1]);
		}
		return result;
	}

	
	/*
	 * Implemented methods
	 */
	@Override
    public byte[] scramble(final byte[] input) { //$JUnit$
		return scramble(input, Byte.MAX_VALUE);
	}

	@Override
    public byte[] scramble(final byte[] input, final byte pattern) { //$JUnit$
		return obfuscate(input, pattern);
	}

	@Override
    public byte[] unscramble(final byte[] input) { //$JUnit$
		return unscramble(input, Byte.MAX_VALUE);
	}

	@Override
    public byte[] unscramble(final byte[] input, final byte pattern) { //$JUnit$
		return unobfuscate(input, pattern);
	}
}
