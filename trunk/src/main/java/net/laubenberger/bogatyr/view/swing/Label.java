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

import javax.swing.Icon;
import javax.swing.JLabel;

import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JLabel.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.2.0
 */
public class Label extends JLabel {
	private static final long serialVersionUID = 2440681846691377894L;

	/*
	 * Superclass constructors
	 */

	public Label() {
		super();
	}

	public Label(final Icon icon, final int horizontalAlignment) {
		super(icon, horizontalAlignment);
	}

	public Label(final String text, final Icon icon, final int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
	}

	public Label(final Icon icon) {
		super(icon);
	}

	public Label(final String text) {
		super(text);
	}

	public Label(final String text, final int horizontalAlignment) {
		super(text, horizontalAlignment);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}