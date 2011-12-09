package org.raziskovalec.web.jsf;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ELException;
import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

public class MessageSourceSpringBeanFacesELResolver extends SpringBeanFacesELResolver
{
	
	@Override
	public Object getValue(final ELContext elContext, final Object base, final Object property)
			throws ELException
			{
		if (base instanceof MessageSource && property instanceof String)
		{
			final String result = ((MessageSource) base).getMessage(
					(String) property, null, getLocale());
			
			logger.debug("  Result for " + property + ": " + result);
			
			if (null != result)
			{
				elContext.setPropertyResolved(true);
			}
			
			return result;
		}
		
		return super.getValue(elContext, base, property);
			}
	
	private Locale getLocale()
	{
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		return facesContext.getViewRoot().getLocale();
	}
	
}
