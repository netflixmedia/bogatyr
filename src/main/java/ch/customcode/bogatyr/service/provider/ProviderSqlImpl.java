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
package ch.customcode.bogatyr.service.provider;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * This class provides functions to connect and execute statements on SQL-Server.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100216)
 * @since 0.2.0
 */
public class ProviderSqlImpl extends ServiceAbstract implements ProviderSql {
	// Server
	private String driver;
	private String url;
	private String user;
	private String password;
	
	
//	public ProviderSqlImpl() {
//        super();
//	}
	
	public ProviderSqlImpl(final String driver, final String url, final String user, final String password) {
        super();
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
    		throw new RuntimeExceptionIsNull("driver"); //$NON-NLS-1$
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
    		throw new RuntimeExceptionIsNull("url"); //$NON-NLS-1$
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
		if (null == user) {
			throw new RuntimeExceptionIsNull("user"); //$NON-NLS-1$
		}

		this.user = user;
	}

	/**
	 * Sets the current db user password.
	 * 
	 * @param password for the user/db
	 * @since 0.2.0
	 */
	public void setPassword(final String password) {
		if (null == password) {
			throw new RuntimeExceptionIsNull("password"); //$NON-NLS-1$
		}

		this.password = password;
	}

	
	/*
	 * Implemented methods
	 */	
    @Override
    public Connection connectToDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
        Class.forName(driver).getConstructor().newInstance();
		return DriverManager.getConnection(url, user, password);
	}
    
    @Override
    public int executeUpdate(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
    	if (!HelperString.isValid(statement)) {
    		throw new RuntimeExceptionIsNullOrEmpty("statement"); //$NON-NLS-1$
    	}

    	Statement stmt = null;
        Connection con = null;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			return stmt.executeUpdate(statement);
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
    public boolean execute(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException  {
    	if (!HelperString.isValid(statement)) {
    		throw new RuntimeExceptionIsNullOrEmpty("statement"); //$NON-NLS-1$
    	}
    	
    	Statement stmt = null;
        Connection con = null;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			return stmt.execute(statement);
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
