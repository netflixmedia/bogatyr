/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
 * <http://www.sisprocom.ch/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * SiSprocom GmbH
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing.pane;

import java.awt.Component;

import javax.swing.JSplitPane;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JSplitPane.
 * 
 * @author Stefan Laubenberger
 * @version 20090506
 */
public class PaneSplit extends JSplitPane {
	private static final long serialVersionUID = 2243720263917281740L;
	

	public PaneSplit() {
		super();
	}
	
	public PaneSplit(final int newOrientation, final boolean newContinuousLayout,
			final Component newLeftComponent, final Component newRightComponent) {
		super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
	}

	public PaneSplit(final int newOrientation, final boolean newContinuousLayout) {
		super(newOrientation, newContinuousLayout);
	}

	public PaneSplit(final int newOrientation, final Component newLeftComponent,
			final Component newRightComponent) {
		super(newOrientation, newLeftComponent, newRightComponent);
	}

	public PaneSplit(final int newOrientation) {
		super(newOrientation);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		
	    for (final Component component : getComponents()) {
	    	component.setEnabled(enabled);
	    }
	}
}