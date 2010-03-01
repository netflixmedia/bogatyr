/*******************************************************************************
 * Copyright (c) 2010 by SiSprocom GmbH.
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
 * Grubenstrasse 9 
 * CH-8045 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.model.misc;

import java.util.Locale;


/**
 * Possible countries in ISO3166 format.
 * <strong>Note:</strong> The language is always the main language of a country. This class is also incomplete (you're welcome to help us)...
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100301)
 * @since 0.9.1
 */
public enum Country {
	SWITZERLAND(new Locale("de", "CH")),
	CANADA(Locale.CANADA),
	CHINA(Locale.CHINA),
	FRANCE(Locale.FRANCE),
	GERMANY(Locale.GERMANY),
	ITALY(Locale.ITALY),
	JAPAN(Locale.JAPAN),
	KOREA(Locale.KOREA),
	TAIWAN(Locale.TAIWAN),
	UK(Locale.UK),
	US(Locale.US);
	
	private final Locale locale;
	
	Country(final Locale locale) {
		this.locale = locale;
	}

	
    public String getName() {
		return locale.getDisplayCountry();
	}
    
    public String getName(final Locale locale) {
		return this.locale.getDisplayCountry(locale);
	}

	public String getCountryCode() { //ISO 3166
		return locale.getCountry();
	}

	public String getLanguageCode() { //ISO 639
		return locale.getLanguage();
	}

	public String getLanguage() {
		return locale.getDisplayLanguage();
	}

	public String getLanguage(final Locale locale) {
		return this.locale.getDisplayLanguage(locale);
	}

	public Locale getLocale() {
		return locale;
	}
}	

