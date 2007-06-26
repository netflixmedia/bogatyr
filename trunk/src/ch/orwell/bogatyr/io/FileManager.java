package ch.orwell.bogatyr.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import ch.orwell.bogatyr.Context;


/**
 * This is a helper class for file system operations
 * 
 * @author Roman Wuersch
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070625
 */
public abstract class FileManager {
	// Resources
	private final static String	RES_INVALID_DIRECTORY = "FileManager.invalidDirecory"; //$NON-NLS-1$
	
	/**
     * Search in a path (directory) for files via identifier
     * @param path Path
     * @param identifier Part of the file name (if it's "null", all files will be delivered)
     * @param isCaseSensitive true/false
     * @param isRecursive true/false
     * @return ArrayList containing the path to the matched files
     */	
	public static ArrayList<String> getFileNames(String path, String identifier, boolean isCaseSensitive, boolean isRecursive) throws Exception {
		ArrayList<String> fileList = new ArrayList<String>();

		File filePath = new File(path);
		
		if (!filePath.isDirectory()) {
			throw new Exception(Context.getInstance().getLocalizer().getValue(RES_INVALID_DIRECTORY));
		}

		getFileNamesRecursion(filePath, identifier, fileList, isCaseSensitive, isRecursive);

		return fileList;
	}
	
	/**
     * Delete a file
     * @param fileName File name (with absolut path)
     */	
	public static synchronized void deleteFile(String fileName) {
    	(new File(fileName)).delete();
    }

	/**
     * Rename a file
     * @param fileNameCurrent Current file name (with absolut path)
     * @param fileNameNew New file name (with absolut path)
     */	
	public static synchronized void renameFile(String fileNameCurrent, String fileNameNew) {
		File fileCurrent = new File(fileNameCurrent);
		File fileNew = new File(fileNameNew);
		fileCurrent.renameTo(fileNew);
	}

	/**
     * Write a text line in a file
     * @param fileName File name (with absolut path)
     * @param code Code page of the text (e.g. "UTF-8")
     * @param line Text line
     */	
	public static synchronized void writeLine(String fileName, String code, String line) throws IOException {
		String encoding = code;
		
		PrintWriter file = null;
		
		if (encoding == null) {
			encoding = "UTF-8"; //$NON-NLS-1$
		}
		
		file = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName, true), encoding));    
		file.println(line);
		file.close();
	}
	
	/**
     * Write a text line with "UTF-8"-encoding in a file
     * @param fileName File name (with absolut path)
     * @param line Text line
     */	
	public static synchronized void writeLine(String fileName, String line) throws IOException {
		writeLine(fileName, null, line);
	}

	/**
     * Read a file in a byte-array
     * @param fileName File name (with absolut path)
     * @return byte-array
     */	
	public static byte[] readBinaryFile(String fileName) throws IOException {
		File file = new File(fileName);
		FileInputStream fileInputStream = new FileInputStream(file);
		
		long length = file.length();
		
		byte[] buffer = new byte[(int) length];
		fileInputStream.read(buffer, 0, (int) length);
		fileInputStream.close();
		
		return buffer;
	}

	/**
     * Write a byte-array into a file 
     * @param fileName File name (with absolut path)
     * @param data byte-array
     */	
	public static void writeBinaryFile(String fileName, byte data[]) throws IOException {
		File file = new File(fileName);
		FileOutputStream fileoutputstream = new FileOutputStream(file);
		fileoutputstream.write(data);
		fileoutputstream.close();
	}

	
	/*
	 * Private methods
	 */
	/**
     * Recursive search method for a path (directories)
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
}
