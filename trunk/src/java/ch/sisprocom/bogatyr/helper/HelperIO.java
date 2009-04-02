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


/**
 * This is a helper class for I/O.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090122
 */
public abstract class HelperIO {
	private static final byte[] BUFFER = new byte[1024];
//	private static final int BUFFER = 1024;
	
	/**
     * Returns a temporary file which will be deleted on program exit.
     * 
     * @param name of the file
     * @param extension of the file (e.g. ".java")
     * @return temporary file
     * @throws IOException
     */	
	public static File getTemporaryFile(final String name, final String extension) throws IOException {
		// Create temp file
	    final File file = File.createTempFile(name, extension);
	
	    // Delete temp file when program exits
	    file.deleteOnExit();
	    
	    return file;
	}
	
	/**
     * Search in a path (directory) for files via identifier.
     * 
     * @param path Path
     * @param identifier array of parts from the file name (if it's "null", all files will be delivered)
     * @param isExclude is the identifier excluded
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     * @param isFile true/false
     * @param isDirectory true/false
     * @return ArrayList containing the path to the matched files
     * @throws IOException
     */	
	public static Iterable<File> getFiles(final File path, final String[] identifier, final boolean isExclude, final boolean isCaseSensitive, final boolean isRecursive, final boolean isFile, final boolean isDirectory) throws IOException {
		final List<File> list = new ArrayList<File>();

		if (!path.isDirectory()) {
            throw new IOException("not a directory");
        }

		getFileNamesRecursion(path, identifier, list, isExclude, isCaseSensitive, isRecursive, isFile, isDirectory);

		return list;
	}

	/**
     * Search in a path (directory) for files and directories via identifier.
     * 
     * @param path Path
     * @param identifier array of parts from the file name (if it's "null", all files will be delivered)
     * @param isExclude is the identifier excluded
     * @return ArrayList containing the path to the matched files
     * @throws IOException
     */	
	public static Iterable<File> getFiles(final File path, final String[] identifier, final boolean isExclude) throws IOException {
		return getFiles(path, identifier, isExclude, false, true, true, true);
	}

	/**
     * Copy a directory.
     * 
     * @param source
     * @param dest
     * @throws IOException
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
     * Copy a file.
     * 
     * @param source
     * @param dest
     * @throws IOException
     */	
	public static void copyFile(final File source, final File dest) throws IOException{
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
     * @param source
     * @param dest
     * @return true/false
     * @throws IOException
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
     * Delete a file or directory.
     * 
     * @param file to delete
     * @return true/false
     * @throws IOException
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
     * Rename a file or directory.
     * 
     * @param source
     * @param dest
     * @return true/false
     */	
	public static boolean rename(final File source, final File dest) {
		return source.renameTo(dest);
	}

	
	/**
     * Writes a text line in a file.
     * 
     * @param file for writing
     * @param encoding of the file
     * @param line containing the text to write
     * @throws IOException
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
			pw.flush();
		} finally {
			if (pw != null) {
                pw.close();
            }
		}
	}
	
	/**
     * Writes a text line with "UTF-8"-encoding in a file.
     * 
     * @param file for writing
     * @param line containing the text to write
     * @throws IOException
     */	
	public static void writeLine(final File file, final String line) throws IOException {
		writeLine(file, null, line);
	}

	/**
     * Writes a byte-array into a file.
     * 
     * @param file for writing
     * @param data byte-array to write
     * @param append to file?
     * @throws IOException
     */	
	public static void writeFileFromBinary(final File file, final byte[] data, final boolean append) throws IOException {
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
     * Writes a string into a file.
     * 
     * @param file for writing
     * @param data string to write
     * @param append to file?
     * @throws IOException
     */	
	public static void writeFileFromString(final File file, final String data, final boolean append) throws IOException {
		final Writer writer = new BufferedWriter(new FileWriter(file));
	
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
     * Writes a stream into a file.
     * 
     * @param file for writing
     * @param is stream to write
     * @param append to file?
     * @throws IOException
     */	
	public static void writeFileFromStream(final File file, final InputStream is, final boolean append) throws IOException { //TODO document in Wiki
		writeFileFromBinary(file, readStream(is), append);
	}	
	
	/**
     * Writes a byte array to a stream.
     * 
     * @param os output stream for writing
     * @param data byte-array for the stream
     * @throws IOException
     */	
	public static void writeStream(final OutputStream os, final byte[] data) throws IOException {
//	    try {
    		os.write(data);
    		os.flush();
//	    } finally {
//	      os.close();
//	    }
	}
	
	/**
     * Reads a stream in a byte-array.
     * 
     * @param is InputStream for reading
     * @return byte-array containing the stream content
     * @throws IOException
     */	
	public static byte[] readStream(final InputStream is) throws IOException {
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
	
//	/**
//     * Reads a stream in a byte-array.
//     * This is a slow method for secure reading a stream (e.g. network streams).
//     * 
//     * @param in InputStream for reading
//     * @return byte-array containing the stream content
//     */	
//	public static byte[] readStreamSecure(InputStream in) throws IOException {
//		Logger.getInstance().writeMethodEntry(HelperIO.class, "readStreamSecure", in);  //$NON-NLS-1$
//
//		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		final byte[] result;
//		int x;
//		
//		try {
//			while ((x = in.read()) != -1) {
//				bos.write(x);
//			}
//			bos.flush();
//			result = bos.toByteArray();
//		} finally {
//			bos.close();
//		}
//		
//		Logger.getInstance().writeMethodExit(HelperIO.class, "readStreamSecure", result);  //$NON-NLS-1$
//		return result;
//	}
	
	/**
     * Reads a file in a byte-array.
     * 
     * @param file for reading
     * @return byte-array containing the file content
     * @throws IOException
     */	
	public static byte[] readFileAsBinary(final File file) throws IOException {
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
     * Reads a file in a String.
     * 
     * @param file for reading
     * @return String containing the file content
     * @throws IOException
     */	
	public static String readFileAsString(final File file) throws IOException {
		final StringBuilder contents = new StringBuilder();
		final Scanner scanner = new Scanner(file);
		final String str;
		
		try {
	    	while (scanner.hasNextLine()){
	    		contents.append(scanner.nextLine());
//	    		contents.append(System.getProperty("line.separator")); //$NON-NLS-1$
                contents.append(HelperGeneral.getLS());
            }
	    	str = contents.toString();
	    } finally {
	      scanner.close();
	    }
		return str;
	}

	/**
     * Reads a file in a list.
     * 
     * @param file for reading
     * @return List containing the file content
     * @throws IOException
     */	
	public static List<String> readFileAsList(final File file) throws IOException {
		final Scanner scanner = new Scanner(file);
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
     * Reads a file into a stream.
     * 
     * @param file for reading
     * @param os output stream for the file content
     * @throws IOException
     */	
	public static void readFileAsStream(final File file, final OutputStream os) throws IOException { //TODO document in Wiki
		writeStream(os, readFileAsBinary(file));
	}	

	/**
     * Concatenates a list of files to one output file.
     * 
     * @param fileOutput Output file
     * @param list List with all files
     * @throws IOException
     */	
	public static void concatenateFiles(final File fileOutput, final File[] list) throws IOException {
		// Create output stream
	    PrintWriter pw = null;

        try {
		    pw = new PrintWriter(new FileOutputStream(fileOutput));

            BufferedReader br = null;

            // Process all files that are not the destination file
            for (final File file : list) {

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
