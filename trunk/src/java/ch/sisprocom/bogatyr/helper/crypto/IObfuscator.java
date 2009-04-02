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
 * @version 20090402
 */
public interface IObfuscator {
	/**
	 * Obfuscate the data.
	 * 
	 * @param input The data to obfuscate as a byte-array
	 * @return Return the obfuscated byte-array 
	 */
	byte[] encrypt(final byte[] input);

	/**
	 * Obfuscate the data.
	 * 
	 * @param input The data to obfuscate as a byte-array
	 * @param pattern for obfuscating (region: -128 - 127)
	 * @return Return the obfuscated byte-array 
	 */
	byte[] encrypt(final byte[] input, final byte pattern);

	/**
	 * Unobfuscate the data.
	 * 
	 * @param input The obfuscated data as a byte-array
	 * @return Return the unobfuscated byte-array
	 */
	byte[] decrypt(final byte[] input);

	
	/**
	 * Unobfuscate the data.
	 * 
	 * @param input The obfuscated data as a byte-array
	 * @param pattern for unobfuscating (region: -128 - 127)
	 * @return Return the unobfuscated byte-array
	 */
	byte[] decrypt(final byte[] input, final byte pattern);
}
