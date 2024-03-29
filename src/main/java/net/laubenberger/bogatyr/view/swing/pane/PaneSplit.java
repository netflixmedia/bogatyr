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

import javax.swing.JSplitPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;


/**
 * This is an extended JSplitPane.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.7.0
 */
public class PaneSplit extends JSplitPane {
	private static final long serialVersionUID = 2243720263917281740L;

	private static final Logger log = LoggerFactory.getLogger(PaneSplit.class);

	/*
	 * Superclass constructors
	 */

	public PaneSplit() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public PaneSplit(final int newOrientation, final boolean newContinuousLayout,
						  final Component newLeftComponent, final Component newRightComponent) {
		super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent));
	}

	public PaneSplit(final int newOrientation, final boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(newOrientation, newContinuousLayout));
	}

	public PaneSplit(final int newOrientation, final Component newLeftComponent,
						  final Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(newOrientation, newLeftComponent, newRightComponent));
	}

	public PaneSplit(final int newOrientation) {
		super(newOrientation);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(newOrientation));
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