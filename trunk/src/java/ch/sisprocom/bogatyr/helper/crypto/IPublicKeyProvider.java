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
package ch.sisprocom.bogatyr.helper.crypto;

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
 * @version 20090508
 */
public interface IPublicKeyProvider {
	/**
     * Get the {@link X509Certificate} out of the given certificate {@link File}.
     * 
     * @param file containing the certificate
     * @return certificate
     * @throws Exception
     */
    X509Certificate getCertificate(final File file) throws Exception;
    
    /**
     * Get the {@link X509Certificate} out of the given certificate {@link InputStream}.
     * 
     * @param is input stream containing the certificate
     * @return certificate
     * @throws Exception
     */
    X509Certificate getCertificate(final InputStream is) throws Exception;
    
    /**
     * Store the {@link Certificate} on a {@link OutputStream}.
     * 
     * @param cert certificate
     * @param os output stream for the certificate
     * @throws Exception 
     */
    void storeCertificate(final Certificate cert, final OutputStream os) throws Exception;
    
    /**
     * Store the {@link Certificate} in a {@link File}.
     * 
     * @param cert certificate
     * @param file for the certificate
     * @throws Exception 
     */
    void storeCertificate(final Certificate cert, final File file) throws Exception;
    
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
     */
    X509Certificate generateCertificate(final KeyPair pair, final String issuerDN, final String subjectDN, final String generalName, final Date start, final Date end) throws Exception; 
}
