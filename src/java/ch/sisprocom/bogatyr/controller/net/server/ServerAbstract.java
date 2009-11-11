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

import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperObject;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;


/**
 * This is the skeleton for servers.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20091111)
 * @since 0.7.0
 */
public abstract class ServerAbstract implements Server, ListenerServerThread {
    private final long createTime = System.currentTimeMillis();

    private Thread thread;
    
	private final Collection<ServerThread> listThread = new HashSet<ServerThread>();

//    private final Map<UUID, ServerThread> mapThread = new ConcurrentHashMap<UUID, ServerThread>();

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
        setTimeout(timeout);
    }

    protected ServerAbstract(final int port) {
        this(port, 0);
    }

	/**
	 * Returns the current {@link Thread} of the server.
	 * 
	 * @return thread of the server
	 * @see Thread
	 * @since 0.7.0
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * Sets the current {@link Thread} for the server.
	 * 
	 * @param thread for the server
	 * @see Thread
	 * @since 0.8.0
	 */
    protected void setThread(final Thread thread) {
    	if (null == thread) {
    		throw new IllegalArgumentException("thread is null!"); //$NON-NLS-1$
    	}

		this.thread = thread;
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
    @Override
	public long getCreateTime() {
		return createTime;
	}

    @Override
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

    @Override
    public void setPort(final int port) {
    	if (0 >= port || HelperNumber.VALUE_65536 <= port) {
    		throw new IllegalArgumentException("port outside of the valid range (0 - 65535): " + port); //$NON-NLS-1$
    	}
        this.port = port;
    }

    @Override
    public void setServerSocket(final ServerSocket serverSocket) {
    	if (null == serverSocket) {
    		throw new IllegalArgumentException("serverSocket is null!"); //$NON-NLS-1$
    	}

		this.serverSocket = serverSocket;
	}

    @Override
    public void setTimeout(final int timeout) {
		if (0 > timeout) {
			throw new IllegalArgumentException("timeout must be positive: " + timeout); //$NON-NLS-1$
		}

        this.timeout = timeout;
    }

//    public void addServerThread(final UUID uuid, final ServerThread serverThread) {
//        mapThread.put(uuid, serverThread);
//    }
//
//    public void removeServerThread(final UUID uuid) {
//        mapThread.remove(uuid);
//    }
//
    @Override
    public Collection<ServerThread> getServerThreads() {
        return Collections.unmodifiableCollection(listThread);
    }

    @Override
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);

        if (0 < timeout) {
            serverSocket.setSoTimeout(timeout);
        }
        
		thread = new Thread(this);
        thread.start();
        
       	isRunning = true;
    }

    @Override
    public void stop() throws IOException {
    	isRunning = false;

        if (null != serverSocket) {
        	serverSocket.close();
        }
		
        for (final ServerThread thread : listThread) {
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

    @Override
    public boolean isRunning() {
        return isRunning;
    }
    
	@Override
    public void serverThreadStarted(final ServerThread serverThread) {
    	if (null == serverThread) {
    		throw new IllegalArgumentException("serverThread is null!"); //$NON-NLS-1$
    	}

		listThread.add(serverThread);
	}
	
	@Override
    public void serverThreadStopped(final ServerThread serverThread) {
    	if (null == serverThread) {
    		throw new IllegalArgumentException("serverThread is null!"); //$NON-NLS-1$
    	}

    	listThread.remove(serverThread);
	}
}
