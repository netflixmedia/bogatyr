/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;



/**
 * This is a helper class for XML operations.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091101)
 * @since 0.3.0
 */
public abstract class HelperXml {
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
        if (!HelperString.isValid(input)) { // vacancy test
            return HelperString.EMPTY_STRING;
        }
       
       	final StringBuilder sb = new StringBuilder(input.length()); // Used to hold the output.

        for (final char current : input.toCharArray()) {
//            if (current != 0x96 && current != 0x9C) { // new
            if ((char) 0x26 != current) { // new
	            if ((char) 0x9 == current || (char) 0xA == current || (char) 0xD == current || (char) 0x20 <= current && (char) 0xD7FF >= current || (char) 0xE000 <= current && (char) 0xFFFD >= current || (char) 0x10000 <= current && (char) 0x10FFFF >= current) {
                    sb.append(current);
                }
            }
        }
        return sb.toString();
    }
    
	/**
     * Serialize data to an XML and store it to a file {@link File}.
     *
     * @param data (object) to serialize as XML
     * @param file to store the serialized data
     * @see File
     * @since 0.9.0
     */
    public static <T> void serialize(final T data, final File file) throws JAXBException {
		getMarshaller(data.getClass()).marshal(data, file);
    }

	/**
     * Serialize data to an XML and store it to an output stream {@link OutputStream}.
     *
     * @param data (object) to serialize as XML
     * @param os {@link OutputStream} to store the serialized data
     * @see OutputStream
     * @since 0.9.0
     */
    public static <T> void serialize(final T data, final OutputStream os) throws JAXBException {
		getMarshaller(data.getClass()).marshal(data, os);
    }

	/**
     * Deserialize data from a XML file {@link File}.
     *
     * @param clazz for the serialized data
     * @param file containing the serialized data
     * @return data as object
     * @see File
     * @since 0.9.0
     */
    @SuppressWarnings("unchecked")
	public static <T> T deserialize(final Class<T> clazz, final File file) throws JAXBException {
		return (T)getUnmarshaller(clazz).unmarshal(file);
    }

	/**
     * Deserialize data from an input stream {@link InputStream}.
     *
     * @param clazz for the serialized data
     * @param is {@link InputStream} containing the serialized data
     * @return data as object
     * @see InputStream
     * @since 0.9.0
     */
    @SuppressWarnings("unchecked")
	public static <T> T deserialize(final Class<T> clazz, final InputStream is) throws JAXBException {
		return (T)getUnmarshaller(clazz).unmarshal(is);
    }
    
    
    /*
     * Private methods
     */
    private static <T> Marshaller getMarshaller(final Class<T> clazz) throws JAXBException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		final Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		return marshaller;
    }
    
    private static <T> Unmarshaller getUnmarshaller(final Class<T> clazz) throws JAXBException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		
		return jaxbContext.createUnmarshaller();
    }
}