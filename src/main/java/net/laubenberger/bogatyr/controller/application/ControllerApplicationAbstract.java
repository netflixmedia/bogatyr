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
import java.util.HashMap;
import java.util.Map;

import net.laubenberger.bogatyr.controller.ControllerAbstract;
import net.laubenberger.bogatyr.helper.HelperCollection;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperMap;
import net.laubenberger.bogatyr.model.Model;
import net.laubenberger.bogatyr.model.application.ModelApplication;
import net.laubenberger.bogatyr.model.misc.Document;
import net.laubenberger.bogatyr.model.misc.Url;
import net.laubenberger.bogatyr.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The skeleton for the application controller.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.5 (20110124)
 * @since 0.9.0
 */
public abstract class ControllerApplicationAbstract<M extends ModelApplication, V extends View> extends
		ControllerAbstract implements ControllerApplication<M, V> {
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
		if (log.isInfoEnabled()) log.info(HelperLog.applicationStart(this));
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
	public void setModel(final M model) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(model));

		this.model = model;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setView(final V view) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(view));

		this.view = view;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public Map<String, Object> getReport() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final Map<String, Object> result = new HashMap<String, Object>();

		result.put(Document.MEMBER_BUILD, model.getBuild());
		result.put(Document.MEMBER_CREATED, model.getCreated());
		result.put(Model.MEMBER_INSTANTIATED, model.getInstantiated());
		result.put(ModelApplication.MEMBER_DEBUG, model.isDebug());

		if (null != model.getName()) {
			result.put(Document.MEMBER_NAME, model.getName());
		}

		if (null != model.getVersion()) {
			result.put(Document.MEMBER_VERSION, model.getVersion());
		}
		if (null != model.getUUID()) {
			result.put(Model.MEMBER_UUID, model.getUUID());
		}
		if (null != model.getLanguage()) {
			result.put(Document.MEMBER_LANGUAGE, model.getLanguage());
		}
		if (null != model.getUrl()) {
			result.put(Url.MEMBER_URL, model.getUrl());
		}
		if (null != model.getName()) {
			result.put(Document.MEMBER_NAME, model.getName());
		}

		if (null != model.getUpdateLocation()) {
			result.put(ModelApplication.MEMBER_UPDATE_LOCATION, model.getUpdateLocation());
		}

		if (null != model.getProperty()) {
			result.put(ModelApplication.MEMBER_PROPERTY, model.getProperty());
		}

		if (null != model.getLocalizer()) {
			result.put(ModelApplication.MEMBER_LOCALIZER, model.getLocalizer());
		}

		if (null != model.getOrganizations()) {
			result.put(Document.MEMBER_ORGANIZATIONS, HelperCollection.dump(model.getOrganizations()));
		}

		if (null != model.getPersons()) {
			result.put(Document.MEMBER_PERSONS, HelperCollection.dump(model.getPersons()));
		}

		if (null != model.getTags()) {
			result.put(Model.MEMBER_TAGS, HelperMap.dump(model.getTags()));
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
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
