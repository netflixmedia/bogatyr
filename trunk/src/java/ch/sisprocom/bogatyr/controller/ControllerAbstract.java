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
package ch.sisprocom.bogatyr.controller;

import ch.sisprocom.bogatyr.helper.HelperGeneral;

/**
 * This is the skeleton for all controllers.
 * 
 * @author Stefan Laubenberger
 * @version 20090511
 */
public abstract class ControllerAbstract {
	private final long createTime = System.currentTimeMillis();
	
	/**
     * Returns the instantiation time of the controller.
     * 
     * @return instantiation time of the controller
     */	
	public long getCreateTime() {
		return createTime;
	}
	
	
    /*
     * Overridden methods
     */
    @Override
    public String toString() {
        return HelperGeneral.toString(this);
    }
}