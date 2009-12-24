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

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;

import ch.sisprocom.bogatyr.model.misc.Manufacturer;
import ch.sisprocom.bogatyr.model.misc.ManufacturerImpl;


/**
 * Collected constants of very general utility.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091224)
 * @since 0.7.0
 */
public abstract class Constants {
	//Bogatyr specific
	public static final String BOGATYR_NAME		 		  = "Bogatyr"; //$NON-NLS-1$
	public static final BigDecimal BOGATYR_VERSION 		  = new BigDecimal("0.9"); //$NON-NLS-1$
	public static final int BOGATYR_BUILD 				  = 213;
	public static final Manufacturer BOGATYR_MANUFACTURER = new ManufacturerImpl();

//	public static final MathContext DEFAULT_MATHCONTEXT = MathContext.DECIMAL128;
	public static final MathContext DEFAULT_MATHCONTEXT = new MathContext(34, RoundingMode.DOWN);
	public static final int DEFAULT_FILE_BUFFER_SIZE = 1048576; //1MB
	
	//algebraic signs
	public static final int POSITIVE 	 	 = 1;
	public static final int NEGATIVE 	 	 = -1;
	public static final String PLUS_SIGN 	 = "+"; //$NON-NLS-1$
	public static final String NEGATIVE_SIGN = "-"; //$NON-NLS-1$
	  
	//constants
	public static final BigDecimal SPEED_OF_LIGHT 	= new BigDecimal("299792458"); //speed of light in m/s //$NON-NLS-1$
	public static final BigDecimal ABSOLUTE_ZERO 	= new BigDecimal("-273.15"); //absolute zero in Celsius //$NON-NLS-1$
	public static final BigDecimal GRAVITY_ON_EARTH = new BigDecimal("9.806"); //gravity on earth in m/s^2 //$NON-NLS-1$

	//factors
	public static final BigDecimal FACTOR_GOLDEN_RATIO_A_TO_B = new BigDecimal("1.6180339887"); //golden ratio between a and b //$NON-NLS-1$
	public static final BigDecimal FACTOR_KCAL_TO_KJ 		  = new BigDecimal("4.184"); //kilogram calorie to kilojoule //$NON-NLS-1$
	  
	//encodings
	public static final String ENCODING_UTF8 	  = "UTF-8"; //$NON-NLS-1$
    public static final String ENCODING_UTF16 	  = "UTF-16"; //$NON-NLS-1$
	public static final String ENCODING_ISO8859_1 = "ISO-8859-1"; //$NON-NLS-1$
	public static final String ENCODING_ASCII 	  = "US-ASCII"; //$NON-NLS-1$
	public static final String ENCODING_DEFAULT   = ENCODING_UTF8;

	static {
		BOGATYR_MANUFACTURER.setName("SiSprocom GmbH"); //$NON-NLS-1$
		BOGATYR_MANUFACTURER.setMail("laubenberger@gmail.com"); //$NON-NLS-1$
		try {
			BOGATYR_MANUFACTURER.setURL(new URL("http://www.sisprocom.ch/bogatyr")); //$NON-NLS-1$
		} catch (MalformedURLException ex) {
			// should never happen!
			ex.printStackTrace();
		}
	}
}
