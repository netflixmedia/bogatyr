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

import javax.swing.JTabbedPane;

import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JTabbedPane.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public class PaneTab extends JTabbedPane {
	private static final long serialVersionUID = -4529079733239863943L;

	/*
	 * Superclass constructors
	 */

	public PaneTab() {
		super();
	}

	public PaneTab(final int tabPlacement, final int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
	}

	public PaneTab(final int tabPlacement) {
		super(tabPlacement);
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

		for (final Component component : getComponents()) {
			component.setEnabled(enabled);
		}
	}
}