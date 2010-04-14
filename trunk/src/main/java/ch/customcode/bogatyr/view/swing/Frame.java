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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.view.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ch.customcode.bogatyr.helper.HelperObject;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.helper.HelperSwing;
import ch.customcode.bogatyr.misc.Displayable;
import ch.customcode.bogatyr.misc.Fadeable;


/**
 * This is an extended JFrame.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100228)
 * @since 0.2.0
 */
public class Frame extends JFrame implements Fadeable, Displayable {
	private static final long serialVersionUID = 7476360387134225315L;

	private boolean isFading;
	
	private Color colorFader = new Color(0, 0, 0, 100);
	
	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		HelperSwing.setMacOSXMenu();
	}
	
	/*
	 * Superclass constructors
	 */
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
	
	/*
	 * Own constructors
	 */
	public Frame(final String title, final Image icon) {
		this();
		setTitle(title);
        setIconImage(icon);
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
	public void createAndShowGUI() {
		setVisible(true);
	}

	@Override
	public void clearAndHide() {
		dispose();
	}
	
	@Override
    public void setFading(final boolean isFading) {
		this.isFading = isFading;

		if (HelperString.contains(UIManager.getLookAndFeel().toString(), "swing.plaf") || HelperString.contains(UIManager.getLookAndFeel().toString(), "apple.laf")) { //do the fade-effect only with original lafs
			final Container containerGlass = (Container) getGlassPane();
			containerGlass.removeAll();	
			containerGlass.setVisible(isFading);
	
			if (isFading) {
		        containerGlass.setLayout(new BorderLayout());
		        containerGlass.add(new Panel(colorFader), BorderLayout.CENTER);
			}
	
	        validate();
	        repaint();
		}
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