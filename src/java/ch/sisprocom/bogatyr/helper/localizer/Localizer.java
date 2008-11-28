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
package ch.sisprocom.bogatyr.helper.localizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Localizer holder and helper to access the localizer from everywhere.
 *  
 * @author Stefan Laubenberger
 * @version 20081112
 */
public abstract class Localizer { //TODO document in Wiki!
	private static ILocalizer instance;

	
    public static ILocalizer getInstance() {
    	return instance;
	}

    public static void setInstance(final ILocalizer instance) {
    	Localizer.instance = instance;
	}
    
	/**
	 * Returns all locales from the current machine.
	 * 
     * @return Array containing locales from the current machine
	 */
    public static Locale[] getLocales() {
		return Locale.getAvailableLocales();
	}

    /**
	 * Returns all supported countries from the current machine.
	 * 
     * @return List containing supported countries from the current machine
	 */
    public static List<String> getCountries() {
    	List<String> list = new ArrayList<String>();
    	
    	Locale[] locales = getLocales();
		
		for (Locale locale : locales) {
			String iso = locale.getCountry();
			String name = locale.getDisplayCountry();
			
			if (!"".equals(iso) && !"".equals(name)) {  //$NON-NLS-1$//$NON-NLS-2$
				list.add(iso +  '-' + name);
			}
		}
		return list;
	}
}
