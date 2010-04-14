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
import ch.customcode.bogatyr.misc.Activatable;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JToggleButton;


/**
 * This is an extended JToggleButton.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public class ToggleButton extends JToggleButton implements Activatable {
	private static final long serialVersionUID = 7669429243607853809L;
	
	private boolean isNotActive;

	/*
	 * Superclass constructors
	 */
	public ToggleButton() {
		super();
	}
	
	public ToggleButton(final Action action) {
		super(action);
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
	@Override
    public boolean isActive() {
		return !isNotActive;
	}

	@Override
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