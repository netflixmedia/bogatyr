/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


/**
 * This is a helper class for compress operations.
 * 
 * @author Stefan Laubenberger
 * @version 20090516
 */
public abstract class HelperCompress { //TODO implement GZip for streams
	private static final byte[] BUFFER = new byte[Const.VALUE_1024];

	/**
     * Writes a ZIP {@link File} containing a list of {@link File}.
     * 
     * @param file for writing
     * @param listOfFiles for the ZIP file
     * @throws IOException
     */	
	public static void writeZip(final File file, final Collection<File> listOfFiles) throws IOException {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (!HelperGeneral.isValid(listOfFiles)) {
			throw new IllegalArgumentException("listOfFiles is null or empty!"); //$NON-NLS-1$
		}
		
		ZipOutputStream zos = null;
		
		try {
			// create a ZipOutputStream to zip the data to
			zos = new ZipOutputStream(new FileOutputStream(file));

			for (final File entry : listOfFiles) {
			addEntry(zos, entry);
			}
		} finally {
			if (zos != null) {
				//close the stream 
			    zos.close();
			} 
		}
	}
	
//	/**
//     * Writes a compressed stream.
//     * 
//     * @param os OutputStram to compress
//     * @return Compressed OutputStream
//     * @throws java.io.IOException
//     */	
//	public static OutputStream writeStream(final OutputStream os) throws IOException {
////		ZipOutputStream zos = null;
////		
////		try {
////			// create a ZipOutputStream to zip the data to
////			zos = new ZipOutputStream(new FileOutputStream(file));
////
////			for (File entry : listOfFiles) {
////			addEntry(zos, entry);
////			}
////		} finally {
////			if (zos != null) {
////				//close the stream 
////			    zos.close();
////			} 
////		}
//		return null;
//	}

	/**
     * Extracts a ZIP {@link File} to a destination directory.
     * 
     * @param file to extract
     * @param destinationDirectory for the ZIP file
     * @throws IOException
     */	
	public static void extractZip(final ZipFile file, final File destinationDirectory) throws IOException { 
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (null == destinationDirectory) {
			throw new IllegalArgumentException("destinationDirectory is null!"); //$NON-NLS-1$
		}
		
		final Enumeration<? extends ZipEntry> zipEntryEnum = file.entries();
 
        while (zipEntryEnum.hasMoreElements()) { 
          final ZipEntry zipEntry = zipEntryEnum.nextElement();
          extractEntry(file, zipEntry, destinationDirectory); 
        }
	} 

//	/**
//     * Read a compressed stream and returns it uncompressed.
//     * 
//     * @param is InputStream to uncompress
//     * @return Uncompressed InutStream
//     * @throws java.io.IOException
//     */	
//	public static InputStream readStream(final InputStream is) throws IOException { 
////        Enumeration<? extends ZipEntry> zipEntryEnum = file.entries(); 
//// 
////        while (zipEntryEnum.hasMoreElements()) { 
////          ZipEntry zipEntry = zipEntryEnum.nextElement(); 
////          extractEntry(file, zipEntry, destinationDirectory); 
////        }
//		return null;
//	} 

	
	/*
	 * Private methods
	 */
	private static void addEntry(final ZipOutputStream zos, final File file) throws IOException {
		FileInputStream fis = null;
//		byte[] readBuffer = new byte[2156];
		
		try {
			fis = new FileInputStream(file);
			
	        // create a new zip entry 
			final ZipEntry entry = new ZipEntry(file.getPath());
	
	        // place the zip entry in the ZipOutputStream object 
	        zos.putNextEntry(entry); 
	        
            int bytesIn;

            // now write the content of the file to the ZipOutputStream
            while(-1 != (bytesIn = fis.read(BUFFER))) {
	            zos.write(BUFFER, 0, bytesIn); 
	        } 
		} finally {
			if (fis != null) {
				// close the stream 
				fis.close();
			}
		}
	}
	
	private static void extractEntry(final ZipFile zipFile, final ZipEntry entry, final File destDir) throws IOException { 
		final File file = new File(destDir, entry.getName());
 	 
	    if (entry.isDirectory()) {
            file.mkdirs();
        }
	    else {
	    	new File(file.getParent()).mkdirs(); 
 
	    	InputStream  is = null; 
	    	OutputStream os = null;
 
	    	try {
	    		is = zipFile.getInputStream(entry); 
	    		os = new FileOutputStream( file );

                int bytesOut;

                while (-1 != (bytesOut = is.read(BUFFER))) { 
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
