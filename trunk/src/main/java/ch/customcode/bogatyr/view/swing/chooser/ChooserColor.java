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
package ch.customcode.bogatyr.view.swing.chooser;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.colorchooser.ColorSelectionModel;

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * This is an extended JColorChooser.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.7.0
 */
public class ChooserColor extends JColorChooser {
	private static final long serialVersionUID = 2106701368372263061L;

	/*
	 * Superclass constructors
	 */
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