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
package ch.customcode.bogatyr.view.swing.labeledcomponent;

import ch.customcode.bogatyr.view.swing.ComboBox;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;


/**
 * This is a Label combined with a ComboBox.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.2.0
 */
public class LabeledComboBox extends LabeledComponent {
	private static final long serialVersionUID = -67296455436983811L;
	
	
	public LabeledComboBox(final String labelText, final String toolTip, final Object[] data) {
		super(labelText, toolTip, new ComboBox(data, null));
	}

	public LabeledComboBox(final String title, final String labelText, final String toolTip, final Object[] data) {
		super(title, labelText, toolTip, new ComboBox(data, null));
	}
	
	public ComboBox getComboBox() {
		return (ComboBox)getComponent();
	}

	public void addActionListener(final ActionListener listener) {
        ((JComboBox) getComponent()).addActionListener(listener);
	}
	
	public void removeActionListener(final ActionListener listener) {
        ((JComboBox) getComponent()).removeActionListener(listener);
	}
}