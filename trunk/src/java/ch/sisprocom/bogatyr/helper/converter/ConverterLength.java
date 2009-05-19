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
package ch.sisprocom.bogatyr.helper.converter;

import ch.sisprocom.bogatyr.helper.Const;


/**
 * Converts different units of length.
 * 
 * @author Stefan Laubenberger
 * @version 20090519
 * @see Const
 */
public abstract class ConverterLength { //TODO please complete at your own...
	public static final int DEFAULT_DPI = 72;
	
    /**
     * Calculates the size in centimeters of the given pixels and the standard DPI (72).
     * 
     * @param value pixels to convert in centimeters
     * @return size in centimeters of the given pixels
     */
    public static double pixelToCm(final int value) {
    	return pixelToCm(value, DEFAULT_DPI); 
    }
    
    /**
     * Calculates the size in centimeters of the given pixels and DPI.
     * 
     * @param value pixels to convert in centimeters
     * @param dpi dots per inch
     * @return size in centimeters of the given pixels
     */
    public static double pixelToCm(final int value, final int dpi) {
    	return inchToCm(value / dpi); 
    }
    
    /**
     * Calculates the pixels of the given size (in centimeters) and the standard DPI (72).
     * 
     * @param value size in centimeters to convert in pixels
     * @return pixels
     */
    public static double cmToPixel(final int value) {
    	return cmToPixel(value, DEFAULT_DPI); 
    }
    
    /**
     * Calculates the pixels of the given size (in centimeters) and DPI.
     * 
     * @param value size in centimeters to convert in pixels
     * @param dpi dots per inch
     * @return pixels
     */
    public static double cmToPixel(final int value, final int dpi) {
    	return cmToInch(value) * dpi; 
    }
    
    /**
     * Converts inch to centimeters.
     * 
     * @param value in inch
     * @return value in centimeters
     */
    public static double inchToCm(final double value) {
    	return value * Const.FACTOR_LENGTH_INCH_TO_CM; 
    }
    
    /**
     * Converts centimeters to inch.
     * 
     * @param value in centimeters
     * @return value in inch
     */
    public static double cmToInch(final double value) {
    	return value / Const.FACTOR_LENGTH_INCH_TO_CM; 
    }
 }
