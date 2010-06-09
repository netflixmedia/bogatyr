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

import javax.swing.JPopupMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JPopupMenu.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100610)
 * @since 0.2.0
 */
public class PopupMenu extends JPopupMenu {
	private static final long serialVersionUID = -2117215021680174633L;

	private static final Logger log = LoggerFactory.getLogger(PopupMenu.class);

	/*
	 * Superclass constructors
	 */

	public PopupMenu() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public PopupMenu(final String label) {
		super(label);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(label));
	}
}