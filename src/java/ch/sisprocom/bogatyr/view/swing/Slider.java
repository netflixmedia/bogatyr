/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.view.swing;

import ch.sisprocom.bogatyr.helper.HelperObject;

import javax.swing.BoundedRangeModel;
import javax.swing.JSlider;
import java.awt.Component;
import java.awt.Font;
import java.util.Collections;
import java.util.Dictionary;


/**
 * This is an extended JSlider.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091123)
 * @since 0.2.0
 */
public class Slider extends JSlider implements Activatable {
	private static final long serialVersionUID = 8676540667794440059L;
	
	private boolean isNotActive;
	

	public Slider() {
		super();
	}

	public Slider(final BoundedRangeModel model) {
		super(model);
	}

	public Slider(int orientation, int min, int max, int value) {
		super(orientation, min, max, value);
	}

	public Slider(int min, int max, int value) {
		super(min, max, value);
	}

	public Slider(int min, int max) {
		super(min, max);
	}

	public Slider(int orientation) {
		super(orientation);
	}

	public Slider(final BoundedRangeModel model, final String toolTip) {
		this(model);
		setToolTipText(toolTip);
	}
	
	public Slider(final int minValue, final int maxValue, final int currentValue, final String toolTip) {
		this(minValue, maxValue, currentValue, HORIZONTAL, toolTip);
	}

	public Slider(final int minValue, final int maxValue, final int currentValue, final int orientation, final String toolTip) {
		this(orientation, minValue, maxValue, currentValue);
		setToolTipText(toolTip);
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
//	@Override
//	public void setToolTipText(final String text) {
//		if (text != null) {
//            super.setToolTipText("<html>" + text + "</html>"); //$NON-NLS-1$ //$NON-NLS-2$
//        }
//	}

	@Override
	public void setFont(final Font font) {
		super.setFont(font);
		
		final Dictionary<?, ?> dict = getLabelTable();
		if (dict != null) {
			for (final Object element : Collections.list(dict.elements())) {
				if (element instanceof Component) {
					((Component) element).setFont( font );
				}
			}
		}
	}
	
	@Override
	public void setEnabled(final boolean isEnabled) {
		if (!isNotActive) {
			super.setEnabled(isEnabled);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	@Override
    public boolean isActive() {
		return !isNotActive;
	}

	@Override
    public void setActive(final boolean isActive) {
		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}
	}
}