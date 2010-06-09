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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.view.swing;

import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.Displayable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JDialog.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100610)
 * @since 0.4.0
 */
public class Dialog extends JDialog implements Displayable {
	private static final long serialVersionUID = -3903296901431213544L;

	private static final Logger log = LoggerFactory.getLogger(Dialog.class);

//	static {
//		JDialog.setDefaultLookAndFeelDecorated(true);
//	}

	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getOwner());
	}

	/*
	 * Superclass constructors
	 */

	public Dialog() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public Dialog(final java.awt.Dialog owner, final boolean modal) {
		super(owner, modal);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, modal));
	}

	public Dialog(final java.awt.Dialog owner, final String title, final boolean modal,
					  final GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, title, modal, gc));
	}

	public Dialog(final java.awt.Dialog owner, final String title, final boolean modal) {
		super(owner, title, modal);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, title, modal));
	}

	public Dialog(final java.awt.Dialog owner, final String title) {
		super(owner, title);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, title));
	}

	public Dialog(final java.awt.Dialog owner) {
		super(owner);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner));
	}

	public Dialog(final Frame owner, final boolean modal) {
		super(owner, modal);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, modal));
	}

	public Dialog(final Frame owner, final String title, final boolean modal,
					  final GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, title, modal, gc));
	}

	public Dialog(final Frame owner, final String title, final boolean modal) {
		super(owner, title, modal);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, title, modal));
	}

	public Dialog(final Frame owner, final String title) {
		super(owner, title);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner, title));
	}

	public Dialog(final Frame owner) {
		super(owner);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(owner));
	}

	public Dialog(final Window arg0, final ModalityType arg1) {
		super(arg0, arg1);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(arg0, arg1));
	}

	public Dialog(final Window arg0, final String arg1, final ModalityType arg2,
					  final GraphicsConfiguration arg3) {
		super(arg0, arg1, arg2, arg3);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(arg0, arg1, arg2, arg3));
	}

	public Dialog(final Window arg0, final String arg1, final ModalityType arg2) {
		super(arg0, arg1, arg2);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(arg0, arg1, arg2));
	}

	public Dialog(final Window arg0, final String arg1) {
		super(arg0, arg1);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(arg0, arg1));
	}

	public Dialog(final Window arg0) {
		super(arg0);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(arg0));
	}

	
	/*
	 * Implemented methods
	 */

	@Override
	public void createAndShowGUI() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		setLocationRelativeTo(getOwner());
		setVisible(true);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void clearAndHide() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		dispose();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}