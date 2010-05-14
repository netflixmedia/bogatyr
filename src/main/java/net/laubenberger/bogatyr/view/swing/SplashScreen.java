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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.view.swing;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;

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
 * @version 0.9.2 (20100514)
 * @since 0.1.0
 */
public class SplashScreen extends Frame {
	private static final long serialVersionUID = 8819965047277292543L;

	private static final Logger log = LoggerFactory.getLogger(SplashScreen.class);

	private final ImageIcon splash;


	public SplashScreen(final String title, final Image icon, final ImageIcon splash) {
		super(title, icon);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, icon, splash));

		this.splash = splash;

		createLayout();
	}

	/**
	 * Displays the splash-screen for the desired time
	 *
	 * @param displayTime Duration in ms
	 * @since 0.1.0
	 */
	public void display(final long displayTime) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(displayTime));

		createAndShowGUI();
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				clearAndHide();
			}
		}, displayTime);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Displays the splash-screen
	 *
	 * @since 0.1.0
	 */
	public void display() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		createAndShowGUI();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Hides the splash-screen
	 *
	 * @since 0.9.1
	 */
	@Override
	public void hide() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		clearAndHide();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}


	/*
		 * Private methods
		 */

	private void createLayout() {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart());

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

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit());
	}
}
