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
import java.math.BigInteger;


/**
 * Collected constants of very general utility.
 * 
 * @author Stefan Laubenberger
 * @version 20090517
 */
public interface Const {
	  //characters
	  String NEW_LINE = System.getProperty("line.separator"); //$NON-NLS-1$
	  String FILE_SEPARATOR = System.getProperty("file.separator"); //$NON-NLS-1$
	  String PATH_SEPARATOR = System.getProperty("path.separator"); //$NON-NLS-1$

	  String EMPTY_STRING = ""; //$NON-NLS-1$
	  String SPACE = " "; //$NON-NLS-1$
	  String PERIOD = "."; //$NON-NLS-1$
	  String SEMICOLON = ";"; //$NON-NLS-1$
	  String TAB = "\t"; //$NON-NLS-1$
	  String SINGLE_QUOTE = "'"; //$NON-NLS-1$
	  String DOUBLE_QUOTE = "\""; //$NON-NLS-1$

	  //algebraic signs
	  int POSITIVE = 1;
	  int NEGATIVE = -1;
	  String PLUS_SIGN = "+"; //$NON-NLS-1$
	  String NEGATIVE_SIGN = "-"; //$NON-NLS-1$
	  
	  //constants
	  int DIVIDER_KILOBYTE = 1024;
	  int DIVIDER_MEGABYTE = 1024 * 1024;
	  int DIVIDER_GIGABYTE = 1024 * 1024 * 1024;
	  
	  int HOUR_DAY = 24;
	  int DAYS_WEEK = 7;
	  int DAYS_YEAR = 365;
	  
	  long MILLISECONDS_MIN = 60L * 1000L;
	  long MILLISECONDS_HOUR = 60L * MILLISECONDS_MIN;
	  long MILLISECONDS_DAY = HOUR_DAY * MILLISECONDS_HOUR;
	  long MILLISECONDS_WEEK = DAYS_WEEK * MILLISECONDS_DAY;
	  long MILLISECONDS_YEAR = DAYS_YEAR * MILLISECONDS_DAY;
	  long SECONDS_1900_1970 = 2208988800L; //seconds from 1900 until 1970
	  
	  int VALUE_8 = 8;
	  int VALUE_16 = 16;
	  int VALUE_32 = 32;
	  int VALUE_64 = 64;
	  int VALUE_128 = 128;
	  int VALUE_256 = 256;
	  int VALUE_512 = 512;
	  int VALUE_1024 = 1024;
	  int VALUE_2048 = 2048;
	  int VALUE_4096 = 4096;
	  int VALUE_8192 = 8192;
	  int VALUE_16384 = 16384;
	  int VALUE_32768 = 32768;
	  int VALUE_65536 = 65536;
	  
	  int SPEED_OF_LIGHT = 299792458; //speed of light in m/s
	  double ABSOLUTE_ZERO = -273.15D; //absolute zero in Celsius
	  double GRAVITY_ON_EARTH = 9.806D; //gravity on earth in m/s^2
	  
	  //arrays
	  Class<?>[] EMPTY_ARRAY_CLASS = new Class[0];
	  Object[] EMPTY_ARRAY_OBJECT = new Object[0];
	  String[] EMPTY_ARRAY_STRING = new String[0];
	  Boolean[] EMPTY_ARRAY_BOOLEAN = new Boolean[0];
	  Double[] EMPTY_ARRAY_DOUBLE = new Double[0];
	  Integer[] EMPTY_ARRAY_INTEGER = new Integer[0];
	  Float[] EMPTY_ARRAY_FLOAT = new Float[0];
	  byte[] EMPTY_ARRAY_BYTE = new byte[0];
	  Long[] EMPTY_ARRAY_LONG = new Long[0];
	  Short[] EMPTY_ARRAY_SHORT = new Short[0];
	  BigInteger[] EMPTY_ARRAY_BIG_INTEGER = new BigInteger[0];
	  BigDecimal[] EMPTY_ARRAY_BIG_DECIMAL = new BigDecimal[0];	 
	  
	  //factors
	  //length units
	  double FACTOR_LENGTH_INCH_TO_CM = 2.54D; //inch to centimeters
	  double FACTOR_LENGTH_FOOT_TO_M = 0.3048D; //foot to meters
	  double FACTOR_LENGTH_YARD_TO_M = 0.9144D; //yard to meters
	  double FACTOR_LENGTH_MILE_TO_M = 1609.344D; //mile (terrestrial) to meters
	  double FACTOR_LENGTH_MM_TO_CM = 10.0D; //millimeters to centimeters
	  double FACTOR_LENGTH_CM_TO_M = 100.0D; //centimeters to meters
	  double FACTOR_LENGTH_M_TO_KM = 1000.0D; //meters to kilometers

	  //area units
	  double FACTOR_AREA_MM2_TO_CM2 = 100.0D; //millimeters^2 to centimeters^2
	  double FACTOR_AREA_CM2_TO_M2 = 100.0D; //centimeters^2 to meters^2
	  double FACTOR_AREA_M2_TO_AREA = 100.0D; //meters^2 to area
	  double FACTOR_AREA_AREA_TO_HECTARE = 100.0D; //area to hectare
	  double FACTOR_AREA_HECTARE_TO_KM2 = 100.0D; //hectare to kilometers^2
	  double FACTOR_AREA_FOOT2_TO_M2 = 0.09290304D; //square foot to meters^2
	  double FACTOR_AREA_YARD2_TO_M2 = 0.83612736D; //square yard to meters^2
	  double FACTOR_AREA_PERCH_TO_M2 = 25.2928526D; //square perch to meters^2
	  double FACTOR_AREA_ACRE_TO_M2 = 4046.8564224D; //acre to meters^2
	  double FACTOR_AREA_MILE2_TO_KM2 = 2.5899881103D; //square mile (terrestrial) to kilometers^2
	  
	  //volume units
	  double FACTOR_VOLUME_PINT_TO_CM3 = 473.176473D; //pint to centimeters^3
	  double FACTOR_VOLUME_QUART_TO_L = 0.946326D; //quart to liter
	  double FACTOR_VOLUME_GALLON_TO_L = 3.785411784D; //gallon to liter
	  double FACTOR_VOLUME_BARREL_TO_L = 158.987294928D; //barrel to liter
	  double FACTOR_VOLUME_MM3_TO_CM3 = 1000.0D; //millimeters^3 to centimeters^3
	  double FACTOR_VOLUME_CM3_TO_L = 1000.0D; //centimeters^3 to liter
	  double FACTOR_VOLUME_L_TO_M3 = 1000.0D; //liter to m^3

	  //weight units
	  double FACTOR_WEIGHT_OUNCE_TO_G = 28.34952D; //ounce to gram
	  double FACTOR_WEIGHT_POUND_TO_KG = 0.453592D; //pound to kilogram
	  double FACTOR_WEIGHT_TON_TO_KG = 907.1847D; //ton to kilogram
	  double FACTOR_VOLUME_MG_TO_G = 1000.0D; //milligram to gram
	  double FACTOR_VOLUME_G_TO_KG = 1000.0D; //gram to kilogram
	  
	  double FACTOR_GOLDEN_RATIO_A_TO_B = 1.6180339887D; //golden ratio between a and b
	  
	  double FACTOR_KCAL_TO_KJ = 4.184D; //kilogram calorie to kilojoule
}
