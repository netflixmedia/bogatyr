/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.service.crypto;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.Mac;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.model.crypto.HmacAlgo;
import ch.sisprocom.bogatyr.service.ServiceAbstract;


/**
 * This is an implementation for hmac generation.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.9.1
 */
public class HmacGeneratorImpl extends ServiceAbstract implements HmacGenerator {
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$

	private final Mac mac;
	
	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}
	
	public HmacGeneratorImpl(final HmacAlgo algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        super();
        mac = Mac.getInstance(algorithm.getAlgorithm(), PROVIDER);
    }
	
	
	/*
	 * Implemented methods
	 */
    @Override
	public byte[] getHmac(final byte[] input, final Key key) throws InvalidKeyException {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }

        mac.init(key);
        mac.update(input);

        return mac.doFinal();
    }
}