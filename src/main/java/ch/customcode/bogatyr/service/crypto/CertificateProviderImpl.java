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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.service.crypto;


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

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.ExtendedKeyUsage;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.KeyPurposeId;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * This class generates, reads and writes X.509 certificates.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100413)
 * @since 0.3.0
 */
public class CertificateProviderImpl extends ServiceAbstract implements CertificateProvider {
	private static final Logger log = LoggerFactory.getLogger(CertificateProviderImpl.class);
	
	private static final String PROVIDER = "BC"; //BouncyCastle //$NON-NLS-1$
	
	
	public CertificateProviderImpl() {
        super();
        log.trace(HelperLog.constructor());
    }

	
	/*
	 * Implemented methods
	 */
    @Override
    public X509Certificate readCertificate(final File file) throws CertificateException, NoSuchProviderException, IOException { //$JUnit$
    	log.debug(HelperLog.methodStart(file));
    	if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		BufferedInputStream bis = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(file));
          
    		final X509Certificate result = readCertificate(new BufferedInputStream(new FileInputStream(file)));
    		
    		log.debug(HelperLog.methodExit(result));
    		return result;
        } finally {
            if (null != bis) {
                bis.close();
            }
        }
    }

    @Override
    public X509Certificate readCertificate(final InputStream is) throws CertificateException, NoSuchProviderException, IOException {
    	log.debug(HelperLog.methodStart(is));
    	if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		
    	try {
            // Generate the certificate factory
            final CertificateFactory cf = CertificateFactory.getInstance("X.509", PROVIDER); //$NON-NLS-1$

            // get the certificate
    		final X509Certificate result = (X509Certificate)cf.generateCertificate(is);
    		
    		log.debug(HelperLog.methodExit(result));
    		return result;
        } finally {
            is.close();
        }
    }
    
    @Override
    public void writeCertificate(final OutputStream os, final Certificate cert) throws CertificateEncodingException, IOException {
    	log.debug(HelperLog.methodStart(os, cert));
    	if (null == os) {
			throw new RuntimeExceptionIsNull("os"); //$NON-NLS-1$
		}
		if (null == cert) {
			throw new RuntimeExceptionIsNull("cert"); //$NON-NLS-1$
		}
		
		HelperIO.writeStream(os, cert.getEncoded());
		
		log.debug(HelperLog.methodExit());
    }
    
    @Override
    public void writeCertificate(final File file, final Certificate cert) throws CertificateEncodingException, IOException { //$JUnit$
    	log.debug(HelperLog.methodStart(file, cert));
    	if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == cert) {
			throw new RuntimeExceptionIsNull("cert"); //$NON-NLS-1$
		}
		
    	HelperIO.writeFile(file, cert.getEncoded(), false);
    }

    @Override
    public X509Certificate generateCertificate(final KeyPair pair, final String issuerDN, final String subjectDN, final String generalName, final Date start, final Date end) throws NoSuchAlgorithmException, IllegalStateException, CertificateEncodingException, InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException { //$JUnit$
		if (null == pair) {
			throw new RuntimeExceptionIsNull("pair"); //$NON-NLS-1$
		}
		if (null == issuerDN) {
			throw new RuntimeExceptionIsNull("issuerDN"); //$NON-NLS-1$
		}
		if (null == subjectDN) {
			throw new RuntimeExceptionIsNull("subjectDN"); //$NON-NLS-1$
		}
		if (null == generalName) {
			throw new RuntimeExceptionIsNull("generalName"); //$NON-NLS-1$
		}
		if (null == start) {
			throw new RuntimeExceptionIsNull("start"); //$NON-NLS-1$
		}
		if (null == end) {
			throw new RuntimeExceptionIsNull("end"); //$NON-NLS-1$
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
	
		return certGen.generate(pair.getPrivate(), PROVIDER);
	}													
}
