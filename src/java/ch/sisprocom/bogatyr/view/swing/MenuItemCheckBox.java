/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU General Public License for more details:
 * ----------------------------------------------------
 * <http://www.gnu.org/licenses>
 * 
 * This distribution is available at:
 * ----------------------------------
 * <http://code.google.com/p/bogatyr/>
 * <http://www.sisprocom.ch/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * SiSprocom GmbH
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing;

import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JCheckBoxMenuItem.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.2.0
 */
public class MenuItemCheckBox extends JCheckBoxMenuItem {
	private static final long serialVersionUID = -5950690648354816752L;
	
	
	public MenuItemCheckBox() {
		super();
    }
	
	public MenuItemCheckBox(final boolean isSelected) {
		super();
		
		setSelected(isSelected);
	}
	
	public MenuItemCheckBox(final boolean isSelected, final Action action) {
		super(action);
		
		setSelected(isSelected);
	}

	public MenuItemCheckBox(final String text, final Icon image, final int mnemonic, final String toolTip, final boolean isSelected) {
		super(text);
		
		// Add the optional icon
		if (image != null) {
            setIcon(image);
        }

		// Add the mnemonic key
		if (0 < mnemonic) {
			setMnemonic(mnemonic);
		}

		// Add the optional tool tip text
		if (toolTip != null) {
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