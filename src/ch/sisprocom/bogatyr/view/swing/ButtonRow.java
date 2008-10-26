/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;


/**
 * This is a button row
 * 
 * @author Stefan Laubenberger
 * @version 20081026
 */
public class ButtonRow extends Panel {
	private static final long serialVersionUID = -3557759501854611930L;
	

	public ButtonRow(final Button[] data) {
		super();
		createLayout(data, false);
	}
	
	public ButtonRow(final Button[] data, final boolean isVertical) {
		super();
		createLayout(data, isVertical);
	}

	public void addActionListener(final ActionListener listener) {
		final Component[] components = getComponents();
		for (final Component component : components) {
	    	if (component instanceof Button) {
	    		((AbstractButton) component).addActionListener(listener);
			}
	    }
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(final Button[] data, final boolean isVertical) {
		if (isVertical) {
			createLayoutVertical(data);
		} else {
			createLayoutHorizontal(data);
		}
	}
	
	private void createLayoutVertical(final Button[] data) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
	    gbc.insets = new Insets(5, 5, 5, 5);
	   
	    if (data != null) {
	    	for (final Button button : data) {
				gbc.gridy++;
		    	add(button, gbc);
		    }
	    }
	}
	
	private void createLayoutHorizontal(final Button[] data) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
	    gbc.insets = new Insets(5, 5, 5, 5);

	    if (data != null) {
	    	for (final Button button : data) {
				gbc.gridx++;
		    	add(button, gbc);
		    }
	    }
	}
}