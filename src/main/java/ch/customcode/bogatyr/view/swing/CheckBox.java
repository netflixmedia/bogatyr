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

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.Activatable;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;


/**
 * This is an extended JCheckBox.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.2.0
 */
public class CheckBox extends JCheckBox implements Activatable {
	private static final long serialVersionUID = -6439735629199643683L;
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */
	public CheckBox() {
		super();
	}	
	
	public CheckBox(final Action action) {
		super(action);
	}

	public CheckBox(final Icon icon, final boolean selected) {
		super(icon, selected);
	}

	public CheckBox(final Icon icon) {
		super(icon);
	}

	public CheckBox(final String text, final boolean selected) {
		super(text, selected);
	}

	public CheckBox(final String text, final Icon icon, final boolean selected) {
		super(text, icon, selected);
	}

	public CheckBox(final String text, final Icon icon) {
		super(text, icon);
	}

	public CheckBox(final String text) {
		super(text);
	}
	
	/*
	 * Own constructors
	 */
	public CheckBox(final boolean isSelected) {
		super();
		
		setSelected(isSelected);
	}
	
	public CheckBox(final boolean isSelected, final Action action) {
		this(action);
		
		setSelected(isSelected);
	}
	
	public CheckBox(final String text, final boolean isSelected, final String toolTip) {
		this(text, isSelected);
		setToolTipText(toolTip);
	}
	
	public CheckBox(final boolean isSelected, final String toolTip) {
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