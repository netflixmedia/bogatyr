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
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ch.sisprocom.bogatyr.controller.ControllerAbstract;
import ch.sisprocom.bogatyr.controller.localizer.IControllerLocalizer;
import ch.sisprocom.bogatyr.helper.HelperGeneral;



/**
 * This is the updater controller for new Bogatyr-based applications versions.
 * 
 * @author Stefan Laubenberger
 * @version 20090426
 */
public class ControllerUpdater extends ControllerAbstract implements IControllerUpdater, ListenerUpdater { //TODO document in Wiki!
	private Collection<ListenerUpdater> listListener = new ArrayList<ListenerUpdater>();

	private final IControllerLocalizer localizer;
	
	
	public ControllerUpdater(final IControllerLocalizer localizer) {
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
        final File file = new File(updateLocation);
        InputStream is = null;
        try {
            if (file.exists()) {
                is = new FileInputStream(file);
            } else {
                final URLConnection con = new URL(updateLocation).openConnection();
                con.setConnectTimeout(2000);
                con.connect();

                is = con.getInputStream();
            }

            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser saxParser = factory.newSAXParser();

            final DefaultHandler handler = new XmlParser(name, id, version, minorversion, build, this, localizer);

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
        listListener = new ArrayList<ListenerUpdater>();
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
