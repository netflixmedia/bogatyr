/*******************************************************************************
 * Copyright (c) 2008-2010 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.test.service.updater;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ch.sisprocom.bogatyr.misc.Constants;
import ch.sisprocom.bogatyr.model.crypto.HashCodeAlgo;
import org.junit.Before;
import org.junit.Test;

import ch.sisprocom.bogatyr.helper.HelperCrypto;
import ch.sisprocom.bogatyr.helper.HelperXml;
import ch.sisprocom.bogatyr.model.misc.Platform;
import ch.sisprocom.bogatyr.model.updater.Document;
import ch.sisprocom.bogatyr.model.updater.DocumentImpl;


/**
 * Junit test
 * 
 * @author Stefan Laubenberger
 * @version 20100215
 */
public class UpdaterTest {
	@Before
	public void setUp() throws Exception {
		final Document doc = new DocumentImpl();
		
		final Map<Platform, String> locations = new HashMap<Platform, String>(3);
//		locations.put(Platform.ANY, "http://code.google.com/p/bogatyr/downloads/list");
		locations.put(Platform.WINDOWS, "www.ms.com");
		locations.put(Platform.MAC_OSX, "www.apple.com");
		locations.put(Platform.UNIX, "www.unix.com");
		doc.setLocations(locations);

		final Map<HashCodeAlgo, String> hashs = new HashMap<HashCodeAlgo, String>(3);
		hashs.put(HashCodeAlgo.MD5, "MD5-Hashvalue");
		hashs.put(HashCodeAlgo.SHA256, "SHA256-Hashvalue");
		doc.setHashs(hashs);
		
		doc.setName(Constants.BOGATYR_NAME);
		doc.setVersion(Constants.BOGATYR_VERSION);
		doc.setBuild(Constants.BOGATYR_BUILD);
		doc.setManufacturer(Constants.BOGATYR_MANUFACTURER);
		doc.setOwner(Constants.BOGATYR_OWNER);
		doc.setPublisher(Constants.BOGATYR_PUBLISHER);
		doc.setCreated(new Date());
		doc.setUUID(HelperCrypto.getUUID());
		
		System.out.println(doc);
		
		try {
			HelperXml.serialize(doc, new File("/Users/Shared/Transfer/test.xml"));
	//		HelperXml.serialize(docs, new File("/Users/Shared/Transfer/test.xml"));
			System.out.println("hi");
			final Document doc2 = HelperXml.deserialize(DocumentImpl.class, new File("/Users/Shared/Transfer/test.xml"));
	
			System.out.println(doc2.getHashs());
			
			System.out.println(doc.equals(doc2));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		try {
			new UpdaterTest().setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetDocuments() {
	}
}
