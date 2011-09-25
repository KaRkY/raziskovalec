package org.raziskovalec.web.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * 
 * @author Rene Svetina
 */
public class Slf4jLoggingFilter extends AbstractRequestLoggingFilter
{
	private final transient Logger	logger	= LoggerFactory.getLogger(this
													.getClass());

	@Override
	protected void afterRequest(final HttpServletRequest request,
			final String message)
	{
		this.logger.debug(message);
	}

	@Override
	protected void beforeRequest(final HttpServletRequest request,
			final String message)
	{
		this.logger.debug(message);
	}

}
