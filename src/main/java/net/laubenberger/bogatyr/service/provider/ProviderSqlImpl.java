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

package net.laubenberger.bogatyr.service.provider;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.helper.HelperString;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import net.laubenberger.bogatyr.service.ServiceAbstract;


/**
 * This class provides functions to connect and execute statements on SQL-Server.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.2 (20100519)
 * @since 0.2.0
 */
public class ProviderSqlImpl extends ServiceAbstract implements ProviderSql {
	private static final Logger log = LoggerFactory.getLogger(ProviderSqlImpl.class);

	// Server
	private String driver;
	private String url;
	private String user;
	private String password;


	public ProviderSqlImpl(final String driver, final String url, final String user, final String password) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(driver, url, user, password));

		setDriver(driver);
		setUrl(url);
		setUser(user);
		setPassword(password);
	}

	/**
	 * Returns the current db driver class.
	 *
	 * @return db driver class
	 * @since 0.2.0
	 */
	public String getDriver() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(driver));
		return driver;
	}

	/**
	 * Returns the current db URL.
	 *
	 * @return db URL
	 * @since 0.2.0
	 */
	public String getUrl() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(url));
		return url;
	}

	/**
	 * Returns the current db user.
	 *
	 * @return db user
	 * @since 0.2.0
	 */
	public String getUser() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(user));
		return user;
	}

	/**
	 * Returns the current db user password.
	 *
	 * @return db user password
	 * @since 0.2.0
	 */
	public String getPassword() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(password));
		return password;
	}

	/**
	 * Sets the current db driver class
	 *
	 * @param driver db driver class
	 * @since 0.2.0
	 */
	public void setDriver(final String driver) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(driver));
		if (null == driver) {
			throw new RuntimeExceptionIsNull("driver"); //$NON-NLS-1$
		}

		this.driver = driver;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Sets the current db URL.
	 *
	 * @param url db URL
	 * @since 0.2.0
	 */
	public void setUrl(final String url) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(url));
		if (null == url) {
			throw new RuntimeExceptionIsNull("url"); //$NON-NLS-1$
		}
		this.url = url;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Sets the current db user.
	 *
	 * @param user for the db
	 * @since 0.2.0
	 */
	public void setUser(final String user) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(user));
		if (null == user) {
			throw new RuntimeExceptionIsNull("user"); //$NON-NLS-1$
		}

		this.user = user;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	/**
	 * Sets the current db user password.
	 *
	 * @param password for the user/db
	 * @since 0.2.0
	 */
	public void setPassword(final String password) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(password));
		if (null == password) {
			throw new RuntimeExceptionIsNull("password"); //$NON-NLS-1$
		}

		this.password = password;

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}


	/*
	 * Implemented methods
	 */

	@Override
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		Class.forName(driver).getConstructor().newInstance();
		final Connection result = DriverManager.getConnection(url, user, password);

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public int executeUpdate(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(statement));
		if (!HelperString.isValid(statement)) {
			throw new RuntimeExceptionIsNullOrEmpty("statement"); //$NON-NLS-1$
		}

		Statement stmt = null;
		Connection con = null;

		try {
			con = getConnection();
			stmt = con.createStatement();

			final int result = stmt.executeUpdate(statement);

			if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
			return result;
		} finally {
			if (null != con) {
				con.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
	}

	@Override
	public boolean execute(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(statement));
		if (!HelperString.isValid(statement)) {
			throw new RuntimeExceptionIsNullOrEmpty("statement"); //$NON-NLS-1$
		}

		Statement stmt = null;
		Connection con = null;

		try {
			con = getConnection();
			stmt = con.createStatement();

			final boolean result = stmt.execute(statement);

			if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
			return result;
		} finally {
			if (null != con) {
				con.close();
			}
			if (null != stmt) {
				stmt.close();
			}
		}
	}
}
