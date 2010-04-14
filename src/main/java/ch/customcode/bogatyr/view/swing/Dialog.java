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

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.helper.HelperSwing;
import ch.customcode.bogatyr.misc.Displayable;

import javax.swing.JDialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;


/**
 * This is an extended JDialog.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.4.0
 */
public class Dialog extends JDialog implements Displayable {
	private static final long serialVersionUID = -3903296901431213544L;
	
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		HelperSwing.setMacOSXMenu();
	}
	
	/*
	 * Superclass constructors
	 */
	public Dialog() {
		super();
    }
	
	public Dialog(final Dialog owner, final boolean modal) {
		super(owner, modal);
	}

	public Dialog(final Dialog owner, final String title, final boolean modal,
			final GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	public Dialog(final Dialog owner, final String title, final boolean modal) {
		super(owner, title, modal);
	}

	public Dialog(final Dialog owner, final String title) {
		super(owner, title);
	}

	public Dialog(final Dialog owner) {
		super(owner);
	}

	public Dialog(final Frame owner, final boolean modal) {
		super(owner, modal);
	}

	public Dialog(final Frame owner, final String title, final boolean modal,
			final GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}

	public Dialog(final Frame owner, final String title, final boolean modal) {
		super(owner, title, modal);
	}

	public Dialog(final Frame owner, final String title) {
		super(owner, title);
	}

	public Dialog(final Frame owner) {
		super(owner);
	}

	public Dialog(final Window arg0, final ModalityType arg1) {
		super(arg0, arg1);
	}

	public Dialog(final Window arg0, final String arg1, final ModalityType arg2,
			final GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public Dialog(final Window arg0, final String arg1, final ModalityType arg2) {
		super(arg0, arg1, arg2);
	}

	public Dialog(final Window arg0, final String arg1) {
		super(arg0, arg1);
	}

	public Dialog(final Window arg0) {
		super(arg0);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	} 

	
	/*
	 * Implemented methods
	 */
	@Override
	public void createAndShowGUI() {
		setLocationRelativeTo(getOwner());
		setVisible(true);
	}

	@Override
	public void clearAndHide() {
		dispose();
	}
}