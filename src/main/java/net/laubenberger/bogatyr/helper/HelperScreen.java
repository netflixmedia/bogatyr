/*
 * Copyright (c) 2007-2011 by Stefan Laubenberger.
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

package net.laubenberger.bogatyr.helper;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.image.ColorModel;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a helper class for screens.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.6 (20110601)
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
	public static Dimension getCurrentScreenSize() { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		Dimension result = null;

		try {
			result = Toolkit.getDefaultToolkit().getScreenSize();
		} catch (HeadlessException ex) {
			if (log.isWarnEnabled()) log.warn("Could not get the screen size", ex); //$NON-NLS-1$
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns the current screen color model as a {@link ColorModel}.
	 * 
	 * @return current screen color model as a {@link ColorModel}
	 * @see ColorModel
	 * @since 0.9.1
	 */
	public static ColorModel getCurrentColorModel() { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		ColorModel result = null;
		
		try {
			result = Toolkit.getDefaultToolkit().getColorModel();
		} catch (HeadlessException ex) {
			if (log.isWarnEnabled()) log.warn("Could not get the color model", ex); //$NON-NLS-1$
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	// public static ColorSpace getCurrentNumberOfColors() {
	// return Toolkit.getDefaultToolkit().getColorModel().getColorSpace();
	// }

	/**
	 * Returns the current screen resolution in DPI.
	 * 
	 * @return current screen resolution in DPI
	 * @since 0.9.1
	 */
	public static int getCurrentScreenResolution() { // $JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		int result = 0;
		
		try {
			result = Toolkit.getDefaultToolkit().getScreenResolution();
		} catch (HeadlessException ex) {
			if (log.isWarnEnabled()) log.warn("Could not get the screen resolution", ex); //$NON-NLS-1$
		}

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Checks if the given screen size fits to the current screen size.
	 * 
	 * @param minSize
	 *           given screen size as a {@link Dimension}
	 * @return true/false
	 * @see Dimension
	 * @since 0.9.1
	 */
	public static boolean isValidScreenSize(final Dimension minSize) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(minSize));
		if (null == minSize) {
			throw new RuntimeExceptionIsNull("minSize"); //$NON-NLS-1$
		}

		final Dimension resolution = getCurrentScreenSize();
		final boolean result = null != getCurrentScreenSize() && resolution.width >= minSize.width && resolution.height >= minSize.height;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}