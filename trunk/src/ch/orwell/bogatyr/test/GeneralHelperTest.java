/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
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
 * 
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 * 
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8022 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import junit.framework.TestCase;
import ch.orwell.bogatyr.util.GeneralHelper;


/**
 * Tests the GeneralHelper
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070709
 */
public class GeneralHelperTest extends TestCase {
	
	private String stringObject;
	private Double doubleObject;
	private double simpleDouble;
	private int simpleInt;
	
	@Override
    protected void setUp() {
    	this.stringObject = new String();
    	this.doubleObject = new Double(0.0);
    	this.simpleDouble = 0.0;
    	this.simpleInt = 0;
    }
	
	public void testGeneralHelperBasics() {
		// Greater than zero
		assertTrue(GeneralHelper.isGreaterThanZero(1));
		assertFalse(GeneralHelper.isGreaterThanZero(0));
		assertFalse(GeneralHelper.isGreaterThanZero(-1));
		
		// Convert double to int
		assertTrue(GeneralHelper.convertDoubleToInt(0.0) == 0);
		assertTrue(GeneralHelper.convertDoubleToInt(1.0) == 1);
		assertTrue(GeneralHelper.convertDoubleToInt(0.6) == 1);
		assertTrue(GeneralHelper.convertDoubleToInt(0.5) == 1);
		assertTrue(GeneralHelper.convertDoubleToInt(0.4) == 0);
		
		// Is valid
		Object[] objects = null;
		assertFalse(GeneralHelper.isValidArray(objects));
		objects = new Object[]{new Object(), new Object()};
		assertTrue(GeneralHelper.isValidArray(objects));
		
		ArrayList<String> arrayList = null;
		assertFalse(GeneralHelper.isValidArrayList(arrayList));
		arrayList = new ArrayList<String>();
		arrayList.add(new String());
		assertTrue(GeneralHelper.isValidArrayList(arrayList));
		
		assertFalse(GeneralHelper.isValidInt(0));
		assertTrue(GeneralHelper.isValidInt(1));
		
		assertFalse(GeneralHelper.isValidLong(0L));
		assertTrue(GeneralHelper.isValidLong(1L));
		
		Object object = null;
		assertFalse(GeneralHelper.isValidObject(object));
		object = new Object();
		assertTrue(GeneralHelper.isValidObject(object));
		
		String string = null;
		assertFalse(GeneralHelper.isValidString(string));
		string = new String();
		assertFalse(GeneralHelper.isValidString(string));
		string = new String("test"); //$NON-NLS-1$
		assertTrue(GeneralHelper.isValidString(string));
	}
    
    @SuppressWarnings({ "boxing", "null" })
	public void testObjectToBytes(){
    	
    	// Simple checks
    	assertTrue(this.stringObject.equals(new String()));
    	assertTrue(this.doubleObject.equals(new Double(0.0)));
    	assertTrue(this.simpleDouble == 0.0);
    	assertTrue(this.simpleInt == 0);
    	
    	// Add some values
    	Random r = new Random();
    	this.stringObject = new String() + r.nextDouble();
    	this.doubleObject = new Double(r.nextDouble());
    	this.simpleDouble = r.nextDouble();
    	this.simpleInt = r.nextInt();
    	
    	// Byte it...
    	byte[] stringObjectInBytes = null;
    	byte[] doubleObjectInBytes = null;
    	byte[] simpleDoubleInBytes = null;
    	byte[] simpleIntInBytes = null;
    	try {
			stringObjectInBytes = GeneralHelper.getBytes(this.stringObject);
			doubleObjectInBytes = GeneralHelper.getBytes(this.doubleObject);
			simpleDoubleInBytes = GeneralHelper.getBytes(new Double(this.simpleDouble));
			simpleIntInBytes = GeneralHelper.getBytes(new Integer(this.simpleInt));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Test byteing
		assertFalse(this.stringObject.equals(stringObjectInBytes));
		assertFalse(this.doubleObject.equals(doubleObjectInBytes));
		assertFalse(simpleDoubleInBytes.equals(this.simpleDouble));
		assertFalse(simpleIntInBytes.equals(this.simpleInt));
		
		String stringObjectFromBytes = null;
    	Double doubleObjectFromBytes = null;
    	double simpleDoubleFromBytes = 0.0;
    	int simpleIntFromBytes = 0;
    	try {
    		stringObjectFromBytes = (String)GeneralHelper.getObject(stringObjectInBytes);
    		doubleObjectFromBytes = (Double)GeneralHelper.getObject(doubleObjectInBytes);
    		simpleDoubleFromBytes = ((Double)GeneralHelper.getObject(simpleDoubleInBytes)).doubleValue();
    		simpleIntFromBytes = ((Integer)GeneralHelper.getObject(simpleIntInBytes)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Back testing
		assertTrue(this.stringObject.equals(stringObjectFromBytes));
		assertTrue(this.doubleObject.equals(doubleObjectFromBytes));
		assertTrue(this.simpleDouble == simpleDoubleFromBytes);
		assertTrue(this.simpleInt == simpleIntFromBytes);
    }
}
