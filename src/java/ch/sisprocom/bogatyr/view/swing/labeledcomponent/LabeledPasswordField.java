/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing.labeledcomponent;

import ch.sisprocom.bogatyr.view.swing.PasswordField;


/**
 * This is an combined Label with a TextField.
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public class LabeledPasswordField extends LabeledComponent {
	private static final long serialVersionUID = 1310593497620798003L;
	
	
	public LabeledPasswordField(final String labelText, final String toolTip) {
		super(labelText, toolTip, new PasswordField());
	}

	public LabeledPasswordField(final String title, final String labelText, final String toolTip) {
		super(title, labelText, toolTip, new PasswordField());
	}

	public PasswordField getPasswordField() {
		return (PasswordField)getComponent();
	}
}