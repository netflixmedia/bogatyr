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

import javax.swing.Action;
import javax.swing.JRadioButton;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JRadioButton.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.20
 */
public class RadioButton extends JRadioButton implements IComponentActivate {
	private static final long serialVersionUID = 8676540667794440059L;
	
	private boolean isNotActive;


	public RadioButton() {
		super();
	}
	
	public RadioButton(final boolean isSelected) {
		super();
		
		setSelected(isSelected);
	}
	
	public RadioButton(final boolean isSelected, final Action action) {
		super(action);
		
		setSelected(isSelected);
	}

	public RadioButton(final String title, final boolean isSelected, final String toolTip) {
		super(title, isSelected);
		setToolTipText(toolTip);
	}
	
	public RadioButton(final boolean isSelected, final String toolTip) {
		this();
		setSelected(isSelected);
		setToolTipText(toolTip);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}

	@Override
	public void setEnabled(final boolean isEnabled) {
		if (!isNotActive) {
			super.setEnabled(isEnabled);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	public boolean isActive() {
		return !isNotActive;
	}

	public void setActive(final boolean isActive) {
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
	}
	
//	@Override
//	public void setToolTipText(final String text) {
//		if (text != null) {
//            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//        }
//	}
}