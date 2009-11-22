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

import ch.sisprocom.bogatyr.helper.HelperMath;
import ch.sisprocom.bogatyr.model.unit.Length;


/**
 * Converts different units of length.
 * 
 * @author Stefan Laubenberger
 * @version 0.8.0 (20090612)
 * @since 0.7.0
 */
public abstract class UnitLength { //TODO add sea miles
	public static final double FACTOR_INCH_TO_CM = 2.54D; //inch to centimeters
	public static final double FACTOR_FOOT_TO_M = 0.3048D; //foot to meters
	public static final double FACTOR_YARD_TO_M = 0.9144D; //yard to meters
	public static final double FACTOR_MILE_TO_M = 1609.344D; //mile (terrestrial) to meters
	public static final double FACTOR_MM_TO_CM = 10.0D; //millimeters to centimeters
	public static final double FACTOR_CM_TO_M = 100.0D; //centimeters to meters
	public static final double FACTOR_M_TO_KM = 1000.0D; //meters to kilometers

	public static final int DEFAULT_DPI = 72;

	
    /**
     * Calculates the size in centimeters of the given pixels and the standard DPI (72).
     * 
     * @param value pixels to convert in centimeters
     * @return size in centimeters of the given pixels
     * @since 0.7.0
     */
    public static double pixelToCm(final int value) { //$JUnit$
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
    public static double pixelToCm(final int value, final int dpi) { //$JUnit$
    	return convert(Length.INCH, Length.CM, value / (double) dpi);
    }
    
    /**
     * Calculates the pixels of the given size (in centimeters) and the standard DPI (72).
     * 
     * @param value size in centimeters to convert in pixels
     * @return pixels
     * @since 0.7.0
     */
    public static int cmToPixel(final double value) { //$JUnit$
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
    public static int cmToPixel(final double value, final int dpi) { //$JUnit$
    	return HelperMath.convertDoubleToInt(convert(Length.CM, Length.INCH, value) * (double) dpi);
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
    public static double convert(final Length fromUnit, final Length toUnit, final double value) { //$JUnit$
		if (null == fromUnit) {
			throw new IllegalArgumentException("fromUnit is null!"); //$NON-NLS-1$
		}
		if (null == toUnit) {
			throw new IllegalArgumentException("toUnit is null!"); //$NON-NLS-1$
		}

    	return value / fromUnit.getFactor() * toUnit.getFactor(); 
    }
 }
