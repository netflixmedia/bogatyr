/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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

import javax.swing.JMenu;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is an extended JMenu
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public class Menu extends JMenu {
	private static final long serialVersionUID = -908869267540163157L;

	
	public Menu() {
		super();
		init();
    }
	
	public Menu(final String text, final int mnemonic) {
		super(text);
		
		// Add the mnemonic key
		if (mnemonic > 0) {
			setMnemonic(mnemonic);
		}
		init();
    }
	
	
	/*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}