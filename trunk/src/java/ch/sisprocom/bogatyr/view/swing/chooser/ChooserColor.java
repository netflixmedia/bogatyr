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
package ch.sisprocom.bogatyr.view.swing.chooser;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.colorchooser.ColorSelectionModel;

import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * This is an extended JColorChooser.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.70
 */
public class ChooserColor extends JColorChooser {
	private static final long serialVersionUID = 2106701368372263061L;

	
	public ChooserColor() {
		super();
	}

	public ChooserColor(final Color initialColor) {
		super(initialColor);
	}

	public ChooserColor(final ColorSelectionModel model) {
		super(model);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}