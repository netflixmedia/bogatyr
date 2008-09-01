/*******************************************************************************
 * Copyright (c) 2007-2008 by Stefan Laubenberger and Silvan Spross.
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
package ch.sisprocom.bogatyr.helper.localizer;

import java.util.Locale;
import java.util.ResourceBundle;

import ch.sisprocom.bogatyr.helper.HelperGeneral;
import ch.sisprocom.bogatyr.helper.property.Property;


/**
 * Localizer for file access
 * 
 * @author Stefan Laubenberger
 * @version 20080612
 */
public class LocalizerFile implements ILocalizer {
	private static final String PROPERTY_LOCALIZER_BASE = "LocalizerFile.base"; //$NON-NLS-1$

	private Locale locale;
	private String localizerBase;
	private ResourceBundle bundle;
	

	public LocalizerFile() {
        super();
        init();
	}
    
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(final Locale locale) {
		this.locale = locale;
        bundle = ResourceBundle.getBundle(localizerBase, locale);
	}

	public String getLocalizerBase() {
		return localizerBase;
	}

	public void setLocalizerBase(final String localizerBase) {
		this.localizerBase = localizerBase;
        bundle = ResourceBundle.getBundle(localizerBase, locale);
	}

	public String getValue(final String key) {
		return bundle.getString(key);
	}
	
	public int getMnemonic(final String key) {
		try {
			final String mnemonic = bundle.getString(key + ".mnemonic"); //$NON-NLS-1$
			return (int) mnemonic.charAt(0);
		} catch (Exception ex) {
			return 0;
		}
	}

	public String getTooltip(final String key) {
		try {
			return bundle.getString(key + ".tooltip"); //$NON-NLS-1$
		} catch (Exception ex) {
			return null;
		}
	}
	
	
	/*
	 * Private methods
	 */
	private void init() {
		readProperties();

        locale = Locale.getDefault();
        bundle = ResourceBundle.getBundle(localizerBase, locale);
	}
	
	private void readProperties() {
		final String value = Property.getInstance().getProperty(PROPERTY_LOCALIZER_BASE);

		if (HelperGeneral.isValidString(value)) {
            localizerBase = value;
		} else {
			System.err.println(getClass().getName() + "::readProperties - " + PROPERTY_LOCALIZER_BASE + " == 'null'"); //$NON-NLS-1$ //$NON-NLS-2$
			//TODO enough? Or are more details needed?
		}
	}

	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
