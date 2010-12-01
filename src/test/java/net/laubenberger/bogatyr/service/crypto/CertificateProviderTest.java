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

package net.laubenberger.bogatyr.service.crypto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Date;

import net.laubenberger.bogatyr.helper.HelperIO;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.helper.HelperTime;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeBefore;
import net.laubenberger.bogatyr.model.crypto.CryptoAsymmetricAlgo;

import org.junit.Before;
import org.junit.Test;


/**
 * JUnit test for {@link CertificateProviderImpl}
 *
 * @author Stefan Laubenberger
 * @version 20101202
 */
public class CertificateProviderTest {
	private static final String CN_ISSUER = "CN=ISSUER"; //$NON-NLS-1$
	private static final String CN_SUBJECT = "CN=SUBJECT"; //$NON-NLS-1$
	private static final String GENERAL_NAME = "laubenberger@gmail.com"; //$NON-NLS-1$
	private static final Date DATE_START = new Date();
	private static final Date DATE_END = new Date(System.currentTimeMillis() + HelperTime.MILLISECONDS_PER_WEEK);
	
	private final CertificateProvider publicKeyProvider = new CertificateProviderImpl();

	private KeyPair keyPair;

	@Before
	public void setUp() throws Exception {
		final CryptoAsymmetric cryptoAsymm = new CryptoAsymmetricImpl(CryptoAsymmetricAlgo.RSA);
		keyPair = cryptoAsymm.generateKeyPair();
	}
	
	@Test
	public void testReadCertificate() {
		X509Certificate cert_original = null;
		File file = null;
		
		try {
			file = HelperIO.getTemporaryFile();

			cert_original = publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, GENERAL_NAME, DATE_START, DATE_END);
			publicKeyProvider.writeCertificate(file, cert_original);
			final X509Certificate cert_new = publicKeyProvider.readCertificate(file);

			assertEquals(cert_original, cert_new);
			assertEquals(keyPair.getPublic(), cert_new.getPublicKey());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.readCertificate((File)null);
			fail("file is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.readCertificate((InputStream)null);
			fail("is is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testWriteCertificate() {
		X509Certificate cert_original = null;
		File file = null;
		
		try {
			file = HelperIO.getTemporaryFile();

			cert_original = publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, GENERAL_NAME, DATE_START, DATE_END);
			publicKeyProvider.writeCertificate(file, cert_original);
			final X509Certificate cert_new = publicKeyProvider.readCertificate(file);

			assertEquals(cert_original, cert_new);
			assertEquals(keyPair.getPublic(), cert_new.getPublicKey());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.writeCertificate((File)null, cert_original);
			fail("file is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.writeCertificate(file, null);
			fail("cert is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.writeCertificate((OutputStream)null, cert_original);
			fail("os is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.writeCertificate(new FileOutputStream(file), null);
			fail("cert is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void testGenerateCertificate() {
		try {
			final File file = HelperIO.getTemporaryFile("bogatr_ProviderCertificateTest", ".cer");  //$NON-NLS-1$//$NON-NLS-2$

			final X509Certificate cert_original = publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, GENERAL_NAME, DATE_START, DATE_END);
			publicKeyProvider.writeCertificate(file, cert_original);
			final X509Certificate cert_new = publicKeyProvider.readCertificate(file);

			assertEquals(cert_original, cert_new);
			assertEquals(keyPair.getPublic(), cert_new.getPublicKey());
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.generateCertificate(null, CN_ISSUER, CN_SUBJECT, GENERAL_NAME, DATE_START, DATE_END);
			fail("pair is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.generateCertificate(keyPair, null, CN_SUBJECT, GENERAL_NAME, DATE_START, DATE_END);
			fail("issuerDN is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			publicKeyProvider.generateCertificate(keyPair, HelperString.EMPTY_STRING, CN_SUBJECT, GENERAL_NAME, DATE_START, DATE_END);
			fail("issuerDN is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, null, GENERAL_NAME, DATE_START, DATE_END);
			fail("subjectDN is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, HelperString.EMPTY_STRING, GENERAL_NAME, DATE_START, DATE_END);
			fail("subjectDN is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, null, DATE_START, DATE_END);
			fail("generalName is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	
		try {
			publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, HelperString.EMPTY_STRING, DATE_START, DATE_END);
			fail("generalName is empty"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsEmpty ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
		
		try {
			publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, GENERAL_NAME, null, DATE_END);
			fail("start is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, GENERAL_NAME, DATE_START, null);
			fail("end is null"); //$NON-NLS-1$
		} catch (RuntimeExceptionIsNull ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}

		try {
			publicKeyProvider.generateCertificate(keyPair, CN_ISSUER, CN_SUBJECT, GENERAL_NAME, DATE_END, DATE_START);
			fail("start is after end"); //$NON-NLS-1$
		} catch (RuntimeExceptionMustBeBefore ex) {
			//nothing to do
		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
