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

package net.laubenberger.bogatyr.helper;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;


/**
 * This is a helper class for XML operations.
 *
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100416)
 * @since 0.3.0
 */
public abstract class HelperXml {
	private static final Logger log = LoggerFactory.getLogger(HelperXml.class);

	/**
	 * This method ensures that the output String has only valid XML unicode characters as specified by the XML 1.0 standard.
	 * For reference, please see <a href="http://www.w3.org/TR/2000/REC-xml-20001006#NT-Char">the standard</a>.
	 * This method will return an empty {@link String} if the input is null or empty.
	 *
	 * @param input {@link String} to remove non-valid characters
	 * @return stripped {@link String}
	 * @since 0.3.0
	 */
	public static String getValidXmlString(final String input) { //$JUnit$
		log.debug(HelperLog.methodStart(input));
		if (null == input) {
			throw new RuntimeExceptionIsNull("input"); //$NON-NLS-1$
		}

		final StringBuilder sb = new StringBuilder(input.length());

		for (final char current : input.toCharArray()) {
//            if (current != 0x96 && current != 0x9C) { // new
			if ((char) 0x26 != current) { // new
				if ((char) 0x9 == current || (char) 0xA == current || (char) 0xD == current || (char) 0x20 <= current && (char) 0xD7FF >= current || (char) 0xE000 <= current && (char) 0xFFFD >= current || (char) 0x10000 <= current && (char) 0x10FFFF >= current) {
					sb.append(current);
				}
			}
		}
		final String result = sb.toString();

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Serialize data to an XML and store it to a file {@link File}.
	 *
	 * @param file to store the serialized data
	 * @param data (object) to serialize as XML
	 * @see File
	 * @since 0.9.0
	 */
	public static <T> void serialize(final File file, final T data) throws JAXBException {
		log.debug(HelperLog.methodStart(file, data));
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}

		getMarshaller(data.getClass()).marshal(data, file);

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Serialize data to an XML and store it to an output stream {@link OutputStream}.
	 *
	 * @param os	{@link OutputStream} to store the serialized data
	 * @param data (object) to serialize as XML
	 * @see OutputStream
	 * @since 0.9.0
	 */
	public static <T> void serialize(final OutputStream os, final T data) throws JAXBException {
		log.debug(HelperLog.methodStart(os, data));
		if (null == os) {
			throw new RuntimeExceptionIsNull("os"); //$NON-NLS-1$
		}
		if (null == data) {
			throw new RuntimeExceptionIsNull("data"); //$NON-NLS-1$
		}

		getMarshaller(data.getClass()).marshal(data, os);

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Deserialize data from a XML file {@link File}.
	 *
	 * @param file  containing the serialized data
	 * @param clazz for the serialized data
	 * @return data as object
	 * @see File
	 * @since 0.9.0
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(final File file, final Class<T> clazz) throws JAXBException {
		log.debug(HelperLog.methodStart(file, clazz));
		if (null == file) {
			throw new RuntimeExceptionIsNull("file"); //$NON-NLS-1$
		}
		if (null == clazz) {
			throw new RuntimeExceptionIsNull("clazz"); //$NON-NLS-1$
		}

		final T result = (T) getUnmarshaller(clazz).unmarshal(file);

		log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Deserialize data from an input stream {@link InputStream}.
	 *
	 * @param is	 {@link InputStream} containing the serialized data
	 * @param clazz for the serialized data
	 * @return data as object
	 * @see InputStream
	 * @since 0.9.0
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(final InputStream is, final Class<T> clazz) throws JAXBException {
		log.debug(HelperLog.methodStart(is, clazz));
		if (null == is) {
			throw new RuntimeExceptionIsNull("is"); //$NON-NLS-1$
		}
		if (null == clazz) {
			throw new RuntimeExceptionIsNull("clazz"); //$NON-NLS-1$
		}

		final T result = (T) getUnmarshaller(clazz).unmarshal(is);

		log.debug(HelperLog.methodExit(result));
		return result;
	}


	/*
	 * Private methods
	 */

	private static <T> Marshaller getMarshaller(final Class<T> clazz) throws JAXBException {
		log.trace(HelperLog.methodStart(clazz));
		final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		final Marshaller result = jaxbContext.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		result.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		log.trace(HelperLog.methodExit(result));
		return result;
	}

	private static <T> Unmarshaller getUnmarshaller(final Class<T> clazz) throws JAXBException {
		log.trace(HelperLog.methodStart(clazz));

		final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		final Unmarshaller result = jaxbContext.createUnmarshaller();

		log.trace(HelperLog.methodExit(result));
		return result;
	}
}