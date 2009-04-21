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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JMenuItem.
 * 
 * @author Stefan Laubenberger
 * @version 20090421
 */
public class MenuItem extends JMenuItem implements IComponentAntiAliasing {
	private static final long serialVersionUID = -5950690648354816752L;

	private boolean isAntiAliasing = true;
	
	
	public MenuItem() {
		super();
    }
	
	public MenuItem(final Action action) {
		super(action);
	}

	public MenuItem(final String text, final Icon image, final int mnemonic, final String toolTip) {
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
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		if (isAntiAliasing) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			super.paintComponent(g2d);
		} else {
			super.paintComponent(g);
		}
	}
	
	
	/*
	 * Implemented methods
	 */
	public boolean isAntiAliasing() {
		return isAntiAliasing;
	}

	public void setAntiAliasing(final boolean isEnabled) {
		isAntiAliasing = isEnabled;
	}
}