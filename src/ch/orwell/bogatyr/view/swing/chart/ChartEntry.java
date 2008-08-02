/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU General Public License for more details:
 * ----------------------------------------------------
 * <http://www.gnu.org/licenses>
 * 
 * This distribution is available at:
 * ----------------------------------
 * <http://code.google.com/p/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.view.swing.chart;

import javax.swing.JComponent;

import ch.orwell.bogatyr.helper.HelperGeneral;

/**
 * ChartEntry
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 20080728
 */
public class ChartEntry {
	private final JComponent component;
	private final int x, y, sizeX, sizeY;
	
	
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
		return HelperGeneral.toString(this);
	}
}
