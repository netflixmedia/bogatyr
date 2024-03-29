/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.view.swing.chooser;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.colorchooser.ColorSelectionModel;

import net.laubenberger.bogatyr.helper.HelperLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JColorChooser.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.7.0
 */
public class ChooserColor extends JColorChooser {
	private static final long serialVersionUID = 2106701368372263061L;

	private static final Logger log = LoggerFactory.getLogger(ChooserColor.class);

	/*
	 * Superclass constructors
	 */

	public ChooserColor() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public ChooserColor(final Color initialColor) {
		super(initialColor);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(initialColor));
	}

	public ChooserColor(final ColorSelectionModel model) {
		super(model);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(model));
	}
}