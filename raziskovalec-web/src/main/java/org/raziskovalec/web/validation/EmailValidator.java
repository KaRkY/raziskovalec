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

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.raziskovalec.base.ObjectsUtil;
import org.raziskovalec.web.jsf.Functions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.faces.util.MessageFactory;

/**
 * For email validation.
 * 
 * @author Rene Svetina
 * 
 */
public class EmailValidator implements
		Validator
{
	// ========================================================================
	// Fields
	// ========================================================================
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// ========================================================================
	// Constructors
	// ========================================================================

	/**
	 * Default constructor.
	 */
	public EmailValidator()
	{
		logger.trace("Creating email validator.");
	}

	// ========================================================================
	// Methods
	// ========================================================================

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
	{
		try
		{
			InternetAddress[] internetAddresses = InternetAddress.parse(value.toString());
			if (internetAddresses.length != Integer.parseInt(getAttribute("numberOfAddresses", component)))
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions
						.msg("org.raziskovalec.web.validation.EmailValidator.numberOfAddresses.summary",
								getAttribute("numberOfAddresses", component),
								MessageFactory.getLabel(context, component)));
				message.setDetail(Functions
						.msg("org.raziskovalec.web.validation.EmailValidator.numberOfAddresses.detail",
								getAttribute("numberOfAddresses", component),
								MessageFactory.getLabel(context, component)));

				throw new ValidatorException(message);
			}

		} catch (AddressException e)
		{
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(Functions.msg("org.raziskovalec.web.validation.EmailValidator.summary",
					MessageFactory.getLabel(context, component)));
			message.setDetail(Functions.msg("org.raziskovalec.web.validation.EmailValidator.detail",
					MessageFactory.getLabel(context, component)));

			throw new ValidatorException(message);
		}

	}

	private String getAttribute(final String name, final UIComponent component)
	{
		return ObjectsUtil.toStringOrNull(component.getAttributes().get(name));
	}
}
