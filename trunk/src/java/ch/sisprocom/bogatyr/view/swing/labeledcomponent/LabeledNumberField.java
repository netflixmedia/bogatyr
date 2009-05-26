/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing.labeledcomponent;

import ch.sisprocom.bogatyr.view.swing.NumberField;


/**
 * This is a Label combined with a NumberField.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.20
 */
public class LabeledNumberField extends LabeledComponent {
	private static final long serialVersionUID = 8536177338219909078L;

	public LabeledNumberField(final String labelText, final String toolTip, final Number number) {
		this(labelText, toolTip, number, Integer.MAX_VALUE);
	}

	public LabeledNumberField(final String title, final String labelText, final String toolTip, final Number number) {
		this(title, labelText, toolTip, number, Integer.MAX_VALUE);
	}
	
	public LabeledNumberField(final String labelText, final String toolTip, final Number number, final int columns) {
		super(labelText, toolTip, new NumberField(number, toolTip, columns));
	}

	public LabeledNumberField(final String title, final String labelText, final String toolTip, final Number number, final int columns) {
		super(title, labelText, toolTip, new NumberField(number, toolTip, columns));
	}

	public NumberField getNumberField() {
		return (NumberField)getComponent();
	}
}