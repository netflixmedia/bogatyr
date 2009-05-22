/*******************************************************************************
 * Copyright (c) 2007-2009 by SiSprocom GmbH.
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
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 *
 * <http://www.sisprocom.ch>
 *
 * <s.laubenberger@sisprocom.ch>
 * <s.spross@sisprocom.ch>
 * 
 *******************************************************************************/
package ch.sisprocom.bogatyr.service.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.sisprocom.bogatyr.helper.HelperObject;
import ch.sisprocom.bogatyr.helper.HelperString;


/**
 * This class provides functions to connect and execute statements on SQL-Server.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 20090522
 */
public class ProviderSql  implements IProviderSql {
//	private static final Logger log = Logger.getLogger(ProviderSqlAbstract.class);
	
	// Server
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	protected ProviderSql(final String driver, final String url, final String user, final String password) {
        super();
        setDriver(driver);
        setUrl(url);
        this.user = user;
        this.password = password;
	}
	
	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setDriver(final String driver) {
    	if (null == driver) {
    		throw new IllegalArgumentException("driver is null!"); //$NON-NLS-1$
    	}
    	
		this.driver = driver;
	}

	public void setUrl(final String url) {
    	if (null == url) {
    		throw new IllegalArgumentException("url is null!"); //$NON-NLS-1$
    	}
		this.url = url;
	}

	public void setUser(final String user) {
		this.user = user;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
 	
	
	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
	
	
	/*
	 * Implemented methods
	 */	
	/**
	 * Connects to a database
     *
	 * @return Connection
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
    public Connection connectToDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {		
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, user, password);
	}
    
	/**
	 * Executes an update
     *
	 * @param statement string in SQL
     * @return SQL-Code
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
    public int executeUpdate(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	if (!HelperString.isValid(statement)) {
    		throw new IllegalArgumentException("statement is null or empty!"); //$NON-NLS-1$
    	}

    	Statement stmt = null;
        Connection con = null;
        final int result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
//			log.debug(stmt.toString());
			
			result = stmt.executeUpdate(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}
		return result;
    }
	
    /**
	 * Executes an SQL command
     *
	 * @param statement string in SQL
     * @return true/false
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
	 */
    public boolean execute(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException  {
    	if (!HelperString.isValid(statement)) {
    		throw new IllegalArgumentException("statement is null or empty!"); //$NON-NLS-1$
    	}
    	
    	Statement stmt = null;
        Connection con = null;
        final boolean result;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
//			log.debug(stmt.toString());
			
			result = stmt.execute(statement);
		} finally {
			if (con != null) {
                con.close();
            }
			if (stmt != null) {
                stmt.close();
            }
		}
		return result;
    }  
}
