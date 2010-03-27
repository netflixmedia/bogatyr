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
package ch.customcode.bogatyr.service.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperTime;
import ch.customcode.bogatyr.model.crypto.CryptoAsymmetricAlgo;
import ch.customcode.bogatyr.service.crypto.CertificateProvider;
import ch.customcode.bogatyr.service.crypto.CertificateProviderImpl;
import ch.customcode.bogatyr.service.crypto.CryptoAsymmetric;
import ch.customcode.bogatyr.service.crypto.CryptoAsymmetricImpl;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100215
 */
public class CertificateProviderTest {
	private final CertificateProvider publicKeyProvider = new CertificateProviderImpl();
	
	private KeyPair keyPair;
	
	
	@Before
	public void setUp() throws Exception {
		final CryptoAsymmetric cryptoAsymm = new CryptoAsymmetricImpl(CryptoAsymmetricAlgo.RSA);
        keyPair = cryptoAsymm.generateKeyPair();
	}
	
	@Test
	public void testGenerateCertificate() {
		try {
			final File file = HelperIO.getTemporaryFile("bogatr_ProviderCertificateTest", ".cer");  //$NON-NLS-1$//$NON-NLS-2$

			X509Certificate cert = publicKeyProvider.generateCertificate(keyPair, "CN=ISSUER", "CN=SUBJECT", "laubenberger@gmail.com", new Date(), new Date(System.currentTimeMillis() + HelperTime.MILLISECONDS_PER_WEEK));   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
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
