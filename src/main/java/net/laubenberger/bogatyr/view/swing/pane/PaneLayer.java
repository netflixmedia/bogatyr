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

import net.laubenberger.bogatyr.helper.HelperLog;
import javax.swing.JLayeredPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Component;


/**
 * This is an extended JLayeredPane.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.7.0
 */
public class PaneLayer extends JLayeredPane {
	private static final long serialVersionUID = -6557519717085237490L;

	private static final Logger log = LoggerFactory.getLogger(PaneLayer.class);

	/*
	 * Superclass constructors
	 */

	public PaneLayer() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
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