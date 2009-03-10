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

import java.awt.event.ActionListener;

import ch.sisprocom.bogatyr.view.swing.ComboBox;


/**
 * This is an combined Label with a ComboBox.
 * 
 * @author Stefan Laubenberger
 * @version 20090310
 */
public class LabeledComboBox extends LabeledComponent { //TODO document in Wiki!
	private static final long serialVersionUID = -67296455436983811L;
	
	
	public LabeledComboBox(final String labelText, final String toolTip, final Object[] data) {
		super(labelText, toolTip, new ComboBox(data, null));
	}

	public LabeledComboBox(final String title, final String labelText, final String toolTip, final Object[] data) {
		super(title, labelText, toolTip, new ComboBox(data, null));
	}
	
	public ComboBox getComboBox() {
		return ((ComboBox)getComponent());
	}

	public void addActionListener(final ActionListener listener) {
        ((ComboBox)getComponent()).addActionListener(listener);
	}
}