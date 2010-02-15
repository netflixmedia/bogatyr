/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.unit;

import java.math.BigDecimal;


/**
 * This is an interface to define all methods of an unit.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100215)
 * @since 0.9.0
 */
public interface Unit<U extends Unit<?>> {
	/**
     * Converts a value to another unit.
     * 
     * @param toUnit resulting unit
     * @param value in the given unit
     * @return {@link BigDecimal} value in the resulting unit
     * @since 0.9.0
     */
    BigDecimal convertTo(U toUnit, Number value);

//    /**
//     * Converts a {@link BigDecimal} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link BigDecimal} value in the resulting unit
//     * @since 0.9.0
//     */
//    BigDecimal convertTo(U toUnit, BigDecimal value);
//	
//	/**
//     * Converts a {@link BigInteger} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link BigInteger} value in the resulting unit
//     * @since 0.9.0
//     */
//    BigInteger convertTo(U toUnit, BigInteger value);
//	
//	/**
//     * Converts a {@link Double} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link Double} value in the resulting unit
//     * @since 0.9.0
//     */
//    Double convertTo(U toUnit, Double value);
//	
//	/**
//     * Converts a {@link Integer} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link Integer} value in the resulting unit
//     * @since 0.9.0
//     */
//    Integer convertTo(U toUnit, Integer value);
//
//	/**
//     * Converts a {@link Float} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link Float} value in the resulting unit
//     * @since 0.9.0
//     */
//    Float convertTo(U toUnit, Float value);
//	
//	/**
//     * Converts a {@link Byte} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link Byte} value in the resulting unit
//     * @since 0.9.0
//     */
//    Byte convertTo(U toUnit, Byte value);
//
//	/**
//     * Converts a {@link Long} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link Long} value in the resulting unit
//     * @since 0.9.0
//     */
//    Long convertTo(U toUnit, Long value);
//
//	/**
//     * Converts a {@link Short} value to another unit.
//     * 
//     * @param toUnit resulting unit
//     * @param value in the given unit
//     * @return {@link Short} value in the resulting unit
//     * @since 0.9.0
//     */
//    Short convertTo(U toUnit, Short value);
}
