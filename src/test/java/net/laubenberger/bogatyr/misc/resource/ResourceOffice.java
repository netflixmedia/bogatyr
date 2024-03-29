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

package net.laubenberger.bogatyr.misc.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import net.laubenberger.bogatyr.helper.HelperIO;

/**
 * Resource enum for Microsoft Office97 test files.
 * 
 * @author Stefan Laubenberger
 * @version 20101119
 */
public enum ResourceOffice implements Resource {
	DOC("/net/laubenberger/bogatyr/office/test.doc"), //$NON-NLS-1$
	PPT("/net/laubenberger/bogatyr/office/test.ppt"), //$NON-NLS-1$
	XLS("/net/laubenberger/bogatyr/office/test.xls"); //$NON-NLS-1$

	private final String resource;
	private File file;
	
	ResourceOffice(final String resource) {
		this.resource = resource;
	}


	/*
	 * Implemented methods
	 */
	@Override
	public String getResource() {
		return resource;
	}

	@Override
	public InputStream getResourceAsStream() {
		return getClass().getResourceAsStream(resource);
	}
	
	@Override
	public File getResourceAsFile() {
		if (null == file) {
			try {
				file = HelperIO.getTemporaryFile(HelperIO.getFileExtension(resource));
				HelperIO.writeFile(file, getResourceAsStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return file;
	}
}

