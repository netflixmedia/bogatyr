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

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.KeyStroke;

import net.laubenberger.bogatyr.helper.HelperLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended AbstractAction.
 *
 * @author Stefan Laubenberger
 * @version 0.9.3 (20100817)
 * @since 0.7.0
 */
public abstract class ActionAbstract extends AbstractAction {
	private static final long serialVersionUID = -2411318943212390523L;

	private static final Logger log = LoggerFactory.getLogger(ActionAbstract.class);

//	Action.LONG_DESCRIPTION
//	Action.SMALL_ICON
//	Action.LARGE_ICON_KEY

	/*
	 * Superclass constructors
	 */

	protected ActionAbstract() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	protected ActionAbstract(final String name, final Icon icon) {
		super(name, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, icon));
	}

	protected ActionAbstract(final String name) {
		super(name);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name));
	}

	/*
	 * Own constructors
	 */

	protected ActionAbstract(final String name, final String toolTip) {
		this(name);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, toolTip));

		putValue(SHORT_DESCRIPTION, toolTip);
	}

	protected ActionAbstract(final String name, final String toolTip, final int mnemonic) {
		super(name);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, toolTip, mnemonic));

		putValue(SHORT_DESCRIPTION, toolTip);
		putValue(MNEMONIC_KEY, mnemonic);
	}

	protected ActionAbstract(final String name, final Icon icon, final String toolTip) {
		this(name, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, toolTip, icon));

		putValue(SHORT_DESCRIPTION, toolTip);
	}

	protected ActionAbstract(final String name, final Icon icon, final String toolTip, final int mnemonic) {
		this(name, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, toolTip, icon, mnemonic));

		putValue(SHORT_DESCRIPTION, toolTip);
		putValue(MNEMONIC_KEY, mnemonic);
	}
	
	protected ActionAbstract(final String name, final Icon icon, final String toolTip, final int mnemonic, final KeyStroke accelerator) {
		this(name, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(name, toolTip, icon, mnemonic, accelerator));

		putValue(SHORT_DESCRIPTION, toolTip);
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, accelerator);
	}
}