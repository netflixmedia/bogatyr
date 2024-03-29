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

import java.security.Provider;
import java.security.Security;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsEmpty;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This is a helper class for cryptography (e.g. random keys).
 * <strong>Note:</strong> This class needs <a href="http://www.bouncycastle.org/">BouncyCastle</a> to work.
 * 
 * @author Stefan Laubenberger
 * @version 0.9.4 (20101227)
 * @since 0.7.0
 */
public abstract class HelperCrypto {
	public static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider(); //BouncyCastle

	private static final Logger log = LoggerFactory.getLogger(HelperCrypto.class);
	private static final char[] DEFAULT_RANDOMKEY_SEED = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	static {
		Security.addProvider(new BouncyCastleProvider()); //Needed because JavaSE doesn't include providers
	}
	
	/**
	 * Generates an unique {@link String} with a given seed.
	 * This is used for unique keys (e.g. for product keys).
	 *
	 * @param digits length of result string
	 * @param seed	for the string (e.g. "1,2...0,A,B...Z)
	 * @return generated unique string
	 * @since 0.7.0
	 */
	public static String getRandomKey(final int digits, final char... seed) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(digits, seed));
		if (0 >= digits) {
			throw new RuntimeExceptionMustBeGreater("digits", digits, 0); //$NON-NLS-1$
		}
		if (null == seed) {
			throw new RuntimeExceptionIsNull("seed"); //$NON-NLS-1$
		}
		if (!HelperArray.isValid(seed)) {
			throw new RuntimeExceptionIsEmpty("seed"); //$NON-NLS-1$
		}
		
		final StringBuilder sb = new StringBuilder(digits);

		for (int ii = 0; ii < digits; ii++) {
			sb.append(seed[HelperMath.getRandom(seed.length)]);
		}

		final String result = sb.toString();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Generates an unique {@link String} with default seed.
	 * This is used for unique keys (e.g. for product keys).
	 *
	 * @param digits length of result string
	 * @return generated unique String
	 * @since 0.7.0
	 */
	public static String getRandomKey(final int digits) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(digits));

		final String result = getRandomKey(digits, DEFAULT_RANDOMKEY_SEED);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Generates an universally unique identifier ({@link UUID}).
	 *
	 * @return generated {@link UUID}
	 * @see UUID
	 * @since 0.7.0
	 */
	public static UUID getUUID() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final UUID result = UUID.randomUUID();

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link List} of all installed security {@link Provider}.
	 *
	 * @return {@link List} containing all installed security {@link Provider}
	 * @see Provider
	 * @since 0.9.1
	 */
	public static List<Provider> getProviders() { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		final List<Provider> result = Arrays.asList(Security.getProviders());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available ciphers for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available ciphers
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getCiphers(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "Cipher."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available key agreements for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available key agreements
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getKeyAgreements(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "KeyAgreement."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available macs for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available macs
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getMacs(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "Mac."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available message digests for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available message digests
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getMessageDigests(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "MessageDigest."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available signatures for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available signatures
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getSignatures(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "Signature."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available key pair generators for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available key pair generators
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getKeyPairGenerators(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "KeyPairGenerator."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available key factories for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available key factories
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getKeyFactories(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "KeyFactory."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	/**
	 * Returns a {@link Collection} of all available key generators for a given security {@link Provider}.
	 *
	 * @param provider for the data
	 * @return {@link Collection} containing all available key generators
	 * @see Provider
	 * @since 0.9.1
	 */
	public static Collection<String> getKeyGenerators(final Provider provider) { //$JUnit$
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(provider));

		final Collection<String> result = getInformation(provider, "KeyGenerator."); //$NON-NLS-1$

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}


	/*
	 * Private methods
	 */

	private static Collection<String> getInformation(final Provider provider, final String id) {
		if (log.isTraceEnabled()) log.trace(HelperLog.methodStart(provider, id));
		if (null == provider) {
			throw new RuntimeExceptionIsNull("provider"); //$NON-NLS-1$
		}
		
		final Collection<String> result = new HashSet<String>();

		for (final Entry<?, ?> pair : provider.entrySet()) {
			final String entry = (String) pair.getKey();

			if (HelperString.startsWith(entry, id) && !HelperString.isNumeric(entry.substring(id.length(), id.length() + 1))) {
				result.add(entry.substring(id.length()));
			}
		}
		if (log.isTraceEnabled()) log.trace(HelperLog.methodExit(result));
		return result;
	}
}