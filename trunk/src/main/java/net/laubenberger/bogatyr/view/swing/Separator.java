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
package net.laubenberger.bogatyr.view.swing;

import java.awt.Color;

import javax.swing.JSeparator;

import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JSeparator.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.2.0
 */
public class Separator extends JSeparator {
	private static final long serialVersionUID = 544751396135811303L;

	/*
	 * Superclass constructors
	 */

	public Separator() {
		super();
	}

	public Separator(final int orientation) {
		super(orientation);
	}

	/*
	 * Own constructors
	 */

	public Separator(final Color color) {
		this();

		setAlignmentY(TOP_ALIGNMENT);
		setAlignmentX(LEFT_ALIGNMENT);
		setBackground(color);
		setForeground(color);
	}

	public Separator(final int orientation, final Color color) {
		this(orientation);

		setAlignmentY(TOP_ALIGNMENT);
		setAlignmentX(LEFT_ALIGNMENT);
		setBackground(color);
		setForeground(color);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}