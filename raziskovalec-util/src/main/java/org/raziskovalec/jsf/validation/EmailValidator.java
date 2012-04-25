/*
 * Copyright 2011 Rene Svetina.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.raziskovalec.jsf.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.raziskovalec.jsf.Functions;
import org.raziskovalec.jsf.JSFUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * For email validation.
 * 
 * @author Rene Svetina
 */
public final class EmailValidator implements
		Validator
{
	// =================================================================================================================
	// Fields
	// =================================================================================================================
	private final Logger	logger	= LoggerFactory.getLogger(getClass());
	private Integer			numberOfAddresses;

	// =================================================================================================================
	// Constructors
	// =================================================================================================================

	/**
	 * Default constructor.
	 */
	public EmailValidator()
	{
		logger.trace("Creating email validator.");
	}

	// =================================================================================================================
	// Methods
	// =================================================================================================================

	@Override
	public void validate(final FacesContext context, final UIComponent component, final Object value)
	{
		if (!UIInput.isEmpty(value))
		{
			try
			{
				InternetAddress[] internetAddresses = InternetAddress.parse(value.toString());
				if (internetAddresses.length != getNumberOfAddresses())
				{
					FacesMessage message = new FacesMessage();
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					message.setSummary(Functions
							.msg("org.raziskovalec.web.validation.EmailValidator.numberOfAddresses.summary",
									getNumberOfAddresses(),
									JSFUtils.getLabel(context, component)));
					message.setDetail(Functions
							.msg("org.raziskovalec.web.validation.EmailValidator.numberOfAddresses.detail",
									getNumberOfAddresses(),
									JSFUtils.getLabel(context, component)));

					throw new ValidatorException(message);
				}

			} catch (AddressException e)
			{
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary(Functions.msg("org.raziskovalec.web.validation.EmailValidator.summary",
						JSFUtils.getLabel(context, component)));
				message.setDetail(Functions.msg("org.raziskovalec.web.validation.EmailValidator.detail",
						JSFUtils.getLabel(context, component)));

				throw new ValidatorException(message);
			}
		}
	}

	/**
	 * @return the numberOfAddresses
	 */
	public Integer getNumberOfAddresses()
	{
		return numberOfAddresses;
	}

	/**
	 * @param numberOfAddresses
	 *            the numberOfAddresses to set
	 */
	public void setNumberOfAddresses(final Integer numberOfAddresses)
	{
		this.numberOfAddresses = numberOfAddresses;
	}
}
