/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing;

import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Display a splash-screen manual or for a desired time.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20091210)
 * @since 0.1.0
 */
public class SplashScreen extends Frame {
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
     * @since 0.1.0
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
     * 
     * @since 0.1.0
     */	
	public void display() {
		createAndShowGUI();
	}  
	

	/*
	 * Private methods
	 */
	private void createLayout() {
        if (null != splash && MediaTracker.COMPLETE == splash.getImageLoadStatus()) {
			
			final Component label = new Label(splash);
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
