package org.raziskovalec.web.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.LocaleUtils;

public class LocaleConverter implements Converter
{
	
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value)
	{
		return LocaleUtils.toLocale(value);
	}
	
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value)
	{
		return value.toString();
	}
	
}
