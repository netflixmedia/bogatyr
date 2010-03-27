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
package ch.customcode.bogatyr.service.updater;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import ch.customcode.bogatyr.model.misc.Platform;
import ch.customcode.bogatyr.model.updater.ModelUpdater;
import ch.customcode.bogatyr.service.Service;



/**
 * Defines the methods for the implementation of the updater.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091205)
 * @since 0.6.0
 */
public interface Updater extends Service {
		
	ModelUpdater getDocument(File file) throws Exception;
	
	ModelUpdater getDocument(InputStream is) throws Exception;
	
	void update(ModelUpdater document, File dest) throws IOException;
	
	void update(ModelUpdater document, Platform platform, File dest) throws IOException;
}   

