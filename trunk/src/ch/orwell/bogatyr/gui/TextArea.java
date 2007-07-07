/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.gui;

import javax.swing.JTextArea;


/**
 * This is an extended TextArea
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public class TextArea extends JTextArea {
	private static final long serialVersionUID = -801724496277932349L;
    
	
	/*
	 * Overriden methods
	 */
	@Override
	public void append(String text) {
		super.append(text);
		this.setCaretPosition(this.getDocument().getLength());
	}
}