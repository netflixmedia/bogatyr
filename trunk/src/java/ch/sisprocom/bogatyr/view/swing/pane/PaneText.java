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

import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JTextPane.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public class PaneText extends JTextPane {
	private static final long serialVersionUID = -3996840505161218721L;
	

	public PaneText() {
		super();
	}
	
	public PaneText(final StyledDocument doc) {
		super(doc);
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