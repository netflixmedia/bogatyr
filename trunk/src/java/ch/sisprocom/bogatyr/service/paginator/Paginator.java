/*******************************************************************************
 * Copyright (c) 2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.service.paginator;

import java.util.List;

import ch.sisprocom.bogatyr.service.Service;


/**
 * Defines the methods for the implementation of a paginator.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20091109)
 * @since 0.9.0
 */
public interface Paginator extends Service {
 
    /**
     * Returns the list containing the data for the pages.
     *
     * @return Total number of pages
     * @since 0.9.0
     */
    List<?> getList();

    /**
     * Sets the list containing the data for the pages.
     * 
     * @param list containing the data for the pages
     * @since 0.9.0
     */
    void setList(final List<?> list);

    /**
     * Returns the total number of elements per page.
     *
     * @return number of elements per page
     * @since 0.9.0
     */
    int getNumberPerPage();

    /**
     * Sets the total number of elements per page.
     * 
     * @param numberPerPage elements per page
     * @since 0.9.0
     */
    void setNumberPerPage(final int numberPerPage);
    
    /**
     * Returns the total number of pages from the list.
     *
     * @return total number of pages
     * @since 0.9.0
     */
    int getNumberOfPages();
    
    /**
     * Returns a requested page from the list.
     *
     * @param requestedPage from the paginator
     * @return list representing the requested page
     * @since 0.9.0
     */
    List<?> getPage(final int requestedPage);
}
