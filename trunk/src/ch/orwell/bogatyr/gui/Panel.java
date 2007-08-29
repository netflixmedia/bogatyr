/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import java.awt.Component;

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
 * @version 20070829
 */
public class Panel extends JPanel {
	private static final long serialVersionUID = 3027031734134184715L;
	protected String className;
	
	private String title;
	
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
	 * @param title Title of the Panel
	 */
	public Panel(String title) {
		super();
		init();
		this.title = title;
		setTitle(title);
	}
	
	/**
     * Set the title of the Panel
     * @param title Title of the Panel
     */	
	public void setTitle(String title){
//		setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), title));
		this.title = title;
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
	}

	/**
     * Get the title of the Panel
     * @return String title of the Panel
     */	
	public String getTitle(){
		return this.title;
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
	
	
	/*
	 * Overriden methods
	 */
	@Override
	public void setEnabled(boolean flag) {
		super.setEnabled(flag);
		
		Component[] components = this.getComponents();
	    for (int ii = 0; ii < components.length; ii++) {
	    	components[ii].setEnabled(flag);
	    }
	}
}