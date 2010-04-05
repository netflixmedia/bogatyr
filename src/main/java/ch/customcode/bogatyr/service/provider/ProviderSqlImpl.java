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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.customcode.bogatyr.helper.HelperLog;
import ch.customcode.bogatyr.helper.HelperString;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNullOrEmpty;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * This class provides functions to connect and execute statements on SQL-Server.
 * 
 * @author Stefan Laubenberger
 * @author Silvan Spross
 * @version 0.9.1 (20100405)
 * @since 0.2.0
 */
public class ProviderSqlImpl extends ServiceAbstract implements ProviderSql {
	private static final Logger log = LoggerFactory.getLogger(ProviderSqlImpl.class);
	
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
        log.trace(HelperLog.constructor(driver, url, user, password));
        
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
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(driver));
		return driver;
	}

	/**
	 * Returns the current db URL.
	 * 
	 * @return db URL
	 * @since 0.2.0
	 */
	public String getUrl() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(url));
		return url;
	}

	/**
	 * Returns the current db user.
	 * 
	 * @return db user
	 * @since 0.2.0
	 */
	public String getUser() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(user));
		return user;
	}

	/**
	 * Returns the current db user password.
	 * 
	 * @return db user password
	 * @since 0.2.0
	 */
	public String getPassword() {
		log.debug(HelperLog.methodStart());
		
		log.debug(HelperLog.methodExit(password));
		return password;
	}

	/**
	 * Sets the current db driver class
	 * 
	 * @param driver db driver class
	 * @since 0.2.0
	 */
	public void setDriver(final String driver) {
		log.debug(HelperLog.methodStart(driver));
    	if (null == driver) {
    		throw new RuntimeExceptionIsNull("driver"); //$NON-NLS-1$
    	}
    	
		this.driver = driver;

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Sets the current db URL.
	 * 
	 * @param url db URL
	 * @since 0.2.0
	 */
	public void setUrl(final String url) {
		log.debug(HelperLog.methodStart(url));
    	if (null == url) {
    		throw new RuntimeExceptionIsNull("url"); //$NON-NLS-1$
    	}
		this.url = url;

		log.debug(HelperLog.methodExit());
	}

	/**
	 * Sets the current db user.
	 * 
	 * @param user for the db
	 * @since 0.2.0
	 */
	public void setUser(final String user) {
		log.debug(HelperLog.methodStart(user));
		if (null == user) {
			throw new RuntimeExceptionIsNull("user"); //$NON-NLS-1$
		}

		this.user = user;
		
		log.debug(HelperLog.methodExit());
	}

	/**
	 * Sets the current db user password.
	 * 
	 * @param password for the user/db
	 * @since 0.2.0
	 */
	public void setPassword(final String password) {
		log.debug(HelperLog.methodStart(password));
		if (null == password) {
			throw new RuntimeExceptionIsNull("password"); //$NON-NLS-1$
		}

		this.password = password;
		
		log.debug(HelperLog.methodExit());
	}

	
	/*
	 * Implemented methods
	 */	
    @Override
    public Connection connectToDb() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
		log.debug(HelperLog.methodStart());
		
		Class.forName(driver).getConstructor().newInstance();
		final Connection result = DriverManager.getConnection(url, user, password);
		
		log.debug(HelperLog.methodExit(result));
		return result;
	}
    
    @Override
    public int executeUpdate(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException {
		log.debug(HelperLog.methodStart(statement));
    	if (!HelperString.isValid(statement)) {
    		throw new RuntimeExceptionIsNullOrEmpty("statement"); //$NON-NLS-1$
    	}

    	Statement stmt = null;
        Connection con = null;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			final int result = stmt.executeUpdate(statement);
			
			log.debug(HelperLog.methodExit(result));
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
    public boolean execute(final String statement) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, NoSuchMethodException, InvocationTargetException  {
		log.debug(HelperLog.methodStart(statement));
    	if (!HelperString.isValid(statement)) {
    		throw new RuntimeExceptionIsNullOrEmpty("statement"); //$NON-NLS-1$
    	}
    	
    	Statement stmt = null;
        Connection con = null;
        
        try {
	    	con = connectToDb();
			stmt = con.createStatement();
	
			final boolean result = stmt.execute(statement);
			
			log.debug(HelperLog.methodExit(result));
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
