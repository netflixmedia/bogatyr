/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.helper.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperCollection;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * Encodes and decodes data to HTML-format.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.9.1
 */
public abstract class EncoderHtml {
	private static final Logger log = LoggerFactory.getLogger(HelperCollection.class);

	public static String encode(final String input) {
		log.debug(HelperLog.methodStart(input));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder(input.length());
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
						sb.append(Integer.toString(ci));
						sb.append(';');
					}
				}
			}
		}
		final String result = sb.toString();

		log.debug(HelperLog.methodExit(result));
		return result;
	}
}
