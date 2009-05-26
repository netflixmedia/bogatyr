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



/**
 * Converts different units of length.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.70
 */
public abstract class ConverterLength {
	private static final double FACTOR_INCH_TO_CM = 2.54D; //inch to centimeters
	private static final double FACTOR_FOOT_TO_M = 0.3048D; //foot to meters
	private static final double FACTOR_YARD_TO_M = 0.9144D; //yard to meters
	private static final double FACTOR_MILE_TO_M = 1609.344D; //mile (terrestrial) to meters
	private static final double FACTOR_MM_TO_CM = 10.0D; //millimeters to centimeters
	private static final double FACTOR_CM_TO_M = 100.0D; //centimeters to meters
	private static final double FACTOR_M_TO_KM = 1000.0D; //meters to kilometers

	public static final int DEFAULT_DPI = 72;
	
	public enum ConversionLength {
		INCH_TO_CM(FACTOR_INCH_TO_CM),
		CM_TO_INCH(1.0D/FACTOR_INCH_TO_CM),
		FOOT_TO_M(FACTOR_FOOT_TO_M),
		M_TO_FOOT(1.0D/FACTOR_FOOT_TO_M),
		YARD_TO_M(FACTOR_YARD_TO_M),
		M_TO_YARD(1.0D/FACTOR_YARD_TO_M),
		MILE_TO_M(FACTOR_MILE_TO_M),
		M_TO_MILE(1.0D/FACTOR_MILE_TO_M),
		MM_TO_CM(FACTOR_MM_TO_CM),
		CM_TO_MM(1.0D/FACTOR_MM_TO_CM),
		CM_TO_M(FACTOR_CM_TO_M),
		M_TO_CM(1.0D/FACTOR_CM_TO_M),
		M_TO_KM(FACTOR_M_TO_KM),
		KM_TO_M(1.0D/FACTOR_M_TO_KM);
		
		ConversionLength(final double factor) {
			this.factor = factor;
		}
		
		public final double factor;
	}
	
	
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
    	return convert(ConversionLength.INCH_TO_CM, (double) (value / dpi));
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
    	return convert(ConversionLength.CM_TO_INCH, (double) value) * (double) dpi; 
    }
    
    /**
     * Converts a value with the given conversion to a new unit.
     * 
     * @param conversion factor
     * @param value
     * @return value in the new unit
     */
    public static double convert(final ConversionLength conversion, final double value) {
    	return value * conversion.factor; 
    }
 }
