/*******************************************************************************
 * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.misc.Activatable;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;


/**
 * This is an extended JRadioButton.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.2.0
 */
public class RadioButton extends JRadioButton implements Activatable {
	private static final long serialVersionUID = 8676540667794440059L;
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */
	public RadioButton() {
		super();
	}
	
	public RadioButton(final Action action) {
		super(action);
	}

	public RadioButton(final Icon icon, final boolean selected) {
		super(icon, selected);
	}

	public RadioButton(final Icon icon) {
		super(icon);
	}

	public RadioButton(final String text, final boolean selected) {
		super(text, selected);
	}

	public RadioButton(final String text, final Icon icon, final boolean selected) {
		super(text, icon, selected);
	}

	public RadioButton(final String text, final Icon icon) {
		super(text, icon);
	}

	public RadioButton(final String text) {
		super(text);
	}
	
	/*
	 * Own constructors
	 */
	public RadioButton(final boolean isSelected) {
		super();
		
		setSelected(isSelected);
	}
	
	public RadioButton(final boolean isSelected, final Action action) {
		this(action);
		
		setSelected(isSelected);
	}

	public RadioButton(final String text, final boolean isSelected, final String toolTip) {
		this(text, isSelected);
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
	@Override
    public boolean isActive() {
		return !isNotActive;
	}

	@Override
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