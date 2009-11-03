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
 * <r.wuersch@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.crypto;


import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.service.ServiceAbstract;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import javax.security.auth.x500.X500Principal;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;


/**
 * This class generates, reads and save X.509 certificates.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091103)
 * @since 0.3.0
 */
public class CertificateProviderImpl extends ServiceAbstract implements CertificateProvider {
	/*
	 * Implemented methods
	 */
    @Override
    public X509Certificate readCertificate(final File file) throws CertificateException, NoSuchProviderException, IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

		final BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        final X509Certificate cert;

        try {
            cert = readCertificate(new BufferedInputStream(new FileInputStream(file)));
        } finally {
            bis.close();
        }
        return cert;
    }

    @Override
    public X509Certificate readCertificate(final InputStream is) throws CertificateException, NoSuchProviderException, IOException {
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}
		
		final X509Certificate cert;
		
    	try {
            // Generate the certificate factory
            final CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC"); //$NON-NLS-1$ //$NON-NLS-2$

            // get the certificate
            cert = (X509Certificate)cf.generateCertificate(is);
        } finally {
            is.close();
        }
		return cert;
    }
    
    @Override
    public void writeCertificate(final OutputStream os, final Certificate cert) throws CertificateEncodingException, IOException {
		if (null == os) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (null == cert) {
			throw new IllegalArgumentException("cert is null!"); //$NON-NLS-1$
		}
		
		HelperIO.writeStream(os, cert.getEncoded());
    }
    
    @Override
    public void writeCertificate(final File file, final Certificate cert) throws CertificateEncodingException, IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (null == cert) {
			throw new IllegalArgumentException("cert is null!"); //$NON-NLS-1$
		}
		
    	HelperIO.writeFile(file, cert.getEncoded(), false);
    }

    @Override
    public X509Certificate generateCertificate(final KeyPair pair, final String issuerDN, final String subjectDN, final String generalName, final Date start, final Date end) throws NoSuchAlgorithmException, IllegalStateException, CertificateEncodingException, InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException { //$JUnit$
		if (null == pair) {
			throw new IllegalArgumentException("pair is null!"); //$NON-NLS-1$
		}
		if (null == issuerDN) {
			throw new IllegalArgumentException("issuerDN is null!"); //$NON-NLS-1$
		}
		if (null == subjectDN) {
			throw new IllegalArgumentException("subjectDN is null!"); //$NON-NLS-1$
		}
		if (null == generalName) {
			throw new IllegalArgumentException("generalName is null!"); //$NON-NLS-1$
		}
		if (null == start) {
			throw new IllegalArgumentException("start is null!"); //$NON-NLS-1$
		}
		if (null == end) {
			throw new IllegalArgumentException("end is null!"); //$NON-NLS-1$
		}
		
	    // generate the certificate
		final X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
	
	    certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
	    certGen.setIssuerDN(new X500Principal(issuerDN));
	    certGen.setNotBefore(start);
	    certGen.setNotAfter(end);
	    certGen.setSubjectDN(new X500Principal(subjectDN));
	    certGen.setPublicKey(pair.getPublic());
	    certGen.setSignatureAlgorithm("SHA256WithRSAEncryption"); //$NON-NLS-1$
	
	    certGen.addExtension(X509Extensions.BasicConstraints, true, new BasicConstraints(false));
	    certGen.addExtension(X509Extensions.KeyUsage, true, new KeyUsage(KeyUsage.digitalSignature | KeyUsage.keyEncipherment));
	    certGen.addExtension(X509Extensions.ExtendedKeyUsage, true, new ExtendedKeyUsage(KeyPurposeId.id_kp_serverAuth));
	    certGen.addExtension(X509Extensions.SubjectAlternativeName, false, new GeneralNames(new GeneralName(GeneralName.rfc822Name, generalName)));
	
		return certGen.generate(pair.getPrivate(), "BC"); //$NON-NLS-1$
	}													
}
