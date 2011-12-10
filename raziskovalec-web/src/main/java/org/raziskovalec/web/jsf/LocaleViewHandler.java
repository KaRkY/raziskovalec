package org.raziskovalec.web.jsf;

import java.util.Locale;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocaleViewHandler extends ViewHandlerWrapper
{
	private final Logger		logger	= LoggerFactory.getLogger(this.getClass());
	private final ViewHandler	parent;
	
	public LocaleViewHandler(final ViewHandler parent)
	{
		logger.debug("Creating LocaleViewHandler");
		this.parent = parent;
	}
	
	@Override
	public Locale calculateLocale(final FacesContext context)
	{
		logger.trace("Entering method calculateLocale()");
		Locale currentLocale = LocaleBeanUtils.getLocaleFromLocaleBean();
		
		if (currentLocale == null)
		{
			currentLocale = super.calculateLocale(context);
			
		}
		final UIViewRoot viewRoot = context.getViewRoot();
		if (viewRoot != null)
		{
			viewRoot.setLocale(currentLocale);
		}
		logger.trace("Leaving method calculateLocale(): {}", currentLocale);
		return currentLocale;
	}
	
	@Override
	public ViewHandler getWrapped()
	{
		return parent;
	}
	
}
