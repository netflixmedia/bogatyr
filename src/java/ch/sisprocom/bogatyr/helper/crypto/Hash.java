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

import java.io.File;
import java.io.InputStream;

import javax.crypto.SecretKey;

/**
 * This is an interface for symmetric cryptology.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091015)
 * @since 0.8.0
 */
public interface Hash {
	
	/**
	 * Generates a hash code for a byte-array with the implementations algorithm.
	 * 
     * @return generated hash code
	 * @throws Exception 
	 * @since 0.8.0
	 */
	String getHash(byte[] input) throws Exception;
	
	/**
	 * Generates a hash code for an input stream {@link InputStream} with the implementations algorithm.
	 * 
     * @return generated secret key
	 * @throws Exception 
	 * @see SecretKey 
	 * @since 0.8.0
	 */
	String getHash(InputStream is) throws Exception;
	
	/**
	 * Generates a hash code for an input stream {@link InputStream} with the implementations algorithm.
	 * 
     * @return generated secret key
	 * @throws Exception 
	 * @see SecretKey 
	 * @since 0.8.0
	 */
	String getHash(InputStream is, int bufferSize) throws Exception;	

	/**
	 * Generates a hash code for an input File {@link File} with the implementations algorithm.
	 * 
     * @return generated secret key
	 * @throws Exception 
	 * @see SecretKey 
	 * @since 0.8.0
	 */
	String getHash(File input) throws Exception;	
	
	/**
	 * Generates a hash code for an input File {@link File} with the implementations algorithm.
	 * 
     * @return generated secret key
	 * @throws Exception 
	 * @see SecretKey 
	 * @since 0.8.0
	 */
	String getHash(File input, int bufferSize) throws Exception;		
}
