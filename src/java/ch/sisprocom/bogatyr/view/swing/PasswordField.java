/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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

import javax.swing.JPasswordField;
import javax.swing.text.Document;

import java.util.Arrays;


/**
 * This is an extended JPasswordField.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.1.0
 */
public class PasswordField extends JPasswordField implements Activatable {
	private static final long serialVersionUID = 4337982428755317915L;
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */
	public PasswordField() {
        super();
    }

	public PasswordField(final Document doc, final String text, final int columns) {
		super(doc, text, columns);
	}

	public PasswordField(final int columns) {
		super(columns);
	}

	public PasswordField(final String text, final int columns) {
		super(text, columns);
	}

	public PasswordField(final String toolTip) {
        super();
        setToolTipText(toolTip);
    }
	
	/**
	 * Checks if the entered password is correct.
	 * 
	 * @param correctPassword from the user (e.g. from the db).
	 * @return true/false
	 */
	public boolean isPasswordCorrect(final char[] correctPassword) {
	    return getPassword().length == correctPassword.length && Arrays.equals(getPassword(), correctPassword);
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
