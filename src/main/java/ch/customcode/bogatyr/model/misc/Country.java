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

import ch.customcode.bogatyr.helper.HelperObject;


/**
 * Possible countries.
 * <strong>Note:</strong> The language is always the main language of a country.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100408)
 * @since 0.9.1
 */
public enum Country {
	SWITZERLAND(new Locale("de", "CH")), //$NON-NLS-1$//$NON-NLS-2$
	CANADA(Locale.CANADA),
	CHINA(Locale.CHINA),
	FRANCE(Locale.FRANCE),
	GERMANY(Locale.GERMANY),
	ITALY(Locale.ITALY),
	JAPAN(Locale.JAPAN),
	KOREA(Locale.KOREA),
	TAIWAN(Locale.TAIWAN),
	UNITED_KINGDOM(Locale.UK),
	UNITED_STATES(Locale.US),
	PERU(new Locale("es", "PE")), //$NON-NLS-1$//$NON-NLS-2$
	PANAMA(new Locale("es", "PA")), //$NON-NLS-1$//$NON-NLS-2$
	BOSNIA_AND_HERZEGOVINA(new Locale("sr", "BA")), //$NON-NLS-1$//$NON-NLS-2$
	GUATEMALA(new Locale("es", "GT")), //$NON-NLS-1$//$NON-NLS-2$
	UNITED_ARAB_EMIRATES(new Locale("ar", "AE")), //$NON-NLS-1$//$NON-NLS-2$
	NORWAY(new Locale("no", "NO")), //$NON-NLS-1$//$NON-NLS-2$
	ALBANIA(new Locale("sq", "AL")), //$NON-NLS-1$//$NON-NLS-2$
	IRAQ(new Locale("ar", "IQ")), //$NON-NLS-1$//$NON-NLS-2$
	YEMEN(new Locale("ar", "YE")), //$NON-NLS-1$//$NON-NLS-2$
	PORTUGAL(new Locale("pt", "PT")), //$NON-NLS-1$//$NON-NLS-2$
	CYPRUS(new Locale("el", "CY")), //$NON-NLS-1$//$NON-NLS-2$
	QATAR(new Locale("ar", "QA")), //$NON-NLS-1$//$NON-NLS-2$
	MACEDONIA(new Locale("mk", "MK")), //$NON-NLS-1$//$NON-NLS-2$
	FINLAND(new Locale("fi", "FI")), //$NON-NLS-1$//$NON-NLS-2$
	SLOVENIA(new Locale("sl", "SI")), //$NON-NLS-1$//$NON-NLS-2$
	SLOVAKIA(new Locale("sk", "SK")), //$NON-NLS-1$//$NON-NLS-2$
	TURKEY(new Locale("tr", "TR")), //$NON-NLS-1$//$NON-NLS-2$
	SAUDI_ARABIA(new Locale("ar", "SA")), //$NON-NLS-1$//$NON-NLS-2$
	SERBIA_AND_MONTENEGRO(new Locale("sr", "CS")), //$NON-NLS-1$//$NON-NLS-2$
	NEW_ZEALAND(new Locale("en", "NZ")), //$NON-NLS-1$//$NON-NLS-2$
	LITHUANIA(new Locale("lt", "LT")), //$NON-NLS-1$//$NON-NLS-2$
	NICARAGUA(new Locale("es", "NI")), //$NON-NLS-1$//$NON-NLS-2$
	SPAIN(new Locale("es", "ES")), //$NON-NLS-1$//$NON-NLS-2$
	LEBANON(new Locale("ar", "LB")), //$NON-NLS-1$//$NON-NLS-2$
	ESTONIA(new Locale("et", "EE")), //$NON-NLS-1$//$NON-NLS-2$
	KUWAIT(new Locale("ar", "KW")), //$NON-NLS-1$//$NON-NLS-2$
	SERBIA(new Locale("sr", "RS")), //$NON-NLS-1$//$NON-NLS-2$
	MEXICO(new Locale("es", "MX")), //$NON-NLS-1$//$NON-NLS-2$
	SUDAN(new Locale("ar", "SD")), //$NON-NLS-1$//$NON-NLS-2$
	INDONESIA(new Locale("in", "ID")), //$NON-NLS-1$//$NON-NLS-2$
	URUGUAY(new Locale("es", "UY")), //$NON-NLS-1$//$NON-NLS-2$
	LATVIA(new Locale("lv", "LV")), //$NON-NLS-1$//$NON-NLS-2$
	BRAZIL(new Locale("pt", "BR")), //$NON-NLS-1$//$NON-NLS-2$
	SYRIA(new Locale("ar", "SY")), //$NON-NLS-1$//$NON-NLS-2$
	DOMINICAN_REPUBLIC(new Locale("es", "DO")), //$NON-NLS-1$//$NON-NLS-2$
	INDIA(new Locale("hi", "IN")), //$NON-NLS-1$//$NON-NLS-2$
	VENEZUELA(new Locale("es", "VE")), //$NON-NLS-1$//$NON-NLS-2$
	BAHRAIN(new Locale("ar", "BH")), //$NON-NLS-1$//$NON-NLS-2$
	PHILIPPINES(new Locale("en", "PH")), //$NON-NLS-1$//$NON-NLS-2$
	TUNISIA(new Locale("ar", "TN")), //$NON-NLS-1$//$NON-NLS-2$
	AUSTRIA(new Locale("de", "AT")), //$NON-NLS-1$//$NON-NLS-2$
	NETHERLANDS(new Locale("nl", "NL")), //$NON-NLS-1$//$NON-NLS-2$
	ECUADOR(new Locale("es", "EC")), //$NON-NLS-1$//$NON-NLS-2$
	JORDAN(new Locale("ar", "JO")), //$NON-NLS-1$//$NON-NLS-2$
	ICELAND(new Locale("is", "IS")), //$NON-NLS-1$//$NON-NLS-2$
	COLOMBIA(new Locale("es", "CO")), //$NON-NLS-1$//$NON-NLS-2$
	COSTA_RICA(new Locale("es", "CR")), //$NON-NLS-1$//$NON-NLS-2$
	CHILE(new Locale("es", "CL")), //$NON-NLS-1$//$NON-NLS-2$
	EGYPT(new Locale("ar", "EG")), //$NON-NLS-1$//$NON-NLS-2$
	SOUTH_AFRICA(new Locale("en", "ZA")), //$NON-NLS-1$//$NON-NLS-2$
	THAILAND(new Locale("th", "TH")), //$NON-NLS-1$//$NON-NLS-2$
	GREECE(new Locale("el", "GR")), //$NON-NLS-1$//$NON-NLS-2$
	HUNGARY(new Locale("hu", "HU")), //$NON-NLS-1$//$NON-NLS-2$
	IRELAND(new Locale("en", "IE")), //$NON-NLS-1$//$NON-NLS-2$
	UKRAINE(new Locale("uk", "UA")), //$NON-NLS-1$//$NON-NLS-2$
	POLAND(new Locale("pl", "PL")), //$NON-NLS-1$//$NON-NLS-2$
	BELGIUM(new Locale("nl", "BE")), //$NON-NLS-1$//$NON-NLS-2$
	MOROCCO(new Locale("ar", "MA")), //$NON-NLS-1$//$NON-NLS-2$
	BOLIVIA(new Locale("es", "BO")), //$NON-NLS-1$//$NON-NLS-2$
	AUSTRALIA(new Locale("en", "AU")), //$NON-NLS-1$//$NON-NLS-2$
	EL_SALVADOR(new Locale("es", "SV")), //$NON-NLS-1$//$NON-NLS-2$
	RUSSIA(new Locale("ru", "RU")), //$NON-NLS-1$//$NON-NLS-2$
	SOUTH_KOREA(new Locale("ko", "KR")), //$NON-NLS-1$//$NON-NLS-2$
	ALGERIA(new Locale("ar", "DZ")), //$NON-NLS-1$//$NON-NLS-2$
	VIETNAM(new Locale("vi", "VN")), //$NON-NLS-1$//$NON-NLS-2$
	MONTENEGRO(new Locale("sr", "ME")), //$NON-NLS-1$//$NON-NLS-2$
	LIBYA(new Locale("ar", "LY")), //$NON-NLS-1$//$NON-NLS-2$
	BELARUS(new Locale("be", "BY")), //$NON-NLS-1$//$NON-NLS-2$
	HONG_KONG(new Locale("zh", "HK")), //$NON-NLS-1$//$NON-NLS-2$
	ISRAEL(new Locale("iw", "IL")), //$NON-NLS-1$//$NON-NLS-2$
	BULGARIA(new Locale("bg", "BG")), //$NON-NLS-1$//$NON-NLS-2$
	MALTA(new Locale("mt", "MT")), //$NON-NLS-1$//$NON-NLS-2$
	PARAGUAY(new Locale("es", "PY")), //$NON-NLS-1$//$NON-NLS-2$
	CZECH_REPUBLIC(new Locale("cs", "CZ")), //$NON-NLS-1$//$NON-NLS-2$
	ROMANIA(new Locale("ro", "RO")), //$NON-NLS-1$//$NON-NLS-2$
	PUERTO_RICO(new Locale("es", "PR")), //$NON-NLS-1$//$NON-NLS-2$
	LUXEMBOURG(new Locale("de", "LU")), //$NON-NLS-1$//$NON-NLS-2$
	ARGENTINA(new Locale("es", "AR")), //$NON-NLS-1$//$NON-NLS-2$
	MALAYSIA(new Locale("ms", "MY")), //$NON-NLS-1$//$NON-NLS-2$
	CROATIA(new Locale("hr", "HR")), //$NON-NLS-1$//$NON-NLS-2$
	SINGAPORE(new Locale("en", "SG")), //$NON-NLS-1$//$NON-NLS-2$
	OMAN(new Locale("ar", "OM")), //$NON-NLS-1$//$NON-NLS-2$
	SWEDEN(new Locale("sv", "SE")), //$NON-NLS-1$//$NON-NLS-2$
	DENMARK(new Locale("da", "DK")), //$NON-NLS-1$//$NON-NLS-2$
	HONDURAS(new Locale("es", "HN")); //$NON-NLS-1$//$NON-NLS-2$
	
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

	public String getCode() { //ISO 3166
		return locale.getCountry();
	}

	public Language getLanguage() {
		for (final Language language : Language.values()) {
			if (HelperObject.isEquals(language.getCode(), locale.getLanguage())) {
				return language;
			}
		}
		return null;
	}

	public Locale getLocale() {
		return locale;
	}
}	

