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

package net.laubenberger.bogatyr.controller.application;

import java.lang.Thread.UncaughtExceptionHandler;

import net.laubenberger.bogatyr.controller.ControllerAbstract;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.model.application.ModelApplication;
import net.laubenberger.bogatyr.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The skeleton for the application controller.
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100813)
 * @since 0.9.0
 */
public abstract class ControllerApplicationAbstract<M extends ModelApplication, V extends View> extends ControllerAbstract implements ControllerApplication<M, V> {
	static final Logger log = LoggerFactory.getLogger(ControllerApplicationAbstract.class);
	
	private M model;
	private V view;

	static {
		Thread.setDefaultUncaughtExceptionHandler(new Handler());
	}
	
	protected ControllerApplicationAbstract() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}
	
	protected ControllerApplicationAbstract(final M model, final V view) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(model, view));
		
		this.model = model;
		this.view = view;
	}


	/*
	 * Implemented methods
	 */
	
	@Override
	public void run() {
		if (log.isInfoEnabled()) log.info(HelperLog.applicationStart(model));
	}
	
	@Override
	public void exit(final int returnCode) {
		if (0 == returnCode) {
			if (log.isInfoEnabled()) log.info(HelperLog.applicationExit(returnCode));
		} else {
			if (log.isWarnEnabled()) log.warn(HelperLog.applicationExit(returnCode));
		}
		System.exit(returnCode);
	}

	@Override
	public M getModel() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(model));
		return model;
	}
	
	@Override
	public V getView() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(view));
		return view;
	}

	@Override
	public void setModel(M model) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(model));
		
		this.model = model;
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	@Override
	public void setView(V view) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(view));
		
		this.view = view;
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
	
	/*
	 * Inner classes
	 */
	
	static class Handler implements UncaughtExceptionHandler {
		@Override
		public void uncaughtException(final Thread t, final Throwable ex) {
			log.error("Uncaught exception occurred", ex); //$NON-NLS-1$
		}
	}
}
