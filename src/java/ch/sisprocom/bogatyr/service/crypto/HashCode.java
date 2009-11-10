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

import ch.sisprocom.bogatyr.helper.HelperObject;

/**
 * Hash codes available in BouncyCastle
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091101)
 * @since 0.9.0
 */
public enum HashCode {
	MD5("MD5"), //$NON-NLS-1$
	SHA1("SHA1"), //$NON-NLS-1$
	SHA256("SHA256"), //$NON-NLS-1$
	SHA512("SHA512"), //$NON-NLS-1$
	RIPEMD160("RIPEMD160"), //$NON-NLS-1$
	RIPEMD320("RIPEMD320"), //$NON-NLS-1$
	TIGER("Tiger"), //$NON-NLS-1$
	WHIRLPOOL("WHIRLPOOL"); //$NON-NLS-1$

	private final String algorithm;
	
	HashCode(final String algorithm) {
		this.algorithm = algorithm;
	}


    public String getAlgorithm() {
		return algorithm;
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}	
