/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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


/**
 * Collected constants of very general utility.
 * 
 * @author Stefan Laubenberger
 * @version 20090511
 */
public interface Const {
	  //characters
	  String NEW_LINE = System.getProperty("line.separator"); //$NON-NLS-1$
	  String FILE_SEPARATOR = System.getProperty("file.separator"); //$NON-NLS-1$
	  String PATH_SEPARATOR = System.getProperty("path.separator"); //$NON-NLS-1$

	  String EMPTY_STRING = ""; //$NON-NLS-1$
	  String SPACE = " "; //$NON-NLS-1$
	  String PERIOD = "."; //$NON-NLS-1$
	  String SEMICOLON = ";"; //$NON-NLS-1$
	  String TAB = "\t"; //$NON-NLS-1$
	  String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	  String DOUBLE_QUOTE = "\""; //$NON-NLS-1$

	  //algebraic signs
	  int POSITIVE = 1;
	  int NEGATIVE = -1;
	  String PLUS_SIGN = "+"; //$NON-NLS-1$
	  String NEGATIVE_SIGN = "-"; //$NON-NLS-1$
	  
	  //constants
	  int DIVIDER_KILOBYTE = 1024;
	  int DIVIDER_MEGABYTE = 1024 * 1024;
	  int DIVIDER_GIGABYTE = 1024 * 1024 * 1024;
	  
	  long MILLISECONDS_MIN = 60L * 1000L;
	  long MILLISECONDS_HOUR = 60L * MILLISECONDS_MIN;
	  long MILLISECONDS_DAY = 24L * MILLISECONDS_HOUR;
	  long MILLISECONDS_WEEK = 7L * MILLISECONDS_DAY;
	  long MILLISECONDS_YEAR = 365L * MILLISECONDS_DAY;
	  long SECONDS_1900_1970 = 2208988800L;
	  
	  int VALUE_8 = 8;
	  int VALUE_16 = 16;
	  int VALUE_32 = 32;
	  int VALUE_64 = 64;
	  int VALUE_128 = 128;
	  int VALUE_256 = 256;
	  int VALUE_512 = 512;
	  int VALUE_1024 = 1024;
	  int VALUE_2048 = 2048;
	  int VALUE_4096 = 4096;
	  int VALUE_8192 = 8192;
	  int VALUE_16384 = 16384;
	  int VALUE_32768 = 32768;
	  int VALUE_65536 = 65536;
}
