/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.crypto;

import java.security.PublicKey;

/**
 * This is a class for obfuscating a byte[]
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080111
 */
public abstract class Obfuscator {
	private static byte pattern = Byte.MAX_VALUE; //Region: -128 - 127

	/**
	 * Obfuscate the data
	 * 
	 * @param input The data to obfuscate as a Byte-Array
	 * @return Return the obfuscated Byte-Array 
	 * @see PublicKey
	 */
	public static byte[] encrypt(byte[] input) {
		return obfuscate(input);
	}

	/**
	 * Unobfuscate the data.
	 * 
	 * @param input The obfuscated data as a Byte-Array
	 * @return Return the unobfuscated Byte-Array
	 */
	public static byte[] decrypt(byte[] input) {
		return obfuscate(input);
	}

	/*
	 * Private methods
	 */
	/**
	 * Obfuscate the data
	 * 
	 * @param input The data to obfuscate as a Byte-Array
	 */
	private static byte[] obfuscate(byte[] input) {
		byte[] result = new byte[input.length];
		
		for (int ii = 0; ii < input.length; ii++ ) {
			result[ii] = (byte)(input[ii] ^ pattern);
		}
		return result;
	}
}
