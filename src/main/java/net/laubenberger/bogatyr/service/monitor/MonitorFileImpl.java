/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.service.monitor;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.model.crypto.HashCodeAlgo;
import net.laubenberger.bogatyr.service.ServiceAbstract;
import net.laubenberger.bogatyr.service.crypto.HashCodeGenerator;
import net.laubenberger.bogatyr.service.crypto.HashCodeGeneratorImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Monitor implementation for files.
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101102)
 * @since 0.9.4
 */
public class MonitorFileImpl extends ServiceAbstract implements MonitorFile {
	private static final Logger log = LoggerFactory.getLogger(MonitorFileImpl.class);

	private static final long DEFAULT_INTERVAL = 2000L;

	private final Collection<ListenerFileChanged> listeners = new HashSet<ListenerFileChanged>();

	private final Event<MonitorFile> event = new Event<MonitorFile>(this);

	final File file;
	private Timer timer = new Timer();

	final HashCodeGenerator hcg;
	
	private boolean isRunning = false;
	
	
	public MonitorFileImpl(final File file) throws NoSuchAlgorithmException, NoSuchProviderException {
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(file));
		
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		this.file = file;
		hcg = new HashCodeGeneratorImpl(HashCodeAlgo.SHA256);
	}

	
	/*
	 * Private methods
	 */

	protected void fireFileChanged() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		for (final ListenerFileChanged listener : listeners) {
			listener.fileChanged(event);
		}

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	protected void fireFileNotFound() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		for (final ListenerFileChanged listener : listeners) {
			listener.fileNotFound(event);
		}

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}	
	
	protected void fireStarted() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		isRunning = true;

		for (final ListenerFileChanged listener : listeners) {
			listener.monitorStarted(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	protected void fireStopped() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());
		
		isRunning = false;

		for (final ListenerFileChanged listener : listeners) {
			listener.monitorStopped(event);
		}
		
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}	
	
	/*
	 * Implemented methods
	 */
	
	@Override
	public void start(long interval) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(interval));
		if (0L > interval) {
			throw new RuntimeExceptionMustBeGreater("interval", interval, 0); //$NON-NLS-1$
		}
		
		if (isRunning()) {
			timer.cancel();
		}
		
		timer = new Timer();

		final FileMonitorTask task = new FileMonitorTask();
		timer.schedule(task, 0, interval);

		fireStarted();
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void start() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		start(DEFAULT_INTERVAL);
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());	
	}

	@Override
	public void stop() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (isRunning) {
			timer.cancel();
		}
		fireStopped();
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	@Override
	public boolean isRunning() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(isRunning));
		return isRunning;
	}

	@Override
	public File getFile() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(file));
		return file;
	}

	@Override
	public void addListener(ListenerFileChanged listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.add(listener);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void deleteListener(ListenerFileChanged listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.remove(listener);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	
	/*
	 * Inner classes
	 */
	
	private class FileMonitorTask extends TimerTask {
		byte[] hash;
		
		public FileMonitorTask() {
			try {
				hash = hcg.getFastHash(file);
			} catch (IOException e) {
//				e.printStackTrace();
//				fireFileNotFound();
			}
		}

		@Override
		public void run() {
			try {
				byte[] hash = hcg.getFastHash(file);
				
				if (!Arrays.equals(this.hash, hash)) {
					this.hash = hash;
					fireFileChanged();
				}
			} catch (IOException e) {
//				e.printStackTrace();
				fireFileNotFound();
			}
		}
	}
}