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
import java.net.UnknownHostException;
import java.util.Date;


/**
 * This is a helper class for time operations.
 * 
 * @author Stefan Laubenberger
 * @version 20090505
 */
public abstract class HelperTime {
	public static final String DEFAULT_TIME_SERVER = "ptbtime1.ptb.de"; //$NON-NLS-1$
	
	public static Date getAtomicTime() throws UnknownHostException, IOException {
		return getAtomicTime(DEFAULT_TIME_SERVER);
	}

	public static Date getAtomicTime(final String timeServer) throws UnknownHostException, IOException {
		final long SECONDS_1900_1970 = 2208988800L;
		
		Socket socket = null;
		InputStream is = null;
		long time = 0;

		try {
			socket = new Socket(timeServer, 37);
			is = socket.getInputStream();

			for (int ii = 3; ii >= 0; ii-- ) {
				time ^= (long) is.read() << ii * 8;
			}

			return new Date((time - SECONDS_1900_1970) * 1000);
		} finally {
			if (is != null) {
				is.close();
			}
			
			if (socket != null) 
				socket.close();
			}
		}
	}
