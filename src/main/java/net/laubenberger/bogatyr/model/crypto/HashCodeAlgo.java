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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package net.laubenberger.bogatyr.model.crypto;


/**
 * Hash codes algoritms available in BouncyCastle
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public enum HashCodeAlgo implements Algorithm {
	MD2("MD2"), //$NON-NLS-1$
	MD4("MD4"), //$NON-NLS-1$
	MD5("MD5"), //$NON-NLS-1$
	SHA1("SHA1"), //$NON-NLS-1$
	SHA224("SHA224"), //$NON-NLS-1$
	SHA256("SHA256"), //$NON-NLS-1$
	SHA384("SHA384"), //$NON-NLS-1$
	SHA512("SHA512"), //$NON-NLS-1$
	RIPEMD128("RIPEMD128"), //$NON-NLS-1$
	RIPEMD160("RIPEMD160"), //$NON-NLS-1$
	RIPEMD256("RIPEMD256"), //$NON-NLS-1$
	RIPEMD320("RIPEMD320"), //$NON-NLS-1$
	TIGER("Tiger"), //$NON-NLS-1$
	GOST3411("GOST3411"), //$NON-NLS-1$
	WHIRLPOOL("WHIRLPOOL"); //$NON-NLS-1$

	private final String algorithm;

	HashCodeAlgo(final String algorithm) {
		this.algorithm = algorithm;
	}


	/*
	 * Implemented methods
	 */

	@Override
	public String getAlgorithm() {
		return algorithm;
	}
}	

