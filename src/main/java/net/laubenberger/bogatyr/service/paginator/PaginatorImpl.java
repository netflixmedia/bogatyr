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

package net.laubenberger.bogatyr.service.paginator;

import java.util.ArrayList;
import java.util.List;

import net.laubenberger.bogatyr.helper.HelperCollection;
import net.laubenberger.bogatyr.helper.HelperLog;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionIsNull;
import net.laubenberger.bogatyr.misc.exception.RuntimeExceptionMustBeGreater;
import net.laubenberger.bogatyr.service.ServiceAbstract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The implementation for a paginator.
 *
 * @author Stefan Laubenberger
 * @version 0.9.2 (20100514)
 * @since 0.5.0
 */
public class PaginatorImpl extends ServiceAbstract implements Paginator {
	private static final Logger log = LoggerFactory.getLogger(PaginatorImpl.class);

	private List<?> list;
	private int numberPerPage;


	public PaginatorImpl() {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor());
	}

	public PaginatorImpl(final List<?> list, final int numberPerPage) {
		super();
		if (log.isTraceEnabled()) log.trace(HelperLog.constructor(list, numberPerPage));

		setList(list);
		setNumberPerPage(numberPerPage);
	}


	/*
	 * Implemented methods
	 */

	@Override
	public List<?> getList() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(list));
		return list;
	}

	@Override
	public void setList(final List<?> list) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(list));
		if (null == list) {
			throw new RuntimeExceptionIsNull("list"); //$NON-NLS-1$
		}

		this.list = list;
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public int getNumberPerPage() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());

		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(numberPerPage));
		return numberPerPage;
	}

	@Override
	public void setNumberPerPage(final int numberPerPage) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(numberPerPage));
		if (0 > numberPerPage) {
			throw new RuntimeExceptionMustBeGreater("numberPerPage", numberPerPage, 0); //$NON-NLS-1$
		}

		this.numberPerPage = numberPerPage;
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit());
	}

	@Override
	public int getNumberOfPages() {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart());
		if (!HelperCollection.isValid(list) || 0 == numberPerPage) {
			return 0;
		} else if (list.size() < numberPerPage) {
			return 1;
		}

		int result = list.size() / numberPerPage;

		if (result * numberPerPage < list.size()) {
			result++;
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}

	@Override
	public List<?> getPage(final int requestedPage) {
		if (log.isDebugEnabled()) log.debug(HelperLog.methodStart(requestedPage));
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

//            boolean isLoop = true;

			for (int ii = (page - 1) * numberPerPage; ii < page * numberPerPage && list.size() > ii; ii++) {
//                if (list.size() > ii) {
				result.add(list.get(ii));
//                } else {
//                    isLoop = false;
//                }
			}
		}
		if (log.isDebugEnabled()) log.debug(HelperLog.methodExit(result));
		return result;
	}
}
