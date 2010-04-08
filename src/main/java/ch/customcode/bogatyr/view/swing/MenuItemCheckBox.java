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
import javax.swing.JCheckBoxMenuItem;

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * This is an extended JCheckBoxMenuItem.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.2.0
 */
public class MenuItemCheckBox extends JCheckBoxMenuItem {
	private static final long serialVersionUID = -5950690648354816752L;
	
	/*
	 * Superclass constructors
	 */
	public MenuItemCheckBox() {
		super();
    }
	
	public MenuItemCheckBox(final Action action) {
		super(action);
	}

	public MenuItemCheckBox(final Icon icon) {
		super(icon);
	}

	public MenuItemCheckBox(final String text, final boolean selected) {
		super(text, selected);
	}

	public MenuItemCheckBox(final String text, final Icon icon, final boolean selected) {
		super(text, icon, selected);
	}

	public MenuItemCheckBox(final String text, final Icon icon) {
		super(text, icon);
	}

	public MenuItemCheckBox(final String text) {
		super(text);
	}

	/*
	 * Own constructors
	 */
	public MenuItemCheckBox(final boolean isSelected) {
		super();
		
		setSelected(isSelected);
	}
	
	public MenuItemCheckBox(final boolean isSelected, final Action action) {
		this(action);
		
		setSelected(isSelected);
	}

	public MenuItemCheckBox(final String text, final Icon image, final int mnemonic, final String toolTip, final boolean isSelected) {
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
		setSelected(isSelected);
    }
	
	public MenuItemCheckBox(final String text, final Icon image, final int mnemonic, final String toolTip, final boolean isSelected, final ActionListener listener) {
		this(text, image, mnemonic, toolTip, isSelected);
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