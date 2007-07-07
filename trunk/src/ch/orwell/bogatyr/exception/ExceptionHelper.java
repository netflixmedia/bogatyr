/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.exception;

import ch.orwell.bogatyr.Context;


/**
 * This is a helper class for exceptions
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public abstract class ExceptionHelper {
	// Resources
	private final static String	RES_UNKNOWN_ERROR 	  = "ExceptionHelper.unknown"; //$NON-NLS-1$
	private final static String	RES_UNKNOWN_EVENT 	  = "ExceptionHelper.event"; //$NON-NLS-1$
	private final static String	RES_THREAD_INTERUPTED = "ExceptionHelper.thread"; //$NON-NLS-1$
	private final static String	RES_COMMUNICATION 	  = "ExceptionHelper.communication"; //$NON-NLS-1$
	private final static String	RES_PERSISTENTER  	  = "ExceptionHelper.persistenter"; //$NON-NLS-1$
	private final static String	RES_PERSISTENCE       = "ExceptionHelper.persistence"; //$NON-NLS-1$
	private final static String	RES_ASYMMCRYPTO		  = "ExceptionHelper.asymmcrypto"; //$NON-NLS-1$
	private final static String	RES_SYMMCRYPTO		  = "ExceptionHelper.symmcrypto"; //$NON-NLS-1$

	public static final String EX_UNKNOWN_ERROR     = Context.getInstance().getLocalizer().getValue(RES_UNKNOWN_ERROR);
	public static final String EX_UNKNOWN_EVENT     = Context.getInstance().getLocalizer().getValue(RES_UNKNOWN_EVENT);
	public static final String EX_THREAD_INTERUPTED = Context.getInstance().getLocalizer().getValue(RES_THREAD_INTERUPTED);
	public static final String EX_COMMUNICATION     = Context.getInstance().getLocalizer().getValue(RES_COMMUNICATION);
	public static final String EX_PERSISTENTER      = Context.getInstance().getLocalizer().getValue(RES_PERSISTENTER);
	public static final String EX_PERSISTENCE       = Context.getInstance().getLocalizer().getValue(RES_PERSISTENCE);
	public static final String EX_ASYMMCRYPTO       = Context.getInstance().getLocalizer().getValue(RES_ASYMMCRYPTO);
	public static final String EX_SYMMCRYPTO        = Context.getInstance().getLocalizer().getValue(RES_SYMMCRYPTO);
	
}