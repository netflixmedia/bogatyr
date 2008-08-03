/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ch.orwell.bogatyr.helper.localizer.Localizer;


/**
 * This is a helper class for file system operations
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20080803
 */
public abstract class HelperFile {
	private static final int BUFFER = 1024;
	
	/**
     * Search in a path (directory) for files via identifier
     * 
     * @param path Path
     * @param identifier array of parts from the file name (if it's "null", all files will be delivered)
     * @param isExclude Is the identifier excluded
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     * @param isFile true/false
     * @param isDirectory true/false
     * @return ArrayList containing the path to the matched files
     * @throws java.io.IOException
     */	
	public static List<File> getFiles(final File path, final String[] identifier, final boolean isExclude, final boolean isCaseSensitive, final boolean isRecursive, final boolean isFile, final boolean isDirectory) throws IOException {
		final List<File> fileList = new ArrayList<File>();

		if (!path.isDirectory()) {
            throw new IOException(Localizer.getInstance().getValue(Localizer.RES_INVALID_DIRECTORY));
        }

		getFileNamesRecursion(path, identifier, fileList, isExclude, isCaseSensitive, isRecursive, isFile, isDirectory);

		return fileList;
	}

	/**
     * Search in a path (directory) for files and directories via identifier
     * 
     * @param path Path
     * @param identifier array of parts from the file name (if it's "null", all files will be delivered)
     * @param isExclude Is the identifier excluded
     * @return ArrayList containing the path to the matched files
     */	
	public static List<File> getFiles(final File path, final String[] identifier, final boolean isExclude) throws IOException {
		return getFiles(path, identifier, isExclude, false, true, true, true);
	}

