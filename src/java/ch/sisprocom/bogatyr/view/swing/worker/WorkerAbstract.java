/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing.worker;

import java.util.Collection;
import java.util.HashSet;

import javax.swing.SwingWorker;

import ch.sisprocom.bogatyr.misc.Event;
import ch.sisprocom.bogatyr.misc.HolderListener;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;

/**
 * This class represents a skeleton for the worker.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
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
