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

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JSlider;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.misc.Constants;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.model.misc.Platform;


/**
 * This is a helper class for Swing.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.1 (20100405)
 * @since 0.9.0
 */
public abstract class HelperSwing {
	private static final Logger log = LoggerFactory.getLogger(HelperSwing.class);
	
    /**
     * Sets the menu on MacOSX to the standard style.
     * This method should be used before creating a new frame and after setting the L&F.
     *
     * @since 0.9.0
     */
	public static void setMacOSXMenu() {
		log.debug(HelperLog.methodStart());
		if (Platform.MAC_OSX == HelperEnvironment.getPlatform()) {
			//display the menu in MacOS X style
			try {
				System.setProperty("apple.laf.useScreenMenuBar", "true"); //$NON-NLS-1$ //$NON-NLS-2$

				final LookAndFeel laf = UIManager.getLookAndFeel();
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				
				final Map<Object, Object> map = new HashMap<Object, Object>(6);
				
				map.put("MenuBarUI", UIManager.get("MenuBarUI")); //$NON-NLS-1$ //$NON-NLS-2$
				map.put("MenuUI", UIManager.get("MenuUI"));  //$NON-NLS-1$//$NON-NLS-2$
				map.put("MenuItemUI", UIManager.get("MenuItemUI")); //$NON-NLS-1$ //$NON-NLS-2$
				map.put("CheckBoxMenuItemUI", UIManager.get("CheckBoxMenuItemUI"));  //$NON-NLS-1$//$NON-NLS-2$
				map.put("RadioButtonMenuItemUI", UIManager.get("RadioButtonMenuItemUI")); //$NON-NLS-1$ //$NON-NLS-2$
				map.put("PopupMenuUI", UIManager.get("PopupMenuUI")); //$NON-NLS-1$ //$NON-NLS-2$
				
				UIManager.setLookAndFeel(laf);
				
		        for (final Map.Entry<?, ?> pair : map.entrySet()) {
					UIManager.put(pair.getKey(), pair.getValue());
		        }
		    } catch(Exception ex) {
		    	log.error("Could not set MacOS X menu bar", ex); //$NON-NLS-1$
		    }
		}
		log.debug(HelperLog.methodExit());
	}
	
//	/**
//     * Calculates the minimum value for a {@link JSlider} with a given tick.
//     * 
//     * @param minValue minimum value
//     * @param tick of the {@link JSlider}
//     * @return calculated minimum value
//     * @see JSlider
//     * @since 0.9.0
//     */	
//    public static int calculateSliderMin(final Number minValue, final Number tick) {
//    	log.debug(HelperLog.methodStart(minValue, tick));
//    	if (null == minValue) {
//            throw new RuntimeExceptionIsNull("minValue"); //$NON-NLS-1$
//        }
//        if (null == tick) {
//            throw new RuntimeExceptionIsNull("tick"); //$NON-NLS-1$
//        }
//
//		final BigDecimal internalValue = new BigDecimal(minValue.toString());
//		final BigDecimal internalTick = new BigDecimal(tick.toString());
//
//		final int result = internalValue.divide(internalTick, Constants.DEFAULT_MATHCONTEXT).intValue();
//		
//		log.debug(HelperLog.methodExit(result));
//		return result;
//    }
//
//	/**
//     * Calculates the maximum value for a {@link JSlider} with a given tick.
//     * 
//     * @param maxValue maximum value
//     * @param tick of the {@link JSlider}
//     * @return calculated maximum value
//     * @see JSlider
//     * @since 0.9.0
//     */	
//    public static int calculateSliderMax(final Number maxValue, final Number tick) {
//    	log.debug(HelperLog.methodStart(maxValue, tick));
//    	if (null == maxValue) {
//            throw new RuntimeExceptionIsNull("maxValue"); //$NON-NLS-1$
//        }
//        if (null == tick) {
//            throw new RuntimeExceptionIsNull("tick"); //$NON-NLS-1$
//        }
//
//		final BigDecimal internalValue = new BigDecimal(maxValue.toString());
//		final BigDecimal internalTick = new BigDecimal(tick.toString());
//
//		final int result = internalValue.divide(internalTick, Constants.DEFAULT_MATHCONTEXT).intValue();
//		
//		log.debug(HelperLog.methodExit(result));
//		return result;
//    }

	/**
     * Calculates the value for a {@link JSlider} with a given tick.
     * 
     * @param value for the {@link JSlider}
     * @param tick of the {@link JSlider}
     * @return calculated value
     * @see JSlider
     * @since 0.9.0
     */	
    public static int calculateValueForSlider(final Number value, final Number tick) {
    	log.debug(HelperLog.methodStart(value, tick));
    	if (null == value) {
            throw new RuntimeExceptionIsNull("value"); //$NON-NLS-1$
        }
        if (null == tick) {
            throw new RuntimeExceptionIsNull("tick"); //$NON-NLS-1$
        }

		final BigDecimal internalValue = new BigDecimal(value.toString());
		final BigDecimal internalTick = new BigDecimal(tick.toString());
		
		final int result = internalValue.divide(internalTick, Constants.DEFAULT_MATHCONTEXT).intValue();
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }

	/**
     * Calculates the current value of a {@link JSlider} with a given tick.
     * 
     * @param slider with the current value
     * @param tick of the {@link JSlider}
     * @return calculated current value
     * @see JSlider
     * @since 0.9.0
     */	
    public static BigDecimal calculateValueFromSlider(final JSlider slider, final Number tick) {
    	log.debug(HelperLog.methodStart(slider, tick));
    	if (null == slider) {
            throw new RuntimeExceptionIsNull("slider"); //$NON-NLS-1$
        }
        if (null == tick) {
            throw new RuntimeExceptionIsNull("tick"); //$NON-NLS-1$
        }

        final BigDecimal internalTick = new BigDecimal(tick.toString());
        
		final BigDecimal result = new BigDecimal(slider.getValue()).multiply(internalTick, Constants.DEFAULT_MATHCONTEXT);
		
		log.debug(HelperLog.methodExit(result));
		return result;
    }
    
    /**
     * Returns a {@link Collection} containing all available system {@link LookAndFeelInfo}.
     *
     * @return {@link Collection} containing all look and feels
     * @see LookAndFeelInfo
     * @since 0.9.1
     */
    public static Collection<LookAndFeelInfo> getAvailableLookAndFeels() {
		log.debug(HelperLog.methodStart());
		
		final Collection<LookAndFeelInfo> result = Arrays.asList(UIManager.getInstalledLookAndFeels());
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
}