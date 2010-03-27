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
package ch.customcode.bogatyr.view.swing;

import javax.swing.BoundedRangeModel;
import javax.swing.JProgressBar;

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * This is an extended JProgressBar.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100201)
 * @since 0.2.0
 */
public class ProgressBar extends JProgressBar {
	private static final long serialVersionUID = -6439735629199643683L;
	
	/*
	 * Superclass constructors
	 */
	public ProgressBar() {
		super();
	}
	
	public ProgressBar(final BoundedRangeModel model) {
		super(model);
	}

	public ProgressBar(final int orientation, final int min, final int max) {
		super(orientation, min, max);
	}

	public ProgressBar(final int orientation) {
		super(orientation);
	}

	public ProgressBar(final int start, final int end) {
		super(start, end);
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
}