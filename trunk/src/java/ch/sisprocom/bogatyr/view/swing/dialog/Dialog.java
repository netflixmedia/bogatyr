/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing.dialog;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JDialog;
import javax.swing.JFrame;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.view.swing.IComponentAntiAliasing;


/**
 * This is an extended JDialog.
 * 
 * @author Stefan Laubenberger
 * @version 20090421
 */
public class Dialog extends JDialog implements IComponentAntiAliasing {
	private static final long serialVersionUID = -3903296901431213544L;
	
	private boolean isAntiAliasing = true;
	
	private JFrame frame;
	
	
	public Dialog() {
		super();
		init();
    }
	
	public Dialog(final JFrame frame, final String title) {
		super(frame, true);
		
		this.frame = frame;
		
		setTitle(title);
		init();
    }
	
	public void createAndShowGUI() {
		setLocationRelativeTo(frame);
		setVisible(true);
	}

	public void clearAndHide() {
		dispose();
	}

	
	/*
	 * Private methods
	 */
	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void paint(final Graphics g) {
		if (isAntiAliasing) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			super.paint(g2d);
		} else {
			super.paint(g);
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