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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.worker;


import ch.sisprocom.bogatyr.model.ModelAbstract;
import ch.sisprocom.bogatyr.view.swing.worker.WorkerAbstract;

import java.util.ArrayList;
import java.util.List;


/**
 * The implementation of the worker model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.9.0
 */
public class ModelWorkerImpl extends ModelAbstract implements ModelWorker {
	private static final long serialVersionUID = -2826684498598090349L;

	private transient List<WorkerAbstract> listWorker = new ArrayList<WorkerAbstract>();
 

	/*
     * Implemented methods
     */
    @Override
    public List<WorkerAbstract> getWorkers() {
		return listWorker;
	}

    @Override
    public void add(final WorkerAbstract<?, ?> worker) {
    	listWorker.add(worker);
        setChanged();
        notifyObservers(METHOD_ADD);
    }

    @Override
    public void remove(final WorkerAbstract<?, ?> worker) {
        worker.cancel(true);
        listWorker.remove(worker);
        setChanged();
        notifyObservers(METHOD_REMOVE);
    }

    @Override
    public void removeAll() {
        for (final WorkerAbstract<?, ?> worker : listWorker) {
            worker.cancel(true);
        }

        listWorker = new ArrayList<WorkerAbstract>();
        setChanged();
        notifyObservers(METHOD_REMOVE_ALL);
    }
}