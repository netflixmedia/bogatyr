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

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is the skeleton for servers.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.8.0 (20090528)
 * @since 0.7.0
 */
public abstract class ServerAbstract implements IServer, ListenerServerThread {
    private final long createTime = System.currentTimeMillis();

    private Thread thread;
    
	private final Collection<IServerThread> listThread = new HashSet<IServerThread>();

//    private final Map<UUID, IServerThread> mapThread = new ConcurrentHashMap<UUID, IServerThread>();

    private ServerSocket serverSocket;
    private int port;
    private int timeout; //ServerSocketSocket timeout in milliseconds

    private boolean isRunning;

    protected ServerAbstract() {
    	super();
    }

    protected ServerAbstract(final int port, final int timeout) {
        super();

        setPort(port);
        this.timeout = timeout;
    }

    protected ServerAbstract(final int port) {
        this(port, 0);
    }

	/**
     * Returns the instantiation time of the server.
     *
     * @return instantiation time of the server
     * @since 0.7.0
     */
	public long getCreateTime() {
		return createTime;
	}

	/**
	 * Returns the current {@link Thread} of the server.
	 * 
	 * @return thread of the server
	 * @since 0.7.0
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * Sets the current {@link Thread} for the server.
	 * 
	 * @param thread for the server
	 * @since 0.8.0
	 */
    protected void setThread(final Thread thread) {
		this.thread = thread;
	}

	/**
	 * Sets the {@link ServerSocket} for the server.
	 * @param serverSocket for the server
	 * @since 0.8.0
	 */
    protected void setServerSocket(final ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	protected void setRunning(final boolean isRunning) {
		this.isRunning = isRunning;
	}


    /*
     * Overridden methods
     */
    @Override
    public String toString() {
        return HelperObject.toString(this);
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

    public void setPort(final int port) {
    	if (0 >= port || HelperNumber.VALUE_65536 <= port) {
    		throw new IllegalArgumentException("port outside of the valid range (0 - 65535): " + port); //$NON-NLS-1$
    	}
        this.port = port;
    }


    public void setTimeout(final int timeout) {
        this.timeout = timeout;
    }

//    public void addServerThread(final UUID uuid, final IServerThread serverThread) {
//        mapThread.put(uuid, serverThread);
//    }
//
//    public void removeServerThread(final UUID uuid) {
//        mapThread.remove(uuid);
//    }
//
    public Collection<IServerThread> getServerThreads() {
        return Collections.unmodifiableCollection(listThread);
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);

        if (0 < timeout) {
            serverSocket.setSoTimeout(timeout);
        }
        
		thread = new Thread(this);
        thread.start();
        
       	isRunning = true;
    }

    public void stop() throws IOException {
    	isRunning = false;

        if (null != serverSocket) {
        	serverSocket.close();
        }
		
        for (final IServerThread thread : listThread) {
             thread.stop();
        }
        
		if (thread != null) {
			if (thread.isAlive()) {
				thread.interrupt();
			} else {
				thread = null;
			}
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
    
	public void serverThreadStarted(final IServerThread serverThread) {
		listThread.add(serverThread);
	}
	
	public void serverThreadStopped(final IServerThread serverThread) {
		listThread.remove(serverThread);
	}
}
