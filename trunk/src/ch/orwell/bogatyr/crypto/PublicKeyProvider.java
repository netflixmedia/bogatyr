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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.crypto;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * The PublicKeyProvider class.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080225
 */
public final class PublicKeyProvider {

	/**
     * Get the Certificate out of the given Certificate File.
     * @param certFile File the certificate File
     * @return X509Certificate the certificate
     * @throws CertificateException e
     * @throws NoSuchProviderException e
     * @throws IOException e
     */
    public static X509Certificate getCertFromFile(File certFile) throws CertificateException, NoSuchProviderException, IOException {
        // open the file stream
        FileInputStream fis = new FileInputStream(certFile);
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Generate the certificte factory
        CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC"); //$NON-NLS-1$ //$NON-NLS-2$

        // get the certifictae
        X509Certificate cert = (X509Certificate)cf.generateCertificate(bis);

        return cert;
    }
}
