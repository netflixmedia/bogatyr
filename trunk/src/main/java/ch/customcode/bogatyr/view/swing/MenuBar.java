/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.view.swing;

import javax.swing.JMenuBar;

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * This is an extended JMenuBar.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.2.0
 */
public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -5107664209576098148L;

	/*
	 * Superclass constructors
	 */
    public MenuBar() {
		super();
	}

    
	/*
      * Overridden methods
      */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}