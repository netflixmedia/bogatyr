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
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ch.sisprocom.bogatyr.controller.localizer.IControllerLocalizer;
import ch.sisprocom.bogatyr.helper.HelperEnvInfo;
import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.HelperIO;
import ch.sisprocom.bogatyr.helper.HelperNet;
import ch.sisprocom.bogatyr.view.swing.dialog.DialogProgress;


/**
 * SAX handler to parse the update XML files
 * 
 * @author Stefan Laubenberger
 * @version 20090304
 */
public class XmlParser extends DefaultHandler {
	// Resources
    private static final String	RES_UPDATE         = "Updater.update"; //$NON-NLS-1$
    private static final String	RES_UPDATE_TEXT    = "Updater.update.text"; //$NON-NLS-1$
    private static final String	RES_DOWNGRADE 	   = "Updater.downgrade"; //$NON-NLS-1$
    private static final String	RES_DOWNGRADE_TEXT = "Updater.downgrade.text"; //$NON-NLS-1$
    private static final String	RES_FILE 	       = "Updater.file"; //$NON-NLS-1$
    private static final String	RES_SUCCESS 	   = "Updater.success"; //$NON-NLS-1$
    private static final String	RES_FAIL     	   = "Updater.fail"; //$NON-NLS-1$

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

	private final String applicationName;
	private final String applicationId;
	private final int applicationVersion;
	private final int applicationMinorversion;
	private final int applicationBuild;
	
	private final ListenerUpdater listener;
	private final IControllerLocalizer localizer;
	
	
	public XmlParser(final String applicationName, final String applicationId, final int applicationVersion, final int applicationMinorversion, final int applicationBuild, final ListenerUpdater listener, final IControllerLocalizer localizer) {
		super();
		
		this.applicationName = applicationName;
		this.applicationId = applicationId;
		this.applicationVersion = applicationVersion;
		this.applicationMinorversion = applicationMinorversion;
		this.applicationBuild = applicationBuild;
		this.listener = listener;
		this.localizer = localizer;
	}
	
	
	/*
	 * Private methods
	 */
	private void update() {
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, localizer.getValue(RES_UPDATE) + HelperGeneral.getLS() +
                localizer.getValue(RES_UPDATE_TEXT), applicationName, JOptionPane.YES_NO_OPTION)) {
			try {
				download();
				listener.updateSuccessful();
			} catch (IOException ex) {
				listener.updateFailed(ex);
//				Application.getInstance().exit(20);
			}
		} else {
			listener.updateCancelled();
//			Application.getInstance().exit(21);
		}
	}
	
	private void downgrade() {
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, localizer.getValue(RES_DOWNGRADE) + HelperGeneral.getLS() +
                localizer.getValue(RES_DOWNGRADE_TEXT), applicationName, JOptionPane.YES_NO_OPTION)) {
			try {
				download();
				listener.downgradeSuccessful();
			} catch (IOException ex) {
				listener.downgradeFailed(ex);
//				Application.getInstance().exit(22);
			}
		} else {
			listener.downgradeCancelled();
//			Application.getInstance().exit(23);
		}
	}
	
    private void download() throws IOException {
		File output = new File(HelperEnvInfo.getUserHomeDirectory() + "/" + (HelperEnvInfo.isWindowsPlatform() ? name_windows : HelperEnvInfo.isMacPlatform() ? name_osx : name_unix));
		boolean isOk = false;
		
		while (!isOk) {
			final JFileChooser fc = new JFileChooser();
			fc.setSelectedFile(output);
	
			final int returnVal = fc.showSaveDialog(null);
	
	        if (JFileChooser.APPROVE_OPTION == returnVal) {
	            output = fc.getSelectedFile();

	            if (output.exists()) {
	            	if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, localizer.getValue(RES_FILE), applicationName, JOptionPane.YES_NO_OPTION)) {
	            		output.delete();
	            		isOk = true;
	            	}
	            } else {
	            	isOk = true;
	            }
	        } else {
//				log.warn("Download cancelled");
				listener.downloadCancelled();
//				Application.getInstance().exit(24);
	        }

		}
		
		final DialogProgress dialogProgress = new DialogProgress();
		dialogProgress.createAndShowGUI();
		
		final String location = HelperEnvInfo.isWindowsPlatform() ? location_windows : HelperEnvInfo.isMacPlatform() ? location_osx : location_unix;
		final File file = new File(location);
		
		try {
            final byte[] data = file.exists() ? HelperIO.readFileAsBinary(file) : HelperNet.readUrl(new URL(location));
	        HelperIO.writeFileFromBinary(output, data, false);
	        
			JOptionPane.showMessageDialog(null, localizer.getValue(RES_SUCCESS), applicationName, JOptionPane.INFORMATION_MESSAGE);
//			Application.getInstance().exit(0);
		} catch (SocketTimeoutException ex) {
			// do nothing (no internet available)
			JOptionPane.showMessageDialog(null, localizer.getValue(RES_FAIL), applicationName, JOptionPane.ERROR_MESSAGE);
			listener.networkNotAvailable();
//			Application.getInstance().exit(25);
		} finally {
			dialogProgress.dispose();
		}
    }


	
	/*
	 * Overridden methods
	 */
    @Override
	public void startElement(final String namespaceUri, final String localName, final String qualifiedName, final Attributes attributes) throws SAXException {
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
	public void endElement(final String namespaceUri, final String localName, final String qualifiedName) throws SAXException {
		if (TAG_APPLICATION.equals(qualifiedName)) {
			if (id.equals(applicationId)) {

				if (version_min > applicationVersion) {
					update();
				} else if (version_max < applicationVersion) {
					downgrade();
				} else {
					if (minorversion_min > applicationMinorversion) {
						update();
					} else if (minorversion_max < applicationMinorversion) {
						downgrade();
					} else {
						if (build_min > applicationBuild) {
							update();
						} else if (build_max < applicationBuild) {
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
	public void characters(final char[] chars, final int startIndex, final int endIndex) {
    	final String value = new String(chars, startIndex, endIndex).trim();
    	
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