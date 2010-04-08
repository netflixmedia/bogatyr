/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.model.crypto;


/**
 * Signature algoritms available in BouncyCastle
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.9.1
 */
public enum SignatureAlgo implements Algorithm {
	MD2_WITH_RSA ("MD2withRSA"), //$NON-NLS-1$
	MD5_WITH_RSA ("MD5withRSA"), //$NON-NLS-1$
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
