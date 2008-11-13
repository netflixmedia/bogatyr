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
package ch.sisprocom.bogatyr.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * This is a helper class for compress operations.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class HelperCompress { //TODO implement GZip for streams
	private final static byte[] BUFFER = new byte[1024];

	/**
     * Writes a zip file containing a list of files.
     * 
     * @param file for writing
     * @param listOfFiles for the zip file
     * @throws java.io.IOException
     */	
	public static void writeZip(final File file, final List<File> listOfFiles) throws IOException {
		ZipOutputStream zos = null;
		
		try {
			// create a ZipOutputStream to zip the data to
			zos = new ZipOutputStream(new FileOutputStream(file));

			for (File entry : listOfFiles) {
			addEntry(zos, entry);
			}
		} finally {
			if (zos != null) {
				//close the stream 
			    zos.close();
			} 
		}
	}
	
	/**
     * Extracts a zip file to a destination directory.
     * 
     * @param file to extract
     * @param destinationDirectory for the zip file
     * @throws java.io.IOException
     */	
	public static void extractZip(final ZipFile file, final File destinationDirectory) throws IOException { 
        Enumeration<? extends ZipEntry> zipEntryEnum = file.entries(); 
 
        while (zipEntryEnum.hasMoreElements()) { 
          ZipEntry zipEntry = zipEntryEnum.nextElement(); 
          extractEntry(file, zipEntry, destinationDirectory); 
        }
	} 

	
	/*
	 * Private methods
	 */
	private static void addEntry(final ZipOutputStream zos, final File file) throws IOException {
		FileInputStream fis = null;
//		byte[] readBuffer = new byte[2156];
        int bytesIn = 0; 
		
		try {
			fis = new FileInputStream(file);
			
	        // create a new zip entry 
			ZipEntry entry = new ZipEntry(file.getPath());
	
	        // place the zip entry in the ZipOutputStream object 
	        zos.putNextEntry(entry); 
	        
	        // now write the content of the file to the ZipOutputStream 
	        while((bytesIn = fis.read(BUFFER)) != -1) { 
	            zos.write(BUFFER, 0, bytesIn); 
	        } 
		} finally {
			if (fis != null) {
				// close the stream 
				fis.close();
			}
		}
	}
	
	private static void extractEntry(ZipFile zipFile, ZipEntry entry, File destDir) throws IOException { 
		File file = new File(destDir, entry.getName()); 
 	 
	    if (entry.isDirectory()) 
	    	file.mkdirs(); 
	    else {
	    	new File(file.getParent()).mkdirs(); 
 
	    	InputStream  is = null; 
	    	OutputStream os = null; 
	        int bytesOut = 0; 
 
	    	try {
	    		is = zipFile.getInputStream(entry); 
	    		os = new FileOutputStream( file ); 
 
	    		while ((bytesOut = is.read(BUFFER)) != -1) { 
	    			os.write(BUFFER, 0, bytesOut); 
	    		}
	    	} finally { 
	    		// close the streams
	    		if (os != null) {
	    			os.close(); 
	    		}
	    		
	    		if (is != null) {
	    			is.close(); 
	    		}
	    	} 
	    }
	} 
}
