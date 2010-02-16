/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.encoder;

import ch.sisprocom.bogatyr.misc.exception.RuntimeExceptionIsNull;



/**
 * Encodes and decodes data to HTML-format.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100216)
 * @since 0.9.1
 */
public abstract class EncoderHtml {
	public static String encode(final String input) {
        if (null == input) {
            throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
        }

        final StringBuffer sb = new StringBuffer(input.length());
	    boolean lastWasBlankChar = false;

	    for (final char c : input.toCharArray()) {
	        if (' ' == c) {
	            if (lastWasBlankChar) {
	                lastWasBlankChar = false;
	                sb.append("&nbsp;"); //$NON-NLS-1$
                } else {
	                lastWasBlankChar = true;
	                sb.append(' ');
                }
            } else {
	            lastWasBlankChar = false;
	            if ('"' == c) {
	                sb.append("&quot;"); //$NON-NLS-1$
	            } else if ('&' == c) {
	                sb.append("&amp;"); //$NON-NLS-1$
	            } else if ('<' == c) {
	                sb.append("&lt;"); //$NON-NLS-1$
	            } else if ('>' == c) {
	                sb.append("&gt;"); //$NON-NLS-1$
	            } else if ('\n' == c) {
	                sb.append("&lt;br/&gt;"); //$NON-NLS-1$
	            } else {
	                final int ci = 0xffff & c;
	                if (160 > ci) {
	                    // nothing special only 7 Bit
	                    sb.append(c);
	                } else {
	                    // Not 7 Bit use the unicode system
	                    sb.append("&#"); //$NON-NLS-1$
	                    sb.append(new Integer(ci).toString());
	                    sb.append(';');
	                    }
	                }
	            }
	        }
	    return sb.toString();
	}
}
