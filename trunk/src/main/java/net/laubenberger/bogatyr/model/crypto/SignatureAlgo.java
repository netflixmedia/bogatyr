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
 * Signature algoritms available in BouncyCastle
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.1
 */
public enum SignatureAlgo implements Algorithm {
	MD2_WITH_RSA("MD2withRSA"), //$NON-NLS-1$
	MD5_WITH_RSA("MD5withRSA"), //$NON-NLS-1$
	SHA1_WITH_RSA("SHA1withRSA"), //$NON-NLS-1$
//	SHA1_WITH_DSA("SHA1withDSA"), //$NON-NLS-1$
	SHA224_WITH_RSA("SHA224withRSA"), //$NON-NLS-1$
	SHA256_WITH_RSA("SHA256withRSA"), //$NON-NLS-1$
	SHA384_WITH_RSA("SHA384withRSA"), //$NON-NLS-1$
	SHA512_WITH_RSA("SHA512withRSA"), //$NON-NLS-1$
	RIPEMD128_WITH_RSA("RIPEMD128withRSA"), //$NON-NLS-1$
	RIPEMD160_WITH_RSA("RIPEMD160withRSA"), //$NON-NLS-1$
	RIPEMD256_WITH_RSA("RIPEMD256withRSA"); //$NON-NLS-1$

	private final String algorithm;

	SignatureAlgo(final String algorithm) {
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

