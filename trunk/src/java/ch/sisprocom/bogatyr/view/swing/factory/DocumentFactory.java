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
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.view.swing.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import ch.sisprocom.bogatyr.helper.HelperString;


/**
 * This is an document factory.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100126)
 * @since 0.9.0
 */
public abstract class DocumentFactory {
	private static final Pattern PATTERN = Pattern.compile("[-%'0-9.]+"); //$NON-NLS-1$

	public static PlainDocument createTextDocument(final int length) {
		return new PlainDocument() {
			private static final long serialVersionUID = -5008928912535075396L;

			@Override
			public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
				if (null != str && str.length() + offs <= length) {
					super.insertString(offs, str, a);
				}
			}
		};
	}
	
	public static PlainDocument createNumberDocument(final int length) {
		return new PlainDocument() {
			private static final long serialVersionUID = 3766889554419497713L;

			@Override
			public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
				if (null != str && str.length() + offs <= length && isStringNumeric(str)) {
		 	          super.insertString(offs, str, a);
				}
			}
		};
	}	
	
	static boolean isStringNumeric(final CharSequence arg) {
		if (HelperString.isValid(arg)) {
			final Matcher matcher = PATTERN.matcher(arg);

			if (matcher.matches()) {
                return true;
            }
		}
		return false;
	}
}
