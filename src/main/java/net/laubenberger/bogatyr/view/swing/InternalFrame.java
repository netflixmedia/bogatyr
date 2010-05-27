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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.*;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperObject;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.Displayable;
import net.laubenberger.bogatyr.misc.Fadeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is an extended JInternalFrame.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100527)
 * @since 0.9.2
 */
public class InternalFrame extends JInternalFrame implements Fadeable, Displayable {
	private static final long serialVersionUID = 7476360387134225315L;

	private static final Logger log = LoggerFactory.getLogger(InternalFrame.class);

	private boolean isFading;

	private Color colorFader = new Color(0, 0, 0, 100);

	{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/*
	 * Superclass constructors
	 */

	public InternalFrame() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}
	
	public InternalFrame(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		super(title, resizable, closable, maximizable, iconifiable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, resizable, closable, maximizable, iconifiable));
	}

	public InternalFrame(String title, boolean resizable, boolean closable, boolean maximizable) {
		super(title, resizable, closable, maximizable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, resizable, closable, maximizable));
	}

	public InternalFrame(String title, boolean resizable, boolean closable) {
		super(title, resizable, closable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, resizable, closable));
	}

	public InternalFrame(String title, boolean resizable) {
		super(title, resizable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, resizable));
	}

	public InternalFrame(String title) {
		super(title);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title));
	}

	/*
	 * Own constructors
	 */

	public InternalFrame(String title, final Icon icon, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
		this(title, resizable, closable, maximizable, iconifiable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, icon, resizable, closable, maximizable, iconifiable));

		setFrameIcon(icon);
	}

	public InternalFrame(String title, final Icon icon, boolean resizable, boolean closable, boolean maximizable) {
		this(title, resizable, closable, maximizable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, icon, resizable, closable, maximizable));

		setFrameIcon(icon);
	}

	public InternalFrame(String title, final Icon icon, boolean resizable, boolean closable) {
		this(title, resizable, closable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, icon, resizable, closable));

		setFrameIcon(icon);
	}

	public InternalFrame(String title, final Icon icon, boolean resizable) {
		this(title, resizable);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, icon, resizable));

		setFrameIcon(icon);
	}

	public InternalFrame(String title, final Icon icon) {
		this(title);
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(title, icon));

		setFrameIcon(icon);
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

		final Component[] components = getComponents();
		for (final Component component : components) {
			component.setEnabled(isEnabled);
		}
	}


	/*
	 * Implemented methods
	 */

	@Override
	public void createAndShowGUI() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		setVisible(true);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void clearAndHide() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		dispose();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setFading(final boolean isFading) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(isFading));

		this.isFading = isFading;

		if (HelperString.contains(UIManager.getLookAndFeel().toString(), "swing.plaf") || HelperString.contains(UIManager.getLookAndFeel().toString(), "apple.laf")) { //do the fade-effect only with original lafs //$NON-NLS-1$ //$NON-NLS-2$
			final Container containerGlass = (Container) getGlassPane();
			containerGlass.removeAll();
			containerGlass.setVisible(isFading);

			if (isFading) {
				containerGlass.setLayout(new BorderLayout());
				containerGlass.add(new Panel(colorFader), BorderLayout.CENTER);
			}

			validate();
			repaint();
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public void setFaderColor(final Color colorFader) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(colorFader));

		this.colorFader = colorFader;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public boolean isFading() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(isFading));
		return isFading;
	}
}