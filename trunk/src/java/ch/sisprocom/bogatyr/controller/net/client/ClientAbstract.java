/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.controller.net.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperIO;

/**
 * This is the skeleton for clients.
 * 
 * @author Stefan Laubenberger
 * @version 20090521
 */
public abstract class ClientAbstract { //TODO document in Wiki!
	final private String host;
	final private int port;
    private Socket socket;
	

	protected ClientAbstract(final String host, final int port) {
		super();

		this.host = host;
		this.port = port;
	}
	

	/**
     * Open a stream.
     * 
     * @throws IOException
     */
	public void start() throws IOException {
		System.out.println("Client started");
		socket = new Socket(host, port);
	}
	
	/**
     * Close a stream.
     * 
     * @throws IOException
     */
	public void stop() throws IOException {
		System.out.println("Client stopped");
		socket.close();
	}

	/**
     * Reads a socket-stream.
     * 
     * @return byte-array
	 * @throws IOException 
     */
	public byte[] readStream() throws IOException {
		System.out.println("start readStream");
		
		byte[] temp = null;
		
		InputStream in = socket.getInputStream();
		byte b;
		do {
			b = (byte) in.read();
			  
			  
			  if (b != -1) {
				  temp = HelperArray.concatenate(temp, new byte[]{b});
			    System.out.println("The next byte is " + b);
			  }
			} while (b != -1);

			System.out.println("end readStream");

			if (null == temp) { //client lost
				stop();
			}
			return temp;
    }

    /**
     * Writes on a socket-stream.
     * 
     * @param data a byte-array
     * @throws Exception 
     */
    public void writeStream(final byte[] data) throws IOException {
    	System.out.println("writeStream: " + new String(data));
    	HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{-1}));
	}
}
