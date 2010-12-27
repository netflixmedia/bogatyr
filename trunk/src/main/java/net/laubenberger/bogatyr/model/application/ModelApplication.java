/*
 * Copyright (c) 2007-2010 by Stefan Laubenberger.
 *
 * Bogatyr is free software: you can redistribute it and/or modify
 * it under the terms of the General Public License v2.0.
 *
 * Bogatyr is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * See the GNU General Public License for more details:
 * <http://www.gnu.org/licenses>
 *
 * This distribution is available at:
 * <http://code.google.com/p/bogatyr/>
 * <http://dev.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <laubenberger@gmail.com>
 */

package net.laubenberger.bogatyr.model.application;

import java.net.URL;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.laubenberger.bogatyr.model.application.ModelApplicationImpl.XmlAdapter;import net.laubenberger.bogatyr.model.misc.Document;
import net.laubenberger.bogatyr.model.worker.ModelWorker;
import net.laubenberger.bogatyr.service.localizer.Localizer;
import net.laubenberger.bogatyr.service.property.Property;

/**
 * The interface for the application model.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100611)
 * @since 0.9.0
 */
@XmlJavaTypeAdapter(XmlAdapter.class)
public interface ModelApplication extends Document {
	String MEMBER_UPDATE_LOCATION	= "updateLocation"; //$NON-NLS-1$
	String MEMBER_DEBUG				= "debug"; //$NON-NLS-1$
	String MEMBER_PROPERTY			= "property"; //$NON-NLS-1$
	String MEMBER_LOCALIZER 		= "localizer"; //$NON-NLS-1$
	String MEMBER_MODEL_WORKER 	= "modelWorker"; //$NON-NLS-1$

	Boolean isDebug();

	void setDebug(boolean isDebug);

	void setUpdateLocation(URL updateLocation);

	URL getUpdateLocation();
	
	void setProperty(Property property);

	Property getProperty();

	void setLocalizer(Localizer localizer);

	Localizer getLocalizer();

	ModelWorker getModelWorker();
}
