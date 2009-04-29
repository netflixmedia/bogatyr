/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing.pane;

import ch.sisprocom.bogatyr.helper.HelperGeneral;

import javax.swing.JEditorPane;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;


/**
 * This is an extended JEditorPane.
 * 
 * @author Stefan Laubenberger
 * @version 20090429
 */
public class PaneEditor extends JEditorPane {
	private static final long serialVersionUID = -3298005917085461997L;
	

	public PaneEditor() {
		super();
	}
	
	public PaneEditor(final String type, final String text) {
		super(type, text);
	}

	public PaneEditor(final String url) throws IOException {
		super(url);
	}

	public PaneEditor(final URL initialPage) throws IOException {
		super(initialPage);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		
		final Component[] components = getComponents();
	    for (final Component component : components) {
	    	component.setEnabled(enabled);
	    }
	}
}