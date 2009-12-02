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
import javax.swing.JMenu;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JMenu.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091123)
 * @since 0.2.0
 */
public class Menu extends JMenu {
	private static final long serialVersionUID = -908869267540163157L;
	
	
	public Menu() {
		super();
    }
	
	public Menu(String text, boolean b) {
		super(text, b);
	}

	public Menu(String text) {
		super(text);
	}

	public Menu(final Action action) {
		super(action);
	}

	public Menu(final String text, final int mnemonic) {
		this(text);
		
		// Add the mnemonic key
		if (0 < mnemonic) {
			setMnemonic(mnemonic);
		}
    }

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}