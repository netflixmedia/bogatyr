/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper.crypto;

import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is a class for obfuscating a byte[]
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public abstract class Obfuscator {
	private static final byte DEFAULT_PATTERN = Byte.MAX_VALUE;

	/**
	 * Obfuscate the data
	 * 
	 * @param input The data to obfuscate as a Byte-Array
	 * @return Return the obfuscated Byte-Array 
	 */
	public static byte[] encrypt(final byte[] input) {
		Logger.getInstance().writeMethodEntry(Obfuscator.class, "encrypt", input);  //$NON-NLS-1$

		final byte[] result = obfuscate(input, DEFAULT_PATTERN);
		
		Logger.getInstance().writeMethodExit(Obfuscator.class, "encrypt", result);  //$NON-NLS-1$
		return result;
	}

	/**
	 * Obfuscate the data
	 * 
	 * @param input The data to obfuscate as a Byte-Array
	 * @param pattern for obfuscating (region: -128 - 127)
	 * @return Return the obfuscated Byte-Array 
	 */
	public static byte[] encrypt(final byte[] input, final byte pattern) {
		Logger.getInstance().writeMethodEntry(Obfuscator.class, "encrypt", new Object[]{input, pattern});  //$NON-NLS-1$

		final byte[] result = obfuscate(input, pattern);
		
		Logger.getInstance().writeMethodExit(Obfuscator.class, "encrypt", result);  //$NON-NLS-1$
		return result;
	}

	/**
	 * Unobfuscate the data.
	 * 
	 * @param input The obfuscated data as a Byte-Array
	 * @return Return the unobfuscated Byte-Array
	 */
	public static byte[] decrypt(final byte[] input) {
		Logger.getInstance().writeMethodEntry(Obfuscator.class, "decrypt", input);  //$NON-NLS-1$

		final byte[] result = obfuscate(input, DEFAULT_PATTERN);
		
		Logger.getInstance().writeMethodExit(Obfuscator.class, "decrypt", result);  //$NON-NLS-1$
		return result;
	}

	
	/**
	 * Unobfuscate the data.
	 * 
	 * @param input The obfuscated data as a Byte-Array
	 * @param pattern for unobfuscating (region: -128 - 127)
	 * @return Return the unobfuscated Byte-Array
	 */
	public static byte[] decrypt(final byte[] input, final byte pattern) {
		Logger.getInstance().writeMethodEntry(Obfuscator.class, "decrypt", new Object[]{input, pattern});  //$NON-NLS-1$

		final byte[] result = obfuscate(input, pattern);
		
		Logger.getInstance().writeMethodExit(Obfuscator.class, "decrypt", result);  //$NON-NLS-1$
		return result;
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Obfuscate the data
	 * 
	 * @param input The data to obfuscate as a Byte-Array
     * @param pattern for unobfuscating (region: -128 - 127)
     * @return the obfuscated data
	 */
	private static byte[] obfuscate(final byte[] input, final byte pattern) {
		Logger.getInstance().writeMethodEntry(Obfuscator.class, "obfuscate", new Object[]{input, pattern});  //$NON-NLS-1$

		final byte[] result = new byte[input.length];
		
		for (int ii = 0; ii < input.length; ii++ ) {
			result[ii] = (byte)(input[ii] ^ (int) pattern);
		}
		
		Logger.getInstance().writeMethodExit(Obfuscator.class, "obfuscate", result);  //$NON-NLS-1$
		return result;
	}
}
