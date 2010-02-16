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
 * Asymmetric crypto algorithms available in BouncyCastle
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.9.1
 */
public enum CryptoAsymmetricAlgo implements CryptoAlgo {
	RSA("RSA", "RSA/NONE/PKCS1PADDING", 2048),  //$NON-NLS-1$//$NON-NLS-2$
	ELGAMAL("ElGamal", "ElGamal/NONE/PKCS1Padding", 1024);  //$NON-NLS-1$//$NON-NLS-2$
//	ELGAMAL("ElGamal", "ElGamal/NONE/NoPadding", 1024);  //$NON-NLS-1$//$NON-NLS-2$
	
	private final String algorithm;
	private final String xform;
	private final int defaultKeysize;
	
	CryptoAsymmetricAlgo(final String algorithm, final String xform, final int defaultKeysize) {
		this.algorithm = algorithm;
		this.xform = xform;
		this.defaultKeysize = defaultKeysize;
	}

	
	/*
	 * Implemented methods
	 */
	@Override
    public String getAlgorithm() {
		return algorithm;
	}
    
	@Override
    public String getXform() {
		return xform;
	}
    
	@Override
    public int getDefaultKeysize() {
		return defaultKeysize;
	}
}	

