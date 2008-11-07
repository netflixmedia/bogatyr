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

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JPanel
 * 
 * @author Stefan Laubenberger
 * @version 20081026
 */
public class Panel extends JPanel {
	private static final long serialVersionUID = 3679443739459084931L;
	
	private String title;
	

	public Panel() {
		super();
		init();
	}

	public Panel(final String title) {
		this();
		this.title = title;
		setTitle(title);
	}

	public Panel(final Color color) {
		this();
		setBackground(color);
	}
	
	/**
     * Set the title of the Panel
     * @param title Title of the Panel
     */	
	public void setTitle(final String title){
		this.title = title;
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
	}

	/**
     * Get the title of the Panel
     * @return String title of the Panel
     */	
	public String getTitle(){
		return title;
	}
	

	/*
	 * Private methods
	 */
	private void init() {
		setLayout(new GridBagLayout());
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		
		final Component[] components = getComponents();
	    for (final Component component : components) {
	    	component.setEnabled(enabled);
	    }
	}
	
	@Override
	public void setToolTipText(final String text) {
		if (text != null) {
            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
	}
	
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}