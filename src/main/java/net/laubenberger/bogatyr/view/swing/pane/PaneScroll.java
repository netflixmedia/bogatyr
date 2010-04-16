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
package net.laubenberger.bogatyr.view.swing.pane;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.border.Border;

import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JScrollPane.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.3.0
 */
public class PaneScroll extends JScrollPane {
	private static final long serialVersionUID = 544751396135811303L;

	{
		setWheelScrollingEnabled(true);
		getVerticalScrollBar().setUnitIncrement(16);
		getHorizontalScrollBar().setUnitIncrement(16);
	}

	/*
	 * Superclass constructors
	 */

	public PaneScroll() {
		super();
	}

	public PaneScroll(final Component component) {
		super(component);
	}

	public PaneScroll(final Component view, final int vsbPolicy, final int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
	}

	public PaneScroll(final int vsbPolicy, final int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
	}

	/*
	 * Own constructors
	 */

	public PaneScroll(final Component component, final Border border) {
		this(component);
		setBorder(border);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}

	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);

		for (final Component component : getViewport().getComponents()) {
			component.setEnabled(enabled);
		}
	}
}