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

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JMenuItem.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
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
		log.trace(HelperLog.constructor());
	}

	public MenuItem(final Icon icon) {
		super(icon);
		log.trace(HelperLog.constructor(icon));
	}

	public MenuItem(final String text, final Icon icon) {
		super(text, icon);
		log.trace(HelperLog.constructor(text, icon));
	}

	public MenuItem(final String text, final int mnemonic) {
		super(text, mnemonic);
		log.trace(HelperLog.constructor(text, mnemonic));
	}

	public MenuItem(final String text) {
		super(text);
		log.trace(HelperLog.constructor(text));
	}

	public MenuItem(final Action action) {
		super(action);
		log.trace(HelperLog.constructor(action));
	}

	/*
	 * Own constructors
	 */

	public MenuItem(final String text, final Icon icon, final int mnemonic, final String toolTip) {
		this(text);
		log.trace(HelperLog.constructor(text, icon, mnemonic, toolTip));

		// Add the optional icon
		if (null != icon) {
			setIcon(icon);
		}

		// Add the mnemonic key
		if (0 < mnemonic) {
			setMnemonic(mnemonic);
		}

		// Add the optional tool tip text
		if (null != toolTip) {
			setToolTipText(toolTip);
		}
	}

	public MenuItem(final String text, final Icon icon, final int mnemonic, final String toolTip, final ActionListener listener) {
		this(text, icon, mnemonic, toolTip);
		log.trace(HelperLog.constructor(text, icon, mnemonic, toolTip, listener));

		addActionListener(listener);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}