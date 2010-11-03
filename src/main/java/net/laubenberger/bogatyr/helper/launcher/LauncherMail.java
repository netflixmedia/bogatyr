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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.laubenberger.bogatyr.helper.HelperArray;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.helper.encoder.EncoderHex;
import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This launcher starts the system mail application.
 *
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101103)
 * @since 0.7.0
 */
public abstract class LauncherMail {
	private static final Logger log = LoggerFactory.getLogger(LauncherMail.class);

	private static final Pattern PATTERN = Pattern.compile(" "); //$NON-NLS-1$
	
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
			throw new RuntimeException("Mail application not supported by your machine!"); //$NON-NLS-1$
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
		if (Desktop.isDesktopSupported()) {
			if (null == uri) {
				throw new RuntimeExceptionIsNull("uri"); //$NON-NLS-1$
			}

			Desktop.getDesktop().mail(uri);
		} else {
			throw new RuntimeException("Mail application not supported by your machine!"); //$NON-NLS-1$
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
		if (!HelperString.isValid(subject)) {
			throw new RuntimeExceptionIsNullOrEmpty("subject"); //$NON-NLS-1$
		}
//		if (null == body) {
//			throw new RuntimeExceptionIsNull("body"); //$NON-NLS-1$
//		}
		if (!HelperArray.isValid(emailAddresses)) {
			throw new RuntimeExceptionIsNullOrEmpty("emailAddresses"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder();
		for (final String address : emailAddresses) {
			if (0 < sb.length()) {
				sb.append(HelperString.COMMA);
			}
			sb.append(address);
		}
		sb.append("?subject="); //$NON-NLS-1$
		sb.append(getValidText(subject));
		
		if (null != body) {
			sb.append("&body="); //$NON-NLS-1$
			sb.append(getValidText(body).replace(HelperString.NEW_LINE, "%0D%0A")); //$NON-NLS-1$
//			sb.append(EncoderHtml.encode(body.replace(HelperString.NEW_LINE, "%0D%0A"))); //$NON-NLS-1$
		}
		
		final String addresses = sb.toString();

		final String prefix = "mailto:"; //$NON-NLS-1$

		final Matcher matcher = PATTERN.matcher(addresses);
		final String temp = matcher.replaceAll("%20"); //$NON-NLS-1$

		mail(new URI(prefix + temp));

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}
//	/**
//	 * Opens an email with a given addresses with the default mail application.
//	 *
//	 * @param emailAddresses for the mail (e.g. "yourname@yourmail.com")
//	 * @throws IOException
//	 * @throws URISyntaxException
//	 * @since 0.9.3
//	 */
//	public static void mail(final String... emailAddresses) throws IOException, URISyntaxException {
//		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(emailAddresses));
//		if (!HelperArray.isValid(emailAddresses)) {
//			throw new RuntimeExceptionIsNullOrEmpty("emailAddresses"); //$NON-NLS-1$
//		}
//
//		final StringBuilder sb = new StringBuilder();
//		for (final String address : emailAddresses) {
//			if (0 < sb.length()) {
//				sb.append(HelperString.COMMA);
//			}
//			sb.append(address);
//		}
//
//		final String addresses = sb.toString();
//
//		final String prefix = "mailto:"; //$NON-NLS-1$
//
//		final Matcher matcher = PATTERN.matcher(addresses);
//		final String temp = matcher.replaceAll("%20"); //$NON-NLS-1$
//
//		mail(new URI(prefix + temp));
//
//		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
//	}

	/*
	 * Private methods
	 */

	private static String getValidText(final String input) {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart(input));

		final StringBuilder sb = new StringBuilder(input.length());

		for (final char c : input.toCharArray()) {
			final int ci = 0xffff & c;
			if (160 > ci) {
				// 7 Bit
				sb.append(c);
			} else {
				// not 7 Bit - replace with url encoding 
				try {
					final String hex = EncoderHex.encode(String.valueOf(c).getBytes(Constants.ENCODING_UTF8));	
					
					sb.append('%');
					sb.append(hex.substring(0, 2));
					sb.append('%');
					sb.append(hex.substring(2, 4));
				} catch (UnsupportedEncodingException ex) {
					// should never happen!
					log.error("Encoding invalid", ex); //$NON-NLS-1$
				}
			}
		}
		final String result = sb.toString();

		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit(result));
		return result;
	}
}
