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
package ch.sisprocom.bogatyr.helper.crypto;

import ch.sisprocom.bogatyr.helper.HelperGeneral;



/**
 * This is a class for obfuscating data.
 * 
 * @author Stefan Laubenberger
 * @version 20090403
 */
public class Obfuscator implements IObfuscator {
	private static final byte DEFAULT_PATTERN = Byte.MAX_VALUE;
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	
	/*
	 * Implemented methods
	 */
	public byte[] encrypt(final byte[] input) {
		return obfuscate(input, DEFAULT_PATTERN);
	}

	public byte[] encrypt(final byte[] input, final byte pattern) {
		return obfuscate(input, pattern);
	}

	public byte[] decrypt(final byte[] input) {
		return obfuscate(input, DEFAULT_PATTERN);
	}

	public byte[] decrypt(final byte[] input, final byte pattern) {
		return obfuscate(input, pattern);
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Obfuscate the data.
	 * 
	 * @param input The data to obfuscate as a byte-array
     * @param pattern for unobfuscating (region: -128 - 127)
     * @return the obfuscated data
	 */
	private byte[] obfuscate(final byte[] input, final byte pattern) {
		final byte[] result = new byte[input.length];
		
		for (int ii = 0; ii < input.length; ii++ ) {
			result[ii] = (byte)(input[ii] ^ (int) pattern);
		}
		return result;
	}
}
