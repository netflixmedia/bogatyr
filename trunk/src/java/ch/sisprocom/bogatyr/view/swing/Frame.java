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
package ch.sisprocom.bogatyr.view.swing;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JFrame.
 * 
 * @author Stefan Laubenberger
 * @version 20090113
 */
public class Frame extends JFrame {
	private static final long serialVersionUID = 7476360387134225315L;

	private Color colorFader = new Color(0, 0, 0, 100);
	
	
	public Frame() {
		super();
		init();
    }
	
	public Frame(final String title, final Image icon) {
		this();
		setTitle(title);
        setIconImage(icon);
    }
	
	public void createAndShowGUI() {
		setVisible(true);
	}

	public void clearAndHide() {
		dispose();
	}
	
	public void setFading(boolean isFading) {
		final JPanel panelGlass = (JPanel) getGlassPane();
		panelGlass.removeAll();
		
		if (isFading) {
	        panelGlass.setVisible(true);
	        panelGlass.setLayout(new GridBagLayout());
	
	        final GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill    = GridBagConstraints.BOTH;
	        gbc.weighty = 1.0D;
	        gbc.weightx = 1.0D;
	
//	    if (isFading) {
	        panelGlass.add(new Panel(colorFader), gbc);
//		} else {
//			panelGlass.add(new Panel(new Color(255, 255, 255, 0)), gbc);
		}
		
        validate();
        repaint();
	}
	
	public void setFaderColor(Color colorFader) {
		this.colorFader = colorFader;
	}
	
	/*
	 * Private methods
	 */
	private void init() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}