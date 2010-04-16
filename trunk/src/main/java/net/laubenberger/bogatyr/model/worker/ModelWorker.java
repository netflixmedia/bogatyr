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
package net.laubenberger.bogatyr.model.worker;

import net.laubenberger.bogatyr.model.Model;
import net.laubenberger.bogatyr.view.swing.worker.Worker;


/**
 * The interface for the worker model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public interface ModelWorker extends Model {
	String METHOD_ADD = "add"; //$NON-NLS-1$
	String METHOD_REMOVE = "remove"; //$NON-NLS-1$
	String METHOD_REMOVE_ALL = "removeAll"; //$NON-NLS-1$

//    List<Worker> getWorkers();

	void add(Worker worker);

	void remove(Worker worker);

	void removeAll();

	int count();
}