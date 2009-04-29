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
package ch.sisprocom.bogatyr.view.swing;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended AbstractAction.
 * 
 * @author Stefan Laubenberger
 * @version 20090430
 */
public abstract class ActionAbstract extends AbstractAction {

//	Action.LONG_DESCRIPTION
//	Action.ACCELERATOR_KEY
//	Action.MNEMONIC_KEY
//	Action.SMALL_ICON
//	Action.LARGE_ICON_KEY

	
	protected ActionAbstract() {
		super();
	}

	protected ActionAbstract(final String name, final Icon icon) {
		super(name, icon);
	}

	protected ActionAbstract(final String name) {
		super(name);
	}
	
	protected ActionAbstract(final String name, final String toolTip) {
		super(name);
		putValue(SHORT_DESCRIPTION, toolTip);
	}

	protected ActionAbstract(final String name, final String toolTip, final Icon icon) {
		super(name, icon);
		putValue(SHORT_DESCRIPTION, toolTip);
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}