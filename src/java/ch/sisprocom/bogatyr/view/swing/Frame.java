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

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.HelperSwing;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;


/**
 * This is an extended JFrame.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091016)
 * @since 0.2.0
 */
public class Frame extends JFrame implements Fadeable {
	private static final long serialVersionUID = 7476360387134225315L;

	private boolean isFading;
	
	private Color colorFader = new Color(0, 0, 0, 100);
	
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		HelperSwing.setMacOSXMenu();
	}
	
	public Frame() {
		super();
    }

	public Frame(final GraphicsConfiguration gc) {
		super(gc);
	}

	public Frame(final String title, final GraphicsConfiguration gc) {
		super(title, gc);
	}

	public Frame(final String title) throws HeadlessException {
		super(title);
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
	
//	/**
//     * Refresh the frame (validate() and repaint() in one method)
//     */	
//	public void refresh() {
//		validate();
//		repaint();
//	}
	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
	@Override
	public void setEnabled(final boolean isEnabled) {
		super.setEnabled(isEnabled);
		
		final Component[] components = getComponents();
	    for (final Component component : components) {
	    	component.setEnabled(isEnabled);
	    }
	}
	
	
	/*
	 * Implemented methods
	 */
	@Override
    public void setFading(final boolean isFading) {
		this.isFading = isFading;
		
		final Container panelGlass = (Container) getGlassPane();
		panelGlass.removeAll();
		
		panelGlass.setVisible(isFading);
		
		if (isFading) {
	        panelGlass.setLayout(new GridBagLayout());
	
	        final GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill    = GridBagConstraints.BOTH;
	        gbc.weighty = 1.0D;
	        gbc.weightx = 1.0D;
	
//	    if (isFading) {
	        panelGlass.add(new Panel(colorFader), gbc);
		}
		
        validate();
        repaint();
	}
	
	@Override
    public void setFaderColor(final Color colorFader) {
		this.colorFader = colorFader;
	}

	@Override
    public boolean isFading() {
		return isFading;
	}
}