/*******************************************************************************
 * Copyright (c) 2007 by Stefan Laubenberger and Silvan Spross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the General Public License v2.0
 * which accompanies this distribution, and is available at
 * http://code.google.com/p/bogatyr/
 *******************************************************************************/
package ch.orwell.bogatyr.util;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Localizer for different languages
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070707
 */
public final class Localizer {
	private String className;
	private long createTime = System.currentTimeMillis();

	// Resources
	public final static String RES_INSTANCIATED = "global.instanciated"; //$NON-NLS-1$
	
	private Locale locale;
	private String localizerBase;
	private ResourceBundle bundle;
	
	/**
	 * Constructs a Localizer.
	 * 
	 * @param localizerBase 
	 * @param locale 
	 */
	public Localizer(String localizerBase, Locale locale) {
		this.locale = locale;
		this.localizerBase = localizerBase;
		this.bundle = ResourceBundle.getBundle(localizerBase, locale);
		
		init();
	}

	public Locale getLocale() {
		return this.locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		this.bundle = ResourceBundle.getBundle(this.localizerBase, locale);
	}

	public String getLocalizerBase() {
		return this.localizerBase;
	}

	public void setLocalizerBase(String localizerBase) {
		this.localizerBase = localizerBase;
		this.bundle = ResourceBundle.getBundle(localizerBase, this.locale);
	}

	public String getValue(String key) {
		return this.bundle.getString(key);
	}
	
	public int getMnemonic(String key) {
		try {
			String mnemonic = this.bundle.getString(key + ".mnemonic"); //$NON-NLS-1$
			return mnemonic.charAt(0);
		} catch (Exception ex) {
			return 0;
		}
	}

	public String getTooltip(String key) {
		try {
			return this.bundle.getString(key + ".tooltip"); //$NON-NLS-1$
		} catch (Exception ex) {
			return null;
		}
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		this.className = this.getClass().getName();
		Logger.getInstance().writeDebug(this.className + "::init", "+++ instanciated +++" + toString()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	
	/*
	 * Overriden methods
	 */
	@Override
	public String toString() {
		return "\nclassName: " + this.className + //$NON-NLS-1$
			"\ncreateTime: " + this.createTime + //$NON-NLS-1$
			"\nlocale: " + this.locale + //$NON-NLS-1$
			"\nlocalizerBase: " + this.localizerBase + //$NON-NLS-1$
			"\nbundle: " + this.bundle; //$NON-NLS-1$
	}
}
