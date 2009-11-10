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

import java.io.File;
import java.io.InputStream;

import ch.sisprocom.bogatyr.service.Service;

/**
 * This is an interface for hash code generation.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.8.0
 */
public interface HashCodeGenerator extends Service {
	
	/**
	 * Generates a hash code for a byte-array with the implementations algorithm.
	 *
     * @param input byte-array for the hash code
     * @return generated hash code
	 * @throws Exception
	 * @since 0.8.0
	 */
	String getHash(byte[] input) throws Exception;
	
	/**
	 * Generates a hash code for an {@link InputStream} with the implementations algorithm.
	 * 
     * @param is {@link InputStream} for the hash code
     * @return generated hash code
	 * @throws Exception 
	 * @see InputStream
	 * @since 0.8.0
	 */
	String getHash(InputStream is) throws Exception;
	
	/**
	 * Generates a hash code for an {@link InputStream} with the implementations algorithm.
	 * 
     * @param is {@link InputStream} for the hash code
     * @param bufferSize in bytes
     * @return generated hash code
	 * @throws Exception 
	 * @see InputStream
	 * @since 0.8.0
	 */
	String getHash(InputStream is, int bufferSize) throws Exception;	

	/**
	 * Generates a hash code for an input {@link File} with the implementations algorithm.
	 * 
     * @param input {@link File} for the hash code
     * @return generated hash code
	 * @throws Exception 
	 * @see File
	 * @since 0.8.0
	 */
	String getHash(File input) throws Exception;	
	
	/**
	 * Generates a hash code for an input {@link File} with the implementations algorithm.
	 * 
     * @param input {@link File} for the hash code
     * @param bufferSize in bytes
     * @return generated hash code
	 * @throws Exception 
	 * @see File
	 * @since 0.8.0
	 */
	String getHash(File input, int bufferSize) throws Exception;		
}
