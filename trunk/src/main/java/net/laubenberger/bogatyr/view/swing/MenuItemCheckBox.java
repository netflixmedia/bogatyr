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
import javax.swing.JCheckBoxMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JCheckBoxMenuItem.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
 * @since 0.2.0
 */
public class MenuItemCheckBox extends JCheckBoxMenuItem {
	private static final long serialVersionUID = -5950690648354816752L;

	private static final Logger log = LoggerFactory.getLogger(MenuItemCheckBox.class);

	/*
		 * Superclass constructors
		 */

	public MenuItemCheckBox() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public MenuItemCheckBox(final Action action) {
		super(action);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(action));
	}

	public MenuItemCheckBox(final Icon icon) {
		super(icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(icon));
	}

	public MenuItemCheckBox(final String text, final boolean selected) {
		super(text, selected);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, selected));
	}

	public MenuItemCheckBox(final String text, final Icon icon, final boolean selected) {
		super(text, icon, selected);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon, selected));
	}

	public MenuItemCheckBox(final String text, final Icon icon) {
		super(text, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon));
	}

	public MenuItemCheckBox(final String text) {
		super(text);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text));
	}

	/*
	 * Own constructors
	 */

	public MenuItemCheckBox(final boolean isSelected) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(isSelected));

		setSelected(isSelected);
	}

	public MenuItemCheckBox(final boolean isSelected, final Action action) {
		this(action);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(isSelected, action));

		setSelected(isSelected);
	}

	public MenuItemCheckBox(final String text, final Icon icon, final int mnemonic, final String toolTip, final boolean isSelected) {
		this(text);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon, mnemonic, toolTip, isSelected));

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
		setSelected(isSelected);
	}

	public MenuItemCheckBox(final String text, final Icon icon, final int mnemonic, final String toolTip, final boolean isSelected, final ActionListener listener) {
		this(text, icon, mnemonic, toolTip, isSelected);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(text, icon, mnemonic, toolTip, isSelected, listener));

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