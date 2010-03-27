/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.view.swing.chart;

import ch.customcode.bogatyr.helper.HelperObject;

import javax.swing.JComponent;


/**
 * ChartEntry
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091016)
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
		this.x		= x;
		this.y		= y;
		this.sizeX 	= sizeX;
		this.sizeY 	= sizeY;
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
