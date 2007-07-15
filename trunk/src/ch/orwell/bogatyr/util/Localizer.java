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
package ch.orwell.bogatyr.util;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Localizer for different languages
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20070715
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
