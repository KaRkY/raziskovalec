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
 * Telephone number validator.
 * 
 * @author Rene Svetina
 */
public final class PhoneNumberValidator implements
										Validator
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private static final Pattern	PHONE_NUMBER_PATTERN	= Pattern.compile("[+]?(0-9){0,3}[0-9- ]+");
	private final Logger			logger					= LoggerFactory.getLogger(getClass());

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	/**
	 * Default constructor.
	 */
	public PhoneNumberValidator()
	{
		logger.trace("Creating telephone number validator.");
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================
	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
	{
		if (!UIInput.isEmpty(value))
		{
			Matcher phoneNumberMatcher = PHONE_NUMBER_PATTERN.matcher(value.toString());
			if (!phoneNumberMatcher.matches())
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions
						.msg("org.raziskovalec.web.validation.TelephoneNumberValidator.summary",
								MessageFactory.getLabel(context, component)));
				message.setDetail(Functions
						.msg("org.raziskovalec.web.validation.TelephoneNumberValidator.detail",
								MessageFactory.getLabel(context, component)));

				throw new ValidatorException(message);
			}
		}
	}

}
