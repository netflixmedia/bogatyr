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
package ch.customcode.bogatyr.service.provider;

import java.sql.Connection;
import java.sql.SQLException;

import ch.customcode.bogatyr.service.Service;

/**
 * This interface provides functions to connect and execute statements on SQL-Server.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20091109)
 * @since 0.6.0
 */
public interface ProviderSql extends Service {
	/**
	 * Connects to a database
     *
	 * @return Connection
	 * @throws Exception 
	 * @since 0.6.0
	 */
    Connection connectToDb() throws Exception;
    
	/**
	 * Executes an update
     *
	 * @param statement string in SQL
     * @return SQL return code
	 * @throws Exception 
	 * @since 0.6.0
	 */
    int executeUpdate(String statement) throws Exception;
    
    /**
	 * Executes a SQL command
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