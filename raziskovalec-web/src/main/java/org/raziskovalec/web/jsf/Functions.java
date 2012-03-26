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
package org.raziskovalec.web.jsf;

import java.util.Map;

import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * JSF functions.
 * 
 * @author Rene Svetina
 */
public final class Functions
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private static final Logger	LOGGER	= LoggerFactory.getLogger(Functions.class);

	// =================================================================================================================
	// Constructors
	// =================================================================================================================
	private Functions()
	{
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	/**
	 * Localized message.
	 * 
	 * @param path
	 *            message path.
	 * @return localized message.
	 */
	public static String msg(final String path)
	{
		return msg(path, (Object[]) null);
	}

	/**
	 * Localized message with arguments.
	 * 
	 * @param path
	 *            localized message path.
	 * @param args
	 *            arguments.
	 * @return actual message.
	 */
	public static String msg(final String path, final Object... args)
	{
		LOGGER.trace("Entering method msg(path: '{}')", path);
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final WebApplicationContext applicationContext = FacesContextUtils
				.getRequiredWebApplicationContext(facesContext);
		final Map<String, MessageSource> messageSources = applicationContext.getBeansOfType(MessageSource.class);
		for (final MessageSource messageSource : messageSources.values())
		{
			try
			{
				final String message = messageSource.getMessage(path, args, facesContext.getViewRoot().getLocale());
				LOGGER.trace("Leaving method msg(): '{}'", message);
				return message;
			} catch (final NoSuchMessageException e)
			{
				LOGGER.trace("No message found.");
			}
		}
		LOGGER.trace("Leaving method msg(): '{}'", path);
		return path;
	}
}
