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
package ch.sisprocom.bogatyr.view.swing.labeledcomponent;

import ch.sisprocom.bogatyr.view.swing.TextArea;
import ch.sisprocom.bogatyr.view.swing.pane.PaneScroll;


/**
 * This is a Label combined with a TextArea.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.2.0
 */
public class LabeledTextArea extends LabeledComponent {
	private static final long serialVersionUID = -3385104817739873049L;
	
	
	public LabeledTextArea(final String labelText, final String toolTip, final String text, final int rows, final int columns) {
		super(labelText, toolTip, new PaneScroll(new TextArea(text, toolTip, rows, columns)));
	}

	public LabeledTextArea(final String title, final String toolTip, final String labelText, final String text, final int rows, final int columns) {
		super(title, labelText, toolTip, new PaneScroll(new TextArea(text, toolTip, rows, columns)));
	}

	public TextArea getTextArea() {
		return (TextArea)getComponent();
	}
}