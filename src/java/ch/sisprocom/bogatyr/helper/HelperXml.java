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



/**
 * This is a helper class for XML operations.
 * 
 * @author Stefan Laubenberger
 * @version 20081215
 */
public abstract class HelperXml { //TODO document in Wiki!
	/**
     * This method ensures that the output String has only valid XML unicode characters as specified by the XML 1.0 standard.
     * For reference, please see <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the standard</a>. 
     * This method will return an empty String if the input is null or empty.
     *
     * @param in the String whose non-valid characters we want to remove
     * @return the in String, stripped of non-valid characters
     */
    public static String getValidXmlString(final String in) {
        if (!HelperGeneral.isValidString(in)) { // vacancy test
            return ""; //$NON-NLS-1$
        }
       
       	final StringBuilder sb = new StringBuilder(); // Used to hold the output.

        for (final char current : in.toCharArray()) {
//            if (current != 0x96 && current != 0x9C) { // new
            if ((char) 0x26 != current) { // new
	            if ((char) 0x9 == current || (char) 0xA == current || (char) 0xD == current || (char) 0x20 <= current && (char) 0xD7FF >= current || (char) 0xE000 <= current && (char) 0xFFFD >= current || (char) 0x10000 <= current && (char) 0x10FFFF >= current) {
                    sb.append(current);
                }
            }
        }
        return sb.toString();
    }   
}