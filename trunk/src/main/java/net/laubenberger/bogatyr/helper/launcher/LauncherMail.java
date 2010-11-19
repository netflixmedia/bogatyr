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

package net.laubenberger.bogatyr.helper.launcher;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This launcher starts the system mail application.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101119)
 * @since 0.7.0
 */
public abstract class LauncherMail {
	private static final Logger log = LoggerFactory.getLogger(LauncherMail.class);

	private static final String HEX_SPACE = "%20"; //$NON-NLS-1$
	
	/**
	 * Opens the default mail application.
	 *
	 * @throws IOException
	 * @since 0.7.0
	 */
	public static void mail() throws IOException { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().mail();
		} else {
			throw new RuntimeException("Mail application not supported by your machine"); //$NON-NLS-1$
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Opens an email address given as {@link URI} with the default mail application.
	 *
	 * @param uri for the mail application (e.g. "mailto:yourname@yourmail.com"). It supports CC, BCC, SUBJECT, and BODY.
	 * @throws IOException
	 * @see URI
	 * @since 0.7.0
	 */
	public static void mail(final URI uri) throws IOException { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(uri));
		if (null == uri) {
			throw new RuntimeExceptionIsNull("uri"); //$NON-NLS-1$
		}

		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().mail(uri);
		} else {
			throw new RuntimeException("Mail application not supported by your machine"); //$NON-NLS-1$
		}
		
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Opens an email with a given subject, body and addresses with the default mail application.
	 *
	 * @param subject		  for the mail
	 * @param body			  for the mail
	 * @param emailAddresses for the mail (e.g. "yourname@yourmail.com")
	 * @throws IOException
	 * @throws URISyntaxException
	 * @since 0.7.0
	 */
	public static void mail(final String subject, final String body, final String... emailAddresses) throws IOException, URISyntaxException { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(subject, body, emailAddresses));
		if (null == subject) {
			throw new RuntimeExceptionIsNull("subject"); //$NON-NLS-1$
		}
		if (!HelperString.isValid(subject)) {
			throw new RuntimeExceptionIsEmpty("subject"); //$NON-NLS-1$
		}
//		if (null == body) {
//			throw new RuntimeExceptionIsNull("body"); //$NON-NLS-1$
//		}
		if (null == emailAddresses) {
			throw new RuntimeExceptionIsNull("emailAddresses"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(emailAddresses)) {
			throw new RuntimeExceptionIsEmpty("emailAddresses"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder();
		for (final String address : emailAddresses) {
			if (null == address) {
				throw new RuntimeExceptionIsNull("address"); //$NON-NLS-1$
			}
			if (0 < sb.length()) {
				sb.append(HelperString.COMMA);
			}

			sb.append(URLEncoder.encode(address, Constants.ENCODING_UTF8).replace(HelperString.PLUS_SIGN, HEX_SPACE));
		}
		sb.append("?subject="); //$NON-NLS-1$
		sb.append(URLEncoder.encode(subject, Constants.ENCODING_UTF8).replace(HelperString.PLUS_SIGN, HEX_SPACE));
		
		if (null != body) {
			sb.append("&body="); //$NON-NLS-1$
			sb.append(URLEncoder.encode(body, Constants.ENCODING_UTF8).replace(HelperString.PLUS_SIGN, HEX_SPACE));
		}
		
		final String addresses = sb.toString();

		final String prefix = "mailto:"; //$NON-NLS-1$

		mail(new URI(prefix + addresses));

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
}
