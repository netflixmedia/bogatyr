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

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.misc.Activatable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JPanel.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100610)
 * @since 0.1.0
 */
public class Panel extends JPanel implements Activatable {
	private static final long serialVersionUID = 3679443739459084931L;

	private static final Logger log = LoggerFactory.getLogger(Panel.class);

	private boolean isNotActive;

	private String title;

	/*
	 * Superclass constructors
	 */

	public Panel() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public Panel(final boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(isDoubleBuffered));
	}

	public Panel(final LayoutManager layout, final boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(layout, isDoubleBuffered));
	}

	public Panel(final LayoutManager layout) {
		super(layout);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(layout));
	}

	/*
	 * Own constructors
	 */

	public Panel(final String title) {
		this();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title));

		this.title = title;
		setTitle(title);
	}

	public Panel(final Color color) {
		this();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(color));

		setBackground(color);
	}

	public Panel(final Color color, final LayoutManager layout) {
		this(layout);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(color, layout));

		setBackground(color);
	}

	/**
	 * Set the title of the Panel
	 *
	 * @param title Title of the Panel
	 */
	public void setTitle(final String title) {
		this.title = title;
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
	}

	/**
	 * Get the title of the Panel
	 *
	 * @return String title of the Panel
	 */
	public String getTitle() {
		return title;
	}

//	/**
//     * Refresh the panel (revalidate() and repaint() in one method)
//     */	
//	public void refresh() {
//		revalidate();
//		repaint();
//	}


	/*
	 * Overridden methods
	 */

	@Override
	public void setEnabled(final boolean enabled) {
		if (!isNotActive) {
			super.setEnabled(enabled);

			for (final Component component : getComponents()) {
				component.setEnabled(enabled);
			}
		}
	}

	@Override
	public void setToolTipText(final String text) {
		super.setToolTipText(text);

		for (final Component component : getComponents()) {
			if (component instanceof JComponent) {
				((JComponent) component).setToolTipText(text);
			}
		}
	}


	/*
	 * Implemented methods
	 */

	@Override
	public boolean isActive() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(!isNotActive));
		return !isNotActive;
	}

	@Override
	public void setActive(final boolean isActive) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(isActive));

		if (isActive) {
			isNotActive = !isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			isNotActive = !isActive;
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}