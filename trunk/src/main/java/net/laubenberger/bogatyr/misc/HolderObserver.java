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

package net.laubenberger.bogatyr.misc;

import java.util.Observable;
import java.util.Observer;


/**
 * Defines the methods for the observer holder (it also delegates the methods from {@link Observable}).
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.0
 */
public interface HolderObserver {
	void setNotifyEnabled(boolean enabled);

	boolean isNotifyEnabled();

	void addObserver(Observer o);

	void deleteObserver(Observer o);

	void notifyObservers();

	void notifyObservers(Object arg);

	void deleteObservers();

	boolean hasChanged();

	int countObservers();
}   