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

package net.laubenberger.bogatyr.service.provider;

import java.sql.Connection;
import java.sql.SQLException;

import net.laubenberger.bogatyr.service.Service;

/**
 * This interface provides functions to connect and execute statements on SQL-Server.
 *
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.2 (20100519)
 * @since 0.6.0
 */
public interface ProviderSql extends Service {
	/**
	 * Retuns a {@link Connection} to a database.
	 *
	 * @return {@link Connection} to a database
	 * @throws Exception
	 * @see Connection
	 * @since 0.6.0
	 */
	Connection getConnection() throws Exception;

	/**
	 * Executes an SQL update statement.
	 *
	 * @param statement string in SQL
	 * @return SQL return code
	 * @throws Exception
	 * @since 0.6.0
	 */
	int executeUpdate(String statement) throws Exception;

	/**
	 * Executes a SQL statement.
	 *
	 * @param statement string in SQL
	 * @return true/false
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @since 0.6.0
	 */
	boolean execute(String statement) throws Exception;
}