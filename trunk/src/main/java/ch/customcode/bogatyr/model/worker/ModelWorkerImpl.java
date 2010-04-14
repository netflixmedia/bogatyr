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
package ch.customcode.bogatyr.model.worker;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.model.ModelAbstract;
import ch.customcode.bogatyr.model.application.ModelApplicationImpl;
import ch.customcode.bogatyr.view.swing.worker.Worker;


/**
 * The implementation of the worker model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100414)
 * @since 0.9.0
 */
public class ModelWorkerImpl extends ModelAbstract implements ModelWorker {
	private static final long serialVersionUID = -2826684498598090349L;
	
	private static final Logger log = LoggerFactory.getLogger(ModelApplicationImpl.class);
	
	private transient List<Worker> listWorker = new ArrayList<Worker>();
 

	public ModelWorkerImpl() {
        super();
        log.trace(HelperLog.constructor());
    }

	/*
     * Implemented methods
     */
    @Override
    public synchronized void add(final Worker worker) {
    	log.debug(HelperLog.methodStart(worker));
		if (null == worker) {
			throw new RuntimeExceptionIsNull("worker"); //$NON-NLS-1$
		}

		listWorker.add(worker);
        setChanged();
        notifyObservers(METHOD_ADD);
        
        log.debug(HelperLog.methodExit());
    }

    @Override
    public synchronized void remove(final Worker worker) {
    	log.debug(HelperLog.methodStart(worker));
		if (null == worker) {
			throw new RuntimeExceptionIsNull("worker"); //$NON-NLS-1$
		}

		worker.cancel(true);
        listWorker.remove(worker);
        setChanged();
        notifyObservers(METHOD_REMOVE);
        
        log.debug(HelperLog.methodExit());
    }

    @Override
    public synchronized void removeAll() {
    	log.debug(HelperLog.methodStart());
    	
        for (final Worker worker : listWorker) {
            worker.cancel(true);
        }

        listWorker = new ArrayList<Worker>();
        setChanged();
        notifyObservers(METHOD_REMOVE_ALL);
        
        log.debug(HelperLog.methodExit());
    }
    
    @Override
    public int count() {
    	log.debug(HelperLog.methodStart());
    	
    	log.debug(HelperLog.methodExit(listWorker.size()));
    	return listWorker.size();
	}
}