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
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.view.swing;

import javax.swing.BoundedRangeModel;
import javax.swing.JProgressBar;

import net.laubenberger.bogatyr.helper.HelperLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JProgressBar.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.2.0
 */
public class ProgressBar extends JProgressBar {
	private static final long serialVersionUID = -6439735629199643683L;

	private static final Logger log = LoggerFactory.getLogger(ProgressBar.class);

	/*
	 * Superclass constructors
	 */

	public ProgressBar() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public ProgressBar(final BoundedRangeModel model) {
		super(model);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(model));
	}

	public ProgressBar(final int orientation, final int min, final int max) {
		super(orientation, min, max);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(orientation, min, max));
	}

	public ProgressBar(final int orientation) {
		super(orientation);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(orientation));
	}

	public ProgressBar(final int start, final int end) {
		super(start, end);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(start, end));
	}
}