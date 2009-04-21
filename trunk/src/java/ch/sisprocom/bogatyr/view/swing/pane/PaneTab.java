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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTabbedPane;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.view.swing.IComponentAntiAliasing;


/**
 * This is an extended JTabbedPane.
 * 
 * @author Stefan Laubenberger
 * @version 20090421
 */
public class PaneTab extends JTabbedPane implements IComponentAntiAliasing {
	private static final long serialVersionUID = -4529079733239863943L;
	
	private boolean isAntiAliasing = true;
	

	public PaneTab() {
		super();
	}

	public PaneTab(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
	}

	public PaneTab(int tabPlacement) {
		super(tabPlacement);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void paintComponent(final Graphics g) {
		if (isAntiAliasing) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			super.paintComponent(g2d);
		} else {
			super.paintComponent(g);
		}
	}
	
	
	/*
	 * Implemented methods
	 */
	public boolean isAntiAliasing() {
		return isAntiAliasing;
	}

	public void setAntiAliasing(final boolean isEnabled) {
		isAntiAliasing = isEnabled;
		final Component[] components = getComponents();
	    for (final Component component : components) {
	    	if (component instanceof IComponentAntiAliasing) {
	    		((IComponentAntiAliasing)component).setAntiAliasing(isEnabled);
	    	}
	    }
	} 
}