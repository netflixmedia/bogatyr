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
package ch.sisprocom.bogatyr.model.updater;

import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ch.sisprocom.bogatyr.model.Model;
import ch.sisprocom.bogatyr.model.misc.Platform;

/**
 * The interface for the application model.
 * 
 * @author Stefan Laubenberger
 * @version 20091122
 */
@XmlRootElement
public interface Documents extends Model {
    String METHOD_SET_DOCUMENTS = "setDocuments";

    /**
     * Returns all documents.
     *
     * @retuns {@link Map} containing all documents
     * @since 0.9.0
     */
    @XmlElement
    Map<UUID, Document> getDocuments();

    /**
     * Sets all documents.
     *
     * @param documents {@link Map} containing all documents
     * @since 0.9.0
     */
    void setDocuments(Map<UUID, Document> documents);

	/**
     * Returns the {@link Document} for a given {@link UUID}.
     * 
     * @param uuid for the {@link Document}
     * @return {@link Document} 
     * @since 0.9.0
     */	
	Document getDocument(UUID uuid);
}
