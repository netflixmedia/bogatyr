/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ch.sisprocom.bogatyr.helper.context.Context;



/**
 * This is the updater for new application versions
 * 
 * @author Stefan Laubenberger
 * @version 20081113
 */
public abstract class Updater { //TODO document in Wiki!
	private static final Logger log = Logger.getLogger(Updater.class);

	public static void update() throws SAXException, IOException, ParserConfigurationException {
		String updateLocation = Context.getInstance().getApplicationUpdateLocation();
		
		if (updateLocation != null) {
			final File file = new File(updateLocation);
			InputStream is = null;
			try {
		        if (file.exists()) {
		            is = new FileInputStream(file);
		        } else {
		    		final URLConnection con = (new URL(updateLocation)).openConnection();
		    		con.setConnectTimeout(2000);
		    		con.connect();
		        	
		        	is = con.getInputStream();
		        }
		        
				final SAXParserFactory factory = SAXParserFactory.newInstance(); 
				final SAXParser saxParser = factory.newSAXParser();
				
				final DefaultHandler handler = new XmlParser(); 
		
				saxParser.parse(is, handler);
			} catch (SocketTimeoutException ex) {
				// do nothing (no internet available)
				log.warn("Couldn't connect to the update xml file! No internet connection available or a proxy authentication is needed.");
			} finally {
				if (is != null) {
					is.close();
				}
		    }
	    }
	}
}
