/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.view.swing.pane;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * This is an extended JOptionPane.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public class PaneOption extends JOptionPane {
	private static final long serialVersionUID = -4947729751470298861L;
	
	/*
	 * Superclass constructors
	 */
	public PaneOption() {
		super();
	}
	
	public PaneOption(final Object message, final int messageType, final int optionType,
			final Icon icon, final Object[] options, final Object initialValue) {
		super(message, messageType, optionType, icon, options, initialValue);
	}

	public PaneOption(final Object message, final int messageType, final int optionType,
			final Icon icon, final Object[] options) {
		super(message, messageType, optionType, icon, options);
	}

	public PaneOption(final Object message, final int messageType, final int optionType, final Icon icon) {
		super(message, messageType, optionType, icon);
	}

	public PaneOption(final Object message, final int messageType, final int optionType) {
		super(message, messageType, optionType);
	}

	public PaneOption(final Object message, final int messageType) {
		super(message, messageType);
	}

	public PaneOption(final Object message) {
		super(message);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		
	    for (final Component component : getComponents()) {
	    	component.setEnabled(enabled);
	    }
	}
}