/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import ch.orwell.bogatyr.Context;


/**
 * This is a helper class for GUI-components
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public abstract class GuiHelper {
	// MenuItem types
	public static final int MENU_ITEM_PLAIN = 0;
	public static final int MENU_ITEM_CHECK = 1;
	public static final int MENU_ITEM_RADIO = 2;	

	/**
     * Creates a JMenuItem
     * 
     * @param type Type (0 = Plain, 1 = Radio, 2 = Check)
     * @param text Text
     * @param image Picture
     * @param mnemonic Mnemonic
     * @param toolTip ToolTip
     * @return JMenuItem
     * @see JMenuItem
     */	
	public static JMenuItem createMenuItem(int type, String text, ImageIcon image, int mnemonic, String toolTip) {
		// Create the item
		JMenuItem menuItem;

		switch(type) {
			case MENU_ITEM_RADIO:
				menuItem = new JRadioButtonMenuItem();
				break;

			case MENU_ITEM_CHECK:
				menuItem = new JCheckBoxMenuItem();
				break;

			default:
				menuItem = new JMenuItem();
				break;
		}

		// Add the item text
		menuItem.setText(text);

		// Add the optional icon
		if (image != null)
			menuItem.setIcon(image);

		// Add the mnemonic key
		if (mnemonic > 0) {
			menuItem.setMnemonic(mnemonic);
		}

		// Add the optional tool tip text
		if (toolTip != null) {
			menuItem.setToolTipText(toolTip);
		}

		return menuItem;
	}
	
	/**
     * Creates a JMenuItem
     * 
     * @param type Type (0 = Plain, 1 = Radio, 2 = Check)
     * @param key Localizer
     * @param image Picture
     * @return JMenuItem
     * @see JMenuItem
     */	
	public static JMenuItem createMenuItem(int type, String key, ImageIcon image) {
		// Create the item
		JMenuItem menuItem;

		switch(type) {
			case MENU_ITEM_RADIO:
				menuItem = new JRadioButtonMenuItem();
				break;

			case MENU_ITEM_CHECK:
				menuItem = new JCheckBoxMenuItem();
				break;

			default:
				menuItem = new JMenuItem();
				break;
		}

		// Add the item text
		menuItem.setText(Context.getInstance().getLocalizer().getValue(key));

		// Add the optional icon
		if (image != null)
			menuItem.setIcon(image);

		// Add the mnemonic key
		menuItem.setMnemonic(Context.getInstance().getLocalizer().getMnemonic(key));

		// Add the optional tool tip text
		menuItem.setToolTipText(Context.getInstance().getLocalizer().getTooltip(key));

		return menuItem;
	}
	/**
     * Creates a JMenu
     * 
     * @param key Localizer
     * @return JMenu
     * @see JMenu
     */	
	public static JMenu createMenu(String key) {
		// Create the item
		JMenu menu = new JMenu();

		// Add the menu text
		menu.setText(Context.getInstance().getLocalizer().getValue(key));

		// Add the mnemonic key
		menu.setMnemonic(Context.getInstance().getLocalizer().getMnemonic(key));

		return menu;
	}
}
