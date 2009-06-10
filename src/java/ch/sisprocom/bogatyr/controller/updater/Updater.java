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
package ch.sisprocom.bogatyr.controller.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ch.sisprocom.bogatyr.controller.ControllerAbstract;
import ch.sisprocom.bogatyr.controller.localizer.ILocalizer;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.helper.HelperString;


/**
 * This is the updater for new Bogatyr-based applications versions.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090610)
 * @since 0.6.0
 */
public class Updater extends ControllerAbstract implements IUpdater, ListenerUpdater {
	private Collection<ListenerUpdater> listListener = new HashSet<ListenerUpdater>();

	private final ILocalizer localizer;
	
	
	public Updater(final ILocalizer localizer) {
        super();
        this.localizer = localizer;
    }
	

    /*
      * Implemented methods
      */
    /*
      * Checks the update XML file for new versions an update the application if needed.
      */
    public synchronized void update(final String name, final String id, final int version, final int minorversion, final int build, final String updateLocation) throws IOException, ParserConfigurationException, SAXException  {
    	if (!HelperString.isValid(name)) {
    		throw new IllegalArgumentException("name is null or empty!"); //$NON-NLS-1$
    	}
    	if (!HelperString.isValid(id)) {
    		throw new IllegalArgumentException("id is null or empty!"); //$NON-NLS-1$
    	}
    	if (!HelperString.isValid(updateLocation)) {
    		throw new IllegalArgumentException("updateLocation is null or empty!"); //$NON-NLS-1$
    	}

    	final File file = new File(updateLocation);
        InputStream is = null;
        try {
            if (file.exists()) {
                is = new FileInputStream(file);
            } else {
                final URLConnection con = new URL(updateLocation).openConnection();
                con.setConnectTimeout(HelperNumber.VALUE_2048);
                con.connect();

                is = con.getInputStream();
            }

            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser saxParser = factory.newSAXParser();

            final DefaultHandler handler = new XmlParserUpdater(name, id, version, minorversion, build, this, localizer);

            saxParser.parse(is, handler);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public synchronized void addListener(final ListenerUpdater listener) {
        listListener.add(listener);
    }

    public synchronized void removeListener(final ListenerUpdater listener) {
        listListener.remove(listener);
    }

    public synchronized void removeAllListener() {
        listListener = new HashSet<ListenerUpdater>();
    }

	public void downgradeCancelled() {
		for (final ListenerUpdater listener : listListener) {
			listener.downgradeCancelled();
		}	
	}

	public void downgradeFailed(final IOException ex) {
		for (final ListenerUpdater listener : listListener) {
			listener.downgradeFailed(ex);
		}	
	}

	public void downgradeSuccessful() {
		for (final ListenerUpdater listener : listListener) {
			listener.downgradeSuccessful();
		}	
	}

	public void downloadCancelled() {
		for (final ListenerUpdater listener : listListener) {
			listener.downloadCancelled();
		}	
	}

	public void networkNotAvailable() {
		for (final ListenerUpdater listener : listListener) {
			listener.networkNotAvailable();
		}	
	}

	public void updateCancelled() {
		for (final ListenerUpdater listener : listListener) {
			listener.updateCancelled();
		}	
	}

	public void updateFailed(final IOException ex) {
		for (final ListenerUpdater listener : listListener) {
			listener.updateFailed(ex);
		}	
	}

	public void updateSuccessful() {
		for (final ListenerUpdater listener : listListener) {
			listener.updateSuccessful();
		}	
	}
}
