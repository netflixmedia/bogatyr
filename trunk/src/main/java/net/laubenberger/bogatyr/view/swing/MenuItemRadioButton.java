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
import javax.swing.JRadioButtonMenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JRadioButtonMenuItem.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100504)
 * @since 0.2.0
 */
public class MenuItemRadioButton extends JRadioButtonMenuItem {
	private static final long serialVersionUID = -5950690648354816752L;

	private static final Logger log = LoggerFactory.getLogger(MenuItemRadioButton.class);

	/*
		 * Superclass constructors
		 */

	public MenuItemRadioButton() {
		super();
		log.trace(HelperLog.constructor());
	}

	public MenuItemRadioButton(final Action action) {
		super(action);
		log.trace(HelperLog.constructor(action));
	}

	public MenuItemRadioButton(final Icon icon, final boolean selected) {
		super(icon, selected);
		log.trace(HelperLog.constructor(icon, selected));
	}

	public MenuItemRadioButton(final Icon icon) {
		super(icon);
		log.trace(HelperLog.constructor(icon));
	}

	public MenuItemRadioButton(final String text, final boolean selected) {
		super(text, selected);
		log.trace(HelperLog.constructor(text, selected));
	}

	public MenuItemRadioButton(final String text, final Icon icon, final boolean selected) {
		super(text, icon, selected);
		log.trace(HelperLog.constructor(text, icon, selected));
	}

	public MenuItemRadioButton(final String text, final Icon icon) {
		super(text, icon);
		log.trace(HelperLog.constructor(text, icon));
	}

	public MenuItemRadioButton(final String text) {
		super(text);
		log.trace(HelperLog.constructor(text));
	}

	/*
	 * Own constructors
	 */

	public MenuItemRadioButton(final boolean isSelected) {
		super();
		log.trace(HelperLog.constructor(isSelected));

		setSelected(isSelected);
	}

	public MenuItemRadioButton(final boolean isSelected, final Action action) {
		this(action);
		log.trace(HelperLog.constructor(isSelected, action));

		setSelected(isSelected);
	}

	public MenuItemRadioButton(final String text, final Icon icon, final int mnemonic, final String toolTip, final boolean isSelected) {
		this(text);
		log.trace(HelperLog.constructor(text, icon, mnemonic, toolTip, isSelected));

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

	public MenuItemRadioButton(final String text, final Icon icon, final int mnemonic, final String toolTip, final boolean isSelected, final ActionListener listener) {
		this(text, icon, mnemonic, toolTip, isSelected);
		log.trace(HelperLog.constructor(text, icon, mnemonic, toolTip, isSelected, listener));

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