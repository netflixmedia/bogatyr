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

import ch.sisprocom.bogatyr.service.Service;


/**
 * This is an interface for scrambling data.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.6.0
 */
public interface Scrambler extends Service {
	/**
	 * Scramble the data with the default pattern.
	 * 
	 * @param input data (byte-array) to scramble
	 * @return scrambled byte-array 
	 * @since 0.6.0
	 */
	byte[] scramble(byte[] input);

	/**
	 * Scramble the data.
	 * 
	 * @param input data (byte-array) to scramble
	 * @param pattern for scrambling (region: -128 - 127)
	 * @return scrambled byte-array 
	 * @since 0.6.0
	 */
	byte[] scramble(byte[] input, byte pattern);

	/**
	 * Unscramble the data.
	 * 
	 * @param input data (byte-array) to unscramble
	 * @return unscrambled byte-array
	 * @since 0.6.0
	 */
	byte[] unscramble(byte[] input);

	
	/**
	 * Unscramble the data.
	 * 
	 * @param input data (byte-array) to unscramble
	 * @param pattern for unscrambling (region: -128 - 127)
	 * @return unscrambled byte-array
	 * @since 0.6.0
	 */
	byte[] unscramble(byte[] input, byte pattern);
}
