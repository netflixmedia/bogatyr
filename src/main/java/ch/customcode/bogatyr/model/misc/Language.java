/*
 * Copyright (c) 2007-2010 by Custom Code GmbH.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://www.customcode.ch/bogatyr/>
 *
 * Contact information:
 * Custom Code GmbH
 * Grubenstrasse 9
 * CH-8045 Zuerich
 *
 * <http://www.customcode.ch>
 *
 * <s.laubenberger@customcode.ch>
 * <s.spross@customcode.ch>
 */
package ch.customcode.bogatyr.model.misc;

import java.util.Locale;


/**
 * Possible languages.
 * <strong>Note:</strong> The language is always the main language of a country.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100301)
 * @since 0.9.1
 */
public enum Language {
	BELARUSIAN(new Locale("be")), //$NON-NLS-1$
	FRENCH(new Locale("fr")), //$NON-NLS-1$
	ESTONIAN(new Locale("et")), //$NON-NLS-1$
	ARABIC(new Locale("ar")), //$NON-NLS-1$
	MALAY(new Locale("ms")), //$NON-NLS-1$
	POLISH(new Locale("pl")), //$NON-NLS-1$
	CROATIAN(new Locale("hr")), //$NON-NLS-1$
	PORTUGUESE(new Locale("pt")), //$NON-NLS-1$
	SERBIAN(new Locale("sr")), //$NON-NLS-1$
	MALTESE(new Locale("mt")), //$NON-NLS-1$
	NORWEGIAN(new Locale("no")), //$NON-NLS-1$
	DANISH(new Locale("da")), //$NON-NLS-1$
	UKRAINIAN(new Locale("uk")), //$NON-NLS-1$
	CATALAN(new Locale("ca")), //$NON-NLS-1$
	ALBANIAN(new Locale("sq")), //$NON-NLS-1$
	GERMAN(new Locale("de")), //$NON-NLS-1$
	FINNISH(new Locale("fi")), //$NON-NLS-1$
	CZECH(new Locale("cs")), //$NON-NLS-1$
	KOREAN(new Locale("ko")), //$NON-NLS-1$
	JAPANESE(new Locale("ja")), //$NON-NLS-1$
	LITHUANIAN(new Locale("lt")), //$NON-NLS-1$
	MACEDONIAN(new Locale("mk")), //$NON-NLS-1$
	IRISH(new Locale("ga")), //$NON-NLS-1$
	VIETNAMESE(new Locale("vi")), //$NON-NLS-1$
	INDONESIAN(new Locale("in")), //$NON-NLS-1$
	HUNGARIAN(new Locale("hu")), //$NON-NLS-1$
	TURKISH(new Locale("tr")), //$NON-NLS-1$
	HEBREW(new Locale("iw")), //$NON-NLS-1$
	SLOVENIAN(new Locale("sl")), //$NON-NLS-1$
	CHINESE(new Locale("zh")), //$NON-NLS-1$
	ENGLISH(new Locale("en")), //$NON-NLS-1$
	ICELANDIC(new Locale("is")), //$NON-NLS-1$
	DUTCH(new Locale("nl")), //$NON-NLS-1$
	SWEDISH(new Locale("sv")), //$NON-NLS-1$
	HINDI(new Locale("hi")), //$NON-NLS-1$
	RUSSIAN(new Locale("ru")), //$NON-NLS-1$
	SLOVAK(new Locale("sk")), //$NON-NLS-1$
	THAI(new Locale("th")), //$NON-NLS-1$
	ITALIAN(new Locale("it")), //$NON-NLS-1$
	BULGARIAN(new Locale("bg")), //$NON-NLS-1$
	SPANISH(new Locale("es")), //$NON-NLS-1$
	GREEK(new Locale("el")), //$NON-NLS-1$
	LATVIAN(new Locale("lv")), //$NON-NLS-1$
	ROMANIAN(new Locale("ro")); //$NON-NLS-1$
	
	private final Locale locale;
	
	Language(final Locale locale) {
		this.locale = locale;
	}
	
    public String getName() {
		return locale.getDisplayLanguage();
	}
    
    public String getName(final Locale locale) {
		return this.locale.getDisplayLanguage(locale);
	}

	public String getCode() { //ISO 639
		return locale.getLanguage();
	}

	public Locale getLocale() {
		return locale;
	}
}	