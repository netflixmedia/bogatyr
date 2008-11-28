/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JDialog.
 * 
 * @author Stefan Laubenberger
 * @version 20081120
 */
public class Dialog extends JDialog { //TODO document in Wiki!
	private static final long serialVersionUID = -3903296901431213544L;
	
	public Dialog() {
		super();
		init();
    }
	
	public Dialog(final JFrame frame, final String title) {
		super(frame, true);
		setTitle(title);
//		setLocationRelativeTo(frame);
//		setLocationRelativeTo(null);
		init();
    }
	
	public void createAndShowGUI() {
		setVisible(true);
	}

	public void clearAndHide() {
		dispose();
	}

	
	/*
	 * Private methods
	 */
	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}