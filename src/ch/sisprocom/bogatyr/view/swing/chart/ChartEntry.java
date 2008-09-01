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
package ch.sisprocom.bogatyr.view.swing.chart;

import javax.swing.JComponent;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.logger.Logger;

/**
 * ChartEntry
 * 
 * @author Silvan Spross
 * @author Stefan Laubenberger
 * @version 20080810
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
		
		init();
	}

    public JComponent getComponent() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getComponent");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getComponent", component);  //$NON-NLS-1$

		return component;
	}

	public int getSizeX() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getSizeX");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getSizeX", sizeX);  //$NON-NLS-1$

		return sizeX;
	}

	public int getSizeY() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getSizeY");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getSizeY", sizeY);  //$NON-NLS-1$

		return sizeY;
	}

	public int getX() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getX");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getX", x);  //$NON-NLS-1$

		return x;
	}

	public int getY() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getY");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getY", y);  //$NON-NLS-1$

		return y;
	}

	
	/*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
