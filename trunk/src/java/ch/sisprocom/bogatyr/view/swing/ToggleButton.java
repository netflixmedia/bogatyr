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

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JToggleButton;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JToggleButton.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.70
 */
public class ToggleButton extends JToggleButton implements IComponentActivate {
	private static final long serialVersionUID = 7669429243607853809L;
	
	private boolean isNotActive;

	
	public ToggleButton() {
		super();
	}
	
	public ToggleButton(final Action a) {
		super(a);
	}

	public ToggleButton(final Icon icon, final boolean selected) {
		super(icon, selected);
	}

	public ToggleButton(final Icon icon) {
		super(icon);
	}

	public ToggleButton(final String text, final boolean selected) {
		super(text, selected);
	}

	public ToggleButton(final String text, final Icon icon, final boolean selected) {
		super(text, icon, selected);
	}

	public ToggleButton(final String text, final Icon icon) {
		super(text, icon);
	}

	public ToggleButton(final String text) {
		super(text);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	

	@Override
	public void setEnabled(final boolean isEnabled) {
		if (!isNotActive) {
			super.setEnabled(isEnabled);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	public boolean isActive() {
		return !isNotActive;
	}

	public void setActive(final boolean isActive) {
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
	}
}