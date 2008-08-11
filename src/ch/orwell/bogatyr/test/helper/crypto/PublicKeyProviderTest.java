/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.test.helper.crypto;

import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Date;

import junit.framework.TestCase;
import ch.orwell.bogatyr.helper.context.Context;
import ch.orwell.bogatyr.helper.crypto.CryptoAsymm;
import ch.orwell.bogatyr.helper.crypto.PublicKeyProvider;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080803
 */
public class PublicKeyProviderTest extends TestCase {

	public void testGenerateCertificate() {
		KeyPair kp = null;
		try {
			kp = CryptoAsymm.generateKeys(512);
		} catch (Exception ex) {ex.printStackTrace();fail(ex.getMessage());}
		
		try {
			X509Certificate cert = PublicKeyProvider.generateCertificate(kp, "CN=ISSUER", "CN=SUBJECT", "laubenberger@gmail.com", new Date(System.currentTimeMillis() - 50000), new Date(System.currentTimeMillis() + 50000));
			PublicKeyProvider.storeCertificate(cert, new File(Context.getInstance().getApplicationWorkDirectory(), "test.cer"));
			cert = PublicKeyProvider.getCertificate(new File(Context.getInstance().getApplicationWorkDirectory(), "test.cer"));
//			System.out.println(HelperGeneral.toString(cert));
//			System.out.println(cert.getIssuerDN());
//			System.out.println(cert.getSubjectDN());
//			System.out.println(cert.getSubjectAlternativeNames());
			assertEquals(kp.getPublic(), cert.getPublicKey());
		} catch (Exception ex) {fail(ex.getMessage());}
	}
}
