/*******************************************************************************
 * Copyright (c) 2008 by SiSprocom GmbH.
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

import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * This is a helper class for XML operations
 * 
 * @author Stefan Laubenberger
 * @version 20080901
 */
public abstract class HelperXml {
	/**
     * This method ensures that the output String has only
     * valid XML unicode characters as specified by the
     * XML 1.0 standard. For reference, please see
     * <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the
     * standard</a>. This method will return an empty
     * String if the input is null or empty.
     *
     * @param in The String whose non-valid characters we want to remove.
     * @return The in String, stripped of non-valid characters.
     */
    public static String getValidXmlString(final String in) {
		Logger.getInstance().writeMethodEntry(HelperXml.class, "getValidXmlString", in);  //$NON-NLS-1$

    	final StringBuilder out = new StringBuilder(); // Used to hold the output.
        char current; // Used to reference the current character.

        if (!HelperGeneral.isValidString(in)) { // vacancy test
            return ""; //$NON-NLS-1$
        }
        
        for (int ii = 0; ii < in.length(); ii++) {
            current = in.charAt(ii);
//            if (current != 0x96 && current != 0x9C) { // new
            if (current != (char) 0x26) { // new
	            if (current == (char) 0x9 || current == (char) 0xA || current == (char) 0xD || current >= (char) 0x20 && current <= (char) 0xD7FF || current >= (char) 0xE000 && current <= (char) 0xFFFD || current >= (char) 0x10000 && current <= (char) 0x10FFFF) {
                    out.append(current);
                }
            }
        }

        final String result = out.toString();
        
		Logger.getInstance().writeMethodExit(HelperXml.class, "getValidXmlString", result);  //$NON-NLS-1$
        return result;
    }   
}