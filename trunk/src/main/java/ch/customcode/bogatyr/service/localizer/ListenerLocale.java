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
