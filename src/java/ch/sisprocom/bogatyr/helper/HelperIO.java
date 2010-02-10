/*******************************************************************************
 * Copyright (c) 2007-2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.filechooser.FileSystemView;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionExceedsVmMemory;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsEquals;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;


/**
 * This is a helper class for I/O.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20100209)
 * @since 0.1.0
 */
public abstract class HelperIO {
	public static final String FILE_SEPARATOR = System.getProperty("file.separator"); //$NON-NLS-1$
	public static final String PATH_SEPARATOR = System.getProperty("path.separator"); //$NON-NLS-1$

	/**
     * Returns a temporary {@link File} which will be deleted on program exit.
     * 
     * @param name of the file
     * @param extension of the file (e.g. ".java")
     * @return temporary file
     * @throws IOException
     * @see File
     * @since 0.5.0
     */	
	public static File getTemporaryFile(final String name, final String extension) throws IOException { //$JUnit$
		if (!HelperString.isValid(name)) {
			throw new RuntimeExceptionIsNullOrEmpty("name"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new RuntimeExceptionIsNullOrEmpty("extension"); //$NON-NLS-1$
		}

		// Create temp file
		final File file = extension.startsWith(HelperString.PERIOD) ? File.createTempFile(name, extension) : File.createTempFile(name, HelperString.PERIOD + extension);
		
	    // Delete temp file when program exits
	    file.deleteOnExit();
	    
	    return file;
	}
	
	/**
     * Returns a temporary {@link File} which will be deleted on program exit.
     * 
     * @return temporary file
     * @throws IOException
     * @see File
     * @since 0.9.0
     */	
	public static File getTemporaryFile() throws IOException { //$JUnit$
	    return getTemporaryFile(Constants.BOGATYR_NAME, "tmp"); //$NON-NLS-1$
	}
	
	/**
     * Copy a directory.
     * 
     * @param source directory to copy
     * @param dest directory destination
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void copyDirectory(final File source, final File dest) throws IOException {
		if (null == source) {
			throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
		}
//		if (!source.exists()) {
//			throw new RuntimeExceptionFileNotFound(source);
//		}
//		if (!source.isDirectory()) {
//			throw new IllegalArgumentException("source is not a directory: " + source); //$NON-NLS-1$
//		}
		if (null == dest) {
			throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
		}
		if (source.equals(dest)) {
			throw new RuntimeExceptionIsEquals("source", "dest"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (!dest.exists()) {
            dest.mkdir();
        }
	    
		final File[] children = source.listFiles();
	    
		for (final File sourceChild : children) {
			final String name = sourceChild.getName();
			final File destChild = new File(dest, name);
			
			if (sourceChild.isDirectory()) {
				copyDirectory(sourceChild, destChild);
			} else {
				copyFile(sourceChild, destChild);
			}
	    }
	}
	
	/**
     * Copy a {@link File}.
     * 
     * @param source {@link File} to copy
     * @param dest {@link File} destination
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void copyFile(final File source, final File dest) throws IOException {
        copyFile(source, dest, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

    /**
     * Copy a {@link File}.
     *
     * @param source {@link File} to copy
     * @param dest {@link File} destination
     * @param bufferSize in bytes
     * @throws IOException
     * @see File
     * @since 0.1.0
     */
    public static void copyFile(final File source, final File dest, final int bufferSize) throws IOException {
        if (null == source) {
            throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
        }
//		if (!source.exists()) {
//			throw new RuntimeExceptionFileNotFound(source);
//		}
//		if (!source.isFile()) {
//            throw new IllegalArgumentException("source is not a file: " + source); //$NON-NLS-1$
//        }
        if (null == dest) {
            throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
        }
		if (source.equals(dest)) {
			throw new RuntimeExceptionIsEquals("source", "dest"); //$NON-NLS-1$ //$NON-NLS-2$
		}
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
        	throw new RuntimeExceptionExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
        }
        
        final byte[] buffer = new byte[bufferSize];

        if (!dest.exists()) {
        	dest.mkdirs();
        	dest.delete();
            dest.createNewFile();
        }

        InputStream is = null;
        OutputStream os = null;

        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            int len;
            while (0 < (len = is.read(buffer))) {
                os.write(buffer, 0, len);
            }
            os.flush();
        } finally {
            if (null != is) {
                is.close();
            }
            if (null != os) {
                os.close();
            }
        }
    }

	/**
     * Move a file or directory.
     * 
     * @param source file/directory to move
     * @param dest file/directory
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void move(final File source, final File dest) throws IOException {
		if (null == source) {
			throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
		}
//		if (!source.exists()) {
//			throw new RuntimeExceptionFileNotFound(source);
//		}
		if (null == dest) {
			throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
		}
		if (source.equals(dest)) {
			throw new RuntimeExceptionIsEquals("source", "dest"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		if (source.isDirectory()) {
	    	copyDirectory(source, dest);
	    } else {
	    	copyFile(source, dest);
	    }
	    delete(source);
	}

	/**
     * Delete files or directories.
     * 
     * @param files to delete (files/directories)
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void delete(final File... files) throws IOException {
//		if (!HelperArray.isValid(files)) {
//			throw new RuntimeExceptionArgumentIsNullOrEmpty("files"); //$NON-NLS-1$
//		}

		if (HelperArray.isValid(files)) {
			for (final File target : files) {
				if (target.isDirectory()) {
					final File[] childFiles = target.listFiles();
					for (final File child : childFiles) {
						delete(child);
					}
				}
				target.delete();
			}
		}
	}
	  
	/**
     * Rename a file or directory.
     * 
     * @param source file/directory to rename
     * @param dest file/directory
     * @return true/false
     * @see File
     * @since 0.1.0
     */	
	public static boolean rename(final File source, final File dest) {
		if (null == source) {
			throw new RuntimeExceptionIsNull("source"); //$NON-NLS-1$
		}
//		if (!source.exists()) {
//			throw new RuntimeExceptionFileNotFound(source);
//		}
		if (null == dest) {
			throw new RuntimeExceptionIsNull("dest"); //$NON-NLS-1$
		}
		if (source.equals(dest)) {
			throw new RuntimeExceptionIsEquals("source", "dest"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return source.renameTo(dest);
	}

	
	/**
     * Writes a text line in a {@link File} with the chosen encoding.
     * 
     * @param file for writing
     * @param encoding of the file
     * @param line containing the text to write
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void writeLine(final File file, final String encoding, final String line) throws IOException { //$JUnit$
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(encoding)) {
			throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
		}
//		if (null == line) {
//			throw new RuntimeExceptionArgumentIsNull("line"); //$NON-NLS-1$
//		}

        PrintWriter pw = null;
        try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), encoding));    
			pw.println(line);
			pw.flush();
		} finally {
			if (null != pw) {
                pw.close();
            }
		}
	}
	
	/**
     * Writes a text line in a {@link File} with the default encoding (UTF-8).
     * 
     * @param file for writing
     * @param line containing the text to write
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void writeLine(final File file, final String line) throws IOException { //$JUnit$
		writeLine(file, Constants.ENCODING_DEFAULT, line);
	}

	/**
     * Writes a byte-array into a {@link File}.
     * 
     * @param file for writing
     * @param data byte-array to write
     * @param append to file?
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void writeFile(final File file, final byte[] data, final boolean append) throws IOException { //$JUnit$
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
//		if (null == data) {
//			throw new RuntimeExceptionArgumentIsNull("data"); //$NON-NLS-1$
//		}

		if (HelperArray.isValid(data)) {
			final BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file, append));
			
			try {
				bos.write(data);
				bos.flush();
			} finally {
	            bos.close();
			}
		}
	}
	
	/**
     * Writes a {@link String} into a {@link File} with the chosen encoding.
     * 
     * @param file for writing
     * @param data string to write
     * @param encoding of the file
     * @param append to file?
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void writeFile(final File file, final String data, final String encoding, final boolean append) throws IOException { //$JUnit$
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(encoding)) {
			throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
		}
//		if (null == data) {
//			throw new RuntimeExceptionArgumentIsNull("data"); //$NON-NLS-1$
//		}

		final Writer writer = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file)), encoding); 
	
	    try {
	    	if (append) {
	    		writer.append(data);
	    	} else {
	    		writer.write(data);
	    	}
	    	writer.flush();
	    } finally {
	    	writer.close();
	    }
	}
	
	/**
     * Writes a {@link String} into a {@link File} with the default encoding (UTF-8).
     * 
     * @param file for writing
     * @param data string to write
     * @param append to file?
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static void writeFile(final File file, final String data, final boolean append) throws IOException { //$JUnit$
		writeFile(file, data, Constants.ENCODING_DEFAULT, append);
	}
	
	/**
     * Writes an {@link InputStream} into a {@link File}.
     * 
     * @param file for writing
     * @param is stream to write
     * @param append to file?
     * @throws IOException
     * @see File
     * @see InputStream
     * @since 0.1.0
     */	
	public static void writeFile(final File file, final InputStream is, final boolean append) throws IOException { //$JUnit$
		writeFile(file, readStream(is), append);
	}	
	
	/**
     * Writes a byte array to an {@link OutputStream}.
     * 
     * @param os output stream for writing
     * @param data byte-array for the stream
     * @throws IOException
     * @see OutputStream
     * @since 0.1.0
     */	
	public static void writeStream(final OutputStream os, final byte[] data) throws IOException {
		if (null == os) {
			throw new RuntimeExceptionIsNull("os"); //$NON-NLS-1$
		}
//		if (null == data) {
//			throw new RuntimeExceptionArgumentIsNull("data"); //$NON-NLS-1$
//		}

		if (HelperArray.isValid(data)) {
    		os.write(data);
    		os.flush();
	    }
	}
	
	/**
     * Reads an {@link InputStream} in a byte-array.
     * 
     * @param is input stream for reading
     * @return byte-array containing the stream content
     * @throws IOException
     * @see InputStream
     * @since 0.1.0
     */	
	public static byte[] readStream(final InputStream is) throws IOException {
		return readStream(is, Constants.DEFAULT_FILE_BUFFER_SIZE);
	}

    /**
     * Reads an {@link InputStream} in a byte-array.
     *
     * @param is input stream for reading
     * @param bufferSize in bytes
     * @return byte-array containing the stream content
     * @throws IOException
     * @see InputStream
     * @since 0.1.0
     */
    public static byte[] readStream(final InputStream is, final int bufferSize) throws IOException {
        if (null == is) {
            throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
        }
        if (1 > bufferSize) {
            throw new RuntimeExceptionMustBeGreater("bufferSize", bufferSize, 1); //$NON-NLS-1$
        }
        if (bufferSize > HelperEnvironment.getMemoryFree()) {
        	throw new RuntimeExceptionExceedsVmMemory("bufferSize", bufferSize); //$NON-NLS-1$
        }

        final byte[] buffer = new byte[bufferSize];

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] result;

        try {
            int x;

            while (-1 != (x = is.read(buffer, 0, bufferSize))) {
                baos.write(buffer, 0, x);
            }
            baos.flush();

            result = baos.toByteArray();
        } finally {
            baos.close();
        }
        return result;
    }

	/**
     * Reads a {@link File} in a byte-array.
     * 
     * @param file for reading
     * @return byte-array containing the file content
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static byte[] readFile(final File file) throws IOException { //$JUnit$
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
//		if (!file.exists()) {
//			throw new RuntimeExceptionFileNotFound(file);
//		}
//		if (!file.isFile()) {
//			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
//		}

		final long length = file.length();

		if (Integer.MAX_VALUE < length) {
            throw new IllegalArgumentException("length of file (" + length + ") is to large to process (" + Integer.MAX_VALUE + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }
		if (length > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("file", file.length()); //$NON-NLS-1$
        }
		
		BufferedInputStream bis = null;
		final byte[] buffer;
		
		try {
            bis = new BufferedInputStream(new FileInputStream(file));
			buffer = new byte[(int) length];
			bis.read(buffer, 0, (int) length);
		} finally {
            if (null != bis) {
                bis.close();
            }
		}
		return buffer;
	}
	
	/**
     * Reads a {@link File} in a {@link String} with the chosen encoding.
     * 
     * @param file for reading
     * @param encoding of the file
     * @return String containing the file content
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static String readFileAsString(final File file, final String encoding) throws IOException { //$JUnit$
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
//		if (!file.exists()) {
//			throw new RuntimeExceptionFileNotFound(file);
//		}
//		if (!file.isFile()) {
//			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
//		}
		if (!HelperString.isValid(encoding)) {
			throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
		}
		
		final long length = file.length();

		if (length > HelperEnvironment.getMemoryFree()) {
            throw new IllegalArgumentException("length of file (" + length + ") exceeds the free VM memory (" + HelperEnvironment.getMemoryFree() + ')'); //$NON-NLS-1$ //$NON-NLS-2$
        }

		final StringBuilder sb = new StringBuilder();
		final Scanner scanner = new Scanner(file, encoding);

		try {
			int ii = 0;
	    	while (scanner.hasNextLine()){
	            if (0 < ii) {
	                sb.append(HelperString.NEW_LINE);
	            }
	            sb.append(scanner.nextLine());
	            ii++;
            }
	    	return sb.toString();
	    } finally {
	      scanner.close();
	    }
	}
	
	/**
     * Reads a {@link File} in a {@link String} with the default encoding (UTF-8).
     * 
     * @param file for reading
     * @return String containing the file content
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static String readFileAsString(final File file) throws IOException { //$JUnit$
		return readFileAsString(file, Constants.ENCODING_DEFAULT);
	}
	
	/**
     * Reads a {@link File} in a {@link List} with the chosen encoding.
     * 
     * @param file for reading
     * @param encoding of the file
     * @return {@link List} containing the file content
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static List<String> readFileAsList(final File file, final String encoding) throws IOException {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
//		if (!file.exists()) {
//			throw new RuntimeExceptionFileNotFound(file);
//		}
//		if (!file.isFile()) {
//			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
//		}
		if (!HelperString.isValid(encoding)) {
			throw new RuntimeExceptionIsNullOrEmpty("encoding"); //$NON-NLS-1$
		}
		if (file.length() > HelperEnvironment.getMemoryFree()) {
            throw new RuntimeExceptionExceedsVmMemory("file", file.length()); //$NON-NLS-1$
        }

		final Scanner scanner = new Scanner(file, encoding);
		final List<String> list = new ArrayList<String>();
		
		try {
	    	while (scanner.hasNextLine()){
	    		list.add(scanner.nextLine());
            }
	    } finally {
	      scanner.close();
	    }
		return list;
	}

	/**
     * Reads a {@link File} in a {@link List} with the default encoding (UTF-8).
     * 
     * @param file for reading
     * @return {@link List} containing the file content
     * @throws IOException
     * @see File
     * @since 0.1.0
     */	
	public static List<String> readFileAsList(final File file) throws IOException {
		return readFileAsList(file, Constants.ENCODING_DEFAULT);
	}
	
	/**
     * Reads a {@link File} into an {@link OutputStream}.
     * 
     * @param file for reading
     * @param os {@link OutputStream} for the file content
     * @throws IOException
     * @see File
     * @see OutputStream
     * @since 0.1.0
     */	
	public static void readFileAsStream(final File file, final OutputStream os) throws IOException { //$JUnit$
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
//		if (!file.exists()) {
//			throw new RuntimeExceptionFileNotFound(file);
//		}
//		if (!file.isFile()) {
//			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
//		}
		if (null == os) {
			throw new RuntimeExceptionIsNull("os"); //$NON-NLS-1$
		}

		writeStream(os, readFile(file));
	}	

	/**
     * Concatenates many files to one output {@link File}.
     * 
     * @param fileOutput Output file
     * @param files to concatenate
     * @throws IOException
     * @see File
     * @since 0.2.0
     */	
	public static void concatenateFiles(final File fileOutput, final File... files) throws IOException {
		if (null == fileOutput) {
			throw new RuntimeExceptionIsNull("fileOutput"); //$NON-NLS-1$
		}
//		if (!HelperArray.isValid(files)) {
//			throw new RuntimeExceptionArgumentIsNullOrEmpty("list"); //$NON-NLS-1$
//		}

		if (HelperArray.isValid(files)) {
			// Create output stream
		    PrintWriter pw = null;
	
	        try {
			    pw = new PrintWriter(new FileOutputStream(fileOutput));
	
	            BufferedReader br = null;
	
	            // Process all files that are not the destination file
	            for (final File file : files) {
	
	            	if (file.isFile()) {
		                try {
		                    // Create input stream
		                    br = new BufferedReader (new FileReader(file));
		
		                    // Read each line from the input file
		                    String line = br.readLine();
		
		                    while (null != line) {
		                        pw.println(line);
		                        line = br.readLine();
		                    }
		                } finally {
		                    if (null != br) {
		                        br.close();
		                    }
		                }
	            	}
	            }
		    } finally {
		    	if (null != pw) {
	                pw.close();
	            }
		    }
		}
	}

	/**
	 * Returns the {@link URL} representation of a given {@link File}.
	 * 
	 * @param file to get the URL
	 * @return {@link URL} representation of a given {@link File}
	 * @throws MalformedURLException 
	 * @see File
	 * @see URL
	 * @since 0.7.0
	 */
	public static URL getURL(final File file) throws MalformedURLException {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

        return file.toURI().toURL();
    }

	/**
	 * Returns a {@link List} containing all drive names of the current system.
	 * 
	 * @return {@link List} containing all drive names of the current system 
	 * @since 0.7.0
	 */
	public static List<String> getDriveNames() {
		final List<String> list = new ArrayList<String>(getAvailableDrives().size());
		final FileSystemView view = FileSystemView.getFileSystemView(); 
		
		for (final File file : getAvailableDrives()) { 
			list.add(view.getSystemDisplayName(file));
		}
		return list;
	}

	/**
	 * Returns a {@link List} containing all available drives of the current system.
	 * 
	 * @return {@link List} containing all drive names of the current system 
	 * @see File
	 * @since 0.7.0
	 */
	public static List<File> getAvailableDrives() {
		return Arrays.asList(File.listRoots());
	}
	
//	/**
//	 * Returns all drive names of the current system.
//	 * 
//	 * @return list containing all drive names of the current system 
//	 */
//	public static Collection<String> getAvailable() {
//		return Charset.availableCharsets();
//	}
	
	/**
	 * Returns the total space of a given {@link File} location in bytes.
	 * 
	 * @param file location
	 * @return total space in bytes
	 * @see File
	 * @since 0.7.0
	 */
	public static long getSpaceTotal(final File file) {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		return file.getTotalSpace();
    }

	/**
	 * Returns the free space of a given {@link File} location in bytes.
	 * 
	 * @param file location
	 * @return free space in bytes
	 * @see File
	 * @since 0.7.0
	 */
	public static long getSpaceFree(final File file) {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

        return file.getFreeSpace();
     }
	
	/** 
	 * Returns the usable space of a given {@link File} location in bytes.
	 * 
	 * @param file location
	 * @return usable space in bytes
	 * @see File
	 * @since 0.7.0
	 */
	public static long getSpaceUsable(final File file) {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		return file.getUsableSpace();
     }
	 
	/**
	 * Returns the used space of a given {@link File} location in bytes.
	 * 
	 * @param file location
	 * @return used space in bytes
	 * @see File
	 * @since 0.7.0
	 */
	public static long getSpaceUsed(final File file) {
		return getSpaceTotal(file) - getSpaceFree(file);
     }
	
	/**
	 * Checks a given {@link File} if it is a drive.
	 * 
	 * @param file location
	 * @return true/false
	 * @see File
	 * @since 0.7.0
	 */
	public static boolean isDrive(final File file) {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		return FileSystemView.getFileSystemView().isDrive(file);
     }
	
	/**
	 * Checks a given {@link File} if it is a removable drive.
	 * 
	 * @param file location
	 * @return true/false
	 * @see File
	 * @since 0.7.0
	 */
	public static boolean isRemovableDrive(final File file) {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		return FileSystemView.getFileSystemView().isFloppyDrive(file);
     }
	
	/**
	 * Checks a given {@link File} if it is a network drive.
	 * 
	 * @param file location
	 * @return true/false
	 * @see File
	 * @since 0.7.0
	 */
	public static boolean isNetworkDrive(final File file) {
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}

		return FileSystemView.getFileSystemView().isComputerNode(file);
    }
	
	/**
	 * Converts a given {@link ByteArrayOutputStream} to an {@link InputStream}.
	 * 
	 * @param baos byte-array output stream
	 * @return {@link InputStream}
	 * @see ByteArrayOutputStream
	 * @see InputStream
	 * @since 0.7.0
	 */
	public static InputStream convertOutputToInputStream(final ByteArrayOutputStream baos) {
		if (null == baos) {
			throw new RuntimeExceptionIsNull("baos"); //$NON-NLS-1$
		}

		return new ByteArrayInputStream(baos.toByteArray());
    }	

	/**
	 * Converts a given {@link Writer} to a {@link Reader}.
	 * 
	 * @param writer to convert
	 * @return {@link Reader} containing the data of the {@link Writer} 
	 * @see Writer
	 * @see Reader
	 * @since 0.7.0
	 */
	public static Reader convertWriterToReader(final Writer writer) {
		if (null == writer) {
			throw new RuntimeExceptionIsNull("writer"); //$NON-NLS-1$
		}

		return new StringReader(writer.toString());
    }
		
	/**
     * Searchs in a path (directory) for files and directories via {@link FileFilter} and returns a {@link List} containing all {@link File}.
     * 
     * @param path for searching
     * @param filter for the match criterias. No filter (== null) will return all files.
     * @param recurseDepth defines how many folder levels the recursion would pass. >= -1 always recurse, 0 only the current folder and any other value will continue recursion until 0 is hit.
     * @return {@link List} containing the matched files
     * @see File
     * @see FileFilter
     * @since 0.1.0
     */
    public static List<File> getFiles(final File path, final FileFilter filter, final int recurseDepth) { //$JUnit$
        if (null == path) {
            throw new RuntimeExceptionIsNull("path"); //$NON-NLS-1$
        }
		
        final List<File> files = new ArrayList<File>();
        final File[] entries = path.listFiles();
        int recurse = recurseDepth;

        if (null != entries) {
            for (final File entry : entries) {

                if (null == filter || filter.accept(entry)) {
                    files.add(entry);
                }

                if (-1 >= recurse || (0 < recurse && entry.isDirectory())) {
                    recurse--;
                    files.addAll(getFiles(entry, filter, recurse));
                    recurse++;
                }
            }
        }
        return files;
    }
    
	/**
     * Searchs in a path (directory) for files and directories via {@link FileFilter} and returns a {@link List} containing all {@link File}.
     * 
     * @param path for searching
     * @param filter for the match criterias. No filter (== null) will return all files.
     * @return {@link List} containing the matched files
     * @see File
     * @see FileFilter
     * @since 0.9.0
     */
    public static List<File> getFiles(final File path, final FileFilter filter) { //$JUnit$
         return getFiles(path, filter, -1);
    }
    
	/**
     * Searchs in a path (directory) for all files and directories and returns a {@link List} containing all {@link File}.
     * 
     * @param path for searching
     * @return {@link List} containing the found files
     * @see File
     * @since 0.9.0
     */
    public static List<File> getFiles(final File path) { //$JUnit$
	    final FileFilter filter = new FileFilter() { 
	    	@Override
            public boolean accept(final File file) {
	    		return true;
	    	} 
	    }; 
	    
    	return getFiles(path, filter, -1);
    }

	/**
     * Removes a file extension from a given {@link String}.
     * 
     * @param fileName to remove the extension
     * @return {@link String} without extension
     * @since 0.9.0
     */
    public static String removeFileExtension(final String fileName) {
    	if (null != fileName && fileName.contains(HelperString.PERIOD)) {
    		return fileName.substring(0, fileName.lastIndexOf(HelperString.PERIOD));
    	}
    	return null;
    }
}
