/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.HelperObject;



/**
 * This is a class for obfuscating data with CFB.
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public class Obfuscator implements IObfuscator {
	private byte pattern = Byte.MAX_VALUE;
	
	public Obfuscator() {
		super();
	}
	
	public Obfuscator(final byte pattern) {
		super();
		
		this.pattern = pattern;
	}
	
	public byte getPattern() {
		return pattern;
	}

	public void setPattern(final byte pattern) {
		this.pattern = pattern;
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
	
	/*
	 * Implemented methods
	 */
	public byte[] encrypt(final byte[] input) { //$JUnit
		return obfuscate(input);
	}

	public byte[] encrypt(final byte[] input, final byte pattern) { //$JUnit
		this.pattern = pattern;
		return obfuscate(input);
	}

	public byte[] decrypt(final byte[] input) { //$JUnit
		return unobfuscate(input);
	}

	public byte[] decrypt(final byte[] input, final byte pattern) { //$JUnit
		this.pattern = pattern;
		return unobfuscate(input);
	}

	
	/*
	 * Private methods
	 */
	/**
	 * Obfuscate the data.
	 * 
	 * @param input data (byte-array) to obfuscate
     * @return obfuscated data
	 */
	private byte[] obfuscate(final byte[] input) {
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}

		final byte[] result = new byte[input.length];
		
		result[0] = (byte)(input[0] ^ (int) pattern);
		for (int ii = 1; ii < input.length; ii++ ) {
			result[ii] = (byte)(input[ii] ^ (int) result[ii-1]);
		}
		return result;
	}

	/**
	 * Unobfuscate the data.
	 * 
	 * @param input data (byte-array) to unobfuscate
     * @return unobfuscated data
	 */
	private byte[] unobfuscate(final byte[] input) {
		if (null == input) {
			throw new IllegalArgumentException("input is null!"); //$NON-NLS-1$
		}

		final byte[] result = new byte[input.length];
		
		result[0] = (byte)(input[0] ^ (int) pattern);
		for (int ii = 1; ii < input.length; ii++ ) {
			result[ii] = (byte)(input[ii] ^ (int) input[ii-1]);
		}
		return result;
	}
}
