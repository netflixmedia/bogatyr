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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
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
import ch.sisprocom.bogatyr.model.crypto.HashCode;

/**
 * This is an interface for hash code generation.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.8.0
 */
public interface HashCodeGenerator extends Service {
	
	/**
	 * Generates a hash code for a byte-array with the given {@link HashCode}.
	 *
     * @param input byte-array for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @return generated hash code
	 * @throws Exception
	 * @see HashCode
	 * @since 0.8.0
	 */
	String getHash(byte[] input, HashCode hashCode) throws Exception;
	
	/**
	 * Generates a hash code for an {@link InputStream} with the given {@link HashCode}.
	 * 
     * @param is {@link InputStream} for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @return generated hash code
	 * @throws Exception 
	 * @see InputStream
	 * @see HashCode
	 * @since 0.8.0
	 */
	String getHash(InputStream is, HashCode hashCode) throws Exception;
	
	/**
	 * Generates a hash code for an {@link InputStream} with the given {@link HashCode}.
	 * 
     * @param is {@link InputStream} for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @param bufferSize in bytes
     * @return generated hash code
	 * @throws Exception 
	 * @see InputStream
	 * @see HashCode
	 * @since 0.8.0
	 */
	String getHash(InputStream is, HashCode hashCode, int bufferSize) throws Exception;	

	/**
	 * Generates a hash code for an input {@link File} with the given {@link HashCode}.
	 * 
     * @param input {@link File} for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @return generated hash code
	 * @throws Exception 
	 * @see File
	 * @see HashCode
	 * @since 0.8.0
	 */
	String getHash(File input, HashCode hashCode) throws Exception;
	
	/**
	 * Generates a hash code for an input {@link File} with the given {@link HashCode}.
	 * 
     * @param input {@link File} for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @param bufferSize in bytes
     * @return generated hash code
	 * @throws Exception 
	 * @see File
	 * @see HashCode
	 * @since 0.8.0
	 */
	String getHash(File input, HashCode hashCode, int bufferSize) throws Exception;		
	
	/**
	 * Generates a hash code for a byte-array with the given {@link HashCode}.
	 * This method generates a fast hash code over the data (e.g. compare different data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
	 *
     * @param input byte-array for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @param parts for the hash code data
     * @param partSize of the hash code data
     * @return generated hash code
	 * @throws Exception
	 * @see HashCode
	 * @since 0.9.0
	 */
	String getFastHash(byte[] input, HashCode hashCode, int parts, int partSize) throws Exception;
	
	/**
	 * Generates a hash code for a byte-array with the given {@link HashCode}.
	 * This method generates a fast hash code over the data (e.g. compare different data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
	 *
     * @param input byte-array for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @return generated hash code
	 * @throws Exception
	 * @see HashCode
	 * @since 0.9.0
	 */
	String getFastHash(byte[] input, HashCode hashCode) throws Exception;

	/**
	 * Generates a fast hash code for an input {@link File} with the given {@link HashCode}.
	 * This method generates a fast hash code over the {@link File} (e.g. find same file containing the same data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
     *
     * @param input {@link File} for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @param parts for the hash code data
     * @param partSize of the hash code data
     * @return generated hash code
	 * @throws Exception 
	 * @see File
	 * @see HashCode
	 * @since 0.9.0
	 */
	String getFastHash(File input, HashCode hashCode, int parts, int partSize) throws Exception;

	/**
	 * Generates a fast hash code for an input {@link File} with the given {@link HashCode}.
	 * This method generates a fast hash code over the {@link File} (e.g. find same file containing the same data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
     *
     * @param input {@link File} for the hash code
     * @param hashCode {@link HashCode} algorithm
     * @return generated hash code
	 * @throws Exception 
	 * @see File
	 * @see HashCode
	 * @since 0.9.0
	 */
	String getFastHash(File input, HashCode hashCode) throws Exception;
}
