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
 * @version 20090506
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
}
