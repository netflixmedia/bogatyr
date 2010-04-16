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

package net.laubenberger.bogatyr.view.swing.pane;

import java.awt.Component;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;


/**
 * This is an extended JEditorPane.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.7.0
 */
public class PaneEditor extends JEditorPane {
	private static final long serialVersionUID = -3298005917085461997L;

	private static final Logger log = LoggerFactory.getLogger(PaneEditor.class);
	
	/*
	 * Superclass constructors
	 */

	public PaneEditor() {
		super();
		log.trace(HelperLog.constructor());
	}

	public PaneEditor(final String type, final String text) {
		super(type, text);
		log.trace(HelperLog.constructor(type, text));
	}

	public PaneEditor(final String url) throws IOException {
		super(url);
		log.trace(HelperLog.constructor(url));
	}

	public PaneEditor(final URL initialPage) throws IOException {
		super(initialPage);
		log.trace(HelperLog.constructor(initialPage));
	}


	/*
	 * Overridden methods
	 */

	@Override
	public String toString() {
		return HelperObject.toString(this);
	}

	@Override
	public void setEnabled(final boolean isEnabled) {
		super.setEnabled(isEnabled);

		for (final Component component : getComponents()) {
			component.setEnabled(isEnabled);
		}
	}
}