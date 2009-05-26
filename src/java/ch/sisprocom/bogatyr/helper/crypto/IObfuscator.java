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
package ch.sisprocom.bogatyr.helper.crypto;


/**
 * This is an interface for obfuscating data.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.70
 */
public interface IObfuscator {
	/**
	 * Obfuscate the data with the default pattern.
	 * 
	 * @param input data (byte-array) to obfuscate
	 * @return obfuscated byte-array 
	 */
	byte[] encrypt(final byte[] input);

	/**
	 * Obfuscate the data.
	 * 
	 * @param input data (byte-array) to obfuscate
	 * @param pattern for obfuscating (region: -128 - 127)
	 * @return obfuscated byte-array 
	 */
	byte[] encrypt(final byte[] input, final byte pattern);

	/**
	 * Unobfuscate the data.
	 * 
	 * @param input data (byte-array) to unobfuscate
	 * @return unobfuscated byte-array
	 */
	byte[] decrypt(final byte[] input);

	
	/**
	 * Unobfuscate the data.
	 * 
	 * @param input data (byte-array) to unobfuscate
	 * @param pattern for unobfuscating (region: -128 - 127)
	 * @return unobfuscated byte-array
	 */
	byte[] decrypt(final byte[] input, final byte pattern);
}
