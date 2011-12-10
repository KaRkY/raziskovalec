package org.raziskovalec.web.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

public class LocalDateConverter implements Converter
{
	
	private final DateTimeFormatter	dateTimeFormatter;
	
	public LocalDateConverter()
	{
		final DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder()
		.appendDayOfMonth(2)
		.appendLiteral(':')
		.appendMonthOfYear(2)
		.appendYear(4, 4);
		dateTimeFormatter = formatterBuilder.toFormatter();
	}
	
	@Override
	public Object getAsObject(final FacesContext context, final UIComponent component, final String value)
	{
		return dateTimeFormatter.withLocale(context.getViewRoot().getLocale()).parseLocalDate(value);
	}
	
	@Override
	public String getAsString(final FacesContext context, final UIComponent component, final Object value)
	{
		return dateTimeFormatter.withLocale(context.getViewRoot().getLocale()).print((ReadableInstant) value);
	}
	
}
