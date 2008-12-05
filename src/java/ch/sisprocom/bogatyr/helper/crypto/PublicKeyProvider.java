/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.crypto;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
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

import ch.sisprocom.bogatyr.helper.HelperIO;


/**
 * This class generates, reads and save X.509 certificates.
 *
 * @author Stefan Laubenberger
 * @version 20081205
 */
public abstract class PublicKeyProvider {
	/**
     * Get the certificate out of the given certificate file.
     * 
     * @param file containing the certificate
     * @return X509Certificate the certificate
     * @throws CertificateException e
     * @throws NoSuchProviderException e
     * @throws IOException e
     */
    public static X509Certificate getCertificate(final File file) throws CertificateException, NoSuchProviderException, IOException {
        final FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = null;
        final X509Certificate cert;

        try {
            bis = new BufferedInputStream(fis);
            cert = getCertificate(bis);
        } finally {
            if (bis != null) {
                bis.close();
            }
        }
        return cert;
    }

    /**
     * Get the certificate out of the given certificate stream.
     * 
     * @param is input stream containing the certificate
     * @return X509Certificate the certificate
     * @throws CertificateException e
     * @throws NoSuchProviderException e
     * @throws IOException e
     */
    public static X509Certificate getCertificate(final InputStream is) throws CertificateException, NoSuchProviderException, IOException {    
		final X509Certificate cert;
		
    	try {
            // Generate the certificate factory
            final CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC"); //$NON-NLS-1$ //$NON-NLS-2$

            // get the certificate
            cert = (X509Certificate)cf.generateCertificate(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
		return cert;
    }
    
    /**
     * Store the certificate on a stream.
     * 
     * @param cert certificate
     * @param os output stream for the certificate
     * @throws IOException 
     * @throws CertificateEncodingException 
     */
    public static void storeCertificate(final Certificate cert, final OutputStream os) throws CertificateEncodingException, IOException {
    	HelperIO.writeStream(os, cert.getEncoded());
    }
    
    /**
     * Store the certificate in a file.
     * 
     * @param cert certificate
     * @param file for the certificate
     * @throws IOException 
     * @throws CertificateEncodingException 
     */
    public static void storeCertificate(final Certificate cert, final File file) throws CertificateEncodingException, IOException {
		HelperIO.writeFileFromBinary(file, cert.getEncoded(), false);
    }

    /**
     * Generate a public key certificate out of the given keypair.
     * 
     * @param pair the keypair for the certificate
     * @param issuerDN (e.g. "CN=Test Certificate")
     * @param subjectDN (e.g. "CN=Test Certificate")
     * @param generalName of the certificate owner (e.g. laubenberger@gmail.com)
     * @param start date of the certificate
     * @param end date of the certificate
     * @return X509Certificate the certificate
     * @throws SignatureException 
     * @throws SecurityException 
     * @throws NoSuchProviderException 
     * @throws InvalidKeyException 
     */
    public static X509Certificate generateCertificate(KeyPair pair, String issuerDN, String subjectDN, String generalName, Date start, Date end) throws InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException {
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
	
	    return certGen.generateX509Certificate(pair.getPrivate(), "BC");  //$NON-NLS-1$
	}													
}
