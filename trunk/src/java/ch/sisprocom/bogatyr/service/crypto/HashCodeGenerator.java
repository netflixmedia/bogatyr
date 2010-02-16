/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
import java.io.IOException;
import java.io.InputStream;

import ch.sisprocom.bogatyr.model.crypto.HashCodeAlgo;
import ch.sisprocom.bogatyr.service.Service;

/**
 * This is an interface for hash code generation.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.8.0
 */
public interface HashCodeGenerator extends Service {
	
	/**
	 * Generates a hash code for a byte-array.
	 *
     * @param input byte-array for the hash code
     * @return generated hash code
	 * @since 0.8.0
	 */
	byte[] getHash(byte[] input);
	
	/**
	 * Generates a hash code for an {@link InputStream}.
	 * 
     * @param is {@link InputStream} for the hash code
     * @return generated hash code
	 * @throws IOException 
	 * @see InputStream
	 * @since 0.8.0
	 */
	byte[] getHash(InputStream is) throws IOException;
	
	/**
	 * Generates a hash code for an {@link InputStream}.
	 * 
     * @param is {@link InputStream} for the hash code
     * @param bufferSize in bytes
     * @return generated hash code
	 * @throws IOException 
	 * @see InputStream
	 * @since 0.8.0
	 */
	byte[] getHash(InputStream is, int bufferSize) throws IOException;	

	/**
	 * Generates a hash code for an input {@link File}.
	 * 
     * @param input {@link File} for the hash code
     * @return generated hash code
	 * @throws IOException 
	 * @see File
	 * @since 0.8.0
	 */
	byte[] getHash(File input) throws IOException;
	
	/**
	 * Generates a hash code for an input {@link File} with the given {@link HashCodeAlgo}.
	 * 
     * @param input {@link File} for the hash code
     * @param bufferSize in bytes
     * @return generated hash code
	 * @throws IOException 
	 * @see File
	 * @see HashCodeAlgo
	 * @since 0.8.0
	 */
	byte[] getHash(File input, int bufferSize) throws IOException;		
	
	/**
	 * Generates a hash code for a byte-array.
	 * This method generates a fast hash code over the data (e.g. compare different data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
	 *
     * @param input byte-array for the hash code
     * @param parts for the hash code data
     * @param partSize of the hash code data
     * @return generated hash code
	 * @since 0.9.0
	 */
	byte[] getFastHash(byte[] input, int parts, int partSize);
	
	/**
	 * Generates a hash code for a byte-array with the standard settings.
	 * This method generates a fast hash code over the data (e.g. compare different data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
	 *
     * @param input byte-array for the hash code
     * @return generated hash code
	 * @since 0.9.0
	 */
	byte[] getFastHash(byte[] input);

	/**
	 * Generates a fast hash code for an input {@link File}.
	 * This method generates a fast hash code over the {@link File} (e.g. find same file containing the same data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
     *
     * @param input {@link File} for the hash code
     * @param parts for the hash code data
     * @param partSize of the hash code data
     * @return generated hash code
	 * @throws IOException 
	 * @see File
	 * @since 0.9.0
	 */
	byte[] getFastHash(File input, int parts, int partSize) throws IOException;

	/**
	 * Generates a fast hash code for an input {@link File} with standard settings.
	 * This method generates a fast hash code over the {@link File} (e.g. find same file containing the same data)
	 * 
     * <strong>Warning:</strong> Don't use this method to verify the integrity of data. It doesn't use the complete data to generate the hash code.
     *
     * @param input {@link File} for the hash code
     * @return generated hash code
	 * @throws IOException 
	 * @see File
	 * @since 0.9.0
	 */
	byte[] getFastHash(File input) throws IOException;
}
