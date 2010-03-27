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
package ch.customcode.bogatyr.model.misc;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.customcode.bogatyr.model.Model;

/**
 * The interface for the publisher model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100301)
 * @since 0.9.0
 */
@XmlJavaTypeAdapter(PublisherImpl.XmlAdapter.class)
public interface Publisher extends Model, Address, Email, Website {
	//atm nothing
}
