/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.view.swing.pane;

import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.border.Border;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;


/**
 * This is an extended JScrollPane.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.3.0
 */
public class PaneScroll extends JScrollPane {
	private static final long serialVersionUID = 544751396135811303L;

	private static final Logger log = LoggerFactory.getLogger(PaneScroll.class);

	private static final int DEFAULT_INCREMENT = 16;

	{
		setWheelScrollingEnabled(true);
		getVerticalScrollBar().setUnitIncrement(DEFAULT_INCREMENT);
		getHorizontalScrollBar().setUnitIncrement(DEFAULT_INCREMENT);
	}

	/*
	 * Superclass constructors
	 */

	public PaneScroll() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public PaneScroll(final Component component) {
		super(component);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(component));
	}

	public PaneScroll(final Component view, final int vsbPolicy, final int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(view, vsbPolicy, hsbPolicy));
	}

	public PaneScroll(final int vsbPolicy, final int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(vsbPolicy, hsbPolicy));
	}

	/*
	 * Own constructors
	 */

	public PaneScroll(final Component component, final Border border) {
		this(component);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(component, border));

		setBorder(border);
	}


	/*
	 * Overridden methods
	 */

	@Override
	public void setEnabled(final boolean isEnabled) {
		super.setEnabled(isEnabled);

		for (final Component component : getComponents()) {
			component.setEnabled(isEnabled);
		}
	}
}