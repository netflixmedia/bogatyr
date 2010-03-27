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
package ch.customcode.bogatyr.model.application;

import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import ch.customcode.bogatyr.model.crypto.HashCodeAlgo;
import ch.customcode.bogatyr.model.misc.Document;
import ch.customcode.bogatyr.model.worker.ModelWorker;
import ch.customcode.bogatyr.service.localizer.Localizer;
import ch.customcode.bogatyr.service.property.Property;

/**
 * The interface for the application model.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100217)
 * @since 0.9.0
 */
@XmlJavaTypeAdapter(ModelApplicationImpl.XmlAdapter.class)
public interface ModelApplication extends Document {
	String MEMBER_HASHS      	   = "hashs"; //$NON-NLS-1$
    String MEMBER_DEBUG     	   = "debug"; //$NON-NLS-1$
    String MEMBER_PROPERTY  	   = "property"; //$NON-NLS-1$
    String MEMBER_LOCALIZER 	   = "localizer"; //$NON-NLS-1$
    String MEMBER_MODEL_WORKER	   = "modelWorker"; //$NON-NLS-1$
    String METHOD_ADD_HASH         = "addHash"; //$NON-NLS-1$
    String METHOD_REMOVE_HASH	   = "removeHash"; //$NON-NLS-1$
	
    /**
     * Returns all hashs.
     *
     * @return {@link Map} containing all hashs
     * @since 0.9.0
     */
    Map<HashCodeAlgo, String> getHashs();

    /**
     * Sets all hashs.
     *
     * @param hashs {@link Map} containing all hashs
     * @since 0.9.0
     */
    void setHashs(Map<HashCodeAlgo, String> hashs);

    /**
     * Returns the hash for a given {@link HashCodeAlgo}.
     * 
     * @param hashCodeAlgo for the hash
     * @return hash 
     * @since 0.9.0
     */	
	String getHash(HashCodeAlgo hashCodeAlgo);

	/**
     * Returns the default hash (generated with SHA256).
     * 
     * @return default hash 
     * @since 0.9.0
     */	
	String getHash();
	
	void addHash(HashCodeAlgo hashCodeAlgo, String hash);

	void removeHash(HashCodeAlgo hashCodeAlgo);

    Boolean isDebug();
    void setDebug(boolean isDebug);

    void setProperty(Property property);
    Property getProperty();

    void setLocalizer(Localizer localizer);
    Localizer getLocalizer(); 
    
    ModelWorker getModelWorker(); 
}
