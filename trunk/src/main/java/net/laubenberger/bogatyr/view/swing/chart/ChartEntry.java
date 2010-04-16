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

package net.laubenberger.bogatyr.view.swing.chart;

import net.laubenberger.bogatyr.helper.HelperObject;

import javax.swing.JComponent;


/**
 * ChartEntry
 *
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.5.0
 */
public class ChartEntry {
	private final JComponent component;
	private final int x;
	private final int y;
	private final int sizeX;
	private final int sizeY;


	public ChartEntry(final JComponent component, final int x, final int y, final int sizeX, final int sizeY) {
		super();
		this.component = component;
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public JComponent getComponent() {
		return component;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}
