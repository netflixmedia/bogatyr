/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;


/**
 * Display a splash-screen
 * 
 * @author Stefan Laubenberger
 * @version 20081026
 */
public class SplashScreen extends Frame {
	private static final long serialVersionUID = 8819965047277292543L;
	
	private final ImageIcon image;
	private static final int BORDER_WIDTH = 40;


	public SplashScreen(final String title, final Image icon, final ImageIcon splash) {
		super(title, icon);

        image = splash;
		
		createLayout();
	}
	
	/**
     * Display the splash-screen for the desired time
     *
     * @param displayTime Duration in ms
     */	
	public void display(final long displayTime) {
		setVisible(true);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				dispose();
			}
		}, displayTime);
	}
	
	/**
     * Display the splash-screen
     */	
	public void display() {
		setVisible(true);
	}  
	

	/*
	 * Private methods
	 */
	private void createLayout() {
        if (image != null && image.getImageLoadStatus() == MediaTracker.COMPLETE) {
            int sizeX = image.getIconWidth();
            int sizeY = image.getIconHeight();

            sizeX += BORDER_WIDTH * 2;
			sizeY += BORDER_WIDTH * 2;

			
			final Label label = new Label(image);
			
			label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,	Color.LIGHT_GRAY, Color.WHITE, Color.DARK_GRAY, Color.GRAY));
			getContentPane().add(label);

			setUndecorated(true);
			setSize(sizeX, sizeY);
			setAlwaysOnTop(true);
			setLocationRelativeTo(null);
		}
	}
}
