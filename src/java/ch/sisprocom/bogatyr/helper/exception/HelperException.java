/*******************************************************************************
 * Copyright (c) 2007-2008 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.exception;

import ch.sisprocom.bogatyr.helper.localizer.Localizer;


/**
 * This is a helper class for exceptions.
 * 
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class HelperException {
	// Resources
    private static final String	RES_UNKNOWN_ERROR 	  = "HelperException.unknown"; //$NON-NLS-1$
	private static final String	RES_UNKNOWN_EVENT 	  = "HelperException.event"; //$NON-NLS-1$
	private static final String	RES_THREAD_INTERUPTED = "HelperException.thread"; //$NON-NLS-1$
	private static final String	RES_COMMUNICATION 	  = "HelperException.communication"; //$NON-NLS-1$
	private static final String	RES_PERSISTENTER  	  = "HelperException.persistenter"; //$NON-NLS-1$
	private static final String	RES_PERSISTENCE       = "HelperException.persistence"; //$NON-NLS-1$
	private static final String	RES_ASYMMCRYPTO		  = "HelperException.asymmcrypto"; //$NON-NLS-1$
	private static final String	RES_SYMMCRYPTO		  = "HelperException.symmcrypto"; //$NON-NLS-1$

	public static final String EX_UNKNOWN_ERROR     = Localizer.getInstance().getValue(RES_UNKNOWN_ERROR);
	public static final String EX_UNKNOWN_EVENT     = Localizer.getInstance().getValue(RES_UNKNOWN_EVENT);
	public static final String EX_THREAD_INTERUPTED = Localizer.getInstance().getValue(RES_THREAD_INTERUPTED);
	public static final String EX_COMMUNICATION     = Localizer.getInstance().getValue(RES_COMMUNICATION);
	public static final String EX_PERSISTENTER      = Localizer.getInstance().getValue(RES_PERSISTENTER);
	public static final String EX_PERSISTENCE       = Localizer.getInstance().getValue(RES_PERSISTENCE);
	public static final String EX_ASYMMCRYPTO       = Localizer.getInstance().getValue(RES_ASYMMCRYPTO);
	public static final String EX_SYMMCRYPTO        = Localizer.getInstance().getValue(RES_SYMMCRYPTO);
}
