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
package ch.sisprocom.bogatyr.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Defines the methods for all models.
 * It also contains all methods from the {@link Observable} class.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.9.0
 */
@XmlRootElement()
public interface Model extends Serializable {
	
	/**
     * Returns the instantiation time of the model.
     * 
     * @return instantiation time of the model
     * @since 0.9.0
     */	
	long getCreateTime();	
	
	//Observable methods
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void notifyObservers();
	void notifyObservers(Object arg);
	void deleteObservers();
	boolean hasChanged();
	int countObservers();
}