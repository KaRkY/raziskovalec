/*
 * Copyright 2011 Rene Svetina.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.raziskovalec.web.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.raziskovalec.web.jsf.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.faces.util.MessageFactory;

/**
 * Validates URL's.
 * 
 * @author Rene Svetina
 */
public final class URLValidator implements
								Validator
{
	// ========================================================================
	// Fields
	// ========================================================================
	private static final Pattern	URL_PATTERN;
	private final Logger			logger	= LoggerFactory.getLogger(getClass());

	// ========================================================================
	// Constructors
	// ========================================================================

	static
	{
		URL_PATTERN = Pattern.compile("^http[s]?://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S*)?$");
	}

	/**
	 * Default constructor.
	 */
	public URLValidator()
	{
		logger.trace("Creating URL validator.");
	}

	// ========================================================================
	// Methods
	// ========================================================================

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
	{
		if (!UIInput.isEmpty(value))
		{
			Matcher urlMatcher = URL_PATTERN.matcher(value.toString());

			if (!urlMatcher.matches())
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions
						.msg("org.raziskovalec.web.validation.URLValidator.summary",
								MessageFactory.getLabel(context, component)));
				message.setDetail(Functions
						.msg("org.raziskovalec.web.validation.URLValidator.detail",
								MessageFactory.getLabel(context, component)));

				throw new ValidatorException(message);
			}
		}
	}

}
