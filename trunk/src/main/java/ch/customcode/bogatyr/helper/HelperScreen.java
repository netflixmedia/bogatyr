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
package ch.customcode.bogatyr.helper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.ColorModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * This is a helper class for screens.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.9.1
 */
public abstract class HelperScreen {
	private static final Logger log = LoggerFactory.getLogger(HelperScreen.class);
	
    /**
     * Returns the current screen size in pixels as a {@link Dimension}.
     *
     * @return current screen size as a {@link Dimension}
     * @see Dimension
     * @since 0.9.1
     */
	public static Dimension getCurrentScreenSize() {
		log.debug(HelperLog.methodStart());
		
		final Dimension result = Toolkit.getDefaultToolkit().getScreenSize();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

    /**
     * Returns the current screen color model as a {@link ColorModel}.
     *
     * @return current screen color model as a {@link ColorModel}
     * @see ColorModel
     * @since 0.9.1
     */
	public static ColorModel getCurrentColorModel() {
		log.debug(HelperLog.methodStart());
		
		final ColorModel result = Toolkit.getDefaultToolkit().getColorModel();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

//	public static ColorModel getCurrentNumberOfColors() {
//		return Toolkit.getDefaultToolkit().getColorModel().getColorSpace();
//	}

    /**
     * Returns the current screen resolution in DPI.
     *
     * @return current screen resolution in DPI
     * @since 0.9.1
     */
	public static int getCurrentScreenResolution() {
		log.debug(HelperLog.methodStart());
		
		final int result = Toolkit.getDefaultToolkit().getScreenResolution();
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}

    /**
     * Checks if the given screen size fits to the current screen size.
     *
     * @param minSize given screen size as a {@link Dimension}
     * @return true/false
     * @see Dimension
     * @since 0.9.1
     */
	public static boolean isValidScreenSize(final Dimension minSize) {
		log.debug(HelperLog.methodStart());
		
		final Dimension resolution = getCurrentScreenSize();
		final boolean result = resolution.width >= minSize.width && resolution.height >= minSize.height;
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
}