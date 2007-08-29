/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import ch.orwell.bogatyr.Context;


/**
 * This is a helper class for file system operations
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070829
 */
public abstract class FileManager {
	// Resources
	private final static String	RES_INVALID_DIRECTORY = "FileManager.invalidDirecory"; //$NON-NLS-1$
	
	/**
     * Search in a path (directory) for files via identifier
     * 
     * @param path Path
     * @param identifier Part of the file name (if it's "null", all files will be delivered)
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     * @return ArrayList containing the path to the matched files
     */	
	public static ArrayList<String> getFileNames(String path, String identifier, boolean isCaseSensitive, boolean isRecursive) throws IOException {
		ArrayList<String> fileList = new ArrayList<String>();

		File filePath = new File(path);
		
		if (!filePath.isDirectory()) {
			throw new IOException(Context.getInstance().getLocalizer().getValue(RES_INVALID_DIRECTORY));
		}

		getFileNamesRecursion(filePath, identifier, fileList, isCaseSensitive, isRecursive);

		return fileList;
	}
	
	/**
     * Copy a directory
     * 
     * @param source Source as absolut path
     * @param dest Destination as absolut path
     */	
	public static void copyDirectory(String source, String dest) throws IOException{
		File input = new File(source);
	    File output = new File(dest);

		if (!output.exists()) {
	      output.mkdir();
	    }
	    
		File[] children = input.listFiles();
	    
		for (File sourceChild : children) {
			String name = sourceChild.getName();
			File destChild = new File(output, name);
			
			if (sourceChild.isDirectory()) {
				copyDirectory(sourceChild.getAbsolutePath(), destChild.getAbsolutePath());
			} else {
				copyFile(sourceChild.getAbsolutePath(), destChild.getAbsolutePath());
			}
	    }
	}
	  
	/**
     * Copy a file
     * 
     * @param source Source as absolut path
     * @param dest Destination as absolut path
     */	
	public static void copyFile(String source, String dest) throws IOException{
	    File input = new File(source);
	    File output = new File(dest);
	    
		InputStream in = null;
	    OutputStream out = null;

		if (!output.exists()) {
			output.createNewFile();
	    }

		try {
			in = new FileInputStream(input);
			out = new FileOutputStream(output);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} finally {
			if (in != null)	in.close();
			if (out != null) out.close();
		}
	}
	
	/**
     * Move a file or directory
     * 
     * @param source Source as absolut path
     * @param dest Destination as absolut path
     */	
	public static boolean move(String source, String dest) throws IOException{
	    File input = new File(source);
	    
	    if (input.isDirectory()) {
	    	copyDirectory(source, dest);
	    } else {
	    	copyFile(source, dest);
	    }
	    return delete(source);
	}

	/**
     * Delete a file or directory
     * 
     * @param name Name (with absolut path)
     */	
	public static boolean delete(String name) throws IOException{ 
		File resource = new File(name);
		  
		if (resource.isDirectory()) {
			File[] childFiles = resource.listFiles();
			for(File child : childFiles) {
				delete(child.getAbsolutePath());
			}
		}
		return resource.delete();
	}
	  
	/**
     * Rename a file or directory
     * 
     * @param nameCurrent Current name (with absolut path)
     * @param nameNew New name (with absolut path)
     */	
	public static boolean rename(String nameCurrent, String nameNew) {
		File fileCurrent = new File(nameCurrent);
		File fileNew = new File(nameNew);
		return fileCurrent.renameTo(fileNew);
	}

	/**
     * Write a text line in a file
     * 
     * @param fileName File name (with absolut path)
     * @param code Code page of the text (e.g. "UTF-8")
     * @param line Text line
     */	
	public static void writeLine(String fileName, String code, String line) throws IOException {
		String encoding = code;
		
		PrintWriter file = null;
		
		if (!GeneralHelper.isValidString(encoding)) {
			encoding = "UTF-8"; //$NON-NLS-1$
		}
		
		file = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), encoding));    
		file.println(line);
		file.close();
	}
	
	/**
     * Write a text line with "UTF-8"-encoding in a file
     * 
     * @param fileName File name (with absolut path)
     * @param line Text line
     */	
	public static void writeLine(String fileName, String line) throws IOException {
		writeLine(fileName, null, line);
	}

	/**
     * Read a file in a byte-array
     * 
     * @param fileName File name (with absolut path)
     * @return byte-array
     */	
	public static byte[] readBinaryFile(String fileName) throws IOException {
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		
		long length = file.length();
		
		byte[] buffer = new byte[(int) length];
		fis.read(buffer, 0, (int) length);
		fis.close();
		
		return buffer;
	}

	/**
     * Write a byte-array into a file 
     * 
     * @param fileName File name (with absolut path)
     * @param data byte-array
     */	
	public static void writeBinaryFile(String fileName, byte data[]) throws IOException {
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(data);
		fos.close();
	}

	/**
     * Concatenate files to one output file 
     * 
     * @param outputFileName Output file name (with absolut path)
     * @param list List with all file names (with absolut path)
     */	
	@SuppressWarnings("unchecked")
	public static void concatenateFiles(String outputFileName, String list[]) throws IOException {
		FileOutputStream fos = new FileOutputStream(outputFileName);

		ListOfFiles lof = new ListOfFiles(list);

	    SequenceInputStream sis = new SequenceInputStream(lof);
        int c;

        while ((c = sis.read()) != -1) {
    		fos.write(c);
        }
        sis.close();
		fos.close();
	}

	
	/*
	 * Private methods
	 */
	/**
     * Recursive search method for a path (directories)
     * 
     * @param filePath Path
     * @param identifier Part of the file name (if it's "null", all files will be delivered)
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     */	
	private static void getFileNamesRecursion(File filePath, String identifier, ArrayList<String> fileList, boolean isCaseSensitive, boolean isRecursive) {
		File[] files = filePath.listFiles();
		File file;
		String fileName;
		
		for (int ii = 0; ii < files.length; ii++) {
			file = new File(files[ii].getAbsolutePath());
			if (file.isFile()) {
				fileName = file.getAbsolutePath();
				if (identifier == null) {
					fileList.add(fileName);
				} else if (isCaseSensitive && fileName.indexOf(identifier) > 0) {
					fileList.add(fileName);
				} else if (!isCaseSensitive && fileName.toUpperCase().indexOf(identifier.toUpperCase()) > 0) {
					fileList.add(fileName);
				}
			} else if (file.isDirectory() && isRecursive) {
				getFileNamesRecursion(file, identifier, fileList, isCaseSensitive, true); // Recursion
			}
		}
	}
	

	/*
	 * Inner classes
	 */
	static class ListOfFiles implements Enumeration<InputStream> {

	    String listOfFiles[];
	    int current = 0;

	    ListOfFiles(String listOfFiles[]) {
	    	this.listOfFiles = listOfFiles;
	    }

	    public boolean hasMoreElements() {
			if (this.current < this.listOfFiles.length) {
			    return true;
		    }
			return false;
	    }
	    
	    public InputStream nextElement() {
			InputStream is = null;
	
			if (!hasMoreElements()) {
			    throw new NoSuchElementException("No more files.");
			}

			try {
		        String nextElement = this.listOfFiles[this.current];
		        this.current++;
		        is = new FileInputStream(nextElement);
		    } catch (FileNotFoundException ex) {
				System.err.println("ListOfFiles: " + ex); //FIXME improve?
		    }
			return is;
	    }
	}
}
