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

import java.awt.Cursor;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;


/**
 * Display a splash-screen manual or for a desired time.
 * 
 * @author Stefan Laubenberger
 * @version 20081120
 */
public class SplashScreen extends Frame { //TODO document in Wiki!
	private static final long serialVersionUID = 8819965047277292543L;
	
	private final ImageIcon splash;


	public SplashScreen(final String title, final Image icon, final ImageIcon splash) {
		super(title, icon);

        this.splash = splash;
		
		createLayout();
	}
	
	/**
     * Display the splash-screen for the desired time
     *
     * @param displayTime Duration in ms
     */	
	public void display(final long displayTime) {
		createAndShowGUI();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				clearAndHide();
			}
		}, displayTime);
	}
	
	/**
     * Display the splash-screen
     */	
	public void display() {
		createAndShowGUI();
	}  
	

	/*
	 * Private methods
	 */
	private void createLayout() {
        if (splash != null && splash.getImageLoadStatus() == MediaTracker.COMPLETE) {
			
			final Label label = new Label(splash);
//			splash.setImageObserver(label);
			
			getContentPane().add(label);

//			pack();
			setSize(splash.getIconWidth(), splash.getIconHeight());
			setUndecorated(true);
			setAlwaysOnTop(true);
			setLocationRelativeTo(null);
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
		}
	}
}
