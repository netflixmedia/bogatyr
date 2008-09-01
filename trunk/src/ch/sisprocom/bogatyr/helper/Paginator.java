/*******************************************************************************
 * Copyright (c) 2008 by Stefan Laubenberger and Silvan Spross.
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
 *
 * Contact information:
 * --------------------
 * Stefan Laubenberger
 * Bullingerstrasse 53
 * CH-8004 Zuerich
 * <laubenberger@gmail.com>
 *
 * Silvan Spross
 * Badenerstrasse 47 
 * CH-8004 Zuerich
 * <silvan.spross@gmail.com>
 *
 *******************************************************************************/
package ch.sisprocom.bogatyr.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.sisprocom.bogatyr.helper.logger.Logger;


/**
 * The Paginator splits a list in different pages
 *
 * @author Stefan Laubenberger
 * @version 20080829
 */
public class Paginator {
    private List<?> list;
    private int numberPerPage;


    public Paginator(final List<?> list, final int numberPerPage) {
        super();
        
        this.list = list;
        this.numberPerPage = numberPerPage;

        init();
    }

    public List<?> getList() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getList");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getList", list);  //$NON-NLS-1$

		return Collections.unmodifiableList(list);
    }

    public void setList(final List<?> list) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setList", list);  //$NON-NLS-1$

        this.list = list;

        Logger.getInstance().writeMethodExit(this.getClass(), "setList");  //$NON-NLS-1$
   }

    public int getNumberPerPage() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getNumberPerPage");  //$NON-NLS-1$
		Logger.getInstance().writeMethodExit(this.getClass(), "getNumberPerPage", numberPerPage);  //$NON-NLS-1$

		return numberPerPage;
    }

    public void setNumberPerPage(final int numberPerPage) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "setNumberPerPage", numberPerPage);  //$NON-NLS-1$

		this.numberPerPage = numberPerPage;
	
		Logger.getInstance().writeMethodExit(this.getClass(), "setNumberPerPage");  //$NON-NLS-1$
    }

    /**
     * Returns the total number of pages from the list
     *
     * @return Total number of pages
     */
    public int getNumberOfPages() {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getNumberOfPages");  //$NON-NLS-1$

        if (list == null || list.isEmpty() || numberPerPage == 0) {
            return 0;
        } else if (list.size() < numberPerPage) {
            return 1;
        }

        final int pages = list.size() / numberPerPage;

        if (pages * numberPerPage < list.size()) {
            return pages + 1;
        }
        
		Logger.getInstance().writeMethodExit(this.getClass(), "getNumberOfPages", pages);  //$NON-NLS-1$
        return pages;
    }

    /**
     * Returns a requested page from the list
     *
     * @param requestedPage
     * @return List representing the requested page
     */
    public List<?> getPage(final int requestedPage) {
		Logger.getInstance().writeMethodEntry(this.getClass(), "getPage", requestedPage);  //$NON-NLS-1$

        int page = requestedPage;
        int xx = 0;
        final List<Object> result = new ArrayList<Object>();

        if (list != null) {
            if (page > getNumberOfPages()) {
                page = getNumberOfPages();
            }
            if (page < 1) {
                page = 1;
            }

            boolean isLoop = true;
            for (int ii = (page - 1) * numberPerPage; ii < page * numberPerPage && isLoop; ii++) {
                if (list.size() > ii) {
                    result.add(list.get(ii));
                    xx++;
                } else {
                    isLoop = false;
                }
            }
        }
        
		Logger.getInstance().writeMethodExit(this.getClass(), "getPage", result);  //$NON-NLS-1$
        return result;
	}

    
    /*
	 * Private methods
	 */
	private void init() {
		Logger.getInstance().writeDebug(this.getClass(), "init",  toString()); //$NON-NLS-1$
	}


	/*
	 * Overridden methods
	 */
	@Override
	public String toString() {
		return HelperGeneral.toString(this);
	}
}
