/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
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

import java.io.File;
import java.io.IOException;

import net.laubenberger.bogatyr.service.Service;


/**
 * This is an interface for scrambling data.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.6.0
 */
public interface Scrambler extends Service {
	/**
	 * Scramble the data.
	 *
	 * @param input	data (byte-array) to scramble
	 * @param pattern for scrambling (region: -128 - 127)
	 * @return scrambled byte-array
	 * @since 0.6.0
	 */
	byte[] scramble(byte[] input, byte pattern);

	/**
	 * Unscramble the data.
	 *
	 * @param input	data (byte-array) to unscramble
	 * @param pattern for unscrambling (region: -128 - 127)
	 * @return unscrambled byte-array
	 * @since 0.6.0
	 */
	byte[] unscramble(byte[] input, byte pattern);

	/**
	 * Scramble an input {@link File} to an output {@link File}.
	 *
	 * @param input	{@link File} to scramble
	 * @param output  {@link File} for the scrambled data
	 * @param pattern for scrambling (region: -128 - 127)
	 * @throws IOException
	 * @see File
	 * @since 0.9.0
	 */
	void scramble(File input, File output, byte pattern) throws IOException;

	/**
	 * Scramble an input {@link File} to an output {@link File}.
	 *
	 * @param input		{@link File} to scramble
	 * @param output	  {@link File} for the scrambled data
	 * @param pattern	 for scrambling (region: -128 - 127)
	 * @param bufferSize in bytes
	 * @throws IOException
	 * @see File
	 * @since 0.9.0
	 */
	void scramble(File input, File output, byte pattern, int bufferSize) throws IOException;

	/**
	 * Unscramble an input {@link File} to an output {@link File}.
	 *
	 * @param input	{@link File} to unscramble
	 * @param output  {@link File} for the unscrambled data
	 * @param pattern for unscrambling (region: -128 - 127)
	 * @throws IOException
	 * @see File
	 * @since 0.9.0
	 */
	void unscramble(File input, File output, byte pattern) throws IOException;

	/**
	 * Unscramble an input {@link File} to an output {@link File}.
	 *
	 * @param input		{@link File} to unscramble
	 * @param output	  {@link File} for the unscrambled data
	 * @param pattern	 for unscrambling (region: -128 - 127)
	 * @param bufferSize in bytes
	 * @throws IOException
	 * @see File
	 * @since 0.9.0
	 */
	void unscramble(File input, File output, byte pattern, int bufferSize) throws IOException;
}
