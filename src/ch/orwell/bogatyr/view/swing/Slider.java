/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.view.swing;

import java.awt.Component;
import java.awt.Font;
import java.util.Collections;
import java.util.Dictionary;

import javax.swing.JSlider;

import ch.orwell.bogatyr.helper.HelperGeneral;
import ch.orwell.bogatyr.helper.logger.Logger;


/**
 * This is an extended JSlider
 * 
 * @author Stefan Laubenberger
 * @version 20080605
 */
public class Slider extends JSlider {
	private static final long serialVersionUID = 8676540667794440059L;


	public Slider() {
		super();
		init();
	}

	public Slider(final int minValue, final int maxValue, final int currentValue, final String toolTip) {
		this(minValue, maxValue, currentValue, HORIZONTAL, toolTip);
	}

	public Slider(final int minValue, final int maxValue, final int currentValue, final int orientation, final String toolTip) {
		super(orientation, minValue, maxValue, currentValue);
		setToolTipText(toolTip);
		init();
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init", toString()); //$NON-NLS-1$
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	
	@Override
	public void setToolTipText(final String text) {
		if (text != null) {
            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
        }
	}

	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		final Dictionary<?, ?> dict = getLabelTable();
		if (dict != null) {
			for (Object element : Collections.list(dict.elements())) {
				if (element instanceof Component) {
					((Component) element).setFont( font );
				}
			}
		}
	}
}