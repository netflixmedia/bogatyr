/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.SwingWorker.StateValue;

import net.laubenberger.bogatyr.misc.HolderListener;


/**
 * Type definition for workers.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100603)
 * @since 0.9.0
 */
public interface Worker extends Runnable, HolderListener<ListenerWorker> {
	
//	void setName(String name);
//	
//	void setDescription(String desc);

	
	//methods from SwingWorker
	boolean cancel(boolean mayInterruptIfRunning);

	int getProgress();

	void execute();

	boolean isCancelled();

	boolean isDone();
//	T get() throws InterruptedException, ExecutionException;
//	T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;

	void addPropertyChangeListener(PropertyChangeListener listener);

	void removePropertyChangeListener(PropertyChangeListener listener);

	void firePropertyChange(String propertyName, Object oldValue, Object newValue);

	PropertyChangeSupport getPropertyChangeSupport();

	StateValue getState();
}   