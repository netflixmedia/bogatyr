/*******************************************************************************
 * Copyright (c) 2009-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.KeyStroke;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended AbstractAction.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public abstract class ActionAbstract extends AbstractAction {
	private static final long serialVersionUID = -2411318943212390523L;

//	Action.LONG_DESCRIPTION
//	Action.SMALL_ICON
//	Action.LARGE_ICON_KEY

	/*
	 * Superclass constructors
	 */
	protected ActionAbstract() {
		super();
	}

	protected ActionAbstract(final String name, final Icon icon) {
		super(name, icon);
	}

	protected ActionAbstract(final String name) {
		super(name);
	}

	/*
	 * Own constructors
	 */
	protected ActionAbstract(final String name, final String toolTip) {
		super(name);
		putValue(SHORT_DESCRIPTION, toolTip);
	}
	
	protected ActionAbstract(final String name, final String toolTip, final int mnemonic) {
		super(name);
		putValue(SHORT_DESCRIPTION, toolTip);
		putValue(MNEMONIC_KEY, mnemonic);
	}
	
	protected ActionAbstract(final String name, final String toolTip, final Icon icon) {
		super(name, icon);
		putValue(SHORT_DESCRIPTION, toolTip);
	}
	
	protected ActionAbstract(final String name, final String toolTip, final Icon icon, final int mnemonic) {
		super(name, icon);
		putValue(SHORT_DESCRIPTION, toolTip);
		putValue(MNEMONIC_KEY, mnemonic);
	}

	protected ActionAbstract(final String name, final String toolTip, final int mnemonic, final KeyStroke accelerator) {
		this(name, toolTip, mnemonic);
		putValue(ACCELERATOR_KEY, accelerator);
	}
	
	protected ActionAbstract(final String name, final String toolTip, final Icon icon, final int mnemonic, final KeyStroke accelerator) {
		this(name, toolTip, icon, mnemonic);
		putValue(ACCELERATOR_KEY, accelerator);
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}