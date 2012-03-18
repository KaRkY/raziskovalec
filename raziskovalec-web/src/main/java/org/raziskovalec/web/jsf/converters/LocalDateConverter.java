/**
 * Copyright 2012 Rene Svetina
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package org.raziskovalec.web.jsf.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converter for converting LocalDate to String and back.
 * 
 * @author Rene Svetina
 */
public final class LocalDateConverter implements
										Converter
{
	private final DateTimeFormatter	dateTimeFormatter;
	// ========================================================================
	// Fields
	// ========================================================================
	private final Logger			logger	= LoggerFactory.getLogger(this.getClass());

	// ========================================================================
	// Constructors
	// ========================================================================
	/**
	 * Default constructor.
	 */
	public LocalDateConverter()
	{
		logger.trace("Creating LocalDateConverter");
		final DateTimeFormatterBuilder formatterBuilder = new DateTimeFormatterBuilder()
				.appendDayOfMonth(2)
				.appendLiteral(':')
				.appendMonthOfYear(2)
				.appendYear(4, 4);
		dateTimeFormatter = formatterBuilder.toFormatter();
		logger.debug("Created formatter: '{}'", dateTimeFormatter);
	}

	// ========================================================================
	// Methods
	// ========================================================================

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
