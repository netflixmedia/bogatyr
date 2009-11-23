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

import java.util.List;

import ch.sisprocom.bogatyr.view.swing.worker.WorkerAbstract;
import ch.sisprocom.bogatyr.model.Model;


/**
 * The interface for the worker model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091123)
 * @since 0.9.0
 */
public interface ModelWorker extends Model {
    String METHOD_ADD        = "add"; //$NON-NLS-1$
    String METHOD_REMOVE     = "remove"; //$NON-NLS-1$
    String METHOD_REMOVE_ALL = "removeAll"; //$NON-NLS-1$

    List<WorkerAbstract> getWorkers();
    
	<T, V> void add(WorkerAbstract<T, V> worker);

	<T, V> void remove(WorkerAbstract<T, V> worker);

	<T, V> void removeAll();
}