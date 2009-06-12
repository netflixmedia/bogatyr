/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.test.helper.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperTime;
import ch.sisprocom.bogatyr.helper.crypto.CryptoRSA;
import ch.sisprocom.bogatyr.helper.crypto.ICryptoAsymmetric;
import ch.sisprocom.bogatyr.helper.crypto.IProviderCertificate;
import ch.sisprocom.bogatyr.helper.crypto.ProviderCertificate;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090610
 */
public class ProviderCertificateTest {
	private final IProviderCertificate publicKeyProvider = new ProviderCertificate();
	
	private KeyPair keyPair;
	
	
	@Before
	public void setUp() throws Exception {
		final ICryptoAsymmetric cryptoAsymm = new CryptoRSA();
        keyPair = cryptoAsymm.generateKeyPair();
	}
	
	@Test
	public void testGenerateCertificate() {
		try {
			final File file = HelperIO.getTemporaryFile("bogatr_ProviderCertificateTest", ".cer");  //$NON-NLS-1$//$NON-NLS-2$

			X509Certificate cert = publicKeyProvider.generateCertificate(keyPair, "CN=ISSUER", "CN=SUBJECT", "laubenberger@gmail.com", new Date(), new Date(System.currentTimeMillis() + HelperTime.MILLISECONDS_WEEK));   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
			publicKeyProvider.writeCertificate(file, cert);
			cert = publicKeyProvider.readCertificate(file);
//			System.out.println(HelperGeneral.toString(cert));
//			System.out.println(cert.getIssuerDN());
//			System.out.println(cert.getSubjectDN());
//			System.out.println(cert.getSubjectAlternativeNames());
			assertEquals(keyPair.getPublic(), cert.getPublicKey());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}