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
import java.net.URL;

import javax.xml.bind.JAXBException;

import ch.customcode.bogatyr.helper.HelperEnvironment;
import ch.customcode.bogatyr.helper.HelperIO;
import ch.customcode.bogatyr.helper.HelperNet;
import ch.customcode.bogatyr.helper.HelperXml;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsEquals;
import ch.customcode.bogatyr.model.misc.Platform;
import ch.customcode.bogatyr.model.updater.ModelUpdater;
import ch.customcode.bogatyr.service.ServiceAbstract;


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
	public ModelUpdater getDocument(final File file) throws JAXBException {
    	return HelperXml.deserialize(ModelUpdater.class, file);
    }

	@Override
	public ModelUpdater getDocument(final InputStream is) throws JAXBException {
		return HelperXml.deserialize(ModelUpdater.class, is);
	}

	@Override
	public void update(final ModelUpdater document, final Platform platform, final File dest) throws IOException {
        if (null == document) {
            throw new RuntimeExceptionIsNull("document"); //$NON-NLS-1$
        }
        if (null == platform) {
            throw new RuntimeExceptionIsNull("platform"); //$NON-NLS-1$
        }
        if (null == dest) {
            throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
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
    			throw new RuntimeExceptionIsEquals("location", "dest"); //$NON-NLS-1$ //$NON-NLS-2$
    		}
            HelperIO.copyFile(source, dest);
        } else {
        	HelperIO.writeFile(dest, HelperNet.readUrl(new URL(location)), false);
        }
	}

	@Override
	public void update(final ModelUpdater document, final File dest) throws IOException {
        if (null == document) {
            throw new RuntimeExceptionIsNull("document"); //$NON-NLS-1$
        }

        update(document, null == document.getLocation(HelperEnvironment.getPlatform()) ? Platform.ANY : HelperEnvironment.getPlatform(), dest);
	}
}
