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
package ch.sisprocom.bogatyr.service.updater;

import ch.sisprocom.bogatyr.model.updater.Document;
import ch.sisprocom.bogatyr.model.updater.Documents;
import ch.sisprocom.bogatyr.service.Service;

import java.io.File;
import java.io.InputStream;



/**
 * Defines the methods for the implementation of the updater.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091122)
 * @since 0.6.0
 */
public interface Updater extends Service {
		
	Documents getDocuments(File file);
	
	Documents getDocuments(InputStream is);
	
	void update(Document document, File destination);
}   

