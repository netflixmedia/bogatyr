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
package ch.customcode.bogatyr.helper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.ColorModel;



/**
 * This is a helper class for screens.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100228)
 * @since 0.9.1
 */
public abstract class HelperScreen {

	public static Dimension getCurrentScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}

	public static ColorModel getCurrentColorModel() {
		return Toolkit.getDefaultToolkit().getColorModel();
	}

//	public static ColorModel getCurrentNumberOfColors() {
//		return Toolkit.getDefaultToolkit().getColorModel().getColorSpace();
//	}

	public static int getCurrentScreenResolution() {
		return Toolkit.getDefaultToolkit().getScreenResolution();
	}

	public static boolean isValidScreenSize(final Dimension minSize) {
		final Dimension resolution = getCurrentScreenSize();
		
		return resolution.width >= minSize.width && resolution.height >= minSize.height;
	}
}