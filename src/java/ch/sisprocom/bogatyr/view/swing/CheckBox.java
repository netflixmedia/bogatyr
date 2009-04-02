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
import javax.swing.JCheckBox;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JCheckBox.
 * 
 * @author Stefan Laubenberger
 * @version 20090318
 */
public class CheckBox extends JCheckBox {
	private static final long serialVersionUID = -6439735629199643683L;

	
	public CheckBox() {
		super();
	}	
	
	public CheckBox(final boolean isSelected, final Action action) {
		super(action);
		
		setSelected(isSelected);
	}
	
	public CheckBox(final String title, final boolean isSelected, final String toolTip) {
		super(title, isSelected);
		setToolTipText(toolTip);
	}
	
	public CheckBox(final boolean isSelected, final String toolTip) {
		super();
		setSelected(isSelected);
		setToolTipText(toolTip);
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void setToolTipText(final String text) {
		if (text != null) {
            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
	}
}