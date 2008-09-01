/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JMenuItem
 * 
 * @author Stefan Laubenberger
 * @version 20080613
 */
public class MenuItem extends JMenuItem {
	private static final long serialVersionUID = -5950690648354816752L;

	
	public MenuItem() {
		super();
    }
	
	public MenuItem(final String text, final ImageIcon image, final int mnemonic, final String toolTip) {
		super(text);
		
		// Add the optional icon
		if (image != null) {
            setIcon(image);
        }

		// Add the mnemonic key
		if (mnemonic > 0) {
			setMnemonic(mnemonic);
		}

		// Add the optional tool tip text
		if (toolTip != null) {
			setToolTipText(toolTip);
		}
    }
	
	public MenuItem(final String text, final ImageIcon image, final int mnemonic, final String toolTip, final ActionListener listener) {
		this(text, image, mnemonic, toolTip);
		addActionListener(listener);
    }
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}