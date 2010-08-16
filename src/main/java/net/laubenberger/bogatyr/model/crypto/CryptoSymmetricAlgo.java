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

package net.laubenberger.bogatyr.model.crypto;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * Symmetric crypto algorithms available in BouncyCastle
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100817)
 * @since 0.9.1
 */
@XmlRootElement(name = "cryptoSymmetricAlgo")
public enum CryptoSymmetricAlgo implements CryptoAlgo {
	AES("AES", "AES/CBC/PKCS5Padding", 192, 16), //$NON-NLS-1$ //$NON-NLS-2$
	DES("DES", "DES/CBC/PKCS5Padding", 64, 8), //$NON-NLS-1$ //$NON-NLS-2$
	DESEDE("DESede", "DESede/CBC/PKCS5Padding", 128, 8), //$NON-NLS-1$ //$NON-NLS-2$
	BLOWFISH("Blowfish", "Blowfish/CBC/PKCS5Padding", 448, 8), //$NON-NLS-1$ //$NON-NLS-2$
	CAMELLIA("Camellia", "Camellia/CBC/PKCS5Padding", 256, 16), //$NON-NLS-1$ //$NON-NLS-2$
	CAST5("CAST5", "CAST5/CBC/PKCS5Padding", 128, 8), //$NON-NLS-1$ //$NON-NLS-2$
	CAST6("CAST6", "CAST6/CBC/PKCS5Padding", 256, 16), //$NON-NLS-1$ //$NON-NLS-2$
	NOEKEON("Noekeon", "Noekeon/CBC/PKCS5Padding", 128, 16), //$NON-NLS-1$ //$NON-NLS-2$
	RC2("RC2", "RC2/CBC/PKCS5Padding", 128, 8), //$NON-NLS-1$ //$NON-NLS-2$
	RC5("RC5", "RC5/CBC/PKCS5Padding", 128, 8), //$NON-NLS-1$ //$NON-NLS-2$
	RC6("RC6", "Blowfish/CBC/PKCS5Padding", 128, 8), //$NON-NLS-1$ //$NON-NLS-2$
	RIJNDAEL("Rijndael", "Rijndael/CBC/PKCS5Padding", 192, 16), //$NON-NLS-1$ //$NON-NLS-2$
	SEED("SEED", "SEED/CBC/PKCS5Padding", 128, 16), //$NON-NLS-1$ //$NON-NLS-2$
	SERPENT("Serpent", "Serpent/CBC/PKCS5Padding", 256, 16), //$NON-NLS-1$ //$NON-NLS-2$
	SKIPJACK("Skipjack", "Skipjack/CBC/PKCS5Padding", 128, 8), //$NON-NLS-1$ //$NON-NLS-2$
	TWOFISH("Twofish", "Twofish/CBC/PKCS5Padding", 256, 16), //$NON-NLS-1$ //$NON-NLS-2$
	TEA("TEA", "TEA/CBC/PKCS5Padding", 128, 8), //$NON-NLS-1$ //$NON-NLS-2$
	XTEA("XTEA", "XTEA/CBC/PKCS5Padding", 128, 8); //$NON-NLS-1$ //$NON-NLS-2$

	private final String algorithm;
	private final String xform;
	private final int defaultKeysize;
	private final int ivSize;

	CryptoSymmetricAlgo(final String algorithm, final String xform, final int defaultKeysize, final int ivSize) {
		this.algorithm = algorithm;
		this.xform = xform;
		this.defaultKeysize = defaultKeysize;
		this.ivSize = ivSize;
	}

	public int getIvSize() {
		return ivSize;
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

