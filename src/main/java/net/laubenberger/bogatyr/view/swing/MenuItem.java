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

package net.laubenberger.bogatyr.view.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JMenuItem.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100610)
 * @since 0.2.0
 */
public class MenuItem extends JMenuItem {
	private static final long serialVersionUID = -5950690648354816752L;

	private static final Logger log = LoggerFactory.getLogger(MenuItem.class);

	/*
		 * Superclass constructors
		 */

	public MenuItem() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public MenuItem(final Icon icon) {
		super(icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(icon));
	}

	public MenuItem(final String text, final Icon icon) {
		super(text, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon));
	}

	public MenuItem(final String text, final int mnemonic) {
		super(text, mnemonic);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, mnemonic));
	}

	public MenuItem(final String text) {
		super(text);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text));
	}

	public MenuItem(final Action action) {
		super(action);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(action));
	}

	/*
	 * Own constructors
	 */
	public MenuItem(final String text, final Icon icon, final String toolTip) {
		this(text, icon, toolTip, 0, null);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon, toolTip));
	}
	
	public MenuItem(final String text, final Icon icon, final String toolTip, final int mnemonic, final KeyStroke accelerator) {
		this(text);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon, toolTip, mnemonic, accelerator));

		// Add the optional icon
		if (null != icon) {
			setIcon(icon);
		}
		
		// Add the optional tool tip text
		if (null != toolTip) {
			setToolTipText(toolTip);
		}

		// Add the optional mnemonic key
		if (0 < mnemonic) {
			setMnemonic(mnemonic);
		}

		// Add the optional accelerator
		if (null != accelerator) {
			setAccelerator(accelerator);
		}
	}
}