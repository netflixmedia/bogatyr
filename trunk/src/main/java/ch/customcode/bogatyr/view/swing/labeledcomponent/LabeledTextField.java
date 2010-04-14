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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.view.swing.labeledcomponent;

import javax.swing.text.Document;

import ch.customcode.bogatyr.view.swing.TextField;


/**
 * This is a Label combined with a TextField.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100203)
 * @since 0.2.0
 */
public class LabeledTextField extends LabeledComponent {
	private static final long serialVersionUID = 1310593497620798003L;
	
	public LabeledTextField(final String labelText, final String toolTip, final String text) {
		super(labelText, toolTip, new TextField(text, toolTip));
	}

	public LabeledTextField(final String title, final String labelText, final String toolTip, final String text) {
		super(title, labelText, toolTip, new TextField(text, toolTip));
	}
	
	public LabeledTextField(final String labelText, final String toolTip, final String text, final Document document, final int columns) {
		super(labelText, toolTip, new TextField(text, toolTip, document, columns));
	}

	public LabeledTextField(final String title, final String labelText, final String toolTip, final String text, final Document document, final int columns) {
		super(title, labelText, toolTip, new TextField(text, toolTip, document, columns));
	}

	public TextField getTextField() {
		return (TextField)getComponent();
	}
}