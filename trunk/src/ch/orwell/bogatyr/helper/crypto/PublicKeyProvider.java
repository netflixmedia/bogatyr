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
package ch.orwell.bogatyr.helper.crypto;


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

import ch.orwell.bogatyr.helper.HelperIO;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * The PublicKeyProvider class
 *
 * @author Stefan Laubenberger
 * @version 2008080910
 */
public abstract class PublicKeyProvider {
	/**
     * Get the certificate out of the given certificate file
     * 
     * @param file containing the certificate
     * @return X509Certificate the certificate
     * @throws CertificateException e
     * @throws NoSuchProviderException e
     * @throws IOException e
     */
    public static X509Certificate getCertificate(final File file) throws CertificateException, NoSuchProviderException, IOException {
		Logger.getInstance().writeMethodEntry(PublicKeyProvider.class, "getCertificate", file);  //$NON-NLS-1$

        final FileInputStream fis = new FileInputStream(file);
        final X509Certificate cert = getCertificate(new BufferedInputStream(fis));
        	
		Logger.getInstance().writeMethodExit(PublicKeyProvider.class, "getCertificate", cert);  //$NON-NLS-1$
        return cert;
    }

    /**
     * Get the certificate out of the given certificate stream
     * 
     * @param file containing the certificate
     * @return X509Certificate the certificate
     * @throws CertificateException e
     * @throws NoSuchProviderException e
     * @throws IOException e
     */
    public static X509Certificate getCertificate(final InputStream is) throws CertificateException, NoSuchProviderException, IOException {    
		Logger.getInstance().writeMethodEntry(PublicKeyProvider.class, "getCertificate", is);  //$NON-NLS-1$

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
		Logger.getInstance().writeMethodExit(PublicKeyProvider.class, "getCertificate", cert);  //$NON-NLS-1$
		return cert;
    }
    
    /**
     * Store the certificate on a stream
     * 
     * @param cert certificate
     * @param file for the certificate
     * @throws IOException 
     * @throws CertificateEncodingException 
     */
    public static void storeCertificate(final X509Certificate cert, final OutputStream os) throws CertificateEncodingException, IOException {    
		Logger.getInstance().writeMethodEntry(PublicKeyProvider.class, "storeCertificate", new Object[]{cert, os});  //$NON-NLS-1$

    	HelperIO.writeStream(os, cert.getEncoded());

    	Logger.getInstance().writeMethodExit(PublicKeyProvider.class, "storeCertificate");  //$NON-NLS-1$
    }
    
    /**
     * Store the certificate in a file
     * 
     * @param cert certificate
     * @param file for the certificate
     * @throws IOException 
     * @throws CertificateEncodingException 
     */
    public static void storeCertificate(final X509Certificate cert, final File file) throws CertificateEncodingException, IOException {    
		Logger.getInstance().writeMethodEntry(PublicKeyProvider.class, "storeCertificate", new Object[]{cert, file});  //$NON-NLS-1$

		HelperIO.writeFileAsBinary(file, cert.getEncoded(), false);

		Logger.getInstance().writeMethodExit(PublicKeyProvider.class, "storeCertificate");  //$NON-NLS-1$
    }

    /**
     * Generate a public key certificate out of the given keypair
     * 
     * @param pair the keypair for the certificate
     * @param issuerDN (e.g. "CN=Test Certificate")
     * @param subjectDN (e.g. "CN=Test Certificate")
     * @param generalName of he certificate owner (e.g. laubenberger@gmail.com)
     * @param start date of the certificate
     * @param end date of the certificate
     * @return X509Certificate the certificate
     * @throws SignatureException 
     * @throws SecurityException 
     * @throws NoSuchProviderException 
     * @throws InvalidKeyException 
     */
    public static X509Certificate generateCertificate(KeyPair pair, String issuerDN, String subjectDN, String generalName, Date start, Date end) throws InvalidKeyException, NoSuchProviderException, SecurityException, SignatureException {
		Logger.getInstance().writeMethodEntry(PublicKeyProvider.class, "generateCertificate", new Object[]{pair, issuerDN, subjectDN, generalName, start, end});  //$NON-NLS-1$

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
	
	    final X509Certificate cert = certGen.generateX509Certificate(pair.getPrivate(), "BC");  //$NON-NLS-1$
	    
		Logger.getInstance().writeMethodExit(PublicKeyProvider.class, "generateCertificate", cert);  //$NON-NLS-1$
	    return cert;
	}													
}
