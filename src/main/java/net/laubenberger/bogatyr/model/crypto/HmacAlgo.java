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

package net.laubenberger.bogatyr.model.crypto;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Hmac algoritms available in BouncyCastle
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101105)
 * @since 0.9.1
 */
@XmlRootElement(name = "hmacAlgo")
public enum HmacAlgo implements Algorithm {
//	DES("DESMAC"), //$NON-NLS-1$
//	DESEDE("DESEDEMAC"), //$NON-NLS-1$
	MD2("HMACMD2"), //$NON-NLS-1$
	MD4("HMACMD4"), //$NON-NLS-1$
	MD5("HMACMD5"), //$NON-NLS-1$
	SHA1("HMACSHA1"), //$NON-NLS-1$
	SHA224("HMACSHA224"), //$NON-NLS-1$
	SHA256("HMACSHA256"), //$NON-NLS-1$
	SHA384("HMACSHA384"), //$NON-NLS-1$
	SHA512("HMACSHA512"), //$NON-NLS-1$
//	RC2("RC2MAC"), //$NON-NLS-1$
//	RC5("RC5MAC"), //$NON-NLS-1$
	RIPEMD128("HMACRIPEMD128"), //$NON-NLS-1$
	RIPEMD160("HMACRIPEMD160"), //$NON-NLS-1$
	TIGER("HMACTIGER"); //$NON-NLS-1$

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

