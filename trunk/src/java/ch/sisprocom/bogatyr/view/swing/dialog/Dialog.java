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
package ch.sisprocom.bogatyr.view.swing.dialog;

import ch.sisprocom.bogatyr.helper.HelperGeneral;

import javax.swing.JDialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;


/**
 * This is an extended JDialog.
 * 
 * @author Stefan Laubenberger
 * @version 20090511
 */
public class Dialog extends JDialog {
	private static final long serialVersionUID = -3903296901431213544L;
	
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public Dialog() {
		super();
    }
	
	public Dialog(Dialog owner, boolean modal) {
		super(owner, modal);
	}

	public Dialog(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	public Dialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public Dialog(Dialog owner, String title) {
		super(owner, title);
	}

	public Dialog(Dialog owner) {
		super(owner);
	}

	public Dialog(Frame owner, boolean modal) {
		super(owner, modal);
	}

	public Dialog(Frame owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	public Dialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public Dialog(Frame owner, String title) {
		super(owner, title);
	}

	public Dialog(Frame owner) {
		super(owner);
	}

	public Dialog(Window arg0, ModalityType arg1) {
		super(arg0, arg1);
	}

	public Dialog(Window arg0, String arg1, ModalityType arg2,
			GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public Dialog(Window arg0, String arg1, ModalityType arg2) {
		super(arg0, arg1, arg2);
	}

	public Dialog(Window arg0, String arg1) {
		super(arg0, arg1);
	}

	public Dialog(Window arg0) {
		super(arg0);
	}

	public void createAndShowGUI() {
//		setLocationRelativeTo(frame);
		setVisible(true);
	}

	public void clearAndHide() {
		dispose();
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	} 
}