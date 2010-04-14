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
package ch.customcode.bogatyr.model.crypto;


/**
 * Hmac algoritms available in BouncyCastle
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.9.1
 */
public enum HmacAlgo implements Algorithm {
	MD2("HmacMD2"), //$NON-NLS-1$
	MD4("HmacMD4"), //$NON-NLS-1$
	MD5("HmacMD5"), //$NON-NLS-1$
	SHA1("HmacSHA1"), //$NON-NLS-1$
	SHA224("HmacSHA224"), //$NON-NLS-1$
	SHA256("HmacSHA256"), //$NON-NLS-1$
	SHA384("HmacSHA384"), //$NON-NLS-1$
	SHA512("HmacSHA512"), //$NON-NLS-1$
	RIPEMD128("HmacRIPEMD128"), //$NON-NLS-1$
	RIPEMD160("HmacRIPEMD160"), //$NON-NLS-1$
	TIGER("HmacTiger"); //$NON-NLS-1$

	private final String algorithm;
	
	HmacAlgo(final String algorithm) {
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

