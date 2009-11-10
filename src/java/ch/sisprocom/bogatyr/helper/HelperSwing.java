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
package ch.sisprocom.bogatyr.helper;

import java.util.HashMap;
import java.util.Map;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;


/**
 * This is a helper class for Swing.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091110)
 * @since 0.9.0
 */
public abstract class HelperSwing {

    /**
     * Sets the menu on MacOSX to the standard style.
     * This method should be used before creating a new frame and after setting the L&F.
     *
     * @since 0.9.0
     */
	public static void setMacOSXMenu() {
		if (HelperEnvironment.isMacPlatform()) {
			//display the menu in MacOS X style
			try {
				System.setProperty("apple.laf.useScreenMenuBar", "true"); //$NON-NLS-1$ //$NON-NLS-2$

				final LookAndFeel laf = UIManager.getLookAndFeel();
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				
				final Map<Object, Object> map = new HashMap<Object, Object>(6);
				
				map.put("MenuBarUI", UIManager.get("MenuBarUI")); //$NON-NLS-1$ //$NON-NLS-2$
				map.put("MenuUI", UIManager.get("MenuUI"));  //$NON-NLS-1$//$NON-NLS-2$
				map.put("MenuItemUI", UIManager.get("MenuItemUI")); //$NON-NLS-1$ //$NON-NLS-2$
				map.put("CheckBoxMenuItemUI", UIManager.get("CheckBoxMenuItemUI"));  //$NON-NLS-1$//$NON-NLS-2$
				map.put("RadioButtonMenuItemUI", UIManager.get("RadioButtonMenuItemUI")); //$NON-NLS-1$ //$NON-NLS-2$
				map.put("PopupMenuUI", UIManager.get("PopupMenuUI")); //$NON-NLS-1$ //$NON-NLS-2$
				
				UIManager.setLookAndFeel(laf);
				
		        for (final Map.Entry<?, ?> pair : map.entrySet()) {
					UIManager.put(pair.getKey(), pair.getValue());
		        }
		    } catch(Exception ex) {
		    	//do nothing
		    }
		}
	}
}