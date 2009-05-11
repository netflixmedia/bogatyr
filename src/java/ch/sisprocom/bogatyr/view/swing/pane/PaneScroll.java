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
package ch.sisprocom.bogatyr.view.swing.pane;

import ch.sisprocom.bogatyr.helper.HelperGeneral;

import javax.swing.JScrollPane;
import javax.swing.border.Border;
import java.awt.Component;


/**
 * This is an extended JScrollPane.
 * 
 * @author Stefan Laubenberger
 * @version 20090511
 */
public class PaneScroll extends JScrollPane {
	private static final long serialVersionUID = 544751396135811303L;
	
	{
		setWheelScrollingEnabled(true);
	}
	
	
	public PaneScroll() {
		super();
    }
	
	public PaneScroll(final Component component) {
		super(component);
    }
	
	public PaneScroll(final Component component, final Border border) {
		this(component);
		setBorder(border);
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
		
	    for (final Component component : getComponents()) {
	    	component.setEnabled(enabled);
	    }
	}
}