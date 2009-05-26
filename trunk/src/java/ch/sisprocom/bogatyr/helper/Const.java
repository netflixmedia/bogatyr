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



/**
 * Collected constants of very general utility.
 * 
 * @author Stefan Laubenberger
 * @version 0.70 (20090527)
 * @since 0.70
 */
public interface Const {
	  //algebraic signs
	  int POSITIVE = 1;
	  int NEGATIVE = -1;
	  String PLUS_SIGN = "+"; //$NON-NLS-1$
	  String NEGATIVE_SIGN = "-"; //$NON-NLS-1$
	  
	  //constants
	  int DIVIDER_KILOBYTE = 1024;
	  int DIVIDER_MEGABYTE = 1024 * 1024;
	  int DIVIDER_GIGABYTE = 1024 * 1024 * 1024;
	  
	  int SPEED_OF_LIGHT = 299792458; //speed of light in m/s
	  double ABSOLUTE_ZERO = -273.15D; //absolute zero in Celsius
	  double GRAVITY_ON_EARTH = 9.806D; //gravity on earth in m/s^2

	  double FACTOR_GOLDEN_RATIO_A_TO_B = 1.6180339887D; //golden ratio between a and b
	  
	  double FACTOR_KCAL_TO_KJ = 4.184D; //kilogram calorie to kilojoule
}
