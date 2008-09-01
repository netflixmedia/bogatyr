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

import java.awt.Image;

import javax.swing.JFrame;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is an extended JFrame
 * 
 * @author Stefan Laubenberger
 * @version 20080810
 */
public class Frame extends JFrame {
	private static final long serialVersionUID = 7476360387134225315L;

	
	public Frame() {
		super();
		init();
    }
	
	public Frame(final String title, final Image icon) {
		this();
		setTitle(title);
        setIconImage(icon);
    }
	
	
	/*
	 * Private methods
	 */
	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
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