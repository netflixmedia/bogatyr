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

import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.crypto.CryptoAsymm;
import ch.sisprocom.bogatyr.helper.crypto.IPublicKeyProvider;
import ch.sisprocom.bogatyr.helper.crypto.PublicKeyProvider;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090310
 */
public class PublicKeyProviderTest {
	private IPublicKeyProvider publicKeyProvider = new PublicKeyProvider();
	
	@Test
	public void testGenerateCertificate() {
		KeyPair kp = null;
		try {
			kp = new CryptoAsymm().generateKeys(512);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
		
		try {
			X509Certificate cert = publicKeyProvider.generateCertificate(kp, "CN=ISSUER", "CN=SUBJECT", "laubenberger@gmail.com", new Date(System.currentTimeMillis() - 50000), new Date(System.currentTimeMillis() + 50000));
			publicKeyProvider.storeCertificate(cert, new File(HelperEnvInfo.getOsTempDirectory(), "test.cer"));
			cert = publicKeyProvider.getCertificate(new File(HelperEnvInfo.getOsTempDirectory(), "test.cer"));
//			System.out.println(HelperGeneral.toString(cert));
//			System.out.println(cert.getIssuerDN());
//			System.out.println(cert.getSubjectDN());
//			System.out.println(cert.getSubjectAlternativeNames());
			assertEquals(kp.getPublic(), cert.getPublicKey());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}
