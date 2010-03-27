/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.view.swing.worker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.SwingWorker.StateValue;



/**
 * Type definition for workers.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100209)
 * @since 0.9.0
 */
public interface Worker {
	//marker interface, only used as type definition for workers
	
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