	/**
     * Copy a directory
     * 
     * @param source Source as absolute path
     * @param dest Destination as absolute path
     * @throws java.io.IOException
     */	
	public static void copyDirectory(final File source, final File dest) throws IOException{
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
     * Copy a file
     * 
     * @param source Source as absolute path
     * @param dest Destination as absolute path
     */	
	public static void copyFile(final File source, final File dest) throws IOException{
        InputStream in = null;
        OutputStream out = null;

		if (!dest.exists()) {
            dest.createNewFile();
        }

        try {
            in = new FileInputStream(source);
            out = new FileOutputStream(dest);
			final byte[] buf = new byte[BUFFER];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
     * Move a file or directory
     * 
     * @param source Source as absolute path
     * @param dest Destination as absolute path
     * @return true/false
     * @throws java.io.IOException
     */	
	public static boolean move(final File source, final File dest) throws IOException{
	    if (source.isDirectory()) {
	    	copyDirectory(source, dest);
	    } else {
	    	copyFile(source, dest);
	    }
	    return delete(source);
	}

	/**
     * Delete a file or directory
     * 
     * @param file
     * @return true/false
     */	
	public static boolean delete(final File file) throws IOException{
		if (file.isDirectory()) {
			final File[] childFiles = file.listFiles();
			for (final File child : childFiles) {
				delete(child);
			}
		}
		return file.delete();
	}
	  
	/**
     * Rename a file or directory
     * 
     * @param source
     * @param dest
     * @return true/false
     */	
	public static boolean rename(final File source, final File dest) {
		return source.renameTo(dest);
	}

	
	/**
     * Writes a text line in a file
     * 
     * @param file File name (with absolute path)
     * @param encoding
     * @param line Text line
     * @throws java.io.IOException
     */	
	public static void writeLine(final File file, final String encoding, final String line) throws IOException {
		String enc = encoding;
		
		if (!HelperGeneral.isValidString(encoding)) {
			enc = "UTF-8"; //$NON-NLS-1$
		}

        PrintWriter pw = null;
        try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file, true), enc));    
			pw.println(line);
		} finally {
			if (pw != null) {
                pw.close();
            }
		}
	}
	
	/**
     * Writes a text line with "UTF-8"-encoding in a file
     * 
     * @param file File name (with absolute path)
     * @param line Text line
     * @throws java.io.IOException
     */	
	public static void writeLine(final File file, final String line) throws IOException {
		writeLine(file, null, line);
	}

	/**
     * Writes a byte-array into a file 
     * 
     * @param file
     * @param data byte-array
     * @param append
     * @throws java.io.IOException
     */	
	public static void writeFileAsBinary(final File file, final byte[] data, final boolean append) throws IOException {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file, append);
			fos.write(data);
		} finally {
			if (fos != null) {
                fos.close();
            }
		}
	}
	
	/**
     * Writes a String into a file 
     * 
     * @param file
     * @param data String
     * @param append
     * @throws java.io.IOException
     */	
	public static void writeFileAsString(final File file, final String data, final boolean append) throws IOException {
	    final Writer writer = new BufferedWriter(new FileWriter(file));
	
	    try {
	    	if (append) {
	    		writer.append(data);
	    	} else {
	    		writer.write(data);
	    	}
	    } finally {
	      writer.close();
	    }
	}
	
	/**
     * Reads a stream in a byte-array.
     * This is a fast method for reading files/resources streams but is probably not suitable for network streams.
     * 
     * @param in InputStream
     * @return byte-array
     */	
	public static byte[] readStreamFast(final InputStream in) throws IOException {
		
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.flush();

		try {
            final byte[] buffer = new byte[BUFFER];
            while (in.available() != 0) {
				bos.write(buffer, 0, in.read(buffer, 0, BUFFER));
			}
		
			return bos.toByteArray();
		} finally {
			bos.close();
		}
	}
	
	/**
     * Reads a stream in a byte-array.
     * This is a slow method for secure reading a stream (e.g. network streams).
     * 
     * @param in InputStream
     * @return byte-array
     */	
	public static byte[] readStreamSecure(InputStream in) throws IOException {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.flush();

		try {
			while (in.available() != 0) {
				bos.write(in.read());
			}
		
			return bos.toByteArray();
		} finally {
			bos.close();
		}
	}
	
	/**
     * Reads a file in a byte-array
     * 
     * @param file File name (with absolute path)
     * @return byte-array
     * @throws java.io.IOException
     */	
	public static byte[] readFileAsBinary(final File file) throws IOException {
		final long length = file.length();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			final byte[] buffer = new byte[(int) length];
			fis.read(buffer, 0, (int) length);

			return buffer;
		} finally {
			if (fis != null) {
                fis.close();
            }
		}
	}
	
	/**
     * Reads a file in a String
     * 
     * @param file File name (with absolute path)
     * @return String
     */	
	public static String readFileAsString(final File file) throws IOException {
        final StringBuilder contents = new StringBuilder();
		 
		final Scanner scanner = new Scanner(file);
	    try {
	    	while (scanner.hasNextLine()){
	    		contents.append(scanner.nextLine());
//	    		contents.append(System.getProperty("line.separator")); //$NON-NLS-1$
                contents.append('\n');
            }
	    	return contents.toString();
	    } finally {
	      scanner.close();
	    }
	}

	/**
     * Concatenates a list of files to one output file 
     * 
     * @param fileOutput Output file name (with absolute path)
     * @param list List with all file names (with absolute path)
     * @throws java.io.IOException
     */	
	public static void concatenateFiles(final File fileOutput, final File[] list) throws IOException {
		// Create output stream
	    PrintWriter pw = null;

	    try {
		    pw = new PrintWriter(new FileOutputStream(fileOutput));
		    // Process all files that are not the destination file
		    for (final File file : list) {
	
				// Create input stream
				final BufferedReader br = new BufferedReader (new FileReader(file));
		
				// Read each line from the input file
				String line = br.readLine();
				
				while (line != null) {
				    pw.println(line);
				    line = br.readLine();
				}
				br.close();
		    }
	    } finally {
	    	if (pw != null) {
                pw.close();
            }
	    }
	}
	
//	public static long getTotalSpace(File file) { //Java 1.6
//        // Using the getTotalSpace() we can get an information of
//        // the actual size of the partition, and we convert it to
//        // mega bytes. 
//        return file.getTotalSpace() // / (1024 * 1024);
//    }
//
//	public static long getFreeSpace(File file) { //Java 1.6
//        // Next we get the free disk space as the name of the
//        // method shown us, and also get the size in mega bytes.
//        return file.getFreeSpace(); // / (1024 * 1024);
//     }

	
	/*
	 * Private methods
	 */
	/**
     * Recursive search method for a path (directories)
     * 
     * @param filePath Path
     * @param identifier array of parts from the file name (if it's "null", all files will be delivered)
     * @param fileList
     * @param isExclude true/false
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     * @param isFile true/false
     * @param isDirectory true/false
     */	
	private static void getFileNamesRecursion(final File filePath, final String[] identifier, final List<File> fileList, final boolean isExclude, final boolean isCaseSensitive, final boolean isRecursive, final boolean isFile, final boolean isDirectory) {
		final File[] files = filePath.listFiles();
		
		if (files != null) {
			for (final File file : files) {
				if (isFile == file.isFile() || isDirectory == file.isDirectory()) {
					if (identifier == null) {
						fileList.add(file);
					} else {
						for (String id : identifier) {
							 if (isCaseSensitive && file.getAbsolutePath().contains(id) != isExclude || !isCaseSensitive && file.getAbsolutePath().toUpperCase().contains(id.toUpperCase()) != isExclude) {
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
