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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 *
 *******************************************************************************/
package ch.sisprocom.bogatyr.controller.net.client;

import javax.net.ssl.SSLSocketFactory;import javax.net.SocketFactory;
import java.io.IOException;

/**
 * This is the skeleton for SSL secured clients.
 *
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091016)
 * @since 0.8.0
 */
public abstract class ClientSSLAbstract extends ClientAbstract {
    protected ClientSSLAbstract() {
    	super();
    }

    protected ClientSSLAbstract(final String host, final int port) {
        super(host, port);
    }

    
    /*
     * Overridden methods
     */
    @Override
    public void start() throws IOException {
        final SocketFactory sslFactory = SSLSocketFactory.getDefault();

        setSocket(sslFactory.createSocket(getHost(), getPort()));

		setThread(new Thread(this));
        getThread().start();
        
        fireStarted();
    }
}
