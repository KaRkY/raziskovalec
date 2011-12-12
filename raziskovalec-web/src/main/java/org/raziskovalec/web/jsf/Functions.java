package org.raziskovalec.web.jsf;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public final class Functions
{
	private Functions()
	{
	}
	
	public static String msg(final String path)
	{
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final WebApplicationContext applicationContext = FacesContextUtils.getRequiredWebApplicationContext(facesContext);
		final Map<String, MessageSource> messageSources = applicationContext.getBeansOfType(MessageSource.class);
		for (final MessageSource messageSource : messageSources.values())
		{
			try
			{
				return messageSource.getMessage(path, null, facesContext.getViewRoot().getLocale());
			}
			catch (final NoSuchMessageException e)
			{
			}
		}
		return path;
	}
}
