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

import ch.sisprocom.bogatyr.helper.HelperArray;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * This is the skeleton for clients.
 *
 * @author Stefan Laubenberger
 * @version 20090522
 */
public abstract class ClientAbstract implements IClient {
    private final long createTime = System.currentTimeMillis();

    private String host;
    private int port;
    private Socket socket;

    private boolean isStopped = true;


    protected ClientAbstract(final String host, final int port) {
        super();

        this.host = host;
        this.port = port;
    }


	/**
     * Returns the instantiation time of the client.
     *
     * @return instantiation time of the controller
     */
	public long getCreateTime() {
		return createTime;
	}


    /*
      * Implemented methods
      */
    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public void start() throws IOException {
        isStopped = false;
        socket = new Socket(host, port);
    }

    public void stop() throws IOException {
        isStopped = true;
        socket.close();
    }

    public byte[] readStream() throws IOException {
    	byte[] result = null;
    	
    	if (!socket.isClosed()) {
	    	System.out.println(socket.isClosed());
	        System.out.println(socket.isBound());
	    	final InputStream is = socket.getInputStream();
	        byte input;
	
	        do {
	            input = (byte) is.read();
	
	            if (-1 != input) {
	                result = HelperArray.concatenate(result, new byte[]{input});
	            }
	        } while (-1 != input);
	
	        if (null == result) { //server lost
	            stop();
	        }
        } else {
        	stop();
        }
        return result;
    }

    public void writeStream(final byte[] data) throws IOException {
        HelperIO.writeStream(socket.getOutputStream(), HelperArray.concatenate(data, new byte[]{(byte) -1}));
    }

    public boolean isStopped() {
        return isStopped;
    }


    /*
      * Overridden methods
      */
    @Override
    public String toString() {
        return HelperObject.toString(this);
    }
}
