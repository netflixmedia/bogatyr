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
package ch.sisprocom.bogatyr.view.swing.pane;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JOptionPane.
 * 
 * @author Stefan Laubenberger
 * @version 20090422
 */
public class PaneOption extends JOptionPane {
	private static final long serialVersionUID = -4947729751470298861L;
	

	public PaneOption() {
		super();
	}
	
	public PaneOption(Object message, int messageType, int optionType,
			Icon icon, Object[] options, Object initialValue) {
		super(message, messageType, optionType, icon, options, initialValue);
	}

	public PaneOption(Object message, int messageType, int optionType,
			Icon icon, Object[] options) {
		super(message, messageType, optionType, icon, options);
	}

	public PaneOption(Object message, int messageType, int optionType, Icon icon) {
		super(message, messageType, optionType, icon);
	}

	public PaneOption(Object message, int messageType, int optionType) {
		super(message, messageType, optionType);
	}

	public PaneOption(Object message, int messageType) {
		super(message, messageType);
	}

	public PaneOption(Object message) {
		super(message);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		
		final Component[] components = getComponents();
	    for (final Component component : components) {
	    	component.setEnabled(enabled);
	    }
	}
}