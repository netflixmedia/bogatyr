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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package net.laubenberger.bogatyr.view.swing.worker;

import java.util.Collection;
import java.util.HashSet;

import javax.swing.SwingWorker;

import net.laubenberger.bogatyr.misc.Event;
import net.laubenberger.bogatyr.misc.HolderListener;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

/**
 * This class represents a skeleton for the worker.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public abstract class WorkerAbstract<T, V> extends SwingWorker<T, V> implements Worker, HolderListener<ListenerWorker> {
	private Collection<ListenerWorker> listListener = new HashSet<ListenerWorker>();

	private final Event<Worker> event = new Event<Worker>(this);

	protected WorkerAbstract() {
		super();
	}


	/*
	 * Private methods
	 */

	protected void fireWorkerStart() {
		for (final ListenerWorker listener : listListener) {
			listener.start(event);
		}
	}

	protected void fireWorkerDone() {
		for (final ListenerWorker listener : listListener) {
			listener.done(event);
		}
	}


	/*
	 * Implemented methods
	 */

	@Override
	public synchronized void addListener(final ListenerWorker listener) {
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.add(listener);
	}

	@Override
	public int countListeners() {
		return listListener.size();
	}

	@Override
	public synchronized void deleteListener(final ListenerWorker listener) {
		if (null == listener) {
			throw new RuntimeExceptionIsNull("listener"); //$NON-NLS-1$
		}

		listListener.remove(listener);
	}

	@Override
	public synchronized void deleteListeners() {
		listListener = new HashSet<ListenerWorker>();
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
