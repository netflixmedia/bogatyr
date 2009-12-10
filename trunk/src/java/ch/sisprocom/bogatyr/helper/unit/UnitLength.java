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
package ch.sisprocom.bogatyr.helper.unit;

import java.math.BigDecimal;

import ch.sisprocom.bogatyr.helper.Constants;
import ch.sisprocom.bogatyr.helper.HelperNumber;
import ch.sisprocom.bogatyr.model.unit.Length;


/**
 * Converts different units of length.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091210)
 * @since 0.7.0
 */
public abstract class UnitLength extends UnitConverter {
	public static final BigDecimal FACTOR_INCH_TO_CM 		 = new BigDecimal("2.54"); //inch to centimeters //$NON-NLS-1$
	public static final BigDecimal FACTOR_FOOT_TO_M 		 = new BigDecimal("0.3048"); //foot to meters //$NON-NLS-1$
	public static final BigDecimal FACTOR_YARD_TO_M 		 = new BigDecimal("0.9144"); //yard to meters //$NON-NLS-1$
	public static final BigDecimal FACTOR_MILE_TO_M 		 = new BigDecimal("1609.344"); //mile (terrestrial) to meters //$NON-NLS-1$
	public static final BigDecimal FACTOR_NAUTICAL_MILE_TO_M = new BigDecimal("1852"); //nautical mile to meters //$NON-NLS-1$
	public static final BigDecimal FACTOR_MM_TO_CM 			 = BigDecimal.TEN; //millimeters to centimeters
	public static final BigDecimal FACTOR_CM_TO_M 			 = HelperNumber.BIGDECIMAL_100; //centimeters to meters
	public static final BigDecimal FACTOR_M_TO_KM 			 = HelperNumber.BIGDECIMAL_1000; //meters to kilometers

	public static final BigDecimal DEFAULT_DPI = new BigDecimal("72"); //$NON-NLS-1$

	
    /**
     * Calculates the size in centimeters of the given pixels and the standard DPI (72).
     * 
     * @param value pixels to convert in centimeters
     * @return size in centimeters of the given pixels
     * @since 0.7.0
     */
    public static BigDecimal pixelToCm(final BigDecimal value) { //$JUnit$
    	return pixelToCm(value, DEFAULT_DPI); 
    }
    
    /**
     * Calculates the size in centimeters of the given pixels and DPI.
     * 
     * @param value pixels to convert in centimeters
     * @param dpi dots per inch
     * @return size in centimeters of the given pixels
     * @since 0.7.0
     */
    public static BigDecimal pixelToCm(final BigDecimal value, final BigDecimal dpi) { //$JUnit$
    	return convert(Length.INCH, Length.CM, value).divide(dpi, Constants.DEFAULT_MATHCONTEXT);
    }
    
    /**
     * Calculates the pixels of the given size (in centimeters) and the standard DPI (72).
     * 
     * @param value size in centimeters to convert in pixels
     * @return pixels
     * @since 0.7.0
     */
    public static BigDecimal cmToPixel(final BigDecimal value) { //$JUnit$
    	return cmToPixel(value, DEFAULT_DPI); 
    }
    
    /**
     * Calculates the pixels of the given size (in centimeters) and DPI.
     * 
     * @param value size in centimeters to convert in pixels
     * @param dpi dots per inch
     * @return pixels
     * @since 0.7.0
     */
    public static BigDecimal cmToPixel(final BigDecimal value, final BigDecimal dpi) { //$JUnit$
    	return convert(Length.CM, Length.INCH, value).multiply(dpi, Constants.DEFAULT_MATHCONTEXT);
    }

    /**
     * Calculates the size in inches of the given pixels and the standard DPI (72).
     * 
     * @param value pixels to convert in inches
     * @return size in inches of the given pixels
     * @since 0.9.0
     */
    public static BigDecimal pixelToInch(final BigDecimal value) { //$JUnit$
    	return pixelToInch(value, DEFAULT_DPI); 
    }
    
    /**
     * Calculates the size in inches of the given pixels and DPI.
     * 
     * @param value pixels to convert in inches
     * @param dpi dots per inch
     * @return size in inches of the given pixels
     * @since 0.9.0
     */
    public static BigDecimal pixelToInch(final BigDecimal value, final BigDecimal dpi) { //$JUnit$
    	return value.divide(dpi, Constants.DEFAULT_MATHCONTEXT);
    }
    
    /**
     * Calculates the pixels of the given size (in inches) and the standard DPI (72).
     * 
     * @param value size in inches to convert in pixels
     * @return pixels
     * @since 0.9.0
     */
    public static BigDecimal inchToPixel(final BigDecimal value) {
    	return inchToPixel(value, DEFAULT_DPI); 
    }
    
    /**
     * Calculates the pixels of the given size (in inches) and DPI.
     * 
     * @param value size in inches to convert in pixels
     * @param dpi dots per inch
     * @return pixels
     * @since 0.9.0
     */
    public static BigDecimal inchToPixel(final BigDecimal value, final BigDecimal dpi) {
    	return value.multiply(dpi, Constants.DEFAULT_MATHCONTEXT);
    }
   
    /**
     * Converts a value with a given unit to another unit.
     * 
     * @param fromUnit length unit
     * @param toUnit length unit
     * @param value in the given unit
     * @return value in the new unit
     * @since 0.7.0
     */
    public static BigDecimal convert(final Length fromUnit, final Length toUnit, final BigDecimal value) { //$JUnit$
    	return UnitConverter.convert(fromUnit, toUnit, value); 
    }
 }
