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
import javax.swing.JButton;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JButton.
 * 
 * @author Stefan Laubenberger
 * @version 20090428
 */
public class Button extends JButton implements IComponentActivate {
	private static final long serialVersionUID = -7231487009931166084L;
	
	private static boolean isActive = true;

	
	public Button() {
		super();
	}

	public Button(final Action action) {
		super(action);
	}
	
	public Button(final String title, final String toolTip) {
		super(title);
		setToolTipText(toolTip);
	}
	
	public Button(final String title, final String toolTip, final ActionListener listener) {
		this(title, toolTip);
		addActionListener(listener);
	}	
	
	public Button(final String title, final Icon icon, final String toolTip, final ActionListener listener) {
		super(title, icon);
		setToolTipText(toolTip);
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
	public void setEnabled(boolean isEnabled) {
		if (isActive) {
			super.setEnabled(isEnabled);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		if (isActive) {
			Button.isActive = isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			Button.isActive = isActive;
		}
	}
	
//	@Override
//	public void setText(final String text) {
//		super.setText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//	}
//	
//	@Override
//	public void setToolTipText(final String text) {
//		if (text != null) {
//            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//        } else {
//        	super.setToolTipText(text);
//        }
//	}
}
