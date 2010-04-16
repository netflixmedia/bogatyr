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
 * <http://www.laubenberger.net/bogatyr/>
 *
 * Contact information:
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 *
 * <http://www.laubenberger.net>
 *
 * <bogatyr@laubenberger.net>
 */

package net.laubenberger.bogatyr.service.updater;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.laubenberger.bogatyr.misc.Constants;
import net.laubenberger.bogatyr.model.crypto.HashCodeAlgo;
import org.junit.Before;
import org.junit.Test;

import net.laubenberger.bogatyr.helper.HelperCrypto;
import net.laubenberger.bogatyr.helper.HelperXml;
import net.laubenberger.bogatyr.model.misc.Platform;
import net.laubenberger.bogatyr.model.updater.ModelUpdater;
import net.laubenberger.bogatyr.model.updater.ModelUpdaterImpl;


/**
 * Junit test
 *
 * @author Stefan Laubenberger
 * @version 20100416
 */
public class UpdaterTest {
	@Before
	public void setUp() throws Exception {
		final ModelUpdater doc = new ModelUpdaterImpl();

		final Map<Platform, String> locations = new HashMap<Platform, String>(3);
//		locations.put(Platform.ANY, "http://code.google.com/p/bogatyr/downloads/list");
		locations.put(Platform.WINDOWS, "www.ms.com"); //$NON-NLS-1$
		locations.put(Platform.MAC_OSX, "www.apple.com"); //$NON-NLS-1$
		locations.put(Platform.UNIX, "www.unix.com"); //$NON-NLS-1$
		doc.setLocations(locations);

		final Map<HashCodeAlgo, String> hashs = new HashMap<HashCodeAlgo, String>(3);
		hashs.put(HashCodeAlgo.MD5, "MD5-Hashvalue"); //$NON-NLS-1$
		hashs.put(HashCodeAlgo.SHA256, "SHA256-Hashvalue"); //$NON-NLS-1$
		doc.setHashs(hashs);

		doc.setName(Constants.BOGATYR.getName());
		doc.setVersion(Constants.BOGATYR.getVersion());
		doc.setBuild(Constants.BOGATYR.getBuild());
		doc.setManufacturer(Constants.BOGATYR.getManufacturer());
		doc.setOwner(Constants.BOGATYR.getOwner());
		doc.setPublisher(Constants.BOGATYR.getPublisher());
		doc.setCreated(new Date());
		doc.setUUID(HelperCrypto.getUUID());

		System.out.println(doc);

		try {
			HelperXml.serialize(new File("/Users/Shared/Transfer/test.xml"), doc); //$NON-NLS-1$
			//		HelperXml.serialize(docs, new File("/Users/Shared/Transfer/test.xml"));
			final ModelUpdater doc2 = HelperXml.deserialize(new File("/Users/Shared/Transfer/test.xml"), ModelUpdaterImpl.class); //$NON-NLS-1$

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
