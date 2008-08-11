/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.orwell.bogatyr.view.swing;

import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;

import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * Display a splash-screen
 * 
 * @author Stefan Laubenberger
 * @version 20080810
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
		Logger.getInstance().writeMethodEntry(this.getClass(), "display", displayTime);  //$NON-NLS-1$

		setVisible(true);
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				dispose();
			}
		}, displayTime);
		
		Logger.getInstance().writeMethodExit(this.getClass(), "display");  //$NON-NLS-1$
	}
	
	/**
     * Display the splash-screen
     */	
	public void display() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "display");  //$NON-NLS-1$

		setVisible(true);

		Logger.getInstance().writeMethodExit(this.getClass(), "display");  //$NON-NLS-1$
	}  
	

	/*
	 * Private methods
	 */
	private void createLayout() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "createLayout");  //$NON-NLS-1$

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

        Logger.getInstance().writeMethodExit(this.getClass(), "createLayout");  //$NON-NLS-1$
	}
}