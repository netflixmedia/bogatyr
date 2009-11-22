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


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.model.ModelAbstract;


/**
 * The implementation of the document model.
 * 
 * @author Stefan Laubenberger
 * @version 20091122
 */
public class DocumentsImpl extends ModelAbstract implements Documents {
	private static final long serialVersionUID = 5020205879710366592L;

	private Map<UUID, Document> mapDocument = new HashMap<UUID, Document>();
    
	
    /*
     * Implemented methods
     */
	@Override
	public Document getDocument(UUID uuid) {
		return mapDocument.get(uuid);
	}

	@Override
	public Map<UUID, Document> getDocuments() {
		return mapDocument;
	}

	@Override
	public void setDocuments(Map<UUID, Document> documents) {
        if (!HelperObject.isEquals(documents, mapDocument)) {
        	mapDocument = documents;
            setChanged();
            notifyObservers(METHOD_SET_DOCUMENTS);
        }
	}
}