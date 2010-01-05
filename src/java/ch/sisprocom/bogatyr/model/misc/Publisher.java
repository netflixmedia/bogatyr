/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU General Public License for more details:
 * ----------------------------------------------------
 * <http://www.gnu.org/licenses>
 * 
 * This distribution is available at:
 * ----------------------------------
 * <http://code.google.com/p/bogatyr/>
 * <http://www.sisprocom.ch/bogatyr/>
 * 
 * Contact information:
 * --------------------
 * SiSprocom GmbH
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.misc;

import java.net.URL;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.sisprocom.bogatyr.model.Model;

/**
 * The interface for the publisher model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100105)
 * @since 0.9.0
 */
@XmlJavaTypeAdapter(PublisherImpl.XmlAdapter.class)
public interface Publisher extends Model {
    String MEMBER_NAME 	  = Publisher.class.getName() + ".name"; //$NON-NLS-1$
    String MEMBER_URL  	  = Publisher.class.getName() + ".url"; //$NON-NLS-1$
    String MEMBER_MAIL 	  = Publisher.class.getName() + ".mail"; //$NON-NLS-1$

    String getName();
    void setName(String name);

    URL getURL();
    void setURL(URL url);

    String getMail();
    void setMail(String mail);
}
