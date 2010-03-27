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
package ch.customcode.bogatyr.service.paginator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.customcode.bogatyr.helper.HelperCollection;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionIsNull;
import ch.customcode.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import ch.customcode.bogatyr.service.ServiceAbstract;


/**
 * The implementation for a paginator.
 *
 * @author Stefan Laubenberger
 * @version 0.9.0 (20100209)
 * @since 0.5.0
 */
public class PaginatorImpl extends ServiceAbstract implements Paginator {
    private List<?> list;
    private int numberPerPage;


    public PaginatorImpl() {
        super();
    }

    public PaginatorImpl(final List<?> list, final int numberPerPage) {
        super();
        
        setList(list);
        setNumberPerPage(numberPerPage);
    }

    
	/*
     * Implemented methods
     */
    @Override
    public List<?> getList() {
		return list;
    }

    @Override
    public void setList(final List<?> list) {
		if (null == list) {
			throw new RuntimeExceptionIsNull("list"); //$NON-NLS-1$
		}

        this.list = Collections.unmodifiableList(list);
    }

    @Override
    public int getNumberPerPage() {
		return numberPerPage;
    }

    @Override
    public void setNumberPerPage(final int numberPerPage) {
		if (0 > numberPerPage) {
			throw new RuntimeExceptionMustBeGreater("numberPerPage", numberPerPage, 0); //$NON-NLS-1$
		}

    	this.numberPerPage = numberPerPage;
    }

    @Override
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

    @Override
    public List<?> getPage(final int requestedPage) {
		if (0 > requestedPage) {
			throw new RuntimeExceptionMustBeGreater("requestedPage", requestedPage, 0); //$NON-NLS-1$
		}

		int page = requestedPage;
        final List<Object> result = new ArrayList<Object>(numberPerPage);

        if (null != list) {
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
}
