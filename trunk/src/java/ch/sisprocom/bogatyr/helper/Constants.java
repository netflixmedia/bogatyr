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

import ch.sisprocom.bogatyr.helper.unit.UnitBit;
import ch.sisprocom.bogatyr.model.unit.Bit;


/**
 * Collected constants of very general utility.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091123)
 * @since 0.7.0
 */
public interface Constants {
	//Bogatyr
	String BOGATYR_VERSION = "0.9.0 (20091123)"; //$NON-NLS-1$
	String BOGATYR_VENDOR = "SiSprocom GmbH"; //$NON-NLS-1$
	String BOGATYR_URL = "www.sisprocom.ch/bogatyr"; //$NON-NLS-1$
	String BOGATYR_MAIL = "laubenberger@gmail.com"; //$NON-NLS-1$
	int DEFAULT_FILE_BUFFER_SIZE = (int)UnitBit.convert(Bit.MEBIBYTE, Bit.BYTE, 1);
	MathContext DEFAULT_MATHCONTEXT = MathContext.DECIMAL128;
//	MathContext DEFAULT_MATHCONTEXT = new MathContext(34, RoundingMode.UP);
	
	//algebraic signs
	int POSITIVE = 1;
	int NEGATIVE = -1;
	String PLUS_SIGN = "+"; //$NON-NLS-1$
	String NEGATIVE_SIGN = "-"; //$NON-NLS-1$
	  
	//constants
	BigDecimal SPEED_OF_LIGHT = new BigDecimal("299792458", Constants.DEFAULT_MATHCONTEXT); //speed of light in m/s //$NON-NLS-1$
	BigDecimal ABSOLUTE_ZERO = new BigDecimal("-273.15", Constants.DEFAULT_MATHCONTEXT); //absolute zero in Celsius //$NON-NLS-1$
	BigDecimal GRAVITY_ON_EARTH = new BigDecimal("9.806", Constants.DEFAULT_MATHCONTEXT); //gravity on earth in m/s^2 //$NON-NLS-1$
	
	BigDecimal FACTOR_GOLDEN_RATIO_A_TO_B = new BigDecimal("1.6180339887", Constants.DEFAULT_MATHCONTEXT); //golden ratio between a and b //$NON-NLS-1$
	BigDecimal FACTOR_KCAL_TO_KJ = new BigDecimal("4.184", Constants.DEFAULT_MATHCONTEXT); //kilogram calorie to kilojoule //$NON-NLS-1$
	  
	//encodings
    String ENCODING_UTF8 = "UTF-8"; //$NON-NLS-1$
	String ENCODING_UTF16 = "UTF-16"; //$NON-NLS-1$
	String ENCODING_ISO8859_1 = "ISO-8859-1"; //$NON-NLS-1$
	String ENCODING_ASCII = "US-ASCII"; //$NON-NLS-1$
	String ENCODING_DEFAULT = ENCODING_UTF8;
}
