/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.service.crypto;

import java.security.InvalidKeyException;
import java.security.Key;

import net.laubenberger.bogatyr.service.Service;

/**
 * This is an interface for hmac generation.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.1
 */
public interface HmacGenerator extends Service {

	/**
	 * Generates a hmac for a byte-array with the given {@link Key}.
	 *
	 * @param input byte-array for the hmac
	 * @param key	for the hmac (e.g. AES-key)
	 * @return generated hmac
	 * @throws InvalidKeyException
	 * @see Key
	 * @since 0.9.1
	 */
	byte[] getHmac(byte[] input, Key key) throws InvalidKeyException;

//	/**
//	 * Generates a hash code for an {@link InputStream} with the given {@link HashCodeAlgo}.
//	 * 
//     * @param is {@link InputStream} for the hash code
//     * @return generated hash code
//	 * @throws IOException 
//	 * @see InputStream
//	 * @see HashCodeAlgo
//	 * @since 0.8.0
//	 */
//	byte[] getHash(InputStream is) throws IOException;
//	
//	/**
//	 * Generates a hash code for an {@link InputStream} with the given {@link HashCodeAlgo}.
//	 * 
//     * @param is {@link InputStream} for the hash code
//     * @param bufferSize in bytes
//     * @return generated hash code
//	 * @throws IOException 
//	 * @see InputStream
//	 * @see HashCodeAlgo
//	 * @since 0.8.0
//	 */
//	byte[] getHash(InputStream is, int bufferSize) throws IOException;	
//
//	/**
//	 * Generates a hash code for an input {@link File} with the given {@link HashCodeAlgo}.
//	 * 
//     * @param input {@link File} for the hash code
//     * @return generated hash code
//	 * @throws IOException 
//	 * @see File
//	 * @see HashCodeAlgo
//	 * @since 0.8.0
//	 */
//	byte[] getHash(File input) throws IOException;
//	
//	/**
//	 * Generates a hash code for an input {@link File} with the given {@link HashCodeAlgo}.
//	 * 
//     * @param input {@link File} for the hash code
//     * @param bufferSize in bytes
//     * @return generated hash code
//	 * @throws IOException 
//	 * @see File
//	 * @see HashCodeAlgo
//	 * @since 0.8.0
//	 */
//	byte[] getHash(File input, int bufferSize) throws IOException;		
}
