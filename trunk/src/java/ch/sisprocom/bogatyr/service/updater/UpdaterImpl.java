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

import ch.sisprocom.bogatyr.helper.HelperEnvironment;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperNet;
import ch.sisprocom.bogatyr.helper.HelperXml;
import ch.sisprocom.bogatyr.model.misc.Platform;
import ch.sisprocom.bogatyr.model.updater.Document;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * This is the updater for documents.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.6.0
 */
public class UpdaterImpl extends ServiceAbstract implements Updater {
    /*
    * Implemented methods
    */
    @Override
	public Document getDocument(final File file) throws JAXBException {
    	return HelperXml.deserialize(Document.class, file);
    }

	@Override
	public Document getDocument(final InputStream is) throws JAXBException {
		return HelperXml.deserialize(Document.class, is);
	}

	@Override
	public void update(final Document document, final Platform platform, final File dest) throws IOException {
        if (null == document) {
            throw new IllegalArgumentException("document is null!"); //$NON-NLS-1$
        }
        if (null == platform) {
            throw new IllegalArgumentException("platform is null!"); //$NON-NLS-1$
        }
        if (null == dest) {
            throw new IllegalArgumentException("dest is null!"); //$NON-NLS-1$
        }
		
		String location = document.getLocation(platform);
		
		if (null == location && Platform.ANY != platform) {
			location = document.getLocation(Platform.ANY);
		}
		
		if (null == location) {
            throw new IllegalArgumentException("no valid location found!"); //$NON-NLS-1$
		}
		
        final File source = new File(location);

        if (source.exists()) {
    		if (source.equals(dest)) {
    			throw new IllegalArgumentException("location is equals to dest!"); //$NON-NLS-1$
    		}
            HelperIO.copyFile(source, dest);
        } else {
        	HelperIO.writeFile(dest, HelperNet.readUrl(new URL(location)), false);
        }
	}

	@Override
	public void update(final Document document, final File dest) throws IOException {
        if (null == document) {
            throw new IllegalArgumentException("document is null!"); //$NON-NLS-1$
        }

        update(document, null == document.getLocation(HelperEnvironment.getPlatform()) ? Platform.ANY : HelperEnvironment.getPlatform(), dest);
	}
}
