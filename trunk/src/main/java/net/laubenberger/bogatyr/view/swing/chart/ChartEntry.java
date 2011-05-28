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

package net.laubenberger.bogatyr.view.swing.chart;

import javax.swing.JComponent;

import net.laubenberger.bogatyr.helper.HelperLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ChartEntry
 *
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.5.0
 */
public class ChartEntry {
	private static final Logger log = LoggerFactory.getLogger(ChartEntry.class);

	private final JComponent component;
	private final int x;
	private final int y;
	private final int sizeX;
	private final int sizeY;


	public ChartEntry(final JComponent component, final int x, final int y, final int sizeX, final int sizeY) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(component, x, y, sizeX, sizeY));

		this.component = component;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public JComponent getComponent() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(component));
		return component;
	}

	public int getSizeX() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(sizeX));
		return sizeX;
	}

	public int getSizeY() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(sizeY));
		return sizeY;
	}

	public int getX() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(x));
		return x;
	}

	public int getY() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(y));
		return y;
	}
}
