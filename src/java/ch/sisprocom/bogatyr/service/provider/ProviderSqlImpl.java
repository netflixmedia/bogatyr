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

import ch.sisprocom.bogatyr.helper.HelperString;
import ch.sisprocom.bogatyr.service.ServiceAbstract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class provides functions to connect and execute statements on SQL-Server.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.0 (20091027)
 * @since 0.2.0
 */
public class ProviderSqlImpl extends ServiceAbstract implements ProviderSql {
//	private static final Logger log = Logger.getLogger(ProviderSqlAbstract.class);
	
	// Server
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
	protected ProviderSqlImpl() {
        super();
	}
	
	protected ProviderSqlImpl(final String driver, final String url, final String user, final String password) {
        super();
        setDriver(driver);
        setUrl(url);
        this.user = user;
        this.password = password;
	}

    /**
	 * Returns the current db driver class.
	 * 
	 * @return db driver class
	 * @since 0.2.0
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * Returns the current db URL.
	 * 
	 * @return db URL
	 * @since 0.2.0
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the current db user.
	 * 
	 * @return db user
	 * @since 0.2.0
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Returns the current db user password.
	 * 
	 * @return db user password
	 * @since 0.2.0
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the current db driver class
	 * 
	 * @param driver db driver class
	 * @since 0.2.0
	 */
	public void setDriver(final String driver) {
    	if (null == driver) {
    		throw new IllegalArgumentException("driver is null!"); //$NON-NLS-1$
    	}
    	
		this.driver = driver;
	}

	/**
	 * Sets the current db URL.
	 * 
	 * @param url db URL
	 * @since 0.2.0
	 */
	public void setUrl(final String url) {
    	if (null == url) {
    		throw new IllegalArgumentException("url is null!"); //$NON-NLS-1$
    	}
		this.url = url;
	}

	/**
	 * Sets the current db user.
	 * 
	 * @param user for the db
	 * @since 0.2.0
	 */
	public void setUser(final String user) {
		this.user = user;
	}

	/**
	 * Sets the current db user password.
	 * 
	 * @param password for the user/db
	 * @since 0.2.0
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	
	/*
	 * Implemented methods
	 */	
    @Override
    public Connection connectToDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, user, password);
	}
    
    @Override
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
	
    @Override
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
