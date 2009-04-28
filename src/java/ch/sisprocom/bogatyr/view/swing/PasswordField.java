/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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

import java.util.Arrays;

import javax.swing.JPasswordField;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JPasswordField.
 * 
 * @author Stefan Laubenberger
 * @version 20090428
 */
public class PasswordField extends JPasswordField implements IComponentActivate {
	private static final long serialVersionUID = 4337982428755317915L;
	
	private boolean isActive = true;

	
	public PasswordField() {
        super();
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

        boolean isCorrect = getPassword().length == correctPassword.length ? Arrays.equals(getPassword(), correctPassword) : false;
	    return isCorrect;
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
			this.isActive = isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			this.isActive = isActive;
		}
	}
	
//	@Override
//	public void setToolTipText(final String text) {
//		if (text != null) {
//            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//        }
//	}
}
