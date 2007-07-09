/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ch.orwell.bogatyr.Context;
import ch.orwell.bogatyr.util.Localizer;
import ch.orwell.bogatyr.util.Logger;


/**
 * This is an extended JPanel
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public class Panel extends JPanel {
	private static final long serialVersionUID = 3027031734134184715L;
	protected String className;
	
	/**
	 * Constructs a Panel without a title.
	 */
	public Panel() {
		super();
		init();
//		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
	}

	/**
	 * Constructs a Panel with a title.
	 * @param title Title of the panel
	 */
	public Panel(String title) {
		super();
		init();
		setTitle(title);
	}
	
	/**
     * Set the title of the panel
     * @param title Title of the panel
     */	
	public void setTitle(String title){
//		setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), title));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
	}
	

	/*
	 * Private methods
	 */
	/**
	 * Initialize the object
	 * Do some logging.
	 */
	private void init() {
		this.className = this.getClass().getName();
		Logger.getInstance().writeDebug(this.className + "::init", Context.getInstance().getLocalizer().getValue(Localizer.RES_INSTANCIATED) + toString()); //$NON-NLS-1$
	}
}