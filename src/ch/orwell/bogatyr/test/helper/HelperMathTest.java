/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 * 
 *******************************************************************************/
package ch.orwell.bogatyr.test.helper;

import junit.framework.TestCase;
import ch.orwell.bogatyr.helper.HelperMath;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20080802 
 */
public class HelperMathTest extends TestCase {

	public void testConvertDoubleToInt() {
		assertEquals(-2, HelperMath.convertDoubleToInt(-2.499D));
		assertEquals(-2, HelperMath.convertDoubleToInt(-2.5D));
		assertEquals(-3, HelperMath.convertDoubleToInt(-2.51D));
		assertEquals(2, HelperMath.convertDoubleToInt(2.499D));
		assertEquals(3, HelperMath.convertDoubleToInt(2.5D));
	}
	
	public void testLog() {
		assertEquals(2.0D, HelperMath.log(10.0, 100.0));
		assertEquals(4.19180654857877D, HelperMath.log(3.0, 100.0));
	}
	
	public void testRound() {
		assertEquals(0.0D, HelperMath.round(0.0D, 4));
		assertEquals(-2.0D, HelperMath.round(-2.499D, 0));
		assertEquals(-2.0D, HelperMath.round(-2.5D, 0));
		assertEquals(-3.0D, HelperMath.round(-2.51D, 0));
		assertEquals(-2.55D, HelperMath.round(-2.554D, 2));
		assertEquals(-2.55D, HelperMath.round(-2.546D, 2));
		assertEquals(2.0D, HelperMath.round(2.499D, 0));
		assertEquals(3.0D, HelperMath.round(2.5D, 0));
		assertEquals(2.55D, HelperMath.round(2.554D, 2));
		assertEquals(2.55D, HelperMath.round(2.545D, 2));
	}
	
	public void testErf() {
		assertEquals(0.0D, HelperMath.erf(0.0D));
		assertEquals(-0.8427007877600067D, HelperMath.erf(-1.0D));
		assertEquals(-0.995322265010666D, HelperMath.erf(-2.0D));
		assertEquals(0.8427007877600067D, HelperMath.erf(1.0D));
		assertEquals(0.995322265010666D, HelperMath.erf(2.0D));
	}
	
	public void testErf2() {
		assertEquals(0.0D, HelperMath.erf2(0.0D));
		assertEquals(-0.842716825727904D, HelperMath.erf2(-1.0D));
		assertEquals(-0.9953087428644348D, HelperMath.erf2(-2.0D));
		assertEquals(0.842716825727904D, HelperMath.erf2(1.0D));
		assertEquals(0.9953087428644348D, HelperMath.erf2(2.0D));
	}

	public void testPhi() {
		assertEquals(0.5D, HelperMath.phi(0.0D));
		assertEquals(0.15865526139567465D, HelperMath.phi(-1.0D));
		assertEquals(0.022750129890124482D, HelperMath.phi(-2.0D));
		assertEquals(0.8413447386043253D, HelperMath.phi(1.0D));
		assertEquals(0.9772498701098755D, HelperMath.phi(2.0D));
	}
	
	public void testRandom() {
		final int range = 2000000000;
		int number;
		
		for (int ii = 0; ii < 100; ii++) {
			number = HelperMath.random(range);

			if (number >= 0 && number <= range) {
				assertTrue(true);
			} else {
				assertTrue(false);
			}
		}
	}
}
