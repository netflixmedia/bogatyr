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
package ch.sisprocom.bogatyr.view.swing;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

import ch.sisprocom.bogatyr.helper.HelperGeneral;


/**
 * This is an extended JSpinner.
 * 
 * @author Stefan Laubenberger
 * @version 20090428
 */
public class Spinner extends JSpinner implements IComponentActivate {
	private static final long serialVersionUID = 1637909823592308393L;
	
	private static boolean isActive = true;

	
	public Spinner() {
		super();
	}

	public Spinner(SpinnerModel model) {
		super(model);
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
	

	@Override
	public void setEnabled(boolean isEnabled) {
		if (isActive) {
			super.setEnabled(isEnabled);
		}
	}

	
	/*
	 * Implemented methods
	 */	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		if (isActive) {
			Spinner.isActive = isActive;
			setEnabled(isActive);
		} else {
			setEnabled(isActive);
			Spinner.isActive = isActive;
		}
	}
}