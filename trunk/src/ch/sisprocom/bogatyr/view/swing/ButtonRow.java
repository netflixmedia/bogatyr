/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing;

import javax.swing.AbstractButton;

import ch.sisprocom.bogatyr.helper.logger.Logger;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;


/**
 * This is a button row
 * 
 * @author Stefan Laubenberger
 * @version 20080810
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "addActionListener", listener);  //$NON-NLS-1$

		final Component[] components = getComponents();
		for (final Component component : components) {
	    	if (component instanceof Button) {
	    		((AbstractButton) component).addActionListener(listener);
			}
	    }
		
		Logger.getInstance().writeMethodExit(this.getClass(), "addActionListener");  //$NON-NLS-1$
	}
  
	
	/*
	 * Private methods
	 */
	private void createLayout(final Button[] data, final boolean isVertical) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "createLayout", new Object[]{data, isVertical});  //$NON-NLS-1$

		if (isVertical) {
			createLayoutVertical(data);
		} else {
			createLayoutHorizontal(data);
		}
		
		Logger.getInstance().writeMethodExit(this.getClass(), "createLayout");  //$NON-NLS-1$
	}
	
	private void createLayoutVertical(final Button[] data) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "createLayoutVertical", data);  //$NON-NLS-1$

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
	    
		Logger.getInstance().writeMethodExit(this.getClass(), "createLayoutVertical");  //$NON-NLS-1$
	}
	
	private void createLayoutHorizontal(final Button[] data) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "createLayoutHorizontal", data);  //$NON-NLS-1$

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
	    
		Logger.getInstance().writeMethodExit(this.getClass(), "createLayoutHorizontal");  //$NON-NLS-1$
	}
}