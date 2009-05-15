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

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;


/**
 * This is a group to add 1-n components (e.g. useful with JButtons).
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public class Group extends Panel {
	private static final long serialVersionUID = -3557759501854611930L;
	

	public Group(final JComponent[] data, final Insets insets) {
		super();
		createLayout(data, insets, false);
	}
	
	public Group(final JComponent[] data, final Insets insets, final boolean isVertical) {
		super();
		createLayout(data, insets, isVertical);
	}

	public void addActionListener(final ActionListener listener) {
		final Component[] components = getComponents();
		for (final Component component : components) {
	    	if (component instanceof AbstractButton) {
	    		((AbstractButton) component).addActionListener(listener);
			}
	    }
	}
	
	public void removeActionListener(final ActionListener listener) {
		final Component[] components = getComponents();
		for (final Component component : components) {
	    	if (component instanceof AbstractButton) {
	    		((AbstractButton) component).removeActionListener(listener);
			}
	    }
	} 
	
	
	/*
	 * Private methods
	 */
	private void createLayout(final JComponent[] data, final Insets insets, final boolean isVertical) {
		if (isVertical) {
			createLayoutVertical(data, insets);
		} else {
			createLayoutHorizontal(data, insets);
		}
	}
	
	private void createLayoutVertical(final JComponent[] data, final Insets insets) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
	    gbc.insets = insets;
	   
	    if (data != null) {
	    	for (final JComponent button : data) {
				gbc.gridy++;
		    	add(button, gbc);
		    }
	    }
	}
	
	private void createLayoutHorizontal(final JComponent[] data, final Insets insets) {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
	    gbc.insets = insets;

	    if (data != null) {
	    	for (final JComponent button : data) {
				gbc.gridx++;
		    	add(button, gbc);
		    }
	    }
	}

	
	/*
	 * Overridden methods
	 */
//	@Override
//	public void setBackground(final Color bg) {
//		super.setBackground(bg);
//		
//	    for (final Component component : getComponents()) {
//	    	if (component instanceof JComponent) {
//	    		((JComponent)component).setBackground(bg);
//	    	}
//	    }
//	}
//	
//	@Override
//	public void setForeground(final Color fg) {
//	    super.setForeground(fg);
//		
//		for (final Component component : getComponents()) {
//	    	if (component instanceof JComponent) {
//	    		((JComponent)component).setForeground(fg);
//	    	}
//	    }
//	}
	
	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
	    for (final Component component : getComponents()) {
            component.setFont(font);
	    }
	}
}