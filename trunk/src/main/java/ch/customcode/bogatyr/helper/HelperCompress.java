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
package ch.customcode.bogatyr.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import ch.customcode.bogatyr.misc.Constants;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for compress operations.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100212)
 * @since 0.3.0
 */
public abstract class HelperCompress { //TODO implement GZip for streams
	/**
     * Writes a ZIP {@link File} containing a list of {@link File}.
     * 
     * @param file for writing
     * @param files for the ZIP file
     * @throws IOException
     * @see File 
     * @since 0.3.0
     */	
	public static void writeZip(final File file, final File... files) throws IOException {
		writeZip(file, files, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}
	
	/**
     * Writes a ZIP {@link File} containing a list of {@link File}.
     * 
     * @param file for writing
     * @param files for the ZIP file
     * @throws IOException
     * @see File
     * @since 0.8.0
     */	
	public static void writeZip(final File file, final File[] files, final int bufferSize) throws IOException {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(files)) {
			throw new RuntimeExceptionIsNullOrEmpty("files"); //$NON-NLS-1$
		}
        if (1 > bufferSize) {
             throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
       }

		ZipOutputStream zos = null;
		
		try {
			// create a ZipOutputStream to zip the data to
			zos = new ZipOutputStream(new FileOutputStream(file));

			for (final File entry : files) {
			addEntry(zos, entry, bufferSize);
			}
		} finally {
			if (null != zos) {
			    zos.close();
			} 
		}
	}
	
//	/**
//     * Writes a compressed stream.
//     * 
//     * @param os OutputStream to compress
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
     * @see File
     * @since 0.3.0
     */	
	public static void extractZip(final ZipFile file, final File destinationDirectory) throws IOException { 
		extractZip(file, destinationDirectory, Constants.DEFAULT_FILE_BUFFER_SIZE); 
	} 
	
	/**
     * Extracts a ZIP {@link File} to a destination directory.
     * 
     * @param file to extract
     * @param destinationDirectory for the ZIP file
     * @param bufferSize in bytes
     * @throws IOException
     * @see File
     * @since 0.8.0
     */	
	public static void extractZip(final ZipFile file, final File destinationDirectory, final int bufferSize) throws IOException { 
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == destinationDirectory) {
			throw new RuntimeExceptionIsNull("destinationDirectory"); //$NON-NLS-1$
		}
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }

		final Enumeration<? extends ZipEntry> zipEntryEnum = file.entries();
 
        while (zipEntryEnum.hasMoreElements()) { 
          final ZipEntry zipEntry = zipEntryEnum.nextElement();
          extractEntry(file, zipEntry, destinationDirectory, bufferSize); 
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

//	public class LevelGZIPOutputStream extends GZIPOutputStream
//	{
//	/**
//	 * Creates a new output stream with a default buffer size and
//	 * sets the current compression level to the specified value.
//	 *
//	 * @param out the output stream.
//	 * @param level the new compression level (0-9).
//	 * @exception IOException If an I/O error has occurred.
//	 * @exception IllegalArgumentException if the compression level is invalid.
//	 */
//	public LevelGZIPOutputStream( OutputStream out, int compressionLevel )
//	    throws IOException
//	{
//	  super( out );
//	  def.setLevel( compressionLevel );
//	}
//	}
	
	/*
	 * Private methods
	 */
	private static void addEntry(final ZipOutputStream zos, final File file, final int bufferSize) throws IOException {
		BufferedInputStream bis = null;
		final byte[] buffer = new byte[bufferSize];

		try {
            bis = new BufferedInputStream(new FileInputStream(file));

	        // create a new zip entry 
			final ZipEntry entry = new ZipEntry(file.getPath());
	
	        // place the zip entry in the ZipOutputStream object 
	        zos.putNextEntry(entry); 
	        
            int offset;

            // now write the content of the file to the ZipOutputStream
            while(-1 != (offset = bis.read(buffer))) {
	            zos.write(buffer, 0, offset); 
	        } 
		} finally {
			if (null != bis) {
                bis.close();
            }
		}
	}
	
	private static void extractEntry(final ZipFile zipFile, final ZipEntry entry, final File destDir, final int bufferSize) throws IOException { 
		final File file = new File(destDir, entry.getName());
 	 
	    if (entry.isDirectory()) {
            file.mkdirs();
        }
	    else {
	    	new File(file.getParent()).mkdirs(); 
 
	    	BufferedInputStream bis = null;
	    	BufferedOutputStream bos = null;
 
	    	final byte[] buffer = new byte[bufferSize];
	    	
	    	try {
                bis = new BufferedInputStream(zipFile.getInputStream(entry));
                bos = new BufferedOutputStream(new FileOutputStream(file));

                int offset;
                while (-1 != (offset = bis.read(buffer))) {
	    			bos.write(buffer, 0, offset); 
	    		}
	    	} finally { 
    			if (null != bos) {
                    bos.close();
                }
                if (null != bis) {
    			    bis.close();
                }
	    	} 
	    }
	} 
}
