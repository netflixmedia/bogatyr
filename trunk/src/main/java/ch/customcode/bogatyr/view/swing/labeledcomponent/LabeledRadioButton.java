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

import ch.customcode.bogatyr.view.swing.RadioButton;

import javax.swing.AbstractButton;
import javax.swing.Action;
import java.awt.event.ActionListener;


/**
 * This is a Label combined with a RadioButton.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090528)
 * @since 0.2.0
 */
public class LabeledRadioButton extends LabeledComponent {
	private static final long serialVersionUID = 3461718081893469685L;

	
	public LabeledRadioButton(final String labelText, final String toolTip, final boolean isSelected, final Action action) {
		super(labelText, toolTip, new RadioButton(isSelected, action));
	}
	
	public LabeledRadioButton(final String labelText, final String toolTip, final boolean isSelected) {
		super(labelText, toolTip, new RadioButton(isSelected, toolTip));
	}

	public LabeledRadioButton(final String title, final String labelText, final String toolTip, final boolean isSelected) {
		super(title, labelText, toolTip, new RadioButton(isSelected, toolTip));
	}

	public RadioButton getRadioButton() {
		return (RadioButton)getComponent();
	}
	
	public boolean isSelected() {
		return ((AbstractButton) getComponent()).isSelected();
	}
	
	public void addActionListener(final ActionListener listener) {
		((AbstractButton) getComponent()).addActionListener(listener);
	}
	
	public void removeActionListener(final ActionListener listener) {
		((AbstractButton) getComponent()).removeActionListener(listener);
	}
}