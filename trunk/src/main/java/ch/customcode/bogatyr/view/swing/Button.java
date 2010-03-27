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
import javax.swing.JButton;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.misc.Activatable;


/**
 * This is an extended JButton.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.2.0
 */
public class Button extends JButton implements Activatable {
	private static final long serialVersionUID = -7231487009931166084L;
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */
	public Button() {
		super();
	}

	public Button(final Action action) {
		super(action);
	}
	
	public Button(final Icon icon) {
		super(icon);
	}

	public Button(final String text, final Icon icon) {
		super(text, icon);
	}

	public Button(final String text) {
		super(text);
	}
	
	/*
	 * Own constructors
	 */
	public Button(final String text, final String toolTip) {
		this(text);
		setToolTipText(toolTip);
	}
	
	public Button(final String text, final String toolTip, final ActionListener listener) {
		this(text, toolTip);
		addActionListener(listener);
	}	
	
	public Button(final String text, final Icon icon, final String toolTip, final ActionListener listener) {
		this(text, icon);
		setToolTipText(toolTip);
		addActionListener(listener);
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
