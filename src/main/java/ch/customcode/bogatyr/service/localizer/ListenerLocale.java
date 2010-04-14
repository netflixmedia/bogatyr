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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */
package ch.customcode.bogatyr.service.localizer;

import ch.customcode.bogatyr.misc.Event;
import ch.customcode.bogatyr.misc.Listener;

/**
 * ListenerLocale
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100205)
 * @since 0.6.0
 */
public interface ListenerLocale extends Listener {
	/**
	 * Informs the listener that the locale has changed.
	 * 
	 * @since 0.6.0
	 */
	void localeChanged(Event<Localizer> event);
}
