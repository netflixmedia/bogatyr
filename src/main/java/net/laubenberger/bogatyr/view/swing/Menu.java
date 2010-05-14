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
import javax.swing.JMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JMenu.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
 * @since 0.2.0
 */
public class Menu extends JMenu {
	private static final long serialVersionUID = -908869267540163157L;

	private static final Logger log = LoggerFactory.getLogger(Menu.class);

	/*
		 * Superclass constructors
		 */

	public Menu() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public Menu(final String text, final boolean b) {
		super(text, b);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, b));
	}

	public Menu(final String text) {
		super(text);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text));
	}

	public Menu(final Action action) {
		super(action);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(action));
	}

	/*
	 * Own constructors
	 */

	public Menu(final String text, final int mnemonic) {
		this(text);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, mnemonic));

		// Add the mnemonic key
		if (0 < mnemonic) {
			setMnemonic(mnemonic);
		}
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}