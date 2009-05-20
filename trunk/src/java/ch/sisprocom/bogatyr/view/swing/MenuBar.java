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

import javax.swing.JMenuBar;
import javax.swing.UIManager;

import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JMenuBar.
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -5107664209576098148L;
	
	static {
		if (HelperEnvironment.isMacPlatform()) {
			//display the menu in MacOS X style
			try {
	            System.setProperty("apple.laf.useScreenMenuBar", "true"); //$NON-NLS-1$ //$NON-NLS-2$
	            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Bogatyr");  //$NON-NLS-1$//$NON-NLS-2$
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } catch(Exception ex) {
		    	//do nothing
		    }
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