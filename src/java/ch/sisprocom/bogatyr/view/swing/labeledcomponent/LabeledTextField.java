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
package ch.sisprocom.bogatyr.view.swing.labeledcomponent;

import ch.sisprocom.bogatyr.view.swing.TextField;



/**
 * This is an combined Label with a TextField.
 * 
 * @author Stefan Laubenberger
 * @version 20090310
 */
public class LabeledTextField extends LabeledComponent { //TODO document in Wiki!
	private static final long serialVersionUID = 1310593497620798003L;
	
	
	public LabeledTextField(final String labelText, final String toolTip, final String text, final int columns) {
		super(labelText, toolTip, new TextField(text, toolTip, columns));
	}

	public LabeledTextField(final String title, final String labelText, final String toolTip, final String text, final int columns) {
		super(title, labelText, toolTip, new TextField(text, toolTip, columns));
	}

	public TextField getTextField() {
		return (TextField)getComponent();
	}
}