package org.raziskovalec.web.jsf;

import java.util.Locale;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;

public class LocaleViewHandler extends ViewHandlerWrapper
{
	private final ViewHandler	parent;
	
	public LocaleViewHandler(final ViewHandler parent)
	{
		this.parent = parent;
	}
	
	@Override
	public Locale calculateLocale(final FacesContext context)
	{
		final Locale locale = (Locale)
				context.getExternalContext().getSessionMap().get("locale");
		if (locale == null)
			return super.calculateLocale(context);
		return locale;
	}
	
	@Override
	public ViewHandler getWrapped()
	{
		return parent;
	}
	
}
