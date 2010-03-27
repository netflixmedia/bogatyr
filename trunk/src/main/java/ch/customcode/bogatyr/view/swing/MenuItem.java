/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.view.swing;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * This is an extended JMenuItem.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.2.0
 */
public class MenuItem extends JMenuItem {
	private static final long serialVersionUID = -5950690648354816752L;
	
	/*
	 * Superclass constructors
	 */
	public MenuItem() {
		super();
    }
	
	public MenuItem(final Icon icon) {
		super(icon);
	}

	public MenuItem(final String text, final Icon icon) {
		super(text, icon);
	}

	public MenuItem(final String text, final int mnemonic) {
		super(text, mnemonic);
	}

	public MenuItem(final String text) {
		super(text);
	}

	public MenuItem(final Action action) {
		super(action);
	}

	/*
	 * Own constructors
	 */
	public MenuItem(final String text, final Icon image, final int mnemonic, final String toolTip) {
		this(text);
		
		// Add the optional icon
		if (null != image) {
            setIcon(image);
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
	
	public MenuItem(final String text, final Icon image, final int mnemonic, final String toolTip, final ActionListener listener) {
		this(text, image, mnemonic, toolTip);
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