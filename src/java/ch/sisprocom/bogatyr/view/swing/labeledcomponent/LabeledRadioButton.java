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

import javax.swing.Action;
import javax.swing.AbstractButton;

import ch.sisprocom.bogatyr.view.swing.RadioButton;


/**
 * This is an combined Label with a RadioButton.
 * 
 * @author Stefan Laubenberger
 * @version 20090318
 */
public class LabeledRadioButton extends LabeledComponent { //TODO document in Wiki!
	private static final long serialVersionUID = 3461718081893469685L;

	
	public LabeledRadioButton(final String labelText, final String toolTip, final boolean isSelected, final Action action) {
		super(labelText, toolTip, new RadioButton(isSelected, action));
	}
	
	public LabeledRadioButton(final String labelText, final String toolTip, final boolean isSelected) {
		super(labelText, toolTip, new RadioButton(isSelected, toolTip));
	}

	public LabeledRadioButton(final String title, final String labelText, final String toolTip, final boolean isSelected) {
		super(title, labelText, toolTip, new RadioButton(isSelected, toolTip));
	}

	public RadioButton getRadioButton() {
		return (RadioButton)getComponent();
	}
	
	public boolean isSelected() {
		return ((AbstractButton) getComponent()).isSelected();
	}
	
	public void addActionListener(final ActionListener listener) {
		((AbstractButton) getComponent()).addActionListener(listener);
	}
}