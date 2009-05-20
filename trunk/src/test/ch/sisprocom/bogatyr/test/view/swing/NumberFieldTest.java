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
package ch.sisprocom.bogatyr.test.view.swing;

import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.view.swing.NumberField;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20090520
 */
public class NumberFieldTest {
	@Test
	public void testNumberField() {
		final NumberField field = new NumberField();
		
		field.setText("abc"); //$NON-NLS-1$
		assertEquals(HelperString.EMPTY_STRING, field.getText()); 

		field.setText(null);
		assertEquals(HelperString.EMPTY_STRING, field.getText());
		
		field.setValue(null);
		assertEquals(HelperString.EMPTY_STRING, field.getText());
		assertNull(field.getDoubleValue());
		assertNull(field.getIntegerValue());
		assertNull(field.getFloatValue());
		assertNull(field.getByteValue());
		assertNull(field.getLongValue());
		assertNull(field.getShortValue());
		assertNull(field.getBigIntegerValue());
		assertNull(field.getBigDecimalValue());

		field.setText("10.0%"); //$NON-NLS-1$
		assertEquals("10.0%", field.getText()); //$NON-NLS-1$
		
		assertEquals(new Double(10.0D), field.getDoubleValue());
		assertEquals(Integer.valueOf(10), field.getIntegerValue());
		assertEquals(new Float(10.0F), field.getFloatValue());
		assertEquals(Byte.valueOf((byte) 10), field.getByteValue());
		assertEquals(Long.valueOf(10L), field.getLongValue());
		assertEquals(Short.valueOf((short) 10), field.getShortValue());
		assertEquals(new BigInteger("10"), field.getBigIntegerValue()); //$NON-NLS-1$
		assertEquals(new BigDecimal("10.0"), field.getBigDecimalValue()); //$NON-NLS-1$

		field.setText("10000000000000000000000000000.0%"); //$NON-NLS-1$
		assertEquals("10000000000000000000000000000.0%", field.getText()); //$NON-NLS-1$
		assertEquals(Double.MAX_VALUE, field.getDoubleValue());
		assertEquals(268435456, field.getIntegerValue());
		assertEquals(Float.MAX_VALUE, field.getFloatValue());
		assertEquals((byte)0, field.getByteValue());
		assertEquals(4477988020393345024L, field.getLongValue());
		assertEquals((short)0, field.getShortValue());
		assertEquals(new BigInteger("10000000000000000000000000000"), field.getBigIntegerValue()); //$NON-NLS-1$
		assertEquals(new BigDecimal("10000000000000000000000000000.0"), field.getBigDecimalValue()); //$NON-NLS-1$
	}
}


