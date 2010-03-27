/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.view.swing.pane;

import java.awt.Component;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * This is an extended JEditorPane.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public class PaneEditor extends JEditorPane {
	private static final long serialVersionUID = -3298005917085461997L;
	
	/*
	 * Superclass constructors
	 */
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
		return HelperObject.toString(this);
	}
	
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		
	    for (final Component component : getComponents()) {
	    	component.setEnabled(enabled);
	    }
	}
}