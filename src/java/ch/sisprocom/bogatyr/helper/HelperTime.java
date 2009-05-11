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
package ch.sisprocom.bogatyr.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Date;


/**
 * This is a helper class for time operations.
 * 
 * @author Stefan Laubenberger
 * @version 20090511
 */
public abstract class HelperTime {
    public static final int TIME_SERVER_PORT = 37;
	public static final String DEFAULT_TIME_SERVER = "ptbtime1.ptb.de"; //$NON-NLS-1$
	
    /**
     * Returns the current atomic time of the default time server.
     *
     * @return atomic time of the default time server
     * @throws IOException
     */
	public static Date getAtomicTime() throws IOException {
		return getAtomicTime(DEFAULT_TIME_SERVER);
	}

    /**
     * Returns the current atomic time of the given time server.
     *
     * @param host time server
     * @return atomic time of the given time server
     * @throws IOException
     */
	public static Date getAtomicTime(final String host) throws IOException {
		if (!HelperGeneral.isValid(host)) {
			throw new IllegalArgumentException("host is null or empty!"); //$NON-NLS-1$
		}
		
		Socket socket = null;
		InputStream is = null;

		try {
			socket = new Socket(host, TIME_SERVER_PORT);
			is = socket.getInputStream();

            long time = 0L;

            for (int ii = 3; 0 <= ii; ii-- ) {
				time ^= (long) is.read() << ii * 8;
			}

			return new Date((time - Const.SECONDS_1900_1970) * 1000L);
		} finally {
			if (is != null) {
				is.close();
			}
			
			if (socket != null) {
                socket.close();
            }
        }
    }
}
