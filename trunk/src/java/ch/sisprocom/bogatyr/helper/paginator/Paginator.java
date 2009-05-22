/*******************************************************************************
 * Copyright (c) 2008-2009 by SiSprocom GmbH.
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
package ch.sisprocom.bogatyr.helper.paginator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.sisprocom.bogatyr.helper.HelperCollection;
import ch.sisprocom.bogatyr.helper.HelperObject;


/**
 * The Paginator splits a list in different pages.
 *
 * @author Stefan Laubenberger
 * @version 20090522
 */
public class Paginator {
    private List<?> list;
    private int numberPerPage;


    public Paginator() {
        super();
    }

    public Paginator(final List<?> list, final int numberPerPage) {
        super();
        
        setList(list);
        setNumberPerPage(numberPerPage);
    }

    public List<?> getList() {
		return Collections.unmodifiableList(list);
    }

    public void setList(final List<?> list) {
		if (null == list) {
			throw new IllegalArgumentException("list is null!"); //$NON-NLS-1$
		}

        this.list = Collections.unmodifiableList(list);
   }

    public int getNumberPerPage() {
		return numberPerPage;
    }

    public void setNumberPerPage(final int numberPerPage) {
		if (0 > numberPerPage) {
			throw new IllegalArgumentException("numberPerPage must be positive: " + numberPerPage); //$NON-NLS-1$
		}

    	this.numberPerPage = numberPerPage;
    }

    /**
     * Returns the total number of pages from the list.
     *
     * @return Total number of pages
     */
    public int getNumberOfPages() {
        if (!HelperCollection.isValid(list) || 0 == numberPerPage) {
            return 0;
        } else if (list.size() < numberPerPage) {
            return 1;
        }

        final int pages = list.size() / numberPerPage;

        if (pages * numberPerPage < list.size()) {
            return pages + 1;
        }
        return pages;
    }

    /**
     * Returns a requested page from the list.
     *
     * @param requestedPage from the paginator
     * @return List representing the requested page
     */
    public List<?> getPage(final int requestedPage) {
        int page = requestedPage;
        final List<Object> result = new ArrayList<Object>(numberPerPage);

        if (list != null) {
            if (page > getNumberOfPages()) {
                page = getNumberOfPages();
            }
            if (1 > page) {
                page = 1;
            }

            boolean isLoop = true;

            for (int ii = (page - 1) * numberPerPage; ii < page * numberPerPage && isLoop; ii++) {
                if (list.size() > ii) {
                    result.add(list.get(ii));
                } else {
                    isLoop = false;
                }
            }
        }
        return result;
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperObject.toString(this);
	}
}
