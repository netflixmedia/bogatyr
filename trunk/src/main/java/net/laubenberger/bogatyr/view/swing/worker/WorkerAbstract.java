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

package net.laubenberger.bogatyr.view.swing.worker;

import java.util.Collection;
import java.util.HashSet;

import javax.swing.SwingWorker;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.HolderListener;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a skeleton for the worker.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
 * @since 0.9.0
 */
public abstract class WorkerAbstract<T, V> extends SwingWorker<T, V> implements Worker, HolderListener<ListenerWorker> {
	private static final Logger log = LoggerFactory.getLogger(WorkerAbstract.class);

	private Collection<ListenerWorker> listeners = new HashSet<ListenerWorker>();

	private final Event<Worker> event = new Event<Worker>(this);

	protected WorkerAbstract() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}


	/*
	 * Private methods
	 */

	protected void fireWorkerStart() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		for (final ListenerWorker listener : listeners) {
			listener.start(event);
		}

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}

	protected void fireWorkerDone() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

		for (final ListenerWorker listener : listeners) {
			listener.done(event);
		}

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}


	/*
	 * Implemented methods
	 */

	@Override
	public synchronized void addListener(final ListenerWorker listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.add(listener);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public int countListeners() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(listeners.size()));
		return listeners.size();
	}

	@Override
	public synchronized void deleteListener(final ListenerWorker listener) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(listener));
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listeners.remove(listener);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public synchronized void deleteListeners() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		listeners = new HashSet<ListenerWorker>();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

//    protected WorkerAbstract(final HandlerWorker handlerWorker) {
//        super();
//
//        this.handlerWorker = handlerWorker;
//    }
//
//    public HandlerWorker getHandlerWorker() {
//        return handlerWorker;
//    }
//
//    public void setHandlerWorker(final HandlerWorker handlerWorker) {
//        this.handlerWorker = handlerWorker;
//    }
//
//    public void start() {
//        if (null != handlerWorker) {
//            handlerWorker.fireWorkerStart();
//        }
//        
//        execute();
//    }
//
//    
//    /*
//     * Overridden methods
//     */
//    @Override
//    protected void done() {
//    	if (null != handlerWorker) {
//            handlerWorker.fireWorkerDone();
//        }
//    }
}
