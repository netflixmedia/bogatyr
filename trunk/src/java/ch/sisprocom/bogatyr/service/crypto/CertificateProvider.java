/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.service.crypto;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Date;


/**
 * This interface is to generate, read and save X.509 certificates.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091019)
 * @since 0.6.0
 */
public interface CertificateProvider { //$Example$
	/**
     * Reads the {@link X509Certificate} out of the given certificate {@link File}.
     * 
     * @param file containing the certificate
     * @return certificate
     * @throws Exception
     * @see File
     * @see X509Certificate
     * @since 0.6.0
     */
    X509Certificate readCertificate(File file) throws Exception;
    
    /**
     * Reads the {@link X509Certificate} out of the given certificate {@link InputStream}.
     * 
     * @param is input stream containing the certificate
     * @return certificate
     * @throws Exception
     * @see InputStream
     * @see X509Certificate
     * @since 0.6.0
     */
    X509Certificate readCertificate(InputStream is) throws Exception;
    
    /**
     * Writes the {@link Certificate} on a {@link OutputStream}.
     * 
     * @param os output stream for the certificate
     * @param cert certificate
     * @throws Exception
     * @see OutputStream
     * @see Certificate 
     * @since 0.6.0
     */
    void writeCertificate(OutputStream os, Certificate cert) throws Exception;
    
    /**
     * Writes the {@link Certificate} in a {@link File}.
     * 
     * @param file for the certificate
     * @param cert certificate
     * @throws Exception 
     * @see File
     * @see Certificate 
     * @since 0.6.0
     */
    void writeCertificate(File file, Certificate cert) throws Exception;
    
    /**
     * Generate a {@link X509Certificate} out of the given {@link KeyPair} and parameters.
     * 
     * @param pair the keypair for the certificate
     * @param issuerDN e.g. "CN=Test Certificate"
     * @param subjectDN e.g. "CN=Test Certificate"
     * @param generalName of the certificate owner (e.g. yourmail@gmail.com)
     * @param start date of the certificate
     * @param end date of the certificate
     * @return certificate
     * @throws Exception
     * @since 0.6.0 
     */
    X509Certificate generateCertificate(KeyPair pair, String issuerDN, String subjectDN, String generalName, Date start, Date end) throws Exception;
}
