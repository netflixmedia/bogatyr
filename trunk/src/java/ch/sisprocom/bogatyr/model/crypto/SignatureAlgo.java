/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.model.crypto;


/**
 * Signature algoritms available in BouncyCastle
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.9.1
 */
public enum SignatureAlgo implements Algorithm {
	MD2WITHRSA ("MD2withRSA"), //$NON-NLS-1$
	MD5WITHRSA ("MD5withRSA"), //$NON-NLS-1$
	SHA1WITHRSA("SHA1withRSA"), //$NON-NLS-1$
	RIPEMD128WITHRSA("RIPEMD128withRSA"), //$NON-NLS-1$
	RIPEMD160WITHRSA("RIPEMD160withRSA"), //$NON-NLS-1$
	RIPEMD256WITHRSA("RIPEMD256withRSA"), //$NON-NLS-1$
	SHA224WITHRSA("SHA224withRSA"), //$NON-NLS-1$
	SHA256WITHRSA("SHA256withRSA"), //$NON-NLS-1$
	SHA384WITHRSA("SHA384withRSA"), //$NON-NLS-1$
	SHA512WITHRSA("SHA512withRSA"); //$NON-NLS-1$

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

