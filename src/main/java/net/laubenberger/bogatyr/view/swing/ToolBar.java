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

package net.laubenberger.bogatyr.view.swing;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.Activatable;

import javax.swing.JToolBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JToolBar.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100610)
 * @since 0.7.0
 */
public class ToolBar extends JToolBar implements Activatable {
	private static final long serialVersionUID = 7538391089705088133L;

	private static final Logger log = LoggerFactory.getLogger(ToolBar.class);

	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */

	public ToolBar() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public ToolBar(final int orientation) {
		super(orientation);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(orientation));
	}

	public ToolBar(final String name, final int orientation) {
		super(name, orientation);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, orientation));
	}

	public ToolBar(final String name) {
		super(name);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name));
	}


	/*
	 * Overridden methods
	 */

	@Override
	public void setEnabled(final boolean isEnabled) {
		if (!isNotActive) {
			super.setEnabled(isEnabled);
		}
	}


	/*
	 * Implemented methods
	 */

	@Override
	public boolean isActive() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(!isNotActive));
		return !isNotActive;
	}

	@Override
	public void setActive(final boolean isActive) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(isActive));

		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}