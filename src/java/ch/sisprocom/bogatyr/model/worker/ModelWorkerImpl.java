/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.model.worker;


import java.util.ArrayList;
import java.util.List;

import ch.sisprocom.bogatyr.view.swing.worker.WorkerAbstract;
import ch.sisprocom.bogatyr.model.ModelAbstract;


/**
 * The implementation of the worker model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091121)
 * @since 0.9.0
 */
public class ModelWorkerImpl extends ModelAbstract implements ModelWorker {
	private static final long serialVersionUID = -2826684498598090349L;

	private List<WorkerAbstract> listWorker = new ArrayList<WorkerAbstract>();

    public ModelWorkerImpl() {
        super();
    }
 

	/*
     * Implemented methods
     */
    public List<WorkerAbstract> getWorkers() {
		return listWorker;
	}

    public <T, V> void add(final WorkerAbstract<T, V> worker) {
    	listWorker.add(worker);
        setChanged();
        notifyObservers(METHOD_ADD);
    }

    public <T, V> void remove(final WorkerAbstract<T, V> worker) {
        worker.cancel(true);
        listWorker.remove(worker);
        setChanged();
        notifyObservers(METHOD_REMOVE);
    }

    public <T, V> void removeAll() {
        for (final WorkerAbstract<T, V> worker : listWorker) {
            worker.cancel(true);
        }

        listWorker = new ArrayList<WorkerAbstract>();
        setChanged();
        notifyObservers(METHOD_REMOVE_ALL);
    }
}