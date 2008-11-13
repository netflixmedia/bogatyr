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
package ch.sisprocom.bogatyr.controller.updater;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ch.sisprocom.bogatyr.controller.Application;
import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperNet;
import ch.sisprocom.bogatyr.helper.context.Context;
import ch.sisprocom.bogatyr.helper.localizer.Localizer;


/**
 * SAX handler to parse the update XML files
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public class XmlParser extends DefaultHandler {
	private static final Logger log = Logger.getLogger(XmlParser.class);

	// Resources
    private static final String	RES_UPDATE         = "Updater.update"; //$NON-NLS-1$
    private static final String	RES_UPDATE_TEXT    = "Updater.update.text"; //$NON-NLS-1$
    private static final String	RES_DOWNGRADE 	   = "Updater.downgrade"; //$NON-NLS-1$
    private static final String	RES_DOWNGRADE_TEXT = "Updater.downgrade.text"; //$NON-NLS-1$
    private static final String	RES_FILE 	       = "Updater.file"; //$NON-NLS-1$
    private static final String	RES_SUCCESS 	   = "Updater.success"; //$NON-NLS-1$

    // Tags
	private static final String TAG_APPLICATION      = "application"; //$NON-NLS-1$
//	private static final String TAG_NAME             = "name"; //$NON-NLS-1$
	private static final String TAG_ID               = "id"; //$NON-NLS-1$
	private static final String TAG_VERSIONS         = "versions"; //$NON-NLS-1$
	private static final String TAG_MINORVERSIONS    = "minorversions"; //$NON-NLS-1$
	private static final String TAG_MAX              = "max"; //$NON-NLS-1$
	private static final String TAG_MIN              = "min"; //$NON-NLS-1$
	private static final String TAG_LOCATION_WINDOWS = "location_windows"; //$NON-NLS-1$
	private static final String TAG_LOCATION_OSX     = "location_osx"; //$NON-NLS-1$
	private static final String TAG_LOCATION_UNIX    = "location_unix"; //$NON-NLS-1$	
	private static final String TAG_NAME_WINDOWS     = "name_windows"; //$NON-NLS-1$
	private static final String TAG_NAME_OSX         = "name_osx"; //$NON-NLS-1$
	private static final String TAG_NAME_UNIX        = "name_unix"; //$NON-NLS-1$
	
//	private String name;
	private String id;
	private int version_min;
	private int version_max;
	private int minorversion_min;
	private int minorversion_max;
	private int build_min;
	private int build_max;
	private String location_windows;
	private String location_osx;
	private String location_unix;
	private String name_windows;
	private String name_osx;
	private String name_unix;
	
//	private boolean isName;
	private boolean isId;
	private boolean isVersions;
	private boolean isVersionMin;
	private boolean isVersionMax;
	private boolean isMinorVersions;
	private boolean isMinorVersionMin;
	private boolean isMinorVersionMax;
	private boolean isBuildMin;
	private boolean isBuildMax;
	private boolean isLocationWindows;
	private boolean isLocationOSX;
	private boolean isLocationUnix;
	private boolean isNameWindows;
	private boolean isNameOSX;
	private boolean isNameUnix;	

	/*
	 * Private methods
	 */
	private void update() {
		if (JOptionPane.showConfirmDialog(null, Localizer.getInstance().getValue(RES_UPDATE) + HelperGeneral.getLineSeparator() + 
				Localizer.getInstance().getValue(RES_UPDATE_TEXT), Context.getInstance().getApplicationName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				download();
			} catch (IOException ex) {
				log.error("Couldn't download the new application version", ex);
				Application.getInstance().exit(50);
			}
		} else {
			log.warn("Update cancelled");
			Application.getInstance().exit(51);
		}
	}
	
	private void downgrade() {
		if (JOptionPane.showConfirmDialog(null, Localizer.getInstance().getValue(RES_DOWNGRADE) + HelperGeneral.getLineSeparator() + 
				Localizer.getInstance().getValue(RES_DOWNGRADE_TEXT), Context.getInstance().getApplicationName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				download();
			} catch (IOException ex) {
				log.error("Couldn't download the old application version", ex);
				Application.getInstance().exit(52);
			}
		} else {
			log.warn("Downgrade cancelled");
			Application.getInstance().exit(53);
		}
	}
	
    private void download() throws IOException {
		File output = new File(HelperEnvInfo.getUserHomeDirectory() + "/" + (HelperEnvInfo.isWindowsPlatform() ? name_windows : (HelperEnvInfo.isMacPlatform() ? name_osx : name_unix)));
		boolean isOk = false;
		
		while (!isOk) {
			final JFileChooser fc = new JFileChooser();
			fc.setSelectedFile(output);
	
			final int returnVal = fc.showSaveDialog(null);
	
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            output = fc.getSelectedFile();

	            if (output.exists()) {
	            	if (JOptionPane.showConfirmDialog(null, Localizer.getInstance().getValue(RES_FILE), Context.getInstance().getApplicationName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	            		output.delete();
	            		isOk = true;
	            	}
	            } else {
	            	isOk = true;
	            }
	        } else {
				log.warn("Download cancelled");
				Application.getInstance().exit(54);
	        }

		}
		
		DialogProgress dialogProgress = new DialogProgress();
		dialogProgress.createAndShowGUI();
		
		final String location = (HelperEnvInfo.isWindowsPlatform() ? location_windows : (HelperEnvInfo.isMacPlatform() ? location_osx : location_unix));
		final File file = new File(location);
		
		byte[] data;
		
		try {
	        if (file.exists()) {
	            data = HelperIO.readFileAsBinary(file);
	        } else {
	        	data = HelperNet.readUrl(new URL(location));
	        }
	        HelperIO.writeFileFromBinary(output, data, false);
		} finally {
			dialogProgress.dispose();
		}

		JOptionPane.showMessageDialog(null, Localizer.getInstance().getValue(RES_SUCCESS), Context.getInstance().getApplicationName(), JOptionPane.INFORMATION_MESSAGE);
		log.info("Update successful");
		Application.getInstance().exit(0);
    }


	
	/*
	 * Overridden methods
	 */
    @Override
	public void startElement(String namespaceUri, String localName, String qualifiedName, Attributes attributes) throws SAXException {
    	/*if (TAG_NAME.equals(qualifiedName)) {
    		isName = true;
    	} else*/ if (TAG_ID.equals(qualifiedName)) {
    		isId = true;
    	} else if (TAG_VERSIONS.equals(qualifiedName)) {
    		isVersions = true;
    	} else if (TAG_MINORVERSIONS.equals(qualifiedName)) {
    		isMinorVersions = true;
    	} else if (TAG_MIN.equals(qualifiedName)) {
    		if (isVersions) {
    			isVersionMin = true;
    		} else if (isMinorVersions) {
    			isMinorVersionMin = true;
    		} else {
    			isBuildMin = true;
    		}
    	} else if (TAG_MAX.equals(qualifiedName)) {
    		if (isVersions) {
    			isVersionMax = true;
    		} else if (isMinorVersions) {
    			isMinorVersionMax = true;
    		} else {
    			isBuildMax = true;
    		}
    	} else if (TAG_LOCATION_WINDOWS.equals(qualifiedName)) {
    		isLocationWindows = true;
    	} else if (TAG_LOCATION_OSX.equals(qualifiedName)) {
    		isLocationOSX = true;
       	} else if (TAG_LOCATION_UNIX.equals(qualifiedName)) {
    		isLocationUnix = true;
       	} else if (TAG_NAME_WINDOWS.equals(qualifiedName)) {
    		isNameWindows = true;
    	} else if (TAG_NAME_OSX.equals(qualifiedName)) {
    		isNameOSX = true;
    	} else if (TAG_NAME_UNIX.equals(qualifiedName)) {
    		isNameUnix = true;
    	}
    }
    
	@Override
	public void endElement(String namespaceUri, String localName, String qualifiedName) throws SAXException {
		if (TAG_APPLICATION.equals(qualifiedName)) {
			if (id.equals(Context.getInstance().getApplicationId())) {

				if (version_min > Context.getInstance().getApplicationVersion()) {
					update();
				} else if (version_max < Context.getInstance().getApplicationVersion()) {
					downgrade();
				} else {
					if (minorversion_min > Context.getInstance().getApplicationMinorVersion()) {
						update();
					} else if (minorversion_max < Context.getInstance().getApplicationMinorVersion()) {
						downgrade();
					} else {
						if (build_min > Context.getInstance().getApplicationBuild()) {
							update();
						} else if (build_max < Context.getInstance().getApplicationBuild()) {
							downgrade();
						}
					}
				}
			}
		} else if (TAG_VERSIONS.equals(qualifiedName)) {
			isVersions = false;
		} else if (TAG_MINORVERSIONS.equals(qualifiedName)) {
			isMinorVersions = false;
		}
	}
		
    @Override 
	public void characters(char[] chars, int startIndex, int endIndex) {
    	String value = new String(chars, startIndex, endIndex).trim();
    	
    	/*if (isName) {
    		name = value;
    		isName = false;
       	} else*/ if (isId) {
    		id = value;
    		isId = false;
    	} else if (isVersionMin) {
    		version_min = HelperGeneral.isStringNumeric(value) ? Integer.parseInt(value) : Integer.MIN_VALUE;
    		isVersionMin = false;
    	} else if (isVersionMax) {
    		version_max = HelperGeneral.isStringNumeric(value) ? Integer.parseInt(value) : Integer.MAX_VALUE;
    		isVersionMax = false;
    	} else if (isMinorVersionMin) {
    		minorversion_min = HelperGeneral.isStringNumeric(value) ? Integer.parseInt(value) : Integer.MIN_VALUE;
    		isMinorVersionMin = false;
    	} else if (isMinorVersionMax) {
    		minorversion_max = HelperGeneral.isStringNumeric(value) ? Integer.parseInt(value) : Integer.MAX_VALUE;
    		isMinorVersionMax = false;
    	} else if (isBuildMin) {
    		build_min = HelperGeneral.isStringNumeric(value) ? Integer.parseInt(value) : Integer.MIN_VALUE;
    		isBuildMin = false;
    	} else if (isBuildMax) {
    		build_max = HelperGeneral.isStringNumeric(value) ? Integer.parseInt(value) : Integer.MAX_VALUE;
    		isBuildMax = false;
    	} else if (isLocationWindows) {
    		location_windows = value;
    		isLocationWindows = false;
    	} else if (isLocationOSX) {
    		location_osx = value;
    		isLocationOSX = false;
    	} else if (isLocationUnix) {
    		location_unix = value;
    		isLocationUnix = false;
    	} else if (isNameWindows) {
    		name_windows = value;
    		isNameWindows = false;
    	} else if (isNameOSX) {
    		name_osx = value;
    		isNameOSX = false;
       	} else if (isNameUnix) {
    		name_unix = value;
    		isNameUnix = false;
    	}
    }
}