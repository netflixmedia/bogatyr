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
package ch.sisprocom.bogatyr.controller.net.server;

import ch.sisprocom.bogatyr.helper.HelperObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This is the skeleton for servers.
 *
 * @author Stefan Laubenberger
 * @version 20090522
 */
public abstract class ServerAbstract implements IServer {
    private final long createTime = System.currentTimeMillis();

    private final Map<UUID, ThreadServerAbstract> mapThread = new ConcurrentHashMap<UUID, ThreadServerAbstract>();

    private ServerSocket serverSocket;
    private int port;
    private int timeout; //ServerSocketSocket timeout in milliseconds

    private boolean isStopped = true;


    protected ServerAbstract(final int port, final int timeout) {
        super();

        setPort(port);
        this.timeout = timeout;
    }

    protected ServerAbstract(final int port) {
        this(port, 0); //TODO set default parameters
    }

	/**
     * Returns the instantiation time of the server.
     *
     * @return instantiation time of the controller
     */
	public long getCreateTime() {
		return createTime;
	}


    /*
      * Implemented methods
      */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public int getPort() {
        return port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setServerSocket(final ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setPort(final int port) {
    	if (0 >= port || 65535 < port) {
    		throw new IllegalArgumentException("port outside of the valid range (0 - 65535): " + port); //$NON-NLS-1$
    	}
        this.port = port;
    }


    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }

    public void addServerThread(final UUID uuid, final ThreadServerAbstract serverThread) {
        mapThread.put(uuid, serverThread);
    }

    public void removeServerThread(final UUID uuid) {
        mapThread.remove(uuid);
    }

    public Map<UUID, ThreadServerAbstract> getServerThreads() {
        return Collections.unmodifiableMap(mapThread);
    }

    public void start() throws IOException {
        isStopped = false;

        serverSocket = new ServerSocket(port);

        if (0 < timeout) {
            serverSocket.setSoTimeout(timeout);
        }
    }

    public void stop() throws IOException {
        isStopped = true;

        for (final UUID key : getServerThreads().keySet()) {
            final ThreadServerAbstract thread = getServerThreads().get(key);

            thread.stop();
        }
        serverSocket.close();
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
