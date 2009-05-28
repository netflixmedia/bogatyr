/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;


/**
 * This is a helper class for I/O.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.8.0 (20090527)
 * @since 0.1.0
 */
public abstract class HelperIO {
	public static final String FILE_SEPARATOR = System.getProperty("file.separator"); //$NON-NLS-1$
	public static final String PATH_SEPARATOR = System.getProperty("path.separator"); //$NON-NLS-1$

	private static final byte[] BUFFER = new byte[HelperNumber.VALUE_1024];
//	private static final int BUFFER = 1024;
	
	/**
     * Returns a temporary {@link File} which will be deleted on program exit.
     * 
     * @param name of the file
     * @param extension of the file (e.g. ".java")
     * @return temporary file
     * @throws IOException
     * @since 0.5.0
     */	
	public static File getTemporaryFile(final String name, final String extension) throws IOException { //$JUnit$
		if (!HelperString.isValid(name)) {
			throw new IllegalArgumentException("name is null or empty!"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(extension)) {
			throw new IllegalArgumentException("extension is null or empty!"); //$NON-NLS-1$
		}

		// Create temp file
		final File file;
		if (extension.startsWith(HelperString.PERIOD)) {
			file = File.createTempFile(name, extension);
		} else {
			file = File.createTempFile(name, HelperString.PERIOD + extension);
		}
		
	    // Delete temp file when program exits
	    file.deleteOnExit();
	    
	    return file;
	}
	
	/**
     * Search in a path (directory) for files and directories via identifiers and returns a {@link Collection} containing all {@link File}.
     * 
     * @param path for searching
     * @param isExclude is the identifiers are excluded
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     * @param isFile true/false
     * @param isDirectory true/false
     * @param identifiers of parts from the file name (if it's "null", all files will be delivered)
     * @return List containing the matched files
     * @since 0.1.0
     */	
	public static Collection<File> getFiles(final File path, final boolean isExclude, final boolean isCaseSensitive, final boolean isRecursive, final boolean isFile, final boolean isDirectory, final String... identifiers) { //$JUnit$
		if (null == path) {
			throw new IllegalArgumentException("path is null!"); //$NON-NLS-1$
		}
		if (!path.isDirectory()) {
            throw new IllegalArgumentException("path is not a directory: " + path); //$NON-NLS-1$
        }

		final List<File> list = new ArrayList<File>();

		getFileNamesRecursion(path, identifiers, list, isExclude, isCaseSensitive, isRecursive, isFile, isDirectory);

		return list;
	}

	/**
     * Search in a path (directory) for files and directories via identifiers and returns a {@link Collection} containing all {@link File}.
     * 
     * @param path for searching
     * @param isExclude is the identifiers are excluded
     * @param identifiers of parts from the file name (if it's "null", all files will be delivered)
     * @return List containing the matched files
     * @throws IOException
     * @since 0.1.0
     */	
	public static Collection<File> getFiles(final File path, final boolean isExclude, final String... identifiers) throws IOException { //$JUnit$
		return getFiles(path, isExclude, false, true, true, true, identifiers);
	}
	
	/**
     * Search in a path (directory) for files and directories via identifiers and returns a {@link Collection} containing all {@link File}.
     * 
     * @param path for searching
     * @param identifiers of parts from the file name (if it's "null", all files will be delivered)
     * @return List containing the matched files
     * @throws IOException
     * @since 0.8.0
     */	
	public static Collection<File> getFiles(final File path, final String... identifiers) throws IOException {
		return getFiles(path, false, false, true, true, true, identifiers);
	}
	
	/**
     * Copy a directory.
     * 
     * @param source directory to copy
     * @param dest directory
     * @throws IOException
     * @since 0.1.0
     */	
	public static void copyDirectory(final File source, final File dest) throws IOException{
		if (null == source) {
			throw new IllegalArgumentException("source is null!"); //$NON-NLS-1$
		}
		if (!source.isDirectory()) {
			throw new IllegalArgumentException("source is not a directory: " + source); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new IllegalArgumentException("dest is null!"); //$NON-NLS-1$
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
     * @param source file to copy
     * @param dest file
     * @throws IOException
     * @since 0.1.0
     */	
	public static void copyFile(final File source, final File dest) throws IOException{
		if (null == source) {
			throw new IllegalArgumentException("source is null!"); //$NON-NLS-1$
		}
		if (!source.isFile()) {
			throw new IllegalArgumentException("source is not a file: " + source); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new IllegalArgumentException("dest is null!"); //$NON-NLS-1$
		}
		
		if (!dest.exists()) {
            dest.createNewFile();
        }

        InputStream fis = null;
        OutputStream fos = null;

        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(dest);
//			final byte[] buf = new byte[BUFFER];
			int len;
			while (0 < (len = fis.read(BUFFER))) {
				fos.write(BUFFER, 0, len);
			}
			fos.flush();
		} finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	/**
     * Move a file or directory.
     * 
     * @param source file/directory to move
     * @param dest file/directory
     * @throws IOException
     * @since 0.1.0
     */	
	public static void move(final File source, final File dest) throws IOException{
		if (null == source) {
			throw new IllegalArgumentException("source is null!"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new IllegalArgumentException("dest is null!"); //$NON-NLS-1$
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
     * @since 0.1.0
     */	
	public static void delete(final File... files) throws IOException{
		if (!HelperArray.isValid(files)) {
			throw new IllegalArgumentException("files is null or empty!"); //$NON-NLS-1$
		}

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
	  
	/**
     * Rename a file or directory.
     * 
     * @param source file/directory to rename
     * @param dest file/directory
     * @return true/false
     * @since 0.1.0
     */	
	public static boolean rename(final File source, final File dest) {
		if (null == source) {
			throw new IllegalArgumentException("source is null!"); //$NON-NLS-1$
		}
		if (null == dest) {
			throw new IllegalArgumentException("dest is null!"); //$NON-NLS-1$
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
     * @since 0.1.0
     */	
	public static void writeLine(final File file, final String encoding, final String line) throws IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
//		if (!file.isFile()) {
//			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
//		}
		if (!HelperString.isValid(encoding)) {
			throw new IllegalArgumentException("encoding is null or empty!"); //$NON-NLS-1$
		}
		if (null == line) {
			throw new IllegalArgumentException("line is null!"); //$NON-NLS-1$
		}

        PrintWriter pw = null;
        try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), encoding));    
			pw.println(line);
			pw.flush();
		} finally {
			if (pw != null) {
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
     * @since 0.1.0
     */	
	public static void writeFile(final File file, final byte[] data, final boolean append) throws IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
//		if (!file.isFile()) {
//			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
//		}
		if (null == data) {
			throw new IllegalArgumentException("data is null!"); //$NON-NLS-1$
		}

		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file, append);
			fos.write(data);
			fos.flush();
		} finally {
			if (fos != null) {
                fos.close();
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
     * @since 0.1.0
     */	
	public static void writeFile(final File file, final String data, final String encoding, final boolean append) throws IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
//		if (!file.isFile()) {
//			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
//		}
		if (!HelperString.isValid(encoding)) {
			throw new IllegalArgumentException("encoding is null or empty!"); //$NON-NLS-1$
		}
		if (null == data) {
			throw new IllegalArgumentException("data is null!"); //$NON-NLS-1$
		}

		final FileOutputStream fos = new FileOutputStream(file);
		final Writer writer = new OutputStreamWriter(fos, encoding); 
		
//		final Writer writer = new BufferedWriter(new FileWriter(file));
//		final Writer writer = new BufferedWriter(new FileWriter(file));
	
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
     * @since 0.1.0
     */	
	public static void writeStream(final OutputStream os, final byte[] data) throws IOException {
		if (null == os) {
			throw new IllegalArgumentException("os is null!"); //$NON-NLS-1$
		}
		if (null == data) {
			throw new IllegalArgumentException("data is null!"); //$NON-NLS-1$
		}

//	    try {
    		os.write(data);
    		os.flush();
//	    } finally {
//	      os.close();
//	    }
	}
	
	/**
     * Reads an {@link InputStream} in a byte-array.
     * 
     * @param is input stream for reading
     * @return byte-array containing the stream content
     * @throws IOException
     * @since 0.1.0
     */	
	public static byte[] readStream(final InputStream is) throws IOException {
		if (null == is) {
			throw new IllegalArgumentException("is is null!"); //$NON-NLS-1$
		}
		
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final byte[] result;
		
		try {
//            final byte[] buffer = new byte[BUFFER];
            int x;

            while (-1 != (x = is.read(BUFFER, 0, BUFFER.length))) {
				bos.write(BUFFER, 0, x);
			}
            bos.flush();
            
            result = bos.toByteArray();
		} finally {
			bos.close();
		}
		return result;
	}
	
	/**
     * Reads a {@link File} in a byte-array.
     * 
     * @param file for reading
     * @return byte-array containing the file content
     * @throws IOException
     * @since 0.1.0
     */	
	public static byte[] readFile(final File file) throws IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
		}

		final long length = file.length();
		FileInputStream fis = null;
		final byte[] buffer;
		
		try {
			fis = new FileInputStream(file);
			buffer = new byte[(int) length];
			fis.read(buffer, 0, (int) length);
		} finally {
			if (fis != null) {
                fis.close();
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
     * @since 0.1.0
     */	
	public static String readFileAsString(final File file, final String encoding) throws IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
		}
		if (!HelperString.isValid(encoding)) {
			throw new IllegalArgumentException("encoding is null or empty!"); //$NON-NLS-1$
		}

		final StringBuilder content = new StringBuilder();
		final Scanner scanner = new Scanner(file, encoding);

		try {
			if (scanner.hasNextLine()) {
				content.append(scanner.nextLine());
			}

	    	while (scanner.hasNextLine()){
	    		content.append(HelperString.NEW_LINE);
	    		content.append(scanner.nextLine());
            }
	    	return content.toString();
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
     * @since 0.1.0
     */	
	public static String readFileAsString(final File file) throws IOException { //$JUnit$
		return readFileAsString(file, Constants.ENCODING_DEFAULT);
	}
	
	/**
     * Reads a {@link File} in a {@link Collection} with the chosen encoding.
     * 
     * @param file for reading
     * @param encoding of the file
     * @return List containing the file content
     * @throws IOException
     * @since 0.1.0
     */	
	public static Collection<String> readFileAsList(final File file, final String encoding) throws IOException {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
		}
		if (!HelperString.isValid(encoding)) {
			throw new IllegalArgumentException("encoding is null or empty!"); //$NON-NLS-1$
		}

		final Scanner scanner = new Scanner(file, encoding);
		final Collection<String> list = new ArrayList<String>();
		
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
     * Reads a {@link File} in a {@link Collection} with the default encoding (UTF-8).
     * 
     * @param file for reading
     * @return List containing the file content
     * @throws IOException
     * @since 0.1.0
     */	
	public static Collection<String> readFileAsList(final File file) throws IOException {
		return readFileAsList(file, Constants.ENCODING_DEFAULT);
	}
	
	/**
     * Reads a {@link File} into an {@link OutputStream}.
     * 
     * @param file for reading
     * @param os output stream for the file content
     * @throws IOException
     * @since 0.1.0
     */	
	public static void readFileAsStream(final File file, final OutputStream os) throws IOException { //$JUnit$
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}
		if (!file.isFile()) {
			throw new IllegalArgumentException("file is not a file: " + file); //$NON-NLS-1$
		}
		if (null == os) {
			throw new IllegalArgumentException("os is null!"); //$NON-NLS-1$
		}

		writeStream(os, readFile(file));
	}	

	/**
     * Concatenates many files to one output {@link File}.
     * 
     * @param fileOutput Output file
     * @param files to concatenate
     * @throws IOException
     * @since 0.2.0
     */	
	public static void concatenateFiles(final File fileOutput, final File... files) throws IOException {
		if (null == fileOutput) {
			throw new IllegalArgumentException("fileOutput is null!"); //$NON-NLS-1$
		}
//		if (!fileOutput.isFile()) {
//			throw new IllegalArgumentException("fileOutput is not a file: " + fileOutput); //$NON-NLS-1$
//		}
		if (!HelperArray.isValid(files)) {
			throw new IllegalArgumentException("list is null or empty!"); //$NON-NLS-1$
		}

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
	
	                    while (line != null) {
	                        pw.println(line);
	                        line = br.readLine();
	                    }
	                } finally {
	                    if (br != null) {
	                        br.close();
	                    }
	                }
            	}
            }
	    } finally {
	    	if (pw != null) {
                pw.close();
            }
	    }
	}

	/**
	 * Returns the {@link URL} representation of a given {@link File}.
	 * 
	 * @param file to get the URL
	 * @return URL representation of a given file
	 * @throws MalformedURLException 
	 * @since 0.7.0
	 */
	public static URL getURL(final File file) throws MalformedURLException {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

        return file.toURI().toURL();
    }

	/**
	 * Returns a {@link Collection} containing all drive names of the current system.
	 * 
	 * @return list containing all drive names of the current system 
	 * @since 0.7.0
	 */
	public static Collection<String> getDriveNames() {
		final Collection<String> list = new ArrayList<String>(getAvailableDrives().size());
		final FileSystemView view = FileSystemView.getFileSystemView(); 
		
		for (final File file : getAvailableDrives()) { 
			list.add(view.getSystemDisplayName(file));
		}
		return list;
	}

	/**
	 * Returns a {@link Collection} containing all available drives of the current system.
	 * 
	 * @return list containing all drive names of the current system 
	 * @since 0.7.0
	 */
	public static Collection<File> getAvailableDrives() {
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
	 * Returns the total space of a given {@link File} location.
	 * 
	 * @param file location
	 * @return total space in bytes
	 * @since 0.7.0
	 */
	public static long getSpaceTotal(final File file) {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

		return file.getTotalSpace(); // / (1024 * 1024);
    }

	/**
	 * Returns the free space of a given {@link File} location.
	 * 
	 * @param file location
	 * @return free space in bytes
	 * @since 0.7.0
	 */
	public static long getSpaceFree(final File file) {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

        return file.getFreeSpace(); // / (1024 * 1024);
     }
	
	/** Returns the usable space of a {@link File} file location.
	 * 
	 * @param file location
	 * @return usable space in bytes
	 * @since 0.7.0
	 */
	public static long getSpaceUsable(final File file) {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

		return file.getUsableSpace(); // / (1024 * 1024);
     }
	 
	/**
	 * Returns the used space of a given {@link File} location.
	 * 
	 * @param file location
	 * @return used space in bytes
	 * @since 0.7.0
	 */
	public static long getSpaceUsed(final File file) {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

		return getSpaceTotal(file) - getSpaceFree(file);
     }
	
	/**
	 * Checks a given {@link File} if its a drive.
	 * 
	 * @param file location
	 * @return true/false
	 * @since 0.7.0
	 */
	public static boolean isDrive(final File file) {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

		return FileSystemView.getFileSystemView().isDrive(file);
     }
	
	/**
	 * Checks a given {@link File} if its a removable drive.
	 * 
	 * @param file location
	 * @return true/false
	 * @since 0.7.0
	 */
	public static boolean isRemovableDrive(final File file) {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

		return FileSystemView.getFileSystemView().isFloppyDrive(file);
     }
	
	/**
	 * Checks a given {@link File} if its a network drive.
	 * 
	 * @param file location
	 * @return true/false
	 * @since 0.7.0
	 */
	public static boolean isNetworkDrive(final File file) {
		if (null == file) {
			throw new IllegalArgumentException("file is null!"); //$NON-NLS-1$
		}

		return FileSystemView.getFileSystemView().isComputerNode(file);
    }
	
	/**
	 * Converts a given {@link ByteArrayOutputStream} to an {@link InputStream}.
	 * 
	 * @param baos byte-array output stream
	 * @return Input stream
	 * @since 0.7.0
	 */
	public static InputStream convertOutputToInputStream(final ByteArrayOutputStream baos) {
		return new ByteArrayInputStream(baos.toByteArray());
    }	

	/**
	 * Converts a given {@link Writer} to a {@link Reader}.
	 * 
	 * @param writer to convert
	 * @return Reader containing the data of the writer 
	 * @since 0.7.0
	 */
	public static Reader convertWriterToReader(final Writer writer) {
		return new StringReader(writer.toString());
    }
	
	
	/*
	 * Private methods
	 */
	/**
     * Recursive search method for a path (directories).
     * 
     * @param filePath Path
     * @param identifier array of parts from the file name (if it's "null", all files will be delivered)
     * @param fileList
     * @param isExclude true/false
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     * @param isFile true/false
     * @param isDirectory true/false
     * @since 0.1.0
     */	
	private static void getFileNamesRecursion(final File filePath, final String[] identifier, final List<File> fileList, final boolean isExclude, final boolean isCaseSensitive, final boolean isRecursive, final boolean isFile, final boolean isDirectory) {
		final File[] files = filePath.listFiles();
		
		if (files != null) {
			for (final File file : files) {
				if (isFile == file.isFile() || isDirectory == file.isDirectory()) {
					if (identifier == null) {
						fileList.add(file);
					} else {
						for (final String id : identifier) {
							 if (isCaseSensitive && file.getAbsolutePath().contains(id) != isExclude || !isCaseSensitive && file.getAbsolutePath().toUpperCase(Locale.getDefault()).contains(id.toUpperCase(Locale.getDefault())) != isExclude) {
								 fileList.add(file);
							 }
						}
					}
				}
	//			} else if (file.isDirectory() && isRecursive) {
					if (file.isDirectory() && isRecursive) {
						getFileNamesRecursion(file, identifier, fileList, isExclude, isCaseSensitive, true, isFile, isDirectory); // Recursion
					}
	//			}
			}
		}
	}
}
