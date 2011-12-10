package org.raziskovalec.web.jsf;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

public class MessageSourceSpringBeanFacesELResolver extends SpringBeanFacesELResolver
{
	private final Logger	logger	= LoggerFactory.getLogger(this.getClass());
	
	// @Override
	// public Object getValue(final ELContext elContext, final Object base,
	// final Object property) throws ELException
	// {
	// logger.trace("Entering method getValue(base: {}, property: {})", base,
	// property);
	// if (base instanceof MessageSource && property instanceof String)
	// {
	// final String result = ((MessageSource) base).getMessage(
	// (String) property, null, getLocale());
	//
	// logger.debug("  Result for " + property + ": " + result);
	//
	// if (null != result)
	// {
	// elContext.setPropertyResolved(true);
	// }
	//
	// return result;
	// }
	//
	// return super.getValue(elContext, base, property);
	// }
	
	private Locale getLocale()
	{
		Locale locale = LocaleBeanUtils.getLocaleFromLocaleBean();
		if (locale == null)
		{
			locale = Locale.getDefault();
		}
		return locale;
	}
	
}
